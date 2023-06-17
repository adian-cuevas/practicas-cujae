/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.EjercicioJpaController;
import Controlador.UsuarioHasEjercicioJpaController;
import Modelo.ComplementosPregunta;
import Modelo.Ejercicio;
import Modelo.Repuestas;
import Modelo.UsuarioHasEjercicio;
//import Modelo.UsuarioHasEjercicioPK;
import Util.UserInterfaceSuport;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author Eduardo
 */
public class MostrarEjercicio extends javax.swing.JDialog {

    /**
     * Creates new form v_Ejercicio2
     * @param parent
     * @param modal
     */
    int ejerc;
    InputStream aus;
    byte[] img;
    Image foto;
    File aux; 
    private Ejercicio modelo;
    //private UsuarioHasEjercicioPK usuejercpk;
    private Repuestas respuestaseleccionada;
    private EjercicioJpaController controlejercicio;
    private UsuarioHasEjercicioJpaController controlusuarioejerc;
    private UsuarioHasEjercicio usuarioejercselecc;
    private int nota=0;
    
    public MostrarEjercicio() {
        //super(parent, modal);
        initComponents();
        ponLaAyuda();
        this.setModal(true);//ESTO ES PARA LLAMARLA DE CUALQUIER FRAME SIN PASARLE PARAMETROS AL CONSTRUCTOR
        controlejercicio = new EjercicioJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlusuarioejerc=new UsuarioHasEjercicioJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        UserInterfaceSuport.centerComponent(this);
        ejerc=ejercicioAleatorio(controlejercicio.getEjercicioCount()-1);
        cargarEjercicio(ejerc);
        inabilitaLabels(false);
        
    }
    
    public void cargarEjercicio(int numero){
        modelo=controlejercicio.Mostrar(numero);
        if (modelo==null) {
            modelo=new Ejercicio();
            JOptionPane.showMessageDialog(null, "la dimension tomada no existe en la base de datos");
        }
        else{
            jLabel2.setText(modelo.getComplementosPreguntaIdcomplementosPregunta().getIncA());
            txtApregunta.setText(modelo.getPregunta());
            jLabel4.setText(modelo.getComplementosPreguntaIdcomplementosPregunta().getIncB());
            jLabel5.setText(modelo.getComplementosPreguntaIdcomplementosPregunta().getIncC());
            jLabel6.setText(modelo.getComplementosPreguntaIdcomplementosPregunta().getIncD());
            jLabel7.setText(modelo.getComplementosPreguntaIdcomplementosPregunta().getIncE());
            if (modelo.getFotoEjercicio()!=null) {
               foto = new ToolkitImage(new ByteArrayImageSource(modelo.getFotoEjercicio()));
               foto=foto.getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(), Image.SCALE_DEFAULT);
               jLabel1.setIcon(new ImageIcon(foto)); 
            }else{
               jLabel1.setIcon(null);
            }
            
            if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f")) {
                habilitaComBox(false);
           }
           else if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")) {
                habilitaComBox(false);
           }
           else{
                 habilitaTextF(false);
             }
           }
           respuestaseleccionada=modelo.getIdcomplementosRespuesta();
    }
    
    private void siguienteEjercicio(){
        int aux=ejerc+1;
        List<Ejercicio> lista=controlejercicio.findEjercicioEntities();
        if (aux > lista.size()-1 ) {
            aux=0;
        }
        cargarEjercicio(aux);
        ejerc = aux;
    }
    
    public int ejercicioAleatorio(int tamanolista){
        Random rand = new Random();      
        //int n =10;//aqui va el tamaño de la lista menos uno para que no se vaya de rango
        int i = rand.nextInt(tamanolista+1);
        return i;
    }
    
    private void inabilitaLabels(boolean valor){
        jLabel13.setVisible(valor);
        jLabel14.setVisible(valor);
        jLabel15.setVisible(valor);
        jLabel16.setVisible(valor);
        jLabel17.setVisible(valor);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtinc_A = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtinc_B = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtinc_C = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtinc_D = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtinc_E = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtApregunta = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnayud = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txtinc_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A.setToolTipText("respuesta correcta");
        txtinc_A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtinc_AKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_AKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton1.setText("Comprobar");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtinc_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_B.setToolTipText("respuesta correcta");
        txtinc_B.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_BKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("jLabel2");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtinc_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_C.setToolTipText("respuesta correcta");
        txtinc_C.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_CKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("jLabel2");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtinc_D.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_D.setToolTipText("respuesta correcta");
        txtinc_D.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_DKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("jLabel2");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtinc_E.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_E.setToolTipText("respuesta correcta");
        txtinc_E.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_EKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setText("jLabel2");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("imagen"), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton2.setText("Exportar(pdf)");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton3.setText("Siguiente");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jScrollPane2.setBackground(new Color(0,0,0,0));
        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);

        txtApregunta.setBackground(new Color(0,0,0,0));
        txtApregunta.setColumns(20);
        txtApregunta.setFont(new java.awt.Font("Modern No. 20", 1, 18)); // NOI18N
        txtApregunta.setLineWrap(true);
        txtApregunta.setRows(5);
        txtApregunta.setWrapStyleWord(true);
        txtApregunta.setOpaque(false);
        jScrollPane2.setViewportView(txtApregunta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtinc_A, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtinc_B, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtinc_C, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtinc_D, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtinc_E, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(633, 633, 633)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtinc_A, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(txtinc_B, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtinc_C, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtinc_D, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(txtinc_E, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnayud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ayuda.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnayud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnayud, javax.swing.GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtinc_AKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_AKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_A.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
                evt.consume();
            }
            else if( ((c=='f') || (c=='F') || (c=='v') || (c=='V')) && modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f") ){
                if ((c=='f') || (c=='F')) {
                    evt.setKeyChar('F');
                }
                else{
                    evt.setKeyChar('V');
                }
            }
            else if(((c=='x') || (c=='X'))&& modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")){//falta comprobar el tipo de ejercicio
                evt.setKeyChar('X');
            }else{
                evt.consume();
            }
        /*else if(!((Character.isLetter(c)|| Character.isDigit(c)|| (c == KeyEvent.VK_BACK_SPACE)
					|| (c == KeyEvent.VK_DELETE)|| c == KeyEvent.VK_SLASH		
					|| c == KeyEvent.VK_BACK_SLASH || (Character.isWhitespace(c))		
					))){evt.consume();
        e.setKeyChar(Character.toUpperCase(c));ver
        }*/
        
    }//GEN-LAST:event_txtinc_AKeyTyped

    private void txtinc_BKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_BKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_B.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
                evt.consume();
            }
            else if( ((c=='f') || (c=='F') || (c=='v') || (c=='V')) && modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f") ){
                if ((c=='f') || (c=='F')) {
                    evt.setKeyChar('F');
                }
                else{
                    evt.setKeyChar('V');
                }
            }
            else if(((c=='x') || (c=='X'))&& modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")){//falta comprobar el tipo de ejercicio
                evt.setKeyChar('X');
            }else{
                evt.consume();
            }
    }//GEN-LAST:event_txtinc_BKeyTyped

    private void txtinc_CKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_CKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_C.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
                evt.consume();
            }
            else if( ((c=='f') || (c=='F') || (c=='v') || (c=='V')) && modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f") ){
                if ((c=='f') || (c=='F')) {
                    evt.setKeyChar('F');
                }
                else{
                    evt.setKeyChar('V');
                };
            }
            else if(((c=='x') || (c=='X'))&& modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")){//falta comprobar el tipo de ejercicio
                evt.setKeyChar('X');
            }else{
                evt.consume();
            }
    }//GEN-LAST:event_txtinc_CKeyTyped

    private void txtinc_DKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_DKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_D.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
                evt.consume();
            }
            else if( ((c=='f') || (c=='F') || (c=='v') || (c=='V')) && modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f") ){
                if ((c=='f') || (c=='F')) {
                    evt.setKeyChar('F');
                }
                else{
                    evt.setKeyChar('V');
                }
            }
            else if(((c=='x') || (c=='X'))&& modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")){//falta comprobar el tipo de ejercicio
                evt.setKeyChar('X');
            }else{
                evt.consume();
            }
    }//GEN-LAST:event_txtinc_DKeyTyped

    private void txtinc_EKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_EKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_E.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
                evt.consume();
            }
            else if( ((c=='f') || (c=='F') || (c=='v') || (c=='V')) && modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f") ){
                if ((c=='f') || (c=='F')) {
                    evt.setKeyChar('F');
                }
                else{
                    evt.setKeyChar('V');
                }
            }
            else if(((c=='x') || (c=='X'))&& modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")){//falta comprobar el tipo de ejercicio
                evt.setKeyChar('X');
            }else{
                evt.consume();
            }
    }//GEN-LAST:event_txtinc_EKeyTyped

    private void txtinc_AKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_AKeyReleased
        // TODO add your handling code here:
        //char[] arreglo=txtinc_A.getText()
//        System.out.println("estoy en ppp1  "+txtinc_A.getText().length());
//        if (txtinc_A.getText().length()==0) {
//            String respuesta=txtinc_A.getText();
//            System.out.println("estoy en pppp  "+ "el valor del texfield es:  "+txtinc_A.getText());
//            if (respuesta.equals(respuestaseleccionada.getRespuestaincA())) {           
//                Image foto=getToolkit().getImage(getClass().getResource("/imagenes/Thumbs_up.png"));
//                jLabel13.setIcon(new ImageIcon(foto));
//            }else{
//                Image foto=getToolkit().getImage(getClass().getResource("/imagenes/Thumbs_down.png"));
//                jLabel13.setIcon(new ImageIcon(foto));
//            }
//        }
    }//GEN-LAST:event_txtinc_AKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here   
        if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f")) {
            compruebaEjercicioVF();
        }
        else if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("marca_x")) {
            compruebaEjercicioMarcaX();
        }
        else if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("completa")) {
            
        }
        JOptionPane.showMessageDialog(null,"Usted tiene una nota de:  "+nota+" puntos");
        nota=0;
        UserInterfaceSuport.clearComponents(jPanel1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        siguienteEjercicio();
    }//GEN-LAST:event_jButton3ActionPerformed
    
//*********************************************************FUNCIONES
    //este metodo ya funciona pero hay que crearlo en el propio controlador del sistema
    private  void llenarRespuesta(){
        /*
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Laboratorio_VirtualPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        //usuejercpk=new UsuarioHasEjercicioPK(AutentificacionInicialVisual.usuarioAutentificado.getIdusuario(),modelo.getIdejercicio());
        usuarioejercselecc=new UsuarioHasEjercicio();
        usuarioejercselecc.setUsuarioHasEjercicioPK(usuejercpk);
        //usuarioejercselecc.setRespA(txtinc_A.getText());
        usuarioejercselecc.setRespA(txtinc_A.getText());
        usuarioejercselecc.setRespB(txtinc_B.getText());
        usuarioejercselecc.setRespC(txtinc_C.getText());
        usuarioejercselecc.setRespD(txtinc_D.getText());
        usuarioejercselecc.setRespE(txtinc_E.getText());
               
        try {
            entitymanager.persist(usuarioejercselecc);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();
        } catch (Exception e) {//me da error porque el usuario ya respondio el ejercicio
            JOptionPane.showMessageDialog(null,"El usuario ya le dio respuesta anteriormente a este ejercicio");
        }*/
        
        
//        System.out.println("usuario :"+usuejercpk.getUsuarioIdusuario());
//        System.out.println("ejercicio :"+usuarioejercselecc.getUsuarioHasEjercicioPK().getEjercicioIdejercicio());
//        try {    
//            controlusuarioejerc.create(usuarioejercselecc);
//        } catch (Exception ex) {
//            Logger.getLogger(MostrarEjercicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    private void compruebaEjercicioVF(){
        Image correcto=getToolkit().getImage(getClass().getResource("/imagenes/rigth.png"));
        Image incorrecto=getToolkit().getImage(getClass().getResource("/imagenes/b_drop.png"));
        boolean vof=validaRespuesta();
        if (!vof) {
            if (txtinc_A.getText().equals(respuestaseleccionada.getRespuestaincA())) {
                nota++;
                jLabel13.setIcon(new ImageIcon(correcto));
            }else{
                jLabel13.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_B.getText().equals(respuestaseleccionada.getRespuestaincB())) {
                nota++;
                jLabel14.setIcon(new ImageIcon(correcto));
            }else{
                jLabel14.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_C.getText().equals(respuestaseleccionada.getRespuestaincC())) {
                nota++;
                jLabel15.setIcon(new ImageIcon(correcto));
            }else{
                jLabel15.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_D.getText().equals(respuestaseleccionada.getRespuestaincD())) {
                nota++;
                jLabel16.setIcon(new ImageIcon(correcto));
            }else{
                jLabel16.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_E.getText().equals(respuestaseleccionada.getRespuestaincE())) {
                nota++;
                jLabel17.setIcon(new ImageIcon(correcto));
            }else{
                jLabel17.setIcon(new ImageIcon(incorrecto));
            }
            llenarRespuesta();
        }
    }
    
    private void compruebaEjercicioMarcaX(){
        Image correcto=getToolkit().getImage(getClass().getResource("/imagenes/rigth.png"));
        Image incorrecto=getToolkit().getImage(getClass().getResource("/imagenes/b_drop.png"));
        if (txtinc_A.getText().equals(respuestaseleccionada.getRespuestaincA())) {
            nota++;
            jLabel13.setIcon(new ImageIcon(correcto));
        }else{
            jLabel13.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_B.getText().equals(respuestaseleccionada.getRespuestaincB())) {
            nota++;
            jLabel14.setIcon(new ImageIcon(correcto));
        }else{
            jLabel14.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_C.getText().equals(respuestaseleccionada.getRespuestaincC())) {
            nota++;
            jLabel15.setIcon(new ImageIcon(correcto));
        }else{
            jLabel15.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_D.getText().equals(respuestaseleccionada.getRespuestaincD())) {
            nota++;
            jLabel16.setIcon(new ImageIcon(correcto));
        }else{
            jLabel16.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_E.getText().equals(respuestaseleccionada.getRespuestaincE())) {
            nota++;
            jLabel17.setIcon(new ImageIcon(correcto));
        }else{
             jLabel17.setIcon(new ImageIcon(incorrecto));
        }
        llenarRespuesta();
    }
    
    private boolean validaRespuesta(){
        boolean respuesta=false;
        if ("".equals(txtinc_A.getText()) || "".equals(txtinc_B.getText()) || "".equals(txtinc_C.getText()) || "".equals(txtinc_D.getText()) || "".equals(txtinc_E.getText())) {
            JOptionPane.showMessageDialog(null,"No pueden quedar incisos sin responder");
            respuesta=true;
        }
        return respuesta;
    }
    
    private void habilitaComBox(boolean valor){
        jComboBox1.setVisible(valor);
        jComboBox2.setVisible(valor);
        jComboBox3.setVisible(valor);
        jComboBox4.setVisible(valor);
        jComboBox5.setVisible(valor);
    }
    
    private void habilitaTextF(boolean valor){
        txtinc_A.setVisible(valor);
        txtinc_B.setVisible(valor);
        txtinc_C.setVisible(valor);
        txtinc_D.setVisible(valor);
        txtinc_E.setVisible(valor);
    }
    
    private void inicializaVentana(){
        
    }
    
   private void ponLaAyuda() {
	try {
	     // Carga el fichero de ayuda
		File fichero = new File("./help/help_set.hs");
		URL hsURL = fichero.toURI().toURL();

		// Crea el HelpSet y el HelpBroker
		HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
		HelpBroker hb = helpset.createHelpBroker();

		// Pone ayuda a item de menu al pulsarlo y a F1 en ventana
		// principal y secundaria.
		//hb.enableHelpOnButton(menuayuda, "aplicacion", helpset);
                hb.enableHelpOnButton(btnayud, "aplicacion", helpset);
                //hb.enableHelpOnButton(jMenuItemAyuda, "ventana_principal", helpset);
		hb.enableHelpKey(this.getContentPane(), "aplicacion",
					helpset);
//		hb.enableHelpKey(secundaria.getContentPane(), "ventana_secundaria",
//					helpset);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MostrarEjercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarEjercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarEjercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarEjercicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarEjercicio().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnayud;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtApregunta;
    private javax.swing.JTextField txtinc_A;
    private javax.swing.JTextField txtinc_B;
    private javax.swing.JTextField txtinc_C;
    private javax.swing.JTextField txtinc_D;
    private javax.swing.JTextField txtinc_E;
    // End of variables declaration//GEN-END:variables
}
