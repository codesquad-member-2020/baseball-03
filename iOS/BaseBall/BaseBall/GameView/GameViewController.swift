//
//  GameViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameViewController: UIViewController {
    
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
