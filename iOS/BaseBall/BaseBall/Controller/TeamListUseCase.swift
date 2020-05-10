//
//  TeamListUseCase.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation


struct TeamListUseCase<T: NetworkManageable> {
    
    private var networkManager: T
    let cachesDirectory = FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask).first!
    
    struct TeamListRequest: Request {
        var path: String {
            return EndPoint.defaultURL + EndPoint.teams
        }
    }
    
    struct ImageRequest: Request {
        var path: String
    }
    
    init(networkManager: T) {
        self.networkManager = networkManager
    }
    
    func requestTeamList(failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping([Team]) -> ()) {
        networkManager.getResource(request: TeamListRequest()) {
            switch $0 {
            case .failure(let error):
                failureHandler(error)
            case .success(let data):
                do {
                    let model = try JSONDecoder().decode(TeamListResponse.self, from: data)
                    completed(model.content.teams)
                } catch {
                    failureHandler(.DecodeError)
                }
            }
        }
    }
    
    func requestTeamImage(name: String, from: String, failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping(Data) -> ()) {
        
        let imageURL = cachesDirectory.appendingPathComponent(name)
        if FileManager.default.fileExists(atPath: imageURL.path) {
            handleData(from: imageURL, failureHandler: failureHandler, completed: completed)
        } else {
            let request = ImageRequest(path: from)
            networkManager.downloadResource(request: request) {
                switch $0 {
                case .failure(let error):
                    failureHandler(error)
                case .success(let url):
                    self.handleData(from: url, failureHandler: failureHandler, completed: completed)
                    try? FileManager.default.moveItem(at: url, to: imageURL)
                }
            }
        }
    }
    
    private func handleData(from url: URL, failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping(Data) -> ()) {
        do {
            let data = try Data(contentsOf: url)
            completed(data)
        } catch {
            failureHandler(.DecodeError)
        }
    }
}
