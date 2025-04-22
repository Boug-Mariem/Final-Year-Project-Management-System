package projetPFE;
import java.time.LocalDate;
import java.util.Objects;
public class Projet {
	private int id;
	private String Titre;
	private LocalDate DateDebut;//(YYYY-MM-DD)
	private Enseignant Encadreur;
	private EncadreurSociete EncadreurSoc;
	private Etudiant FirstEtudiant;
	private Etudiant SecondEtudiant;
	
	public Projet(int i,String t,LocalDate d,Enseignant e1,EncadreurSociete e2,Etudiant etud1) {
		id=i;
		Titre=t;
		DateDebut=d;
		Encadreur=e1;
		EncadreurSoc=e2;
		FirstEtudiant=etud1;
	}
	public Projet(int i,String t,LocalDate d,Enseignant e1,EncadreurSociete e2,Etudiant etud1,Etudiant etud2) {
		id=i;
		Titre=t;
		DateDebut=d;
		Encadreur=e1;
		EncadreurSoc=e2;
		FirstEtudiant=etud1;
		SecondEtudiant=etud2;
	}
	
	public Projet() {
		// TODO Auto-generated constructor stub
	}
	public String getTitre() {
		return Titre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public LocalDate getDateDebut() {
		return DateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		DateDebut = dateDebut;
	}
	public Enseignant getEncadreur() {
		return Encadreur;
	}
	public void setEncadreur(Enseignant encadreur) {
		Encadreur = encadreur;
	}
	public EncadreurSociete getEncadreurSoc() {
		return EncadreurSoc;
	}
	public void setEncadreurSoc(EncadreurSociete encadreurSoc) {
		EncadreurSoc = encadreurSoc;
	}
	public Etudiant getFirstEtudiant() {
		return FirstEtudiant;
	}
	public void setFirstEtudiant(Etudiant FirstEtudiant) {
		this.FirstEtudiant = FirstEtudiant;
	}
	public Etudiant getSecondEtudiant() {
		return SecondEtudiant;
	}
	public void setSecondEtudiant(Etudiant secondEtudiant) {
		SecondEtudiant = secondEtudiant;
	}
	@Override
	public String toString() {
		return "Projet [Titre=" + Titre + ", DateDebut=" + DateDebut + ", Encadreur=" + Encadreur + ", EncadreurSoc="
				+ EncadreurSoc + ", FirstEtudiant=" + FirstEtudiant + ", SecondEtudiant=" + SecondEtudiant + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(DateDebut, Encadreur, EncadreurSoc, FirstEtudiant, SecondEtudiant, Titre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projet other = (Projet) obj;
		return Objects.equals(DateDebut, other.DateDebut) && Objects.equals(Encadreur, other.Encadreur)
				&& Objects.equals(EncadreurSoc, other.EncadreurSoc)
				&& Objects.equals(FirstEtudiant, other.FirstEtudiant)
				&& Objects.equals(SecondEtudiant, other.SecondEtudiant) && Objects.equals(Titre, other.Titre);
	}
	
	
}
