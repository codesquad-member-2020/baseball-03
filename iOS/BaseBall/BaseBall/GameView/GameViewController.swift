//
//  GameViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameViewController: UIViewController {
    
    @IBOutlet weak var inningCollectionView: UICollectionView!
    @IBOutlet weak var pitchButton: UIButton!
    @IBOutlet weak var recordTableView: UITableView!
    @IBOutlet weak var electronicView: ElectronicView!
    @IBAction func pitchButtonPushed(_ sender: UIButton) {
        self.pitchButton.isEnabled = false
        UIView.animate(withDuration: 0.5) {
            self.pitchButton.alpha = 0
        }
        useCase.requestMatchUpdate(failureHandler: {
            AlertView.errorHandling(viewController: self, error: $0)
        }, completed: {
            self.matchInProgressManager.insert(match: $0)
        })
    }
    
    private var isAttack = false
    private let useCase = MatchInProgressUseCase(networkManager: NetworkManager())
    private let imageUseCase = ImageUseCase(networkManager: NetworkManager())
    private let matchInProgressManager = MatchInProgressManager()
    private let recordManager = RecordManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.electronicView.layer.cornerRadius = self.electronicView.frame.width / 20
        setupPitchButton()
        setupUseCase()
        setupObserver()
        inningCollectionView.dataSource = self
        inningCollectionView.delegate = self
        recordTableView.estimatedRowHeight = 40
        recordTableView.rowHeight = UITableView.automaticDimension
        recordTableView.dataSource = self
        recordTableView.delegate = self
    }
    
    private func setupObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(setupUI),
                                               name: .MatchInProgressInserted,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(recordsUpdated),
                                               name: .RecordsUpdated,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(updateUI
            ),
                                               name: .MatchInProgressUpdated,
                                               object: nil)
    }
    
    private func setupUseCase() {
        useCase.requestMatchInProgress(failureHandler: {
            AlertView.errorHandling(viewController: self, error: $0)
        }, completed:  {
            self.matchInProgressManager.setMatch(matchInProgress: $0)
        })
    }
    
    private func setupPitchButton() {
        pitchButton.isHidden = isAttack
        pitchButton.layer.cornerRadius = pitchButton.frame.height / 2
    }
    
    @objc func setupUI() {
        guard let team = self.matchInProgressManager.teamInfo() else {return}
        
        DispatchQueue.main.async {
            self.electronicView.setTeamName(team: team)
        }
        
        imageUseCase.loadTeamImage(name: team.away, failureHandler: {
            AlertView.alertError(viewController: self, message: $0)
        }, completed: {
            let url = $0
            DispatchQueue.main.async {
                self.electronicView.setAwayTeamImage(url: url)
            }
        })
        
        imageUseCase.loadTeamImage(name: team.home, failureHandler: {
            AlertView.alertError(viewController: self, message: $0)
        }, completed: {
            let url = $0
            DispatchQueue.main.async {
                self.electronicView.setHomeTeamImage(url: url)
            }
        })
        
        guard let pitcher = self.matchInProgressManager.currentPitcher() else {return}
        guard let hitter = self.matchInProgressManager.currentHitter() else {return}
        
        recordManager.setRecords(pitcher: pitcher, hitter: hitter)
    }
    
    @objc func recordsUpdated() {
        DispatchQueue.main.async {
            self.recordTableView.reloadData()
        }
    }
    
    @objc func updateUI() {
        DispatchQueue.main.async {
            self.pitchButton.isEnabled = true
            UIView.animate(withDuration: 0.5) {
                self.pitchButton.alpha = 1
            }
            
            guard let pitcher = self.matchInProgressManager.currentPitcher() else {return}
            guard let hitter = self.matchInProgressManager.currentHitter() else {return}
            guard let preHitter = self.matchInProgressManager.prevHitter() else {return}
            guard let log = self.matchInProgressManager.currentLog() else {return}
            guard let homeScore = self.matchInProgressManager.homeScore() else {return}
            guard let awayScore = self.matchInProgressManager.awayScore() else {return}
            
            self.recordManager.update(pitcher: pitcher, log: log)
            if preHitter.name != hitter.name {
                self.recordManager.insertNewPlayer(pitcher: pitcher, hitter: hitter)
            }
            
            self.electronicView.setSBO(log: log)
            
            self.electronicView.homeScoreLabel.text = "\(homeScore)"
            self.electronicView.awayScoreLabel.text = "\(awayScore)"
        }
    }
}

extension GameViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return recordManager.count()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "PlayerCell") as? RecordTableViewCell else {return UITableViewCell()}
        if indexPath.row == 0 {
            guard let record = recordManager.pitcher() else {return cell}
            guard let pitcher = record.player as? Pitcher else {return cell}
            cell.setRecord(pitcher: pitcher)
        } else {
            guard let record = recordManager.hitter(at: indexPath.row) else {return cell}
            cell.setRecord(record: record)
        }
        return cell
    }
}

extension GameViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}

extension GameViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 11
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "InningCell", for: indexPath) as? InningCollectionViewCell else {return UICollectionViewCell()}
        
        cell.inningLabel.text = "\(indexPath.item + 1)회"
        
        guard let selected = collectionView.indexPathsForSelectedItems?.first else {
            if indexPath.item == 0 {
                cell.select()
            }
            return cell
        }
        
        if selected.item == indexPath.item {
            cell.select()
        } else {
            cell.deSelect()
        }
        
        return cell
    }
    
}

extension GameViewController:  UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? InningCollectionViewCell else {return}
        collectionView.visibleCells.forEach {
            guard let cell = $0 as? InningCollectionViewCell else {return}
            cell.deSelect()
        }
        cell.select()
        collectionView.setContentOffset(CGPoint(x: (cell.frame.minX + cell.frame.maxX - collectionView.frame.width) / 2 , y: cell.frame.minY), animated: true)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width / 3, height: collectionView.frame.height)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        let cellWidth = collectionView.frame.width / 3
        let insetY = (collectionView.frame.width - cellWidth) / 2
        
        return UIEdgeInsets(top: 0, left: insetY, bottom: 0, right: insetY)
    }
}

