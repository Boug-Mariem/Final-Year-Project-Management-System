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
import projetPFE.Etudiant.Groupe;


public class EtudiantDAO extends DAO<Etudiant> {

	@Override
	public Etudiant create(Etudiant etud)throws PFEException {
		try {
			//tester s'il existe un etudiant avec ce cin
			if(existe(etud))
				throw new PFEException(6);
			//tester s'il existe un etudiant avec ce email
			if(Emailexiste(etud))
				throw new PFEException(45);
			//tester s'il existe un etudiant avec ce numero de tel
			if(NumTelexiste(etud))
				throw new PFEException(44);
			
			//tester s'il existe un enseignant avce ce cin
			Enseignant enstest=new Enseignant();
			enstest.setCIN(etud.getCIN());
			EnseignantDAO rechesn= new EnseignantDAO();
			if(rechesn.existe(enstest))
				throw new PFEException(48);
			//tester s'il existe un enseignant avce ce email
			enstest.setEmail(etud.getEmail());
			if(rechesn.Emailexiste(enstest))
				throw new PFEException(45);
			//tester s'il existe un enseignant avce ce numero de tel
			enstest.setNumTel(etud.getNumTel());
			if(rechesn.NumTelexiste(enstest))
				throw new PFEException(44);
			
			//tester s'il existe un encadreur societe avce ce cin
			EncadreurSociete enctest=new EncadreurSociete();
			enctest.setCIN(etud.getCIN());
			EncadreurSocieteDAO rechenc= new EncadreurSocieteDAO();
			if(rechenc.existe(enctest))
				throw new PFEException(48);
			//
			String groupetud=etud.getGroupe().name();
			String requete = "INSERT INTO Etudiant VALUES ('" + etud.getCIN() + "', '" + etud.getNom() + "', '" + etud.getPrenom() + "', '" + groupetud + "','"+etud.getNumTel()+"','"+etud.getEmail()+"')";
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Étudiant ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
		} 
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	@Override
	public Etudiant update(Etudiant e)throws PFEException {
		try {
			if(!existe(e))
				throw new PFEException(7);
			//tester s'il existe un etudiant avec ce email
			if(EmailexisteModif(e))
				throw new PFEException(45);
			//tester s'il existe un etudiant avec ce numero de tel
			if(NumTelexisteModif(e))
				throw new PFEException(44);
			
			//tester s'il existe un enseignant avce ce email
			Enseignant enstest=new Enseignant();
			EnseignantDAO rechesn= new EnseignantDAO();
			enstest.setEmail(e.getEmail());
			if(rechesn.Emailexiste(enstest))
				throw new PFEException(45);
			//tester s'il existe un enseignant avce ce numero de tel
			enstest.setNumTel(e.getNumTel());
			if(rechesn.NumTelexiste(enstest))
				throw new PFEException(44);
			
			
			String groupetud=e.getGroupe().name();
	        String requete = "UPDATE Etudiant SET Nom = ?, Prenom = ?, Groupe = ?, NUMTEL= ?, Email= ? WHERE CIN = ?";
	        PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
	        ps.setString(1, e.getNom());
	        ps.setString(2, e.getPrenom());
	        ps.setString(3,groupetud );
	        ps.setString(4,e.getNumTel() );
	        ps.setString(5,e.getEmail());
	        ps.setInt(6, e.getCIN() );
	        
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
	public boolean delete(Etudiant etud) throws PFEException{
		try {
			if(!existe(etud))
				throw new PFEException(7);
			
			//tester s'il existe un projet e ce etud
			String requetePr = "Select * FROM Projet WHERE CINFIRSTETUDIANT="+etud.getCIN()+" OR CINSECONDETUDIANT="+etud.getCIN();
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
			String requete = "DELETE FROM Etudiant WHERE CIN="+etud.getCIN();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Étudiant supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
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
	public List<Etudiant> findAll()throws PFEException {
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Etudiant ";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			
			while (rs.next()) {
				Etudiant e=new Etudiant();		
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
	public boolean existe(Etudiant obj) {
		try {
			String requete = "SELECT * FROM Etudiant Where CIN=?";
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
	@Override
	public Etudiant find(Etudiant obj) throws PFEException {
		try {
			String requete = "SELECT * FROM Etudiant Where CIN=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getCIN());
			ResultSet rs;
			rs =ps.executeQuery();
			Etudiant etud;
		
			if (rs.next()) {
	             etud = new Etudiant(rs.getInt("CIN"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Groupe"),rs.getString("Email"),rs.getString("NUMTEL"));
	             rs.close();
				 ps.close();
	             return etud;
	        } else {
	        	rs.close();
				ps.close();
	        	throw new PFEException(7);
	        }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Etudiant> findByGrp(Etudiant etud)throws PFEException {
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String groupetud=etud.getGroupe().name();
			String requete = "SELECT * FROM Etudiant WHERE  GROUPE=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, groupetud);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Etudiant e=new Etudiant();		
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
	
	public List<Etudiant> findByNom(Etudiant etud)throws PFEException {
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Etudiant WHERE  NOM=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, etud.getNom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Etudiant e=new Etudiant();		
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
	
	public List<Etudiant> findByPrenom(Etudiant etud) throws PFEException{
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Etudiant WHERE  Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, etud.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Etudiant e=new Etudiant();		
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
	
	public List<Etudiant> findByNomPrenom(Etudiant etud)throws PFEException {
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Etudiant WHERE  Nom=? AND Prenom=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, etud.getNom());
			ps.setString(2, etud.getPrenom());
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Etudiant e=new Etudiant();		
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
	
	public List<Etudiant> findByNoteEg(float N) throws PFEException{
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Soutenance WHERE  Note = ?"; //on va utiliser id projet
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setFloat(1, N);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Projet p=new Projet();	
				ProjetDAO rechp=new ProjetDAO();
				p.setId(rs.getInt("IDPROJET"));
				Projet p2=rechp.find(p);
				//ajout des etudiant 
				Etudiant e1=p2.getFirstEtudiant();	
				all.add(e1);
				
				if(p2.getSecondEtudiant()!=null) {
					Etudiant e2=p2.getSecondEtudiant();	
					all.add(e2);
				}
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
	
	public List<Etudiant> findByNoteSupEg(float N) throws PFEException{
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Soutenance WHERE  Note >= ?"; //on va utiliser id projet
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setFloat(1, N);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Projet p=new Projet();	
				ProjetDAO rechp=new ProjetDAO();
				p.setId(rs.getInt("IDPROJET"));
				Projet p2=rechp.find(p);
				//ajout des etudiant 
				Etudiant e1=p2.getFirstEtudiant();	
				all.add(e1);
				
				if(p2.getSecondEtudiant()!=null) {
					Etudiant e2=p2.getSecondEtudiant();	
					all.add(e2);
				}
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
	
	public List<Etudiant> findByNoteInfEg(float N)throws PFEException {
		ArrayList<Etudiant> all=new ArrayList<Etudiant>();
		try {
			String requete = "SELECT * FROM Soutenance WHERE  Note <= ?"; //on va utiliser id projet
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setFloat(1, N);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				Projet p=new Projet();	
				ProjetDAO rechp=new ProjetDAO();
				p.setId(rs.getInt("IDPROJET"));
				Projet p2=rechp.find(p);
				//ajout des etudiant 
				Etudiant e1=p2.getFirstEtudiant();	
				all.add(e1);
				
				if(p2.getSecondEtudiant()!=null) {
					Etudiant e2=p2.getSecondEtudiant();	
					all.add(e2);
				}
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
	
	public boolean Emailexiste(Etudiant obj) {
		try {
			String requete = "SELECT * FROM Etudiant Where email=?";
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
	
	public boolean NumTelexiste(Etudiant obj) {
		try {
			String requete = "SELECT * FROM Etudiant Where NUMTEL=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getNumTel());
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
	
	public boolean EmailexisteModif(Etudiant obj) {
		try {
			String requete = "SELECT * FROM Etudiant Where email=? AND CIN <> ?";
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
	
	public boolean NumTelexisteModif(Etudiant obj) {
		try {
			String requete = "SELECT * FROM Etudiant Where NUMTEL=? AND CIN <> ?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getNumTel());
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
