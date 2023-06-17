/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfonso
 */
public class Control {

    static String appPath = System.getProperties().getProperty("user.dir");
    public static File fichero = new File(appPath + "\\miApp.tmp");
    static int segundos = 20;

    public Control() {
    }

    public boolean comprobar() {
        if (fichero.exists()) {
            long tiempo = leer();
            long res = restarTiempo(tiempo);
            if (res < segundos) {
                JOptionPane.showMessageDialog(null, "La aplicación ya está es ejecución");

                return false;
            } else {
                programarTarea();
                return true;
            }
        } else { //No existe fichero
            crearTMP();
            programarTarea();
            return true;
        }
    }

    private long leer() {
        String linea = "()";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fichero));
            while (bufferedReader.ready()) {
                linea = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return Long.valueOf(linea).longValue();
    }

    private long restarTiempo(long tiempoActual) {
        Date date = new Date();
        long tiempoTMP = date.getTime();
        long tiempo = tiempoTMP - tiempoActual;
        tiempo = tiempo / 1000;
        return tiempo;
    }

    private void programarTarea() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                crearTMP();
            }
        }, 1000, segundos * 1000, TimeUnit.MILLISECONDS);
    }

    private void crearTMP() {
        Date fecha = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
            writer.write(String.valueOf(fecha.getTime()));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void cerrarApp() {
        int i = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?");
        if (i == 0) {
            if (Control.fichero.exists()) {
                if (Control.fichero.delete()) {
                    System.err.println("se borro");
                    System.exit(0);//cierra aplicacion
                } else {
                    System.err.println("no se borro. hubo un error");
                }
            } else {
                System.exit(0);
            }
        }
    }

}
