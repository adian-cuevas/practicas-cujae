/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.PosicionesDimensionesJpaController;
import Controlador.PosturasJpaController;
import Controlador.SujetoJpaController;
import Controlador.SujetoPosicionesJpaController;
import Controlador.ExperimentacionJpaController;
import Controlador.InstrumentoJpaController;
import Controlador.ErrorJpaController;
import Controlador.RespuestaExperimentacionJpaController;
import Controlador.DimensionesJpaController;
import Controlador.FormulaDrJpaController;

import Modelo.PosicionesDimensiones;
import Modelo.DimensionesRelevantes;
import Modelo.Sujeto;
import Modelo.SujetoPosiciones;
import Modelo.Experimentacion;
import Modelo.Instrumento;
import Modelo.Usuario;
import Modelo.Error;
import Modelo.Posicionespuestotrabajo;
import Modelo.RespuestaExperimentacion;
import Modelo.Dimensiones;
//import Modelo.*;

import Util.Control;
import Util.ReportesController;

import Util.UserInterfaceSuport;
import java.awt.Color;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.persistence.NoResultException;
import javax.swing.DefaultCellEditor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import sun.awt.image.ByteArrayImageSource;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author Eduardo
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    //***********************************************************estudiante1 inicia
    public static int muestra = 0;
    static boolean lbloav = false;
    static boolean lblfce = false;
    static boolean lblmi = false;
    static boolean lblp = false;
    static boolean lblsbr = false;
    static boolean lbltpc = false;
    private boolean clickpuestotrabajo = false;
    public static boolean autenticado = false;
    //esta son para las dimensiones antropometricas probar con otra forma 
    public static boolean estatura = false;
    public static boolean alturadelosojos = false;
    public static boolean alturadelcodo = false;
    public static boolean diametrobiacromial = false;
    public static boolean alturasubescapular = false;
    public static boolean alturadelmuslo = false;
    public static boolean alturapoplitea = false;
    public static boolean alcancemaximodelbrazo = false;
    public static boolean alcanceminimodelbrazo = false;
    public static boolean alturadelcodosentado = false;
    public static boolean alcancelateraldelbrazo = false;
    public static boolean alturadelosojossentado = false;
    public static boolean alturadelarodilla = false;
    public static boolean anchodelpie = false;
    public static boolean longitudsacropoplitea = false;
    public static boolean alturasacrorotula = false;
    public static boolean longituddelmuslo = false;
    public static boolean anchodelacaderasentado = false;
    public static boolean anchodecodoacodo = false;
    public static boolean alturailiocrestal = false;
    public static boolean longituddelpie = false;

    //************************************************************************
    int experim;
    public static Usuario usuarioAutentificado = null;
    private Sujeto sujetomodelo;
    private Experimentacion modeloexp;
    private Dimensiones modelodimensiones;
    private Instrumento modeloInstrumento;
    private Sujeto sujetoseleccionado = null;
    private Error auxerrorCE;
    private Error auxerrorExp;
    private Error auxerrorPA;
    private Error auxerrorPu;
    private Error auxerrorA;

    private SujetoJpaController controlsujeto;
    private FormulaDrJpaController controlformula;
    private DimensionesJpaController controldimensiones;
    private InstrumentoJpaController controlinstrumento;
    private ExperimentacionJpaController controlexperimentacion;
    private PosturasJpaController controlpostura;
    private PosicionesDimensionesJpaController controlposicionesdimensiones;
    private SujetoPosicionesJpaController controlsujetoposiciones;
    private SujetoPosiciones sujetoposiciones;
    private PosicionesDimensiones posiciondimensionseleccionado = null;
    private ErrorJpaController controlerror;
    private RespuestaExperimentacionJpaController controlrespt;

    public static LinkedList<String> listadimensionesantropometricas;
    public static LinkedList<String> listadimensionesdiseno;
    private ArrayList<String> listaexpresiondiseno;
    private ArrayList<Error> errores;
    private ArrayList<String> ppt;
    private List<PosicionesDimensiones> dimensionescoordenadaslist;
    private ArrayList listaarray = new ArrayList();

    //***********************************************************************
    public static boolean dr1 = false;
    public static boolean dr2 = false;
    public static boolean dr3 = false;
    public static boolean dr4 = false;
    public static boolean dr5 = false;
    public static boolean dr6 = false;
    public static boolean dr7 = false;
    public static boolean dr8 = false;
    public static boolean dr9 = false;
    public static boolean dr10 = false;
    //*************************************************************************estudiante1 cierra

    //************************************************************************calculo inicia
    private int dim_antropo = 0;
    private int oper_aritm = 0;
    private int valor_antrop1 = 0;
    private int valor_antrop2 = 0;

    private DefaultComboBoxModel modelodimensionesdiseno;
    private DefaultComboBoxModel modelposturas;
    private DefaultListModel modellist;
    private DefaultComboBoxModel modelmuestra;
    private DefaultListModel listamodelcalculo;
    private DefaultTableModel modeltabla;
    private DefaultTableModel modeltablamuestra;

    private String potencia = "^2";
    private String cateto = "cat ady * tan30";
    private String division = "÷";
    private String holgura = "holgura";
    private String duplo = "2";
    private String raiz = "rz";
    private String aparentisis = "(";
    private double holgc = 0.25;
    private double espc = 0.2;
    private String cparenticis = ")";
    private String multiplicacion;
    private String suma;
    private String resta;
    private String expresionlogica = "";
    private String expresionnumerica = "";
    private double operando1 = 0;
    private double operando2 = 0;
    private double resultado = 0;
    private String operador = "";

    private boolean lista;
    private boolean percentil;
    private boolean operadores;
    private boolean cat;//trabaja por tangente 
    private boolean dup;
    private boolean multx2 = false;
    private boolean dimensR;
    //private boolean rz;
    private boolean potenc;
    private boolean espesor;
    private boolean holg;

    //******************************************************************calculo cierra
    //*****************************************************************prof inicia
    Imagen imag;

    private Image fpostura1;
    private Image fpostura2;
    private Image fpostura3;
    private Image fpostura4;
    private Image fpostura5;
    private Image fpostura6;
    private Image fpostura7;
    private Image foto = null;
    private Image fotoauxiliar = null;
    private Icon fotoprincipal = null;

    private String dimension;

    boolean depieDL;
    boolean depieCA;
    boolean depieDF;
    boolean sentadodeladoCA;
    boolean sentadodeladoBE;
    boolean sentadodeespalda;
    boolean sentadodeespaldaBL;

    int idposcdesc1;
    int idposcdesc2;
    int idposcdesc3;
    int idposcdesc4;
    int idposcdesc5;
    int idposcdesc6;
    int idposcdesc7;
    int retroalimentacion_veces = 0;//despues de usarlo debo ponerlo en cero
    int clickcount = 0;
    //int postdimension;
    int anterior = 0;
    int siguiente = 0;
    int rotac_muestra = 0;
    int sujeto_aleatorio;

    //public static List<GestionPosturas> gestion_posturas;
    //private SujetoJpaController controlsujeto;
    private int columna = 0;
    private int canterrpostura = 0;
    private int canterrpuntos = 0;
    //****************************************************************prof cierra

    public Principal() {
        super();

        initComponents();

        ponLaAyuda();
        //alineacion(StyleConstants.ALIGN_LEFT);
        //this.setModal(true);
        //**********************************

        UserInterfaceSuport.centerComponent(this);
        spprincipal.setVisible(false);
        auxerrorCE = new Modelo.Error();
        //lbldim1.setVisible(false);
        //controladore iniciados
        controlformula = new FormulaDrJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlerror = new ErrorJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlpostura = new PosturasJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlsujeto = new SujetoJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlposicionesdimensiones = new PosicionesDimensionesJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlsujetoposiciones = new SujetoPosicionesJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlexperimentacion = new ExperimentacionJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlinstrumento = new InstrumentoJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controldimensiones = new DimensionesJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
        controlrespt = new RespuestaExperimentacionJpaController(javax.persistence.Persistence.createEntityManagerFactory("Prueba5PU"));
//variables
        //experim=comienzoAleatorio(controlexperimentacion.getExperimentacionCount()-1);
        //modeloexp=controlexperimentacion.Mostrar(experim);
        llenaPanelExperimentacion();
        errores = new ArrayList<>();
        //funciones
        llenaPosturasComboBox();
        habilitaCasosdeEstudio(false);
        //llenarExperimentacion();

        auxerrorExp = new Modelo.Error();
        auxerrorPA = new Modelo.Error();
        auxerrorPu = new Modelo.Error();

        //Eliminar Fichero miApp.tmp al cerrar la aplicacion al dar click en la 'X'
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
        });

        //*********************************************************
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
        jLabel6 = new javax.swing.JLabel();
        panelpuestotrabajo = new javax.swing.JPanel();
        panelinstrumento = new javax.swing.JPanel();
        lblinstrumentos = new javax.swing.JLabel();
        panelmonbinstrumento = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelpuestos = new javax.swing.JPanel();
        lbltrabajopc = new javax.swing.JLabel();
        lblagenciaviajes = new javax.swing.JLabel();
        lblfabricacomponentes = new javax.swing.JLabel();
        lblmaquinaimpresion = new javax.swing.JLabel();
        lblpupitre = new javax.swing.JLabel();
        lblsillaburo = new javax.swing.JLabel();
        lblpuestotrabajo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelrespuestaexperimentacion = new javax.swing.JPanel();
        panelprincipal = new javax.swing.JPanel();
        spprincipal = new javax.swing.JScrollPane();
        txtareaprincipal = new javax.swing.JTextArea();
        btnsalir = new javax.swing.JButton();
        btnautenticar = new javax.swing.JButton();
        lblimagenprincipal = new javax.swing.JLabel();
        panelcasoestudio = new javax.swing.JPanel();
        btncomenzar = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablamuestra = new javax.swing.JTable();
        jcbmuestra = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAEnunciado = new javax.swing.JTextArea();
        btncomenzar1 = new javax.swing.JButton();
        btncomenzar2 = new javax.swing.JButton();
        btncomenzar3 = new javax.swing.JButton();
        paneldimensiones = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        spdimensionesR = new javax.swing.JScrollPane();
        paneldr = new javax.swing.JPanel();
        jChBdr10 = new javax.swing.JCheckBox();
        lbldr10 = new javax.swing.JLabel();
        jChBdr9 = new javax.swing.JCheckBox();
        lbldr9 = new javax.swing.JLabel();
        jChBdr8 = new javax.swing.JCheckBox();
        lbldr8 = new javax.swing.JLabel();
        jChBdr7 = new javax.swing.JCheckBox();
        lbldr7 = new javax.swing.JLabel();
        jChBdr6 = new javax.swing.JCheckBox();
        lbldr6 = new javax.swing.JLabel();
        jChBdr5 = new javax.swing.JCheckBox();
        lbldr5 = new javax.swing.JLabel();
        jChBdr4 = new javax.swing.JCheckBox();
        lbldr4 = new javax.swing.JLabel();
        jChBdr3 = new javax.swing.JCheckBox();
        lbldr3 = new javax.swing.JLabel();
        jChBdr2 = new javax.swing.JCheckBox();
        lbldr2 = new javax.swing.JLabel();
        jChBdr1 = new javax.swing.JCheckBox();
        lbldr1 = new javax.swing.JLabel();
        jChBdr11 = new javax.swing.JCheckBox();
        lbldr11 = new javax.swing.JLabel();
        jChBdr12 = new javax.swing.JCheckBox();
        lbldr12 = new javax.swing.JLabel();
        jChBdr13 = new javax.swing.JCheckBox();
        lbldr13 = new javax.swing.JLabel();
        jChBdr14 = new javax.swing.JCheckBox();
        lbldr14 = new javax.swing.JLabel();
        jChBdr15 = new javax.swing.JCheckBox();
        lbldr15 = new javax.swing.JLabel();
        lbldr16 = new javax.swing.JLabel();
        jChBdr16 = new javax.swing.JCheckBox();
        lbldr17 = new javax.swing.JLabel();
        jChBdr17 = new javax.swing.JCheckBox();
        lbldr18 = new javax.swing.JLabel();
        jChBdr18 = new javax.swing.JCheckBox();
        jChBdr19 = new javax.swing.JCheckBox();
        lbldr19 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        scdimensionesA = new javax.swing.JScrollPane();
        panelda = new javax.swing.JPanel();
        chbanchodelpie = new javax.swing.JCheckBox();
        lblanchodelpie = new javax.swing.JLabel();
        chbsacropoplitea = new javax.swing.JCheckBox();
        lblsacropoplitea = new javax.swing.JLabel();
        chbalturasacrorotula = new javax.swing.JCheckBox();
        lblalturasacrorotula = new javax.swing.JLabel();
        chblongituddelmuslo = new javax.swing.JCheckBox();
        lbllongituddelmuslo = new javax.swing.JLabel();
        chbalturademuslo = new javax.swing.JCheckBox();
        lblalturadelmusloxxxx = new javax.swing.JLabel();
        chbalcancemaximodelbrazo = new javax.swing.JCheckBox();
        lblalcancemaximodelbrazo = new javax.swing.JLabel();
        chbalturasubescapular = new javax.swing.JCheckBox();
        lblalturasubescapular = new javax.swing.JLabel();
        chbanchodecodoacodo = new javax.swing.JCheckBox();
        lblanchodecodoacodo = new javax.swing.JLabel();
        chbalcancelateraldelbrazo = new javax.swing.JCheckBox();
        lblalcancelateraldelbrazo = new javax.swing.JLabel();
        chbalturadelosojossentado = new javax.swing.JCheckBox();
        lblalturadelosojossentado = new javax.swing.JLabel();
        chbalturailiocrestal = new javax.swing.JCheckBox();
        lblalturailiocrestal = new javax.swing.JLabel();
        chbalturadelosojos = new javax.swing.JCheckBox();
        lblalturadelosojos = new javax.swing.JLabel();
        lblalturadelcodo = new javax.swing.JLabel();
        chbalturadelcodo = new javax.swing.JCheckBox();
        lblestatura = new javax.swing.JLabel();
        chbestatura = new javax.swing.JCheckBox();
        chbdiametrobiacromial = new javax.swing.JCheckBox();
        lbldiametrobiacromial = new javax.swing.JLabel();
        lblalcanceminimodelbrazo = new javax.swing.JLabel();
        chbalcanceminimodelbrazo = new javax.swing.JCheckBox();
        chbalturadelarodilla = new javax.swing.JCheckBox();
        lblalturadelarodilla = new javax.swing.JLabel();
        chbalturadelcodosentado = new javax.swing.JCheckBox();
        lblalturadelcodosentado = new javax.swing.JLabel();
        chbalturadeojos1 = new javax.swing.JCheckBox();
        lblalturadeojos1 = new javax.swing.JLabel();
        lblalturapoplitea = new javax.swing.JLabel();
        chbalturapoplitea = new javax.swing.JCheckBox();
        chbanchodecaderasentado = new javax.swing.JCheckBox();
        lblanchodecaderasentado = new javax.swing.JLabel();
        lblalturailiocrestal11 = new javax.swing.JLabel();
        chbalturailiocrestal11 = new javax.swing.JCheckBox();
        btnpasarAexperimentacion = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        panelexperimentacion = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelimagen = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelposturas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        btbsiguienteexp = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        sptabladimensiones = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabladimension = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        btnpasarAcalculardiseno = new javax.swing.JButton();
        btnatrasExp = new javax.swing.JButton();
        panelcalculo = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbdimrelv = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        listadiseno = new javax.swing.JList();
        paneloperadores = new javax.swing.JPanel();
        panelopr1 = new javax.swing.JPanel();
        btnsuma = new javax.swing.JButton();
        btnresta = new javax.swing.JButton();
        btnmultiplicacion = new javax.swing.JButton();
        btnespesor = new javax.swing.JButton();
        btndivision = new javax.swing.JButton();
        panelopr2 = new javax.swing.JPanel();
        btnduplo = new javax.swing.JButton();
        btnholgura = new javax.swing.JButton();
        btnpotencia = new javax.swing.JButton();
        cbtangente = new javax.swing.JComboBox();
        cbpercentil = new javax.swing.JComboBox();
        paneldiseno = new javax.swing.JPanel();
        lbldim1 = new javax.swing.JLabel();
        lblresultado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        btncalculo = new javax.swing.JButton();
        btnreiniciar = new javax.swing.JButton();
        btnfincalculo = new javax.swing.JButton();
        btnatrascalculo = new javax.swing.JButton();
        lblejercicio = new javax.swing.JLabel();
        btnayuda = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniempezar = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuregistro = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuevaluacion = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        menugestion = new javax.swing.JMenu();
        menuitemejercicios = new javax.swing.JMenuItem();
        menuitemcasoestud = new javax.swing.JMenuItem();
        menuitemtraza = new javax.swing.JMenuItem();
        menureportes = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laboratorio Virtual de Antropometría");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 833, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel6.setText("                                                          SELECCIONE UN PUESTO DE TRABAJO A DISEÑAR Y SUS DIMENSIONES RELEVANTES");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 41, 1111, 44));

        panelpuestotrabajo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelpuestotrabajo.setLayout(new java.awt.CardLayout());

        lblinstrumentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vacio.JPG"))); // NOI18N
        lblinstrumentos.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout panelmonbinstrumentoLayout = new javax.swing.GroupLayout(panelmonbinstrumento);
        panelmonbinstrumento.setLayout(panelmonbinstrumentoLayout);
        panelmonbinstrumentoLayout.setHorizontalGroup(
            panelmonbinstrumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelmonbinstrumentoLayout.setVerticalGroup(
            panelmonbinstrumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Puestos de trabajo");
        jLabel4.setEnabled(false);

        javax.swing.GroupLayout panelinstrumentoLayout = new javax.swing.GroupLayout(panelinstrumento);
        panelinstrumento.setLayout(panelinstrumentoLayout);
        panelinstrumentoLayout.setHorizontalGroup(
            panelinstrumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelinstrumentoLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelmonbinstrumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblinstrumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelinstrumentoLayout.setVerticalGroup(
            panelinstrumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelinstrumentoLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelmonbinstrumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblinstrumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelpuestotrabajo.add(panelinstrumento, "card2");

        lbltrabajopc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltrabajopc.setText("Silla antropométrica");
        lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbltrabajopc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbltrabajopc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltrabajopcMouseClicked(evt);
            }
        });

        lblagenciaviajes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblagenciaviajes.setText("Tallímetro o estadímetro");
        lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblagenciaviajes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblagenciaviajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblagenciaviajesMouseClicked(evt);
            }
        });

        lblfabricacomponentes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblfabricacomponentes.setText("Antropómetro");
        lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblfabricacomponentes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblfabricacomponentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblfabricacomponentesMouseClicked(evt);
            }
        });

        lblmaquinaimpresion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblmaquinaimpresion.setText("Cinta antropométrica");
        lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblmaquinaimpresion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblmaquinaimpresion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmaquinaimpresionMouseClicked(evt);
            }
        });

        lblpupitre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblpupitre.setText("Compás antropométrico");
        lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblpupitre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblpupitre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpupitreMouseClicked(evt);
            }
        });

        lblsillaburo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblsillaburo.setText("Segmómetro o cinta Lufkin");
        lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblsillaburo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblsillaburo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsillaburoMouseClicked(evt);
            }
        });

        lblpuestotrabajo.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("1. Seleccione un instrumento de medición");

        javax.swing.GroupLayout panelpuestosLayout = new javax.swing.GroupLayout(panelpuestos);
        panelpuestos.setLayout(panelpuestosLayout);
        panelpuestosLayout.setHorizontalGroup(
            panelpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelpuestosLayout.createSequentialGroup()
                .addGroup(panelpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltrabajopc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblagenciaviajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblfabricacomponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblmaquinaimpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblpupitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblsillaburo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblpuestotrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panelpuestosLayout.setVerticalGroup(
            panelpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelpuestosLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltrabajopc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblagenciaviajes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblfabricacomponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblmaquinaimpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpupitre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsillaburo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 23, Short.MAX_VALUE)
                .addComponent(lblpuestotrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelpuestotrabajo.add(panelpuestos, "card3");

        getContentPane().add(panelpuestotrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 91, -1, -1));

        panelrespuestaexperimentacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        panelrespuestaexperimentacion.setLayout(new java.awt.CardLayout());

        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spprincipal.setBackground(new Color(0,0,0,0));
        spprincipal.setBorder(null);

        txtareaprincipal.setEditable(false);
        txtareaprincipal.setBackground(new Color(0,0,0,0));
        txtareaprincipal.setColumns(20);
        txtareaprincipal.setFont(new java.awt.Font("MS Mincho", 1, 18)); // NOI18N
        txtareaprincipal.setLineWrap(true);
        txtareaprincipal.setRows(5);
        txtareaprincipal.setText("Para iniciar la resolución de un caso\n de estudio primero debe seleccionar \n    el puesto de trabajo que va a \n               diseñar\n");
        txtareaprincipal.setBorder(null);
        spprincipal.setViewportView(txtareaprincipal);

        panelprincipal.add(spprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 381, 125));

        btnsalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0))));
        btnsalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        panelprincipal.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, 140, 80));

        btnautenticar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnautenticar.setText("Iniciar sesión");
        btnautenticar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0))));
        btnautenticar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnautenticar.setName("autentificar"); // NOI18N
        btnautenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnautenticarActionPerformed(evt);
            }
        });
        panelprincipal.add(btnautenticar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 140, 80));

        lblimagenprincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Collage4.jpg"))); // NOI18N
        panelprincipal.add(lblimagenprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 640));

        panelrespuestaexperimentacion.add(panelprincipal, "card6");

        panelcasoestudio.setBackground(lblalturadelmusloxxxx.getBackground());

        btncomenzar.setText("Comenzar");
        btncomenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomenzarActionPerformed(evt);
            }
        });

        tablamuestra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "                    Sujetos", "            Posiciones del puesto de trabajo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablamuestra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablamuestraMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablamuestra);

        jScrollPane7.setViewportView(jScrollPane8);

        jcbmuestra.setToolTipText("seleccione tamaño de muestra");
        jcbmuestra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbmuestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbmuestraActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("1. Tamaño de muestra");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("2. Posiciones del puesto de trabajo por sujetos");

        jScrollPane2.setBackground(new Color(0,0,0,0));
        jScrollPane2.setBorder(null);

        txtAEnunciado.setEditable(false);
        txtAEnunciado.setBackground(new Color(0,0,0,0));
        txtAEnunciado.setColumns(20);
        txtAEnunciado.setFont(new java.awt.Font("Myriad Hebrew", 0, 18)); // NOI18N
        txtAEnunciado.setLineWrap(true);
        txtAEnunciado.setRows(5);
        txtAEnunciado.setWrapStyleWord(true);
        txtAEnunciado.setBorder(null);
        jScrollPane2.setViewportView(txtAEnunciado);

        btncomenzar1.setText("Comenzar");
        btncomenzar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomenzar1ActionPerformed(evt);
            }
        });

        btncomenzar2.setText("Comenzar");
        btncomenzar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomenzar2ActionPerformed(evt);
            }
        });

        btncomenzar3.setText("Comenzar");
        btncomenzar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomenzar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcasoestudioLayout = new javax.swing.GroupLayout(panelcasoestudio);
        panelcasoestudio.setLayout(panelcasoestudioLayout);
        panelcasoestudioLayout.setHorizontalGroup(
            panelcasoestudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcasoestudioLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(panelcasoestudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelcasoestudioLayout.createSequentialGroup()
                        .addGroup(panelcasoestudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbmuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(110, 110, 110))
            .addGroup(panelcasoestudioLayout.createSequentialGroup()
                .addGroup(panelcasoestudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcasoestudioLayout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addComponent(btncomenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcasoestudioLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcasoestudioLayout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addComponent(btncomenzar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcasoestudioLayout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addComponent(btncomenzar2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelcasoestudioLayout.createSequentialGroup()
                        .addGap(705, 705, 705)
                        .addComponent(btncomenzar3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelcasoestudioLayout.setVerticalGroup(
            panelcasoestudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcasoestudioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbmuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btncomenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btncomenzar3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btncomenzar2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btncomenzar1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelrespuestaexperimentacion.add(panelcasoestudio, "card6");

        paneldimensiones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("                    del puesto de trabajo");
        paneldimensiones.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 310, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("     1. Dimensiones relevantes para el diseño");
        paneldimensiones.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 310, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("            2. Dimensiones humanas que se corresponden");
        paneldimensiones.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 380, 30));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("                         con las dimensiones relevantes");
        paneldimensiones.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 32, 360, 20));
        paneldimensiones.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 20));

        spdimensionesR.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spdimensionesR.setHorizontalScrollBar(null);

        paneldr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbldr10.setText("Altura del apoyapié");
        lbldr10.setToolTipText("");
        lbldr10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr10MouseClicked(evt);
            }
        });

        lbldr9.setText("Altura superior del espaldar");
        lbldr9.setToolTipText("");
        lbldr9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr9MouseClicked(evt);
            }
        });

        lbldr8.setText("Altura del centro del display");
        lbldr8.setToolTipText("");
        lbldr8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr8MouseClicked(evt);
            }
        });

        lbldr7.setText("Altura inferior del espaldar");
        lbldr7.setToolTipText("");
        lbldr7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr7MouseClicked(evt);
            }
        });

        lbldr6.setText("Ancho del asiento");
        lbldr6.setToolTipText("");
        lbldr6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr6MouseClicked(evt);
            }
        });

        lbldr5.setText("Profundidad de la mesa");
        lbldr5.setToolTipText("");
        lbldr5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr5MouseClicked(evt);
            }
        });

        lbldr4.setText("Profundidad de la silla");
        lbldr4.setToolTipText("");
        lbldr4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr4MouseClicked(evt);
            }
        });

        lbldr3.setText("Altura del asiento");
        lbldr3.setToolTipText("");
        lbldr3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr3MouseClicked(evt);
            }
        });

        lbldr2.setText("Altura de la mesa");
        lbldr2.setToolTipText("");
        lbldr2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr2MouseClicked(evt);
            }
        });

        lbldr1.setText("Ancho del espaldar");
        lbldr1.setToolTipText("");
        lbldr1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbldr11.setText("Ancho de la mesa");
        lbldr11.setToolTipText("");
        lbldr11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr11MouseClicked(evt);
            }
        });

        lbldr12.setText("Altura superior de la paleta del pupitre");
        lbldr12.setToolTipText("");
        lbldr12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr12MouseClicked(evt);
            }
        });

        lbldr13.setText("Altura de la recepción");
        lbldr13.setToolTipText("");
        lbldr13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr13MouseClicked(evt);
            }
        });

        lbldr14.setText("Altura de la repisa de la mesa");
        lbldr14.setToolTipText("");
        lbldr14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr14MouseClicked(evt);
            }
        });

        lbldr15.setText("Altura inferior de la paleta del pupitre");
        lbldr15.setToolTipText("");
        lbldr15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr15MouseClicked(evt);
            }
        });

        lbldr16.setText("Altura de la mesa del teclado");
        lbldr16.setToolTipText("");
        lbldr16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr16MouseClicked(evt);
            }
        });

        lbldr17.setText("Espacio para las extremidades bajo la mesa");
        lbldr17.setToolTipText("");
        lbldr17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr17MouseClicked(evt);
            }
        });

        lbldr18.setText("Altura de la silla");
        lbldr18.setToolTipText("");
        lbldr18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr18MouseClicked(evt);
            }
        });

        lbldr19.setText("Altura de la superficie de los controles");
        lbldr19.setToolTipText("");
        lbldr19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbldr19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldr19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneldrLayout = new javax.swing.GroupLayout(paneldr);
        paneldr.setLayout(paneldrLayout);
        paneldrLayout.setHorizontalGroup(
            paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldrLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(paneldrLayout.createSequentialGroup()
                                    .addComponent(jChBdr2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldr2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(paneldrLayout.createSequentialGroup()
                                    .addComponent(jChBdr1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldr1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldrLayout.createSequentialGroup()
                                .addComponent(jChBdr3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbldr3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paneldrLayout.createSequentialGroup()
                                    .addComponent(jChBdr4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldr4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paneldrLayout.createSequentialGroup()
                                    .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jChBdr6)
                                        .addComponent(jChBdr5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbldr6, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbldr5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(13, 13, 13))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paneldrLayout.createSequentialGroup()
                        .addComponent(jChBdr8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldr8, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldrLayout.createSequentialGroup()
                        .addComponent(jChBdr9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldr9, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr11, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr14, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr16, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr18, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(paneldrLayout.createSequentialGroup()
                            .addComponent(jChBdr19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldr19, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(paneldrLayout.createSequentialGroup()
                        .addComponent(jChBdr12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldr12, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldrLayout.createSequentialGroup()
                        .addComponent(jChBdr15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldr15, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldrLayout.createSequentialGroup()
                        .addComponent(jChBdr17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldr17, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        paneldrLayout.setVerticalGroup(
            paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneldrLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr1)
                    .addComponent(lbldr1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr2)
                    .addComponent(lbldr2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr3)
                    .addComponent(lbldr3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr4)
                    .addComponent(lbldr4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr5)
                    .addComponent(lbldr5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr6)
                    .addComponent(lbldr6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr7)
                    .addComponent(lbldr7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr8)
                    .addComponent(lbldr8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr9)
                    .addComponent(lbldr9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr10)
                    .addComponent(lbldr10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr11)
                    .addComponent(lbldr11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr12)
                    .addComponent(lbldr12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr13)
                    .addComponent(lbldr13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr14)
                    .addComponent(lbldr14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr15)
                    .addComponent(lbldr15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr16)
                    .addComponent(lbldr16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr17)
                    .addComponent(lbldr17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr18)
                    .addComponent(lbldr18, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jChBdr19)
                    .addComponent(lbldr19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        spdimensionesR.setViewportView(paneldr);

        paneldimensiones.add(spdimensionesR, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 394, 490));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        paneldimensiones.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 10, 520));

        scdimensionesA.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scdimensionesA.setHorizontalScrollBar(null);

        panelda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chbanchodelpie.setToolTipText("");

        lblanchodelpie.setText("Ancho del pie");
        lblanchodelpie.setToolTipText("Información");
        lblanchodelpie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblanchodelpie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblanchodelpieMouseClicked(evt);
            }
        });

        lblsacropoplitea.setText("Longitud sacropoplítea");
        lblsacropoplitea.setToolTipText("Información");
        lblsacropoplitea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblsacropoplitea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsacropopliteaMouseClicked(evt);
            }
        });

        lblalturasacrorotula.setText("Longitud sacrorótula");
        lblalturasacrorotula.setToolTipText("Información");
        lblalturasacrorotula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturasacrorotula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturasacrorotulaMouseClicked(evt);
            }
        });

        lbllongituddelmuslo.setText("Longitud del muslo");
        lbllongituddelmuslo.setToolTipText("Información");
        lbllongituddelmuslo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbllongituddelmuslo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllongituddelmusloMouseClicked(evt);
            }
        });

        lblalturadelmusloxxxx.setText("Altura del muslo");
        lblalturadelmusloxxxx.setToolTipText("Información");
        lblalturadelmusloxxxx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelmusloxxxx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelmusloxxxxMouseClicked(evt);
            }
        });

        lblalcancemaximodelbrazo.setText("Alcance máximo del brazo con agarre");
        lblalcancemaximodelbrazo.setToolTipText("Información");
        lblalcancemaximodelbrazo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalcancemaximodelbrazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalcancemaximodelbrazoMouseClicked(evt);
            }
        });

        lblalturasubescapular.setText("Altura subescapular");
        lblalturasubescapular.setToolTipText("Información");
        lblalturasubescapular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturasubescapular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturasubescapularMouseClicked(evt);
            }
        });

        lblanchodecodoacodo.setText("Ancho de codo a codo");
        lblanchodecodoacodo.setToolTipText("Información");
        lblanchodecodoacodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblanchodecodoacodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblanchodecodoacodoMouseClicked(evt);
            }
        });

        lblalcancelateraldelbrazo.setText("Alcance lateral del brazo");
        lblalcancelateraldelbrazo.setToolTipText("Información");
        lblalcancelateraldelbrazo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalcancelateraldelbrazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalcancelateraldelbrazoMouseClicked(evt);
            }
        });

        lblalturadelosojossentado.setText("Altura de los ojos sentado");
        lblalturadelosojossentado.setToolTipText("Información");
        lblalturadelosojossentado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelosojossentado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelosojossentadoMouseClicked(evt);
            }
        });

        lblalturailiocrestal.setText("Altura iliocrestal");
        lblalturailiocrestal.setToolTipText("Información");
        lblalturailiocrestal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturailiocrestal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturailiocrestalMouseClicked(evt);
            }
        });

        lblalturadelosojos.setText("Altura de los ojos");
        lblalturadelosojos.setToolTipText("Información");
        lblalturadelosojos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelosojos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelosojosMouseClicked(evt);
            }
        });

        lblalturadelcodo.setText("Altura del codo");
        lblalturadelcodo.setToolTipText("Información");
        lblalturadelcodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelcodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelcodoMouseClicked(evt);
            }
        });

        lblestatura.setText("Estatura");
        lblestatura.setToolTipText("Información");
        lblestatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblestatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblestaturaMouseClicked(evt);
            }
        });

        lbldiametrobiacromial.setText("Diámetro biacromial");
        lbldiametrobiacromial.setToolTipText("Información");
        lbldiametrobiacromial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbldiametrobiacromial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldiametrobiacromialMouseClicked(evt);
            }
        });

        lblalcanceminimodelbrazo.setText("Ancho de la cadera sentado");
        lblalcanceminimodelbrazo.setToolTipText("Información");
        lblalcanceminimodelbrazo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalcanceminimodelbrazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalcanceminimodelbrazoMouseClicked(evt);
            }
        });

        lblalturadelarodilla.setText("Altura de la rodilla");
        lblalturadelarodilla.setToolTipText("Información");
        lblalturadelarodilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelarodilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelarodillaMouseClicked(evt);
            }
        });

        lblalturadelcodosentado.setText("Altura poplítea");
        lblalturadelcodosentado.setToolTipText("Información");
        lblalturadelcodosentado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadelcodosentado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadelcodosentadoMouseClicked(evt);
            }
        });

        lblalturadeojos1.setText("Altura del codo sentado");
        lblalturadeojos1.setToolTipText("Información");
        lblalturadeojos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturadeojos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturadeojos1MouseClicked(evt);
            }
        });

        lblalturapoplitea.setText("Profundidad del cuerpo");
        lblalturapoplitea.setToolTipText("Información");
        lblalturapoplitea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturapoplitea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturapopliteaMouseClicked(evt);
            }
        });

        lblanchodecaderasentado.setText("Alcance mínimo del brazo");
        lblanchodecaderasentado.setToolTipText("Información");
        lblanchodecaderasentado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblanchodecaderasentado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblanchodecaderasentadoMouseClicked(evt);
            }
        });

        lblalturailiocrestal11.setText("Alcance máximo del brazo ");
        lblalturailiocrestal11.setToolTipText("Información");
        lblalturailiocrestal11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblalturailiocrestal11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblalturailiocrestal11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout paneldaLayout = new javax.swing.GroupLayout(panelda);
        panelda.setLayout(paneldaLayout);
        paneldaLayout.setHorizontalGroup(
            paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldaLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbanchodelpie)
                        .addGap(9, 9, 9)
                        .addComponent(lblanchodelpie, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbsacropoplitea)
                        .addGap(9, 9, 9)
                        .addComponent(lblsacropoplitea, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturasacrorotula)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturasacrorotula, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chblongituddelmuslo)
                        .addGap(9, 9, 9)
                        .addComponent(lbllongituddelmuslo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturademuslo)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelmusloxxxx, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalcancemaximodelbrazo)
                        .addGap(9, 9, 9)
                        .addComponent(lblalcancemaximodelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturasubescapular)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturasubescapular, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbanchodecodoacodo)
                        .addGap(9, 9, 9)
                        .addComponent(lblanchodecodoacodo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalcancelateraldelbrazo)
                        .addGap(9, 9, 9)
                        .addComponent(lblalcancelateraldelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadelosojossentado)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelosojossentado, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturailiocrestal)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturailiocrestal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadelosojos)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelosojos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadelcodo)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelcodo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbestatura)
                        .addGap(9, 9, 9)
                        .addComponent(lblestatura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbdiametrobiacromial)
                        .addGap(9, 9, 9)
                        .addComponent(lbldiametrobiacromial, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalcanceminimodelbrazo)
                        .addGap(9, 9, 9)
                        .addComponent(lblalcanceminimodelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadelarodilla)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelarodilla, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadelcodosentado)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadelcodosentado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturadeojos1)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturadeojos1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturapoplitea)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturapoplitea, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbanchodecaderasentado)
                        .addGap(9, 9, 9)
                        .addComponent(lblanchodecaderasentado, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneldaLayout.createSequentialGroup()
                        .addComponent(chbalturailiocrestal11)
                        .addGap(9, 9, 9)
                        .addComponent(lblalturailiocrestal11, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        paneldaLayout.setVerticalGroup(
            paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneldaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbanchodelpie)
                    .addComponent(lblanchodelpie, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbsacropoplitea)
                    .addComponent(lblsacropoplitea, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturasacrorotula)
                    .addComponent(lblalturasacrorotula, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chblongituddelmuslo)
                    .addComponent(lbllongituddelmuslo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturademuslo)
                    .addComponent(lblalturadelmusloxxxx, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalcancemaximodelbrazo)
                    .addComponent(lblalcancemaximodelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturasubescapular)
                    .addComponent(lblalturasubescapular, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbanchodecodoacodo)
                    .addComponent(lblanchodecodoacodo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalcancelateraldelbrazo)
                    .addComponent(lblalcancelateraldelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadelosojossentado)
                    .addComponent(lblalturadelosojossentado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturailiocrestal)
                    .addComponent(lblalturailiocrestal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadelosojos)
                    .addComponent(lblalturadelosojos, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadelcodo)
                    .addComponent(lblalturadelcodo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbestatura)
                    .addComponent(lblestatura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbdiametrobiacromial)
                    .addComponent(lbldiametrobiacromial, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalcanceminimodelbrazo)
                    .addComponent(lblalcanceminimodelbrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadelarodilla)
                    .addComponent(lblalturadelarodilla, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadelcodosentado)
                    .addComponent(lblalturadelcodosentado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturadeojos1)
                    .addComponent(lblalturadeojos1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturapoplitea)
                    .addComponent(lblalturapoplitea, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbanchodecaderasentado)
                    .addComponent(lblanchodecaderasentado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paneldaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbalturailiocrestal11)
                    .addComponent(lblalturailiocrestal11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scdimensionesA.setViewportView(panelda);

        paneldimensiones.add(scdimensionesA, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 360, 490));

        btnpasarAexperimentacion.setText("Siguiente");
        btnpasarAexperimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasarAexperimentacionActionPerformed(evt);
            }
        });
        paneldimensiones.add(btnpasarAexperimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 590, 100, 30));

        jButton6.setText("Atrás");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        paneldimensiones.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 102, 29));
        jButton6.getAccessibleContext().setAccessibleDescription("");

        panelrespuestaexperimentacion.add(paneldimensiones, "card2");

        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "sujeto"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        jPanel5.setPreferredSize(new java.awt.Dimension(353, 549));

        panelimagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelimagen.setPreferredSize(new java.awt.Dimension(314, 499));
        panelimagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelimagenMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelimagenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelimagenMouseExited(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Mannequin_p.jpg"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelimagenLayout = new javax.swing.GroupLayout(panelimagen);
        panelimagen.setLayout(panelimagenLayout);
        panelimagenLayout.setHorizontalGroup(
            panelimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelimagenLayout.setVerticalGroup(
            panelimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelimagen.getAccessibleContext().setAccessibleName("");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("       4.  Marque los puntos antropométricos asociados ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("                   a la dimensión seleccionada ");

        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "datos"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        panelposturas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "Posturas"));
        panelposturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        panelposturas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 90));

        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        panelposturas.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 80));

        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        panelposturas.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 150, 80));
        panelposturas.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 370, 10));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelposturas.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 6, 10, 212));

        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        panelposturas.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 151, 90));

        btbsiguienteexp.setText("Siguiente");
        btbsiguienteexp.setEnabled(false);
        btbsiguienteexp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbsiguienteexpActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("    3.  Seleccione una  postura");

        tabladimension.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabladimension.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabladimension.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladimensionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabladimension);

        sptabladimensiones.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(btbsiguienteexp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(jLabel17))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(panelposturas, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sptabladimensiones, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(sptabladimensiones, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelposturas, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btbsiguienteexp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("             2.  Seleccione una dimensión humana");

        btnpasarAcalculardiseno.setText("Diseñar");
        btnpasarAcalculardiseno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpasarAcalculardisenoActionPerformed(evt);
            }
        });

        btnatrasExp.setText("Atrás");
        btnatrasExp.setPreferredSize(new java.awt.Dimension(69, 23));
        btnatrasExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrasExpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelexperimentacionLayout = new javax.swing.GroupLayout(panelexperimentacion);
        panelexperimentacion.setLayout(panelexperimentacionLayout);
        panelexperimentacionLayout.setHorizontalGroup(
            panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelexperimentacionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(panelexperimentacionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addComponent(btnatrasExp, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(btnpasarAcalculardiseno, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        panelexperimentacionLayout.setVerticalGroup(
            panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelexperimentacionLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelexperimentacionLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(panelexperimentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnpasarAcalculardiseno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnatrasExp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(8, 8, 8))
        );

        jPanel5.getAccessibleContext().setAccessibleDescription("");

        panelrespuestaexperimentacion.add(panelexperimentacion, "card5");

        jLabel12.setText("Dimensiones relevantes del puesto de trabajo");

        cbdimrelv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdimrelvActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "Dimensiones Antropométricas"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jScrollPane3.setHorizontalScrollBar(null);

        listadiseno.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        listadiseno.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listadiseno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listadisenoMouseClicked(evt);
            }
        });
        listadiseno.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listadisenoValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listadiseno);

        paneloperadores.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "Expresiones para el diseño"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        panelopr1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsuma.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnsuma.setText("+");
        btnsuma.setToolTipText("Suma");
        btnsuma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsuma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsumaActionPerformed(evt);
            }
        });
        panelopr1.add(btnsuma, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, -1));

        btnresta.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnresta.setText("-");
        btnresta.setToolTipText("Resta");
        btnresta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnresta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrestaActionPerformed(evt);
            }
        });
        panelopr1.add(btnresta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 50, -1));

        btnmultiplicacion.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnmultiplicacion.setText("*");
        btnmultiplicacion.setToolTipText("Multiplicación");
        btnmultiplicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnmultiplicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmultiplicacionActionPerformed(evt);
            }
        });
        panelopr1.add(btnmultiplicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 50, -1));

        btnespesor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnespesor.setText("Espesor");
        btnespesor.setToolTipText("Espesor");
        btnespesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnespesorActionPerformed(evt);
            }
        });
        panelopr1.add(btnespesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 80, 40));

        btndivision.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btndivision.setText("/");
        btndivision.setToolTipText("División");
        btndivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndivisionActionPerformed(evt);
            }
        });
        panelopr1.add(btndivision, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 50, -1));

        panelopr2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnduplo.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnduplo.setText("2x");
        btnduplo.setToolTipText("Dos veces x");
        btnduplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnduploActionPerformed(evt);
            }
        });
        panelopr2.add(btnduplo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 40));

        btnholgura.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnholgura.setText("Holgura");
        btnholgura.setToolTipText("Holgura");
        btnholgura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnholguraActionPerformed(evt);
            }
        });
        panelopr2.add(btnholgura, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 80, 40));

        btnpotencia.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnpotencia.setText("x^2");
        btnpotencia.setToolTipText("Potencia cuadrada");
        btnpotencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpotencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpotenciaActionPerformed(evt);
            }
        });
        panelopr2.add(btnpotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 60, 40));

        cbtangente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tan", "20", "30", "45", "60", " " }));
        cbtangente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtangenteActionPerformed(evt);
            }
        });
        panelopr2.add(cbtangente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 50, 40));

        cbpercentil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Percentil", "P(5)", "P(95)" }));
        cbpercentil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbpercentilActionPerformed(evt);
            }
        });
        panelopr2.add(cbpercentil, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 80, 40));

        javax.swing.GroupLayout paneloperadoresLayout = new javax.swing.GroupLayout(paneloperadores);
        paneloperadores.setLayout(paneloperadoresLayout);
        paneloperadoresLayout.setHorizontalGroup(
            paneloperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneloperadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneloperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelopr2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addComponent(panelopr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        paneloperadoresLayout.setVerticalGroup(
            paneloperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneloperadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelopr1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelopr2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        paneldiseno.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "Resultado"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        paneldiseno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbldim1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbldim1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, null, null, new java.awt.Color(153, 153, 153)));
        paneldiseno.add(lbldim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 750, 31));

        lblresultado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblresultado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        paneldiseno.add(lblresultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 750, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)), "Registro"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setViewportView(jList1);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 780, 60));

        btncalculo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btncalculo.setText("Calcular");
        btncalculo.setToolTipText("Calcular");
        btncalculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalculoActionPerformed(evt);
            }
        });

        btnreiniciar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnreiniciar.setText("Reiniciar");
        btnreiniciar.setToolTipText("Comenzar de nuevo");
        btnreiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreiniciarActionPerformed(evt);
            }
        });

        btnfincalculo.setText("Fin");
        btnfincalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfincalculoActionPerformed(evt);
            }
        });

        btnatrascalculo.setText("Atrás");
        btnatrascalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrascalculoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcalculoLayout = new javax.swing.GroupLayout(panelcalculo);
        panelcalculo.setLayout(panelcalculoLayout);
        panelcalculoLayout.setHorizontalGroup(
            panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcalculoLayout.createSequentialGroup()
                .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcalculoLayout.createSequentialGroup()
                        .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelcalculoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paneloperadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelcalculoLayout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(btncalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(btnreiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelcalculoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(paneldiseno, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelcalculoLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbdimrelv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelcalculoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelcalculoLayout.createSequentialGroup()
                                .addComponent(btnatrascalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnfincalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelcalculoLayout.setVerticalGroup(
            panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcalculoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cbdimrelv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcalculoLayout.createSequentialGroup()
                        .addComponent(paneloperadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnreiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(paneldiseno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelcalculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfincalculo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnatrascalculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelrespuestaexperimentacion.add(panelcalculo, "card4");

        getContentPane().add(panelrespuestaexperimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 91, -1, 642));

        lblejercicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ejercitacion.jpg"))); // NOI18N
        lblejercicio.setToolTipText("Ejercitación");
        lblejercicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblejercicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblejercicioMouseClicked(evt);
            }
        });
        getContentPane().add(lblejercicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1005, 0, 50, -1));

        btnayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ayuda.jpg"))); // NOI18N
        getContentPane().add(btnayuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(1059, 0, 50, 40));

        jMenuBar1.setEnabled(false);

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        mniempezar.setText("Empezar");
        mniempezar.setEnabled(false);
        mniempezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniempezarActionPerformed(evt);
            }
        });
        jMenu1.add(mniempezar);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        menuregistro.setText("Registro");
        menuregistro.setEnabled(false);

        jMenuItem6.setText("Usuario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuregistro.add(jMenuItem6);

        jMenuBar1.add(menuregistro);

        menuevaluacion.setText("Evaluación");
        menuevaluacion.setEnabled(false);

        jMenuItem7.setText("Calificaciones");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuevaluacion.add(jMenuItem7);

        jMenuBar1.add(menuevaluacion);

        menugestion.setText("Gestión");
        menugestion.setEnabled(false);

        menuitemejercicios.setText("Ejercicios");
        menuitemejercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemejerciciosActionPerformed(evt);
            }
        });
        menugestion.add(menuitemejercicios);

        menuitemcasoestud.setText("Caso de estudio");
        menuitemcasoestud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemcasoestudActionPerformed(evt);
            }
        });
        menugestion.add(menuitemcasoestud);

        menuitemtraza.setText("Trazas");
        menuitemtraza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemtrazaActionPerformed(evt);
            }
        });
        menugestion.add(menuitemtraza);

        jMenuBar1.add(menugestion);

        menureportes.setText("Reportes");
        menureportes.setEnabled(false);

        jMenuItem1.setText("Casos de estudio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menureportes.add(jMenuItem1);

        jMenuItem8.setText("Ejericicios autoevaluativos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menureportes.add(jMenuItem8);

        jMenuBar1.add(menureportes);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lblejercicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblejercicioMouseClicked
        // TODO add your handling code here:
        if (autenticado) {
            ResolverEjercicio dialog = new ResolverEjercicio(Principal.this, true);
            dialog.setVisible(true);
        }
        //dispose();
    }//GEN-LAST:event_lblejercicioMouseClicked

    private void lblanchodelpieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblanchodelpieMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblanchodelpie.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblanchodelpieMouseClicked

    private void lblalcancemaximodelbrazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalcancemaximodelbrazoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalcancemaximodelbrazo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalcancemaximodelbrazoMouseClicked

    private void lblalturasubescapularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturasubescapularMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturasubescapular.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturasubescapularMouseClicked

    private void lblalcancelateraldelbrazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalcancelateraldelbrazoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalcancelateraldelbrazo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalcancelateraldelbrazoMouseClicked

    private void lblalturadelosojossentadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelosojossentadoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelosojossentado.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelosojossentadoMouseClicked

    private void cbpercentilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbpercentilActionPerformed
        // TODO add your handling code here:
        if (cbpercentil.getSelectedItem() != "seleccione" && percentil) {
            creaExpresion((String) cbpercentil.getSelectedItem());
            actualizaOperandos();
            creaExpresionNumerica((String) cbpercentil.getSelectedItem());
            muestraResultado();
            lista = false;
            potenc = true;
            //rz=false;
            operadores = true;
            percentil = false;
            dup = false;
            cat = false;
            espesor = false;
            holg = false;
            cbpercentil.setSelectedIndex(0);
            cbpercentil.setEnabled(percentil);
            btncalculo.setEnabled(true);
        }
        //*************************
//        int pos = posicionFilaTabla((String) listadiseno.getSelectedValue());
//        int valor = menorP5(pos);
//        String aux = lbldim1.getText();
//        lbldim1.setText(aux + String.valueOf(valor));
        //**********************************************
//        String aux = lbldim1.getText();
//        lbldim1.setText(aux + String.valueOf(25));
    }//GEN-LAST:event_cbpercentilActionPerformed

    private void listadisenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadisenoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listadisenoMouseClicked

    private void listadisenoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listadisenoValueChanged
        // TODO add your handling code here:
        creaExpresion(((String) listadiseno.getSelectedValue()));//esta expresion logica es para cuando yo tenga toda la formula se la paso al label resultado
        muestraResultado();
        cbpercentil.setEnabled(lista);
        //****************
        lista = false;
        potenc = false;
        //rz=false;
        operadores = false;
        percentil = true;
        dup = false;
        cat = false;
        espesor = false;
        holg = false;
        //*************************
        listadiseno.setEnabled(lista);
        cbtangente.setEnabled(cat);
        //**************************************

    }//GEN-LAST:event_listadisenoValueChanged

    private void btnsumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsumaActionPerformed
        // TODO add your handling code here:
        if (operadores) {
            creaExpresion(btnsuma.getText());
            operador = btnsuma.getText();
            creaExpresionNumerica(btnsuma.getText());
            muestraResultado();
            lista = true;
            listadiseno.setEnabled(lista);
            potenc = false;
            //rz=true;
            operadores = false;
            percentil = false;
            dup = true;
            cat = true;
            cbtangente.setEnabled(cat);
            espesor = true;
            holg = true;
        }
    }//GEN-LAST:event_btnsumaActionPerformed

    private void btnrestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrestaActionPerformed
        // TODO add your handling code here:
        if (operadores) {
            creaExpresion(btnresta.getText());
            operador = btnresta.getText();
            creaExpresionNumerica(btnresta.getText());
            muestraResultado();
            lista = true;
            listadiseno.setEnabled(lista);
            potenc = false;
            //rz=true;
            operadores = false;
            percentil = false;
            dup = true;
            cat = true;
            cbtangente.setEnabled(cat);
            espesor = true;
            holg = true;
        }
    }//GEN-LAST:event_btnrestaActionPerformed

    private void btnmultiplicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmultiplicacionActionPerformed
        // TODO add your handling code here:
        if (operadores) {
            creaExpresion(btnmultiplicacion.getText());
            operador = btnmultiplicacion.getText();
            creaExpresionNumerica(btnmultiplicacion.getText());
            muestraResultado();
            lista = true;
            listadiseno.setEnabled(lista);
            potenc = false;
            //rz=true;
            operadores = false;
            percentil = false;
            dup = true;
            cat = true;
            cbtangente.setEnabled(cat);
            espesor = true;
            holg = true;
        }
    }//GEN-LAST:event_btnmultiplicacionActionPerformed

    private void btndivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndivisionActionPerformed
        // TODO add your handling code here:
        if (operadores) {
            creaExpresion(btndivision.getText());
            operador = btndivision.getText();
            creaExpresionNumerica(btndivision.getText());
            muestraResultado();
            lista = true;
            listadiseno.setEnabled(lista);
            potenc = false;
            //rz=true;
            operadores = false;
            percentil = false;
            dup = true;
            cat = true;
            cbtangente.setEnabled(cat);
            espesor = true;
            holg = true;
        }
    }//GEN-LAST:event_btndivisionActionPerformed

    private void btnduploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnduploActionPerformed
        // TODO add your handling code here:
        if (dup) {
            creaExpresion("2x");
            muestraResultado();
            lista = true;
            potenc = false;
            //rz=false;
            operadores = false;
            percentil = false;
            dup = false;
            multx2 = true;
            cat = false;
            espesor = false;
            holg = false;
        }
    }//GEN-LAST:event_btnduploActionPerformed

    private void btnespesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnespesorActionPerformed
        // TODO add your handling code here:
        if (espesor) {
            creaExpresion(btnespesor.getText());
            actualizaOperandos();
            creaExpresionNumerica(btnespesor.getText());
            muestraResultado();
            lista = false;
            potenc = false;
            //rz=false;
            operadores = true;
            percentil = false;
            dup = false;
            cat = false;
            espesor = false;
            holg = false;
        }
    }//GEN-LAST:event_btnespesorActionPerformed

    private void btnpotenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpotenciaActionPerformed
        // TODO add your handling code here:
        if (potenc) {
            creaExpresion(potencia);
            creaExpresionNumerica(potencia);
            muestraResultado();
            lista = false;
            potenc = false;
            //rz=false;
            operadores = true;
            percentil = false;
            dup = false;
            cat = false;
            espesor = false;
            holg = false;
        }
    }//GEN-LAST:event_btnpotenciaActionPerformed

    //*******************************************************************************************
    private void btnholguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnholguraActionPerformed
        // TODO add your handling code here:
        if (holg) {
            creaExpresion(holgura);
            actualizaOperandos();
            creaExpresionNumerica(holgura);
            muestraResultado();
            lista = false;
            potenc = false;
            //rz=false;
            operadores = true;
            percentil = false;
            dup = false;
            cat = false;
            espesor = false;
            holg = false;
        }
    }//GEN-LAST:event_btnholguraActionPerformed

    private void lblsacropopliteaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsacropopliteaMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblsacropoplitea.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblsacropopliteaMouseClicked

    private void lblalturasacrorotulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturasacrorotulaMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturasacrorotula.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturasacrorotulaMouseClicked

    private void lbllongituddelmusloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllongituddelmusloMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lbllongituddelmuslo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lbllongituddelmusloMouseClicked

    private void lblalturadelmusloxxxxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelmusloxxxxMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelmusloxxxx.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelmusloxxxxMouseClicked

    private void lblanchodecodoacodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblanchodecodoacodoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblanchodecodoacodo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblanchodecodoacodoMouseClicked

    private void lblalturailiocrestalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturailiocrestalMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblanchodecodoacodo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturailiocrestalMouseClicked

    private void lblalturadelosojosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelosojosMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelosojos.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelosojosMouseClicked

    private void lblalturadelcodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelcodoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelcodo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelcodoMouseClicked

    private void lblestaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblestaturaMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblestatura.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblestaturaMouseClicked

    private void lbldiametrobiacromialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldiametrobiacromialMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lbldiametrobiacromial.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lbldiametrobiacromialMouseClicked

    private void lblalcanceminimodelbrazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalcanceminimodelbrazoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalcanceminimodelbrazo.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalcanceminimodelbrazoMouseClicked

    private void lblalturadelarodillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelarodillaMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelarodilla.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelarodillaMouseClicked

    private void lblalturadelcodosentadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadelcodosentadoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadelcodosentado.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadelcodosentadoMouseClicked

    private void lblalturadeojos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturadeojos1MouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturadeojos1.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturadeojos1MouseClicked

    private void lblalturapopliteaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturapopliteaMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturapoplitea.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturapopliteaMouseClicked

    private void lblanchodecaderasentadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblanchodecaderasentadoMouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblanchodecaderasentado.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblanchodecaderasentadoMouseClicked

    private void lblalturailiocrestal11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblalturailiocrestal11MouseClicked
        // TODO add your handling code here:
        TeoriaGeneral.seleccion = lblalturailiocrestal11.getText();
        TeoriaGeneral aux = new TeoriaGeneral();
        aux.setVisible(true);
    }//GEN-LAST:event_lblalturailiocrestal11MouseClicked

    //DIMENSIONES RELEVANTES DE PUESTO DE TRABAJO
    private void lbldr2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr2MouseClicked

    private void lbldr3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr3MouseClicked

    private void lbldr4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr4MouseClicked

    private void lbldr5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr5MouseClicked

    private void lbldr6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr6MouseClicked

    private void lbldr7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr7MouseClicked

    private void lbldr8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr8MouseClicked

    private void lbldr9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr9MouseClicked

    private void lbldr10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr10MouseClicked

    private void lbldr11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr11MouseClicked

    private void lbldr12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr12MouseClicked

    private void lbldr13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr13MouseClicked

    private void lbldr14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr14MouseClicked

    private void lbldr15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr15MouseClicked

    private void lbldr16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr16MouseClicked

    private void lbldr17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr17MouseClicked

    private void lbldr18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr18MouseClicked

    private void lbldr19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldr19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldr19MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        if (!btbsiguienteexp.isEnabled()) {
            try {
                if (jLabel10.isEnabled()) {
                    posiciondimensionseleccionado = controlposicionesdimensiones.coordenadasxPosiciones(dimension, sujetoseleccionado.getIdsujeto(), idposcdesc3);
                    imag = new Imagen(fpostura3);
                    panelimagen.removeAll();//esto me ayuda a quitarle todo lo que tiene el panel para pasarle un panel nuevo con imagen
                    imag.setSize(panelimagen.getWidth(), panelimagen.getHeight());
                    panelimagen.add(imag);
                    panelimagen.repaint();
                    habilitaPanelPosturas(false);
                    panelimagen.setEnabled(true);
                }

            } catch (NoResultException e) {
                canterrpostura++;
                auxerrorPA.setCantidad(canterrpostura);
                JOptionPane.showMessageDialog(null, "La postura antropométrica escogida no se corresponde con\n la dimensión anteriormente seleccionada");
                TeoriaGeneral.seleccion = dimension;
                TeoriaGeneral aux = new TeoriaGeneral();
                aux.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe cambiar de sujeto");
        }

    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        if (!btbsiguienteexp.isEnabled()) {
            try {
                if (jLabel8.isEnabled()) {
                    posiciondimensionseleccionado = controlposicionesdimensiones.coordenadasxPosiciones(dimension, sujetoseleccionado.getIdsujeto(), idposcdesc1);
                    imag = new Imagen(fpostura1);
                    panelimagen.removeAll();//esto me ayuda a quitarle todo lo que tiene el panel para pasarle un panel nuevo con imagen
                    imag.setSize(panelimagen.getWidth(), panelimagen.getHeight());
                    panelimagen.add(imag);
                    panelimagen.repaint();
                    habilitaPanelPosturas(false);
                    panelimagen.setEnabled(true);
                }
            } catch (NoResultException e) {
                canterrpostura++;
                auxerrorPA.setCantidad(canterrpostura);
                JOptionPane.showMessageDialog(null, "La postura antropométrica escogida no se corresponde con\n la dimensión anteriormente seleccionada");
                TeoriaGeneral.seleccion = dimension;
                TeoriaGeneral aux = new TeoriaGeneral();
                aux.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe cambiar de sujeto");
        }

    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        if (!btbsiguienteexp.isEnabled()) {
            try {
                if (jLabel11.isEnabled()) {
                    posiciondimensionseleccionado = controlposicionesdimensiones.coordenadasxPosiciones(dimension, sujetoseleccionado.getIdsujeto(), idposcdesc4);
                    imag = new Imagen(fpostura4);
                    panelimagen.removeAll();//esto me ayuda a quitarle todo lo que tiene el panel para pasarle un panel nuevo con imagen
                    imag.setSize(panelimagen.getWidth(), panelimagen.getHeight());
                    panelimagen.add(imag);
                    panelimagen.repaint();
                    habilitaPanelPosturas(false);
                    panelimagen.setEnabled(true);
                }

            } catch (NoResultException e) {
                canterrpostura++;
                auxerrorPA.setCantidad(canterrpostura);
                JOptionPane.showMessageDialog(null, "La postura antropométrica escogida no se corresponde con\n la dimensión anteriormente seleccionada");
                TeoriaGeneral.seleccion = dimension;
                TeoriaGeneral aux = new TeoriaGeneral();
                aux.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe cambiar de sujeto");
        }

    }//GEN-LAST:event_jLabel11MouseClicked

    private void btnpasarAcalculardisenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasarAcalculardisenoActionPerformed
        // TODO add your handling code here:
        if (!validaTablaExperimentacion()) {
            panelprincipal.setVisible(false);
            panelcalculo.setVisible(true);
            panelcasoestudio.setVisible(false);
            paneldimensiones.setVisible(false);
            panelexperimentacion.setVisible(false);
            panelpuestos.setVisible(false);
            panelinstrumento.setVisible(true);

            //aqui comienza el codigo uerte de este panel
            llenarComboBoxDimensionesDiseno();
            llenarListaMedidasAntropometricas();
            inahabititaOperadores();
            //llenarComboBoxPosiciones();
            btncalculo.setEnabled(false);
            btnreiniciar.setEnabled(false);
        }
    }//GEN-LAST:event_btnpasarAcalculardisenoActionPerformed

    private void btncomenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomenzarActionPerformed
        // TODO add your handling code here:
        if (!validaVentanaCasoEstudio()) {
            panelcalculo.setVisible(false);
            panelcasoestudio.setVisible(false);
            paneldimensiones.setVisible(true);
            panelexperimentacion.setVisible(false);
            panelprincipal.setVisible(false);
            panelinstrumento.setVisible(true);
            panelpuestos.setVisible(false);
            mniempezar.setEnabled(true);
        }
    }//GEN-LAST:event_btncomenzarActionPerformed

    private void panelimagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelimagenMouseClicked
        // TODO add your handling code here:
//        jTextField1.setText(String.valueOf(evt.getX()));
//        jTextField2.setText(String.valueOf(evt.getY()));
        float y_1 = 0;
        float x_1 = 0;
        float x_2 = 0;
        float y_2 = 0;
        int x;
        int y;
        int valor = 0;
        System.out.println("Dimension seleccionada: " + dimension);
        System.out.println("Posicion del sujeto: " + posiciondimensionseleccionado.getNombDimension());
        System.out.println("Posicion del sujeto: " + posiciondimensionseleccionado.getIdsujetoPosiciones().getIdsujeto());
        System.out.println("Posicion del sujeto: " + posiciondimensionseleccionado.getIdsujetoPosiciones().getIdsujetoPosiciones());
        //*********************************************************************************
        if (clickcount == 0 && panelimagen.isEnabled()) {
            x = evt.getX();
            y = evt.getY();
            //Arreglar no puedo poner el numero estatico
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
            //dimensionescoordenadaslist = sujetoseleccionado.getSujetoPosicionesList().get(2).getPosicionesDimensionesList();

//*******************************************************************************
//            for (PosicionesDimensiones dimensionescoordenadaslist1 : dimensionescoordenadaslist) {
//                if (dimensionescoordenadaslist1.getNombDimension().equalsIgnoreCase(dimension)) {
//                    posiciondimensionseleccionado = dimensionescoordenadaslist1;
//                    x_1 = dimensionescoordenadaslist1.getPosX1();
//                    y_1 = dimensionescoordenadaslist1.getPosY1();
//                    break;
//                }   
//            }
            x_1 = posiciondimensionseleccionado.getPosX1();
            y_1 = posiciondimensionseleccionado.getPosY1();
            System.out.println("X_1: " + x_1);
            System.out.println("Y_1: " + y_1);
            if (((x_1 + 12) < x || x < (x_1 - 12) || (y_1 + 12) < y || y < (y_1 - 12)) && clickcount == 0) {
                canterrpuntos++;
                auxerrorPu.setCantidad(anterior);
                Object[] options = {"Si", "No"};
                int action = JOptionPane.showOptionDialog(this, "Usted ha seleccionado un punto erróneo.\n¿Desea consultar la teoria referente a esta dimensión?", "Información",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (action == JOptionPane.YES_OPTION) {
                    TeoriaGeneral.seleccion = dimension;
                    TeoriaGeneral aux = new TeoriaGeneral();
                    aux.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor vuelva a escoger un punto en la imagen");
                }
            } else {
                imag.posicionX = evt.getX();
                imag.posicionY = evt.getY();
                clickcount++;
                imag.count++;
                imag.repaint();
                JOptionPane.showMessageDialog(null, "Seleccione el otro punto para completar la dimensión");
            }
        } //**************************************************************************
        else {
            //if ( clickcount == 1) {
            x_2 = posiciondimensionseleccionado.getPosX2();
            y_2 = posiciondimensionseleccionado.getPosY2();
            valor = posiciondimensionseleccionado.getValor();
            x = evt.getX();
            y = evt.getY();
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
            System.out.println("X_2: " + x_2);
            System.out.println("Y_2: " + y_2);
            if ((x_2 + 12) < x || x < (x_2 - 12) || (y_2 + 12) < y || y < (y_2 - 12)) {
                Object[] options = {"Si", "No"};
                int action = JOptionPane.showOptionDialog(this, "Usted ha seleccionado un punto erróneo.\n¿Desea consultar la teoria referente a esta dimensión?", "Información",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                //System.out.println("yo soy x_2 : "+x_2+"\n yo soy y_2 : "+y_2);
                if (action == JOptionPane.YES_OPTION) {
                    TeoriaGeneral.seleccion = dimension;
                    TeoriaGeneral aux = new TeoriaGeneral();
                    aux.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor vuelva ha escoger un punto en la imagen");
                }
            } else {

//                GestionPosturas usuario=new GestionPosturas();
//                usuario.setNomb_dimension((String) model.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn()));
//                usuario.seId_Sujeto(sujetoseleccionado.getIdsujeto());
//                usuario.setValorDimension(valor);
//                gestion_posturas.add(usuario);
                imag.posicionX = evt.getX();
                imag.posicionY = evt.getY();
                clickcount = 0;
                imag.count++;
                imag.repaint();
                JOptionPane.showMessageDialog(null, "Usted a seleccionado los puntos de la dimensión correctamente");
                modeltabla.setValueAt(valor, tabladimension.getSelectedRow(), tabladimension.getSelectedColumn());
                posiciondimensionseleccionado = null;
                imag.repaint();
                tabladimension.clearSelection();
                panelpuestos.setEnabled(true);
                panelimagen.setEnabled(false);
                habilitaPanelInstrumentos(true);
                habilitarLabelPuestoTrabajo();
                lblpuestotrabajo.setIcon(null);
                panelimagen.removeAll();
                panelimagen.add(jLabel5);
                validaColumnaBoton();

            }
            //}

        }
    }//GEN-LAST:event_panelimagenMouseClicked

    private void lbltrabajopcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltrabajopcMouseClicked
        // TODO add your handling code here:
        if (lbltrabajopc.isEnabled()) {
            lblfce = false;
            lbloav = false;
            lblmi = false;
            lblp = false;
            lblsbr = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lbltrabajopcMouseClicked

    private void lblagenciaviajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblagenciaviajesMouseClicked
        // TODO add your handling code here:
        if (lblagenciaviajes.isEnabled()) {
            lbloav = true;
            lblfce = false;
            lblmi = false;
            lblp = false;
            lblsbr = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lblagenciaviajesMouseClicked

    private void lblfabricacomponentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblfabricacomponentesMouseClicked
        // TODO add your handling code here:
        if (lblfabricacomponentes.isEnabled()) {
            lblfce = true;
            lbloav = false;
            lblmi = false;
            lblp = false;
            lblsbr = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lblfabricacomponentesMouseClicked

    private void lblmaquinaimpresionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmaquinaimpresionMouseClicked
        // TODO add your handling code here:
        if (lblmaquinaimpresion.isEnabled()) {
            lblmi = true;
            lbloav = false;
            lblfce = false;
            lblp = false;
            lblsbr = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lblmaquinaimpresionMouseClicked

    private void lblpupitreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpupitreMouseClicked
        // TODO add your handling code here:
        if (lblpupitre.isEnabled()) {
            lblp = true;
            lbloav = false;
            lblfce = false;
            lblmi = false;
            lblsbr = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lblpupitreMouseClicked

    private void lblsillaburoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsillaburoMouseClicked
        // TODO add your handling code here:
        if (lblsillaburo.isEnabled()) {
            lblsbr = true;
            lbloav = false;
            lblfce = false;
            lblmi = false;
            lblp = false;
            inicializaComponentes();
            tabladimension.setEnabled(true);
            sptabladimensiones.setEnabled(true);
            jScrollPane1.setEnabled(true);
            habilitaPanelInstrumentos(false);
        }

    }//GEN-LAST:event_lblsillaburoMouseClicked

    private void jcbmuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbmuestraActionPerformed
        // TODO add your handling code here:
        try {
            if (!jcbmuestra.getSelectedItem().equals("Seleccione")) {
                muestra = (int) jcbmuestra.getSelectedItem();
                System.out.println(muestra + " Son los sujetos");
                modeltablamuestra = (DefaultTableModel) tablamuestra.getModel();
                modeltablamuestra.setNumRows(0);
                llenarTablaMuestra();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un tamaño de muestra");
        }

    }//GEN-LAST:event_jcbmuestraActionPerformed

    private void panelimagenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelimagenMouseEntered
        // TODO add your handling code here:
//        jTextField1.setText(String.valueOf(evt.getX()));
//        jTextField2.setText(String.valueOf(evt.getY()));
    }//GEN-LAST:event_panelimagenMouseEntered

    private void panelimagenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelimagenMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_panelimagenMouseExited

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mniempezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniempezarActionPerformed
        // TODO add your handling code here:
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(false);
        paneldimensiones.setVisible(false);
        panelexperimentacion.setVisible(false);
        panelprincipal.setVisible(true);
        panelinstrumento.setVisible(true);
        panelpuestos.setVisible(false);
        clickpuestotrabajo = false;
        modeloexp = null;
        habilitarLabelInstrumento();
        lblinstrumentos.setIcon(fotoprincipal);
    }//GEN-LAST:event_mniempezarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        Control.cerrarApp();
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnautenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnautenticarActionPerformed
        // TODO add your handling code here:
        if (btnautenticar.getName().equals("autentificar")) {
            AutentificacionInicialVisual a = new AutentificacionInicialVisual();
            a.setVisible(true);

            if (usuarioAutentificado != null) {
                btnautenticar.setName("cerrar");
                btnautenticar.setText("Cerrar sesión");

                validaMenu();
            }

        } else {
            btnautenticar.setName("autentificar");
            btnautenticar.setText("Iniciar sesión");
            jLabel4.setEnabled(false);
            habilitaCasosdeEstudio(false);
            if (usuarioAutentificado != null) {
                usuarioAutentificado = null;
                menureportes.setEnabled(false);
                menugestion.setEnabled(false);
                mniempezar.setEnabled(false);
                menuitemcasoestud.setEnabled(false);
                menuitemejercicios.setEnabled(false);
                menuevaluacion.setEnabled(false);
                menuregistro.setEnabled(false);
//                panelmonbinstrumento.setEnabled(false);
//                panelpuestotrabajo.setEnabled(false);
//                panelpuestos.setEnabled(false);
            }
        }


    }//GEN-LAST:event_btnautenticarActionPerformed

    private boolean comprueba() {
        boolean result = false;
        List<DimensionesRelevantes> lista = modeloexp.getDimensionesRelevantesList();
        for (DimensionesRelevantes lista1 : lista) {
            //System.out.println("Las dimensiones son:  " + (String)cbdimrelv.getSelectedItem() +"  esta es la bd     " +lista1.getNombDimension()+"\n");
            if (lista1.getNombDimension().equals((String) cbdimrelv.getSelectedItem())) {
                System.out.println("***La dimension relevante esta en el modelo***");
                //int pos = lista.indexOf(cbdimrelv.getSelectedItem());
                DimensionesRelevantes dr = lista1;//lista.get(pos);
                String auxexp = controlformula.MostrarFR(dr.getIddimensionesRelevantes()).getFormulacion();
                ArrayList<String> auxlist = guardaPartes(expresionlogica);
                result = igualExpresion(auxlist, auxexp);
                JOptionPane.showMessageDialog(null, "Esta es la formula de la base de datos" + result);
            }
        }
        return result;
    }

    public ArrayList<String> guardaPartes(String valor) {
        ArrayList<String> element = new ArrayList();
        String auxvalor = valor;
        int indice = -1;
        while (posicionOperador(auxvalor) != -1) {
            int auxpos = posicionOperador(auxvalor);
            element.add(auxvalor.substring(indice + 1, auxpos));
            auxvalor = auxvalor.substring(auxpos + 1);
        }
        element.add(auxvalor);
        return element;
    }

    public boolean igualExpresion(ArrayList<String> list, String valor) {
        /**
         * list es lo que escribe el estudiante comprueba lo escrito pore el
         * estudiante con la base de datos
         */
        boolean result = true;
        String auxvalor = valor;
        int indice = -1;
        while (posicionOperador(auxvalor) != -1) {
            int auxpos = posicionOperador(auxvalor);
            if (!list.contains(auxvalor.substring(indice + 1, auxpos))) {
                result = false;
                break;
            }
            auxvalor = auxvalor.substring(auxpos + 1);
        }
        if (!list.contains(auxvalor)) {
            result = false;
        }
        return result;
    }

    private void btncalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalculoActionPerformed
        // TODO add your handling code here:
        comprueba();
        calculoExpresionNumerica();
        String aux = lbldim1.getText();
        lbldim1.setText(aux + "= " + resultado);
        Object[] options = {"Si", "No"};
        int action = JOptionPane.showOptionDialog(null, "Desea registrar el calculo realizado", "Información",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (action == JOptionPane.YES_OPTION) {
            listamodelcalculo.addElement(cbdimrelv.getSelectedItem() + ": " + expresionlogica);
            jList1.setModel(listamodelcalculo);
        }
        cleanPanelCalculo();
        btncalculo.setEnabled(false);
    }//GEN-LAST:event_btncalculoActionPerformed

    private void btnreiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreiniciarActionPerformed
        // TODO add your handling code here:
        cleanPanelCalculo();
        listadiseno.setEnabled(false);
        //**************************************
    }//GEN-LAST:event_btnreiniciarActionPerformed

    private void cbtangenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtangenteActionPerformed
        // TODO add your handling code here:
        if (cat && cbtangente.getSelectedItem() != "tan") {
            creaExpresion("tan" + cbtangente.getSelectedItem());
            actualizaOperandos();
            creaExpresionNumerica("tan");
            muestraResultado();
            lista = false;
            potenc = false;
            //rz=false;
            operadores = true;
            percentil = false;
            dup = false;
            cat = false;
            cbtangente.setSelectedIndex(0);
            cbtangente.setEnabled(cat);
            espesor = false;
            holg = false;
            listadiseno.setEnabled(lista);
        }

    }//GEN-LAST:event_cbtangenteActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(true);
        paneldimensiones.setVisible(false);
        panelexperimentacion.setVisible(false);
        panelprincipal.setVisible(false);
        panelinstrumento.setVisible(true);
        panelpuestos.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnatrasExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatrasExpActionPerformed
        // TODO add your handling code here:
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(false);
        paneldimensiones.setVisible(true);
        panelexperimentacion.setVisible(false);
        panelprincipal.setVisible(false);
        panelinstrumento.setVisible(true);
        panelpuestos.setVisible(false);
    }//GEN-LAST:event_btnatrasExpActionPerformed

    private void btnatrascalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatrascalculoActionPerformed
        // TODO add your handling code here:
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(false);
        paneldimensiones.setVisible(false);
        panelexperimentacion.setVisible(true);
        panelprincipal.setVisible(false);
        panelinstrumento.setVisible(false);
        panelposturas.setVisible(true);
    }//GEN-LAST:event_btnatrascalculoActionPerformed

    private void btnfincalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfincalculoActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Si", "No"};
//        int action = JOptionPane.showOptionDialog(Principal.this, "Desea generar un reporte con los pasos realizados\n para dar solución al caso de estudio seleccionado", "Información",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
//        if (action == JOptionPane.YES_OPTION) {
//            //aqui va reporte de alfonso
//        }
        try {
            RespuestaExperimentacion nueva = new RespuestaExperimentacion();
            nueva.setErrorList(errores);
            nueva.setExperimentacionidExperimentacion(modeloexp);
            nueva.setUsuarioidusuario(usuarioAutentificado);
            controlrespt.create(nueva);
            auxerrorExp.setRespuestaExperimentacionid(nueva);
            auxerrorCE.setRespuestaExperimentacionid(nueva);
            auxerrorPA.setRespuestaExperimentacionid(nueva);
            auxerrorPu.setRespuestaExperimentacionid(nueva);
            auxerrorExp.setDescripcion("El nstrumento seleccionado no es el adecuado");
            auxerrorPA.setDescripcion("La postura antropometrica seleccionada no es la correcta");
            auxerrorPu.setDescripcion("El punto seleccionado no pertenece a la postura identificada");
            controlerror.create(auxerrorCE);
            controlerror.create(auxerrorExp);
            controlerror.create(auxerrorPA);
            controlerror.create(auxerrorPu);
            errores.add(auxerrorCE);
            errores.add(auxerrorExp);
            errores.add(auxerrorPA);
            errores.add(auxerrorPu);
        } catch (Exception e) {

        }

        int action1 = JOptionPane.showOptionDialog(Principal.this, "Desea condultar a través de un reporte la respuesta correcta de caso de estudio en cuestièn ", "Información",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (action1 == JOptionPane.YES_OPTION) {
            ReportesController d = new ReportesController();
            //int s = Integer.parseInt(JOptionPane.showInputDialog("Entre el Caso de Estudio"));
            d.mostrarReporteInformeReal(modeloexp.getIdExperimentacion());
        }
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(false);
        paneldimensiones.setVisible(false);
        panelexperimentacion.setVisible(false);
        panelprincipal.setVisible(true);
        btnsalir.setVisible(true);
        panelinstrumento.setVisible(true);
        lblinstrumentos.setIcon(fotoprincipal);
        panelposturas.setVisible(false);
        habilitarLabelInstrumento();
        InicializaAllComponentes();
        clickpuestotrabajo = true;
    }//GEN-LAST:event_btnfincalculoActionPerformed

    private void tablamuestraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablamuestraMouseClicked
        // TODO add your handling code here:
        tablamuestra.clearSelection();
        System.out.println("di click");
    }//GEN-LAST:event_tablamuestraMouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        RegistroAdministrador a = new RegistroAdministrador(new javax.swing.JFrame(), true);
        a.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void menuitemejerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemejerciciosActionPerformed
        // TODO add your handling code here:
        GestionarEjercicios a = new GestionarEjercicios(new javax.swing.JFrame(), true);
        a.setVisible(true);
    }//GEN-LAST:event_menuitemejerciciosActionPerformed

    private void menuitemcasoestudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemcasoestudActionPerformed
        // TODO add your handling code here:
        casoestudio a = new casoestudio(new javax.swing.JFrame(), true);
        a.setVisible(true);
    }//GEN-LAST:event_menuitemcasoestudActionPerformed

    private void menuitemtrazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemtrazaActionPerformed
        // TODO add your handling code here:
        TrazasVisual a = new TrazasVisual(Principal.this, true);
        a.setVisible(true);
    }//GEN-LAST:event_menuitemtrazaActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if (!AutentificacionInicialVisual.usuarioAutentificado.getRespuestaExperimentacionList().isEmpty()
                && AutentificacionInicialVisual.usuarioAutentificado.getRolIdrol().getNombRol().equals("Estudiante")) {

            Evaluacion a = new Evaluacion(Principal.this, true);
            a.setVisible(true);
        } else if (AutentificacionInicialVisual.usuarioAutentificado.getRolIdrol().getNombRol().equals("Profesor")) {
            Evaluacion a = new Evaluacion(Principal.this, true);
            a.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void tabladimensionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladimensionMouseClicked
        // TODO add your handling code here:
        auxerrorExp.setCantidad(0);
        if (tabladimension.isEnabled()) {
            Object obj = modeltabla.getValueAt(tabladimension.getSelectedRow(), tabladimension.getSelectedColumn());//para saber si la celda esta vacia
            System.out.println("este es la celda que selecciono" + obj);
            if (tabladimension.getSelectedColumn() != 0 && obj == null) {
                dimension = (String) modeltabla.getValueAt(tabladimension.getSelectedRow(), 0);
                modelodimensiones = controldimensiones.Mostrar(dimension);
                if (modelodimensiones != null) {
                    if (!modelodimensiones.getIdinstrumento().getNombInstrumento().equals(modeloInstrumento.getNombInstrumento()) && retroalimentacion_veces < 3) {

                        retroalimentacion_veces++;
                        auxerrorExp.setCantidad(auxerrorExp.getCantidad() + retroalimentacion_veces);
                        lblpuestotrabajo.setIcon(null);
                        habilitaPanelInstrumentos(true);
                        habilitarLabelPuestoTrabajo();
                        tabladimension.clearSelection();
                        sptabladimensiones.setEnabled(false);
                        jScrollPane1.setEnabled(false);
                        tabladimension.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "El instrumento seleccionado no es el adecuado para la toma de la dimensión correspondiente");
                        TeoriaGeneral.seleccion = dimension;
                        TeoriaGeneral aux = new TeoriaGeneral();
                        aux.setVisible(true);
                    } else {
                        retroalimentacion_veces = 0;
                        habilitaPanelInstrumentos(false);
                        sptabladimensiones.setEnabled(false);
                        tabladimension.setEnabled(false);
                        habilitaPanelPosturas(true);
                        JOptionPane.showMessageDialog(null, "Escoja una de las posturas mostradas y señale en ella la dimensión seleccionada");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La dimensión tomada no se encontró en la base de datos");
                }
                //imagenesEnables(true);
                //System.out.println("esta es la dimension" + dimension);

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una casilla vacía ");
            }
        }

    }//GEN-LAST:event_tabladimensionMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ReportesController d = new ReportesController();
        d.mostrarReporteCasos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        ReportesController d = new ReportesController();
        d.mostrarReporteEjercicioAutoevaluativos();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void btncomenzar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomenzar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncomenzar1ActionPerformed

    private void btnpasarAexperimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpasarAexperimentacionActionPerformed
        // TODO add your handling code here:
        if (!validaVentanaDimensiones()) {
            llenaListaDimensionesAntropometricas();
            llenaListaDimensionesDiseno();
            panelcalculo.setVisible(false);
            panelcasoestudio.setVisible(false);
            paneldimensiones.setVisible(false);
            panelexperimentacion.setVisible(true);
            panelprincipal.setVisible(false);
            panelinstrumento.setVisible(false);
            panelpuestos.setVisible(true);

            //**************************************comienza el codigo para tomar las mediciones
            sujeto_aleatorio = comienzoAleatorio(controlsujeto.getSujetoCount() - 1);
            creaTabla();
            //llenaPanelInstrumento();
            llenarImagenes(sujeto_aleatorio);
            listaexpresiondiseno = new ArrayList<String>();//pertenece al panel calculo
            listamodelcalculo = new DefaultListModel();
            //if (muestra > 1) {
            btbsiguienteexp.setEnabled(false);
            panelimagen.removeAll();
            panelimagen.add(jLabel5);
            //}

            panelimagen.setEnabled(false);
            habilitaPanelPosturas(false);//me desaltiva el panel posturas
            habilitaPanelInstrumentos(true);
            lblpuestotrabajo.setIcon(null);
            tabladimension.setEnabled(false);
            sptabladimensiones.setEnabled(false);
            jScrollPane1.setEnabled(false);

        }
    }//GEN-LAST:event_btnpasarAexperimentacionActionPerformed

    private void cbdimrelvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdimrelvActionPerformed
        // TODO add your handling code here:
        if (cbdimrelv.getSelectedItem() != "Seleccione") {
            dimensR = true;
            lista = true;
            listadiseno.setEnabled(dimensR);
            cbdimrelv.setEnabled(false);
            btnreiniciar.setEnabled(true);
        }
    }//GEN-LAST:event_cbdimrelvActionPerformed

    private void btncomenzar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomenzar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncomenzar2ActionPerformed

    private void btbsiguienteexpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbsiguienteexpActionPerformed
        // TODO add your handling code here:
        panelimagen.removeAll();
        panelimagen.add(jLabel5);
        panelimagen.repaint();
        anteriorImagen();
    }//GEN-LAST:event_btbsiguienteexpActionPerformed

    private void btncomenzar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomenzar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncomenzar3ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        if (!btbsiguienteexp.isEnabled()) {
            try {
                if (jLabel10.isEnabled()) {
                    posiciondimensionseleccionado = controlposicionesdimensiones.coordenadasxPosiciones(dimension, sujetoseleccionado.getIdsujeto(), idposcdesc2);
                    imag = new Imagen(fpostura2);
                    panelimagen.removeAll();//esto me ayuda a quitarle todo lo que tiene el panel para pasarle un panel nuevo con imagen
                    imag.setSize(panelimagen.getWidth(), panelimagen.getHeight());
                    panelimagen.add(imag);
                    panelimagen.repaint();
                    habilitaPanelPosturas(false);
                    panelimagen.setEnabled(true);
                }

            } catch (NoResultException e) {
                canterrpostura++;
                auxerrorPA.setCantidad(canterrpostura);
                JOptionPane.showMessageDialog(null, "La postura antropométrica escogida no se corresponde con\n la dimensión anteriormente seleccionada");
                TeoriaGeneral.seleccion = dimension;
                TeoriaGeneral aux = new TeoriaGeneral();
                aux.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe cambiar de sujeto");
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        panelcalculo.setVisible(false);
        panelcasoestudio.setVisible(false);
        paneldimensiones.setVisible(false);
        panelexperimentacion.setVisible(false);
        panelprincipal.setVisible(true);
        btnsalir.setVisible(true);
        panelinstrumento.setVisible(true);
        lblinstrumentos.setIcon(fotoprincipal);
        panelposturas.setVisible(false);
        habilitarLabelInstrumento();
        InicializaAllComponentes();
        clickpuestotrabajo = true;
    }//GEN-LAST:event_jMenu1ActionPerformed

    //*******************************************FUNCIONES AGREGADAS DE LAS OTRAS CLASES
    private void creaExpresion(String valor) {
        String expresionauxiliar = expresionlogica;
        expresionlogica = expresionauxiliar + valor;
    }

    private void creaExpresionNumerica(String valor) {
        String expresionauxiliar = expresionnumerica;
        double valoraux = 0;
        switch (valor) {
            case "P(5)": {
                int pos = posicionFilaTabla((String) listadiseno.getSelectedValue());
                valoraux = menorP5(pos);
                if (multx2) {
                    valoraux = 2 * valoraux;
                    multx2 = false;
                }
                asignaOperadores(valoraux);
                //System.out.println("***********"+ valoraux);
                //String aux = lbldim1.getText();
                expresionnumerica = expresionauxiliar + String.valueOf(valoraux);
                //System.out.println("Expresion numerica**:"+expresionnumerica);
                //lbldim1.setText(expresionnumerica);
                break;
            }
            case "P(95)": {
                int pos = posicionFilaTabla((String) listadiseno.getSelectedValue());
                valoraux = menorP95(pos);
                if (multx2) {
                    valoraux = 2 * valoraux;
                    multx2 = false;
                }
                asignaOperadores(valoraux);
                //String aux = lbldim1.getText();
                expresionnumerica = expresionauxiliar + String.valueOf(valoraux);
                //System.out.println("Expresion numerica**:"+expresionnumerica);
                //lbldim1.setText(expresionnumerica);
                break;
            }
            case "Espesor": {
                valoraux = espc;
                System.out.println("----Yo soy :" + valoraux + "\n");
                asignaOperadores(valoraux);
                System.out.println("----Yo soy operador2 :" + operando2 + "\n");
                expresionnumerica = expresionauxiliar + String.valueOf(valoraux);
                System.out.println("----Yo soy :" + expresionnumerica + "\n");
                break;
            }
            case "holgura": {
                valoraux = holgc;
                asignaOperadores(valoraux);
                expresionnumerica = expresionauxiliar + String.valueOf(valoraux);
                break;
            }
            case "^2": {
                //expresionnumerica="";
                expresionnumerica = arreglaExpresion(expresionauxiliar);
                break;
            }
            case "tan": {
                double auxtan = Double.valueOf((String) cbtangente.getSelectedItem());
                System.out.println("el valor de tan es: " + auxtan);
                valoraux = tangenteNumero(auxtan);
                asignaOperadores(valoraux);
                expresionnumerica = expresionauxiliar + String.valueOf(valoraux);
                break;
            }
            default: {
                //System.out.println("Soy el default**:");
                expresionnumerica = expresionauxiliar + valor;
            }
        }
        //expresionnumerica = expresionauxiliar + valor;
        //lbldim1.setText(expresionnumerica);
    }

    private void asignaOperadores(double valor) {
        if (operando1 == 0) {
            operando1 = valor;
        } else {
            operando2 = valor;
        }
    }

    private double tangenteNumero(double valor) {
        return Math.tan(valor);
    }

    private double potenciaNumero() {
        double valor = 0;
        if (operando2 != 0) {
            operando2 = Math.pow(operando2, 2);
            valor = operando2;
        } else {
            operando1 = Math.pow(operando1, 2);
            valor = operando1;
        }
        resultado = valor;
        return valor;
    }

    private void actualizaOperandos() {
        if (operando1 != 0 && operando2 != 0) {
            calculoExpresionNumerica();
        }
    }

    private double calculoExpresionNumerica() {
        if (operando2 != 0) {
            switch (operador) {
                case "+": {
                    operando1 = operando1 + operando2;
                    break;
                }
                case "-": {
                    operando1 = operando1 - operando2;
                    break;
                }
                case "*": {
                    operando1 = operando1 * operando2;
                    break;
                }
                default: {
                    try {
                        operando1 = operando1 / operando2;
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No es posible realizar la operación");
                    }
                }
            }
            operando2 = 0;
        }
        resultado = operando1;
        JOptionPane.showMessageDialog(null, "Este es el valor: " + resultado);
        return resultado;
    }

    private void muestraResultado() {
        lblresultado.setText(expresionlogica);
        lbldim1.setText(expresionnumerica);
    }

    private void cleanPanelCalculo() {
        lbldim1.setText("");
        expresionlogica = "";
        expresionnumerica = "";
        lblresultado.setText("");
        operador = "";
        operando1 = 0;
        operando2 = 0;
        resultado = 0;
        inahabititaOperadores();
        cbdimrelv.setSelectedIndex(0);
        //cbposturasantropom.setSelectedIndex(0);
        cbpercentil.setSelectedIndex(0);
        cbdimrelv.setEnabled(true);
        btnreiniciar.setEnabled(false);
        btncalculo.setEnabled(false);
    }

    private String arreglaExpresion(String valor) {
        String auxvalor = "";
        if (posicionOperador(valor) != -1) {
            //System.out.println("Entre en el if \n");
            auxvalor = valor.substring(0, posicionOperador(valor) + 1) + String.valueOf(potenciaNumero());
            //System.out.println("este es el valor: "+auxvalor+"\n");
        } else {
            //System.out.println("Entre en el else \n");
            expresionnumerica = "";
            auxvalor = String.valueOf(potenciaNumero());
            //System.out.println("este es el valor: "+auxvalor+"\n");
        }
        return auxvalor;
    }

//    private boolean contieneOperador(String exp){
//        boolean result=true;
//        if (exp.contains("+")) {
//            result=false;
//        } else if(exp.contains("-")){
//            result=false;
//        }else if(exp.contains("*")){
//            result=false;
//        }else if(exp.contains("/")){
//            result=false;
//        }
//        return !result;
//    }
    public int posicionOperador(String exp) {
        int aux = -1;
        for (int i = 0; i < exp.length(); i++) {
            char auxchar = exp.charAt(exp.length() - i - 1);
            if (auxchar == '+' || auxchar == '-' || auxchar == '/' || auxchar == '*') {
                aux = exp.length() - i - 1;
                break;
            }
        }
        return aux;
    }

    private void inahabititaOperadores() {
        lista = false;
        potenc = false;
        //rz=false;
        operadores = false;
        percentil = false;
        dup = false;
        cat = false;
        espesor = false;
        holg = false;
        cbpercentil.setEnabled(percentil);
        cbtangente.setEnabled(cat);
        listadiseno.setEnabled(lista);
        cbdimrelv.setEnabled(true);
    }

    private void inicializaComponentes() {
        if (lbloav) {
            modeloInstrumento = controlinstrumento.Mostrar(lblagenciaviajes.getText());
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else if (lblfce) {
            modeloInstrumento = controlinstrumento.Mostrar(lblfabricacomponentes.getText());
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else if (lblmi) {
            modeloInstrumento = controlinstrumento.Mostrar(lblmaquinaimpresion.getText());
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else if (lblp) {
            modeloInstrumento = controlinstrumento.Mostrar(lblpupitre.getText());
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else if (lblsbr) {
            modeloInstrumento = controlinstrumento.Mostrar(lblsillaburo.getText());
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        } else {
            modeloInstrumento = controlinstrumento.Mostrar(lbltrabajopc.getText());
            lbltrabajopc.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            muestraImagen();
            lblagenciaviajes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblfabricacomponentes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblmaquinaimpresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblpupitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lblsillaburo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        }
    }

    private void muestraImagen() {
//        Image foto=getToolkit().getImage(getClass().getResource(src));
        foto = new ToolkitImage(new ByteArrayImageSource(modeloInstrumento.getFotoInstrumento()));
        foto = foto.getScaledInstance(lblpuestotrabajo.getWidth(), lblpuestotrabajo.getHeight(), Image.SCALE_DEFAULT);
        lblpuestotrabajo.setIcon(new ImageIcon(foto));
        foto = null;
    }
    //******************************************

    //******************************************
    private void llenarSujetosComboBox() {
        modelmuestra = (DefaultComboBoxModel) jcbmuestra.getModel();
        modelmuestra.removeAllElements();
        List<Sujeto> list = controlsujeto.findSujetoEntities();
        modelmuestra.addElement("Seleccione");
        for (int i = 0; i < list.size(); i++) {
            modelmuestra.addElement(i + 1);
        }
        jcbmuestra.setModel(modelmuestra);
    }

    private boolean validaVentanaCasoEstudio() {
        boolean pasar = false;

        if (jcbmuestra.getSelectedItem().equals("Seleccione")) {
            pasar = true;
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un tamaño de muestra");
        } else {
            pasar = validaTablaCasoEstudio();
        }
        errorePanelCE();
        return pasar;
    }

    private boolean validaTablaCasoEstudio() {
        ppt = new ArrayList<>();
        modeltablamuestra = (DefaultTableModel) tablamuestra.getModel();
        boolean auxiliar = false;
        int j = 1;
        if (modeltablamuestra.getRowCount() < 1) {
            auxiliar = true;
            JOptionPane.showMessageDialog(null, " La tabla debe contener como mínimo una fila");
        } else {
            for (int i = 0; i < modeltablamuestra.getRowCount(); i++) {
                if (modeltablamuestra.getValueAt(i, j) == null) {
                    auxiliar = true;
                } else {
                    ppt.add((String) modeltablamuestra.getValueAt(i, j));
                }

            }
            if (auxiliar) {
                JOptionPane.showMessageDialog(null, "Por favor debe llenar los espacios en blancos de la tabla");
            }
        }
        return auxiliar;
    }

    private boolean validaVentanaDimensiones() {
        boolean dhantropometricas = false;
        boolean drdiseno = false;
        boolean todoseleccionado = true;
        Component[] componente = panelda.getComponents();
        for (Component component : componente) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    dhantropometricas = true;
                    break;
                }
            }
        }
        Component[] auxiliar = paneldr.getComponents();
        for (Component component : auxiliar) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    drdiseno = true;
                    break;
                }
            }
        }
        if (!dhantropometricas) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar las dimensiones humanas necesarias");
        } else if (!drdiseno) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar las dimensiones relevantes del puesto de trabajo necesarias");
        }
        if (dhantropometricas && drdiseno) {
            todoseleccionado = false;
        }
        return todoseleccionado;
    }

    private void llenarComboBoxDimensionesDiseno() {
        modelodimensionesdiseno = (DefaultComboBoxModel) cbdimrelv.getModel();
        modelodimensionesdiseno.removeAllElements();
        modelodimensionesdiseno.addElement("seleccione");
        for (String obj : listadimensionesdiseno) {
            modelodimensionesdiseno.addElement(obj);
        }
    }

    private boolean validaTablaExperimentacion() {
        boolean auxiliar = false;
        int j = 1;
        for (int i = 1; i < tabladimension.getRowCount(); i++) {
            for (int k = 1; k < tabladimension.getColumnCount(); k++) {
                if (modeltabla.getValueAt(i, k) == null) {
                    JOptionPane.showMessageDialog(null, "Por favor debe calcular el valor de todas las dimensiones que aparecen en la tabla");
                    auxiliar = true;
                    return auxiliar;
                }
            }

        }
        return auxiliar;
    }

    private void llenaPosturasComboBox() {
        /* modelposturas=(DefaultComboBoxModel) cbposturasantropom.getModel();
         List<Posturas> list=controlpostura.findPosturasEntities();
         modelposturas.addElement("seleccione");
         for (Posturas posturas : list) {
         modelposturas.addElement(posturas.getNombPostura());
         }*/
    }

    private int comienzoAleatorio(int tamanolista) {
        Random rand = new Random();
        //int n =10;//aqui va el tamaño de la lista menos uno para que no se vaya de rango
        int i = rand.nextInt(tamanolista + 1);
        return i;
    }

    private boolean valitaTablaDimensionesColumna() {
        boolean valor = false;
        modeltabla = (DefaultTableModel) tabladimension.getModel();

        return valor;
    }

    private boolean validaPanelDimensiones() {
        boolean dhantropometricas = false;
        boolean drdiseno = false;
        boolean todoseleccionado = true;

        Component[] componente = paneldr.getComponents();
        for (Component component : componente) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    dhantropometricas = true;
                }
            }
        }
        Component[] auxiliar = panelda.getComponents();
        for (Component component : auxiliar) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    drdiseno = true;
                }
            }
        }
        if (jcbmuestra.getSelectedItem().equals("seleccione")) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un tamaño de muestra");
        } else if (!dhantropometricas) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar las dimensiones humanas necesarias");
        } else if (!drdiseno) {
            JOptionPane.showMessageDialog(null, "Por favor debe seleccionar las dimensiones relevantes necesarias");
        }
        if (dhantropometricas && drdiseno && !jcbmuestra.getSelectedItem().equals("seleccione")) {
            todoseleccionado = false;//si lo niego es que puedo avanzar a la siguiente interfaz
        }
        return todoseleccionado;
    }//se elimina

    //HAY UNO QUE NO LE HE HECHO NADA VER SI TENGO QUE QUITARLO y hay uno que esta y no lo cojo
    private void llenaListaDimensionesAntropometricas() {
        listadimensionesantropometricas = new LinkedList<>();
        if (chbestatura.isSelected()) {
            estatura = true;
            listadimensionesantropometricas.add(lblestatura.getText());
        } else {
            estatura = false;
        }
        if (chbalcancelateraldelbrazo.isSelected()) {
            listadimensionesantropometricas.add(lblalcancelateraldelbrazo.getText());
            alcancelateraldelbrazo = true;
        } else {
            alcancelateraldelbrazo = false;
        }
        if (chbalcancemaximodelbrazo.isSelected()) {
            listadimensionesantropometricas.add(lblalcancemaximodelbrazo.getText());
            alcancemaximodelbrazo = true;
        } else {
            alcancemaximodelbrazo = false;
        }
        if (chbalcanceminimodelbrazo.isSelected()) {
            listadimensionesantropometricas.add(lblalcanceminimodelbrazo.getText());
            alcanceminimodelbrazo = true;
        } else {
            alcanceminimodelbrazo = false;
        }
        if (chbalturadelcodo.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelcodo.getText());
            alturadelcodo = true;
        } else {
            alturadelcodo = false;
        }
        if (chbalturadelarodilla.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelarodilla.getText());
            alturadelarodilla = true;
        } else {
            alturadelarodilla = false;
        }
        if (chbalturadelcodosentado.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelcodosentado.getText());
            alturadelcodosentado = true;
        } else {
            alturadelcodosentado = false;
        }
        if (chbalturadelosojossentado.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelosojossentado.getText());
            alturadelosojossentado = true;
        } else {
            alturadelosojossentado = false;
        }
        if (chbalturademuslo.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelmusloxxxx.getText());
            alturadelmuslo = true;
        } else {
            alturadelmuslo = false;
        }
        if (chbalturadelosojos.isSelected()) {
            listadimensionesantropometricas.add(lblalturadelosojos.getText());
            alturadelosojos = true;
        } else {
            alturadelosojos = false;
        }
        if (chbalturailiocrestal.isSelected()) {
            listadimensionesantropometricas.add(lblalturailiocrestal.getText());
            alturailiocrestal = true;
        } else {
            alturailiocrestal = false;
        }
        if (chbalturapoplitea.isSelected()) {
            listadimensionesantropometricas.add(lblalturapoplitea.getText());
            alturapoplitea = true;
        } else {
            alturapoplitea = false;
        }
        if (chbalturasacrorotula.isSelected()) {
            listadimensionesantropometricas.add(lblalturasacrorotula.getText());
            alturasacrorotula = true;
        } else {
            alturasacrorotula = false;
        }
        if (chbalturasubescapular.isSelected()) {
            listadimensionesantropometricas.add(lblalturasubescapular.getText());
            alturasubescapular = true;
        } else {
            alturasubescapular = false;
        }
        if (chbanchodecaderasentado.isSelected()) {
            listadimensionesantropometricas.add(lblanchodecaderasentado.getText());
            anchodelacaderasentado = true;
        } else {
            anchodelacaderasentado = false;
        }
        if (chbanchodecodoacodo.isSelected()) {
            listadimensionesantropometricas.add(lblanchodecodoacodo.getText());
            anchodecodoacodo = true;
        } else {
            anchodecodoacodo = false;
        }
        if (chbanchodelpie.isSelected()) {
            listadimensionesantropometricas.add(lblanchodelpie.getText());
            anchodelpie = true;
        } else {
            anchodelpie = false;
        }
        if (chbdiametrobiacromial.isSelected()) {
            listadimensionesantropometricas.add(lbldiametrobiacromial.getText());
            diametrobiacromial = true;
        } else {
            diametrobiacromial = false;
        }
        if (chblongituddelmuslo.isSelected()) {
            listadimensionesantropometricas.add(lbllongituddelmuslo.getText());
            longituddelmuslo = true;
        } else {
            longituddelmuslo = false;
        }
        if (chbalturadeojos1.isSelected()) {
            listadimensionesantropometricas.add(lblalturadeojos1.getText());
            longituddelmuslo = true;
        } else {
            longituddelmuslo = false;
        }
        if (chbsacropoplitea.isSelected()) {
            listadimensionesantropometricas.add(lblsacropoplitea.getText());
            longituddelmuslo = true;
        } else {
            longituddelmuslo = false;
        }
    }

    //REVIZAR SI CADA ELEMENTO EN QUE SE DA CLICK SE ESTA GUARDANDO EL NOMBRE REAL
    private void llenaListaDimensionesDiseno() {
        //hay que eliminar los else y las otras validaciones de igual a true
        listadimensionesdiseno = new LinkedList<>();
        if (jChBdr1.isSelected()) {
            listadimensionesdiseno.add(lbldr1.getText());
            dr1 = true;
        } else {
            dr1 = false;
        }
        if (jChBdr2.isSelected()) {
            listadimensionesdiseno.add(lbldr2.getText());
            dr2 = true;
        } else {
            dr2 = false;
        }
        if (jChBdr3.isSelected()) {
            listadimensionesdiseno.add(lbldr3.getText());
            dr3 = true;
        } else {
            dr3 = false;
        }
        if (jChBdr4.isSelected()) {
            listadimensionesdiseno.add(lbldr4.getText());
            dr4 = true;
        } else {
            dr4 = false;
        }
        if (jChBdr5.isSelected()) {
            listadimensionesdiseno.add(lbldr5.getText());
            dr5 = true;
        } else {
            dr5 = false;
        }
        if (jChBdr6.isSelected()) {
            listadimensionesdiseno.add(lbldr6.getText());
            dr6 = true;
        } else {
            dr6 = false;
        }
        if (jChBdr7.isSelected()) {
            listadimensionesdiseno.add(lbldr7.getText());
            dr7 = true;
        } else {
            dr7 = false;
        }
        if (jChBdr8.isSelected()) {
            listadimensionesdiseno.add(lbldr8.getText());
        } else {
            dr8 = false;
        }
        if (jChBdr9.isSelected()) {
            listadimensionesdiseno.add(lbldr9.getText());
            dr9 = true;
        } else {
            dr9 = false;
        }
        if (jChBdr10.isSelected()) {
            listadimensionesdiseno.add(lbldr10.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr11.isSelected()) {
            listadimensionesdiseno.add(lbldr11.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr12.isSelected()) {
            listadimensionesdiseno.add(lbldr12.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr13.isSelected()) {
            listadimensionesdiseno.add(lbldr13.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr14.isSelected()) {
            listadimensionesdiseno.add(lbldr14.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr15.isSelected()) {
            listadimensionesdiseno.add(lbldr15.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr16.isSelected()) {
            listadimensionesdiseno.add(lbldr16.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr17.isSelected()) {
            listadimensionesdiseno.add(lbldr17.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr18.isSelected()) {
            listadimensionesdiseno.add(lbldr18.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
        if (jChBdr19.isSelected()) {
            listadimensionesdiseno.add(lbldr19.getText());
            dr10 = true;
        } else {
            dr10 = false;
        }
    }

    private void llenarTabla() {
        //modeltabla = (DefaultTableModel)tabladimension.getModel();
        for (String dimensiones : listadimensionesantropometricas) {
            modeltabla.addRow(new Object[]{dimensiones});
        }
        tabladimension.setModel(modeltabla);

    }

    private void llenarTablaMuestra() {
        modeltablamuestra = (DefaultTableModel) tablamuestra.getModel();
        creaTablaMuestra();
        int sujetos = (int) jcbmuestra.getSelectedItem();
        for (int i = 0; i < sujetos; i++) {
            int aux2 = i + 1;
            String nombre = "Sujeto " + aux2;
            modeltablamuestra.addRow(new Object[]{nombre});
        }
        tablamuestra.setModel(modeltablamuestra);
        jScrollPane8.setViewportView(tablamuestra);
        jScrollPane7.setViewportView(jScrollPane8);
    }

    private void creaTablaMuestra() {
        JComboBox cbox1 = new JComboBox();
        DefaultComboBoxModel listacb1 = new DefaultComboBoxModel();
        listacb1.addElement("De pie");
        listacb1.addElement("Sentado de pie");
        listacb1.addElement("Sentado normal");
        cbox1.setModel(listacb1);
        tablamuestra.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbox1));
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
            hb.enableHelpOnButton(btnayuda, "aplicacion", helpset);
            //hb.enableHelpOnButton(jMenuItemAyuda, "ventana_principal", helpset);
            hb.enableHelpKey(this.getContentPane(), "aplicacion",
                    helpset);
//			hb.enableHelpKey(secundaria.getContentPane(), "ventana_secundaria",
//					helpset);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alineacion(int stc) {
        /*StyledDocument st=jTextPane1.getStyledDocument();
         SimpleAttributeSet bSet = new SimpleAttributeSet(); 
         StyleConstants.setAlignment(bSet, stc); 
         st.setParagraphAttributes(0,jTextPane1.getText().length(), bSet, false); 
         jTextPane1.updateUI();*/
    }

    private void llenarImagenes(int pos_sujeto) {
        //debo buscar aleatoriamente el id del sujeto y despues llamar al metodo find
        sujetoseleccionado = controlsujeto.Mostrar(pos_sujeto);
        //sujetoseleccionado=controlsujeto.Mostrar(0);
        //JOptionPane.showMessageDialog(null,"sujeto  : "+ sujetoseleccionado.getNombSujet());
        List<SujetoPosiciones> sujetosfotos = sujetoseleccionado.getSujetoPosicionesList();
        for (int i = 0; i < sujetosfotos.size(); i++) {
            foto = new ToolkitImage(new ByteArrayImageSource(sujetosfotos.get(i).getPosicionFoto()));

            if (sujetosfotos.get(i).getIdposicionesDescripcion().getDescripcion().equals("sentado de lado con agarre")) {
                //if (fpostura1 == null) {
                fpostura1 = foto;
                //}
                foto = foto.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_AREA_AVERAGING);
                jLabel8.setIcon(new ImageIcon(foto));
                idposcdesc1 = sujetosfotos.get(i).getIdposicionesDescripcion().getIdposicionesDescripcion();
                foto = null;
            } else if (sujetosfotos.get(i).getIdposicionesDescripcion().getDescripcion().equals("sentado de espalda")) {
                //if (fpostura2 == null) {
                fpostura2 = foto;
                //}
                foto = foto.getScaledInstance(jLabel9.getWidth(), jLabel9.getHeight(), Image.SCALE_AREA_AVERAGING);
                jLabel9.setIcon(new ImageIcon(foto));
                idposcdesc2 = sujetosfotos.get(i).getIdposicionesDescripcion().getIdposicionesDescripcion();
                foto = null;
            } else if (sujetosfotos.get(i).getIdposicionesDescripcion().getDescripcion().equals("de pie de lado")) {
                //if (fpostura3 == null) {
                fpostura3 = foto;
                //}
                foto = foto.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_AREA_AVERAGING);
                jLabel10.setIcon(new ImageIcon(foto));
                idposcdesc3 = sujetosfotos.get(i).getIdposicionesDescripcion().getIdposicionesDescripcion();
                foto = null;
            } else if (sujetosfotos.get(i).getIdposicionesDescripcion().getDescripcion().equals("de pie con agarre")) {
                //if (fpostura4 == null) {
                fpostura4 = foto;
                //}
                foto = foto.getScaledInstance(jLabel11.getWidth(), jLabel11.getHeight(), Image.SCALE_AREA_AVERAGING);
                jLabel11.setIcon(new ImageIcon(foto));
                idposcdesc4 = sujetosfotos.get(i).getIdposicionesDescripcion().getIdposicionesDescripcion();
                foto = null;
            }
        }
    }

    private void llenarListaMedidasAntropometricas() {
        modellist = new DefaultListModel();
        for (String dim : listadimensionesantropometricas) {
            modellist.addElement(dim);
        }
        listadiseno.setModel(modellist);
    }

    private void validaMenu() {
        if (AutentificacionInicialVisual.usuarioAutentificado != null) {
            btnautenticar.setName("logout");
            String rol = AutentificacionInicialVisual.usuarioAutentificado.getRolIdrol().getNombRol();
            if (rol.equals("Administrador")) {
                menureportes.setEnabled(false);
                menuitemcasoestud.setEnabled(false);
                menuitemejercicios.setEnabled(false);
                menuevaluacion.setEnabled(false);
                mniempezar.setEnabled(false);
                menuregistro.setEnabled(true);
                menureportes.setEnabled(false);
                menugestion.setEnabled(true);
                menuitemtraza.setEnabled(true);

            }
            if (rol.equals("Estudiante")) {
                menureportes.setEnabled(false);
                menugestion.setEnabled(false);
                menuregistro.setEnabled(false);
                mniempezar.setEnabled(true);
                //menuitemcasoestud.setEnabled(true);
                //menuitemejercicios.setEnabled(true);
                menuevaluacion.setEnabled(true);
                jLabel4.setEnabled(true);
                habilitaCasosdeEstudio(true);
            }
            if (rol.equals("Profesor")) {
                menuitemtraza.setEnabled(false);
                menuitemcasoestud.setEnabled(true);
                menuitemejercicios.setEnabled(true);
                menugestion.setEnabled(true);
                menuevaluacion.setEnabled(true);
                menureportes.setEnabled(true);
                mniempezar.setEnabled(false);
            }

            if (rol.equals("Super")) {
                menuitemtraza.setEnabled(false);
                menuitemcasoestud.setEnabled(false);
                menuitemejercicios.setEnabled(false);
                menugestion.setEnabled(false);
                menuevaluacion.setEnabled(false);
                menureportes.setEnabled(false);
                menuregistro.setEnabled(true);
                mniempezar.setEnabled(false);
            }
        } else {

        }
    }

    private void creaTabla() {
        //PARA COMBERTIR EL ELEMENTO EN UN ENTERO
        //String cantsuj=(String)jcbmuestra.getSelectedItem();
        //int sujetos=Integer.valueOf(cantsuj);
        //modeltabla=(DefaultTableModel) tabladimension.getModel();
        if (modeltabla != null) {

            modeltabla = (DefaultTableModel) tabladimension.getModel();
            modeltabla.setColumnCount(0);
            modeltabla.setRowCount(0);
            tabladimension.repaint();
            actualizaTabla();
        } else {
            actualizaTabla();
        }

        jScrollPane1.setViewportView(tabladimension);
        sptabladimensiones.setViewportView(jScrollPane1);
        //modeltabla=null;
    }

    private void actualizaTabla() {
        int sujetos = (int) jcbmuestra.getSelectedItem();
        listaarray.add("Medida");
        for (int i = 0; i < sujetos; i++) {
            int aux2 = i + 1;
            listaarray.add("Sujeto " + aux2);
            //modelotabla.addColumn(new Object[]{"Sujeto "+i+1});
        }
        //CUANDO DOY 2 VECES ME ESCOGE PONE TODOS Y NO ME LIMPIA LA TABLA
        modeltabla = new DefaultTableModel(listaarray.toArray(), 0);
        llenarTabla();
        listaarray.clear();
        System.out.println("mide " + modeltabla.getRowCount());
    }

    private void cambioAInstrumento() {
        List<Instrumento> listinstrumento = controlinstrumento.findInstrumentoEntities();
    }

    private void llenaPanelExperimentacion() {
        List<Experimentacion> list = controlexperimentacion.findExperimentacionEntities();
        //List<Instrumento> list=controlinstrumento.findInstrumentoEntities();
        GridLayout instrumlayot = new GridLayout(list.size(), 1, 5, 5);
        panelmonbinstrumento.setLayout(instrumlayot);
        for (final Experimentacion instrumento : list) {
            final JLabel lbl = new JLabel(instrumento.getNombre());
            lbl.setSize(289, 30);
            lbl.setFont(new java.awt.Font("Tahoma", 1, 12));
            lbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (autenticado && !clickpuestotrabajo && lbl.isEnabled()) {
                        habilitarLabelInstrumento();
                        //no la voy a poner null porque la voy a utilizar
                        fotoprincipal = lblinstrumentos.getIcon();
                        fotoauxiliar = new ToolkitImage(new ByteArrayImageSource(instrumento.getPuestotrabaj()));
                        fotoauxiliar = fotoauxiliar.getScaledInstance(lblinstrumentos.getWidth(), lblinstrumentos.getHeight(), Image.SCALE_DEFAULT);
                        lblinstrumentos.setIcon(new ImageIcon(fotoauxiliar));
                        fotoauxiliar = null;
                        llenarSujetosComboBox();
                        lbl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
                        modeloexp = controlexperimentacion.Mostrar(lbl.getText());//este es el objeto que toma la experimentacion seleccionada
                        txtAEnunciado.setOpaque(false);
                        //if (!clickpuestotrabajo) {
                        panelcalculo.setVisible(false);
                        panelcasoestudio.setVisible(true);
                        paneldimensiones.setVisible(false);
                        panelexperimentacion.setVisible(false);
                        panelprincipal.setVisible(false);
                        panelinstrumento.setVisible(true);
                        panelpuestos.setVisible(false);
                        clickpuestotrabajo = true;
                        //}
                        txtAEnunciado.setText("");
                        txtAEnunciado.setText(modeloexp.getDescripcion());
                    }
                }
            });
            panelmonbinstrumento.add(lbl);
        }
        panelmonbinstrumento.setEnabled(false);
    }

    private void habilitarLabelInstrumento() {
        Component[] componente = panelmonbinstrumento.getComponents();
        for (Component component : componente) {
            if (component instanceof JLabel) {
                JLabel aux = (JLabel) component;
                aux.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            }
        }
    }

    private void habilitarLabelPuestoTrabajo() {
        Component[] componente = panelpuestos.getComponents();
        for (Component component : componente) {
            if (component instanceof JLabel) {
                JLabel aux = (JLabel) component;
                if (!aux.getText().isEmpty()) {
                    aux.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                }

            }
        }
    }

    private void llenarComboBoxPosiciones() {
//        DefaultTableModel modeloTabla = (DefaultTableModel) tablamuestra.getModel();
//        DefaultComboBoxModel modeloCombo = (DefaultComboBoxModel) jcbposiciontrabajo.getModel();
//        modeloCombo.removeAllElements();
//        modeloCombo.addElement("seleccione");
//        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
//            boolean error = true;
//            for (int j = 0; j < modeloCombo.getSize(); j++) {
//                if (modeloTabla.getValueAt(i, 0) == modeloCombo.getElementAt(j)) {
//                    error = false;
//                }
//            }
//            if (error == true) {
//                modeloCombo.addElement(modeloTabla.getValueAt(i, 1));
//            }
//        }
    }

    private int posicionFilaTabla(String nombre) {
        modeltabla = (DefaultTableModel) tabladimension.getModel();
        int pos = -1;
        if (modeltabla != null) {
            //System.out.println("No es nulo el tabla model");
            for (int i = 0; i < modeltabla.getRowCount(); i++) {
                //System.out.println("este es el valor que tomo " + i + ": " + modeltabla.getValueAt(i, 0) + " este es el nombre " + nombre);
                if (modeltabla.getValueAt(i, 0) == nombre) {
                    pos = i;
                    return pos;
                }
            }
        }
        return pos;
    }

    //no funciona
    private int menorP5(int posicion) {
        modeltabla = (DefaultTableModel) tabladimension.getModel();
        int valor = -1;
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "No funciona la posicion");
        } else {

            String valo1 = String.valueOf(modeltabla.getValueAt(posicion, 1));
            valor = Integer.parseInt(valo1);
            if (modeltabla != null) {
                for (int i = 2; i < modeltabla.getColumnCount(); i++) {
                    //System.out.println("Estoy escogiendo el numero");
                    String nosre = String.valueOf(modeltabla.getValueAt(posicion, i));
                    //int auxvalor=Integer.parseInt(nosre);//(Integer) tabladimension.getValueAt(posicion, i);
                    int auxvalor = Integer.parseInt(nosre);
                    if (auxvalor < valor) {
                        valor = auxvalor;
                    }
                }
            }
        }

        return valor;
    }

    private int menorP95(int posicion) {
        modeltabla = (DefaultTableModel) tabladimension.getModel();
        int valor = -1;
        if (posicion == -1) {
            JOptionPane.showMessageDialog(null, "No funciona la posicion");
        } else {

            String valo1 = String.valueOf(modeltabla.getValueAt(posicion, 1));
            valor = Integer.parseInt(valo1);
            if (modeltabla != null) {
                for (int i = 2; i < modeltabla.getColumnCount(); i++) {
                    //System.out.println("Estoy escogiendo el numero");
                    String nosre = String.valueOf(modeltabla.getValueAt(posicion, i));
                    //int auxvalor=Integer.parseInt(nosre);//(Integer) tabladimension.getValueAt(posicion, i);
                    int auxvalor = Integer.parseInt(nosre);
                    if (auxvalor > valor) {
                        valor = auxvalor;
                    }
                }
            }
        }

        return valor;
    }

    private void habilitaCasosdeEstudio(boolean valor) {
        Component[] componente = panelmonbinstrumento.getComponents();
        for (Component component : componente) {
            if (component instanceof JLabel) {
                JLabel aux = (JLabel) component;
                aux.setEnabled(valor);
            }
        }
    }

    private void InicializaAllComponentes() {
        Component[] componente = panelda.getComponents();
        for (Component component : componente) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    ((JCheckBox) component).setSelected(false);
                }
            }
        }
        Component[] auxiliar = paneldr.getComponents();
        for (Component component : auxiliar) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    ((JCheckBox) component).setSelected(false);
                }
            }
        }
    }

    private void siguienteImagen() {
        rotac_muestra--;
        btbsiguienteexp.setEnabled(true);
        boolean valor = true;
        if (rotac_muestra <= 0) {
            //btnanteriorexp.setEnabled(false);
            valor = false;

        }
        if (btbsiguienteexp.isEnabled()) {
            int aux;
            if (sujeto_aleatorio == 0) {
                aux = controlsujeto.getSujetoCount() - 1;
            } else {
                aux = sujeto_aleatorio - 1;
            }
//            if (aux < 0) {
//                aux=controlsujeto.getSujetoCount()-1;
//            }
            llenarImagenes(aux);
            sujeto_aleatorio = aux;
            //btnanteriorexp.setEnabled(false);
        }
    }

    private void anteriorImagen() {
        boolean valor = true;
        rotac_muestra++;
        if (rotac_muestra >= muestra - 1) {
            valor = false;
            //btnanteriorexp.setEnabled(true);
        }
        if (btbsiguienteexp.isEnabled()) {
            int aux = sujeto_aleatorio + 1;
            if (aux == controlsujeto.getSujetoCount()) {
                aux = 0;
            }
            llenarImagenes(aux);
            sujeto_aleatorio = aux;
            btbsiguienteexp.setEnabled(false);
        }
    }

    private void habilitaPanelPosturas(boolean valor) {
        jLabel8.setEnabled(valor);
        jLabel9.setEnabled(valor);
        jLabel10.setEnabled(valor);
        jLabel11.setEnabled(valor);
        panelposturas.setEnabled(valor);
    }

    private void habilitaPanelInstrumentos(boolean valor) {
        lblagenciaviajes.setEnabled(valor);
        lbltrabajopc.setEnabled(valor);
        lblsillaburo.setEnabled(valor);
        lblmaquinaimpresion.setEnabled(valor);
        lblfabricacomponentes.setEnabled(valor);
        lblpupitre.setEnabled(valor);
        panelpuestos.setEnabled(valor);
    }

    private void validaColumnaBoton() {
        boolean correcto = false;
        modeltabla = (DefaultTableModel) tabladimension.getModel();
        if (columna < muestra) {
            for (int i = 0; i < modeltabla.getRowCount(); i++) {
                if (modeltabla.getValueAt(i, columna + 1) == null) {
                    correcto = true;
                    break;
                }
            }
            if (!correcto) {
                columna++;
                btbsiguienteexp.setEnabled(true);
            }
        }
    }

    private void errorePanelCE() {
        int counterror = 0;
        int sumaer = auxerrorCE.getCantidad();
        List<Posicionespuestotrabajo> lista = modeloexp.getPosicionespuestotrabajoList();
        for (Posicionespuestotrabajo posicionespuestotrabajo : lista) {
            if (!ppt.contains(posicionespuestotrabajo.getDescripcion())) {
                counterror++;
                sumaer += counterror;
            }
        }
        auxerrorCE.setCantidad(sumaer);
        auxerrorCE.setDescripcion("Error al identificar las posiciones del puesto de trabajo ");
    }
    //*******************************************FIN 

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
         java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }*/
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal dialog = new Principal();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbsiguienteexp;
    private javax.swing.JButton btnatrasExp;
    private javax.swing.JButton btnatrascalculo;
    public static javax.swing.JButton btnautenticar;
    private javax.swing.JButton btnayuda;
    private javax.swing.JButton btncalculo;
    private javax.swing.JButton btncomenzar;
    private javax.swing.JButton btncomenzar1;
    private javax.swing.JButton btncomenzar2;
    private javax.swing.JButton btncomenzar3;
    private javax.swing.JButton btndivision;
    private javax.swing.JButton btnduplo;
    private javax.swing.JButton btnespesor;
    private javax.swing.JButton btnfincalculo;
    private javax.swing.JButton btnholgura;
    private javax.swing.JButton btnmultiplicacion;
    private javax.swing.JButton btnpasarAcalculardiseno;
    private javax.swing.JButton btnpasarAexperimentacion;
    private javax.swing.JButton btnpotencia;
    private javax.swing.JButton btnreiniciar;
    private javax.swing.JButton btnresta;
    public static javax.swing.JButton btnsalir;
    private javax.swing.JButton btnsuma;
    private javax.swing.JComboBox cbdimrelv;
    private javax.swing.JComboBox cbpercentil;
    private javax.swing.JComboBox cbtangente;
    private javax.swing.JCheckBox chbalcancelateraldelbrazo;
    private javax.swing.JCheckBox chbalcancemaximodelbrazo;
    private javax.swing.JCheckBox chbalcanceminimodelbrazo;
    private javax.swing.JCheckBox chbalturadelarodilla;
    private javax.swing.JCheckBox chbalturadelcodo;
    private javax.swing.JCheckBox chbalturadelcodosentado;
    private javax.swing.JCheckBox chbalturadelosojos;
    private javax.swing.JCheckBox chbalturadelosojossentado;
    private javax.swing.JCheckBox chbalturademuslo;
    private javax.swing.JCheckBox chbalturadeojos1;
    private javax.swing.JCheckBox chbalturailiocrestal;
    private javax.swing.JCheckBox chbalturailiocrestal11;
    private javax.swing.JCheckBox chbalturapoplitea;
    private javax.swing.JCheckBox chbalturasacrorotula;
    private javax.swing.JCheckBox chbalturasubescapular;
    private javax.swing.JCheckBox chbanchodecaderasentado;
    private javax.swing.JCheckBox chbanchodecodoacodo;
    private javax.swing.JCheckBox chbanchodelpie;
    private javax.swing.JCheckBox chbdiametrobiacromial;
    private javax.swing.JCheckBox chbestatura;
    private javax.swing.JCheckBox chblongituddelmuslo;
    private javax.swing.JCheckBox chbsacropoplitea;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jChBdr1;
    private javax.swing.JCheckBox jChBdr10;
    private javax.swing.JCheckBox jChBdr11;
    private javax.swing.JCheckBox jChBdr12;
    private javax.swing.JCheckBox jChBdr13;
    private javax.swing.JCheckBox jChBdr14;
    private javax.swing.JCheckBox jChBdr15;
    private javax.swing.JCheckBox jChBdr16;
    private javax.swing.JCheckBox jChBdr17;
    private javax.swing.JCheckBox jChBdr18;
    private javax.swing.JCheckBox jChBdr19;
    private javax.swing.JCheckBox jChBdr2;
    private javax.swing.JCheckBox jChBdr3;
    private javax.swing.JCheckBox jChBdr4;
    private javax.swing.JCheckBox jChBdr5;
    private javax.swing.JCheckBox jChBdr6;
    private javax.swing.JCheckBox jChBdr7;
    private javax.swing.JCheckBox jChBdr8;
    private javax.swing.JCheckBox jChBdr9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox jcbmuestra;
    private javax.swing.JLabel lblagenciaviajes;
    private javax.swing.JLabel lblalcancelateraldelbrazo;
    private javax.swing.JLabel lblalcancemaximodelbrazo;
    private javax.swing.JLabel lblalcanceminimodelbrazo;
    private javax.swing.JLabel lblalturadelarodilla;
    private javax.swing.JLabel lblalturadelcodo;
    private javax.swing.JLabel lblalturadelcodosentado;
    private javax.swing.JLabel lblalturadelmusloxxxx;
    private javax.swing.JLabel lblalturadelosojos;
    private javax.swing.JLabel lblalturadelosojossentado;
    private javax.swing.JLabel lblalturadeojos1;
    private javax.swing.JLabel lblalturailiocrestal;
    private javax.swing.JLabel lblalturailiocrestal11;
    private javax.swing.JLabel lblalturapoplitea;
    private javax.swing.JLabel lblalturasacrorotula;
    private javax.swing.JLabel lblalturasubescapular;
    private javax.swing.JLabel lblanchodecaderasentado;
    private javax.swing.JLabel lblanchodecodoacodo;
    private javax.swing.JLabel lblanchodelpie;
    private javax.swing.JLabel lbldiametrobiacromial;
    private javax.swing.JLabel lbldim1;
    private javax.swing.JLabel lbldr1;
    private javax.swing.JLabel lbldr10;
    private javax.swing.JLabel lbldr11;
    private javax.swing.JLabel lbldr12;
    private javax.swing.JLabel lbldr13;
    private javax.swing.JLabel lbldr14;
    private javax.swing.JLabel lbldr15;
    private javax.swing.JLabel lbldr16;
    private javax.swing.JLabel lbldr17;
    private javax.swing.JLabel lbldr18;
    private javax.swing.JLabel lbldr19;
    private javax.swing.JLabel lbldr2;
    private javax.swing.JLabel lbldr3;
    private javax.swing.JLabel lbldr4;
    private javax.swing.JLabel lbldr5;
    private javax.swing.JLabel lbldr6;
    private javax.swing.JLabel lbldr7;
    private javax.swing.JLabel lbldr8;
    private javax.swing.JLabel lbldr9;
    private javax.swing.JLabel lblejercicio;
    private javax.swing.JLabel lblestatura;
    private javax.swing.JLabel lblfabricacomponentes;
    private javax.swing.JLabel lblimagenprincipal;
    private javax.swing.JLabel lblinstrumentos;
    private javax.swing.JLabel lbllongituddelmuslo;
    private javax.swing.JLabel lblmaquinaimpresion;
    private javax.swing.JLabel lblpuestotrabajo;
    private javax.swing.JLabel lblpupitre;
    private javax.swing.JLabel lblresultado;
    private javax.swing.JLabel lblsacropoplitea;
    private javax.swing.JLabel lblsillaburo;
    private javax.swing.JLabel lbltrabajopc;
    private javax.swing.JList listadiseno;
    private javax.swing.JMenu menuevaluacion;
    private javax.swing.JMenu menugestion;
    private javax.swing.JMenuItem menuitemcasoestud;
    private javax.swing.JMenuItem menuitemejercicios;
    private javax.swing.JMenuItem menuitemtraza;
    private javax.swing.JMenu menuregistro;
    private javax.swing.JMenu menureportes;
    private javax.swing.JMenuItem mniempezar;
    private javax.swing.JPanel panelcalculo;
    private javax.swing.JPanel panelcasoestudio;
    private javax.swing.JPanel panelda;
    private javax.swing.JPanel paneldimensiones;
    private javax.swing.JPanel paneldiseno;
    private javax.swing.JPanel paneldr;
    private javax.swing.JPanel panelexperimentacion;
    private javax.swing.JPanel panelimagen;
    private javax.swing.JPanel panelinstrumento;
    private javax.swing.JPanel panelmonbinstrumento;
    private javax.swing.JPanel paneloperadores;
    private javax.swing.JPanel panelopr1;
    private javax.swing.JPanel panelopr2;
    private javax.swing.JPanel panelposturas;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel panelpuestos;
    private javax.swing.JPanel panelpuestotrabajo;
    private javax.swing.JPanel panelrespuestaexperimentacion;
    private javax.swing.JScrollPane scdimensionesA;
    private javax.swing.JScrollPane spdimensionesR;
    public static javax.swing.JScrollPane spprincipal;
    private javax.swing.JScrollPane sptabladimensiones;
    private javax.swing.JTable tabladimension;
    private javax.swing.JTable tablamuestra;
    private javax.swing.JTextArea txtAEnunciado;
    public static javax.swing.JTextArea txtareaprincipal;
    // End of variables declaration//GEN-END:variables
}
