/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Dwayne
 */
public class DOCFileFilter  extends FileFilter {
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
        
        if(extension.equals("doc")) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public String getDescription() {
        return "Word documents (*.doc)";
    }
}