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
    private let xibName = String(describing: ElectronicView.self)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setXib()
        self.layer.cornerRadius = self.frame.width / 20
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setXib()
        self.layer.cornerRadius = self.frame.width / 20
    }
    
    private func setXib() {
        let bundle = Bundle(for: ElectronicView.self)
        bundle.loadNibNamed(xibName, owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
}
