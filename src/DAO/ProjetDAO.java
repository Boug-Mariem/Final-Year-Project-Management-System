package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import interfacePFE.MyConnection;
import projetPFE.*;

// existe-il une exception por date de debut du projet ? 
public class ProjetDAO extends DAO<Projet> {

	@Override
	public Projet create(Projet pr) throws PFEException{
		try {
			// test existance du titre du projet
			if (existe(pr))
				throw new PFEException(12);
			// test existnace des etudiant dans la base
			EtudiantDAO testetud = new EtudiantDAO();
			if (pr.getSecondEtudiant() == null) {
				if (!testetud.existe(pr.getFirstEtudiant()))
					throw new PFEException(16);
			} else {
				if (!testetud.existe(pr.getFirstEtudiant()))
					throw new PFEException(16);
				if (!testetud.existe(pr.getSecondEtudiant()))
					throw new PFEException(17);
				if(pr.getFirstEtudiant().getCIN()==pr.getSecondEtudiant().getCIN())
					throw new PFEException(39);
				if(!pr.getFirstEtudiant().getGroupe().equals(pr.getSecondEtudiant().getGroupe()))
					throw new PFEException(49);
			}
			// test existnace des enseignant dans la base
			EnseignantDAO testens = new EnseignantDAO();
			if (!testens.existe(pr.getEncadreur()))
				throw new PFEException(18);
			// test existance des encadreur socite
			EncadreurSocieteDAO testencSoc = new EncadreurSocieteDAO();
			if (!testencSoc.existe(pr.getEncadreurSoc()))
				throw new PFEException(19);

			// test des affectation des etudiant
			if (pr.getSecondEtudiant() == null) {
				if (etudiantDejaAffecte(pr.getFirstEtudiant()))
					throw new PFEException(14);
			} else {
				if (etudiantDejaAffecte(pr.getFirstEtudiant()))
					throw new PFEException(14);
				if (etudiantDejaAffecte(pr.getSecondEtudiant()))
					throw new PFEException(15);
			}
			String requete="";
			Date date = Date.valueOf(pr.getDateDebut());

			PreparedStatement ps=null;

			if (pr.getSecondEtudiant() != null) {
				requete = "INSERT INTO Projet"
				  +" (TITRE,DATEDEBUT,CINENCADREUR,CINENCADREURSOC,CINFIRSTETUDIANT,CINSECONDETUDIANT) "
				  + " VALUES (?, ?, ?, ?, ?,?)";
				 
				ps = connbase.prepareStatement(requete);
				ps.setString(1, pr.getTitre());
				ps.setDate(2, date);
				ps.setInt(3, pr.getEncadreur().getCIN());
				ps.setInt(4, pr.getEncadreurSoc().getCIN());
				ps.setInt(5, pr.getFirstEtudiant().getCIN());
				ps.setInt(6, pr.getSecondEtudiant().getCIN());


			} else {
				
				requete = "INSERT INTO Projet"
						  +" (TITRE,DATEDEBUT,CINENCADREUR,CINENCADREURSOC,CINFIRSTETUDIANT) "
						  + " VALUES (? , ?, ?, ?, ?)";
						 
				ps = connbase.prepareStatement(requete);
				ps.setString(1, pr.getTitre());
				ps.setDate(2, date);
				ps.setInt(3, pr.getEncadreur().getCIN());
				ps.setInt(4, pr.getEncadreurSoc().getCIN());
				ps.setInt(5, pr.getFirstEtudiant().getCIN());
				 
			}
			
			int rs;
			rs = ps.executeUpdate();
			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "Projet ajouté avec succès", "Succès",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Projet update(Projet pr) throws PFEException{
		try {
			// test existance du titre du projet
			if (!existe(pr))
				throw new PFEException(13);
			// test exisatnace des enseignant dans la base
			EnseignantDAO testens = new EnseignantDAO();
			if (!testens.existe(pr.getEncadreur()))
				throw new PFEException(18);
			// test existance des encadreur socite
			EncadreurSocieteDAO testencSoc = new EncadreurSocieteDAO();
			if (!testencSoc.existe(pr.getEncadreurSoc()))
				throw new PFEException(19);
			//
			Date date = Date.valueOf(pr.getDateDebut());
			String requete;
			PreparedStatement ps;
			if (pr.getSecondEtudiant() != null) {
				requete = "UPDATE Projet SET Titre =?, DATEDEBUT = ?, CINENCADREUR=?,CINENCADREURSOC =? ,CINFIRSTETUDIANT=?,CINSECONDETUDIANT=? WHERE id=?";// ,
																											// CINFIRSTETUDIANT=?,CINSECONDETUDIANT=?";
				ps = connbase.prepareStatement(requete);
				ps.setString(1, pr.getTitre());
				ps.setDate(2, date);
				ps.setInt(3, pr.getEncadreur().getCIN());
				ps.setInt(4, pr.getEncadreurSoc().getCIN());
				ps.setInt(5,pr.getFirstEtudiant().getCIN());
				ps.setInt(6,pr.getSecondEtudiant().getCIN());
				ps.setInt(7,pr.getId());
				 

			} else {
				requete = "UPDATE Projet SET Titre =?, DATEDEBUT = ?, CINENCADREUR=?,CINENCADREURSOC =?,CINFIRSTETUDIANT=? WHERE id=?";// ,
																											// CINFIRSTETUDIANT=?";
				ps = connbase.prepareStatement(requete);
				ps.setString(1, pr.getTitre());
				ps.setDate(2, date);
				ps.setInt(3, pr.getEncadreur().getCIN());
				ps.setInt(4, pr.getEncadreurSoc().getCIN());
				ps.setInt(5,pr.getFirstEtudiant().getCIN());
				ps.setInt(6,pr.getId());
			}

			int rs;
			rs = ps.executeUpdate();
			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "lignes ont été mises à jour avec succès", "Succès",
						JOptionPane.INFORMATION_MESSAGE);

			}
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Projet pr)throws PFEException {
		try {
			if (!existe(pr))
				throw new PFEException(13);
			//tester si ce projet possede une setenance
			String requeteSout = "Select * FROM Soutenance WHERE IDPROJET="+pr.getId();
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
			
			
			///
			String requete = "DELETE FROM Projet WHERE id=" + pr.getId();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs = ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null, "Projet supprimé avec succès", "Succès",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Projet> findAll() throws PFEException {
		try {
			String requete = "SELECT * FROM Projet ";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs = ps.executeQuery(requete);
			ArrayList<Projet> all = new ArrayList<Projet>();
			while (rs.next()) {
				Projet pr = new Projet();
				pr.setId(rs.getInt("id"));
				all.add(find(pr));
			}
			rs.close();
			ps.close();
			return all;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Projet find(Projet obj)throws PFEException {
		try {
			String requete = "SELECT * FROM Projet Where id=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, "" + obj.getId());
			ResultSet rs;
			rs = ps.executeQuery();
			Projet pr;
			if (rs.next()) {
				// recherche encadreur
				Enseignant encadreur = new Enseignant();
				encadreur.setCIN(rs.getInt("CINENCADREUR"));
				EnseignantDAO rechencadreur = new EnseignantDAO();
				// recherche encadreur societe
				EncadreurSociete encadreurSoc = new EncadreurSociete();
				encadreurSoc.setCIN(rs.getInt("CINENCADREURSOC"));
				EncadreurSocieteDAO rechencadreurSoc = new EncadreurSocieteDAO();
				// recherche etudiant 1
				Etudiant etud1 = new Etudiant();
				etud1.setCIN(rs.getInt("CINFIRSTETUDIANT"));
				EtudiantDAO rechetud = new EtudiantDAO();
                Date db=rs.getDate("DateDebut");
				LocalDate dateprojet = db.toLocalDate();
				
				if (rs.getString("CINSECONDETUDIANT") == null)
					pr = new Projet(rs.getInt("id"),rs.getString("Titre"), dateprojet, rechencadreur.find(encadreur),
							rechencadreurSoc.find(encadreurSoc), rechetud.find(etud1));
				else {
					// recherche etudiant 2
					Etudiant etud2 = new Etudiant();
					etud2.setCIN(rs.getInt("CINSECONDETUDIANT"));
					pr = new Projet(rs.getInt("id"),rs.getString("Titre"), dateprojet, rechencadreur.find(encadreur),
							rechencadreurSoc.find(encadreurSoc), rechetud.find(etud1), rechetud.find(etud2));
					
				}
				rs.close();
				ps.close();
				return pr;
			} else {
				rs.close();
				ps.close();
				throw new PFEException(13);
			}
			

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean existe(Projet obj) {
		try {
			String requete = "SELECT * FROM Projet Where Titre =?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, "" + obj.getTitre());
			ResultSet rs;
			rs = ps.executeQuery();
			if (rs.next()) {
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

	public boolean etudiantDejaAffecte(Etudiant etud) throws PFEException{
		ArrayList<Projet> projets = (ArrayList<Projet>) findAll();
		for (Projet el : projets) {
			if (el.getSecondEtudiant() != null) {
				if (etud.getCIN() == el.getFirstEtudiant().getCIN() || etud.getCIN() == el.getSecondEtudiant().getCIN())
					return true;
			} else {
				if (etud.getCIN() == el.getFirstEtudiant().getCIN())
					return true;
			}
		}

		return false;
	}
	
	public Projet findByTitre(Projet obj) throws PFEException{
		try {
			String requete = "SELECT * FROM Projet Where Titre=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, obj.getTitre());
			ResultSet rs;
			rs = ps.executeQuery();
			Projet pr;
			if (rs.next()) {
				// recherche encadreur
				Enseignant encadreur = new Enseignant();
				encadreur.setCIN(rs.getInt("CINENCADREUR"));
				EnseignantDAO rechencadreur = new EnseignantDAO();
				// recherche encadreur societe
				EncadreurSociete encadreurSoc = new EncadreurSociete();
				encadreurSoc.setCIN(rs.getInt("CINENCADREURSOC"));
				EncadreurSocieteDAO rechencadreurSoc = new EncadreurSocieteDAO();
				// recherche etudiant 1
				Etudiant etud1 = new Etudiant();
				etud1.setCIN(rs.getInt("CINFIRSTETUDIANT"));
				EtudiantDAO rechetud = new EtudiantDAO();
				
                Date db=rs.getDate("DateDebut");
				LocalDate dateprojet = db.toLocalDate();
				
				if (rs.getString("CINSECONDETUDIANT") == null)
					pr = new Projet(rs.getInt("id"),rs.getString("Titre"), dateprojet, rechencadreur.find(encadreur),
							rechencadreurSoc.find(encadreurSoc), rechetud.find(etud1));
				else {
					// recherche etudiant 2
					Etudiant etud2 = new Etudiant();
					etud2.setCIN(rs.getInt("CINSECONDETUDIANT"));
					pr = new Projet(rs.getInt("id"),rs.getString("Titre"), dateprojet, rechencadreur.find(encadreur),
							rechencadreurSoc.find(encadreurSoc), rechetud.find(etud1), rechetud.find(etud2));
					
				}
				rs.close();
				ps.close();
				return pr;
			} else {
				throw new PFEException(13);
			}

		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Projet> findAllByStudent(Etudiant etud) throws PFEException {
		try {
			String requete = "SELECT * FROM Projet "
					+ "Where Projet.CINFIRSTETUDIANT = "+etud.getCIN()+" or Projet.CINSECONDETUDIANT= "+etud.getCIN();
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs = ps.executeQuery(requete);
			ArrayList<Projet> all = new ArrayList<Projet>();
			while (rs.next()) {
				Projet pr = new Projet();
				pr.setId(rs.getInt("id"));
				all.add(find(pr));
			}
			rs.close();
			ps.close();
			return all;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public List<Projet> findAllByEncadreur(Enseignant ens) throws PFEException {
		try {
			String requete = "SELECT * FROM Projet "
					+ "Where CINENCADREUR = "+ens.getCIN();
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs = ps.executeQuery(requete);
			ArrayList<Projet> all = new ArrayList<Projet>();
			while (rs.next()) {
				Projet pr = new Projet();
				pr.setId(rs.getInt("id"));
				all.add(find(pr));
			}
			rs.close();
			ps.close();
			return all;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
}
