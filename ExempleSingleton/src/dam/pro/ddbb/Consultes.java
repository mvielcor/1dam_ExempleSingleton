package dam.pro.ddbb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mviel
 * Classe que contindrà totes les consultes de l'app.
 * 
 */
public class Consultes {
	// Exemple d'una consulta d'inserció
	// inserim les dades d'un profesor en la base de dades.
	public int insereixProfessor(Object dadesProfe[]){
		int resultat=-1;
		if(ConnexioBaseDades.con!=null){
			try {
				String consultaInsercio = "INSERT INTO PROFESSORS VALUES (?,?,?,?,?)";
				PreparedStatement pstm=ConnexioBaseDades.con.prepareStatement(consultaInsercio);
				for(int i=0;i<dadesProfe.length;i++){

					if(i==3){
						pstm.setInt(i+1, Integer.parseInt(dadesProfe[i].toString()));	
					}else{
						pstm.setString(i+1, dadesProfe[i].toString());	
					}
				}
				resultat=pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultat;
	}
	
	//Exemple d'una consulta d'eliminació
	public int eliminaProfessor(Object dades[]){
		int resultat=-1;
		if(ConnexioBaseDades.con!=null){	
			try {
				PreparedStatement pstm=ConnexioBaseDades.con.prepareStatement("DELETE FROM PROFESSORS WHERE nom=? and cognom=? and modul=? and curs=? and cicle=?");
				for(int i=0;i<dades.length;i++){

					if(i==3){
						pstm.setInt(i+1, Integer.parseInt(dades[i].toString()));	
					}else{
						pstm.setString(i+1, dades[i].toString());	
					}
				}
				resultat=pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultat;
	}
	
	// Exemple d'una consulta de selecció
    public ResultSet consultaTotsRegistresDeLaTaula(String taula){
		ResultSet resultat=null;
		if(ConnexioBaseDades.con!=null){
			try {
				PreparedStatement pstm=ConnexioBaseDades.con.prepareStatement("SELECT * FROM "+taula);
				resultat=pstm.executeQuery();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultat;
	}

}
