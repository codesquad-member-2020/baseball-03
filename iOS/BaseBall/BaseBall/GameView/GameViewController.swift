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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        currentPlayerTableView.delegate = self
        currentPlayerTableView.dataSource = self
    }
}

extension GameViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "currentPlayerCell") as? CurrentPlayerTableViewCell else {return UITableViewCell()}
        cell.accessoryView = UIImageView(image: UIImage(systemName: "checkmark"))
        return cell
    }
}

extension GameViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return tableView.frame.height / 2
    }
}
