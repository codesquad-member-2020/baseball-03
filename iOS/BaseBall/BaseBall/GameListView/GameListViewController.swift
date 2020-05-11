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
    private var gameListViews: [GameListView] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubview()
        setupObserver()
        setupGameListLabel()
        setupSrcollView()
        setupGameListStackView()
        setupGameListViews()
    }
    
    private func setupObserver() {
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
        for index in 0..<10 {
            let gameListView = GameListView()
            gameListViews.append(gameListView)
            gameListStackView.addArrangedSubview(gameListView)
            gameListView.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.2).isActive = true
            gameListView.widthAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.widthAnchor, multiplier: 0.95).isActive = true
            gameListView.gameLabel.text = "GAME \(index + 1)"
        }
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
