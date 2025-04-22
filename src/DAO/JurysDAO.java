package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import interfacePFE.MyConnection;
import projetPFE.*;

public class JurysDAO extends DAO<Jurys> {

	@Override
	public Jurys create(Jurys jurys) throws PFEException{
		try {
			//test existance des enseignat dans la base
			EnseignantDAO testens=new EnseignantDAO();
			if(!testens.existe(jurys.getPresident()))
					throw new PFEException(20);
			if(!testens.existe(jurys.getRapporteur()))
				throw new PFEException(21);
			if(!testens.existe(jurys.getExaminateur()))
				throw new PFEException(22);
			//test existance de 3 enseignant different 
			if(jurys.getPresident().getCIN()== jurys.getExaminateur().getCIN())
				throw new PFEException(27);
			if(jurys.getPresident().getCIN()== jurys.getRapporteur().getCIN())
				throw new PFEException(26);
			if(jurys.getExaminateur().getCIN()== jurys.getRapporteur().getCIN())
				throw new PFEException(28);
			
			
			Jurys jurysfinal;
			if(!combinaison_existe(jurys)) {
				String requete = "INSERT INTO Jurys (CINpresident, CINrapporteur, CINexaminateur) VALUES ('" + jurys.getPresident().getCIN() + "', '" + jurys.getRapporteur().getCIN() + "', '" + jurys.getExaminateur().getCIN()+ "')";
				Statement ps;
				ps = connbase.createStatement();
				int rs;
				rs =ps.executeUpdate(requete);
				ps.close();
				
				
			}
			//chercher le jurys finale
			String requete2 = "Select * From Jurys Where CINpresident=? AND CINrapporteur=? AND CINexaminateur=?";
			PreparedStatement ps2;
			ps2 = connbase.prepareStatement(requete2);
			ps2.setInt(1, jurys.getPresident().getCIN());
	        ps2.setInt(2, jurys.getRapporteur().getCIN());
	        ps2.setInt(3,jurys.getExaminateur().getCIN() );
			ResultSet rs2;
			rs2 =ps2.executeQuery();
			if(rs2.next()) {
				jurysfinal=new Jurys(jurys.getPresident() , jurys.getRapporteur() , jurys.getExaminateur() );
				jurysfinal.setId(rs2.getInt("id"));
				//fermer la connexion 
				rs2.close();
				ps2.close();
				return jurysfinal;
			}
			rs2.close();
			ps2.close();	
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Jurys update(Jurys jurys) {
		try {
			String requete= "UPDATE Jurys SET CINPRESIDENT ="+ jurys.getPresident().getCIN()+" , CINRAPPORTEUR = "+jurys.getRapporteur().getCIN()+", CINEXAMINATEUR="+jurys.getExaminateur().getCIN();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			ps.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Jurys jurys) throws PFEException{
		try {
			//
			//tester si Jursy possede une setenance
			String requeteSout = "Select * FROM Soutenance WHERE IDJURYS="+jurys.getId();
			Statement psSout;
			psSout = connbase.createStatement();
			ResultSet rsSout;
			rsSout =psSout.executeQuery(requeteSout);
			while(rsSout.next()) {
				Soutenance Sout=new Soutenance();
				Sout.setId(rsSout.getInt("ID"));
				SoutenanceDAO rechSout =new SoutenanceDAO();
				rechSout.delete(Sout);
			}
			rsSout.close();
			psSout.close();
			
			
			//
			String requete = "DELETE FROM Jurys WHERE id="+jurys.getId();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			
			ps.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Jurys> findAll() throws PFEException{
		try {
			String requete = "SELECT * FROM Jurys ";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			ArrayList<Jurys> all=new ArrayList<Jurys>();
			while (rs.next()) {
				Jurys ju=new Jurys();		
				ju.setId(rs.getInt("id"));
				all.add(find(ju));
			}
			rs.close();
			ps.close();
			return all;
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Jurys find(Jurys obj) throws PFEException{
		try {
			String requete = "SELECT * FROM Jurys Where id=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ResultSet rs;
			ps.setString(1, ""+obj.getId());
			rs =ps.executeQuery();
			EnseignantDAO rech=new EnseignantDAO();
			
			Jurys ju;
			if (rs.next()) {
				//recherche presidant 
				Enseignant presidant=new Enseignant();
				presidant.setCIN(rs.getInt("CINPRESIDENT"));
				//recherche rapporteur
				Enseignant rapporteur=new Enseignant();
				rapporteur.setCIN(rs.getInt("CINRAPPORTEUR"));
				//recherche examinateur  
				Enseignant examinateur=new Enseignant();
				examinateur.setCIN(rs.getInt("CINEXAMINATEUR"));
	            ju=new Jurys(rs.getInt("Id"),rech.find(presidant),rech.find(rapporteur),rech.find(examinateur));
	            rs.close();
				ps.close();
	            return ju;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean existe(Jurys obj) {
		try {
			String requete = "SELECT * FROM Jurys Where id=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getId());
			ResultSet  rs;
			rs =ps.executeQuery();
			if(rs.next()) {
				rs.close();
				ps.close();
				return true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean combinaison_existe(Jurys obj) throws PFEException{
		ArrayList<Jurys> jurys=(ArrayList<Jurys>) findAll();
		for(Jurys el:jurys) {
			if(obj.getPresident().getCIN()==el.getPresident().getCIN() &&
			   obj.getRapporteur().getCIN()==el.getRapporteur().getCIN()&&
			   obj.getExaminateur().getCIN()==el.getExaminateur().getCIN())
				return true;
		}
		return false;
	}
}
