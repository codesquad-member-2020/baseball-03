//
//  MatchInProgressUseCase.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct MatchInProgressUseCase {
    
    private var networkManager: NetworkManageable
    
    struct MatchInProgressRequest: Request {
        var path: String {
            return EndPoint.defaultURL + EndPoint.games
        }
    }
    
    init(networkManager: NetworkManageable) {
        self.networkManager = networkManager
    }
    
    func requestMatchList(failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping(MatchInProgress) -> ()) {
        networkManager.loadResource(request: MatchInProgressRequest()) {
            switch $0 {
            case .failure(let error):
                failureHandler(error)
            case .success(let data):
                do {
                    let model = try JSONDecoder().decode(MatchInProgressResponse.self, from: data)
                    completed(model.content)
                } catch {
                    failureHandler(.DecodeError)
                }
            }
        }
    }
}
