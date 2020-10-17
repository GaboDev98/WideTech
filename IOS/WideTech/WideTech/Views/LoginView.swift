//
//  LoginView.swift
//  WideTech
//
//  Created by Angel Gabriel Ascanio Duran on 14/10/20.
//

import SwiftUI

struct LoginView: View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    var body: some View {
        
        ZStack {
            LinearGradient(gradient: .init(colors: [Color("Color"), Color("Color1"), Color("Color2")]), startPoint: .top, endPoint: .bottom).edgesIgnoringSafeArea(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
            
            if UIScreen.main.bounds.height > 800 {
                
                Home().environmentObject(userInfo)
            }
            else {
                
                ScrollView(.vertical, showsIndicators: false) {
                    
                    Home().environmentObject(userInfo)
                }
            }
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}

struct Home : View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    @State var index = 0
    
    var body : some View {
            
        VStack {
            
            Image("Login")
                .resizable()
                .frame(width: 300, height: 100)
                .padding(.top, 20)
            
            HStack {
                
                Button(action: {
                    
                    withAnimation(.spring(response: 0.8, dampingFraction: 0.5, blendDuration: 0.5)) {
                        
                        self.index = 0
                    }
                    
                }) {
                    Text("Existing").foregroundColor(self.index == 0 ? .black : .white)
                        .fontWeight(.bold)
                        .padding(.vertical, 10)
                        .frame(width: (UIScreen.main.bounds.width - 50) / 2)
                }.background(self.index == 0 ? Color.white : Color.clear)
                .clipShape(Capsule())
                
                Button(action: {
                    
                    withAnimation(.spring(response: 0.8, dampingFraction: 0.5, blendDuration: 0.5)) {
                        
                        self.index = 1
                    }
                    
                }) {
                    Text("New").foregroundColor(self.index == 1 ? .black : .white)
                        .fontWeight(.bold)
                        .padding(.vertical, 10)
                        .frame(width: (UIScreen.main.bounds.width - 50) / 2)
                }.background(self.index == 1 ? Color.white : Color.clear)
                .clipShape(Capsule())
                
            }.background(Color.black.opacity(0.1))
            .clipShape(Capsule())
            .padding(.top, 20)
            
            if self.index == 0 {
                
                Login().environmentObject(userInfo)

            }
            else {
                
                SignUp().environmentObject(userInfo)
            }
            
            if self.index == 0 {
                
                Button(action: {
                    
                }) {
                    
                    Text("Forget Password?")
                        .foregroundColor(.white)
                    
                }
                .padding(.top, 15)
            }
            
            HStack(spacing: 10) {
                
                Color.white.opacity(0.7)
                .frame(width: 35, height: 1)
                
                Text("Or")
                    .fontWeight(.bold)
                    .foregroundColor(.white)
                
                Color.white.opacity(0.7)
                .frame(width: 35, height: 1)
                
            }
            .padding(.top, 10)
            
            HStack {
                Button(action: {
                    
                }) {
                    
                    Image("Facebook")
                        .renderingMode(.original)
                        .padding()
                    
                }.background(Color.white)
                .clipShape(Circle())
                
                Button(action: {
                    
                }) {
                    
                    Image("Google")
                        .renderingMode(.original)
                        .padding()
                    
                }.background(Color.white)
                .clipShape(Circle())
            }
            .padding(.top, 10)
        }
        .padding()
    }
}

struct Login : View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    @State var email = ""
    @State var pass = ""
    
    var body : some View {
        
        VStack {
            
            HStack(spacing: 15) {
                
                Image(systemName: "envelope")
                    .foregroundColor(.black)
                
                TextField("Email", text: self.$email)
                
            }.padding(.vertical, 20)
            
            Divider()
            
            HStack(spacing: 15) {
                
                Image(systemName: "lock")
                .resizable()
                .frame(width: 15, height: 18)
                .foregroundColor(.black)
                
                SecureField("Password", text: self.$pass)
                
                Button(action: {
                    
                }) {
                    Image(systemName: "eye")
                        .foregroundColor(.black)
                }
                
            }.padding(.vertical, 20)
            
        }.padding(.vertical, 20)
        .padding(.horizontal, 20)
        .background(Color.white)
        .cornerRadius(10)
        .padding(.top, 25)
        
        Button(action: {
            
            self.userInfo.isUserAuthenticated = .signedIn
            
        }) {
            
            Text("LOGIN")
                .foregroundColor(.white)
                .fontWeight(.bold)
                .padding(.vertical)
                .frame(width: UIScreen.main.bounds.width - 100)
            
        }.background(
            LinearGradient(gradient: .init(colors:
                [Color("Color2"), Color("Color1"), Color("Color")]), startPoint: .leading, endPoint: .trailing)
        )
        .cornerRadius(8)
        .offset(y: -30)
        .padding(.bottom, -30)
        .shadow(radius: 15)
    }
}

struct SignUp : View {
    
    @EnvironmentObject var userInfo: UserInfo
    
    @State var email = ""
    @State var pass = ""
    @State var repass = ""
    
    var body : some View {
        
        VStack {
            
            HStack(spacing: 15) {
                
                Image(systemName: "envelope")
                    .foregroundColor(.black)
                
                TextField("Email", text: self.$email)
                
            }.padding(.vertical, 20)
            
            Divider()
            
            HStack(spacing: 15) {
                
                Image(systemName: "lock")
                .resizable()
                .frame(width: 15, height: 18)
                .foregroundColor(.black)
                
                SecureField("Password", text: self.$pass)
                
                Button(action: {
                    
                }) {
                    Image(systemName: "eye")
                        .foregroundColor(.black)
                }
                
            }.padding(.vertical, 20)
            
            Divider()
            
            HStack(spacing: 15) {
                
                Image(systemName: "lock")
                .resizable()
                .frame(width: 15, height: 18)
                .foregroundColor(.black)
                
                SecureField("Re-Enter", text: self.$pass)
                
                Button(action: {
                    
                }) {
                    Image(systemName: "eye")
                        .foregroundColor(.black)
                }
                
            }.padding(.vertical, 20)
            
        }.padding(.vertical, 20)
        .padding(.horizontal, 20)
        .background(Color.white)
        .cornerRadius(10)
        .padding(.top, 25)
        
        Button(action: {
            
        }) {
            
            Text("SIGNUP")
                .foregroundColor(.white)
                .fontWeight(.bold)
                .padding(.vertical)
                .frame(width: UIScreen.main.bounds.width - 100)
            
        }.background(
            LinearGradient(gradient: .init(colors:
                [Color("Color2"), Color("Color1"), Color("Color")]), startPoint: .leading, endPoint: .trailing)
        )
        .cornerRadius(8)
        .offset(y: -30)
        .padding(.bottom, -30)
        .shadow(radius: 15)
    }
}
