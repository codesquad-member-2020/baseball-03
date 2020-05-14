//
//  PitcherHitterView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/14.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class PitcherHitterView: UIView {
    
    @IBOutlet weak var piterHitterLabel: PaddingLabel!
    @IBOutlet weak var playerInfo: UILabel!
    @IBOutlet weak var recordLabel: UILabel!
    @IBOutlet weak var playerLabel: UILabel!
    @IBOutlet var contentView: UIView!
    
    private let xibName = String(describing: PitcherHitterView.self)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setXib()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setXib()
    }
    
    private func setXib() {
        let bundle = Bundle(for: PitcherHitterView.self)
        bundle.loadNibNamed(xibName, owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
}
