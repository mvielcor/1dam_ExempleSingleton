package dam.pro.ddbb;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnexioBaseDades {

    static Connection con; // atribut per a guardar l’objecte connexió.
    private static ConnexioBaseDades INSTANCIA = null;  // referència a l'objecte que anem a crear.
 
    // Constructor, privat, NO públic, de manera que ninguna altra classe, apart d'aquesta, podrà cridar-lo.
    private ConnexioBaseDades() {
        realitzaConnexio();
    }
 
    // Si no existeix, crea una instancia de la base de datos.
    // El mètode és sinchronized per a protegir l'objecte en cas que treballem amb threads.
    private synchronized static void creaInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ConnexioBaseDades();
        }
    }
 
    /** Metode que retorna una instància de la connexió. Si no esta creada la crea, i si esta creada la torna.
     *  @return retorna una instància de la connexió a la base de dades
     */
    public static ConnexioBaseDades getInstancia() {
        if (INSTANCIA == null) creaInstancia();
        return INSTANCIA;
    }
 
    // Métode per a eliminar la instància de la connexió.
    public static void eliminaInstancia() {
        INSTANCIA = null;
        tancaConnexio();
    }
 
     // Mètode que realitza la connexió a la base de dades.
    public void realitzaConnexio() {
    	// Dades per a connectar amb MySQL
    	String host = "eu-cdbr-azure-west-d.cloudapp.net";//MySQL creat el el cloud d'azure
        String user = "b3cca9ed080a40";  
        String pass = "78d92cdc";
        String dtbs = "db1dam";
 
        try { 
        	// registrem el driver
            Class.forName("com.mysql.jdbc.Driver");
            // obtenim la cadena de connexió
            String newConnectionURL = "jdbc:mysql://" + host + "/" + dtbs + "?" + "user=" + user + "&password=" + pass;
            // Establim la connexió
            con = DriverManager.getConnection(newConnectionURL);
        } catch (Exception e) {
        	String missatge="Error intentant obrir la connexió amb '"+host+"' i la base de dades '"+dtbs+"'\nReviseu la vostra connexió a Internet i els paràmetres de connexió";
        	JOptionPane.showMessageDialog(null,missatge,"Error. Alguna cosa no ha anat bé",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error intentant obrir la connexió.");
        }
    }
 
    // Mètode per a tancar la connexió amb la base de dades
    public static void tancaConnexio() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error intentant tancar la connexió.");
        }
    }
}

