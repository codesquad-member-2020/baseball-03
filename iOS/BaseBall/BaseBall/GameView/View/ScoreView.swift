//
//  ScoreView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class ScoreView: UIView {
    
    private var versusLabel = UILabel()
    private var homeTeamStackView = UIStackView()
    private var homeTeamScoreLabel = UILabel()
    private var homeTeamNameLabel = UILabel()
    private var awayTeamStackView = UIStackView()
    private var awayTeamNameLabel = UILabel()
    private var awayTeamScoreLabel = UILabel()
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupVersusLabel()
        setupHomeTeamStackView()
        setupHomeTeamNameLabel()
        setupHomeTeamScoreLabel()
        setupAwayTeamStackView()
        setupAwayTeamScoreLabel()
        setupAwayTeamNameLabel()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupVersusLabel()
        setupHomeTeamStackView()
        setupHomeTeamNameLabel()
        setupHomeTeamScoreLabel()
        setupAwayTeamStackView()
        setupAwayTeamScoreLabel()
        setupAwayTeamNameLabel()
    }
    
    private func setupVersusLabel() {
        self.addSubview(versusLabel)
        versusLabel.translatesAutoresizingMaskIntoConstraints = false
        versusLabel.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        versusLabel.text = "VS"
        versusLabel.font = .boldSystemFont(ofSize: versusLabel.font.pointSize)
        versusLabel.textColor = .gray
    }
    
    private func setupHomeTeamStackView() {
        self.addSubview(homeTeamStackView)
        homeTeamStackView.axis = .horizontal
        homeTeamStackView.translatesAutoresizingMaskIntoConstraints = false
        homeTeamStackView.leadingAnchor.constraint(equalTo: self.leadingAnchor).isActive = true
        homeTeamStackView.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor, constant: -10).isActive = true
        homeTeamStackView.heightAnchor.constraint(equalTo: self.heightAnchor).isActive = true
        homeTeamStackView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        homeTeamStackView.distribution = .fill
        homeTeamStackView.spacing = 10
    }
    
    private func setupHomeTeamNameLabel() {
        homeTeamNameLabel.translatesAutoresizingMaskIntoConstraints = false
        homeTeamNameLabel.font = .boldSystemFont(ofSize: 30)
        homeTeamNameLabel.text = "Hanwha"
        homeTeamNameLabel.textAlignment = .right
        homeTeamStackView.addArrangedSubview(homeTeamNameLabel)
    }
    
    private func setupHomeTeamScoreLabel() {
        homeTeamScoreLabel.translatesAutoresizingMaskIntoConstraints = false
        homeTeamScoreLabel.font = .boldSystemFont(ofSize: 30)
        homeTeamScoreLabel.text = "1"
        homeTeamScoreLabel.setContentHuggingPriority(.required, for: .horizontal)
        homeTeamStackView.addArrangedSubview(homeTeamScoreLabel)
    }
    
    private func setupAwayTeamStackView() {
        self.addSubview(awayTeamStackView)
        awayTeamStackView.axis = .horizontal
        awayTeamStackView.translatesAutoresizingMaskIntoConstraints = false
        awayTeamStackView.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor, constant: 10).isActive = true
        awayTeamStackView.trailingAnchor.constraint(equalTo: self.trailingAnchor).isActive = true
        awayTeamStackView.heightAnchor.constraint(equalTo: self.heightAnchor).isActive = true
        awayTeamStackView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        awayTeamStackView.distribution = .fill
        awayTeamStackView.spacing = 10
    }
    
    private func setupAwayTeamScoreLabel() {
        awayTeamScoreLabel.translatesAutoresizingMaskIntoConstraints = false
        awayTeamScoreLabel.font = .boldSystemFont(ofSize: 30)
        awayTeamScoreLabel.text = "1"
        awayTeamScoreLabel.setContentHuggingPriority(.required, for: .horizontal)
        awayTeamStackView.addArrangedSubview(awayTeamScoreLabel)
    }
    
    private func setupAwayTeamNameLabel() {
        awayTeamNameLabel.translatesAutoresizingMaskIntoConstraints = false
        awayTeamNameLabel.font = .boldSystemFont(ofSize: 30)
        awayTeamNameLabel.text = "Samsung"
        awayTeamNameLabel.textAlignment = .left
        awayTeamStackView.addArrangedSubview(awayTeamNameLabel)
    }
}
