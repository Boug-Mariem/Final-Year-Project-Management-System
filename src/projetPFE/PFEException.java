package projetPFE;

public class PFEException extends Exception {
	private String msg="";
	public PFEException(int n) {
		switch(n) {
		case 1:msg="Erreur : Veuillez verifier le numero de CIN";break;
		case 2:msg="Erreur : Veuillez verifier le Nom";break;
		case 3:msg="Erreur : Veuillez verifier le Prenom";break;
		case 4:msg="Erreur : Veuillez valider la soutenance";break;
		case 5:msg="Erreur : Veuillez verifier la note de la soutenance";break;
		case 6:msg="Erreur : Étudiant existe déjà.";break;//pour la creation
		case 7:msg="Erreur : Étudiant introuvable.";break;//pour la modification
		case 8:msg="Erreur : Enseignant existe déjà.";break;//pour la creation
		case 9:msg="Erreur : Enseignant introuvable.";break;//pour la modification
		case 10:msg="Erreur : Encadreur Societe  existe déjà.";break;//pour la creation
		case 11:msg="Erreur : Encadreur Societe introuvable.";break;//pour la modification
		case 12:msg="Erreur : Titre de Projet existe déjà.";break;//pour la creation
		case 13:msg="Erreur : Projet introuvable.";break;//pour la modification
		case 14:msg="Erreur : Étudiant numéro 1 déjà affecté à un autre projet.";break;
		case 15:msg="Erreur : Étudiant numéro 2 déjà affecté à un autre projet.";break;
		case 16:msg="Erreur : Étudiant numéro 1 introuvable.";break;
		case 17:msg="Erreur : Étudiant numéro 2 introuvable.";break;
		case 18:msg="Erreur : Enseignant introuvable.";break;
		case 19:msg="Erreur : Encadreur Societe introuvable.";break;
		case 20:msg="Erreur : President introuvable.";break;
		case 21:msg="Erreur : Rapporteur introuvable.";break;
		case 22:msg="Erreur : Examinateur introuvable.";break;
		case 23:msg="Erreur : Jurys introuvable.";break;
		case 24:msg="Erreur : Soutenance déjà programmée pour ce projet.";break;
		case 25:msg="Erreur : Soutenance introuvable.";break;
		case 26:msg="Erreur : Le Président et le Rapporteur de la soutenance ne peuvent pas être la même personne.";break;
		case 27:msg="Erreur : Le Président et l'Examinateur de la soutenance ne peuvent pas être la même personne.";break;
		case 28:msg="Erreur : Le Rapporteur et l'Examinateur de la soutenance ne peuvent pas être la même personne.";break;
		case 29:msg="Erreur : Le Président et l'Encadreur du projet ne peuvent pas être la même personne.";break;
		case 30:msg="Erreur : Le Rapporteur et l'Encadreur du projet ne peuvent pas être la même personne.";break;
		case 31:msg="Erreur : l'Examinateur et l'Encadreur du projet ne peuvent pas être la même personne.";break;
		case 32:msg="Erreur : Le Président de la soutenance est déjà engagé dans une autre soutenance à cette date.";break;
		case 33:msg="Erreur : Le Rapporteur de la soutenance est déjà engagé dans une autre soutenance à cette date.";break;
		case 34:msg="Erreur : l'Examinateur de la soutenance est déjà engagé dans une autre soutenance à cette date.";break;
		case 35:msg="Erreur : l'Encadreur du projet est déjà engagé dans une autre soutenance à cette date.";break;
		case 36:msg="Erreur : Nom d'utilisateur déjà existant.";break;
		case 37:msg="Erreur : Veuillez vérifier vos informations de connexion et réessayer.";break;
		case 38:msg="Erreur : Ce local est déjà réservé à cette heure.";break;
		case 39:msg="Erreur : Les Étudiants sélectionnés doivent être différents.";break;
		case 40:msg="Erreur : Veuillez verifier le titre du projet.";break;
		case 41:msg="Erreur : Veuillez verifier l'ID de la soutenance.";break;
		
		case 42:
		    msg = "Erreur : le mot de passe doit :\n"
		        + "       - Contenir au moins une lettre majuscule.\n"
		        + "       - Contenir au moins une lettre minuscule.\n"
		        + "       - Contenir au moins un chiffre.\n"
		        + "       - Avoir une longueur minimale de 5 caractères.\n"
		        + "       - Ne pas dépasser 32 caractères.";
		    break;
		case 43:
		    msg = "Erreur : Le nom d'utilisateur doit :\r\n"
		    		+ "Contenir au moins une lettre majuscule.\r\n"
		    		+ "Contenir au moins une lettre minuscule.\r\n"
		    		+ "Avoir une longueur minimale de 5 caractères et maximale de 32 caractères.\"";
		    break;
		case 44:msg="Erreur : Le numéro de téléphone saisi est déjà attribué à une autre personne.";break;
		case 45:msg="Erreur : L'email saisi est déjà attribué à une autre personne.";break;
		case 46:msg="Erreur : Veuillez verifier L'email";break;
		case 47:msg="Erreur : Veuillez verifier Le numéro de téléphone.";break;
		case 48:msg="Erreur : Le numéro de CIN est déjà affecté à une autre personne.";break;
		case 49:msg="Erreur : Les Étudiants sélectionnés doivent être du même groupe.";break;
		case 50:msg="Erreur : L'Étudiant sélectionné n'a pas encore de soutenance.";break;
		}
		
		
	}
	
	public String getMessage() {
		return msg;
	}
}
