//
//  BlurView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class BlurView: UIVisualEffectView {
    
    override init(effect: UIVisualEffect?) {
        super.init(effect: effect)
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.effect = UIBlurEffect(style: .regular)
    }
}
