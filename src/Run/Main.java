/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import Util.Control;
import Util.SingletonPatron;

/**
 *
 * @author Eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Control control = new Control();
        if (control.comprobar()) {

            SingletonPatron patron = SingletonPatron.getInstance();
            patron.getVisual().setVisible(true);

        } else {

        }

    }

}
