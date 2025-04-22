package interfacePFE;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Acceuil_Projet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Acceuil_Projet ajouterEnseignant = new Acceuil_Projet();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Acceuil_Projet() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Acceuil Projet");
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

  
  JPanel panel = new JPanel();
  panel.setLayout(null);
  panel.setBackground(new Color(115, 186, 193));
  panel.setBounds(215, 0, 769, 28);
  contentPane.add(panel);
  
  JLabel lblNewLabel = new JLabel("espace projet");
  lblNewLabel.setForeground(Color.WHITE);
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
				Acceuil_Projet.this.setVisible(false);

	    }
	});
	lblCreerProjet.addMouseListener(new MouseAdapter() {
			@Override
	    public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre CreerProjet
				Creation_projet creerProjetFrame = new Creation_projet();
				creerProjetFrame.setVisible(true);
				Acceuil_Projet.this.setVisible(false);
	    }
	});
	lblSupprimerProjet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ouvrir la fenêtre SupprimerProjet
				Supprimer_projet supprimerProjetFrame = new Supprimer_projet();
				supprimerProjetFrame.setVisible(true);
				Acceuil_Projet.this.setVisible(false);
	    }
	});
	lblChercher_projet.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // Ouvrir la fenêtre ChercherEtModifier
	        ChercherProjet chercherEtModifierFrame = new ChercherProjet();
	        chercherEtModifierFrame.setVisible(true);
	        Acceuil_Projet.this.setVisible(false);
	    }
	});
	
	lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        RechercheProjet_etudiant rechercheEtudiantFrame = new RechercheProjet_etudiant();
	        rechercheEtudiantFrame.setVisible(true);
	        Acceuil_Projet.this.setVisible(false);
	    }
	});

	lblChercher_par_encadreur.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {

	        RechercheProjet_encadreur rechercheEncadreurFrame = new RechercheProjet_encadreur();
	        rechercheEncadreurFrame.setVisible(true);
	        Acceuil_Projet.this.setVisible(false);
	    }
	});
	//TODO  ////////////////////////////////
    
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            // Ouvrir la nouvelle interface ici
        	Acceuil frame = new Acceuil();
			frame.setVisible(true);
        }
    });
    
    //////////////////////////////////////////////////////////////////////

  	
////droite panel 

	JPanel paneldroite = new JPanel();
	paneldroite.setLayout(null);
	paneldroite.setBounds(215, 28, 769, 533);
	contentPane.add(paneldroite);
	
	JLabel lblNewLabel_2 = new JLabel("Espace Projet");
	lblNewLabel_2.setForeground(new Color(64, 128, 128));
	lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
	lblNewLabel_2.setBounds(238, 46, 293, 62);
	paneldroite.add(lblNewLabel_2);
	
    ImageIcon originalIcon3 = new ImageIcon("pfe.png");
    ImageIcon newIcon3 = resizeIcon(originalIcon3, 350, 350);
    JLabel lblNewLabel_3 = new JLabel(newIcon3);
    lblNewLabel_3.setBounds(209, 142, 350, 350);
	paneldroite.add(lblNewLabel_3);
	
	
    
    }
	 // Create a method to resize the image while preserving the aspect ratio
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage;
        
        // Calculate the new size while preserving the aspect ratio
        double aspectRatio = (double) icon.getIconWidth() / icon.getIconHeight();
        if (width / aspectRatio <= height) {
            // Width is limiting factor; adjust height to maintain aspect ratio
            resizedImage = img.getScaledInstance(width, (int) (width / aspectRatio), Image.SCALE_SMOOTH);
        } else {
            // Height is limiting factor; adjust width to maintain aspect ratio
            resizedImage = img.getScaledInstance((int) (height * aspectRatio), height, Image.SCALE_SMOOTH);
        }
        
        return new ImageIcon(resizedImage);
    }
    }



        