//
//  ListContentView.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 13/10/20.
//

import SwiftUI

struct ContentView: View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    var body: some View {
        Group {
            if userInfo.isUserAuthenticated == .undefined {
                Text("Loading")
            } else if userInfo.isUserAuthenticated == .signedOut {
                LoginView().environmentObject(userInfo)
            } else {
                ListProductsView().environmentObject(userInfo)
            }
        }
        .onAppear {
            self.userInfo.configureFirebaseStateDidChange()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
