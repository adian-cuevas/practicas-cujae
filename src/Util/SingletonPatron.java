/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import Vista.Principal;


/**
 *
 * @author Alfonso
 */
public class SingletonPatron {
    private static Principal l = null;
    private static SingletonPatron instancia = null;
    
    private SingletonPatron(){
        
    }
    
    private synchronized static void createIntance(){
        if(instancia == null){
            instancia = new SingletonPatron();
            l = new Principal();
        }
    }
    
    public static SingletonPatron getInstance() {
        if(instancia == null){
            createIntance();
        }
        return instancia;
    }
    public Principal getVisual(){
        return l;
    }
}
