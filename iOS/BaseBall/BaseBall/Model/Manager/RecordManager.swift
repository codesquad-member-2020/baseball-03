//
//  RecordManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/15.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class RecordManager {
    
    private var pitcherRecord: PlayerRecord?
    private var hitterRecords: [PlayerRecord]? {
        didSet {
            NotificationCenter.default.post(name: .RecordsUpdated,
                                            object: nil)
        }
    }
    
    func setRecords(pitcher: Pitcher, hitter: Hitter) {
        hitterRecords = [PlayerRecord]()
        pitcherRecord? = PlayerRecord(player: pitcher, records: [String]())
        hitterRecords?.append(PlayerRecord(player: hitter, records: [String]()))
    }
    
    func pitcher() -> PlayerRecord? {
        return pitcherRecord
    }
    
    func hitter(at index: Int) -> PlayerRecord? {
        return hitterRecords?[index - 1]
    }
    
    func count() -> Int {
        return (hitterRecords?.count ?? 0) + 1
    }
}

struct PlayerRecord {
    var player: HasName
    var records: [String]
}

extension Notification.Name {
    static let RecordsUpdated = Notification.Name("RecordsUpdated")
}
