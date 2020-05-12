//
//  MatchListManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/11.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class MatchListManager {
    private var matchList: [Match]?
    
    func insertMatchList(matchList: [Match]) {
        self.matchList = matchList
        NotificationCenter.default.post(name: .MatchListInserted,
                                        object: nil)
    }
    
    func count() -> Int {
        return matchList?.count ?? 0
    }
    
    func match(at index: Int) -> Match? {
        return matchList?[index]
    }
}

extension Notification.Name {
    static let MatchListInserted = Notification.Name("MatchListInserted")
}

