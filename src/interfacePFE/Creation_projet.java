package interfacePFE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;

import DAO.EncadreurSocieteDAO;
import DAO.EnseignantDAO;
import DAO.EtudiantDAO;
import DAO.ProjetDAO;
import projetPFE.EncadreurSociete;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.Projet;
import projetPFE.PFEException;


public class Creation_projet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JCalendar jCalendar; // Déclaration au niveau de la classe

    private ArrayList<Projet> tousLesProjets;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox comboBoxEncSoc;
    private JComboBox comboBoxEnc;
    private ArrayList<Enseignant> ToutEnseigant;
    private ArrayList<EncadreurSociete> ToutEncadreurSoc;


    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Creation_projet ajouterEnseignant = new Creation_projet();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Creation_projet() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Creer Projet");
        setLocationRelativeTo(null);
        
        ImageIcon icone = new ImageIcon("pfe.png");
        setIconImage(icone.getImage());

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelgauche = new JPanel();
        panelgauche.setBackground(new Color(62, 133, 140));
        panelgauche.setBounds(0, 0, 216, 561);
        contentPane.add(panelgauche);
        panelgauche.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1.setBounds(45, 0, 130, 130);
        panelgauche.add(lblNewLabel_1);

        ImageIcon icon = new ImageIcon("pfe.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);

        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(5, 146, 206, 316);
        panelgauche.add(panel_1);
        
        JLabel lblAfficherProjets = new JLabel("afficher projets");
        lblAfficherProjets.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAfficherProjets.setBounds(48, 0, 152, 52);
        panel_1.add(lblAfficherProjets);
        
        JLabel lblCreerProjet = new JLabel("creer projet");
        lblCreerProjet.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreerProjet.setBounds(48, 52, 152, 52);
        panel_1.add(lblCreerProjet);
        
        JLabel lblSupprimerProjet = new JLabel("supprimer projet");
        lblSupprimerProjet.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSupprimerProjet.setBounds(48, 104, 152, 52);
        panel_1.add(lblSupprimerProjet);
        
        JLabel lblChercher_projet = new JLabel("chercher et modifier");
        lblChercher_projet.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercher_projet.setBounds(48, 156, 142, 52);
        panel_1.add(lblChercher_projet);
        
        JLabel lblChercher_par_etudiant = new JLabel("chercher par etudiant");
        lblChercher_par_etudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercher_par_etudiant.setBounds(48, 208, 152, 52);
        panel_1.add(lblChercher_par_etudiant);
        
        JLabel lblChercher_par_encadreur = new JLabel("chercher par encadreur");
        lblChercher_par_encadreur.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercher_par_encadreur.setBounds(44, 260, 164, 52);
        panel_1.add(lblChercher_par_encadreur);
        

	       ///////////////icone de afficher etudiant 
  // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon = new ImageIcon("db.png"); // Remplacez "ajouter.png" par le chemin de votre image

  // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image = imageIcon.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon = new ImageIcon(nouvelleImage); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  // Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon1 = new JLabel(imageIcon);
  icon1.setBounds(3, 6, 40, 40);
  panel_1.add(icon1);

  
  ///////////////icone de ajouter etudiant 
  // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon2 = new ImageIcon("ajouter_projet.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

  // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image2 = imageIcon2.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage2 = image2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon2 = new ImageIcon(nouvelleImage2); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  // Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon2 = new JLabel(imageIcon2);
	 icon2.setBounds(3, 58, 40, 40);
  panel_1.add(icon2);
  
  
  ///////////////icone de supprimer etudiant 
  // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon3 = new ImageIcon("supprimer_projet.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

  // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image3 = imageIcon3.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage3 = image3.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon3 = new ImageIcon(nouvelleImage3); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  // Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon3 = new JLabel(imageIcon3);
	 icon3.setBounds(3, 110, 40, 40);
  panel_1.add(icon3);

  
  ///////////////icone de modifier etudiant 
  // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon4 = new ImageIcon("loop.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

  // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image4 = imageIcon4.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage4 = image4.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon4 = new ImageIcon(nouvelleImage4); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  // Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon4 = new JLabel(imageIcon4);
	 icon4.setBounds(3, 162, 40, 40);
  panel_1.add(icon4);
 
  
  //Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon5 = new ImageIcon("search-student.png"); // Remplacez "edit.png" par le chemin de votre image

  //Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image5 = imageIcon5.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage5 = image5.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon5 = new ImageIcon(nouvelleImage5); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  //Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon5 = new JLabel(imageIcon5);
  icon5.setBounds(3, 214, 40, 40);
  panel_1.add(icon5);


  //Créez une instance de ImageIcon en chargeant l'image depuis un fichier
  ImageIcon imageIcon6 = new ImageIcon("file.png"); // Remplacez "delete.png" par le chemin de votre image

  //Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
  Image image6 = imageIcon6.getImage(); // Obtenez l'image de l'icône
  Image nouvelleImage6 = image6.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
  imageIcon6 = new ImageIcon(nouvelleImage6); // Créez une nouvelle ImageIcon avec l'image redimensionnée

  //Créez le JLabel avec l'image chargée et redimensionnée
  JLabel icon6 = new JLabel(imageIcon6);
  icon6.setBounds(3, 266, 40, 40);
  panel_1.add(icon6);

  
	//////////////////////////////////
	lblAfficherProjets.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre AfficherProjets
			Affichage_projet afficherProjetsFrame = new Affichage_projet();
			afficherProjetsFrame.setVisible(true);
			Creation_projet.this.setVisible(false);
		}
	});
	
/*	lblCreerProjet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre CreerProjet
			Creation_projet creerProjetFrame = new Creation_projet();
			creerProjetFrame.setVisible(true);
		}
	});*/
	lblSupprimerProjet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre SupprimerProjet
			Supprimer_projet supprimerProjetFrame = new Supprimer_projet();
			supprimerProjetFrame.setVisible(true);
			Creation_projet.this.setVisible(false);

		}
	});
		lblChercher_projet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre ChercherEtModifier
			ChercherProjet chercherEtModifierFrame = new ChercherProjet();
			chercherEtModifierFrame.setVisible(true);
			Creation_projet.this.setVisible(false);
		}
	});
	lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // Supposons que vous avez une classe qui s'appelle RechercheProjet_etudiant
	        RechercheProjet_etudiant rechercheEtudiantFrame = new RechercheProjet_etudiant();
	        rechercheEtudiantFrame.setVisible(true);
	        Creation_projet.this.setVisible(false);
	    }
	});

	lblChercher_par_encadreur.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // Vous avez déjà une classe nommée RechercheProjet_encadreur, donc ici vous pourriez soit
	        // réutiliser la même instance, soit créer une nouvelle selon le contexte
	        RechercheProjet_encadreur rechercheEncadreurFrame = new RechercheProjet_encadreur();
	        rechercheEncadreurFrame.setVisible(true);
	        Creation_projet.this.setVisible(false);
	    }
	});
//TODO  ////////////////////////////////
    
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            // Ouvrir la nouvelle interface ici
        	Acceuil_Projet frame = new Acceuil_Projet();
			frame.setVisible(true);
        }
    });
    ///////////////////////////////////////////////////


//// droite panel 

        JPanel paneldroite = new JPanel();
        paneldroite.setBounds(215, 28, 769, 533);
        contentPane.add(paneldroite);
        paneldroite.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField.setColumns(10);
        textField.setBounds(278, 26, 285, 35);
        paneldroite.add(textField);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_2.setColumns(10);
        textField_2.setBounds(278, 250, 285, 35);
        paneldroite.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_3.setColumns(10);
        textField_3.setBounds(278, 296, 285, 35);
        paneldroite.add(textField_3);
        
        JButton btnCree = new JButton("cree");
        btnCree.setForeground(Color.WHITE);
        btnCree.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCree.setBackground(new Color(62, 133, 140));
        btnCree.setBounds(278, 456, 285, 35);
        paneldroite.add(btnCree);
        btnCree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					ProjetDAO DAOprojet=new ProjetDAO();
	            	Projet projet;
	            	projet = lire();
					DAOprojet.create(projet);
					clearTextFields();
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}}}
        );
    
        
        JLabel lblNewLabel_2 = new JLabel("Titre Projet : ");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setBounds(105, 27, 163, 35);
        paneldroite.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Date debut : ");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_1.setBounds(105, 80, 163, 35);
        paneldroite.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("CIN etudiant n°1 : ");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_2.setBounds(105, 251, 163, 35);
        paneldroite.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("CIN etudiant n°2 : ");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_3.setBounds(105, 297, 163, 35);
        paneldroite.add(lblNewLabel_2_3);
        
        JLabel lblNewLabel_2_4 = new JLabel("CIN encadreur: ");
        lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_4.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_4.setBounds(105, 343, 163, 35);
        paneldroite.add(lblNewLabel_2_4);
        
        JLabel lblNewLabel_2_5 = new JLabel("CIN encadreur societe : ");
        lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_5.setBounds(10, 389, 258, 35);
        paneldroite.add(lblNewLabel_2_5);

        /////////////////////////////////
        jCalendar = new JCalendar();
        jCalendar.getDayChooser().setBackground(SystemColor.inactiveCaption);
        jCalendar.getDayChooser().getDayPanel().setBounds(16, 0, 184, 130);

        // Ajustez 280, 80, 200, et 150 selon vos besoins
        jCalendar.setBounds(280, 80, 200, 150);

        // Ajoutez le JCalendar à un conteneur (par exemple, paneldroite).
        paneldroite.add(jCalendar);

        
        
     // Définissez les coordonnées et la taille du JCalendar.
     // Notez que vous voudrez peut-être ajuster la largeur et la hauteur pour mieux accueillir le calendrier.
     jCalendar.setBounds(280, 80, 200, 150); // Ajustez 200 et 150 selon vos besoins

     // Ajoutez le JCalendar au panneau.
     paneldroite.add(jCalendar);
     jCalendar.getDayChooser().setLayout(null);
     
     //reglage du combobox encadreur
     EnseignantDAO rechens=new EnseignantDAO();
     try {
		ToutEnseigant=(ArrayList<Enseignant>) rechens.findAll();
	} catch (PFEException e2) {
		JOptionPane.showMessageDialog(null,e2.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
	}
     comboBoxEnc = new JComboBox(ToutEnseigant.toArray(new Enseignant[0]));
     comboBoxEnc.setRenderer(new EnseignantComboBoxRenderer());
     comboBoxEnc.setBounds(278, 342, 285, 35);
     paneldroite.add(comboBoxEnc);
     
     //regalge du combobox encadreur societe
     EncadreurSocieteDAO rechenc=new EncadreurSocieteDAO();
     try {
		ToutEncadreurSoc=(ArrayList<EncadreurSociete>) rechenc.findAll();
	} catch (PFEException e1) {
		JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);

	}
     comboBoxEncSoc = new JComboBox(ToutEncadreurSoc.toArray(new EncadreurSociete[0]));
     comboBoxEncSoc.setRenderer(new EncadreurSocieteComboboxRenderer());
     comboBoxEncSoc.setBounds(278, 392, 285, 35);
     paneldroite.add(comboBoxEncSoc);

     // Ceci est nécessaire pour rafraîchir le panneau et afficher le JCalendar après l'avoir ajouté.
     paneldroite.revalidate();
     paneldroite.repaint();//////////

        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(115, 186, 193));
        panel.setBounds(215, 0, 769, 28);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("espace projet");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(10, 0, 280, 25);
        panel.add(lblNewLabel);
        

        
  }
        

    public Projet lire() throws PFEException {

        // Vérification du champ titre
    	
        if (textField.getText().isEmpty() || !(textField.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(40);


        // Vérification des champs CIN
        String regexCIN = "\\d{8}";
        if (textField_2.getText().isEmpty() || !(textField_2.getText().matches(regexCIN))) {
            throw new PFEException(1);
        }
        // on doit ajouter si projet n'a pas d'etudiant 2 !!!!!!!!!!!!!!!!!!!!
        if (!textField_3.getText().isEmpty() &&!(textField_3.getText().matches(regexCIN))) {
            throw new PFEException(1);
        }
        
      //lire encadreur
        EnseignantDAO rechEnseignant=new EnseignantDAO();
        Enseignant encadreur = new Enseignant();
        Enseignant encadreurSelectionne = (Enseignant) comboBoxEnc.getSelectedItem();
        if (encadreurSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = encadreurSelectionne.getCIN();
            encadreur.setCIN(CIN);
            encadreur=rechEnseignant.find(encadreur);
        }
        else {
        	throw new PFEException(9);
        }
        //lire encadreur societe
        EncadreurSocieteDAO rechEncadreurSociete=new EncadreurSocieteDAO();
        EncadreurSociete encadreurSos = new EncadreurSociete();
        EncadreurSociete encadreurSosSelectionne = (EncadreurSociete) comboBoxEncSoc.getSelectedItem();
        if (encadreurSosSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = encadreurSosSelectionne.getCIN();
            encadreurSos.setCIN(CIN);
            encadreurSos=rechEncadreurSociete.find(encadreurSos);
        }
        else {
        	throw new PFEException(11);
        }

     // Convertir la chaîne de date en LocalDate
        Date selectedDate = jCalendar.getDate();
        Instant instant = selectedDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        // Récupération des valeurs des champs CIN
        int CIN1 = Integer.parseInt(textField_2.getText());

        // Création d'une instance
        
        Projet projet = new Projet();
        
        EtudiantDAO rechetudf=new EtudiantDAO();
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCIN(CIN1);
        
        Etudiant etudiant2 = new Etudiant();
        if(!textField_3.getText().isEmpty()) {
        	int CIN2 = Integer.parseInt(textField_3.getText());
        	etudiant2.setCIN(CIN2);
        	projet.setSecondEtudiant(rechetudf.find(etudiant2));
        }
        projet.setTitre(textField.getText());
        projet.setDateDebut(localDate);
        projet.setEncadreur(encadreur);
        projet.setEncadreurSoc(encadreurSos);
        projet.setFirstEtudiant(rechetudf.find(etudiant1));
        
        return projet;
    }
    private void clearTextFields() {
        textField.setText(""); 
        textField_2.setText(""); 
        textField_3.setText(""); 
        comboBoxEncSoc.setSelectedIndex(0);
        comboBoxEnc.setSelectedIndex(0);
    }
}

