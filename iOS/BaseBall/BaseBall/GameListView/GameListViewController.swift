//
//  GameListViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/05.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameListViewController: UIViewController {

    var gameListLabel: UILabel = {
       let label = UILabel()
        label.font = .boldSystemFont(ofSize: 35)
        label.textColor = .white
        return label
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubview()
        setupGameListLabel()
    }
    
    private func setupSubview() {
        self.view.addSubview(gameListLabel)
    }
    
    private func setupGameListLabel() {
        gameListLabel.translatesAutoresizingMaskIntoConstraints = false
        gameListLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        gameListLabel.heightAnchor.constraint(equalTo: self.view.heightAnchor, multiplier: 0.2).isActive = true
        gameListLabel.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
        gameListLabel.text = "게임 목록"
    }
}
