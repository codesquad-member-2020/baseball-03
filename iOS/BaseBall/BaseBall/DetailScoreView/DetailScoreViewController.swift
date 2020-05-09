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
    @IBOutlet weak var playerInfoTableView: UITableView!
    
    private let detailTableViewDataSource = DetailTableViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSegmentedControl()
        playerInfoTableView.dataSource = detailTableViewDataSource
        playerInfoTableView.delegate = self
    }
    
    private func setupSegmentedControl() {
        teamSelectSegmentedControl.setTitleTextAttributes([.font : UIFont(name: "DungGeunMo", size: 17) as Any], for: .normal)
    }
}

extension DetailScoreViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return playerInfoTableView.frame.height / 11
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        guard let header = tableView.dequeueReusableCell(withIdentifier: "PlayerInfoCell") as? PlayerInfoCell else {return UIView()}
        return header.contentView
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return playerInfoTableView.frame.height / 11
    }
}
