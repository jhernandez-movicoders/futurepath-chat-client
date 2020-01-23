/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.controllers;

import dev.futurepath.views.ChatView;
import dev.futurepath.views.RoomListView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author yo hehe, ayuda
 */
public class IncomingMsgController implements RefreshChat.ChatListener {

    private ChatView chatview;
    private RoomListView roomListView;
    private final String SEP = " ";
    private final String MESSAGE = "MESSAGE";
    private final String USERROOM = "USERROOM";
    private final String USER = "USER";
    private final String ROOM = "ROOM";
    
    private RefreshChat thread;

    public IncomingMsgController(ChatView chatview, RoomListView roomListView) {
        this.chatview = new ChatView();
        this.roomListView = new RoomListView();
        thread = new RefreshChat();
        thread.addChatListener(this);
        thread.start();
    }

    public IncomingMsgController() {
        thread = new RefreshChat();
        thread.addChatListener(this);
        thread.start();
    }

    @Override
    public void onMessageReceived() {
        try {
            System.out.println("entro :D");
            //COMMAND USER MESSAGE
            
            String serverMsg = ConnectController.in.readUTF();
            
            System.out.println("server msg "+serverMsg);
            //String serverMsg = "MESSAGE PEPE Hola dani hace tiempo que no hablamos, te echo de menos";
            String[] parts = serverMsg.split(SEP);//the split method can be changed depending on the received command
            System.out.println("parts "+parts[0]);
            if (parts[0].equals(MESSAGE)) {
                String textMsg = "";
                for (int i = 2; i < parts.length; i++) {
                    textMsg += parts[i] + " ";
                }
                String message = "<" + parts[1] + "> " + textMsg;
                //chatview.getMessagesTextArea().append(message);
                System.out.println(message);
                serverMsg = "";
            }
            if (parts[0] == USERROOM) {
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 1; i < parts.length; i++) {
                    ((DefaultListModel) model).addElement(parts[i]);
                }
                this.chatview.getUserList().setModel(model);
            }
            if (parts[0] == ROOM){
                
            }
        } catch (IOException ex) {
            Logger.getLogger(IncomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
