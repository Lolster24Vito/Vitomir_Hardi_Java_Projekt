/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models.transferables;

import hr.algebra.models.Movie;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author vitom
 */
   public class ExportMovieTransferHandler extends TransferHandler {

        private final JList<Movie> allMovie;

        public ExportMovieTransferHandler(JList<Movie> allMovie) {
            this.allMovie = allMovie;
        }
        
        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
                      // return new MovieTransferable(jListAllMovies.getSelectedValue());
return new MovieTransferable(allMovie.getSelectedValue());
        }

    }
