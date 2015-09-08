/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import client.Client;
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
public class ClientHandler extends Observable {

    private Protocol pro;

    public void registerNewObserver(Observer o) {

        addObserver(o);
        System.out.println("Number of Observers: " + countObservers());

    }

    public void sendMessage(String msg) {

        int secondHashIndex = msg.indexOf("#", 4);//get the index of the second hash
        
        if (secondHashIndex == 5) {//means the client used *

            setChanged();//mark the observable as changed
            notifyObservers(msg);//notify all observers
        }else{
            
            setChanged();//mark the observable as changed

            notifyObservers(msg);//notify all observers
        }

        
    }

    //returns the userList HashMap as a String
    public String getUserList(HashMap<String, String> userList) {

        pro = new Protocol(userList);
        String output = pro.processInput("");//pass empty string to get userlist
        return output;
    }

    //return the current user
    public String getCurrentUser() {

        return pro.getCurrentUser();
    }

    public String processClientMessage(String msg) {

        String output = pro.processInput(msg);

        return output;
    }

}
