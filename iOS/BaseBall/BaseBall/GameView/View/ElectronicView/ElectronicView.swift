//
//  ElectronicView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/08.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class ElectronicView: UIView {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var awayImageView: UIImageView!
    @IBOutlet weak var awayLabel: UILabel!
    @IBOutlet weak var homeImageView: UIImageView!
    @IBOutlet weak var homeLabel: UILabel!
    @IBOutlet weak var strikeStackView: UIStackView!
    @IBOutlet weak var ballStackView: UIStackView!
    @IBOutlet weak var outStackView: UIStackView!
    
    private let xibName = String(describing: ElectronicView.self)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setXib()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setXib()
    }
    
    private func setXib() {
        let bundle = Bundle(for: ElectronicView.self)
        bundle.loadNibNamed(xibName, owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.layer.cornerRadius = contentView.frame.width / 20
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
    
    func setTeamName(team: TeamInMatch) {
        self.awayLabel.text = team.away
        self.homeLabel.text = team.home
    }
    
    func setAwayTeamImage(url: URL) {
        self.awayImageView.image = UIImage(named: url.path)
    }
    
    func setHomeTeamImage(url: URL) {
        self.homeImageView.image = UIImage(named: url.path)
    }
    
    func setSBO(log: Log) {
        let count = log.count
        
        setStrike(count: count.STRIKE)
        setBall(count: count.BALL)
        setOut(count: count.OUT)
        
        if count.STRIKE == 3 || count.BALL == 4{
            resetStrike()
            resetBall()
        }
        
        if count.OUT == 3 {
            resetSBO()
        }
        
        guard let hit = log.isHit else {return}
        guard let out = log.isOut else {return}
        
        if hit || out{
            resetBall()
            resetStrike()
        }
    }
    
    func setStrike(count: Int) {
        for (index, view) in strikeStackView.subviews.enumerated() {
            if index == count - 1 {
                view.backgroundColor = .yellow
            }
        }
    }
    
    func setBall(count: Int) {
        for (index, view) in ballStackView.subviews.enumerated() {
            if index == count - 1 {
                view.backgroundColor = .green
            }
        }
    }
    
    func setOut(count: Int) {
        for (index, view) in outStackView.subviews.enumerated() {
            if index == count - 1 {
                view.backgroundColor = .red
            }
        }
    }
    
    func resetStrike() {
        strikeStackView.subviews.forEach {
            $0.backgroundColor = .darkGray
        }
    }
    
    func resetBall() {
        ballStackView.subviews.forEach {
            $0.backgroundColor = .darkGray
        }
    }
    
    func resetOut() {
        outStackView.subviews.forEach {
            $0.backgroundColor = .darkGray
        }
    }
    
    func resetSBO() {
        resetStrike()
        resetBall()
        resetOut()
    }
}
