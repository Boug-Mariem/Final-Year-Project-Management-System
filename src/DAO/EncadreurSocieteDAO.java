package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import interfacePFE.MyConnection;
import projetPFE.*;

public class EncadreurSocieteDAO extends DAO<EncadreurSociete> {

	@Override
	public EncadreurSociete create(EncadreurSociete enc) throws PFEException{
		try {//tester s'il existe un encadreur socite avec ce cin
			if(existe(enc))
				throw new PFEException(10);
			//tester s'il existe un enseignant avec ce cin
			Enseignant enstest=new Enseignant();
			enstest.setCIN(enc.getCIN());
			EnseignantDAO rechesn= new EnseignantDAO();
			if(rechesn.existe(enstest))
				throw new PFEException(48);
			//tester s'il existe un etudiant avec ce cin
			Etudiant etudtest=new Etudiant();
			etudtest.setCIN(enc.getCIN());
			EtudiantDAO rechetud= new EtudiantDAO();
			if(rechetud.existe(etudtest))
				throw new PFEException(48);
			//
			String requete = "INSERT INTO EncadreurSociete VALUES ('" + enc.getCIN() + "', '" + enc.getNom() + "', '" + enc.getPrenom() + "')";
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Encadreur Societe ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public EncadreurSociete update(EncadreurSociete e) throws PFEException{
		try {
			if(!existe(e))
				throw new PFEException(11);
			String requete= "UPDATE EncadreurSociete SET Nom = ?, Prenom=? WHERE CIN=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
	        ps.setString(1, e.getNom());
	        ps.setString(2, e.getPrenom());
	        ps.setInt(3, e.getCIN() );
			int rs;
			rs =ps.executeUpdate();
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"lignes ont été mises à jour avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);

	        }
			
			ps.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(EncadreurSociete enc)throws PFEException {
		try {
			if(!existe(enc))
				throw new PFEException(11);
			
			//tester s'il est un encadreur d'un projet
			String requetePr = "Select * FROM Projet WHERE CINENCADREURSOC="+enc.getCIN();
			Statement psPr;
			psPr = connbase.createStatement();
			ResultSet rsPr;
			rsPr =psPr.executeQuery(requetePr);
			while(rsPr.next()) {
				Projet pr=new Projet();
				pr.setId(rsPr.getInt("ID"));
				pr.setTitre(rsPr.getString("Titre"));
				ProjetDAO rechpr =new ProjetDAO();
				rechpr.delete(pr);
			}
			rsPr.close();
			psPr.close();
			
			//
			String requete = "DELETE FROM EncadreurSociete WHERE CIN="+enc.getCIN();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Encadreur Societe supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<EncadreurSociete> findAll() throws PFEException{
		ArrayList<EncadreurSociete> all=new ArrayList<EncadreurSociete>();
		try {
			String requete = "SELECT * FROM EncadreurSociete ";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			
			while (rs.next()) {
				EncadreurSociete e=new EncadreurSociete();		
				e.setCIN(rs.getInt("CIN"));
				all.add(find(e));
			}
			rs.close();
			ps.close();
			return all;
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return all;
	}

	@Override
	public EncadreurSociete find(EncadreurSociete obj)throws PFEException {
		try {
			String requete = "SELECT * FROM EncadreurSociete Where CIN=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getCIN());
			ResultSet rs;
			rs =ps.executeQuery();
			EncadreurSociete enc;
			if (rs.next()) {
				enc=new EncadreurSociete(rs.getInt("CIN"),rs.getString("Nom"),rs.getString("Prenom"));
				rs.close();
				ps.close();
				return enc;
	        } else {
	        	rs.close();
				ps.close();
	        	throw new PFEException(11);
	        }	
		}  
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean existe(EncadreurSociete obj) {
		try {
			String requete = "SELECT * FROM EncadreurSociete Where CIN=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getCIN());
			ResultSet  rs;
			rs =ps.executeQuery();
			if(rs.next()) {
				rs.close();
				ps.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<EncadreurSociete> findByNom(EncadreurSociete es) throws PFEException {
		ArrayList<EncadreurSociete> all=new ArrayList<EncadreurSociete>();
		try {
			String requete = "SELECT * FROM EncadreurSociete WHERE  NOM=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getNom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				EncadreurSociete e=new EncadreurSociete();		
				e.setCIN(rs.getInt("CIN"));
				all.add(find(e));
			}
			rs.close();
			ps.close();
			return all;
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return all;
	}
	
	public List<EncadreurSociete> findByPrenom(EncadreurSociete es) throws PFEException{
		ArrayList<EncadreurSociete> all=new ArrayList<EncadreurSociete>();
		try {
			String requete = "SELECT * FROM EncadreurSociete WHERE  Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				EncadreurSociete e=new EncadreurSociete();		
				e.setCIN(rs.getInt("CIN"));
				all.add(find(e));
			}
			rs.close();
			ps.close();
			return all;
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return all;
	}
	
	public List<EncadreurSociete> findByNomPrenom(EncadreurSociete es) throws PFEException{
		ArrayList<EncadreurSociete> all=new ArrayList<EncadreurSociete>();
		try {
			String requete = "SELECT * FROM EncadreurSociete WHERE  Nom=? AND Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getNom());
			ps.setString(2, es.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				EncadreurSociete e=new EncadreurSociete();		
				e.setCIN(rs.getInt("CIN"));
				all.add(find(e));
			}
			rs.close();
			ps.close();
			return all;
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return all;
	}


}
