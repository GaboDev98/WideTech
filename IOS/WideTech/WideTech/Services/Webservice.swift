//
//  Webservice.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 13/10/20.
//

import Foundation

class Webservice {
    
    func getProducts(completion: @escaping ([Product]?) -> ()) {
        
        guard let url = URL(string: "http://ws4.shareservice.co/TestMobile/rest/GetProductsData") else {
            fatalError("Invalid URL")
        }
        
        // Prepare URL Request Object
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
        // HTTP Request Parameters which will be sent in HTTP Request Body
        let postString = "";
        
        // Set HTTP Request Body
        request.httpBody = postString.data(using: String.Encoding.utf8);
        
        URLSession.shared.dataTask(with: request) { data, response, error in
            
            guard let data = data, error == nil else {
                DispatchQueue.main.async {
                    completion(nil)
                }
                return
            }
            
            let posts = try? JSONDecoder().decode([Product].self, from: data)
            
            DispatchQueue.main.async {
                completion(posts)
            }
            
        }.resume();
    }
}
