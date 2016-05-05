package dam.pro.JTables;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import dam.pro.ddbb.ConnexioBaseDades;
import dam.pro.ddbb.Consultes;;

public class Exemple1 extends JFrame {
	private JTable dades;
	private JPanel panellDeContingut;
	private DefaultTableModel dtm;
	private JPanel panellBotonera;
	private JButton btnAfegirProfe;
	private JButton btnEliminar;
	private ConnexioBaseDades cbd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exemple1 frame = new Exemple1();
					//centrem la finestra a la pantalla
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Exemple1() {
		
		//definim l'operació de tancament de l'app
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// Quan tanquem la finestra, tanquem l'única connexio a la base de dades
				cbd.tancaConnexio();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setBounds(100, 100, 450, 300);
		panellDeContingut = new JPanel();
		panellDeContingut.setBorder(new EmptyBorder(5, 5, 5, 5));
		panellDeContingut.setLayout(new BorderLayout(0, 0));
		setContentPane(panellDeContingut);
		//Ara ens connectarem a la BD per rebre esta informació
		/*String dadesTaula[][]={{"Manel","Viel","PROGRAMACIÓ","1","DAM"},
				{"Paco","Gomez","ENTORNS","1","DAM"},
				{"JuanMi","Benavent","LLENGUATGE MARQUES","1","DAM"},
				{"Toni","Ruiz","BBDD","1","DAM"}
				
		};
		*/
		//String dadesTaula[][];
		
		String nomsColumnes[]={"Nom","Cognoms","Mòdul","Curs","Cicle"};
		
		dtm=new DefaultTableModel();
		dades = new JTable(dtm);  // enllacem el JTable amb l'objecte DefaultTableModel
		
		
		//Ompliem el nom de les columnes del dtm
		//recorreguem l'array nomsColumnes i afegim els strings al dtm
		for(int i=0;i<nomsColumnes.length;i++){
			dtm.addColumn(nomsColumnes[i]);  
		}
		
		// Iniciem la connexió a la base de dades
		cbd = ConnexioBaseDades.getInstancia();
		
		//Consultem tots els resistres de la taula Professors
		Consultes consulta = new Consultes();
		ResultSet rtat = consulta.consultaTotsRegistresDeLaTaula("professors");
		//Recorreguem el resultSet i afegim les dades al dtm
		if(rtat!=null){
			try{
				while(rtat.next()){
					Object fila[] = new Object[5];
					fila[0] = rtat.getObject(1);
					fila[1] = rtat.getObject(2);
					fila[2] = rtat.getObject(3);
					fila[3] = rtat.getObject(4);
					fila[4] = rtat.getObject(5);

					dtm.addRow(fila); //Afegim les dades d'una fila del ResultSet a una fila del DefaultTableModel
				}
				rtat.close();
			}catch(SQLException e){}
		}
		
		
		
	
		
		dades.setPreferredScrollableViewportSize(getSize());
		JScrollPane scrollPane = new JScrollPane(dades);		
		panellDeContingut.add(scrollPane, BorderLayout.CENTER);
		
		panellBotonera = new JPanel();
		panellDeContingut.add(panellBotonera, BorderLayout.NORTH);
		
		btnAfegirProfe = new JButton("Afegir");
		btnAfegirProfe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfegirProfessor ap = new AfegirProfessor(dtm);
				
			}
		});
		panellBotonera.add(btnAfegirProfe);
		
		btnEliminar = new JButton("Eliminar");
		panellBotonera.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int numFilaSeleccionada=dades.getSelectedRow();
				if (numFilaSeleccionada!=-1){
					Object dadesFila[] = new Object[dtm.getColumnCount()];
					for (int i=0;i<dtm.getColumnCount();i++){
						dadesFila[i]=dtm.getValueAt(numFilaSeleccionada, i);
					}
					// Obtenim una instància de la connexió a la base de dades
					ConnexioBaseDades cbd = ConnexioBaseDades.getInstancia();
					// Creem un objecte de la clase Consultes.
					Consultes eliminacio = new Consultes();
					// Cridem a la consulta per a eliminar eixe registre.
					int registres = eliminacio.eliminaProfessor(dadesFila);

					if(registres==1){
						//eliminem les dades del dtm
						dtm.removeRow(numFilaSeleccionada);
					}else{
						String missatge="L'intent d'eliminació de les dades del profesor ens ha tornat el valor "+registres;
						JOptionPane.showMessageDialog(null, missatge, "Alguna cosa <b>NO</b> ha anat bé", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					//No s'ha seleccionat cap fila delJTable
					String missatge="Seleccione un professor a eliminar";
					JOptionPane.showMessageDialog(null, missatge, "Alguna cosa <b>NO</b> ha anat bé", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		
		
	}
	
	

}

