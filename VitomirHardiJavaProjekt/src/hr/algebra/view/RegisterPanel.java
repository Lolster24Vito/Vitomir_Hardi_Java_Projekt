/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view;

import hr.algebra.dal.RepositoryFactory;
import hr.algebra.dal.UserRepository;
import hr.algebra.utils.EncryptionUtils;
import java.awt.Color;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitom
 */
public class RegisterPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegisterPanel
     */
    
    private UserRepository repository;
    


    
    public RegisterPanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();
        btnCreateAccount = new javax.swing.JButton();
        lbPasswordError = new javax.swing.JLabel();
        lbUsernameError = new javax.swing.JLabel();
        lbPasswordConfirmError = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel1.setText("REGISTER");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Password:");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel4.setText("Confirm password:");

        tfUsername.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        pfPassword.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        pfConfirmPassword.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N

        btnCreateAccount.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        btnCreateAccount.setText("Create account");
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });

        lbPasswordError.setForeground(new java.awt.Color(255, 0, 51));

        lbUsernameError.setForeground(new java.awt.Color(255, 0, 51));

        lbPasswordConfirmError.setForeground(new java.awt.Color(255, 0, 51));

        lbStatus.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(102, 204, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(btnCreateAccount)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUsername)
                            .addComponent(pfPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPasswordConfirmError)
                                    .addComponent(lbUsernameError)
                                    .addComponent(lbPasswordError))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pfConfirmPassword)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbStatus))
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(lbUsernameError)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPasswordError)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lbPasswordConfirmError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateAccount)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
        // TODO add your handling code here:
        if(validateData()){
            try {
                boolean usernameExists=repository.checkIfUserExists(tfUsername.getText().trim());
                //todo sql check if exists code and proc
                if(!usernameExists){
                repository.createUser(tfUsername.getText().trim(), EncryptionUtils.encryptSHA256(pfPassword.getPassword()));
                            accountSuccesfullyCreated();
                  }
                else{
                        lbUsernameError.setText("This username is already taken");
                }
                
            } catch (Exception ex) {
                lbStatus.setText("Error account cannot be created");
                lbStatus.setForeground(Color.RED);
                Logger.getLogger(RegisterPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_btnCreateAccountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbPasswordConfirmError;
    private javax.swing.JLabel lbPasswordError;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbUsernameError;
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    private boolean validateData() {
        boolean usernameValid = !tfUsername.getText().trim().isEmpty();
        boolean passwordValid = pfPassword.getPassword().length>0;
        boolean confirmPasswordValid = Arrays.equals(pfPassword.getPassword(), pfConfirmPassword.getPassword());
                
                //pfPassword.getText().equals(pfConfirmPassword.getText());

        lbUsernameError.setText("");
        lbPasswordError.setText("");
        lbPasswordConfirmError.setText("");

        if (!usernameValid) {
            lbUsernameError.setText("Username cannot be empty");
        }
        if (!passwordValid) {
            lbPasswordError.setText("Password cannot be empty");
        }
        if (!confirmPasswordValid) {
            lbPasswordConfirmError.setText("Passwords do not match");
        }
        return usernameValid & passwordValid & confirmPasswordValid;
    }
    
      private void init(){
        repository=RepositoryFactory.getUserRepository();
    }

    private void accountSuccesfullyCreated() {
                    lbStatus.setText("Account succesfully created");
                                    lbStatus.setForeground(new Color(102,204,0));

                    pfPassword.setText("");
                    pfConfirmPassword.setText("");
                    tfUsername.setText("");

    }


}
