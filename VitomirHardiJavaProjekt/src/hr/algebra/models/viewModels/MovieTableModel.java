/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models.viewModels;

import hr.algebra.models.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vitom
 */
public class MovieTableModel extends AbstractTableModel{

        private static final String[] COLUMN_NAMES = {"Id", "Title", "Released date", "Original name","Duration", "Poster path"};
    private List<Movie> movies;
    
    public MovieTableModel(List<Movie> movies){
        this.movies=movies;
    }
    
    public void setMovies(List<Movie> movies){
        this.movies=movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
return movies.size(); 
    }

    @Override
    public int getColumnCount() {
        //todo potential bug here
        return COLUMN_NAMES.length;
    //    return Movie.class.getDeclaredFields().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0:
                    return movies.get(rowIndex).getId();
                case 1:
                     return movies.get(rowIndex).getTitle();
                case 2:
                     return movies.get(rowIndex).getReleased().toString();
                case 3:
                     return movies.get(rowIndex).getOriginalName();
                case 4:
                     return movies.get(rowIndex).getDuration();
                case 5:
                     return movies.get(rowIndex).getPosterPath();
                             // return movies.get(rowIndex).
                default:
                    throw new RuntimeException("No such column");
            }
    }
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
      @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Integer.class;
            case 4:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); 
    }

    
}
