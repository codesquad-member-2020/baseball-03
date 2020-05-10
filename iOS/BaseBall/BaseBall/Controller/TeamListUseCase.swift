//
//  TeamListUseCase.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct TeamListUseCase<T: NetworkManageable> {
    
    var networkManager: T
    
    struct TeamListRequest: Request {
        var path: String {
            return EndPoint.defaultURL + EndPoint.teams
        }
    }
    
    init(networkManager: T) {
        self.networkManager = networkManager
    }
    
    func requestTeamList(failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping(TeamList) -> ()) {
        networkManager.getResource(request: TeamListRequest()) {
            switch $0 {
            case .failure(let error):
                failureHandler(error)
            case .success(let data):
                do {
                    let model = try JSONDecoder().decode(TeamListResponse.self, from: data)
                    completed(model.content)
                } catch {
                    failureHandler(.DecodeError)
                }
            }
        }
    }
}
