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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubview()
        setupGameListLabel()
        setupSrcollView()
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
        scrollView.bottomAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.bottomAnchor).isActive = true
    }
}
