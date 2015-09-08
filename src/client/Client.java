///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package client;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author sasho
// */
//public class Client {
//
//    public static void main(String[] args) {
//
//        if (args.length != 2) {
//            System.err.println(
//                    "<host name> <port number> NOT FOUND!!!");
//            System.exit(1);
//        }
//
//        String hostName = args[0];
//        int portNumber = Integer.parseInt(args[1]);
//
//        try {
//            Socket chatSocket = new Socket(hostName, portNumber);
//            PrintWriter out = new PrintWriter(chatSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(chatSocket.getInputStream()));
//            
//            BufferedReader stdIn
//                    = new BufferedReader(new InputStreamReader(System.in));
//            
//            //get input from user
//            
//            //send user input to the server
//            
//            
//            
//            
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//}
