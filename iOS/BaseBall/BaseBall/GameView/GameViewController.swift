//
//  GameViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameViewController: UIViewController {
    
    private let scoreView: ScoreView = ScoreView()

    override func viewDidLoad() {
        super.viewDidLoad()
        setupScoreView()
    }
    
    private func setupScoreView() {
        self.view.addSubview(scoreView)
        scoreView.translatesAutoresizingMaskIntoConstraints = false
        scoreView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        scoreView.widthAnchor.constraint(equalTo: self.view.widthAnchor).isActive = true
        scoreView.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.1).isActive = true
        scoreView.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor).isActive = true
    }
}
