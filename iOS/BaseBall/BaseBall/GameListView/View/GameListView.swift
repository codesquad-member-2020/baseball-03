//
//  GameListView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameListView: UIView {
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.backgroundColor = .lightGray
        self.alpha = 0.6
        self.layer.cornerRadius = 20
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .lightGray
        self.alpha = 0.6
        self.layer.cornerRadius = 20
    }
}
