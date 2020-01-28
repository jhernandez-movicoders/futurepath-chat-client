/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.views;

/**
 *
 * @author Daniel Gomez
 */
public class RoomView extends javax.swing.JPanel {

    /**
     * Creates new form RoomChatView
     */
    public RoomView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        messagesTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        sendButton = new javax.swing.JButton();
        messageTextField = new javax.swing.JTextField();

        setLayout(null);

        messagesTextArea.setColumns(20);
        messagesTextArea.setRows(5);
        jScrollPane1.setViewportView(messagesTextArea);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 270, 200);

        userList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(userList);

        add(jScrollPane2);
        jScrollPane2.setBounds(290, 10, 100, 280);

        sendButton.setText("Send");
        add(sendButton);
        sendButton.setBounds(200, 230, 70, 40);
        add(messageTextField);
        messageTextField.setBounds(20, 230, 160, 40);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JTextArea messagesTextArea;
    private javax.swing.JButton sendButton;
    private javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}
