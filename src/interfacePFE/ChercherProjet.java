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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
import java.time.LocalDate;

public class ChercherProjet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JCalendar jCalendar; // Déclaration au niveau de la classe

    private ArrayList<Projet> tousLesProjets;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox comboBoxEncSoc;
    private JComboBox comboBoxEnc;
    private JTable table;
    //private DefaultTableModel model = new DefaultTableModel();
    private  ArrayList<Enseignant> ToutEnseigant;
    private ArrayList<EncadreurSociete> ToutEncadreurSoc;
    private Projet prInitiale;
    private DefaultTableModel model;


    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	ChercherProjet ChercherProjet = new ChercherProjet();
                	ChercherProjet.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public ChercherProjet() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Modifier projet par Titre ");
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
        textField_2.setBounds(451, 170, 285, 35);
        paneldroite.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_3.setColumns(10);
        textField_3.setBounds(451, 236, 285, 35);
        paneldroite.add(textField_3);
        
        JButton btnmodifier = new JButton("modifier");
        btnmodifier.setForeground(Color.WHITE);
        btnmodifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnmodifier.setBackground(new Color(62, 133, 140));
        btnmodifier.setBounds(247, 393, 285, 35);
        paneldroite.add(btnmodifier);
        btnmodifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            	try {
	  					ProjetDAO DAOpr=new ProjetDAO();
	  	            	Projet pr;
	  					pr = liremodification();
	  					DAOpr.update(pr);
	  					refreshTable(pr);
	  					clearTextFields();
	  				} catch (PFEException e1) {
	  					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	  				}
				}
            }
        );
    
        
        JLabel lblNewLabel_2 = new JLabel("Titre Projet : ");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setBounds(105, 27, 163, 35);
        paneldroite.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Date debut : ");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_1.setBounds(27, 122, 125, 35);
        paneldroite.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("CIN etudiant n°1 : ");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_2.setBounds(278, 171, 163, 35);
        paneldroite.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("CIN etudiant n°2 : ");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_3.setBounds(278, 237, 163, 35);
        paneldroite.add(lblNewLabel_2_3);
        
        JLabel lblNewLabel_2_4 = new JLabel("CIN encadreur: ");
        lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_4.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_4.setBounds(484, 314, 163, 35);
        paneldroite.add(lblNewLabel_2_4);
        
        JLabel lblNewLabel_2_5 = new JLabel("CIN encadreur societe : ");
        lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_5.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_5.setBounds(89, 314, 211, 35);
        paneldroite.add(lblNewLabel_2_5);

        /////////////////////////////////
        jCalendar = new JCalendar();
        jCalendar.getDayChooser().setBackground(SystemColor.inactiveCaption);
        jCalendar.getDayChooser().getDayPanel().setBounds(0, 0, 211, 130);

        // Ajustez 280, 80, 200, et 150 selon vos besoins
        jCalendar.setBounds(35, 155, 211, 147);

        // Ajoutez le JCalendar à un conteneur (par exemple, paneldroite).
        paneldroite.add(jCalendar);


     // Ajoutez le JCalendar au panneau.
     paneldroite.add(jCalendar);
     jCalendar.getDayChooser().setLayout(null);
     
     JScrollPane scrollPane_1 = new JScrollPane();
     scrollPane_1.setBounds(38, 439, 707, 73);
     paneldroite.add(scrollPane_1);
     
     table = new JTable();
     scrollPane_1.setViewportView(table);
    
     model = new DefaultTableModel() {
         @Override
         public boolean isCellEditable(int row, int column) {
             // Rendre toutes les cellules non modifiables
             return false;
         }
     };
     
 	 model.addColumn("ID");
     model.addColumn("Titre");
     model.addColumn("Date debut");
     model.addColumn("1er etudiant");
     model.addColumn("2eme etudiant ");
     model.addColumn("Encadreur");
     model.addColumn("Encadreur societe");
     table.setModel(model);
     table.setRowHeight(25);
     //diminuer la colone de id
     table.getColumnModel().getColumn(0).setPreferredWidth(30);
     table.getColumnModel().getColumn(0).setMaxWidth(30);
     
     
     
     
     JButton btnchercher = new JButton("chercher");
     btnchercher.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent e) {
     		try {
					ProjetDAO DAOpr=new ProjetDAO();
	            	Projet pr;
					pr = lire();
					prInitiale=DAOpr.findByTitre(pr);
					if(prInitiale!=null) {
						afficherpr(prInitiale);
						textField_2.setText(""+prInitiale.getFirstEtudiant().getCIN());
						if(prInitiale.getSecondEtudiant()!=null)
							textField_3.setText(""+prInitiale.getSecondEtudiant().getCIN());
						comboBoxEnc.setSelectedItem(prInitiale.getEncadreur());
						comboBoxEncSoc.setSelectedItem(prInitiale.getEncadreurSoc());
						java.sql.Date date = java.sql.Date.valueOf(prInitiale.getDateDebut());
						jCalendar.setDate(date);
					}
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
     	}
     });
     
     btnchercher.setForeground(Color.WHITE);
     btnchercher.setFont(new Font("Tahoma", Font.BOLD, 18));
     btnchercher.setBackground(new Color(62, 133, 140));
     btnchercher.setBounds(278, 72, 285, 35);
     paneldroite.add(btnchercher);
     
   
     
     //reglage du combox encadreur soc 
     EncadreurSocieteDAO rechenc=new EncadreurSocieteDAO();
     try {
		ToutEncadreurSoc=(ArrayList<EncadreurSociete>) rechenc.findAll();
	} catch (PFEException e1) {
		JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);

	}
     comboBoxEncSoc = new JComboBox(ToutEncadreurSoc.toArray(new EncadreurSociete[0]));
     comboBoxEncSoc.setRenderer(new EncadreurSocieteComboboxRenderer());
     comboBoxEncSoc.setBounds(76, 347, 246, 35);
     paneldroite.add(comboBoxEncSoc);
     
     //regalge du comboox encadreur
     EnseignantDAO rechens=new EnseignantDAO();
     try {
		ToutEnseigant=(ArrayList<Enseignant>) rechens.findAll();
	} catch (PFEException e1) {
		JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
	}
     comboBoxEnc = new JComboBox(ToutEnseigant.toArray(new Enseignant[0]));
     comboBoxEnc.setRenderer(new EnseignantComboBoxRenderer());
     comboBoxEnc.setBounds(451, 347, 246, 35);
     paneldroite.add(comboBoxEnc);

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
        

		//////////////////////////////////
		lblAfficherProjets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre AfficherProjets
				Affichage_projet afficherProjetsFrame = new Affichage_projet();
				afficherProjetsFrame.setVisible(true);
				ChercherProjet.this.setVisible(false);
			}
		});
		
		lblCreerProjet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre CreerProjet
				Creation_projet creerProjetFrame = new Creation_projet();
				creerProjetFrame.setVisible(true);
				ChercherProjet.this.setVisible(false);
			}
		});
		lblSupprimerProjet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre SupprimerProjet
				Supprimer_projet supprimerProjetFrame = new Supprimer_projet();
				supprimerProjetFrame.setVisible(true);
				ChercherProjet.this.setVisible(false);
			}
		});
/*			lblChercher_projet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre ChercherEtModifier
				ChercherProjet chercherEtModifierFrame = new ChercherProjet();
				chercherEtModifierFrame.setVisible(true);
			}
		});*/
		lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Supposons que vous avez une classe qui s'appelle RechercheProjet_etudiant
		        RechercheProjet_etudiant rechercheEtudiantFrame = new RechercheProjet_etudiant();
		        rechercheEtudiantFrame.setVisible(true);
		        ChercherProjet.this.setVisible(false);
		    }
		});

		lblChercher_par_encadreur.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Vous avez déjà une classe nommée RechercheProjet_encadreur, donc ici vous pourriez soit
		        // réutiliser la même instance, soit créer une nouvelle selon le contexte
		        RechercheProjet_encadreur rechercheEncadreurFrame = new RechercheProjet_encadreur();
		        rechercheEncadreurFrame.setVisible(true);
		        ChercherProjet.this.setVisible(false);
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



      }
    public Projet lire() throws PFEException{
    	// Vérification du champ titre
        if (textField.getText().isEmpty() || !(textField.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(40);
        Projet pr=new Projet();
        pr.setTitre(textField.getText());
        return pr;
        
    }
        

    public Projet liremodification() throws PFEException {

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
        

        // Convertir la chaîne de date en LocalDate
        java.util.Date selectedDate = jCalendar.getDate();
        Instant instant = selectedDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();

        // Récupération des valeurs des champs CIN
        int CIN1 = Integer.parseInt(textField_2.getText());
   
        // Création d'une instance
        
        Projet projet = new Projet();
        
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
        
        EtudiantDAO rechetud=new EtudiantDAO();
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setCIN(CIN1);
        etudiant1=rechetud.find(etudiant1);
        
        Etudiant etudiant2 = new Etudiant();
        if(!textField_3.getText().isEmpty()) {
        	int CIN2 = Integer.parseInt(textField_3.getText());
        	etudiant2.setCIN(CIN2);
        	etudiant2=rechetud.find(etudiant2);
        	projet.setSecondEtudiant(etudiant2);
        }
        
        projet.setTitre(textField.getText());
        projet.setDateDebut(localDate);
        projet.setEncadreur(encadreur);
        projet.setEncadreurSoc(encadreurSos);
        projet.setFirstEtudiant(etudiant1);
        projet.setId(prInitiale.getId());
        
        return projet;
    }
    public void afficherpr(Projet projet) {
    	
        // Créer le modèle de tableau avec les données de la base de données
       
    	Object[] row = new Object[7];
        row[0] = projet.getId();
        row[1] = projet.getTitre();
        row[2] = projet.getDateDebut();
        
        // Formatage des informations du 1er étudiant en HTML
        Etudiant firstEtudiant = projet.getFirstEtudiant();
        row[3] = String.format("<html>%s<br>%s<br>%s</html>",firstEtudiant.getCIN(), firstEtudiant.getNom(), firstEtudiant.getPrenom());
        Etudiant secondEtudiant = projet.getSecondEtudiant();
        if(secondEtudiant!=null)
        	row[4] = String.format("<html>%s<br>%s<br>%s</html>",secondEtudiant.getCIN(), secondEtudiant.getNom(), secondEtudiant.getPrenom());
        else {
        	row[4]="***********";
        }
        Enseignant encadreur = projet.getEncadreur();
        row[5] = String.format("<html>%s<br>%s<br>%s</html>",encadreur.getCIN(), encadreur.getNom(), encadreur.getPrenom());
        
        EncadreurSociete encadreurSoc = projet.getEncadreurSoc();
        row[6] = String.format("<html>%s<br>%s<br>%s</html>",encadreurSoc.getCIN(), encadreurSoc.getNom(), encadreurSoc.getPrenom());
        
        model.setRowCount(0);
        model.addRow(row);
        ///////////////////////////////////////////////////
     // Appliquer le modèle de tableau et le rendu personnalisé
        table.setModel(model);
        table.setRowHeight(25);
        setupHtmlCellRendering(table);
        
        // Ajuster la hauteur des lignes pour s'adapter au contenu HTML
        for (int row1 = 0; row1 < table.getRowCount(); row1++) {
            int rowHeight = table.getRowHeight();
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row1, column), row1, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }
            table.setRowHeight(row1, rowHeight);
        }
    }
    
 // Méthode pour configurer le rendu de cellule HTML
    private void setupHtmlCellRendering(JTable table) {
        // Utiliser le rendu de cellule personnalisé
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (value instanceof String && ((String)value).contains("<html>")) {
                    // Assurez-vous que les valeurs de type String avec HTML sont interprétées correctement
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                } else {
                    // Utilisez toString() pour gérer les valeurs non-String
                    return super.getTableCellRendererComponent(table, value == null ? "" : value.toString(), isSelected, hasFocus, row, column);
                }
            }
        });
    }
    
    public void refreshTable(Projet pr) {
    	afficherpr(pr);
    
	}
    private void clearTextFields() {
        textField.setText(""); 
        textField_2.setText(""); 
        textField_3.setText(""); 
        comboBoxEncSoc.setSelectedIndex(0);
        comboBoxEnc.setSelectedIndex(0);
        model.setRowCount(0);
    }
}

