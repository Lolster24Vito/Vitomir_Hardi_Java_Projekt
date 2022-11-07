
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Actor;
import hr.algebra.models.Generic2ForeignKeyDB;
import hr.algebra.models.GenericDbEntity;
import hr.algebra.models.Movie;
import hr.algebra.models.transferables.ExportMovieTransferHandler;
import hr.algebra.models.transferables.MovieTransferable;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author vitom
 */
public class UserActorPanel extends javax.swing.JPanel  {

    /**
     * Creates new form UserActorPanel
     */
    //transfer drag and drog transfersupport samo int i preko dictionary-a ga nabaviti ili obicne list get
    private List<Movie> allMovies;
    private List<Actor> allActors;
    private List<Movie> moviesOfActor;
    //added movies and actors
    private List<Generic2ForeignKeyDB> moviesWithActorAdded = new ArrayList<>();

    private DefaultListModel<Movie> moviesModel;
    private DefaultListModel<Movie> actorMoviesModel;

    private DefaultListModel<Actor> actorsModel;

    private Actor selectedActor = null;
    private MovieRepository repository;

    public UserActorPanel(List<Movie> movies) {
        initComponents();
        allMovies = movies;
        init();
        String test = getActorNameById(1001);

    }

    public void LoadActors() throws SQLException {
        allActors = new ArrayList<>(repository.getActors());
    }

    private String getActorNameById(int id) //throws Exception
    {
        Optional<Actor> findFirst = allActors.stream().filter(a -> a.getId() == id).findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get().toString();
        } else {
            return "";
        }
        //throw new Exception("ActorNotFound");
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
        jListAllActors = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfActorName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAllMovies = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListMoviesWithActor = new javax.swing.JList<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 747));

        jListAllActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListAllActorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListAllActors);

        jLabel1.setText("Select an actor");

        jLabel2.setText("Actor name:");

        jLabel3.setText("Movies with actor");

        jScrollPane2.setViewportView(jListAllMovies);

        jLabel4.setText("All movies");

        jScrollPane3.setViewportView(jListMoviesWithActor);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(487, 487, 487)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfActorName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfActorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jListAllActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListAllActorsMouseClicked
        selectedActor = (Actor) jListAllActors.getSelectedValue();
        if (selectedActor != null) {
            tfActorName.setText(selectedActor.getName());
            actorMoviesModel.clear();
            jListMoviesWithActor.removeAll();
            try {
                //only save shown result on page
                moviesWithActorAdded.clear();
                loadActorMovies(selectedActor.getId());
            } catch (SQLException ex) {
                Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jListAllActorsMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        saveChanges();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
            // TODO add your handling code here:
            addActor();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteActor();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<Actor> jListAllActors;
    private javax.swing.JList<Movie> jListAllMovies;
    private javax.swing.JList<Movie> jListMoviesWithActor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField tfActorName;
    // End of variables declaration//GEN-END:variables

    private void init() {
        repository = RepositoryFactory.getMovieRepository();
        actorsModel = new DefaultListModel<>();
        moviesModel = new DefaultListModel<>();
        actorMoviesModel = new DefaultListModel<>();
        try {

            LoadActors();

        } catch (SQLException ex) {
            Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initDragNDrop();
        refreshActors();
        refreshMovies();

    }

    private void refreshMovies() {
        moviesModel.clear();
        // allMovies.forEach(movie-> moviesModel.addElement(movie.toString()));
        allMovies.forEach(movie -> moviesModel.addElement(movie));
        jListAllMovies.setModel(moviesModel);
    }

    private void refreshActors() {
        actorsModel.clear();
        allActors.forEach(actor -> actorsModel.addElement(actor));
        jListAllActors.setModel(actorsModel);
    }

    private void loadActorMovies(int actorId) throws SQLException {
        moviesOfActor = repository.getMoviesOfActor(actorId);
        refreshActorMovies();
    }

    private void initDragNDrop() {
        jListAllMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListAllMovies.setDragEnabled(true);
        jListAllMovies.setTransferHandler(new ExportMovieTransferHandler(jListAllMovies));

        jListMoviesWithActor.setDropMode(DropMode.ON);
        jListMoviesWithActor.setTransferHandler(new ImportMovieActorsTransferHandler());

    }

    private void refreshActorMovies() {
        actorMoviesModel.clear();
        moviesOfActor.forEach(g -> actorMoviesModel.addElement(g));
        jListMoviesWithActor.setModel(actorMoviesModel);
    }

   // @Override saveable interface
    public void saveChanges() {

        try {
            //repository add,

            //repository update changes to actor
//update current selected movies
            if (!moviesWithActorAdded.isEmpty()) {
                repository.addMoviesToActor(moviesWithActorAdded);
                moviesWithActorAdded.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActor() {
        try {
            String actorName=tfActorName.getText().trim();
            int id=repository.createActor(actorName);
            actorsModel.addElement(new Actor(id,actorName));
            allActors.add(new Actor(id,actorName));
            jListAllActors.setModel(actorsModel);

        } catch (SQLException ex) {
            Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteActor() {
        try {
            Actor actor=selectedActor;
            repository.deleteActor(actor.getId());
            
            allActors.removeIf(p->p.getId()==actor.getId());
            actorsModel.removeElement(actor);
        } catch (SQLException ex) {
            Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class ImportMovieActorsTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(MovieTransferable.MOVIE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Movie add = (Movie) transferable.getTransferData(MovieTransferable.MOVIE_FLAVOR);
                moviesOfActor.add(add);
                moviesWithActorAdded.add(new Generic2ForeignKeyDB(add.getId(), selectedActor.getId()));
                refreshActorMovies();

            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(UserActorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

}
