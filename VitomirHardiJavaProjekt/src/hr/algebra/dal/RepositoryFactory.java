/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.MovieSqlRepository;
import hr.algebra.dal.sql.UserSqlRepository;

/**
 *
 * @author vitom
 */
public class RepositoryFactory {


    private RepositoryFactory() {
    }
    
    public static MovieRepository getMovieRepository() throws Exception {
        return new MovieSqlRepository();
    }
        public static UserRepository getUserRepository() {
        return new UserSqlRepository();
    }
}
