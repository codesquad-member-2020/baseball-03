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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSegmentedControl()
        playerInfoTableView.dataSource = self
        playerInfoTableView.delegate = self
    }
    
    private func setupSegmentedControl() {
        teamSelectSegmentedControl.setTitleTextAttributes([.font : UIFont(name: "DungGeunMo", size: 17) as Any], for: .normal)
    }
}

extension DetailScoreViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "PlayerInfoCell") as? PlayerInfoCell else {return UITableViewCell()}
        if indexPath.row == 9 {
            cell.configuateAverage()
        } else {
            cell.configurateCell()
        }
        return cell
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
