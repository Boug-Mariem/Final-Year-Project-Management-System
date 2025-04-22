package interfacePFE;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Acceuil_EncadreurSociete extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil_EncadreurSociete frame = new Acceuil_EncadreurSociete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public Acceuil_EncadreurSociete() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Acceuil Encadreur Societe");
        setLocationRelativeTo(null);
        
        ImageIcon icone = new ImageIcon("encadreur_societe.png");
        setIconImage(icone.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelgauche = new JPanel();
		panelgauche.setLayout(null);
		panelgauche.setBackground(new Color(62, 133, 140));
		panelgauche.setBounds(0, 0, 219, 561);
		contentPane.add(panelgauche);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(135, 206, 250));
		lblNewLabel_1.setBounds(45, 0, 130, 130);
		panelgauche.add(lblNewLabel_1);
		
        ImageIcon icon = new ImageIcon("encadreur_societe.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);
		
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 146, 198, 300);//initale 10, 146, 196, 325
        panelgauche.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblAfficherEncadreurSociete = new JLabel("afficher Encadreur");
        lblAfficherEncadreurSociete.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAfficherEncadreurSociete.setBounds(51, 0, 133, 61);
        panel_1.add(lblAfficherEncadreurSociete);

        JLabel lblAjouterEncadreurSociete = new JLabel("ajouter Encadreur");
        lblAjouterEncadreurSociete.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAjouterEncadreurSociete.setBounds(51, 60, 143, 61);
        panel_1.add(lblAjouterEncadreurSociete);

        JLabel lblSupprimerEncadreurSociete = new JLabel("supprimer Encadreur");
        lblSupprimerEncadreurSociete.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSupprimerEncadreurSociete.setBounds(51, 120, 169, 61);
        panel_1.add(lblSupprimerEncadreurSociete);

        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1.setBounds(3, 10, 40, 40);
        panel_1.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("");
        lblNewLabel_1_1_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_1.setBounds(3, 70, 40, 40);
        panel_1.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("");
        lblNewLabel_1_1_2.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_2.setBounds(3, 130, 40, 40);
        panel_1.add(lblNewLabel_1_1_2);

        JLabel lblChercherEncadreurSociete = new JLabel("chercher et modifier");
        lblChercherEncadreurSociete.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherEncadreurSociete.setBounds(51, 180, 159, 61);
        panel_1.add(lblChercherEncadreurSociete);


        JLabel lblNewLabel_1_1_1_1 = new JLabel("");
        lblNewLabel_1_1_1_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_1_1.setBounds(3, 190, 40, 40);
        panel_1.add(lblNewLabel_1_1_1_1);
        
        JLabel lblChercherEncadreurSociete_N = new JLabel("chercher par N/P");
        lblChercherEncadreurSociete_N.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherEncadreurSociete_N.setBounds(51, 240, 159, 61);
        panel_1.add(lblChercherEncadreurSociete_N);

        

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
		Image nouvelleImage4 = image4.getScaledInstance(46, 46, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
		imageIcon4 = new ImageIcon(nouvelleImage4); // Créez une nouvelle ImageIcon avec l'image redimensionnée
		
		// Créez le JLabel avec l'image chargée et redimensionnée
		JLabel icon4 = new JLabel(imageIcon4);
		icon4.setBounds(3, 194, 40, 40);
		panel_1.add(icon4);
		
		
		/////////////// Créez une instance de ImageIcon en chargeant l'image depuis un fichier
		ImageIcon imageIcon5 = new ImageIcon("chercher.png"); // Remplacez "icone5.png" par le chemin de votre image

		// Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
		Image image5 = imageIcon5.getImage(); // Obtenez l'image de l'icône
		Image nouvelleImage5 = image5.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
		imageIcon5 = new ImageIcon(nouvelleImage5); // Créez une nouvelle ImageIcon avec l'image redimensionnée

		// Créez le JLabel avec l'image chargée et redimensionnée
		JLabel icon5 = new JLabel(imageIcon5);
		icon5.setBounds(3, 250, 40, 40);
		panel_1.add(icon5);

           
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(115, 186, 193));
		panel.setBounds(218, 0, 769, 28);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("espace Encadreur Societe");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 0, 280, 25);
		panel.add(lblNewLabel);
		
		JPanel paneldroite = new JPanel();
		paneldroite.setLayout(null);
		paneldroite.setBounds(218, 28, 769, 533);
		contentPane.add(paneldroite);
		
		JLabel lblNewLabel_2 = new JLabel("Espace Encadreur Societe");
		lblNewLabel_2.setForeground(new Color(64, 128, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_2.setBounds(120, 46, 528, 62);
		paneldroite.add(lblNewLabel_2);
		
        ImageIcon originalIcon3 = new ImageIcon("encadreur_societe.png");
        ImageIcon newIcon3 = resizeIcon(originalIcon3, 350, 350);
        JLabel lblNewLabel_3 = new JLabel(newIcon3);
        lblNewLabel_3.setBounds(209, 142, 350, 350);
		paneldroite.add(lblNewLabel_3);
		
		
        
        ///////////////////////////////////////////////////
        lblAfficherEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Afficher_EncadreurSociete
                Afficher_EncadreurSociete afficherEncadreurSocieteFrame = new Afficher_EncadreurSociete();
                afficherEncadreurSocieteFrame.setVisible(true);
                Acceuil_EncadreurSociete.this.setVisible(false);

            }
        });
        
        lblAjouterEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_encadreurSociete
                Ajouter_EncadreurSociete ajouterEncadreurSocieteFrame = new Ajouter_EncadreurSociete();
                ajouterEncadreurSocieteFrame.setVisible(true);
                Acceuil_EncadreurSociete.this.setVisible(false);
            }
        });
        
        lblSupprimerEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Supprimer_EncadreurSociete
                Supprimer_EncadreurSociete supprimerEncadreurSocieteFrame = new Supprimer_EncadreurSociete();
                supprimerEncadreurSocieteFrame.setVisible(true);
                Acceuil_EncadreurSociete.this.setVisible(false);
            }
        });
        
        lblChercherEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Chercher_EncadreurSociete
                Chercher_EncadreurSociete chercherEncadreurSocieteFrame = new Chercher_EncadreurSociete();
                chercherEncadreurSocieteFrame.setVisible(true);
                Acceuil_EncadreurSociete.this.setVisible(false);
            }
        });
        
        lblChercherEncadreurSociete_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Chercher_EncadreurSociete
                RechercheEncadreurSociete_N RechercheEncadreurSociete_NFrame = new RechercheEncadreurSociete_N();
                RechercheEncadreurSociete_NFrame.setVisible(true);
                Acceuil_EncadreurSociete.this.setVisible(false);
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

}
    //////////////////////////////////////////////////////////////////////
		
	
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
