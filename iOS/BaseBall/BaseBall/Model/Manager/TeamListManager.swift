//
//  TeamListManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class TeamListManager {
    
    private var teamList: [Team]?
    
    func insertTeamList(teamList: [Team]) {
        self.teamList = teamList
        NotificationCenter.default.post(name: .TeamListInserted,
                                        object: nil)
    }
    
    func count() -> Int {
        return teamList?.count ?? 0
    }
    
    func team(at index: Int) -> Team? {
        return teamList?[index]
    }
    
    func random() -> Team? {
        return teamList?.randomElement()
    }
}

extension Notification.Name {
    static let TeamListInserted = Notification.Name("TeamListInserted")
}
