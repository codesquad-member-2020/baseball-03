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
    
    func iterate(_ transform: (Team) -> ()) {
        teamList?.forEach {
            transform($0)
        }
    }
}

extension Notification.Name {
    static let TeamListInserted = Notification.Name("TeamListInserted")
}
