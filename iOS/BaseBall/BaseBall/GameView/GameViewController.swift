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
    
    private let dataSource = DataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        currentPlayerTableView.delegate = self
        currentPlayerTableView.dataSource = self
        currentRecordTableView.dataSource = dataSource
    }
}

class DataSource: NSObject, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 10
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "CurrentRecordCell") as? CurrentRecordCell else {return UITableViewCell()}
        cell.orderView.layer.cornerRadius = cell.orderView.frame.height / 2
        cell.orderLabel.text = String(indexPath.row + 1)
        return cell
    }
}

extension GameViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "CurrentPlayerCell") as? CurrentPlayerCell else {return UITableViewCell()}
        cell.accessoryView = UIImageView(image: UIImage(systemName: "checkmark"))
        return cell
    }
}

extension GameViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableView.frame.height / 2
    }
}
