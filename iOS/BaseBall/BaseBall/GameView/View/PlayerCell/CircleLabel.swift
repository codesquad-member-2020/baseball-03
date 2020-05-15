//
//  CircleLabel.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/15.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class CircleLabel: UILabel {
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.layer.cornerRadius = self.frame.height / 2
        self.layer.masksToBounds = true
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.layer.cornerRadius = self.frame.height / 2
        self.layer.masksToBounds = true
    }
}
