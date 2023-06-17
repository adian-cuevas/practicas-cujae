/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alfonso
 */
public class ReportesController {

    private ReportesController cont = null;
    private Connection myConnection = null;

    public ReportesController() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull", "root", "");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void mostrarReportePostura(Date fechaInicio, Date fechaFin, String numInspector) {
         try {

            
//            JasperReport reporte = JasperCompileManager.compileReport("Logs.jrxml");
            JasperPrint p = JasperFillManager.fillReport("Reportes//posturas.jasper", null, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de Registro");
            view.setAlwaysOnTop(true);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public  void mostrarReporteEjercicio(int id) {
         try {

            Map parametros = new HashMap();
            parametros.put("id", id); 
          
            JasperPrint p = JasperFillManager.fillReport("Reportes//ejercicio.jasper", parametros, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de Ejercicio");
            view.setAlwaysOnTop(true);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public  void mostrarReporteCasos() {
         try {
            JasperPrint p = JasperFillManager.fillReport("Reportes//experimentacion.jasper", null, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de experimentacion");
            view.setAlwaysOnTop(true);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public  void mostrarReporteEjercicioAutoevaluativos() {
         try {

             
          
            JasperPrint p = JasperFillManager.fillReport("Reportes//ejercicios_autoevaluativos.jasper", null, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de experimentacion");
            view.setAlwaysOnTop(true);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public  void mostrarReporteEvaluacionProfesor(int id, int idexperimentacion) {
         try {

            Map parametros = new HashMap();
            parametros.put("id", id); 
            parametros.put("id_resp_experimentacion", idexperimentacion);
          
            JasperPrint p = JasperFillManager.fillReport("Reportes//evaluacion_del_profesor.jasper", parametros, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de Evaluación del profesor");
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public  void mostrarReporteInformeReal(int idexperimentacion) {
         try {
            Map parametros = new HashMap();
            parametros.put("id_caso_estudio", idexperimentacion);
            JasperPrint p = JasperFillManager.fillReport("Reportes//informe_correcto.jasper", parametros, myConnection);
            JasperViewer view = new JasperViewer(p,false); 
            view.setTitle("Reporte de Informe real");
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
    
//    public void mostrarReporteExperimentacion(String a, String b, int c, List<String> d) {
//         try {
//             
//            Map parametros = new HashMap();
//            parametros.put("dimensionR", a); 
//            parametros.put("dimensionesA", b); 
//            parametros.put("tamano", c); 
//            
//            ListaStringDataSource dataSource = new ListaStringDataSource(d);
//            
//            JasperPrint p = JasperFillManager.fillReport("Reportes//report1.jasper", parametros, dataSource);
//            JasperViewer view = new JasperViewer(p,false); 
//            view.setTitle("Reporte de Experimentacion");
//            view.setAlwaysOnTop(true);
//            view.setVisible(true);
//        } catch (JRException ex) {
//            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }    
    
    
    
    
    
}
