/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_gui.systemConfig;

/**
 *
 * @author Dwayne
 */
public class JTreeInfo {
    
    private final String name;
    private final int id;
    
    public JTreeInfo(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
