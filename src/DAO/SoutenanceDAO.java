package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import interfacePFE.MyConnection;
import projetPFE.*;
//dans la base de donner validation est de type number(1) la valeur 1:true la valeur 0:false
//datedebut et heure sont dans un seul attribus dans la base de donne: DateHeure TIMESTAMP 

//dans la creation de soutenance (interface)la fonction create jurys va rendre le jurys dont on a besoin
public class SoutenanceDAO extends DAO<Soutenance> {
	@Override
	public Soutenance create(Soutenance sout) throws PFEException{
		try {
			// test sur existance du projet dans la base
			ProjetDAO testprojet=new ProjetDAO();
			if(!testprojet.existe(sout.getProjet()))
				throw new PFEException(13);
			//test sur le projet deja progarmme ou non
			if(soutenance_projet(sout))
				throw new PFEException(24);
			//test existance jurys dans la base
			JurysDAO testjurys=new JurysDAO();
			if(!testjurys.existe( sout.getJurys()))
				throw new PFEException(23);
			//test encadreur different du Jurys 
			if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getPresident().getCIN())
				throw new PFEException(29);
			if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getRapporteur().getCIN())
				throw new PFEException(30);
			if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getExaminateur().getCIN())
				throw new PFEException(31);
			//test emplois des enseignant
			if(PresidentOccuper(sout))
				throw new PFEException(32);
			if(RapporteurOccuper(sout))
				throw new PFEException(33);
			if(ExaminateurOccuper(sout))
				throw new PFEException(34);
			if(EncadreurOccuper(sout))
				throw new PFEException(35);
			
			//test locale vide 
			if(LocaleOccuper(sout))
				throw new PFEException(38);
			
			//
			String localsout=sout.getlocalsout().name();
			//conversion du date et heure en timestamp de la base
			LocalDateTime deteheure=LocalDateTime.of(sout.getDateSoutenance(), sout.getHeureSoutenance());
			Timestamp timestamp = Timestamp.valueOf(deteheure);
			//conversion de timestamp en datefinal sans millisecondes
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datefinale = sdf.format(timestamp);

			String requete = "INSERT INTO Soutenance (DateHeure,local,idJurys,idProjet) "
					+ "VALUES ( TO_DATE('"+datefinale+"', 'YYYY-MM-DD HH24:MI:SS'), '" + localsout+"','"+sout.getJurys().getId()+"','"+ sout.getProjet().getId()+"')";
			
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Soutenance ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}

			ps.close();
		} 
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Soutenance update(Soutenance sout) throws PFEException{
		try {
			
			// test sur existance du projet dans la base
						ProjetDAO testprojet=new ProjetDAO();
						if(!testprojet.existe(sout.getProjet()))
							throw new PFEException(13);
						//test sur le projet deja progarmme ou non
						if(!soutenance_projet(sout))
							throw new PFEException(24);
						//test existance jurys dans la base
						JurysDAO testjurys=new JurysDAO();
						if(!testjurys.existe( sout.getJurys()))
							throw new PFEException(23);
						//test encadreur different du Jurys 
						if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getPresident().getCIN())
							throw new PFEException(29);
						if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getRapporteur().getCIN())
							throw new PFEException(30);
						if(sout.getProjet().getEncadreur().getCIN()==sout.getJurys().getExaminateur().getCIN())
							throw new PFEException(31);
						//test emplois des enseignant
						if(PresidentOccuper(sout))
							throw new PFEException(32);
						if(RapporteurOccuper(sout))
							throw new PFEException(33);
						if(ExaminateurOccuper(sout))
							throw new PFEException(34);
						if(EncadreurOccuper(sout))
							throw new PFEException(35);
						
						//test locale vide 
						if(LocaleOccuper(sout))
							throw new PFEException(38);
			
			String localsout=sout.getlocalsout().name();
			LocalDateTime deteheure=LocalDateTime.of(sout.getDateSoutenance(), sout.getHeureSoutenance());
			Timestamp deteheuretimestamp = Timestamp.valueOf(deteheure);
			//je doit verifier la format du timestamp
			//conversion de timestamp en datefinal sans millisecondes
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datefinale = sdf.format(deteheuretimestamp);
			
			String requete = "UPDATE Soutenance SET DATEHEURE =?, Local = ?, idJurys=?,idProjet=? WHERE id= ?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setTimestamp(1,deteheuretimestamp);
	        ps.setString(2, localsout);
	        ps.setInt(3,sout.getJurys().getId() );
	        ps.setInt(4,sout.getProjet().getId() );
	        ps.setInt(5,sout.getId() );
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
	public boolean delete(Soutenance sout) throws PFEException{
		try {
			/*if(!soutenance_projet(sout))
				throw new PFEException(25);*/
			if(!existe(sout))
				throw new PFEException(25);
			String requete = "DELETE FROM Soutenance WHERE id="+sout.getId();
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Soutenance supprimé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			ps.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
	public Soutenance updatenote(Soutenance sout)throws PFEException {
		try {
			if(!soutenance_projet(sout))
				throw new PFEException(25);
			
			String requete = "UPDATE Soutenance SET validation =?, Note = ? WHERE id= ?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			if(sout.getNote()>=10)
				ps.setString(1, "1");
			else
				ps.setString(1, "0");
	        ps.setFloat(2, sout.getNote());
	        ps.setInt(3, sout.getId() );
			
			int rs;
			rs =ps.executeUpdate();
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Note effectué avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			ps.close();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public List<Soutenance> findAll()throws PFEException {
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			String requete = "SELECT * FROM Soutenance ORDER BY DateHeure";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			
			while (rs.next()) {
				boolean validation;
				if(rs.getInt("VALIDATION")==0)
					validation=false;
				else
					validation=true;				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
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
	
	public List<Soutenance> findAllNonValide()throws PFEException{
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			String requete = "SELECT * FROM Soutenance Where VALIDATION=? ORDER BY DateHeure";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setInt(1, 0);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				boolean validation=false;
				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
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
	public List<Soutenance> findAllValide()throws PFEException{
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			String requete = "SELECT * FROM Soutenance Where VALIDATION=? ORDER BY DateHeure";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setInt(1, 1);
			ResultSet rs;
			rs =ps.executeQuery();
			
			while (rs.next()) {
				boolean validation=true;
				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
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
	public Soutenance find(Soutenance obj)throws PFEException {
		try {
			String requete = "SELECT * FROM Soutenance Where id=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getId());
			ResultSet rs;
			rs =ps.executeQuery();
			Soutenance st;
			if(rs.next()) {
				//recherche jurys 
				Jurys jurys=new Jurys();
				jurys.setId(rs.getInt("IDJURYS"));
				JurysDAO rechjurys=new JurysDAO();
				//recherche projet 
				Projet pr=new Projet();
				pr.setId(rs.getInt("IDPROJET"));
				ProjetDAO rechpr=new ProjetDAO();
				
				LocalTime heure = rs.getTimestamp("DATEHEURE").toLocalDateTime().toLocalTime();
				LocalDate date = rs.getTimestamp("DATEHEURE").toLocalDateTime().toLocalDate();
				//LocalDate dateprojet=rs.getDate("DateDebut").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
				st=new Soutenance(rs.getInt("id"),date, heure,rs.getString("LOCAL"),rechjurys.find(jurys),rechpr.find(pr));
				rs.close();
				ps.close();
				return st;	
			}else {
				rs.close();
				ps.close();
	        	throw new PFEException(25);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Soutenance findByIDpr(Soutenance obj) throws PFEException {
		try {
			String requete = "SELECT * FROM Soutenance Where IDPROJET=?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+obj.getProjet().getId());
			ResultSet rs;
			rs =ps.executeQuery();
			Soutenance st;
			if(rs.next()) {
				//recherche jurys 
				Jurys jurys=new Jurys();
				jurys.setId(rs.getInt("IDJURYS"));
				JurysDAO rechjurys=new JurysDAO();
				//recherche projet 
				Projet pr=new Projet();
				pr.setId(rs.getInt("IDPROJET"));
				ProjetDAO rechpr=new ProjetDAO();
				
				LocalTime heure = rs.getTimestamp("DATEHEURE").toLocalDateTime().toLocalTime();
				LocalDate date = rs.getTimestamp("DATEHEURE").toLocalDateTime().toLocalDate();
				//LocalDate dateprojet=rs.getDate("DateDebut").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
				st=new Soutenance(rs.getInt("id"),date, heure,rs.getString("LOCAL"),rechjurys.find(jurys),rechpr.find(pr));
				rs.close();
				ps.close();
				return st;	
			}else {
				rs.close();
				ps.close();
	        	throw new PFEException(25);
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean existe(Soutenance obj) {
		try {
			String requete = "SELECT * FROM Soutenance Where id =?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, "" + obj.getId());
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
	
	public boolean soutenance_projet(Soutenance sout)throws PFEException {
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance el:souts) {
			if(el.getProjet().getId()==sout.getProjet().getId()) {
				
				return true;
			}
		}
		return false;
	}
	public boolean PresidentOccuper(Soutenance sout) throws PFEException{
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance element:souts) {
			//test soutenance differente 
			if(element.getProjet().getTitre().compareTo(sout.getProjet().getTitre())!=0) {
				//test les date des soutenances
				if(element.getDateSoutenance().equals(sout.getDateSoutenance())) {
					//test heure du soutanance
			        LocalTime heureFin = sout.getHeureSoutenance().plusMinutes(30); // Heure de fin (30 minutes après heuresout)
					if(element.getHeureSoutenance().equals( sout.getHeureSoutenance() ) || (!element.getHeureSoutenance().isBefore(sout.getHeureSoutenance()) && element.getHeureSoutenance().isBefore(heureFin.plusSeconds(1)))) {
						if(sout.getJurys().getPresident().getCIN()==element.getJurys().getPresident().getCIN())
							return true;
						if(sout.getJurys().getPresident().getCIN()==element.getJurys().getRapporteur().getCIN())
							return true;
						if(sout.getJurys().getPresident().getCIN()==element.getJurys().getExaminateur().getCIN())
							return true;
						if(sout.getJurys().getPresident().getCIN()==element.getProjet().getEncadreur().getCIN())
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean RapporteurOccuper(Soutenance sout)throws PFEException {
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance element:souts) {
			//test soutenance differente 
			if(element.getProjet().getTitre().compareTo(sout.getProjet().getTitre())!=0) {
				//test les date des soutenances
				if(element.getDateSoutenance().equals(sout.getDateSoutenance())) {
					//test heure du soutanance
			        LocalTime heureFin = sout.getHeureSoutenance().plusMinutes(30); // Heure de fin (30 minutes après heuresout)
					if(element.getHeureSoutenance().equals( sout.getHeureSoutenance() ) || (!element.getHeureSoutenance().isBefore(sout.getHeureSoutenance()) && element.getHeureSoutenance().isBefore(heureFin.plusSeconds(1)))) {
						if(sout.getJurys().getRapporteur().getCIN()==element.getJurys().getPresident().getCIN())
							return true;
						if(sout.getJurys().getRapporteur().getCIN()==element.getJurys().getRapporteur().getCIN())
							return true;
						if(sout.getJurys().getRapporteur().getCIN()==element.getJurys().getExaminateur().getCIN())
							return true;
						if(sout.getJurys().getRapporteur().getCIN()==element.getProjet().getEncadreur().getCIN())
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean ExaminateurOccuper(Soutenance sout)throws PFEException {
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance element:souts) {
			//test soutenance differente 
			if(element.getProjet().getTitre().compareTo(sout.getProjet().getTitre())!=0) {
				//test les date des soutenances
				if(element.getDateSoutenance().equals(sout.getDateSoutenance())) {
					//test heure du soutanance
			        LocalTime heureFin = sout.getHeureSoutenance().plusMinutes(30); // Heure de fin (30 minutes après heuresout)
					if(element.getHeureSoutenance().equals( sout.getHeureSoutenance() ) || (!element.getHeureSoutenance().isBefore(sout.getHeureSoutenance()) && element.getHeureSoutenance().isBefore(heureFin.plusSeconds(1)))) {
						if(sout.getJurys().getRapporteur().getCIN()==element.getJurys().getPresident().getCIN())
							return true;
						if(sout.getJurys().getExaminateur().getCIN()==element.getJurys().getRapporteur().getCIN())
							return true;
						if(sout.getJurys().getExaminateur().getCIN()==element.getJurys().getExaminateur().getCIN())
							return true;
						if(sout.getJurys().getExaminateur().getCIN()==element.getProjet().getEncadreur().getCIN())
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean EncadreurOccuper(Soutenance sout)throws PFEException {
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance element:souts) {
			//test soutenance differente 
			if(element.getProjet().getTitre().compareTo(sout.getProjet().getTitre())!=0) {
				//test les date des soutenances
				if(element.getDateSoutenance().equals(sout.getDateSoutenance())) {
					//test heure du soutanance
			        LocalTime heureFin = sout.getHeureSoutenance().plusMinutes(30); // Heure de fin (30 minutes après heuresout)
					if(element.getHeureSoutenance().equals( sout.getHeureSoutenance() ) || (!element.getHeureSoutenance().isBefore(sout.getHeureSoutenance()) && element.getHeureSoutenance().isBefore(heureFin.plusSeconds(1)))) {
						if(sout.getProjet().getEncadreur().getCIN()==element.getJurys().getPresident().getCIN())
							return true;
						if(sout.getProjet().getEncadreur().getCIN()==element.getJurys().getRapporteur().getCIN())
							return true;
						if(sout.getProjet().getEncadreur().getCIN()==element.getJurys().getExaminateur().getCIN())
							return true;
						if(sout.getProjet().getEncadreur().getCIN()==element.getProjet().getEncadreur().getCIN())
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean LocaleOccuper(Soutenance sout)throws PFEException {
		ArrayList<Soutenance> souts=(ArrayList<Soutenance>) findAll();
		for(Soutenance element:souts) {
			//test soutenance differente 
			if(element.getProjet().getTitre().compareTo(sout.getProjet().getTitre())!=0) {
				//test les date des soutenances
				if(element.getDateSoutenance().equals(sout.getDateSoutenance())) {
					//test heure du soutanance
			        LocalTime heureFin = sout.getHeureSoutenance().plusMinutes(30); // Heure de fin (30 minutes après heuresout)
					if(element.getHeureSoutenance().equals( sout.getHeureSoutenance() ) || (!element.getHeureSoutenance().isBefore(sout.getHeureSoutenance()) && element.getHeureSoutenance().isBefore(heureFin.plusSeconds(1)))) {
						if(sout.getlocalsout()==element.getlocalsout())
							return true;
						
					}
				}
			}
		}
		return false;
	}
	
	public List<Soutenance> findAllByDate(Soutenance soutenance)throws PFEException {
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			Date date = Date.valueOf(soutenance.getDateSoutenance());
			String requete = "SELECT * FROM Soutenance WHERE TRUNC(DATEHEURE)= ?"
					+ " ORDER BY DateHeure";
			PreparedStatement ps = connbase.prepareStatement(requete);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				boolean validation;
				if(rs.getInt("VALIDATION")==0)
					validation=false;
				else
					validation=true;				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
				
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
	
	public List<Soutenance> findAllByStudent(Etudiant etud)throws PFEException {
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			String requete = "select soutenance.id, soutenance.dateheure, soutenance.local,soutenance.validation ,soutenance.note ,soutenance.idjurys,soutenance.idprojet "
					+ "from soutenance  "
					+ "Inner Join projet on projet.id=soutenance.idprojet "
					+ "where  projet.cinsecondetudiant= "+etud.getCIN()+" or projet.cinfirstetudiant= "+etud.getCIN()
					+ " ORDER BY DateHeure";
			Statement ps;
			ps = connbase.createStatement();
			ResultSet rs;
			rs =ps.executeQuery(requete);
			
			while (rs.next()) {
				boolean validation;
				if(rs.getInt("VALIDATION")==0)
					validation=false;
				else
					validation=true;				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
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
	
	public List<Soutenance> findAllBySalle(Soutenance soutenance)throws PFEException {
		ArrayList<Soutenance> all=new ArrayList<Soutenance>();
		try {
			String requete = "SELECT * FROM Soutenance WHERE LOCAL= ?"
					+ " ORDER BY DateHeure";
			PreparedStatement ps = connbase.prepareStatement(requete);
			ps.setString(1, soutenance.getlocalsout().name());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				boolean validation;
				if(rs.getInt("VALIDATION")==0)
					validation=false;
				else
					validation=true;				
				Soutenance sout=new Soutenance();		
				sout.setId(rs.getInt("id"));
				
				Soutenance sout2=find(sout);
				sout2.setValidation(validation);
				sout2.setNote(rs.getFloat("NOTE"));
				all.add(sout2);
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
