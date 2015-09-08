/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sasho
 */
public class Server {
    
   private static HashMap<String, String> userList;
   private static ClientHandler clientHandler;
    
    public static void main(String[] args) {
        
//        if (args.length != 1) {
//            System.err.println("<port number> not found");
//            System.exit(1);
//        }
//        
//        int portNumber = Integer.parseInt(args[0]);
        int portNumber = 8112;
        
        userList = new HashMap<String, String>();
        clientHandler = new ClientHandler();
        
        boolean listening = true;
        
        try {
            
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            while (listening) {
                
                new ServerThread(serverSocket.accept(), clientHandler, userList).start();
            }
            
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
        
    }
    
}
