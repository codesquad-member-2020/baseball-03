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
    @IBOutlet weak var electronicView: ElectronicView!
    @IBAction func pitchButtonPushed(_ sender: UIButton) {
        self.pitchButton.isEnabled = false
        UIView.animate(withDuration: 0.5) {
            self.pitchButton.alpha = 0
        }
    }
    
    private var isAttack = false
    private let useCase = MatchInProgressUseCase(networkManager: NetworkManager())
    private let imageUseCase = ImageUseCase(networkManager: NetworkManager())
    private let matchInProgressManager = MatchInProgressManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.electronicView.layer.cornerRadius = self.electronicView.frame.width / 20
        setupPitchButton()
        setupUseCase()
        setupObserver()
        inningCollectionView.dataSource = self
        inningCollectionView.delegate = self
    }
    
    private func setupObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(setupUI),
                                               name: .MatchInProgressInserted,
                                               object: nil)
    }
    
    private func setupUseCase() {
        useCase.requestMatchList(failureHandler: {
            AlertView.errorHandling(viewController: self, error: $0)
        }, completed:  {
            self.matchInProgressManager.insertMatch(matchInProgress: $0)
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
    }
}

extension GameViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 11
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "InningCell", for: indexPath) as? InningCollectionViewCell else {return UICollectionViewCell()}
        cell.inningLabel.text
            = "\(indexPath.item + 1)회"
        return cell
    }
}

extension GameViewController:  UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? InningCollectionViewCell else {return}
        cell.select()
    }
    
    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? InningCollectionViewCell else {return}
        cell.deSelect()
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width / 4, height: collectionView.frame.height)
    }
}

