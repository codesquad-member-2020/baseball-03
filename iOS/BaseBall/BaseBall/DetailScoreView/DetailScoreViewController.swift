//
//  DetailScoreViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/09.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class DetailScoreViewController: UIViewController {

    @IBOutlet weak var teamSelectSegmentedControl: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSegmentedControl()
    }
    
    private func setupSegmentedControl() {
        teamSelectSegmentedControl.setTitleTextAttributes([.font : UIFont(name: "DungGeunMo", size: 17) as Any], for: .normal)
    }
}
