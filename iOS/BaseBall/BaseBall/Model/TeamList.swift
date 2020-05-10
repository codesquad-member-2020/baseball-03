//
//  TeamList.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct TeamListResponse: Decodable {
    var content: TeamList
}

struct TeamList: Decodable {
    var teams: [Team]
}

struct Team: Decodable {
    var name: String
    var url: String
    var color: String
}
