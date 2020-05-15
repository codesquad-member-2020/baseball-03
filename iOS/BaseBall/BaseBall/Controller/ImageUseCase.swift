//
//  ImageUseCase.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/11.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

struct ImageUseCase {
    
    private var networkManager: NetworkManageable
    let cachesDirectory = FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask).first!
    
    struct ImageRequest: Request {
        var path: String
    }
    
    init(networkManager: NetworkManageable) {
        self.networkManager = networkManager
    }
    
    func requestTeamImage(name: String, from: String, failureHandler: @escaping (NetworkManager.NetworkError) -> (), completed: @escaping(URL) -> ()) {
        
        let imageURL = cachesDirectory.appendingPathComponent(name)
        if FileManager.default.fileExists(atPath: imageURL.path) {
            completed(imageURL)
        } else {
            let request = ImageRequest(path: from)
            networkManager.storeResource(request: request) {
                switch $0 {
                case .failure(let error):
                    failureHandler(error)
                case .success(let url):
                    try? FileManager.default.moveItem(at: url, to: imageURL)
                    completed(imageURL)
                }
            }
        }
    }
    
    func loadTeamImage(name: String, failureHandler: @escaping (String) -> (), completed: @escaping(URL) -> ()) {
        let imageURL = cachesDirectory.appendingPathComponent(name)
        
        guard FileManager.default.fileExists(atPath: imageURL.path) else {
            failureHandler("이미지가 없습니다.")
            return
        }
        completed(imageURL)
    }
}
