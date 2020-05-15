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
    
    func setPitcher(pitcher: Pitcher) {
        self.playerInfo.text = pitcher.name
        guard let count = pitcher.pitchCount else {return}
        self.recordLabel.text = "투구 \(count)"
    }
    
    func setHiiter(hitter: Hitter) {
        self.playerInfo.text = "\(hitter.order)번 타자 " + hitter.name
        recordLabel.text = ""
        piterHitterLabel.text = "타자"
        piterHitterLabel.backgroundColor = .blue
        playerLabel.text = ""
    }
}
