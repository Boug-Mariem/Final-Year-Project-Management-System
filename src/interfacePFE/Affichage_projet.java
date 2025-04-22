package interfacePFE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import DAO.ProjetDAO;
import projetPFE.Projet;
import projetPFE.Projet;
import projetPFE.EncadreurSociete;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.PFEException;

public class Affichage_projet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ArrayList<Projet> tousLesProjets;
    /**
     * @wbp.nonvisual location=448,-31
     */

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Affichage_projet ajouterProjet = new Affichage_projet();
                    ajouterProjet.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Affichage_projet() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Afficher projet");
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
/*	lblAfficherProjets.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre AfficherProjets
			Affichage_projet afficherProjetsFrame = new Affichage_projet();
			afficherProjetsFrame.setVisible(true);
		}
	});*/
	
	lblCreerProjet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre CreerProjet
			Creation_projet creerProjetFrame = new Creation_projet();
			creerProjetFrame.setVisible(true);
			Affichage_projet.this.setVisible(false);

		}
	});
	lblSupprimerProjet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre SupprimerProjet
			Supprimer_projet supprimerProjetFrame = new Supprimer_projet();
			supprimerProjetFrame.setVisible(true);
			Affichage_projet.this.setVisible(false);
		}
	});
		lblChercher_projet.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// Ouvrir la fenêtre ChercherEtModifier
			ChercherProjet chercherEtModifierFrame = new ChercherProjet();
			chercherEtModifierFrame.setVisible(true);
			Affichage_projet.this.setVisible(false);
		}
	});
	lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // Supposons que vous avez une classe qui s'appelle RechercheProjet_etudiant
	        RechercheProjet_etudiant rechercheEtudiantFrame = new RechercheProjet_etudiant();
	        rechercheEtudiantFrame.setVisible(true);
	        Affichage_projet.this.setVisible(false);
	    }
	});

	lblChercher_par_encadreur.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // Vous avez déjà une classe nommée RechercheProjet_encadreur, donc ici vous pourriez soit
	        // réutiliser la même instance, soit créer une nouvelle selon le contexte
	        RechercheProjet_encadreur rechercheEncadreurFrame = new RechercheProjet_encadreur();
	        rechercheEncadreurFrame.setVisible(true);
	        Affichage_projet.this.setVisible(false);
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
        
        JPanel paneldroite = new JPanel();
        paneldroite.setLayout(null);
        paneldroite.setBackground(UIManager.getColor("Button.background"));
        paneldroite.setBounds(215, 28, 769, 533);
        contentPane.add(paneldroite);
        
        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(27, 11, 719, 511);
        paneldroite.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
 
        
        
        try {
			afficher();
		} catch (PFEException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);

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

    // Votre méthode 'afficher' complète
    public void afficher() throws PFEException{
        ProjetDAO etdao = new ProjetDAO();
        tousLesProjets = (ArrayList<Projet>) etdao.findAll();
    
        // Créer le modèle de tableau avec les données de la base de données
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };        
        model.addColumn("N°");
        model.addColumn("ID");
        model.addColumn("Titre");
        model.addColumn("Date debut");
        model.addColumn("1er etudiant");
        model.addColumn("2eme etudiant ");
        model.addColumn("Encadreur");
        model.addColumn("Encadreur societe");
        int j=0;
        // Remplir le modèle avec les données des projets
        for (Projet projet : tousLesProjets) {
            Object[] row = new Object[8];
            j=j+1;
            row[0] = j;
            row[1] = projet.getId();
            row[2] = projet.getTitre();
            row[3] = projet.getDateDebut();
            
            // Formatage des informations du 1er étudiant en HTML
            Etudiant firstEtudiant = projet.getFirstEtudiant();
            row[4] = String.format("<html>%s<br>%s<br>%s</html>",firstEtudiant.getCIN(), firstEtudiant.getNom(), firstEtudiant.getPrenom());
            Etudiant secondEtudiant = projet.getSecondEtudiant();
            if(secondEtudiant!=null)
            	row[5] = String.format("<html>%s<br>%s<br>%s</html>",secondEtudiant.getCIN(), secondEtudiant.getNom(), secondEtudiant.getPrenom());
            else {
            	row[5]="***********";
            }
            Enseignant encadreur = projet.getEncadreur();
            row[6] = String.format("<html>%s<br>%s<br>%s</html>",encadreur.getCIN(), encadreur.getNom(), encadreur.getPrenom());
            
            EncadreurSociete encadreurSoc = projet.getEncadreurSoc();
            row[7] = String.format("<html>%s<br>%s<br>%s</html>",encadreurSoc.getCIN(), encadreurSoc.getNom(), encadreurSoc.getPrenom());
            
            model.addRow(row);
        }
    
        // Appliquer le modèle de tableau et le rendu personnalisé
        table.setModel(model);
        table.setRowHeight(25);
        // numero
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(0).setMaxWidth(30);
       // id
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        setupHtmlCellRendering(table);
        
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


}


