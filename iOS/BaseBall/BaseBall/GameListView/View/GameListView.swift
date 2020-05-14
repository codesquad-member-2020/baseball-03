//
//  GameListView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameListView: UIView {
    
    private(set) var gameLabel = UILabel()
    private var versusView = UIView()
    private var versusLabel = UILabel()
    private var homeTeamButton = UIButton()
    private var awayTeamButon = UIButton()
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setup()
        setupGameLabel()
        setupVersusView()
        setupVersusLabel()
        setupHomeTeamButton()
        setupAwayTeamButton()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
        setupGameLabel()
        setupVersusView()
        setupVersusLabel()
        setupHomeTeamButton()
        setupAwayTeamButton()
    }
    
    func setGameLabel(order: String) {
        self.gameLabel.text = "GAME " + order
    }
    
    func setHomeTeamImage(image: UIImage?) {
        homeTeamButton.widthAnchor.constraint(equalToConstant: image?.size.width ?? 0 * homeTeamButton.frame.height / (image?.size.height ?? 0)).isActive = true
        homeTeamButton.setImage(image, for: .normal)
    }

    func setAwayTeamImage(image: UIImage?) {
        awayTeamButon.widthAnchor.constraint(equalToConstant: image?.size.width ?? 0 * awayTeamButon.frame.height / (image?.size.height ?? 0)).isActive = true
        awayTeamButon.setImage(image, for: .normal)
    }
    
    private func setup() {
        self.backgroundColor = .lightGray
        self.backgroundColor = self.backgroundColor?.withAlphaComponent(0.6)
        self.layer.cornerRadius = 20
    }
    
    private func setupGameLabel() {
        let containerView = UIView()
        self.addSubview(containerView)
        containerView.translatesAutoresizingMaskIntoConstraints = false
        containerView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        containerView.topAnchor.constraint(equalTo: self.topAnchor).isActive = true
        containerView.widthAnchor.constraint(equalTo: self.widthAnchor, multiplier: 1).isActive = true
        containerView.heightAnchor.constraint(equalTo: self.heightAnchor, multiplier: 0.3).isActive = true
        
        containerView.addSubview(gameLabel)
        gameLabel.translatesAutoresizingMaskIntoConstraints = false
        gameLabel.centerXAnchor.constraint(equalTo: containerView.centerXAnchor).isActive = true
        gameLabel.centerYAnchor.constraint(equalTo: containerView.centerYAnchor).isActive = true
        gameLabel.textColor = .white
        gameLabel.font = UIFont(name: "DungGeunMo", size: 30)
    }
    
    private func setupVersusView() {
        self.addSubview(versusView)
        versusView.translatesAutoresizingMaskIntoConstraints = false
        versusView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        versusView.bottomAnchor.constraint(equalTo: self.bottomAnchor).isActive = true
        versusView.widthAnchor.constraint(equalTo: self.widthAnchor, multiplier: 1).isActive = true
        versusView.heightAnchor.constraint(equalTo: self.heightAnchor, multiplier: 0.7).isActive = true
    }
    
    private func setupVersusLabel() {
        versusView.addSubview(versusLabel)
        versusLabel.translatesAutoresizingMaskIntoConstraints = false
        versusLabel.centerXAnchor.constraint(equalTo: versusView.centerXAnchor).isActive = true
        versusLabel.centerYAnchor.constraint(equalTo: versusView.centerYAnchor).isActive = true
        versusLabel.textColor = .white
        versusLabel.font = UIFont(name: "DungGeunMo", size: 40)
        versusLabel.text = "VS"
    }
    
    private func setupHomeTeamButton() {
        let containerView = UIView()
        self.addSubview(containerView)
        containerView.translatesAutoresizingMaskIntoConstraints = false
        containerView.leadingAnchor.constraint(equalTo: versusView.leadingAnchor).isActive = true
        containerView.trailingAnchor.constraint(equalTo: versusLabel.leadingAnchor).isActive = true
        containerView.topAnchor.constraint(equalTo: versusView.topAnchor).isActive = true
        containerView.bottomAnchor.constraint(equalTo: versusView.bottomAnchor).isActive = true
        
        containerView.addSubview(homeTeamButton)
        
        homeTeamButton.translatesAutoresizingMaskIntoConstraints = false
        homeTeamButton.heightAnchor.constraint(equalTo: versusView.heightAnchor, multiplier: 0.7).isActive = true
        homeTeamButton.centerXAnchor.constraint(equalTo: containerView.centerXAnchor).isActive = true
        homeTeamButton.centerYAnchor.constraint(equalTo: containerView.centerYAnchor).isActive = true
        homeTeamButton.imageView?.contentMode = .scaleAspectFit
        homeTeamButton.addTarget(self, action: #selector(homeTeamPushed(_:)), for: .touchUpInside)
    }
    
    private func setupAwayTeamButton() {
        let containerView = UIView()
        self.addSubview(containerView)
        containerView.translatesAutoresizingMaskIntoConstraints = false
        containerView.leadingAnchor.constraint(equalTo: versusLabel.trailingAnchor).isActive = true
        containerView.trailingAnchor.constraint(equalTo: versusView.trailingAnchor).isActive = true
        containerView.topAnchor.constraint(equalTo: versusView.topAnchor).isActive = true
        containerView.bottomAnchor.constraint(equalTo: versusView.bottomAnchor).isActive = true
        
        containerView.addSubview(awayTeamButon)
        awayTeamButon.translatesAutoresizingMaskIntoConstraints = false
        awayTeamButon.heightAnchor.constraint(equalTo: versusView.heightAnchor, multiplier: 0.7).isActive = true
        awayTeamButon.centerXAnchor.constraint(equalTo: containerView.centerXAnchor).isActive = true
        awayTeamButon.centerYAnchor.constraint(equalTo: containerView.centerYAnchor).isActive = true
        awayTeamButon.imageView?.contentMode = .scaleAspectFit
        awayTeamButon.addTarget(self, action: #selector(awayTeamPushed(_:)), for: .touchUpInside)
    }

    
    @objc func homeTeamPushed(_ sender: UIButton) {
        NotificationCenter.default.post(name: .HomeTeamSelected,
                                        object: nil)
    }
    
    @objc func awayTeamPushed(_ sender: UIButton) {
        NotificationCenter.default.post(name: .AwayTeamSelected,
                                        object: nil)
    }
}

extension Notification.Name {
    static let HomeTeamSelected = Notification.Name("homeTeamSelected")
    static let AwayTeamSelected = Notification.Name("awayTeamSelected")
}
