/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_application;

import classes.Utils;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Dwayne
 */
public class Desktopt {
    public static boolean openFile(String fileName) throws IOException {
        File file = new File(fileName);
            byte buffer[] = new byte[(int) file.length()];
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName))) {
                input.read(buffer, 0, buffer.length);
            }
            String extension = Utils.getFileExtension(fileName);
        File temp = File.createTempFile("msctmp", "." + extension, new File("D:\\TESTING"));
        
            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(temp.getName()))) {
            output.write(buffer, 0, buffer.length);
            output.flush();
        }
        
        if(temp.canExecute()) {
            
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            //temp.deleteOnExit();
        }
        return true;
    }
}