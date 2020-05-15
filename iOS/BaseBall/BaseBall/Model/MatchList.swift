//
//  MatchList.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/11.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct MatchListResponse: Decodable {
    var content: [Match]
}

struct Match: Decodable {
    var matchId: Int
    var home: TeamPreview
    var away: TeamPreview
}

struct TeamPreview: Decodable {
    var id: Int
    var name: String
    var thumbnail_url: String
}
