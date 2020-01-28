package dev.futurepath.controllers;

import dev.futurepath.views.ChatView;
import dev.futurepath.views.RoomListView;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OutcomingMsgController {
    private ChatView chatview;
    private RoomListView roomListView;
    private final String SEP = " ";
    private final String SEND = "SEND";
    private final String JOIN = "JOIN";
    private final String LEAVE = "LEAVE";
    private final String DISCONNECT = "DISCONNECT";
    private final String LIST = "LIST";
    private static ArrayList<ChatView> panelList = new ArrayList<>();

    public OutcomingMsgController(RoomListView roomListView) {
        this.roomListView = roomListView;
    }
    
    public OutcomingMsgController(ChatView chatview) {
        this.chatview = chatview;
    }

    public static ArrayList<ChatView> getPanelList() {
        return panelList;
    }
    
    
    public void sendTextMessage(ChatView cv){ //Creo que se podría hacer con una instancia
        String textMsg = cv.getMessageTextField().getText();//Lista
        String toServerMsg = SEND + SEP + cv.getRoomName() + SEP + textMsg;
        try {
            ConnectController.out.writeUTF(toServerMsg);
           // cv.getMessagesTextArea().append(textMsg+"\n");
            cv.getMessageTextField().setText("");
            
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void joinRoom(){
        String room = this.roomListView.getRoomTF().getText();
        String toServerMsg = JOIN + SEP + room;
        this.roomListView.getRoomTF().setText("");
        try {
            
            ConnectController.out.writeUTF(toServerMsg);
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                Dimension d= new Dimension(500, 500);
                frame.setSize(d);
                frame.setPreferredSize(d);
                frame.setMinimumSize(d);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ChatView chatV = new ChatView(room);
                panelList.add(chatV);
                frame.add(chatV);
            }
        });
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
    
    public void listRooms(){
        String toServerMsg = LIST + SEP + "-r";
        try {
            ConnectController.out.writeUTF(toServerMsg);
        } catch (IOException ex) {
            Logger.getLogger(OutcomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
