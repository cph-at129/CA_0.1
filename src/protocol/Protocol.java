/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sasho
 */
public class Protocol {

    public static final String USER = "USER#";
    public static final String MSG = "MSG#";
    public static final String STOP = "STOP#";
    public static final String USERLIST = "USERLIST#";

    private final HashMap<String, String> userList;
    private String currentUser = "unknown";

    public Protocol(HashMap<String, String> userList) {

        this.userList = userList;
    }
    public String getCurrentUser(){
        
        return currentUser;
    }

    //process the input
    public String processInput(String input) {

        String output = null;
        String userName = null;

        if (input.isEmpty()) {//when new client connects
            String tmp = "";
            for (String value : userList.values()) {
                tmp += value + ",";
            }
            output = USERLIST + tmp;//return the userList
        } 
        else if (input.startsWith(USER)) {//if USER# command
            
            userName = input.substring(5);//get username
            this.currentUser = userName;
            
            userList.put(userName, userName);//insert the username in the map
            
            output = userName + ", you are now online!";//notify the user.. 
        } 
        else if (input.startsWith(STOP)) {//if user wants to disconnect
            
            output = STOP; //notify the server that it can stop the connection
            userList.remove(currentUser);
        }
        else if(input.startsWith(MSG)){//if user sends message
            
            output = input;
        } 
        else {
            output = "Unrecognized command!";
        }
        return output;
    }

    //String processInput(String theInput){}
}
