/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import protocol.Protocol;
import protocol.TestProtocol;

/**
 *
 * @author sasho
 */
public class ServerThread extends Thread {

    private Socket clientSocket = null;

    public ServerThread(Socket socket) {

        this.clientSocket = socket;
    }

    @Override
    public void run() {

        try {

            PrintWriter out
                    = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            Protocol chatProtocol = new Protocol();

            String input, output;

            
            TestProtocol tp = new TestProtocol();
            output = tp.processInput(null);
            out.println(output);

            while ((input = in.readLine()) != null) {
                output = tp.processInput(input);
                out.println(output);
                if (output.equals("Bye")) {
                    break;
                }
            }
            
            
            
            
            
            
            
            
            //process the input
            //output = chatProtocol.processInput(input);
            //out.println(output);
            /*
             while ((input = in.readLine()) != null) {
             output = chatProtocol.processInput(input);
             out.println(output);
             if (outputLine.equals(Protocol.STOP)) {
             break;
             }
             }
             */
            clientSocket.close();
        } catch (IOException e) {
        }
    }

}
