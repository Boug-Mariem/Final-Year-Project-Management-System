package projetPFE;

import java.util.Objects;

public class EncadreurSociete {
	private int CIN;
	private String Nom;
	private String Prenom;
	
	public EncadreurSociete(int c , String n ,String p) {
		CIN=c;
		Nom=n;
		Prenom=p;	
	}

	public EncadreurSociete() {
		// TODO Auto-generated constructor stub
	}

	public int getCIN() {
		return CIN;
	}

	public void setCIN(int cIN) {
		CIN = cIN;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	@Override
	public String toString() {
		return CIN+"   "+Nom+"   "+Prenom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CIN, Nom, Prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncadreurSociete other = (EncadreurSociete) obj;
		return CIN == other.CIN && Objects.equals(Nom, other.Nom) && Objects.equals(Prenom, other.Prenom);
	}
	
}
