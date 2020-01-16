/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.controllers;

import dev.futurepath.views.ChatView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author yo hehe, ayuda
 */
public class ChatController implements RefreshChat.ChatListener {

    private ChatView chatview;
    private final String SEP = "-";
    private final String MENSAJE = "MSG";
    private final String LISTUSERS = "LISTUSERS";
    
    private RefreshChat thread;

    public ChatController(ChatView chatview) {
        this.chatview = new ChatView();
        thread = new RefreshChat();
        thread.addChatListener(this);
        thread.start();
    }

    @Override
    public void onMessageReceived() {
        try {

            String serverMsg = ConnectController.in.readUTF();
            String[] parts = serverMsg.split(SEP);//the split method can be changed depending on the received command
            if (parts[0] == MENSAJE) {
                String message = "<" + parts[1] + "> " + parts[2];
                chatview.getMessagesTextArea().append(message);
            }
            if (parts[0] == LISTUSERS) {

                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 1; i < parts.length; i++) {
                    ((DefaultListModel) model).addElement(parts[i]);
                }
                this.chatview.getUserList().setModel(model);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
