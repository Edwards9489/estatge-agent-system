package interfaces;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dwayne
 */
public class RegistryLoader {

    private static boolean loaded = false;

    public static void Load() throws RemoteException {
        if (loaded) {
            return;
        }
        LocateRegistry.createRegistry(1099);
        System.out.println("Started Registry");
        loaded = true;
    }

    private RegistryLoader() {

    }
}
