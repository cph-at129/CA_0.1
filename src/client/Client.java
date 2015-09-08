/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import handler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sasho
 */
public class Client implements Observer{

    private static ClientHandler clientHandler;
    
   
    
    public static void main(String[] args) {

//        if (args.length != 2) {
//            System.err.println(
//                    "<host name> <port number> NOT FOUND!!!");
//            System.exit(1);
//        }

//        String hostName = args[0];
//        int portNumber = Integer.parseInt(args[1]);
        String hostName = "localhost";
        int portNumber = 8112;

        try {
            Socket chatSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(chatSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(chatSocket.getInputStream()));
            
            BufferedReader stdIn
                    = new BufferedReader(new InputStreamReader(System.in));
            
            String fromServer;
            String fromUser;
            clientHandler = new ClientHandler();
            
            while ((fromServer = in.readLine()) != null) {
                
                System.out.println("Server: " + fromServer); 
                
                fromUser = stdIn.readLine();
                
                if (fromUser != null) {
                    out.println(fromUser);
                }
//                clientHandler.addObserver(new Observer(){//add this class as an observer
//                    @Override
//                    public void update(Observable o, Object arg) {
//                        
//                        System.out.println(arg.toString());//get the message from the observable
//                    }
//                });
                
                
                
            }
//                fromServer = in.readLine();
//                System.out.println("Server: " + fromServer);
                
                
                
                
//                if (fromUser != null) {
//                    System.out.println("Client: " + fromUser);
//                    out.println(fromUser);
//                }
                
            
            
            //get input from user
            
            //send user input to the server
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
