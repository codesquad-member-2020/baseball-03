//
//  MatchInProgressManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class MatchInProgressManager {
    private var matchInProgress: MatchInProgress?
    
    func insertMatch(matchInProgress: MatchInProgress) {
        self.matchInProgress = matchInProgress
        NotificationCenter.default.post(name: .MatchInProgressInserted,
                                        object: nil)
    }
    
    func teamInfo() -> TeamInMatch? {
        return matchInProgress?.team
    }
}

extension Notification.Name {
    static let MatchInProgressInserted = Notification.Name("MatchInProgressInserted")
}
