//
//  CurrentRecordCell.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/09.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class CurrentRecordCell: UITableViewCell {
    
    @IBOutlet weak var orderView: UIView!
    @IBOutlet weak var orderLabel: UILabel!
    @IBOutlet weak var SBOLabel: NSLayoutConstraint!
    @IBOutlet weak var countLabel: UILabel!
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
}
