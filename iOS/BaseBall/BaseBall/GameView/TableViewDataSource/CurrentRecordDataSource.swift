//
//  CurrentRecordDataSource.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/09.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class CurrentRecordDataSource: NSObject, UITableViewDataSource {
    
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
