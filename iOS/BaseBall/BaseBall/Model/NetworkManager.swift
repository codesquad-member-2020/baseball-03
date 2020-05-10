//
//  NetworkManager.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

protocol NetworkManageable {
    associatedtype dataResponse
    associatedtype downloadResponse
    func getResource(request: Request, handler: @escaping (dataResponse) -> ())
    func downloadResource(request: Request, handler: @escaping (downloadResponse) ->())
}

class NetworkManager: NetworkManageable {

    typealias dataResponse = Result<Data, NetworkManager.NetworkError>
    typealias downloadResponse = Result<URL, NetworkManager.NetworkError>
    
    enum EndPoints {
        static let defaultURL = "https://69612ad4-ca1b-4bc6-a7e2-a7d141c32cb5.mock.pstmn.io/api/"
        static let matches = "matches"
    }
    
    enum NetworkError: Error {
        case DataEmpty
        case DecodeError
        case InvalidHTTPResonse
        case InvalidStatusCode(Int)
        case InvalidURL
        case requestError
        
        func message() -> String {
            switch self {
            case .DataEmpty:
                return "데이터가 비었어요."
            case .DecodeError:
                return "응답을 복호화 하는 도중 문제가 발생했어요."
            case .InvalidHTTPResonse:
                return "HTTP 응답이 유효하지 않아요."
            case .InvalidStatusCode(let code):
                return "HTTP 응답 \(code) 에러 발생했어요."
            case .InvalidURL:
                return "URL이 유효하지 않아요."
            case .requestError:
                return "요청을 보내는 중에 오류가 발생했어요."
            }
        }
    }
    
    func downloadResource(request: Request, handler: @escaping (downloadResponse) -> ()) {
        guard let urlRequest = request.urlRequest() else {
            handler(.failure(.InvalidURL))
            return
        }
        
        URLSession.shared.downloadTask(with: urlRequest) { (url, response, error) in
            guard error == nil else {
                handler(.failure(.requestError))
                return
            }

            guard let httpResponse = response as? HTTPURLResponse else {
                handler(.failure(.InvalidHTTPResonse))
                return
            }

            guard httpResponse.statusCode == 200 else {
                handler(.failure(.InvalidStatusCode(httpResponse.statusCode)))
                return
            }

            guard let url = url else {
                handler(.failure(.DataEmpty))
                return
            }

            handler(.success(url))
        }.resume()
    }

    func getResource(request: Request, handler: @escaping (dataResponse) -> ()) {
        guard let urlRequest = request.urlRequest() else {
            handler(.failure(.InvalidURL))
            return
        }
        
        URLSession.shared.dataTask(with: urlRequest) { (data, response, error) in
            guard error == nil else {
                handler(.failure(.requestError))
                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse else {
                handler(.failure(.InvalidHTTPResonse))
                return
            }
            
            guard httpResponse.statusCode == 200 else {
                handler(.failure(.InvalidStatusCode(httpResponse.statusCode)))
                return
            }
            
            guard let data = data else {
                handler(.failure(.DataEmpty))
                return
            }
            
            handler(.success(data))
        }.resume()
    }
}

