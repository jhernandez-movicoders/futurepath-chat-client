/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anna Cilona
 */
public class RoomList {
    
    private List<String> rooms;

    public RoomList() {
        rooms = new ArrayList<>();
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }
    
    
}
