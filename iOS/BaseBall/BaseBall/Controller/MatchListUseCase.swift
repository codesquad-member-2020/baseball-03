//
//  MatchListUseCase.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/11.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct MatchListUseCase<T: NetworkManageable> {
    
    private var networkManager: T
    
    struct MatchListRequest: Request {
        var path: String {
            return EndPoint.defaultURL + EndPoint.games
        }
    }
    
    init(networkManager: T) {
        self.networkManager = networkManager
    }
    
    func requestMatchList(failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping([Match]) -> ()) {
        networkManager.getResource(request: MatchListRequest()) {
            switch $0 {
            case .failure(let error):
                failureHandler(error)
            case .success(let data):
                do {
                    let model = try JSONDecoder().decode(MatchListResponse.self, from: data)
                    completed(model.content)
                } catch {
                    failureHandler(.DecodeError)
                }
            }
        }
    }
}
