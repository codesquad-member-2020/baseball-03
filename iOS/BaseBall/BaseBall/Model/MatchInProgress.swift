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
    var team: TeamInMatch
    var pitcher: Pitcher
    var hitter: Hitter
}

struct TeamInMatch: Decodable {
    var home: String
    var away: String
}

struct Pitcher: Decodable, HasName {
    var name: String
}

struct Hitter: Decodable, HasName {
    var name: String
    var order: Int
}
