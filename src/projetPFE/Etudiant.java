package projetPFE;

public class Etudiant {
    private int CIN;
    private String nom;
    private String prenom;
    private Groupe groupe; // Ajout d'un attribut pour stocker le groupe de l'étudiant
    private String email;
	private String numTel;

    public enum Groupe {
        LICENCE_INFORMATQUE,
        LICENCE_ELECTRONIQUE,
        MASTER_PROFESSIONNEL_INFORMATQUE,
        MASTER_PROFESSIONNEL_ELECTRONIQUE,
        MASTER_RECHERCHE_ELECTRONIQUE,
        MASTER_RECHERCHE_INFORMATQUE,
        INGENIEUR_INFORMATIQUE,
        INGENIEUR_ELECTRONIQUE;
    }
    
    // Constructeur
    public Etudiant(int CIN, String nom, String prenom, String groupe) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        if(groupe.compareTo("LICENCE_INFORMATQUE")==0) 
        	this.groupe=Groupe.LICENCE_INFORMATQUE;
        if(groupe.compareTo("LICENCE_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.LICENCE_ELECTRONIQUE;
        if(groupe.compareTo("MASTER_PROFESSIONNEL_INFORMATQUE")==0) 
        	this.groupe=Groupe.MASTER_PROFESSIONNEL_INFORMATQUE;
        if(groupe.compareTo("MASTER_RECHERCHE_INFORMATQUE")==0) 
        	this.groupe=Groupe.MASTER_RECHERCHE_INFORMATQUE;
        if(groupe.compareTo("MASTER_PROFESSIONNEL_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.MASTER_PROFESSIONNEL_ELECTRONIQUE;
        if(groupe.compareTo("MASTER_RECHERCHE_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.MASTER_RECHERCHE_ELECTRONIQUE;
        if(groupe.compareTo("INGENIEUR_INFORMATIQUE")==0) 
        	this.groupe=Groupe.INGENIEUR_INFORMATIQUE;
        if(groupe.compareTo("INGENIEUR_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.INGENIEUR_ELECTRONIQUE; 
    }
    
 // Constructeur
    public Etudiant(int CIN, String nom, String prenom, String groupe,String em,String num) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        if(groupe.compareTo("LICENCE_INFORMATQUE")==0) 
        	this.groupe=Groupe.LICENCE_INFORMATQUE;
        if(groupe.compareTo("LICENCE_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.LICENCE_ELECTRONIQUE;
        if(groupe.compareTo("MASTER_PROFESSIONNEL_INFORMATQUE")==0) 
        	this.groupe=Groupe.MASTER_PROFESSIONNEL_INFORMATQUE;
        if(groupe.compareTo("MASTER_RECHERCHE_INFORMATQUE")==0) 
        	this.groupe=Groupe.MASTER_RECHERCHE_INFORMATQUE;
        if(groupe.compareTo("MASTER_PROFESSIONNEL_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.MASTER_PROFESSIONNEL_ELECTRONIQUE;
        if(groupe.compareTo("MASTER_RECHERCHE_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.MASTER_RECHERCHE_ELECTRONIQUE;
        if(groupe.compareTo("INGENIEUR_INFORMATIQUE")==0) 
        	this.groupe=Groupe.INGENIEUR_INFORMATIQUE;
        if(groupe.compareTo("INGENIEUR_ELECTRONIQUE")==0) 
        	this.groupe=Groupe.INGENIEUR_ELECTRONIQUE; 
        email=em;
        numTel=num;
    }

    public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	// Getters et Setters
    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
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
	public String toString() {
		return "Etudiant [CIN=" + CIN + ", nom=" + nom + ", prenom=" + prenom + ", groupe=" + groupe + "]";
	}
}
