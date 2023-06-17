/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo
 */
public class Imagen extends javax.swing.JPanel /*implements MouseListener*/{
    
private ImageIcon Img;
 int posicionX;
 int posicionY;
 int count=0,posX,posY;


public Imagen() {
this.setSize(300, 400); //se selecciona el tamaño del panel
}
public Imagen(String src){
     Img = new ImageIcon(src);
}
public Imagen(Image imagen) {
   Img=new ImageIcon(imagen);
}
//Se crea un método cuyo parámetro debe ser un objeto Graphics
 
@Override
public void paint(Graphics g) {
Dimension height = getSize();
 
//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
//ImageIcon Img = new ImageIcon(getClass().getResource("/imagenes/11.jpg"));//esto trabaja para la interface estudiante2 del mismo paquete
//pasandole el path tambien funciona;
//ImageIcon Img = new ImageIcon("D:\\cursos\\fotos\\20160419_181333.jpg"/*getClass().getResource("/imagenes/1.jpg")*/); 
//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel /*("D:\\cursos\\fotos\\20160419_181333.jpg");*/
 
g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

/////*****************
//grafico.setColor(Color.red);
//grafico.fillOval(posicionX, posicionY, 7,7);
///*************************** 

//*******************************************
g.setColor(Color.red);
        
        //g.fillOval(0, 0, 5,5);
        //****************************************************
        //count++;
        if (count==1) {
            posX=posicionX; posY=posicionY;
            g.setColor(Color.BLACK);
            g.fillOval(posicionX, posicionY, 3,3);
            
        }
        else if (count==2) {
            /*
            //g.fillOval(posicionX, posicionY, 7,7);
            g.drawLine(posX, posY, posicionX, posicionY);
            int distance=(int)Math.sqrt((Math.pow((posicionX-posX), 2))+(Math.pow((posicionY-posY), 2)));
            //g.drawString(String.valueOf(distance),(posicionX+posX)/2,(posicionY+posY)/2);
            g.setColor(Color.red);
            //g.fillOval(posicionX, posicionY, 10,10);
            */
    //*****************************************************
     double ang=0.0, angSep=0.0;
     double tx,ty;
     int dist=0;
     Point punto1=null,punto2=null;

     //defino dos puntos extremos
     punto1=new Point(posX,posY);
     punto2=new Point(posicionX,posicionY);

     //tamaño de la punta de la flecha
     dist=15;

     /* (la coordenadas de la ventana es al revez)
         calculo de la variacion de "x" y "y" para hallar el angulo
      **/

     ty=-(punto1.y-punto2.y)*1.0;
     tx=(punto1.x-punto2.x)*1.0;
     //angulo
     ang=Math.atan (ty/tx);

     if(tx<0)
     {// si tx es negativo aumentar 180 grados
        ang+=Math.PI;
     }

     //puntos de control para la punta
     //p1 y p2 son los puntos de salida
     Point p1=new Point(),p2=new Point(),punto=punto2;

     //angulo de separacion
     angSep=25.0;
    
     p1.x=(int)(punto.x+dist*Math.cos (ang-Math.toRadians (angSep)));
     p1.y=(int)(punto.y-dist*Math.sin (ang-Math.toRadians (angSep)));
     p2.x=(int)(punto.x+dist*Math.cos (ang+Math.toRadians (angSep)));
     p2.y=(int)(punto.y-dist*Math.sin (ang+Math.toRadians (angSep)));

     Graphics2D g2D=(Graphics2D)g;

     //dale color a la linea
     g.setColor (Color.BLACK);
     // grosor de la linea
     g2D.setStroke (new BasicStroke(3f));
     //dibuja la linea de extremo a extremo
     g.drawLine (punto1.x,punto1.y,punto.x,punto.y);
     //g.drawLine(posX, posY, posicionX, posicionY);
     //dibujar la punta
     g.drawLine (p1.x,p1.y,punto.x,punto.y);
     g.drawLine (p2.x,p2.y,punto.x,punto.y);
    //*****************************************************
     
            count=0;
        }
//********************************************
setOpaque(false);
super.paintComponent(g);
}

}
