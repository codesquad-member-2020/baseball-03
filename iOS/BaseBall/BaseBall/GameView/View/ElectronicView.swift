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
    @IBOutlet weak var awayImage: UIImageView!
    @IBOutlet weak var awayLabel: UILabel!
    @IBOutlet weak var homeImage: UIImageView!
    @IBOutlet weak var homeLabel: UILabel!
    
    private let xibName = String(describing: ElectronicView.self)
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.setXib()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.setXib()
    }
    
    private func setXib() {
        let bundle = Bundle(for: ElectronicView.self)
        bundle.loadNibNamed(xibName, owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.layer.cornerRadius = contentView.frame.width / 20
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
    
    func setTeamName(team: TeamInMatch) {
        self.awayLabel.text = team.away
        self.homeLabel.text = team.home
    }
}
