/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import classes.Utils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Dwayne
 */
public class PNGFileFilter extends FileFilter{
    @Override
    public boolean accept(File file) {
        
        if(file.isDirectory()) {
            return true;
        }
        
        String name = file.getName();
        
        String extension = Utils.getFileExtension(name);
        
        if(extension == null) {
            return false;
        }
        
        return extension.equals("png");
    }
    
    @Override
    public String getDescription() {
        return "PNG files (*.png)";
    }
}