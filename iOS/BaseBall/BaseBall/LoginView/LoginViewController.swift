//
//  LoginViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/05.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    
    private var logoButton = UIButton()
    private var loginLabel = UILabel()

    private let blurView = UIVisualEffectView(effect: UIBlurEffect(style: .regular))
    private let useCase = TeamListUseCase(networkManager: NetworkManager())
    private let imageUseCase = ImageUseCase(networkManager: NetworkManager())
    private let teamListManager = TeamListManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupModel()
        setupObserver()
        setupLogInImage()
        setupLoginLabel()
        setupBlurView()
    }
    
    private func setupModel() {
        useCase.requestTeamList(failureHandler: {
            self.errorHandling(error: $0)
        }, completed: {
            self.teamListManager.insertTeamList(teamList: $0)
        })
    }
    
    private func setupObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(teamListInserted),
                                               name: .TeamListInserted,
                                               object: nil)
    }
    
    private func setupLogInImage() {
        self.view.addSubview(logoButton)
        self.logoButton.translatesAutoresizingMaskIntoConstraints = false
        self.logoButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        self.logoButton.centerYAnchor.constraint(equalTo: self.view.centerYAnchor).isActive = true
        self.logoButton.imageView?.contentMode = .scaleAspectFit
        self.logoButton.addTarget(self, action: #selector(logoButtonPushed(_:)), for: .touchUpInside)
    }
    
    private func setupLoginLabel() {
        self.view.addSubview(self.loginLabel)
        self.loginLabel.translatesAutoresizingMaskIntoConstraints = false
        self.loginLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        self.loginLabel.topAnchor.constraint(equalTo: self.logoButton.bottomAnchor, constant: 30).isActive = true
        self.loginLabel.textColor = .white
        self.loginLabel.font = UIFont(name: "DungGeunMo", size: 20)
        self.loginLabel.text = "로고를 눌러서 로그인을 해주세요!"
    }
    
    private func setupBlurView() {
        self.blurView.frame = self.view.frame
    }
    
    private func alertError(message: String) {
        DispatchQueue.main.async {
            let alert = UIAlertController(title: "문제가 생겼어요", message: message, preferredStyle: .alert)
            let ok = UIAlertAction(title: "넵...", style: .default)
            alert.addAction(ok)
            self.present(alert, animated: true)
        }
    }
    
    private func errorHandling(error: NetworkManager.NetworkError) {
        alertError(message: error.message())
    }
    
    @objc func logoButtonPushed(_ sender: UIButton) {
        guard let gameListViewController = storyboard?.instantiateViewController(identifier: "GameListViewController") else {return}
        gameListViewController.modalPresentationStyle = .overFullScreen
        gameListViewController.view.backgroundColor = .clear
        self.view.addSubview(blurView)
        present(gameListViewController, animated: true)
    }
    
    @objc func teamListInserted() {
        let group = DispatchGroup()
        let queue = DispatchQueue.global()
        
        DispatchQueue.concurrentPerform(iterations: teamListManager.count()) {
            guard let team = teamListManager.team(at: $0) else {return}
            group.enter()
            queue.async {
                self.imageUseCase.requestTeamImage(name: team.name, from: team.url, failureHandler: {
                    self.errorHandling(error: $0)
                }, completed: {_ in
                    group.leave()
                })
            }
        }
        
        group.notify(queue: queue) {
            guard let team = self.teamListManager.random() else {
                return
            }
            
            self.imageUseCase.requestTeamImage(name: team.name, from: team.url, failureHandler: {
                self.errorHandling(error: $0)
            }, completed: {
                let image = UIImage(contentsOfFile: $0.path)
                DispatchQueue.main.async {
                    self.view.backgroundColor = UIColor(hex: team.color)
                    self.logoButton.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.3).isActive = true
                    self.logoButton.widthAnchor.constraint(equalToConstant: image?.size.width ?? 0 * self.logoButton.frame.height / (image?.size.height ?? 0)).isActive = true
                    self.logoButton.setImage(image, for: .normal)
                }
            })
        }
    }
}

