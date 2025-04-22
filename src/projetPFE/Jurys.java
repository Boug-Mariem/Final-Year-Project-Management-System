package projetPFE;

import java.util.Objects;

public class Jurys {
	private int id;
	private Enseignant president;
	private Enseignant rapporteur;
	private Enseignant examinateur;
	
	public Jurys(Enseignant p,Enseignant r,Enseignant e) {
		president=p;
		rapporteur=r;
		examinateur=e;
	}
	public Jurys(int i,Enseignant p,Enseignant r,Enseignant e) {
		id=i;
		president=p;
		rapporteur=r;
		examinateur=e;
	}

	public Jurys() {
		// TODO Auto-generated constructor stub
	}

	public Enseignant getPresident() {
		return president;
	}

	public void setPresident(Enseignant president) {
		this.president = president;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Enseignant getRapporteur() {
		return rapporteur;
	}

	public void setRapporteur(Enseignant rapporteur) {
		this.rapporteur = rapporteur;
	}

	public Enseignant getExaminateur() {
		return examinateur;
	}

	public void setExaminateur(Enseignant examinateur) {
		this.examinateur = examinateur;
	}

	@Override
	public int hashCode() {
		return Objects.hash(examinateur, president, rapporteur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jurys other = (Jurys) obj;
		return Objects.equals(examinateur, other.examinateur) && Objects.equals(president, other.president)
				&& Objects.equals(rapporteur, other.rapporteur);
	}

	@Override
	public String toString() {
		return "Jurys [president=" + president + ", rapporteur=" + rapporteur + ", examinateur=" + examinateur + "]";
	}
	
	
}
