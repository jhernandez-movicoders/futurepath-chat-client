/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.futurepath.views;

import dev.futurepath.controllers.ConnectController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Pedro Ojeda
 */
public class ConnectView extends javax.swing.JFrame {

    private ConnectController controller;
    
    public ConnectView() {
        this.controller = new ConnectController(this);
        initComponents();
        Dimension d= new Dimension(500, 500);
        setSize(d);
        setPreferredSize(d);
        setMinimumSize(d);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        userTF = new javax.swing.JTextField();
        connectButtom = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        containerPanel.setLayout(null);
        containerPanel.add(userTF);
        userTF.setBounds(110, 90, 210, 22);

        connectButtom.setText("Connect");
        connectButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtomActionPerformed(evt);
            }
        });
        containerPanel.add(connectButtom);
        connectButtom.setBounds(260, 170, 79, 25);

        usernameLabel.setText("User:");
        containerPanel.add(usernameLabel);
        usernameLabel.setBounds(40, 90, 80, 16);

        getContentPane().add(containerPanel);
        containerPanel.setBounds(0, 0, 400, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtomActionPerformed
        controller.connect();
        //loadPanel(new RoomListView());
    }//GEN-LAST:event_connectButtomActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConnectView cv = new ConnectView();
                cv.setVisible(true);
                cv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
            }
        });
    }
    
    private void loadPanel(JPanel panelToLoad){
        this.containerPanel.removeAll();
        this.containerPanel.setLayout(new BorderLayout());
        this.containerPanel.add(panelToLoad, BorderLayout.CENTER);
    }

    public JTextField getUserTF() {
        return userTF;
    }

    public void setUserTF(JTextField userTF) {
        this.userTF = userTF;
    }
    
    public JPanel getContainerPanel() {
        return containerPanel;
    }

    public void setContainerPanel(JPanel containerPanel) {
        this.containerPanel = containerPanel;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButtom;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JTextField userTF;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
