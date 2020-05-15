//
//  MatchInProgress.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

protocol HasName {
    var name: String {get set}
}

struct MatchInProgressResponse: Decodable {
    var content: MatchInProgress
}

struct MatchInProgress: Decodable {
    var isHome: Bool
    var gameScore: GameScore?
    var halfInning: HalfInning?
    var team: TeamInMatch?
    var pitcher: Pitcher
    var hitter: Hitter
    var log: Log?
    var base: Base?
}

struct GameScore: Decodable {
    var away: Int
    var home: Int
}

struct HalfInning: Decodable {
    var round: Int
    var isTop: Bool
    var isAttack: Bool
}

struct TeamInMatch: Decodable {
    var home: String
    var away: String
}

struct Pitcher: Decodable, HasName {
    var name: String
    var pitchCount: Int?
}

struct Hitter: Decodable, HasName {
    var name: String
    var order: Int
    var avg: Float?
}

struct Log: Decodable {
    var result: String
    var count: Count
    var isOut: Bool?
    var isHit: Bool?
}

struct Count: Decodable {
    var OUT: Int
    var STRIKE: Int
    var BALL: Int
}

struct Base: Decodable {
    var FIRST: Bool
    var SECOND: Bool
    var THIRD: Bool
    var HOME: Bool
}


