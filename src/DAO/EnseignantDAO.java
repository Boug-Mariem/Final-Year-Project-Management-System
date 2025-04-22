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

public class EnseignantDAO extends DAO<Enseignant> {

	@Override
	public Enseignant create(Enseignant ens) throws PFEException{
		try {//tester s'il existe un enseigant avec ce cin
			if(existe(ens))
				throw new PFEException(8);
			//tester s'il existe un enseigant avec ce email
			if(Emailexiste(ens))
				throw new PFEException(45);
			//tester s'il existe un enseigant avec ce numero de tel
			if(NumTelexiste(ens))
				throw new PFEException(44);
			
			
			//tester s'il existe un encadreur societe avec ce cin
			EncadreurSociete enctest=new EncadreurSociete();
			enctest.setCIN(ens.getCIN());
			EncadreurSocieteDAO rechenc= new EncadreurSocieteDAO();
			if(rechenc.existe(enctest))
				throw new PFEException(48);
			
			
			//tester s'il existe un etudiant avec ce cin
			Etudiant etudtest=new Etudiant();
			etudtest.setCIN(ens.getCIN());
			EtudiantDAO rechetud= new EtudiantDAO();
			if(rechetud.existe(etudtest))
				throw new PFEException(48);
			//tester s'il existe un etudiant avec ce email
			etudtest.setEmail(ens.getEmail());
			if(rechetud.Emailexiste(etudtest))
				throw new PFEException(45);
			//tester s'il existe un etudiant avec ce numero de tel
			etudtest.setNumTel(ens.getNumTel());
			if(rechetud.NumTelexiste(etudtest))
				throw new PFEException(44);
			
			//
			String requete = "INSERT INTO Enseignant VALUES ('" + ens.getCIN() + "', '" + ens.getNom() + "', '" + ens.getPrenom() + "','"+ens.getEmail()+"','"+ens.getNumTel()+"')";
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Enseignant ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Enseignant update(Enseignant e) throws PFEException{
		try {
			if(!existe(e))
				throw new PFEException(9);
			
			//tester s'il existe un ens avec ce email
			if(EmailexisteModif(e))
				throw new PFEException(45);
			//tester s'il existe un ens avec ce numero de tel
			if(NumTelexisteModif(e))
				throw new PFEException(44);
			
			//tester s'il existe un etud avce ce email
			Etudiant enstest=new Etudiant();
			EtudiantDAO rechesn= new EtudiantDAO();
			enstest.setEmail(e.getEmail());
			if(rechesn.Emailexiste(enstest))
				throw new PFEException(45);
			//tester s'il existe un enseignant avce ce numero de tel
			enstest.setNumTel(e.getNumTel());
			if(rechesn.NumTelexiste(enstest))
				throw new PFEException(44);
			
			
			String requete= "UPDATE Enseignant SET Nom = ?, Prenom= ?, Email = ?,NUMTEL = ? WHERE CIN=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, e.getNom());
	        ps.setString(2, e.getPrenom());
	        ps.setString(3, e.getEmail());
	        ps.setString(4, e.getNumTel());
	        ps.setInt(5, e.getCIN() );
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
	public boolean delete(Enseignant ens)throws PFEException {
		try {
			if(!existe(ens))
				throw new PFEException(9);
			//tester s'il existe un projet ou  ce ens est encadreur
			String requetePr = "Select * FROM Projet WHERE CINENCADREUR="+ens.getCIN();
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
			
			//tester s'il existe une soutenance ou ce ens est un jurys 
			
			String requeteJ = "Select * FROM Jurys WHERE CINPRESIDENT=? OR CINRAPPORTEUR=? OR CINEXAMINATEUR=?";//+ens.getCIN();
			PreparedStatement psJ;
			psJ = connbase.prepareStatement(requeteJ);
			psJ.setInt(1,ens.getCIN());
			psJ.setInt(2,ens.getCIN());
			psJ.setInt(3,ens.getCIN());
			ResultSet rsJ;
			rsJ =psJ.executeQuery();
			while(rsJ.next()) {
				Jurys ju=new Jurys();
				ju.setId(rsJ.getInt("ID"));
				JurysDAO rechJ =new JurysDAO();
				rechJ.delete(ju);
			}
			rsJ.close();
			psJ.close();
			
			//
			String requete = "DELETE FROM Enseignant WHERE CIN="+ens.getCIN();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Enseignant supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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
	public List<Enseignant> findAll() throws PFEException{
		ArrayList<Enseignant> all=new ArrayList<Enseignant>();
		try {
			String requete = "SELECT * FROM Enseignant ";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			
			while (rs.next()) {
				Enseignant e=new Enseignant();		
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
	public Enseignant find(Enseignant obj)throws PFEException {
		try {
			String requete = "SELECT * FROM Enseignant Where CIN= ? ";
			PreparedStatement ps;
			ps =connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getCIN());
			ResultSet rs;
			rs =ps.executeQuery();
		
			Enseignant ens;
			if (rs.next()) {
				ens=new Enseignant(rs.getInt("CIN"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Email"),rs.getString("NUMTEL"));
				rs.close();
				ps.close();
				return ens;
	        } else {
	        	rs.close();
				ps.close();
	        	throw new PFEException(9);
	        }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean existe(Enseignant obj) {
		try {
			String requete = "SELECT * FROM Enseignant Where CIN=?";
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
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Enseignant> findByNom(Enseignant es)throws PFEException {
		ArrayList<Enseignant> all=new ArrayList<Enseignant>();
		try {
			String requete = "SELECT * FROM Enseignant WHERE  NOM=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getNom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Enseignant e=new Enseignant();		
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
	
	public List<Enseignant> findByPrenom(Enseignant es)throws PFEException {
		ArrayList<Enseignant> all=new ArrayList<Enseignant>();
		try {
			String requete = "SELECT * FROM Enseignant WHERE  Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Enseignant e=new Enseignant();		
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
	
	public List<Enseignant> findByNomPrenom(Enseignant es) throws PFEException {
		ArrayList<Enseignant> all=new ArrayList<Enseignant>();
		try {
			String requete = "SELECT * FROM Enseignant WHERE  Nom=? AND Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, es.getNom());
			ps.setString(2, es.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Enseignant e=new Enseignant();		
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
	
	public List<Soutenance> emploiEns(Enseignant ens)throws PFEException{
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			if(!existe(ens))
				throw new PFEException(9);
			String requete ="SELECT * FROM soutenance ,Jurys, Projet "
					+ "WHERE soutenance.idjurys =jurys.id and soutenance.idprojet=projet.id "
					+ "and( (jurys.cinpresident = ? OR jurys.cinrapporteur = ? OR jurys.cinexaminateur = ?) OR (projet.cinencadreur = ?))"
					+ "ORDER BY soutenance.DateHeure";
			
			          
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setInt(1, ens.getCIN());
			ps.setInt(2, ens.getCIN());
			ps.setInt(3, ens.getCIN());
			ps.setInt(4, ens.getCIN());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Soutenance e=new Soutenance();		
				e.setId(rs.getInt("id"));
				SoutenanceDAO rechsout=new SoutenanceDAO();
				all.add(rechsout.find(e));
			}
			rs.close();
			ps.close();
			return all;
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}		
		return all;
	}
	
	public boolean Emailexiste(Enseignant obj) {
		try {
			String requete = "SELECT * FROM Enseignant Where email=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getEmail());
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
	
	public boolean NumTelexiste(Enseignant obj) {
		try {
			String requete = "SELECT * FROM Enseignant Where NUMTEL=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1,obj.getNumTel());
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
	
	public boolean EmailexisteModif(Enseignant obj) {
		try {
			String requete = "SELECT * FROM Enseignant Where email=? And CIN <> ?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getEmail());
			ps.setString(2, ""+obj.getCIN());
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
	
	public boolean NumTelexisteModif(Enseignant obj) {
		try {
			String requete = "SELECT * FROM Enseignant Where NUMTEL=? And CIN <> ?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1,obj.getNumTel());
			ps.setString(2, ""+obj.getCIN());
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
}
