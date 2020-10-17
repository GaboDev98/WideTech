//
//  ListProductsView.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 16/10/20.
//

import SwiftUI

struct ListProductsView: View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    @ObservedObject private var productListVM = ProductListViewModel()
    
    var body: some View {
        NavigationView() {
            List(self.productListVM.products, id: \.name) { product in
                VStack {
                    RemoteImage(url: URL(string: product.imageUrl)!)
                        .padding()
                    
                    HStack {
                        VStack(alignment: .leading) {
                            Text(product.name)
                                .font(.title2)
                                .foregroundColor(.primary)
                                .padding(.bottom, 2)
                            Text(product.description)
                                .font(.headline)
                                .foregroundColor(.secondary)
                                .padding(.bottom, 15)
                        }
                        .layoutPriority(100)
                        
                        Spacer()
                    }
                }
            }
            .listRowInsets(EdgeInsets())
            .background(Color("BackColor"))
            .navigationBarTitle("List of products")
            .navigationBarItems(trailing: Button("Log Out") {
                self.userInfo.isUserAuthenticated = .signedOut
            })
        }
    }
}

struct ListProductsView_Previews: PreviewProvider {
    static var previews: some View {
        ListProductsView()
    }
}
