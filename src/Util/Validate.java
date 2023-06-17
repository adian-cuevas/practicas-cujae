package Util;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Validate {

    public static final int CONTIENE_CARACTER_NO_VALIDO = 1;
    public static final int NUMERO_DE_CARACTERES_ERRONEO = 2;
    public static final int VALIDACION_OK = 3;

    public static final int FECHA_FUERA_RANGO = 4;
    public static final int NUMERO_MAL_FORMADO =5;

    protected static Toolkit getToolkit() {
        return Toolkit.getDefaultToolkit();
    }

    public static void validateDigit(JTextField src) {
        src.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isDigit(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)))) {
                   // getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public static void validateDigitAndPoint(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isDigit(c)
                        || (c == '.')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)))) {
                    e.consume();
                }
            }
        });
    }

    public static void validateLetter(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isLetter(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)
                        || (Character.isWhitespace(c))))) {
                    e.consume();
                }
            }
        });
    }
    

    /**
     * @author WAO!!!
     * @param src
     *
     */
    public static void validateDigitAndLetter(JTextField src) {
        src.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || Character.isLetter(c) || Character
                        .isWhitespace(c)))) {
                    e.consume();
                }
            }
        });
    }

    public static InputVerifier validatePasswordEnvio(JTextField src) {
        InputVerifier verifier = new InputVerifier() {
            public boolean verify(JComponent input) {
                final JTextComponent source = (JTextComponent) input;
                String text = source.getText();
                if ((text.length() != 0) && text.length() < 5) {
                    Runnable runnable = new Runnable() {
                        public void run() {
                            JOptionPane men = new JOptionPane("La contrasena debe poseer mas de 5 caracteres");
                            men.setVisible(true);
                        }
                    };
                    EventQueue.invokeLater(runnable);
                    return false;
                } else {
                    return true;
                }
            }
        };
        return verifier;
    }

    
    public static int validarNumeroId(String numeroPas) {
        
        if (numeroPas.length() != 11) {
            return NUMERO_DE_CARACTERES_ERRONEO;
        }
        
        int anoActual = new Date().getYear();
        System.out.println("Ano actual " + anoActual);
        anoActual = anoActual+1900-2000;
        
        System.out.println("Ano actual " + anoActual);
        
        String ano = numeroPas.substring(0, 2);
        String mes = numeroPas.substring(2,4);
        String dia = numeroPas.substring(4,6);
        int anoInt = Integer.valueOf(ano);
        int mesInt = Integer.valueOf(mes);
        int diaInt = Integer.valueOf(dia);
        
        if(mesInt<0 || mesInt>12){
            return NUMERO_MAL_FORMADO;
        }
        
        if(diaInt<1 || diaInt>31){
            return NUMERO_MAL_FORMADO;
        }
        
        Calendar dateOfBirth = null;
        
        if(anoInt>=0 && anoInt<=anoActual){
             dateOfBirth = new GregorianCalendar(2000+anoInt, Integer.valueOf(mes), Integer.valueOf(dia));
        }
        else
             dateOfBirth = new GregorianCalendar(1900+anoInt, Integer.valueOf(mes), Integer.valueOf(dia));
        
        
        System.out.println(dateOfBirth.get(Calendar.YEAR));
        System.out.println(dateOfBirth.get(Calendar.MONTH));
        System.out.println(dateOfBirth.get(Calendar.DAY_OF_MONTH));
        
        
        // Create a calendar object with today's date
        Calendar today = Calendar.getInstance();

        // Get age based on year
        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        // Add the tentative age to the date of birth to get this year's birthday
        dateOfBirth.add(Calendar.YEAR, age);

        // If this year's birthday has not happened yet, subtract one from age
        if (today.before(dateOfBirth)) {
            age--;
        }
        if (age<18) {
            return FECHA_FUERA_RANGO;
        }

        return VALIDACION_OK;
    }

    public static int validarNumeroEnvio(String numPas) {
        if (numPas.contains(".")) {
            return CONTIENE_CARACTER_NO_VALIDO;
        }
        if (numPas.length() != 13) {
            return NUMERO_DE_CARACTERES_ERRONEO;
        }
        return VALIDACION_OK;
    }
    

    
}
