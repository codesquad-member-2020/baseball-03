//
//  AlertView.swift
//  BaseBall
//
//  Created by 신한섭 on 2020/05/13.
//  Copyright © 2020 신한섭. All rights reserved.
//

import UIKit

final class AlertView {
    static func alertError(viewController: UIViewController, message: String) {
        DispatchQueue.main.async {
            let alert = UIAlertController(title: "문제가 생겼어요", message: message, preferredStyle: .alert)
            let ok = UIAlertAction(title: "넵...", style: .default)
            alert.addAction(ok)
            viewController.present(alert, animated: true)
        }
    }
    
    static func errorHandling(viewController: UIViewController, error: NetworkManager.NetworkError) {
        alertError(viewController: viewController, message: error.message())
    }
}
