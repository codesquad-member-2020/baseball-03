//
//  InningCollectionViewCell.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/14.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class InningCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var inningLabel: UILabel!
    
    func select() {
        inningLabel.textColor = UIColor(named: "Samsung")
        inningLabel.backgroundColor = .white
    }
    
    func deSelect() {
        inningLabel.textColor = .white
        inningLabel.backgroundColor = .clear
    }
    
    override func prepareForReuse() {
        deSelect()
    }
}
