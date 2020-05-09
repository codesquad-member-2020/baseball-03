//
//  CurrentPlayerDataSource.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/09.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

class CurrentPlayerDataSource: NSObject, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "CurrentPlayerCell") as? CurrentPlayerCell else {return UITableViewCell()}
        cell.accessoryView = UIImageView(image: UIImage(systemName: "checkmark"))
        return cell
    }
}
