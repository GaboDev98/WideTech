//
//  File.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 17/10/20.
//

import Foundation

class UserInfo: ObservableObject {
    enum FBAuthState {
        case undefined, signedOut, signedIn
    }
    @Published var isUserAuthenticated: FBAuthState = .undefined
    
    func configureFirebaseStateDidChange() {
        self.isUserAuthenticated = .signedOut
        // self.isUserAuthenticated = .signedIn
    }
}
