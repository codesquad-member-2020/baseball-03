//
//  RecordView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/15.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

@IBDesignable class RecordView: UIView {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var orderLabel: CircleLabel!
    @IBOutlet weak var SBOLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    
    private let xibName = String(describing: RecordView.self)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setXib()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setXib()
    }
    
    private func setXib() {
        let bundle = Bundle(for: RecordView.self)
        bundle.loadNibNamed(xibName, owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
}
