package interfacePFE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import DAO.EnseignantDAO;
import DAO.ProjetDAO;
import DAO.SoutenanceDAO;
import projetPFE.EncadreurSociete;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.PFEException;
import projetPFE.Projet;
import projetPFE.Soutenance;

public class Note extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTable tableEtud1;
    private JTable tableEtud2;

    private ArrayList<Soutenance> tous_les_soutenance;
    private ArrayList<Enseignant> ToutEnseigant;
    private JTextField textField_titreprojet;
    private JTextField textField_Note;
    private DefaultTableModel modeltableEtud1 ;
    private DefaultTableModel modeltableEtud2 ;


    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Note ajouterEnseignant = new Note();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Note() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("attribuer note a la soutenance ");
        setLocationRelativeTo(null);
        
        ImageIcon icone = new ImageIcon("graduation.png");
        setIconImage(icone.getImage());
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelgauche = new JPanel();
        panelgauche.setBackground(new Color(62, 133, 140));
        panelgauche.setBounds(0, 0, 216, 661);
        contentPane.add(panelgauche);
        panelgauche.setLayout(null);
        
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1.setBounds(45, 0, 130, 130);
        panelgauche.add(lblNewLabel_1);

        ImageIcon icon = new ImageIcon("graduation.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);


        JPanel panel_1 = new JPanel();
        panel_1.setBounds(6, 135, 204, 416);
        panelgauche.add(panel_1);
        panel_1.setLayout(null);
        JLabel lblAfficherer_soutenance = new JLabel("afficher soutenances");
        lblAfficherer_soutenance.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAfficherer_soutenance.setBounds(48, 0, 143, 52);
        panel_1.add(lblAfficherer_soutenance);

        JLabel lblCreer_soutenance = new JLabel("creer soutenance");
        lblCreer_soutenance.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCreer_soutenance.setBounds(48, 52, 143, 52);
        panel_1.add(lblCreer_soutenance);

        JLabel lblSupprimer_soutenance = new JLabel("supprimer soutenance");
        lblSupprimer_soutenance.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSupprimer_soutenance.setBounds(48, 104, 165, 52);
        panel_1.add(lblSupprimer_soutenance);

        JLabel lblChercher_et_modifier = new JLabel("chercher et modifier ");
        lblChercher_et_modifier.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercher_et_modifier.setBounds(48, 156, 159, 52);
        panel_1.add(lblChercher_et_modifier);


        JLabel lblChercher_par_etudiant = new JLabel("chercher par etudiant");
        lblChercher_par_etudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercher_par_etudiant.setBounds(48, 208, 159, 52);
        panel_1.add(lblChercher_par_etudiant);
        
        
        JLabel lblChercherParDate = new JLabel("chercher par date");
        lblChercherParDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherParDate.setBounds(48, 312, 159, 52);
        panel_1.add(lblChercherParDate);
        
        JLabel lbl_Chercher_par_salle = new JLabel("chercher par salle");
        lbl_Chercher_par_salle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_Chercher_par_salle.setBounds(48, 260, 159, 52);
        panel_1.add(lbl_Chercher_par_salle);
        

        
        JLabel lblAttribuerNote = new JLabel("attribuer Note");
        lblAttribuerNote.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAttribuerNote.setBounds(48, 364, 159, 52);
        panel_1.add(lblAttribuerNote);
        
        
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
       
        
     // Pour icone5
        ImageIcon imageIcon5 = new ImageIcon("search-student.png"); // Remplacez par le chemin de votre image
        Image image5 = imageIcon5.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage5 = image5.getScaledInstance(38, 38, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon5 = new ImageIcon(nouvelleImage5); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée pour icone5
        JLabel icon5 = new JLabel(imageIcon5);
        icon5.setBounds(3, 214, 40, 40);
        panel_1.add(icon5);

     // Pour icone6
        ImageIcon imageIcon6 = new ImageIcon("salle.png"); // Remplacez par le chemin de votre image
        Image image6 = imageIcon6.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage6 = image6.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon6 = new ImageIcon(nouvelleImage6); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée pour icone6
        JLabel icon6 = new JLabel(imageIcon6);
        icon6.setBounds(3, 266, 40, 40);
        panel_1.add(icon6);

        
     // Pour icone7
        ImageIcon imageIcon7 = new ImageIcon("calendrier.png");
        Image image7 = imageIcon7.getImage();
        Image nouvelleImage7 = image7.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        imageIcon7 = new ImageIcon(nouvelleImage7);

        JLabel icon7 = new JLabel(imageIcon7);
        icon7.setBounds(3, 318, 40, 40);
        panel_1.add(icon7);

        
        // Pour icone8
     // Pour icone8
        ImageIcon imageIcon8 = new ImageIcon("exam.png");
        Image image8 = imageIcon8.getImage();
        Image nouvelleImage8 = image8.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        imageIcon8 = new ImageIcon(nouvelleImage8);

        JLabel icon8 = new JLabel(imageIcon8);
        icon8.setBounds(3, 370, 40, 40);
        panel_1.add(icon8);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(115, 186, 193));
        panel.setBounds(215, 0, 769, 28);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("espace Soutenance");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(10, 0, 280, 25);
        panel.add(lblNewLabel);
        
        JPanel paneldroite = new JPanel();
        paneldroite.setLayout(null);
        paneldroite.setBounds(215, 28, 769, 533);
        contentPane.add(paneldroite);
        
        textField_titreprojet = new JTextField();
        textField_titreprojet.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_titreprojet.setColumns(10);
        textField_titreprojet.setBounds(236, 37, 297, 35);
        paneldroite.add(textField_titreprojet);
        
        JLabel lbl_titreprojet = new JLabel("Titre Projet : ");
        lbl_titreprojet.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_titreprojet.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_titreprojet.setBounds(59, 38, 177, 35);
        paneldroite.add(lbl_titreprojet);
        
       // aporter les enseignant de la base de donne
        EnseignantDAO rechens=new EnseignantDAO();
        try {
			ToutEnseigant=(ArrayList<Enseignant>) rechens.findAll();
		} catch (PFEException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);


		}
        
        JButton btnChercher = new JButton("chercher");
        btnChercher.setForeground(Color.WHITE);
        btnChercher.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnChercher.setBackground(new Color(62, 133, 140));
        btnChercher.setBounds(238, 83, 295, 35);
        paneldroite.add(btnChercher);
        btnChercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
	            	Soutenance soutenance;
	            	soutenance = lire();
	            	soutenance=DAOsoutenance.findByIDpr(soutenance);
					if(soutenance!=null) {
						afficher_tout();
						afficherEtud1(soutenance.getProjet().getFirstEtudiant());
						Etudiant etud2=soutenance.getProjet().getSecondEtudiant();
						if(etud2!=null)
							afficherEtud2(etud2);
						
					}
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}} 
           } );
        
        
        JButton btnModifier = new JButton("modifier");
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModifier.setBackground(new Color(62, 133, 140));
        btnModifier.setBounds(248, 281, 285, 35);
        paneldroite.add(btnModifier);
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    			try {
					SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
					Soutenance SoutenanceNote = lire_modification();
					DAOsoutenance.updatenote(SoutenanceNote);
                    refreshTable();
                    clearTextFields();
    			} catch (PFEException e1) {
    				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    			}} });
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 327, 749, 195);
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lbl_CIN1 = new JLabel("Etudiant n°1 : ");
        lbl_CIN1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_CIN1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_CIN1.setBounds(10, 129, 293, 35);
        paneldroite.add(lbl_CIN1);
        
        JLabel lbl_CIN2 = new JLabel("Etudiant n°2 : ");
        lbl_CIN2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_CIN2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_CIN2.setBounds(472, 129, 297, 35);
        paneldroite.add(lbl_CIN2);
        
        textField_Note = new JTextField();
        textField_Note.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_Note.setColumns(10);
        textField_Note.setBounds(264, 230, 241, 40);
        paneldroite.add(textField_Note);
        
        JLabel lbl_CIN2_1 = new JLabel("NOTE attribué : ");
        lbl_CIN2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_CIN2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_CIN2_1.setBounds(74, 233, 177, 35);
        paneldroite.add(lbl_CIN2_1);

        
        modeltableEtud1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        tableEtud1 = new JTable();
        modeltableEtud1.addColumn("CIN");
        modeltableEtud1.addColumn("Nom");
        modeltableEtud1.addColumn("Prenom");
        tableEtud1.setModel(modeltableEtud1);
        JScrollPane scrollPaneEtudiant1 = new JScrollPane();
        scrollPaneEtudiant1.setBounds(10, 164, 368, 45);
        paneldroite.add(scrollPaneEtudiant1);
        scrollPaneEtudiant1.setViewportView(tableEtud1);
        
        modeltableEtud2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        tableEtud2 = new JTable();
        modeltableEtud2.addColumn("CIN");
        modeltableEtud2.addColumn("Nom");
        modeltableEtud2.addColumn("Prenom");
        tableEtud2.setModel(modeltableEtud2);
        JScrollPane scrollPaneEtudiant2 = new JScrollPane();
        scrollPaneEtudiant2.setBounds(391, 164, 368, 45);
        paneldroite.add(scrollPaneEtudiant2);
        scrollPaneEtudiant2.setViewportView(tableEtud2);

        
        
        
        ///////////////////////////////////////////////////
        // Action pour "afficher soutenances"
           lblAfficherer_soutenance.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   Afficher_Soutenance afficherSoutenancesFrame = new Afficher_Soutenance();
                   afficherSoutenancesFrame.setVisible(true);
                   Note.this.setVisible(false);

               }
           });

           // Action pour "creer soutenance"
           lblCreer_soutenance.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	Creation_Soutenance creerSoutenanceFrame = new Creation_Soutenance();
                   creerSoutenanceFrame.setVisible(true);
                   Note.this.setVisible(false);
               }
           });

           // Action pour "supprimer soutenance"
           lblSupprimer_soutenance.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	Supprimer_Soutenance supprimerSoutenanceFrame = new Supprimer_Soutenance();
                   supprimerSoutenanceFrame.setVisible(true);
                   Note.this.setVisible(false);
               }
           });

           // Action pour "chercher et modifier"
           lblChercher_et_modifier.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	Chercher_Soutenance chercherEtModifierFrame = new Chercher_Soutenance();
                   chercherEtModifierFrame.setVisible(true);
                   Note.this.setVisible(false);
               }
           });

           // Action pour "chercher par étudiant"
           lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	RechercheSoutenance_etudiant chercherParEtudiantFrame = new RechercheSoutenance_etudiant();
                   chercherParEtudiantFrame.setVisible(true);
                   Note.this.setVisible(false);
               }
           });
           
           // Action pour "chercher par salle"
           lbl_Chercher_par_salle.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	RechercheSoutenance_salle chercherParEtudiantFrame = new RechercheSoutenance_salle();
                   chercherParEtudiantFrame.setVisible(true);
                   Note.this.setVisible(false);
               }
           });
           
           
           // Action pour "chercher par date"
           lblChercherParDate.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
               	RechercheSoutenance_date chercherPardateFrame = new RechercheSoutenance_date();
               	chercherPardateFrame.setVisible(true);
               	Note.this.setVisible(false);
               }
           });

           // Action pour "attribuer Note"
         /*  lblAttribuerNote.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   Note attribuerNoteFrame = new Note();
                   attribuerNoteFrame.setVisible(true);
                   Chercher_Soutenance.this.setVisible(false);
               }
           });*/
        
           addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                   // Ouvrir la nouvelle interface ici
               	Acceuil_Soutenance frame = new Acceuil_Soutenance();
   				frame.setVisible(true);
               }
           });
           
           try {
			afficher_tout();
		} catch (PFEException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);


		}


}
    public Soutenance lire() throws PFEException {
    	Soutenance soutenance=new Soutenance();
    	Projet projet=new Projet();

    	// Validate project title
        if (textField_titreprojet.getText().isEmpty()|| !(textField_titreprojet.getText().matches("[a-zA-Z ]+"))) {
            throw new PFEException(40); // Assuming error code 12 is for an empty project name
        }
        projet.setTitre(textField_titreprojet.getText());
        //recherche du projet avec ce titre
        ProjetDAO rechpr=new ProjetDAO();
        soutenance.setProjet(rechpr.findByTitre(projet));
        
  	return soutenance;


    }
    
    public Soutenance lire_modification() throws PFEException {
        //verification du note 
        if(textField_Note.getText().isEmpty()) {
        	throw new PFEException(5);
        }
        float note;
        try {
        	note = Float.parseFloat(textField_Note.getText());
        }
        catch (NumberFormatException e) {
        	throw new PFEException(5);
        }
        if(!(note<=20 && note>=0)) {
			throw new PFEException(5);
		}
        
        //transforametion des valeur de a note en des numero avec 2 chiffre avec la vergule
        String nombreFormate = String.format(Locale.US,"%.2f", note);
        float notef = Float.parseFloat(nombreFormate);
        
        Soutenance sout=lire();
        SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
        Soutenance soutenancerecherche=DAOsoutenance.findByIDpr(sout);
        soutenancerecherche.setNote(notef);
        return soutenancerecherche;
    }
    
    
    // Méthode pour récupérer et afficher les données à partir de la fonction findAllValide
    public void afficher_tout() throws PFEException{

        SoutenanceDAO soutenancedao = new SoutenanceDAO();
        tous_les_soutenance = (ArrayList<Soutenance>) soutenancedao.findAll();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        int rowCount = tous_les_soutenance.size();          

        model.addColumn("N°");
        model.addColumn("ID");
        String colprojet=String.format("<html>%s<br>%s</html>","Titre","projet");
        model.addColumn(colprojet);
        model.addColumn("Etudiants");
        model.addColumn("Date");
        model.addColumn("Heure");
        model.addColumn("Locale");
        model.addColumn("Encadreur");
        String colencadreurSoc=String.format("<html>%s<br>%s</html>","Encadreur","Societe");
        model.addColumn(colencadreurSoc);
        model.addColumn("Jurys");
        model.addColumn("Validation");
        model.addColumn("Note");
        
      
        for (int j = 0; j < rowCount; j++) {
            Soutenance soutenance = tous_les_soutenance.get(j);

            // Récupérer les détails du jury
            Enseignant president = soutenance.getJurys().getPresident();
            Enseignant rapporteur = soutenance.getJurys().getRapporteur();
            Enseignant examinateur = soutenance.getJurys().getExaminateur();
            //recuperation des etudiant 
            Etudiant Etud1=soutenance.getProjet().getFirstEtudiant();
            Etudiant Etud2=new Etudiant();
            if(soutenance.getProjet().getFirstEtudiant()!=null)
            	Etud2=soutenance.getProjet().getSecondEtudiant();
            //recuperation de encadreur
            Enseignant encadreur = soutenance.getProjet().getEncadreur();
          //recuperation de encadreur societe
            EncadreurSociete encadreurSoc = soutenance.getProjet().getEncadreurSoc();

            // Récupérer le titre du projet
            String titreProjet = soutenance.getProjet().getTitre();

            Object[] row = new Object[12]; // Nombre de colonnes dans le modèle de tableau
            row[0] = j+1;
            row[1] = soutenance.getId();
            row[2] = titreProjet;
            if(soutenance.getProjet().getSecondEtudiant()!=null) {
            	String lesEtudinats=String.format("<html>%s<br>%s<br>%s<br>%s<br>%s<br>%s</html>","1/",Etud1.getNom(),Etud1.getPrenom(),"2/",Etud2.getNom(),Etud2.getPrenom());
            	row[3]=lesEtudinats;
            }
            else {
            	String lesEtudinats=String.format("<html>%s<br>%s</html>",Etud1.getNom(),Etud1.getPrenom());
            	row[3]=lesEtudinats;
            }
            row[4] = soutenance.getDateSoutenance();
            row[5] = soutenance.getHeureSoutenance().withSecond(0).withNano(0);
            row[6] = soutenance.getlocalsout();
        	String Stringencadreur=String.format("<html>%s<br>%s</html>",encadreur.getNom(),encadreur.getPrenom());
        	row[7] = Stringencadreur;
        	String StringencadreurSoc=String.format("<html>%s<br>%s</html>",encadreurSoc.getNom(),encadreurSoc.getPrenom());
        	row[8] = StringencadreurSoc;
        	String Stringjurys=String.format("<html>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br></html>","(P):",president.getNom(),president.getPrenom(),"(R):",rapporteur.getNom(),rapporteur.getPrenom(),"(E):",examinateur.getNom(),examinateur.getPrenom());
        	row[9] = Stringjurys;
        	if(soutenance.getNote()>=10) {
        		row[10] = "Validé"; // Remplacez par la valeur de validation appropriée
        		row[11] = soutenance.getNote();
        	}
        	else if(soutenance.getNote()<10 && soutenance.getNote()>=0) {
            	String nonValide=String.format("<html>%s<br>%s</html>","Non","Validé");
        		row[10] = nonValide; // Remplacez par la valeur de validation appropriée
        		row[11] = soutenance.getNote();
        	}
        	else {
        		row[10] = "***"; // Remplacez par la valeur de validation appropriée
        		row[11] = "***";
        	}
            
            model.addRow(row);
        }


        table.setModel(model);
        table.setRowHeight(25);
        //////////   numero    
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(0).setMaxWidth(25);
        //////////id  
		 table.getColumnModel().getColumn(1).setPreferredWidth(30);
		 table.getColumnModel().getColumn(1).setMaxWidth(30);
		//diminuer la taille du colone date
         table.getColumnModel().getColumn(4).setPreferredWidth(70);
         table.getColumnModel().getColumn(4).setMaxWidth(70);
		//diminuer la taille du colone heure
         table.getColumnModel().getColumn(5).setPreferredWidth(50);
         table.getColumnModel().getColumn(5).setMaxWidth(50);
         //diminuer la taille du colone locale
         table.getColumnModel().getColumn(6).setPreferredWidth(45);
         table.getColumnModel().getColumn(6).setMaxWidth(45);
         //diminuer la taille du colone validation
         table.getColumnModel().getColumn(10).setPreferredWidth(58);
         table.getColumnModel().getColumn(10).setMaxWidth(58);
         //diminuer la taille du colone note
         table.getColumnModel().getColumn(11).setPreferredWidth(40);
         table.getColumnModel().getColumn(11).setMaxWidth(40);
		 
		 
		 
		 
		//agrandir la taille du header du table
         table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
         
      // Ajuster la hauteur des lignes pour s'adapter au contenu HTML
         for (int row = 0; row < table.getRowCount(); row++) {
             int rowHeight = table.getRowHeight();
             for (int column = 0; column < table.getColumnCount(); column++) {
                 Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                 rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
             }
             table.setRowHeight(row, rowHeight);
         }

    }
    
	public void afficherEtud1(Etudiant etud) {
			
	        Object[] row = new Object[3];
	        row[0]=etud.getCIN();
	        row[1]=etud.getNom();
	        row[2]=etud.getPrenom();
	        modeltableEtud1.addRow(row);
	        //diminuer la colone de cin
	        tableEtud1.getColumnModel().getColumn(0).setPreferredWidth(100);
	        tableEtud1.getColumnModel().getColumn(0).setMaxWidth(100);
	           
	        tableEtud1.setModel(modeltableEtud1);
	        tableEtud1.setRowHeight(25);
	       
	      
	    }
	
	public void afficherEtud2(Etudiant etud) {
	      
        Object[] row = new Object[3];
        row[0]=etud.getCIN();
        row[1]=etud.getNom();
        row[2]=etud.getPrenom();
        modeltableEtud2.addRow(row);
        //diminuer la colone de cin
        tableEtud2.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableEtud2.getColumnModel().getColumn(0).setMaxWidth(100);
           
        tableEtud2.setModel(modeltableEtud2);
        tableEtud2.setRowHeight(25);
       
      
    }
    
    public void refreshTable()throws PFEException {
    	afficher_tout();
}
    private void clearTextFields() {
    	textField_titreprojet.setText(""); 
    	textField_Note.setText(""); 
    	modeltableEtud1.setRowCount(0);
    	modeltableEtud2.setRowCount(0);

    }
}
