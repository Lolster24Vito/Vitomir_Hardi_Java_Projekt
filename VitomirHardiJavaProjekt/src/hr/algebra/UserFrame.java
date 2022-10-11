/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Movie;
import hr.algebra.view.UserActorPanel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author vitom
 */
public class UserFrame extends javax.swing.JFrame {

    /**
     * Creates new form UserFrame
     */
     private MovieRepository repository;

    UserActorPanel userActorPanel;
    private DefaultListModel<String> moviesModel; 

    
    private List<Movie> movies;
    public UserFrame() {
        initComponents();
        
         try {
             init();
             initTabs();
         } catch (SQLException ex) {
             Logger.getLogger(UserFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPane = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuProfile = new javax.swing.JMenu();
        miLogOut = new javax.swing.JMenuItem();
        menuFile = new javax.swing.JMenu();
        miSave = new javax.swing.JMenuItem();
        menuDownload = new javax.swing.JMenu();
        miXMLDownload = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuProfile.setText("Profile");

        miLogOut.setText("Log out");
        menuProfile.add(miLogOut);

        jMenuBar1.add(menuProfile);

        menuFile.setText("File");

        miSave.setText("Save changes to database");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        menuFile.add(miSave);

        jMenuBar1.add(menuFile);

        menuDownload.setText("Download");

        miXMLDownload.setText("XML download");
        menuDownload.add(miXMLDownload);

        jMenuBar1.add(menuDownload);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_miSaveActionPerformed

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
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuDownload;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuProfile;
    private javax.swing.JMenuItem miLogOut;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JMenuItem miXMLDownload;
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables

    private void initTabs() {
        userActorPanel=new UserActorPanel(movies);
        tabPane.add("Actors",userActorPanel);
        
    }

    private void loadMovies() {
       
    }

    private void init() throws SQLException {
                 repository = RepositoryFactory.getMovieRepository();
                 movies=repository.getMovies();
                 

    }
}