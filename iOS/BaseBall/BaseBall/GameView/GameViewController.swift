//
//  GameViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameViewController: UIViewController {
    
    @IBOutlet weak var playerTableView: UITableView!
    @IBOutlet weak var recordTableView: UITableView!
    @IBOutlet weak var pitchButton: UIButton!
    @IBAction func pitchButtonPushed(_ sender: UIButton) {
        self.pitchButton.isEnabled = false
        UIView.animate(withDuration: 0.5) {
            self.pitchButton.alpha = 0
        }
    }
    
    private var isAttack = false
    private let recordDataSource = RecordDataSource()
    private let playerDataSource = PlayerDataSource()
    private let useCase = MatchInProgressUseCase(networkManager: NetworkManager())
    private let matchInProgressManager = MatchInProgressManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        playerTableView.delegate = self
        playerTableView.dataSource = playerDataSource
        recordTableView.dataSource = recordDataSource
        setupPitchButton()
        setupUseCase()
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
}

extension GameViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableView.frame.height / 2
    }
}
