//
//  CurrentPlayerCell.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/09.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class PlayerCell: UITableViewCell {
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        self.selectedBackgroundView?.backgroundColor = .clear
    }
}
