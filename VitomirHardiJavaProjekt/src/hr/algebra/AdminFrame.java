/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Actor;
import hr.algebra.models.Director;
import hr.algebra.models.Genre;
import hr.algebra.models.Movie;
import hr.algebra.models.MovieArchive;
import hr.algebra.parsers.rss.MovieParser;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.MessageUtils;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author vitom
 */
public class AdminFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private MovieRepository repository;

    private DefaultListModel<String> moviesModel;
    private DefaultListModel<String> actorsModel;
    private DefaultListModel<String> directorsModel;
    private DefaultListModel<String> genresModel;

    private static final String UPLOAD_MOVIES = "Upload articles";
    private static final String EDIT_MOVIES = "Edit articles";

    private static final String POSTER_DIR = "assets\\moviePosters";

    public AdminFrame() {
        initComponents();
        try {
            init();
        } catch (Exception ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void init() throws Exception {

        moviesModel = new DefaultListModel<>();
        actorsModel = new DefaultListModel<>();
        directorsModel = new DefaultListModel<>();
        genresModel = new DefaultListModel<>();

        repository = RepositoryFactory.getMovieRepository();

        MovieArchive movieArchiveDatabase = new MovieArchive();
        movieArchiveDatabase.setActors(repository.getActors());
        movieArchiveDatabase.setDirectors(repository.getDirectors());
        movieArchiveDatabase.setGenres(repository.getGenres());
        movieArchiveDatabase.setMovies(repository.getMovies());
        //MovieArchive movieArchiveDatabase=repository.getMovieData();
        updateJLists(movieArchiveDatabase);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUpload = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelMovies = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlMovies = new javax.swing.JList<>();
        jPanelActors = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlActors = new javax.swing.JList<>();
        jPanelDirectors = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlDirectors = new javax.swing.JList<>();
        jPanelGenres = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlGenres = new javax.swing.JList<>();
        jMenuBar = new javax.swing.JMenuBar();
        menuProfile = new javax.swing.JMenu();
        miSignOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 800));

        btnUpload.setText("Upload novih podataka");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnDeleteAll.setText("Brisanje svih podataka");
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jlMovies);

        javax.swing.GroupLayout jPanelMoviesLayout = new javax.swing.GroupLayout(jPanelMovies);
        jPanelMovies.setLayout(jPanelMoviesLayout);
        jPanelMoviesLayout.setHorizontalGroup(
            jPanelMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoviesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1143, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMoviesLayout.setVerticalGroup(
            jPanelMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMoviesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Movies", jPanelMovies);

        jScrollPane2.setViewportView(jlActors);

        javax.swing.GroupLayout jPanelActorsLayout = new javax.swing.GroupLayout(jPanelActors);
        jPanelActors.setLayout(jPanelActorsLayout);
        jPanelActorsLayout.setHorizontalGroup(
            jPanelActorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActorsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanelActorsLayout.setVerticalGroup(
            jPanelActorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActorsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Actors", jPanelActors);

        jScrollPane3.setViewportView(jlDirectors);

        javax.swing.GroupLayout jPanelDirectorsLayout = new javax.swing.GroupLayout(jPanelDirectors);
        jPanelDirectors.setLayout(jPanelDirectorsLayout);
        jPanelDirectorsLayout.setHorizontalGroup(
            jPanelDirectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDirectorsLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDirectorsLayout.setVerticalGroup(
            jPanelDirectorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Directors", jPanelDirectors);

        jScrollPane4.setViewportView(jlGenres);

        javax.swing.GroupLayout jPanelGenresLayout = new javax.swing.GroupLayout(jPanelGenres);
        jPanelGenres.setLayout(jPanelGenresLayout);
        jPanelGenresLayout.setHorizontalGroup(
            jPanelGenresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGenresLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelGenresLayout.setVerticalGroup(
            jPanelGenresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGenresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Genres", jPanelGenres);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );

        menuProfile.setText("Profile");

        miSignOut.setText("Sign out");
        miSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSignOutActionPerformed(evt);
            }
        });
        menuProfile.add(miSignOut);

        jMenuBar.add(menuProfile);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed

        MovieArchive movieArchiveloaded;
        MovieArchive movieArchiveDatabase;
        try {

            //sql working code
            //movieArchiveDatabase=repository.getMovieData();
            //repository.createMovies(movieArchive.getMovies());
            //repository.setMovieActor("Test", 236);
            //  repository.createMovies(movieArchive.getMovies());
            //xml working code
            movieArchiveloaded = MovieParser.parse();
            repository.addMovieArchive(movieArchiveloaded);
            updateJLists(repository.getAllMData());
            MessageUtils.showInformationMessage("Success", "Successfully uploaded all files");

        } catch (ParseException ex) {
            MessageUtils.showErrorMessage("Error", "Parse error");
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Error happened while uploading");

            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        try {
            // TODO add your handling code here:
            repository.deleteAllData();
            clearJLists();
            FileUtils.deleteFilesFromDirectory(POSTER_DIR);
            MessageUtils.showInformationMessage("Success", "Successfully deleted all files");
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Files cannot be deleted");
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void miSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSignOutActionPerformed
        // TODO add your handling code here:
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.show();
        this.dispose();
    }//GEN-LAST:event_miSignOutActionPerformed

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
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnUpload;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelActors;
    private javax.swing.JPanel jPanelDirectors;
    private javax.swing.JPanel jPanelGenres;
    private javax.swing.JPanel jPanelMovies;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> jlActors;
    private javax.swing.JList<String> jlDirectors;
    private javax.swing.JList<String> jlGenres;
    private javax.swing.JList<String> jlMovies;
    private javax.swing.JMenu menuProfile;
    private javax.swing.JMenuItem miSignOut;
    // End of variables declaration//GEN-END:variables

    private void updateJListMovies(List<Movie> movies) {
        moviesModel.clear();
        movies.forEach(movie -> moviesModel.addElement(movie.toString()));
        jlMovies.setModel(moviesModel);
    }

    private void updateJListActors(Set<Actor> actors) {

        actorsModel.clear();
        actors.forEach(actor -> actorsModel.addElement(actor.getName()));
        jlActors.setModel(actorsModel);

    }

    private void updateJListDirectors(Set<Director> directors) {

        directorsModel.clear();
        directors.forEach(actor -> directorsModel.addElement(actor.getName()));
        jlDirectors.setModel(directorsModel);

    }

    private void updateJListGenres(Set<Genre> genres) {

        genresModel.clear();
        genres.forEach(genre -> genresModel.addElement(genre.getName()));
        jlGenres.setModel(genresModel);

    }

    ;
     
    private void updateJListGeneric(Set<String> names, DefaultListModel<String> defaultModel, JList<String> jlList) {
        defaultModel.clear();
        names.forEach(defaultModel::addElement);
        jlList.setModel(defaultModel);
    }

    private void updateJLists(MovieArchive movieArchive) {
        updateJListMovies(movieArchive.getMovies());
        updateJListActors(movieArchive.getActors());
        updateJListDirectors(movieArchive.getDirectors());
        updateJListGenres(movieArchive.getGenres());
    }

    private void clearJLists() {
        moviesModel.clear();
        actorsModel.clear();
        genresModel.clear();
        jlMovies.removeAll();
        jlActors.removeAll();
        jlGenres.removeAll();
    }

}
