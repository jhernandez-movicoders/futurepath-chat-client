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

    private RefreshChat thread; //debería ser static??

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
    public void onMessageReceived() { //Este método podría simplente devolver strings y después hacer otros controllers. Un factory pattern para elegir controller???
        try {
            System.out.println("entro :D");
            //COMMAND USER MESSAGE

            String serverMsg = ConnectController.in.readUTF();

            String[] parts = serverMsg.split(SEP);
            System.out.println("MSG: " + parts[0]);
            if (parts[0].equals(MESSAGE)) {
                String textMsg = "";
                for (int i = 2; i < parts.length; i++) {
                    textMsg += parts[i] + " ";
                }
                String message = "<" + parts[1] + "> " + textMsg;
                chatview.getMessagesTextArea().append(message);
                serverMsg = "";
            }
            else if (parts[0].equals(USERROOM)) {
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 1; i < parts.length; i++) {
                    model.addElement(parts[i]);
                }
                this.chatview.getUserList().setModel(model);
            }
            else if (parts[0].equals(ROOM)) {
                DefaultListModel<String> model = new DefaultListModel<>();
                
                for (int i = 1; i < parts.length; i++) {
                    model.addElement(parts[i]);
                }
                System.out.println(parts[1]);
                
                //Es podible que tengamos poner el panel RoomListView aquí pues al conectar devuelve ya una lista de rooms
                this.roomListView.getRoomList().setModel(model);
            }
            
            //Un if más para los errores que salte 
        } catch (IOException ex) {
            Logger.getLogger(IncomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
