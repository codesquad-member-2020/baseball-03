//
//  RecordTableViewCell.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/15.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class RecordTableViewCell: UITableViewCell {
    
    @IBOutlet weak var pitcherHitterView: PitcherHitterView!
    @IBOutlet weak var recordStackView: UIStackView!
    
    func setRecord(pitcher: Pitcher) {
        pitcherHitterView.setPitcher(pitcher: pitcher)
    }
    
    func setRecord(record: PlayerRecord) {
        guard let hitter = record.player as? Hitter else {return}
        pitcherHitterView.setHiiter(hitter: hitter)
    }
}
