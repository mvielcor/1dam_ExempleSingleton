package dam.pro.JTables;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dam.pro.ddbb.ConnexioBaseDades;
import dam.pro.ddbb.Consultes;

public class AfegirProfessor extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNom;
	private JTextField txtCognoms;
	private JTextField txtModul;
	private JTextField txtCurs;
	private JTextField txtCicle;
	private JButton okButton,cancelButton;
	private Object[] dadesProfessor = new Object[5];
	private DefaultTableModel dtm2;
	

	/**
	 * Create the dialog.
	 */
	public AfegirProfessor(DefaultTableModel eldtm) {
		dtm2=eldtm;
		setTitle("Afegir Professor...");
		setBounds(100, 100, 450, 312);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 7, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIntroduuLesDades = new JLabel("Introdu\u00EFu les dades del Professor a afegir....");
			GridBagConstraints gbc_lblIntroduuLesDades = new GridBagConstraints();
			gbc_lblIntroduuLesDades.gridwidth = 7;
			gbc_lblIntroduuLesDades.insets = new Insets(0, 0, 5, 0);
			gbc_lblIntroduuLesDades.gridx = 0;
			gbc_lblIntroduuLesDades.gridy = 1;
			contentPanel.add(lblIntroduuLesDades, gbc_lblIntroduuLesDades);
		}
		{
			JLabel lblNom = new JLabel("Nom:");
			GridBagConstraints gbc_lblNom = new GridBagConstraints();
			gbc_lblNom.anchor = GridBagConstraints.EAST;
			gbc_lblNom.insets = new Insets(0, 0, 5, 5);
			gbc_lblNom.gridx = 0;
			gbc_lblNom.gridy = 3;
			contentPanel.add(lblNom, gbc_lblNom);
		}
		{
			txtNom = new JTextField();
			GridBagConstraints gbc_txtNom = new GridBagConstraints();
			gbc_txtNom.gridwidth = 5;
			gbc_txtNom.anchor = GridBagConstraints.NORTH;
			gbc_txtNom.insets = new Insets(0, 0, 5, 5);
			gbc_txtNom.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNom.gridx = 1;
			gbc_txtNom.gridy = 3;
			contentPanel.add(txtNom, gbc_txtNom);
			txtNom.setColumns(10);
		}
		{
			JLabel lblCognoms = new JLabel("Cognoms:");
			GridBagConstraints gbc_lblCognoms = new GridBagConstraints();
			gbc_lblCognoms.anchor = GridBagConstraints.EAST;
			gbc_lblCognoms.insets = new Insets(0, 0, 5, 5);
			gbc_lblCognoms.gridx = 0;
			gbc_lblCognoms.gridy = 4;
			contentPanel.add(lblCognoms, gbc_lblCognoms);
		}
		{
			txtCognoms = new JTextField();
			GridBagConstraints gbc_txtCognoms = new GridBagConstraints();
			gbc_txtCognoms.gridwidth = 5;
			gbc_txtCognoms.insets = new Insets(0, 0, 5, 5);
			gbc_txtCognoms.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCognoms.gridx = 1;
			gbc_txtCognoms.gridy = 4;
			contentPanel.add(txtCognoms, gbc_txtCognoms);
			txtCognoms.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("M\u00F2dul:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 5;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtModul = new JTextField();
			GridBagConstraints gbc_txtModul = new GridBagConstraints();
			gbc_txtModul.insets = new Insets(0, 0, 5, 5);
			gbc_txtModul.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtModul.gridx = 1;
			gbc_txtModul.gridy = 5;
			contentPanel.add(txtModul, gbc_txtModul);
			txtModul.setColumns(10);
		}
		{
			JLabel lblCurs = new JLabel("Curs:");
			GridBagConstraints gbc_lblCurs = new GridBagConstraints();
			gbc_lblCurs.anchor = GridBagConstraints.EAST;
			gbc_lblCurs.insets = new Insets(0, 0, 5, 5);
			gbc_lblCurs.gridx = 0;
			gbc_lblCurs.gridy = 6;
			contentPanel.add(lblCurs, gbc_lblCurs);
		}
		{
			txtCurs = new JTextField();
			GridBagConstraints gbc_txtCurs = new GridBagConstraints();
			gbc_txtCurs.insets = new Insets(0, 0, 5, 5);
			gbc_txtCurs.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCurs.gridx = 1;
			gbc_txtCurs.gridy = 6;
			contentPanel.add(txtCurs, gbc_txtCurs);
			txtCurs.setColumns(10);
		}
		{
			JLabel lblCicle = new JLabel("Cicle;");
			GridBagConstraints gbc_lblCicle = new GridBagConstraints();
			gbc_lblCicle.anchor = GridBagConstraints.EAST;
			gbc_lblCicle.insets = new Insets(0, 0, 0, 5);
			gbc_lblCicle.gridx = 0;
			gbc_lblCicle.gridy = 7;
			contentPanel.add(lblCicle, gbc_lblCicle);
		}
		{
			txtCicle = new JTextField();
			GridBagConstraints gbc_txtCicle = new GridBagConstraints();
			gbc_txtCicle.gridwidth = 5;
			gbc_txtCicle.insets = new Insets(0, 0, 0, 5);
			gbc_txtCicle.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCicle.gridx = 1;
			gbc_txtCicle.gridy = 7;
			contentPanel.add(txtCicle, gbc_txtCicle);
			txtCicle.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		setModal(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String opcio=e.getActionCommand();
		if (opcio.compareTo("OK")==0){
			//comprove que les dades introduïdes són correctes
			if(comprovaDades()){
				//inserim les dades a la BBDD
				// Iniciem la connexió a la base de dades
				ConnexioBaseDades cbd = ConnexioBaseDades.getInstancia();
				Consultes insercio = new Consultes();
				int registres = insercio.insereixProfessor(dadesProfessor);
				if(registres==1){
					//afegim les dades al dtm
					dtm2.addRow(dadesProfessor);
				}else{
					String missatge="L'intent d'inserció de les dades del profesor ens ha tornat el valor "+registres;
					JOptionPane.showMessageDialog(null, missatge, "Alguna cosa <b>NO</b> ha anat bé", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		}
		if(opcio.compareTo("Cancel")==0){
			dispose();
		}
		
	}
	private boolean comprovaDades(){
		//Caldria comprovar que les dades són correctes...
		dadesProfessor[0] = txtNom.getText();
		dadesProfessor[1] = txtCognoms.getText();
		dadesProfessor[2] = txtModul.getText();
		dadesProfessor[3] = txtCurs.getText();
		dadesProfessor[4] = txtCicle.getText();
		return true;
	}

	
}
