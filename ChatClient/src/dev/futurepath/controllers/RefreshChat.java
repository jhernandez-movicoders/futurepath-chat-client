/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sir Daniel Gomez
 */
public class RefreshChat extends Thread{
    
    public interface ChatListener{
        void onMessageReceived();
    }
    
    private List<ChatListener> listeners = new ArrayList<>();
    
    public void addChatListener(ChatListener listener){
        listeners.add(listener);
    }
    
    private boolean receiving = true;
    
    public void stopReceiving() {
        receiving = false;
    }
    
    @Override
    public void run() {
        while(receiving) {
            try {
                listeners.forEach(listener -> { listener.onMessageReceived();} );
            } catch (Exception ex) {
                Logger.getLogger(RefreshChat.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }
}
