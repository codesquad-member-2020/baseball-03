//
//  ScoreView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/08.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class ScoreView: UIView {
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.layer.cornerRadius = self.frame.height / 2
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.layer.cornerRadius = self.frame.height / 2
    }
}
