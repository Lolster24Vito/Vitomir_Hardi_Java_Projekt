/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

/**
 *
 * @author vitom
 */
public interface UserRepository {
    
    void createUser(String username,String passwordHash) throws Exception ;
    boolean checkIfUserExists(String username) throws Exception;
    int userLogin(String username, String passwordHash);
    
}
