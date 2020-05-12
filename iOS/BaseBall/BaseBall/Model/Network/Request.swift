//
//  Request.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/10.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

enum HTTPMethod: String, CustomStringConvertible {
    case get = "GET"
    case post = "POST"
    case patch = "PATCH"
    case delete = "DELETE"
    case put = "PUT"
    
    var description: String {
        return self.rawValue
    }
}

protocol Request {
    var path: String {get}
    var httpMethod: HTTPMethod {get}
    var httpBody: Data? {get}
    var httpHeaderValue: String? {get}
    var httpHeaderFields: [String]? {get}
    func urlRequest() -> URLRequest?
}

extension Request {
    var httpMethod: HTTPMethod {return .get}
    var httpBody: Data? {return nil}
    var httpHeaderValue: String? {return nil}
    var httpHeaderFields: [String]? {return nil}
    
    func urlRequest() -> URLRequest? {
        guard let encodedPath = path.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed) else {return nil}
        guard let url = URL(string: encodedPath) else {return nil}
        
        var request = URLRequest(url: url)
        request.httpMethod = httpMethod.description
        request.httpBody = httpBody
        
        guard let headerFields = httpHeaderFields, let headerValue = httpHeaderValue else {return request}
        
        headerFields.forEach {
            request.addValue(headerValue, forHTTPHeaderField: $0)
        }
        
        return request
    }
}
