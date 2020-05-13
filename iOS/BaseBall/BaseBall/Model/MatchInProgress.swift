//
//  MatchInProgress.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct MatchInProgressResponse: Decodable {
    var content: MatchInProgress
}

struct MatchInProgress: Decodable {
    var isHome: Bool
    var team: TeamInMatch
    var gameScore: GameScore
    var halfInning: HalfInning
    var pitcher: Pitcher
    var hitter: Hitter
}

struct TeamInMatch: Decodable {
    var home: String
    var away: String
}

struct GameScore: Decodable {
    var home: Int
    var away: Int
}

struct HalfInning: Decodable {
    var round: Int
    var isTop: Bool
    var isAttack: Bool
}

struct Pitcher: Decodable {
    var name: String
    var pitchCount: Int
}

struct Hitter: Decodable {
    var name: String
    var order: Int
    var atBats: Int
    var hits: Int
    var outs: Int
    var avg: Double
}
