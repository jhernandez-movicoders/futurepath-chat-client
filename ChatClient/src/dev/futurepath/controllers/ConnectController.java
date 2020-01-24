/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.controllers;

import dev.futurepath.views.ConnectView;
import dev.futurepath.views.RoomListView;
import java.awt.BorderLayout;
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
    
    //127.0.0.1 and 6066

    private String serverName = "192.168.2.83";
    private int port = 6666;
    private ConnectView connectFrame;
    protected static Socket client;
    protected static DataOutputStream out;
    protected static DataInputStream in;

    public ConnectController(ConnectView connectFrame) {
        this.connectFrame = connectFrame;
    }

    public void connect() {
            

        String username = this.connectFrame.getUserTF().getText();

        try {
            connectFrame.getContainerPanel().removeAll();
            connectFrame.getContainerPanel().setLayout(new BorderLayout());
            connectFrame.getContainerPanel().add(new RoomListView(), BorderLayout.CENTER);
            
            this.client = new Socket(this.serverName, this.port);
            InputStream inFromServer = this.client.getInputStream();
            this.in = new DataInputStream(inFromServer);
            IncomingMsgController inmc = new IncomingMsgController();

            OutputStream outToServer = this.client.getOutputStream();
            this.out = new DataOutputStream(outToServer);
            this.out.writeUTF("CONNECT " + username);

            //RoomListView roomLV = new RoomListView();
            connectFrame.getContainerPanel().removeAll();
            connectFrame.getContainerPanel().setLayout(new BorderLayout());
            connectFrame.getContainerPanel().add(new RoomListView(), BorderLayout.CENTER);

            /*if (this.in.readUTF().equals("OK")){
                RoomListView roomLV = new RoomListView();
                this.connectFrame.add(roomLV);
            }
            else{
                //gestionar mensaje de error
            }*/
        } catch (IOException ex) {
            Logger.getLogger(ConnectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
