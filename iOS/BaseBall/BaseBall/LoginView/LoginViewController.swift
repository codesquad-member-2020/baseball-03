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
    
    private let logoName = ["Doosan", "Hanwha", "Kia", "Kiwoom", "KT", "LG", "Lotte", "NC", "Samsung", "SK"]
    private let blurView = UIVisualEffectView(effect: UIBlurEffect(style: .regular))
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupLogInImage()
        setupLoginLabel()
        setupBlurView()
    }
    
    private func setupLogInImage() {
        let name = logoName[Int.random(in: 0..<logoName.count)]
        self.view.addSubview(logoButton)
        self.view.backgroundColor = UIColor(named: name)
        self.logoButton.translatesAutoresizingMaskIntoConstraints = false
        self.logoButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        self.logoButton.centerYAnchor.constraint(equalTo: self.view.centerYAnchor).isActive = true
        self.logoButton.heightAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.heightAnchor, multiplier: 0.5).isActive = true
        self.logoButton.widthAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.widthAnchor, multiplier: 0.9).isActive = true
        self.logoButton.imageView?.contentMode = .scaleAspectFit
        self.logoButton.setImage(UIImage(named: name), for: .normal)
        self.logoButton.addTarget(self, action: #selector(logoButtonPushed(_:)), for: .touchUpInside)
    }
    
    private func setupLoginLabel() {
        self.view.addSubview(self.loginLabel)
        self.loginLabel.translatesAutoresizingMaskIntoConstraints = false
        self.loginLabel.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        self.loginLabel.topAnchor.constraint(equalTo: self.logoButton.bottomAnchor, constant: 20).isActive = true
        self.loginLabel.textColor = .white
        self.loginLabel.font = .systemFont(ofSize: 20)
        self.loginLabel.text = "로고를 눌러서 로그인을 해주세요!"
    }
    
    private func setupBlurView() {
        self.blurView.frame = self.view.frame
    }
    
    @objc func logoButtonPushed(_ sender: UIButton) {
        guard let gameListViewController = storyboard?.instantiateViewController(identifier: "GameListViewController") else {return}
        gameListViewController.modalPresentationStyle = .overFullScreen
        gameListViewController.view.backgroundColor = .clear
        self.view.addSubview(blurView)
        present(gameListViewController, animated: true)
    }
}

