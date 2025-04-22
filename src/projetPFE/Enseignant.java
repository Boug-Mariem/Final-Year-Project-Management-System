package projetPFE;

import java.util.Objects;

public class Enseignant {
	private int CIN;
	private String Nom;
	private String Prenom;
	private String email;
	private String numTel;
	
	
	public Enseignant(int c , String n ,String p) {
		CIN=c;
		Nom=n;
		Prenom=p;	
	}
	
	public Enseignant(int c , String n ,String p,String email,String num) {
		CIN=c;
		Nom=n;
		Prenom=p;
		this.email=email;
		numTel=num;
	}

	public Enseignant() {
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
		return CIN + "   "+Nom+"   "+Prenom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CIN, Nom, Prenom);
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enseignant other = (Enseignant) obj;
		return CIN == other.CIN && Objects.equals(Nom, other.Nom) && Objects.equals(Prenom, other.Prenom);
	}
	
	
	
}

