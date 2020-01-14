/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.controllers;

import dev.futurepath.views.ConnectView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Gomez
 */

public class ConnectController {
    
    private String serverName = "192.168.43.9";
    private int port = 6066;
    private ConnectView connectFrame;
    protected static Socket client;
    protected static DataOutputStream out;
    protected static DataInputStream in;
    
    public ConnectController(ConnectView connect) {
        this.connectFrame = connectFrame;
    }
    
    public void Connect(){
        String username = this.connectFrame.getUsernameText().getText().trim();
            
        try {
            
            this.client = new Socket(this.serverName, this.port);
            
            OutputStream outToServer = this.client.getOutputStream();
            this.out = new DataOutputStream(outToServer);
            this.out.writeUTF("CONNECT "+username); //cambiar dependiendo de los monos borachos
            
            InputStream inFromServer = this.client.getInputStream();
            this.in = new DataInputStream(inFromServer);
            if (this.in.readUTF() == "mensaje afirmativo"){
                //abrir ventana correspondiente
            }
            else{
                //gestionar mensaje de error
            }
            //client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
