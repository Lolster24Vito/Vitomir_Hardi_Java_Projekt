/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import hr.algebra.factory.UrlConnectionFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author dnlbe
 */
public class FileUtils {

    private static final String UPLOAD = "Upload";

    public static File uploadFile(String description, String... extensions) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        chooser.setDialogTitle(UPLOAD);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
            return selectedFile.exists() && Arrays.asList(extensions).contains(extension.toLowerCase()) ? selectedFile : null;
        }
        return null;
    }

    public static void copyFromUrl(String source, String destination) throws MalformedURLException, IOException {
        createDirHierarchy(destination);
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(source);
        File tempFile=new File(destination);
        if(!tempFile.exists()){
            
        
        try (InputStream is = con.getInputStream()) {
            Files.copy(is, Paths.get(destination));
        }
        }
    }

    public static void copy(String source, String destination) throws FileNotFoundException, IOException {
        createDirHierarchy(destination);
        Files.copy(Paths.get(source), Paths.get(destination));
    }

    private static void createDirHierarchy(String destination) throws IOException {
        String dir = destination.substring(0, destination.lastIndexOf(File.separator));
        if (!Files.exists(Paths.get(dir))) {
            Files.createDirectories(Paths.get(dir));
        }
    }
    public static void deleteFilesFromDirectory(String destination) throws IOException{
                File directory = new File(destination);

        if (Files.exists(Paths.get(destination))) {
            for (File file: Objects.requireNonNull(directory.listFiles())) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
            
        }
    }

}