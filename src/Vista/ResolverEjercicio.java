/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Controlador.EjercicioJpaController;
import Controlador.UsuarioHasEjercicioJpaController;
import Controlador.DimensionesJpaController;
import Controlador.PuntantrpJpaController;
import Controlador.InstrumentoJpaController;
import Controlador.PosturasJpaController;
import Controlador.RepuestasJpaController;
import Controlador.ComplementosPreguntaJpaController;
import Controlador.TemaJpaController;
import Controlador.TipoejercicioJpaController;

import Modelo.ComplementosPregunta;
import Modelo.Dimensiones;
import Modelo.Ejercicio;
import Modelo.Instrumento;
import Modelo.Posturas;
import Modelo.Puntantrp;
import Modelo.Repuestas;
import Modelo.UsuarioHasEjercicio;
import Util.ReportesController;


//import Modelo.UsuarioHasEjercicioPK;
import Util.UserInterfaceSuport;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author Eduardo
 */
public class ResolverEjercicio extends javax.swing.JDialog {

    /**
     * Creates new form v_Ejercicio2
     * @param parent
     * @param modal
     */
    int ejerc;
    InputStream aus;
    byte[] img;
    Image foto;
    File file;

    private Ejercicio modelo;
    private Repuestas respuestaseleccionada;
    private EjercicioJpaController controlejercicio;
    private UsuarioHasEjercicioJpaController controlusuarioejerc;
    private DimensionesJpaController controldimensiones;
    private InstrumentoJpaController controlinstrumento;
    private PosturasJpaController controlposturas;
    private PuntantrpJpaController controlpuntos;
    private ComplementosPreguntaJpaController controlcomplementos;
    private RepuestasJpaController controlrespuestas;
    private TemaJpaController controltema;
    private TipoejercicioJpaController controltipoejercicio;
    
    private UsuarioHasEjercicio modelousuario;
    private int nota=0;
    private boolean guardado=false;
    public boolean ejerActual = false;
    
    public ResolverEjercicio(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ponLaAyuda();
        this.setModal(true);//ESTO ES PARA LLAMARLA DE CUALQUIER FRAME SIN PASARLE PARAMETROS AL CONSTRUCTOR
        controlejercicio = new EjercicioJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlusuarioejerc=new UsuarioHasEjercicioJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controltema=new TemaJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controltipoejercicio=new TipoejercicioJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controldimensiones=new DimensionesJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlinstrumento=new InstrumentoJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlposturas= new PosturasJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlpuntos= new PuntantrpJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlcomplementos=new ComplementosPreguntaJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlrespuestas=new RepuestasJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        
        UserInterfaceSuport.centerComponent(this);
        ejerc=0;
        cargarEjercicio(ejerc);
        inabilitaLabelSelecciona(false);
        inabilitaLabelCompletar(false);
    }
    
    public void cargarEjercicio(int numero){
        modelo=controlejercicio.Mostrar(numero);
        if (modelo==null) {
            modelo=new Ejercicio();
            JOptionPane.showMessageDialog(null, "la dimension tomada no existe en la base de datos");
        }
        else{ 
            llenarElementos(modelo);
            respuestaseleccionada=modelo.getIdcomplementosRespuesta();
       }
    }
    
    private void llenarElementos(Ejercicio ejerc){
        String a1=ejerc.getComplementosPreguntaIdcomplementosPregunta().getIncA();
        String a2=ejerc.getComplementosPreguntaIdcomplementosPregunta().getIncB();
        String a3=ejerc.getComplementosPreguntaIdcomplementosPregunta().getIncC();
        String a4=ejerc.getComplementosPreguntaIdcomplementosPregunta().getIncD();
        String a5=ejerc.getComplementosPreguntaIdcomplementosPregunta().getIncE();
        txtApregunta.setText(ejerc.getPregunta());
        if (!ejerc.getIdtipoejercicio().getNombTipoejerc().equals("completar")) {
            panelcompletar.setVisible(false);
            panelselecciona.setVisible(true);
            txtpanelSA.setText(a1);
            txtpanelSB.setText(a2);
            txtpanelSC.setText(a3);
            txtpanelSD.setText(a4);
            txtpanelSE.setText(a5);
        }else{
            panelcompletar.setVisible(true);
            panelselecciona.setVisible(false);
            txtpanelCA.setText(a1);
            txtpanelCB.setText(a2);
            txtpanelCC.setText(a3);
            txtpanelCD.setText(a4);
            txtpanelCE.setText(a5);
            String tema1=ejerc.getIdtema().getDescripcion();
            llenaComboxTipo(tema1, jcbresp1);
            llenaComboxTipo(tema1, jcbresp2);
            llenaComboxTipo(tema1, jcbresp3);
            llenaComboxTipo(tema1, jcbresp4);
            llenaComboxTipo(tema1, jcbresp5);
        }
        if (ejerc.getFotoEjercicio()!= null) {
               foto = new ToolkitImage(new ByteArrayImageSource(ejerc.getFotoEjercicio()));
               foto=foto.getScaledInstance(lblimagen.getWidth(),lblimagen.getHeight(), Image.SCALE_DEFAULT);
               lblimagen.setIcon(new ImageIcon(foto));
        }else{
            lblimagen.setIcon(null);
        }
    }
    
    private void llenaComboxTipo(String tema1,JComboBox combo){
        DefaultComboBoxModel aux=(DefaultComboBoxModel) combo.getModel();
        aux.removeAllElements();
        aux.addElement("Seleccione");
        if ("Dimensiones".equals(tema1)) {
            List<Dimensiones> lista=controldimensiones.findDimensionesEntities();
            for (Dimensiones dimensiones : lista) {
                aux.addElement(dimensiones.getNombmedid());
            }           
        }else if("Posturas".equals(tema1)){
            List<Posturas> lista=controlposturas.findPosturasEntities();
            for (Posturas posturas : lista) {
                aux.addElement(posturas.getNombPostura());
            }
        }else if("Puntos".equals(tema1)){
            List<Puntantrp> lista= controlpuntos.findPuntantrpEntities();
            for (Puntantrp puntantrp : lista) {
                aux.addElement(puntantrp.getNombPto());
            }
        }else{
            List<Instrumento> lista=controlinstrumento.findInstrumentoEntities();
            for (Instrumento instrumento : lista) {
                aux.addElement(instrumento.getNombInstrumento());
            }
        }
        combo.setModel(aux);
    }
    
    private void siguienteEjercicio(){
        ejerActual= false;
        int aux=ejerc+1;
        List<Ejercicio> lista=controlejercicio.findEjercicioEntities();
       
        
        if (aux > lista.size()-1 ) {
            JOptionPane.showMessageDialog(null, "Todos los ejercicios han sido completados");
            this.dispose();
            aux = 0;
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
    
    private void inabilitaLabelSelecciona(boolean valor){
        jLabel13.setVisible(valor);
        jLabel14.setVisible(valor);
        jLabel15.setVisible(valor);
        jLabel16.setVisible(valor);
        jLabel17.setVisible(valor);       
    }
    
    private void inabilitaLabelCompletar(boolean valor){
        jLabel23.setVisible(valor);
        jLabel24.setVisible(valor);
        jLabel25.setVisible(valor);
        jLabel2.setVisible(valor);
        jLabel27.setVisible(valor);       
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
        btncomprobar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblimagen = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnsiguiente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtApregunta = new javax.swing.JTextArea();
        panelcontenedor = new javax.swing.JPanel();
        panelcompletar = new javax.swing.JPanel();
        jcbresp1 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jcbresp2 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jcbresp3 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jcbresp4 = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jcbresp5 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtpanelCA = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtpanelCB = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtpanelCC = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtpanelCD = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtpanelCE = new javax.swing.JTextField();
        panelselecciona = new javax.swing.JPanel();
        txtinc_A = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtpanelSA = new javax.swing.JTextField();
        txtinc_A1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtpanelSB = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtpanelSC = new javax.swing.JTextField();
        txtinc_A2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtinc_A3 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtpanelSD = new javax.swing.JTextField();
        txtinc_A4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtpanelSE = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnayud = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btncomprobar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btncomprobar.setText("Comprobar");
        btncomprobar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btncomprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("imagen"), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton2.setText("Exportar(pdf)");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnsiguiente.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        btnsiguiente.setText("Siguiente");
        btnsiguiente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });

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

        panelcontenedor.setLayout(new java.awt.CardLayout());

        jcbresp1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jcbresp2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jcbresp3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jcbresp4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jcbresp5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N

        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelCA.setEditable(false);
        txtpanelCA.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelCA.setText("jTextField1");
        jScrollPane7.setViewportView(txtpanelCA);

        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelCB.setEditable(false);
        txtpanelCB.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelCB.setText("jTextField1");
        jScrollPane8.setViewportView(txtpanelCB);

        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelCC.setEditable(false);
        txtpanelCC.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelCC.setText("jTextField1");
        jScrollPane9.setViewportView(txtpanelCC);

        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelCD.setEditable(false);
        txtpanelCD.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelCD.setText("jTextField1");
        jScrollPane10.setViewportView(txtpanelCD);

        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelCE.setEditable(false);
        txtpanelCE.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelCE.setText("jTextField1");
        jScrollPane11.setViewportView(txtpanelCE);

        javax.swing.GroupLayout panelcompletarLayout = new javax.swing.GroupLayout(panelcompletar);
        panelcompletar.setLayout(panelcompletarLayout);
        panelcompletarLayout.setHorizontalGroup(
            panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcompletarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcompletarLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jcbresp1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcompletarLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jcbresp2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcompletarLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jcbresp3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcompletarLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jcbresp4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcompletarLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jcbresp5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        panelcompletarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcbresp1, jcbresp2, jcbresp3, jcbresp4, jcbresp5});

        panelcompletarLayout.setVerticalGroup(
            panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcompletarLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbresp1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbresp2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbresp3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbresp4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(panelcompletarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbresp5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelcompletarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcbresp1, jcbresp2, jcbresp3, jcbresp4, jcbresp5});

        panelcontenedor.add(panelcompletar, "card3");

        panelselecciona.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtinc_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A.setToolTipText("respuesta correcta");
        txtinc_A.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_AKeyTyped(evt);
            }
        });
        panelselecciona.add(txtinc_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 34, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N
        panelselecciona.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 12, 20, 42));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelSA.setEditable(false);
        txtpanelSA.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelSA.setText("jTextField1");
        jScrollPane1.setViewportView(txtpanelSA);

        panelselecciona.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 12, 570, 41));

        txtinc_A1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A1.setToolTipText("respuesta correcta");
        txtinc_A1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_A1KeyTyped(evt);
            }
        });
        panelselecciona.add(txtinc_A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 34, 30));

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelSB.setEditable(false);
        txtpanelSB.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelSB.setText("jTextField1");
        jScrollPane3.setViewportView(txtpanelSB);

        panelselecciona.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 86, 570, 41));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N
        panelselecciona.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 85, 20, 42));

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelSC.setEditable(false);
        txtpanelSC.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelSC.setText("jTextField1");
        jScrollPane4.setViewportView(txtpanelSC);

        panelselecciona.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 155, 570, 41));

        txtinc_A2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A2.setToolTipText("respuesta correcta");
        txtinc_A2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_A2KeyTyped(evt);
            }
        });
        panelselecciona.add(txtinc_A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 163, 34, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N
        panelselecciona.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 154, 20, 42));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N
        panelselecciona.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 229, 20, 42));

        txtinc_A3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A3.setToolTipText("respuesta correcta");
        txtinc_A3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_A3KeyTyped(evt);
            }
        });
        panelselecciona.add(txtinc_A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 34, 30));

        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelSD.setEditable(false);
        txtpanelSD.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelSD.setText("jTextField1");
        jScrollPane5.setViewportView(txtpanelSD);

        panelselecciona.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 229, 570, 41));

        txtinc_A4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtinc_A4.setToolTipText("respuesta correcta");
        txtinc_A4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtinc_A4KeyTyped(evt);
            }
        });
        panelselecciona.add(txtinc_A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 312, 34, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b_drop.png"))); // NOI18N
        panelselecciona.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(628, 303, 20, 42));

        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtpanelSE.setEditable(false);
        txtpanelSE.setBackground(new java.awt.Color(255, 255, 255));
        txtpanelSE.setText("jTextField1");
        jScrollPane6.setViewportView(txtpanelSE);

        panelselecciona.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 304, 570, 41));

        panelcontenedor.add(panelselecciona, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(630, 630, 630)
                .addComponent(btncomprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnsiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(panelcontenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncomprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btncomprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobarActionPerformed
        // TODO add your handling code here
       if(!ejerActual){
        if (panelselecciona.isVisible()) {
            if (modelo.getIdtipoejercicio().getNombTipoejerc().equals("v_f")) {
                System.out.println("V_F");
                compruebaEjercicioVF();
                
            }
            else {
                System.out.println("es la X");
                if (txtinc_A.getText().isEmpty()&& txtinc_A1.getText().isEmpty()&&txtinc_A2.getText().isEmpty()
                        && txtinc_A3.getText().isEmpty()&&txtinc_A4.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Debe responder un inciso como mínimo");
                }else{
                    System.out.println("ok dar2");
                    compruebaEjercicioMarcaX();
                    
                }           
            }
        }
        else{
            compruebaPaneCompletar();         
        } 
        //poner una variable booleana para imprimir  la nota si se guardo en la bd
        if (guardado) {
            JOptionPane.showMessageDialog(null,"Usted tiene una nota de:  "+nota+" puntos");
            guardado=false;
            nota=0;
            modelo=null;
        } 
        
        
        
        }
        else{
            JOptionPane.showMessageDialog(null, "Ejercicio completado");
        }
    }//GEN-LAST:event_btncomprobarActionPerformed

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
        // TODO add your handling code here:
        UserInterfaceSuport.clearComponents(panelcompletar);
        UserInterfaceSuport.clearComponents(panelselecciona);
        siguienteEjercicio();
    }//GEN-LAST:event_btnsiguienteActionPerformed

    private void txtinc_A1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_A1KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_A1.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
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
    }//GEN-LAST:event_txtinc_A1KeyTyped

    private void txtinc_A2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_A2KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_A2.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
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
    }//GEN-LAST:event_txtinc_A2KeyTyped

    private void txtinc_A3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_A3KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_A3.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
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
    }//GEN-LAST:event_txtinc_A3KeyTyped

    private void txtinc_A4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinc_A4KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        System.out.println(evt.getKeyChar()+"\n");
        if (Character.isDigit(c)) {
            evt.consume();
        }else
            if(txtinc_A4.getText().length()>0){//poner mayor que cero porque el lenght empieza en cero
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
    }//GEN-LAST:event_txtinc_A4KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ReportesController a =new ReportesController();
        a.mostrarReporteEjercicio(modelo.getIdejercicio());
    }//GEN-LAST:event_jButton2ActionPerformed
    
//*********************************************************FUNCIONES
    //este metodo ya funciona pero hay que crearlo en el propio controlador del sistema
    private  void llenarRespuesta(){
        if (nota < 2) {
            nota=2;
        }
        modelousuario=new UsuarioHasEjercicio();
        modelousuario.setIdejercicios(modelo);
        modelousuario.setUsuarioIdusuario(AutentificacionInicialVisual.usuarioAutentificado);
        if (panelselecciona.isVisible()) {
            modelousuario.setRespA(txtinc_A.getText());
            modelousuario.setRespB(txtinc_A1.getText());
            modelousuario.setRespC(txtinc_A2.getText());
            modelousuario.setRespD(txtinc_A3.getText());
            modelousuario.setRespE(txtinc_A4.getText());
        }
        else{
           modelousuario.setRespA((String) jcbresp1.getSelectedItem());
           modelousuario.setRespB((String) jcbresp2.getSelectedItem());
           modelousuario.setRespC((String) jcbresp3.getSelectedItem());
           modelousuario.setRespD((String) jcbresp4.getSelectedItem());
           modelousuario.setRespE((String) jcbresp5.getSelectedItem()); 
        }
        modelousuario.setNota(nota);
        try {
            controlusuarioejerc.create(modelousuario);
            JOptionPane.showMessageDialog(null,"Las respuesta fueron guardadas en la base de datos");
            ejerActual = true;
            guardado=true;
        } catch (Exception ex) {
            Logger.getLogger(ResolverEjercicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void compruebaPaneCompletar(){
        boolean comprueba=validaRespuestaCompletar();
        if (!comprueba) {
            if (jcbresp1.getSelectedItem().equals(respuestaseleccionada.getRespuestaincA())) {
                nota++;
            } else {
                jLabel23.setVisible(true);
            }
            if (jcbresp2.getSelectedItem().equals(respuestaseleccionada.getRespuestaincB())) {
                nota++;
            } else {
                jLabel24.setVisible(true);
            }
            if (jcbresp3.getSelectedItem().equals(respuestaseleccionada.getRespuestaincC())) {
                nota++;
            } else {
                jLabel25.setVisible(true);
            }
            if (jcbresp4.getSelectedItem().equals(respuestaseleccionada.getRespuestaincD())) {
                nota++;
            } else {
                jLabel2.setVisible(true);
            }
            if (jcbresp5.getSelectedItem().equals(respuestaseleccionada.getRespuestaincE())) {
                nota++;
            } else {
                jLabel27.setVisible(true);
            }
            llenarRespuesta();
        }else{
            JOptionPane.showMessageDialog(null,"No puede dejar elementos sin seleccionar");
        }
    }
    
    private boolean validaRespuestaCompletar(){
        boolean result=false;
        Component [] components = panelcompletar.getComponents();
        for (Component component : components) {
            if (component instanceof JComboBox) {
                JComboBox combo=((JComboBox)component);
		if (combo.getSelectedIndex()==0) {
                    result=true;
                }
            }
        }
        return result;
    }
    
    private void compruebaEjercicioVF(){
        //Image correcto=getToolkit().getImage(getClass().getResource("/imagenes/rigth.png"));
        //Image incorrecto=getToolkit().getImage(getClass().getResource("/imagenes/b_drop.png"));
        boolean vof=validaRespuesta();
        if (!vof) {
            if (txtinc_A.getText().equals(respuestaseleccionada.getRespuestaincA())) {
                nota++;
                //jLabel13.setVisible(true);//setIcon(new ImageIcon(correcto));
            }else{
                jLabel13.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_A1.getText().equals(respuestaseleccionada.getRespuestaincB())) {
                nota++;
                //jLabel14.setVisible(true);//setIcon(new ImageIcon(correcto));
            }else{
                jLabel14.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_A2.getText().equals(respuestaseleccionada.getRespuestaincC())) {
                nota++;
                //jLabel15.setVisible(true);//setIcon(new ImageIcon(correcto));
            }else{
                jLabel15.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_A3.getText().equals(respuestaseleccionada.getRespuestaincD())) {
                nota++;
                //jLabel16.setVisible(true);//setIcon(new ImageIcon(correcto));
            }else{
                jLabel16.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
            }
            if (txtinc_A4.getText().equals(respuestaseleccionada.getRespuestaincE())) {
                nota++;
                //jLabel17.setVisible(true);//setIcon(new ImageIcon(correcto));
            }else{
                jLabel17.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
            }
            System.out.println(" sali del V_F");
            llenarRespuesta();
        }
    }
    
    private void compruebaEjercicioMarcaX(){
        //Image correcto=getToolkit().getImage(getClass().getResource("/imagenes/rigth.png"));
        //Image incorrecto=getToolkit().getImage(getClass().getResource("/imagenes/b_drop.png"));
        if (txtinc_A.getText().equals(respuestaseleccionada.getRespuestaincA())) {
            nota++;
            //jLabel13.setIcon(new ImageIcon(correcto));
        }else{
            jLabel13.setVisible(true);//setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_A1.getText().equals(respuestaseleccionada.getRespuestaincB())) {
            nota++;
            //jLabel14.setIcon(new ImageIcon(correcto));
        }else{
            jLabel14.setVisible(true);//setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_A2.getText().equals(respuestaseleccionada.getRespuestaincC())) {
            nota++;
            //jLabel15.setIcon(new ImageIcon(correcto));
        }else{
            jLabel15.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_A3.getText().equals(respuestaseleccionada.getRespuestaincD())) {
            nota++;
            //jLabel16.setIcon(new ImageIcon(correcto));
        }else{
            jLabel16.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
        }
        if (txtinc_A4.getText().equals(respuestaseleccionada.getRespuestaincE())) {
            nota++;
            //jLabel17.setIcon(new ImageIcon(correcto));
        }else{
             jLabel17.setVisible(true);//.setIcon(new ImageIcon(incorrecto));
        }
        System.out.println("Sali del marcaX");
        llenarRespuesta();
    }
    
    private boolean validaRespuesta(){
        boolean respuesta=false;
        if ("".equals(txtinc_A.getText()) || "".equals(txtinc_A1.getText()) || "".equals(txtinc_A2.getText()) || "".equals(txtinc_A3.getText()) || "".equals(txtinc_A4.getText())) {
            JOptionPane.showMessageDialog(null,"No pueden quedar incisos sin responder");
            respuesta=true;
        }
        return respuesta;
    }
    
    private void habilitaComBox(boolean valor){
        /*jComboBox1.setVisible(valor);
        jComboBox2.setVisible(valor);
        jComboBox3.setVisible(valor);
        jComboBox4.setVisible(valor);
        jComboBox5.setVisible(valor);*/
    }
    
    private void habilitaTextF(boolean valor){
        /*txtinc_A.setVisible(valor);
        txtinc_B.setVisible(valor);
        txtinc_C.setVisible(valor);
        txtinc_D.setVisible(valor);
        txtinc_E.setVisible(valor);*/
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
                new ResolverEjercicio(null,true).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnayud;
    private javax.swing.JButton btncomprobar;
    private javax.swing.JButton btnsiguiente;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JComboBox jcbresp1;
    private javax.swing.JComboBox jcbresp2;
    private javax.swing.JComboBox jcbresp3;
    private javax.swing.JComboBox jcbresp4;
    private javax.swing.JComboBox jcbresp5;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JPanel panelcompletar;
    private javax.swing.JPanel panelcontenedor;
    private javax.swing.JPanel panelselecciona;
    private javax.swing.JTextArea txtApregunta;
    private javax.swing.JTextField txtinc_A;
    private javax.swing.JTextField txtinc_A1;
    private javax.swing.JTextField txtinc_A2;
    private javax.swing.JTextField txtinc_A3;
    private javax.swing.JTextField txtinc_A4;
    private javax.swing.JTextField txtpanelCA;
    private javax.swing.JTextField txtpanelCB;
    private javax.swing.JTextField txtpanelCC;
    private javax.swing.JTextField txtpanelCD;
    private javax.swing.JTextField txtpanelCE;
    private javax.swing.JTextField txtpanelSA;
    private javax.swing.JTextField txtpanelSB;
    private javax.swing.JTextField txtpanelSC;
    private javax.swing.JTextField txtpanelSD;
    private javax.swing.JTextField txtpanelSE;
    // End of variables declaration//GEN-END:variables
}
