//
//  DetailTableViewDataSource.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class DetailTableViewDataSource: NSObject, UITableViewDataSource {
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
