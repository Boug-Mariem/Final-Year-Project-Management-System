package projetPFE;
import java.time.LocalDate;
import java.time.LocalTime;

import projetPFE.Etudiant.Groupe;

public class Soutenance {
	private int id;
    private LocalDate dateSoutenance;
    private LocalTime heureSoutenance;
    private Local localsout;
    private boolean validation;
    private float note=-1;
    private Jurys jurys;
    private Projet projet;
    
    public enum Local {
        C01,
        C11,
        A21,
        A22,
        AMPHI_B,
    	AMPHI_KANOUN;
    }

    public Soutenance(int i,LocalDate dateSoutenance, LocalTime heureSoutenance, String localsout,Jurys jurys,Projet projet) {
        id=i;
    	this.dateSoutenance = dateSoutenance;
        this.heureSoutenance = heureSoutenance;
        if(localsout.compareTo("C01")==0) 
        	this.localsout=Local.C01;
        if(localsout.compareTo("C11")==0) 
        	this.localsout=Local.C11;
        if(localsout.compareTo("A21")==0) 
        	this.localsout=Local.A21;
        if(localsout.compareTo("A22")==0) 
        	this.localsout=Local.A22;
        if(localsout.compareTo("AMPHI_B")==0) 
        	this.localsout=Local.AMPHI_B;
        if(localsout.compareTo("AMPHI_KANOUN")==0) 
        	this.localsout=Local.AMPHI_KANOUN;
        this.validation = false;
        this.jurys=jurys;
        this.projet=projet;
    }
    public Soutenance() {}
    
    
    @Override
	public String toString() {
		return "Soutenance [dateSoutenance=" + dateSoutenance + ", heureSoutenance=" + heureSoutenance + ", local="
				+ localsout + ", validation=" + validation + ", note=" + note + "]";
	}


	public LocalDate getDateSoutenance() {
        return dateSoutenance;
    }

    public void setDateSoutenance(LocalDate dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
    }

    public LocalTime getHeureSoutenance() {
        return heureSoutenance;
    }

    public void setHeureSoutenance(LocalTime heureSoutenance) {
        this.heureSoutenance = heureSoutenance;
    }

    public Local getlocalsout() {
        return localsout;
    }

    public void setlocalsout(Local local) {
        this.localsout = local;
    }

    public boolean isValidated() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

	public Jurys getJurys() {
		return jurys;
	}


	public void setJurys(Jurys jurys) {
		this.jurys = jurys;
	}


	public Projet getProjet() {
		return projet;
	}


	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
    
}