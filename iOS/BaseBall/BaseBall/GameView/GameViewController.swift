//
//  GameViewController.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/06.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class GameViewController: UIViewController {
    
    @IBOutlet weak var currentPlayerTableView: UITableView!
    @IBOutlet weak var currentRecordTableView: UITableView!
    
    private let currentRecordDataSource = CurrentRecordDataSource()
    private let currentPlayerDataSource = CurrentPlayerDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        currentPlayerTableView.delegate = self
        currentPlayerTableView.dataSource = currentPlayerDataSource
        currentRecordTableView.dataSource = currentRecordDataSource
    }
}

extension GameViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableView.frame.height / 2
    }
}
