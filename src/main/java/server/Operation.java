package server;

import java.rmi.RemoteException;

/*
 * Interface for holding operation methods
 */

public interface Operation {

    String add(double val1, double val2);
    String subt(double val1, double val2);
    String mult(double val1, double val2);
    String div(double val1, double val2);
}
