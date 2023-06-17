/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba5;

import java.util.ArrayList;

/**
 *
 * @author Eduardo
 */
public class PruebaExpresion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String aux="catp(95)+altmuslop(5)-cat";//los espacios cuentan como posiciones
        String comp="refwjshdjhsd";
        String comp1="catp(95)";
        String comp2="catp(95)-cat";
        String comp3="altmuslop(5)-cat+catp(95)";
        //int result=contieneOperador(aux);
        int result=posicionOperador(aux);
        //System.out.println("este es la longitud: "+aux.length()+"\n");//25
        System.out.println("este es la subcadena: "+aux.substring(0, result)+"\n");//catp(95)
        //System.out.println("este es la posicion del primer operador: "+result);//8
        //System.out.println("este es la subcadena: "+aux.substring(result+1)+"\n");//altmuslop(5)-cat
        //posicionesOperadores(aux);
        ArrayList<String> list=guardaPartes(comp);
        //System.out.println("Esto representa si lo contiene: "+list.contains("cat"));
//        try {
//            for (String list1 : list) {
//                System.out.println("Estos son los valores: "+list1);
//            }
//        } catch (Exception e) {
//            System.out.println("La lista esta vacia o no existe\n");
//        }
//        boolean contiene=igualExpresion(list, aux);
//        System.out.println("Este es el valor devuelto: "+contiene+"\n");
//        ArrayList<String> list1=guardaPartes(comp1);
//        boolean contiene1=igualExpresion(list1, aux);
//        System.out.println("Este es el valor devuelto: "+contiene1+"\n");
//        ArrayList<String> list2=guardaPartes(comp2);
//        boolean contiene2=igualExpresion(list2, aux);
//        System.out.println("Este es el valor devuelto: "+contiene2+"\n");
//        ArrayList<String> list3=guardaPartes(comp3);
//        boolean contiene3=igualExpresion(list3, aux);
//        System.out.println("Este es el valor devuelto: "+contiene3+"\n");
    }
    
    public static int contieneOperador(String exp){//con boolean identifico si tiene operadores
        int result=0;
        if (exp.contains("+")) {
            result++;
        } if(exp.contains("-")){
            result++;
        } if(exp.contains("*")){
            result++;
        } if(exp.contains("/")){
            result++;
        }
        return result;
    }
    
//    public static int posicionOperador(String exp){//ya me devuelve la posicion del operador menos 1
//        int aux=-1;
//        for (int i=0; i < exp.length(); i++) {
//            char auxchar=exp.charAt(i);
//            if (auxchar=='+'|| auxchar=='-' || auxchar=='/' || auxchar=='*' ) {
//                aux=i;
//                break;
//            }
//        }
//        return aux;//si no encuentra un operador devuelve menos 1
//    }
    
    public static void posicionesOperadores(String valor){
        String auxvalor=valor;
        while (posicionOperador(auxvalor)!=-1) {            
            int auxpos=posicionOperador(auxvalor);
            System.out.println("Esta son las posiciones: "+auxpos);
            auxvalor=auxvalor.substring(auxpos+1);
        }
        System.out.println("No hay mas  ***************\n");
    }
    
    public static ArrayList<String> guardaPartes(String valor) {
        ArrayList<String> element=new ArrayList();
        String auxvalor=valor;
        int indice=-1;
        while (posicionOperador(auxvalor)!=-1) {            
            int auxpos=posicionOperador(auxvalor);
            element.add(auxvalor.substring(indice+1,auxpos));
            auxvalor=auxvalor.substring(auxpos+1);
        }
        element.add(auxvalor);
        return element;
    }
    
    public static boolean igualExpresion(ArrayList<String> list,String valor){
        /**
         * list es lo que escribe el estudiante
         * comprueba lo escrito pore el estudiante con la base de datos
         */
        boolean result=true;
        String auxvalor=valor;
        int indice=-1;
        while (posicionOperador(auxvalor)!=-1) {            
            int auxpos=posicionOperador(auxvalor);
            if (!list.contains(auxvalor.substring(indice+1,auxpos))) {
                result=false;
                break;
            }
            auxvalor=auxvalor.substring(auxpos+1);
        }if (!list.contains(auxvalor)) {
            result=false;
        }
        return result;
    }
    
    public static int posicionOperador(String exp){
        int aux=-1;
        for (int i=0; i < exp.length(); i++) {
            char auxchar=exp.charAt(exp.length()-i-1);
            if (auxchar=='+'|| auxchar=='-' || auxchar=='/' || auxchar=='*' ) {
                aux=exp.length()-i-1;
                break;
            }
        }
        return aux;
    }
}
