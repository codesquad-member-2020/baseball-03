//
//  MatchInProgressManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class MatchInProgressManager {
    
    private var matchInProgress: [MatchInProgress]?
    
    func setMatch(matchInProgress: MatchInProgress) {
        self.matchInProgress = [MatchInProgress]()
        self.matchInProgress?.append(matchInProgress)
        NotificationCenter.default.post(name: .MatchInProgressInserted,
                                        object: nil)
    }
    
    func insert(match: MatchInProgress) {
        self.matchInProgress?.insert(match, at: 0)
        NotificationCenter.default.post(name: .MatchInProgressUpdated,
                                        object: nil)
    }
    
    func teamInfo() -> TeamInMatch? {
        return matchInProgress?[0].team
    }
    
    func currentPitcher() -> Pitcher? {
        return matchInProgress?[0].pitcher
    }
    
    func currentHitter() -> Hitter? {
        return matchInProgress?[0].hitter
    }
    
    func prevHitter() -> Hitter? {
        return matchInProgress?[1].hitter
    }
    
    func currentLog() -> Log? {
        return matchInProgress?[0].log
    }
}

extension Notification.Name {
    static let MatchInProgressInserted = Notification.Name("MatchInProgressInserted")
    static let MatchInProgressUpdated = Notification.Name("MatchInProgressUpdated")
}
