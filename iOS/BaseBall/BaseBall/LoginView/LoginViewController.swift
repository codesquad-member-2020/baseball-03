//
//  LoginViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/05.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    
    @IBAction func logoButtonPushed(_ sender: UIButton) {
        guard let gameListViewController = storyboard?.instantiateViewController(identifier: "GameListViewController") else {return}
        gameListViewController.modalPresentationStyle = .overFullScreen
        gameListViewController.view.backgroundColor = .clear
        self.view.addSubview(blurView)
        present(gameListViewController, animated: true)
    }
    @IBOutlet weak var logoButton: UIButton!
    
    private let logoName = ["Doosan", "Hanwha", "Kia", "Kiwoom", "KT", "LG", "Lotte", "NC", "Samsung", "SK"]
    private let blurView = UIVisualEffectView(effect: UIBlurEffect(style: .regular))
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupLogInImage()
        setupBlurView()
    }
    
    private func setupLogInImage() {
        let name = logoName[Int.random(in: 0..<logoName.count)]
        self.logoButton.setImage(UIImage(named: name), for: .normal)
        self.view.backgroundColor = UIColor(named: name)
        
        if name == "Hanwha" || name == "NC" || name == "Kiwoom" {
            logoButton.translatesAutoresizingMaskIntoConstraints = false
            let widthAnchor = logoButton.widthAnchor
            logoButton.removeConstraints(logoButton.constraints)
            logoButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
            logoButton.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
            logoButton.widthAnchor.constraint(equalTo: widthAnchor).isActive = true
            
            if name == "Hanwha" {
                logoButton.heightAnchor.constraint(equalTo: logoButton.widthAnchor, multiplier: 1 / 1.5).isActive = true
            } else {
                logoButton.heightAnchor.constraint(equalTo: logoButton.widthAnchor, multiplier: 1 / 1.4).isActive = true
            }
        }
    }
    
    private func setupBlurView() {
        blurView.frame = self.view.frame
    }
}

