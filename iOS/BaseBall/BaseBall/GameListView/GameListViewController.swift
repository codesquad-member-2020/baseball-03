//
//  GameListViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/05.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameListViewController: UIViewController {
    
    private var gameListLabel: GameListLabel = GameListLabel()
    private var scrollView: UIScrollView = UIScrollView()
    private var gameListStackView: GameListStackView = GameListStackView()
    private var useCase: MatchListUseCase = MatchListUseCase(networkManager: NetworkManager())
    private var imageUseCase: ImageUseCase = ImageUseCase(networkManager: NetworkManager())
    private var matchListManager = MatchListManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubview()
        setupObserver()
        setupGameListLabel()
        setupSrcollView()
        setupGameListStackView()
        setupUseCase()
    }
    
    private func setupUseCase() {
        useCase.requestMatchList(failureHandler: {
            AlertView.errorHandling(viewController: self, error: $0)
        }, completed: {
            self.matchListManager.insertMatchList(matchList: $0)
        })
    }
    
    private func setupObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(matchListInserted),
                                               name: .MatchListInserted,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(homeTeamSelected(_:)),
                                               name: .HomeTeamSelected,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(awayTeamSelected(_:)),
                                               name: .AwayTeamSelected,
                                               object: nil)
    }
    
    private func setupSubview() {
        self.view.addSubview(gameListLabel)
        self.view.addSubview(scrollView)
    }
    
    private func setupGameListLabel() {
        gameListLabel.translatesAutoresizingMaskIntoConstraints = false
        gameListLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        gameListLabel.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.2).isActive = true
        gameListLabel.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        gameListLabel.text = "게임 목록"
    }
    
    private func setupSrcollView() {
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.topAnchor.constraint(equalTo: gameListLabel.bottomAnchor).isActive = true
        scrollView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor).isActive = true
        scrollView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor).isActive = true
        scrollView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
    }
    
    private func setupGameListStackView() {
        gameListStackView.translatesAutoresizingMaskIntoConstraints = false
        scrollView.addSubview(gameListStackView)
        gameListStackView.leadingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.leadingAnchor).isActive = true
        gameListStackView.trailingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.trailingAnchor).isActive = true
        gameListStackView.topAnchor.constraint(equalTo: scrollView.contentLayoutGuide.topAnchor).isActive = true
        gameListStackView.bottomAnchor.constraint(equalTo: scrollView.contentLayoutGuide.bottomAnchor).isActive = true
        gameListStackView.widthAnchor.constraint(equalTo: scrollView.frameLayoutGuide.widthAnchor, multiplier: 1).isActive = true
    }
    
    private func setupGameListViews() {
        for index in 0..<matchListManager.count() {
            DispatchQueue.main.async {
                guard let match = self.matchListManager.match(at: index) else {return}
                let gameListView = GameListView()
                self.gameListStackView.addArrangedSubview(gameListView)
               
                gameListView.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.2).isActive = true
                gameListView.widthAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.widthAnchor, multiplier: 0.95).isActive = true
                
                gameListView.setGameLabel(order: match.matchId)
                
                self.imageUseCase.requestTeamImage(name: match.away.name + "_thumbnail", from: match.away.thumbnail_url, failureHandler: {
                    AlertView.errorHandling(viewController: self, error: $0)
                }, completed: {
                    let data = $0
                    DispatchQueue.main.async {
                        let image = UIImage(named: data.path)
                        gameListView.setAwayTeamImage(image: image)
                    }
                })
                
                self.imageUseCase.requestTeamImage(name: match.home.name + "_thumbnail", from: match.home.thumbnail_url, failureHandler: {
                    AlertView.errorHandling(viewController: self, error: $0)
                }, completed: {
                    let data = $0
                    DispatchQueue.main.async {
                        let image = UIImage(named: data.path)
                        gameListView.setHomeTeamImage(image: image)
                    }
                })
            }
        }
    }
    
    @objc func matchListInserted() {
        setupGameListViews()
    }
    
    @objc func homeTeamSelected(_ notification: Notification) {
        guard let gameTabBarController = storyboard?.instantiateViewController(identifier: "GameTabBarController") as? UITabBarController else {return}
        gameTabBarController.modalPresentationStyle = .fullScreen
        present(gameTabBarController, animated: true)
    }
    
    @objc func awayTeamSelected(_ notification: Notification) {
        guard let gameViewController = storyboard?.instantiateViewController(identifier: "GameTabBarController") as? UITabBarController else {return}
        gameViewController.modalPresentationStyle = .fullScreen
        present(gameViewController, animated: true)
    }
}
