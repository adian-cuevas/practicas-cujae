package Util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class UserInterfaceSuport {


	public static void centerComponent(Window frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2,(screenSize.height - frame.getHeight()) / 2);
	}
	
        

	@SuppressWarnings("unchecked")
	public static void clearComponents(JPanel panel){
		
		Component [] components = panel.getComponents();
		for (Component component : components) {
			if (component instanceof JTextComponent) {
				((JTextComponent)component).setText("");
			}
			if (component instanceof JComboBox) {
				((JComboBox)component).setSelectedIndex(0);  
			}
		}
	}
        
        public static void fullScreenMode(JFrame win) {

        //win.setUndecorated(true);
        win.setLocation(0, 0);
        win.setAlwaysOnTop(true);
        win.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        win.setVisible(true);
    }

    /**
     * Pone una ventana en modo Pantalla Completa
     *
     * @param win Ventana de tipo {@link JDialog} que se desea poner en modo
     * Pantalla Completa
     */
    public static void fullScreenMode(JDialog win) {
        //win.setUndecorated(true);
        win.setLocation(0, 0);
        win.setAlwaysOnTop(false);
        win.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        win.setVisible(true);
    }

}
