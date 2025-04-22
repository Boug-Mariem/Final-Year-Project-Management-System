package DAO;

import java.util.List;

import javax.swing.JOptionPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projetPFE.PFEException;
import projetPFE.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {

	public String hacherPassword(String password) throws NoSuchAlgorithmException  {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());

        // Convertir les bytes hachés en une représentation hexadécimale
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
	@Override
	public Utilisateur create(Utilisateur objet) throws PFEException {
		try {
			if(existe(objet))
				throw new PFEException(36);
			String requete;
			requete = "INSERT INTO Utilisateur VALUES ('" + objet.getUsername() + "', '" + hacherPassword(objet.getPassword()) + "')";
			Statement ps;
			ps = connbase.createStatement();
			int rs;
			rs =ps.executeUpdate(requete);
			if (rs > 0) {
				JOptionPane.showMessageDialog(null,"Utilisateur ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean connecter(Utilisateur objet)throws PFEException {
		try {
			String requete = "SELECT * FROM Utilisateur WHERE USERNAME= ?";
			PreparedStatement ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+objet.getUsername());
			ResultSet rs;
			rs =ps.executeQuery();
			
			boolean testuser=rs.next();
			
			if(testuser==true) {
				String objPasswordH=hacherPassword(objet.getPassword());
				if(rs.getString("PASSWORDH").compareTo(objPasswordH)==0) {
					return true;
				}
			}
			//le nom d'utilisateur est faux ou le mot de passe est faux
			throw new PFEException(37);

		} 
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existe(Utilisateur ut) {
		try {
			String requete = "SELECT * FROM Utilisateur Where USERNAME=?";
			PreparedStatement  ps;
			ps = connbase.prepareStatement(requete);
			ps.setString(1, ""+ut.getUsername());
			ResultSet  rs;
			rs =ps.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
	
	@Override
	public Utilisateur update(Utilisateur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Utilisateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur find(Utilisateur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
