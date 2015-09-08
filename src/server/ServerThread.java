/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Client;
import handler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocol.Protocol;

/**
 *
 * @author sasho
 */
public class ServerThread extends Thread implements Observer {

    private Socket clientSocket = null;
    private final ClientHandler clientHandler;
    private final HashMap<String, String> userList;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;

    public ServerThread(Socket clientSocket, ClientHandler clientHandler, HashMap<String, String> userList) {

        this.clientSocket = clientSocket;
        this.userList = userList;
        this.clientHandler = clientHandler;
    }

    @Override
    public void run() {

        try {

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;
            String outputLine;

            outputLine = clientHandler.getUserList(userList);
            out.println(outputLine);

            clientHandler.registerNewObserver(this);
            
            while ((inputLine = in.readLine()) != null) {

                outputLine = clientHandler.processClientMessage(inputLine);
                if (outputLine.equals(Protocol.STOP)) {
                    break;
                } 
                else if (outputLine.startsWith(Protocol.MSG)) {                   
                        clientHandler.sendMessage(outputLine);
                }
                else{
                    out.println(outputLine);//print welcome message
                }
            }
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Observable o, Object arg) {

        out.println(arg.toString());

    }

}
