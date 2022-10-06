/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.UserRepository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author vitom
 */
public class UserSqlRepository implements UserRepository {

    private static final String CREATE_USER = "{ CALL CreateUser (?,?) }";
    private static final String CHECK_IF_USER_EXISTS = "{ CALL CheckIfUserExists (?) }";
    private static final String USER_LOGIN = "{ CALL UserLogin (?,?) }";


    
    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwordHash";
    private static final String ROLE_ID = "RoleId";

    private static final String EXISTS = "DoesExists";




    @Override
    public void createUser(String username, String passwordHash) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
                stmt.setString("@"+USERNAME,username);
                stmt.setString("@"+PASSWORD, passwordHash);
                stmt.executeUpdate();

        }
    }

    @Override
    public boolean checkIfUserExists(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CHECK_IF_USER_EXISTS)) {
                stmt.setString("@"+USERNAME,username);
            
            try(ResultSet rs=stmt.executeQuery()){
            if(rs.next()){
            return rs.getBoolean(EXISTS);
            }
            }
        }
        
        return true;
    }

    //returns roleId as int
    @Override
    public int userLogin(String username, String passwordHash) {
                DataSource dataSource = DataSourceSingleton.getInstance();
                int roleId=-1;
              try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_LOGIN)) {
                stmt.setString("@"+USERNAME,username);
                stmt.setString("@"+PASSWORD,passwordHash);

            
            try(ResultSet rs=stmt.executeQuery()){
            if(rs.next()){
            roleId=rs.getInt(ROLE_ID);
            }
            }
        } catch (SQLException ex) {
           return -2;
        }
              return roleId;
                
                
    }

}
