//
//  RecordManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/15.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class RecordManager {
    
    private var playerRecords: [PlayerRecord]? {
        didSet {
            NotificationCenter.default.post(name: .RecordsUpdated,
                                            object: nil)
        }
    }
    
    func setRecords(pitcher: Pitcher, hitter: Hitter) {
        playerRecords = [PlayerRecord]()
        playerRecords?.append(PlayerRecord(player: pitcher, records: [Record]()))
        playerRecords?.append(PlayerRecord(player: hitter, records: [Record]()))
    }
    
    func insertNewPlayer(pitcher: Pitcher, hitter: Hitter) {
        playerRecords?[0] = PlayerRecord(player: pitcher, records: [Record]())
        playerRecords?.insert(PlayerRecord(player: hitter, records: [Record]()), at: 1)
    }
    
    func update(pitcher: Pitcher, log: Log) {
        playerRecords?[0].player = pitcher
        playerRecords?[1].records.insert(Record(SBO: log.result, count: log.count), at: 0)
    }
    
    func pitcher() -> PlayerRecord? {
        return playerRecords?[0]
    }
    
    func hitter(at index: Int) -> PlayerRecord? {
        return playerRecords?[index]
    }
    
    func count() -> Int {
        return playerRecords?.count ?? 0
    }
}

struct PlayerRecord {
    var player: HasName
    var records: [Record]
}

struct Record {
    var SBO: String
    var count: Count
}

extension Notification.Name {
    static let RecordsUpdated = Notification.Name("RecordsUpdated")
}
