//
//  GameListViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/05.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameListViewController: UIViewController {

    private var gameListLabel: UILabel = {
        let label = UILabel()
        label.font = .boldSystemFont(ofSize: 35)
        label.textColor = .white
        return label
    }()
    private var scrollView: UIScrollView = UIScrollView()
    private var gameListStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.distribution = .fill
        stackView.spacing = 20
        stackView.alignment = .center
        return stackView
    }()
    private var gameListViews: [GameListView] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubview()
        setupGameListLabel()
        setupSrcollView()
        setupGameListStackView()
        setupGameListViews()
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
        for _ in 0..<10 {
            let gameListView = GameListView()
            gameListViews.append(gameListView)
            gameListStackView.addArrangedSubview(gameListView)
            gameListView.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.2).isActive = true
            gameListView.widthAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.widthAnchor, multiplier: 0.95).isActive = true
        }
    }
}
