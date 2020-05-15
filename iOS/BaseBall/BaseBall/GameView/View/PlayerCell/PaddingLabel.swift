//
//  PaddingLabel.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/14.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class PaddingLabel: UILabel {
    var padding: UIEdgeInsets = UIEdgeInsets(top: 3, left: 3, bottom: 3, right: 3)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    override func drawText(in rect: CGRect) {
        super.drawText(in: rect)
        let tempText = self.text
        self.text = ""
        let paddingRect = rect.inset(by: padding)
        super.drawText(in: paddingRect)
        self.text = tempText
    }
    
    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.width += padding.left + padding.right
        contentSize.height += padding.top + padding.bottom
        return contentSize
    }
    
    func setBorder(color: UIColor, width: CGFloat) {
        self.layer.borderWidth = width
        self.layer.borderColor = color.cgColor
    }
}
