package dev.futurepath.controllers;

import dev.futurepath.views.ChatView;
import dev.futurepath.views.RoomListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @authors Daniel, Pedro, Anna
 */
public class IncomingMsgController implements RefreshChat.ChatListener {

    private RoomListView roomListView;
    private final String SEP = " ";
    private final String MESSAGE = "MESSAGE";
    private final String USERROOM = "USERROOM";
    private final String USER = "USER";
    private final String ROOM = "ROOM";
    private RefreshChat thread;

    public IncomingMsgController(RoomListView roomListView) {
        this.roomListView = roomListView;
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

            String serverMsg = ConnectController.in.readUTF();

            String[] parts = serverMsg.split(SEP);
            //String message2 = String.join(" ", parts);
            //System.out.println("MSG: " + message2);
            
            if (parts[0].equals(MESSAGE)) {
                String textMsg = "";
                for (int i = 3; i < parts.length; i++) {
                    textMsg += parts[i] + " ";
                }
                String message = "<" + parts[1] + "> " + textMsg + "\n";
                ArrayList<ChatView> list = OutcomingMsgController.getPanelList();
                for(ChatView p: list){
                    if(p.getRoomName().equals(parts[2])){
                        p.getMessagesTextArea().append(message);
                    }
                }
                serverMsg = "";
            }
            else if (parts[0].equals(USERROOM)) {
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 2; i < parts.length; i++) {
                    model.addElement(parts[i]);
                }
                ArrayList<ChatView> list = OutcomingMsgController.getPanelList();
                System.out.println(list.size());
                for(ChatView p: list){
                    if(p.getRoomName().equals(parts[1])){
                        p.getUserList().setModel(model);
                    }
                }
                serverMsg = "";
            }
            else if (parts[0].equals(ROOM)) {
                DefaultListModel<String> model = new DefaultListModel<>();
                
                for (int i = 1; i < parts.length; i++) {
                    model.addElement(parts[i]);
                }
                
                this.roomListView.getRoomList().setModel(model);
                serverMsg = "";
            }
            
        } catch (IOException ex) {
            Logger.getLogger(IncomingMsgController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
