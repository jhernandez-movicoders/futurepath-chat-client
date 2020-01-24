package dev.futurepath.controllers;

import dev.futurepath.views.ChatView;
import dev.futurepath.views.RoomListView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutcomingMsgController {
    private ChatView chatview;
    private RoomListView roomListView;
    private final String SEP = " ";
    private final String SEND = "SEND";
    private final String JOIN = "JOIN";
    private final String LEAVE = "LEAVE";
    private final String DISCONNECT = "DISCONNECT";
    private final String LIST = "LIST";
    
    
    public void sendTextMessage(){
        String textMsg = this.chatview.getMessageTextField().getText();
        String toServerMsg = SEND + SEP + textMsg;
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void joinRoom(){
        String room = this.roomListView.getRoomTF().getText();
        String toServerMsg = JOIN + SEP + room;
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leaveRoom(){
        String toServerMsg = LEAVE;
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        String toServerMsg = DISCONNECT;
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listRomms(){
        String toServerMsg = LIST + SEP + "-r";
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
