package interfacePFE;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class Acceuil_Etudiant extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil_Etudiant frame = new Acceuil_Etudiant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/

	public Acceuil_Etudiant() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Acceuil etudiant");
        setLocationRelativeTo(null);

        ImageIcon icone = new ImageIcon("etudiant.png");
        setIconImage(icone.getImage());

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelgauche = new JPanel();
		panelgauche.setLayout(null);
		panelgauche.setBackground(new Color(62, 133, 140));
		panelgauche.setBounds(0, 0, 216, 561);
		contentPane.add(panelgauche);
		
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1.setBounds(45, 0, 130, 130);
        panelgauche.add(lblNewLabel_1);

        ImageIcon icon = new ImageIcon("etudiant.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 130, 196, 420);
		panelgauche.add(panel_1);
		
		JLabel lblAfficherEtudiant = new JLabel("afficher etudiant");
		lblAfficherEtudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAfficherEtudiant.setBounds(53, 0, 143, 61);
		panel_1.add(lblAfficherEtudiant);
		
		JLabel lblAjouterEtudiant = new JLabel("ajouter etudiant");
		lblAjouterEtudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAjouterEtudiant.setBounds(53, 60, 143, 61);
		panel_1.add(lblAjouterEtudiant);
		
		JLabel lblSupprimerEtudiant = new JLabel("supprimer etudiant");
		lblSupprimerEtudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupprimerEtudiant.setBounds(53, 120, 143, 61);
		panel_1.add(lblSupprimerEtudiant);
		
		JLabel lblChercherEtudiant = new JLabel("chercher et modifier");
		lblChercherEtudiant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant.setBounds(53, 180, 143, 61);
		panel_1.add(lblChercherEtudiant);
		
		JLabel lblChercherEtudiant_N = new JLabel("chercher par N/P");
		lblChercherEtudiant_N.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant_N.setBounds(53, 240, 143, 61);
		panel_1.add(lblChercherEtudiant_N);
		
		JLabel lblChercherEtudiant_Note = new JLabel("chercher par note");
		lblChercherEtudiant_Note.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant_Note.setBounds(53, 360, 143, 61);
		panel_1.add(lblChercherEtudiant_Note);
		
		JLabel lblChercherEtudiant_Groupe = new JLabel("chercher par groupe");
		lblChercherEtudiant_Groupe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant_Groupe.setBounds(53, 300, 143, 61);
		panel_1.add(lblChercherEtudiant_Groupe);
		
	       ///////////////icone de afficher etudiant 
        // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
        ImageIcon imageIcon = new ImageIcon("db.png"); // Remplacez "ajouter.png" par le chemin de votre image

        // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
        Image image = imageIcon.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon = new ImageIcon(nouvelleImage); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée
        JLabel icon1 = new JLabel(imageIcon);
        icon1.setBounds(3, 10, 40, 40);
        panel_1.add(icon1);

        
        ///////////////icone de ajouter etudiant 
        // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
        ImageIcon imageIcon2 = new ImageIcon("ajouter.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

        // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
        Image image2 = imageIcon2.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage2 = image2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon2 = new ImageIcon(nouvelleImage2); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée
        JLabel icon2 = new JLabel(imageIcon2);
        icon2.setBounds(3, 70, 40, 40);
        panel_1.add(icon2);
        
        
        ///////////////icone de supprimer etudiant 
        // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
        ImageIcon imageIcon3 = new ImageIcon("supprimer.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

        // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
        Image image3 = imageIcon3.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage3 = image3.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon3 = new ImageIcon(nouvelleImage3); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée
        JLabel icon3 = new JLabel(imageIcon3);
        icon3.setBounds(3, 130, 40, 40);
        panel_1.add(icon3);

        
        ///////////////icone de modifier etudiant 
        // Créez une instance de ImageIcon en chargeant l'image depuis un fichier
        ImageIcon imageIcon4 = new ImageIcon("id.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

        // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
        Image image4 = imageIcon4.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage4 = image4.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon4 = new ImageIcon(nouvelleImage4); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée
        JLabel icon4 = new JLabel(imageIcon4);
        icon4.setBounds(3, 190, 40, 40);
        panel_1.add(icon4);


        ///////////////icone de modifier etudiant par nom
        ImageIcon imageIcon5 = new ImageIcon("chercher.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image

        // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
        Image image5 = imageIcon5.getImage(); // Obtenez l'image de l'icône
        Image nouvelleImage5 = image5.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
        imageIcon5 = new ImageIcon(nouvelleImage5); // Créez une nouvelle ImageIcon avec l'image redimensionnée

        // Créez le JLabel avec l'image chargée et redimensionnée
        JLabel icon5 = new JLabel(imageIcon5);
        icon5.setBounds(3, 250, 40, 40);
        panel_1.add(icon5);
              
        
        ///////////////icone de modifier etudiant par groupe
	    ImageIcon imageIcon6 = new ImageIcon("groupe.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image
	
	    // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
	    Image image6 = imageIcon6.getImage(); // Obtenez l'image de l'icône
	    Image nouvelleImage6 = image6.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
	    imageIcon6 = new ImageIcon(nouvelleImage6); // Créez une nouvelle ImageIcon avec l'image redimensionnée
	
	    // Créez le JLabel avec l'image chargée et redimensionnée
	    JLabel icon6 = new JLabel(imageIcon6);
	    icon6.setBounds(3, 310, 40, 40);
	    panel_1.add(icon6);
        
	    
        ///////////////icone de modifier etudiant par note
	    ImageIcon imageIcon7 = new ImageIcon("note.png"); // Remplacez "chemin/vers/votre/image.png" par le chemin de votre image
		
	    // Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
	    Image image7 = imageIcon7.getImage(); // Obtenez l'image de l'icône
	    Image nouvelleImage7 = image7.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
	    imageIcon7 = new ImageIcon(nouvelleImage7); // Créez une nouvelle ImageIcon avec l'image redimensionnée
	
	    // Créez le JLabel avec l'image chargée et redimensionnée
	    JLabel icon7 = new JLabel(imageIcon7);
	    icon7.setBounds(3, 370, 40, 40);
	    panel_1.add(icon7);
       
		JPanel paneldroite = new JPanel();
		paneldroite.setBounds(215, 28, 769, 533);
		contentPane.add(paneldroite);
		paneldroite.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Espace Etudiant");
		lblNewLabel_2.setForeground(new Color(64, 128, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_2.setBounds(215, 46, 338, 62);
		paneldroite.add(lblNewLabel_2);
		

        ImageIcon originalIcon3 = new ImageIcon("etudiant.png");
        ImageIcon newIcon3 = resizeIcon(originalIcon3, 350, 350);
        JLabel lblNewLabel_3 = new JLabel(newIcon3);
        lblNewLabel_3.setBounds(209, 142, 350, 350);
		paneldroite.add(lblNewLabel_3);

		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(115, 186, 193));
		panel.setBounds(215, 0, 769, 28);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("espace etudiant");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 0, 280, 25);
		panel.add(lblNewLabel);
		
		
        ///////////////////////////////////////////////////
        lblAfficherEtudiant.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


            
                // Ouvrir la fenêtre Ajouter_etudiant
                Afficher_etudiant afficherEtudiantFrame = new Afficher_etudiant();
                afficherEtudiantFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
                
           
            }
        });

        
        lblAjouterEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Ouvrir la fenêtre Ajouter_etudiant
                Ajouter_etudiant ajouterEtudiantFrame = new Ajouter_etudiant();
                ajouterEtudiantFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        
        lblSupprimerEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Supprimer_etudiant supprimerEtudiantFrame = new Supprimer_etudiant();
                supprimerEtudiantFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        
        lblChercherEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Chercher_etudiant chercherEtudiantFrame = new Chercher_etudiant();
                chercherEtudiantFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        // les nouveaux recherches
        //recherche par nom
        lblChercherEtudiant_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_N chercherEtudiantNFrame = new RechercheEtudiant_N();
                chercherEtudiantNFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        // recherche par note
        lblChercherEtudiant_Note.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_Note chercherEtudiantNoteFrame = new RechercheEtudiant_Note();
                chercherEtudiantNoteFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        //recherche par groupe
        lblChercherEtudiant_Groupe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_Groupe chercherEtudiantGroupeFrame = new RechercheEtudiant_Groupe();
                chercherEtudiantGroupeFrame.setVisible(true);
                Acceuil_Etudiant.this.setVisible(false);
            }
        });
        //////////////////////////////////////////
      //TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil frame = new Acceuil();
				frame.setVisible(true);
            }
        });
        
        //////////////////////////////////////////////////////////////////////
        
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

