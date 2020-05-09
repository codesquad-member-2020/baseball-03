//
//  PlayerInfoCell.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class PlayerInfoCell: UITableViewCell {
    
    @IBOutlet weak var playerNameLabel: UILabel!
    @IBOutlet weak var batterBoxLabel: UILabel!
    @IBOutlet weak var hitCountLabel: UILabel!
    @IBOutlet weak var outCountLabel: UILabel!
    @IBOutlet weak var averageLabel: UILabel!
    
    func configurateCell() {
        playerNameLabel.text = "선수"
        batterBoxLabel.text = "1"
        hitCountLabel.text = "1"
        outCountLabel.text = "0"
        averageLabel.text = "0.123"
        
        playerNameLabel.textColor = UIColor(named: "NeonColor")
        batterBoxLabel.textColor = UIColor(named: "NeonColor")
        hitCountLabel.textColor = UIColor(named: "NeonColor")
        outCountLabel.textColor = UIColor(named: "NeonColor")
        averageLabel.textColor = UIColor(named: "NeonColor")
    }
    
    func configuateAverage() {
        playerNameLabel.text = "Totals"
        batterBoxLabel.text = "9"
        hitCountLabel.text = "9"
        outCountLabel.text = "0"
        averageLabel.alpha = 0
        
        playerNameLabel.textColor = UIColor(named: "NeonColor")
        batterBoxLabel.textColor = UIColor(named: "NeonColor")
        hitCountLabel.textColor = UIColor(named: "NeonColor")
        outCountLabel.textColor = UIColor(named: "NeonColor")
        averageLabel.textColor = UIColor(named: "NeonColor")
    }
}
