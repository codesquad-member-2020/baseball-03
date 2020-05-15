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
        playerRecords?.append(PlayerRecord(player: pitcher, records: [String]()))
        playerRecords?.append(PlayerRecord(player: hitter, records: [String]()))
    }
    
    func update(pitcher: Pitcher, hitter: Hitter, log: Log) {
        playerRecords?[0].player = pitcher
        playerRecords?[1].player = hitter
        playerRecords?[1].records.insert(log.result, at: 0)
        print(playerRecords?[1].records)
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
    var records: [String]
}

extension Notification.Name {
    static let RecordsUpdated = Notification.Name("RecordsUpdated")
}
