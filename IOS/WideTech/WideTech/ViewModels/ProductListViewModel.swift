//
//  ProductListViewModel.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 16/10/20.
//

import Foundation

class ProductListViewModel: ObservableObject {
    
    @Published var products = [ProductViewModel]()
    
    init() {
        
        Webservice().getProducts { products in
            
            if let products = products {
                self.products = products.map(ProductViewModel.init)
            }
        }
        
    }
}

struct ProductViewModel {
    
    var product: Product
    
    init(product: Product) {
        self.product = product
    }
    
    var description: String {
        return self.product.Description
    }
    
    var imageUrl: String {
        return self.product.ImageUrl
    }
    
    var name: String {
        return self.product.Name
    }
}
