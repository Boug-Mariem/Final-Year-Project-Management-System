package interfacePFE;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import DAO.EtudiantDAO;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import projetPFE.Etudiant;
import projetPFE.PFEException;

public class Chercher_etudiant extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTable tableEtud;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_1;
    private JComboBox<Etudiant.Groupe> groupeComboBox = new JComboBox<>(Etudiant.Groupe.values());
    private ArrayList<Etudiant> toutetud;
    private DefaultTableModel modeltableEtud;
    private JTextField textField_3;
    private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chercher_etudiant frame = new Chercher_etudiant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Chercher_etudiant() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Modifier etudiant ");
        setLocationRelativeTo(null);

        ImageIcon icone = new ImageIcon("etudiant.png");
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

        ImageIcon icon = new ImageIcon("etudiant.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 130, 196, 420);
        panelgauche.add(panel_1);
        panel_1.setLayout(null);

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
        
        //recherche par nom
        JLabel lblChercherEtudiant_N = new JLabel("chercher par N/P");
        lblChercherEtudiant_N.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant_N.setBounds(53, 240, 143, 61);
		panel_1.add(lblChercherEtudiant_N);
		
		JLabel lblChercherEtudiant_Note = new JLabel("chercher par note");
		lblChercherEtudiant_Note.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherEtudiant_Note.setBounds(53, 360, 143, 61);
		panel_1.add(lblChercherEtudiant_Note);
		
		JLabel lblChercherEtudiant_Groupe = new JLabel("chercher par groupe");
		lblChercherEtudiant_Groupe.setBounds(53, 300, 143, 61);
		panel_1.add(lblChercherEtudiant_Groupe);
		lblChercherEtudiant_Groupe.setFont(new Font("Tahoma", Font.BOLD, 14));

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


        JPanel panel = new JPanel();
        panel.setBackground(new Color(115, 186, 193));
        panel.setBounds(215, 0, 769, 28);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("espace etudiant");
        lblNewLabel.setForeground(new Color(255, 255, 255));
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
                Chercher_etudiant.this.setVisible(false);
            }
        });
        
        lblAjouterEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Ajouter_etudiant ajouterEtudiantFrame = new Ajouter_etudiant();
                ajouterEtudiantFrame.setVisible(true);
                Chercher_etudiant.this.setVisible(false);
            }
        });
        
        lblSupprimerEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Supprimer_etudiant supprimerEtudiantFrame = new Supprimer_etudiant();
                supprimerEtudiantFrame.setVisible(true);
                Chercher_etudiant.this.setVisible(false);
            }
        });
        /*
        lblChercherEtudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Chercher_etudiant chercherEtudiantFrame = new Chercher_etudiant();
                chercherEtudiantFrame.setVisible(true);
            }
        });*/
        // les nouveaux recherches
        //recherche par nom
        lblChercherEtudiant_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_N chercherEtudiantNFrame = new RechercheEtudiant_N();
                chercherEtudiantNFrame.setVisible(true);
                Chercher_etudiant.this.setVisible(false);
            }
        });
        // recherche par note
        lblChercherEtudiant_Note.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_Note chercherEtudiantNoteFrame = new RechercheEtudiant_Note();
                chercherEtudiantNoteFrame.setVisible(true);
                Chercher_etudiant.this.setVisible(false);
            }
        });
        //recherche par groupe
        lblChercherEtudiant_Groupe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre chercher etudiant par nom
                RechercheEtudiant_Groupe chercherEtudiantGroupeFrame = new RechercheEtudiant_Groupe();
                chercherEtudiantGroupeFrame.setVisible(true);
                Chercher_etudiant.this.setVisible(false);
            }
        });
//TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil_Etudiant frame = new Acceuil_Etudiant();
				frame.setVisible(true);
            }
        });
        //////////////////////////////////////////              
              textField = new JTextField();
              textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
              textField.setColumns(10);
              textField.setBounds(254, 32, 285, 35);
              paneldroite.add(textField);
              
              JLabel lblNewLabel_2 = new JLabel("CIN :  ");
              lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2.setBounds(85, 33, 143, 35);
              paneldroite.add(lblNewLabel_2);
              
              JButton btnChercher = new JButton("chercher");
              btnChercher.setForeground(Color.WHITE);
              btnChercher.setFont(new Font("Tahoma", Font.BOLD, 18));
              btnChercher.setBackground(new Color(62, 133, 140));
              btnChercher.setBounds(254, 78, 285, 35);
              paneldroite.add(btnChercher);
              
              btnChercher.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
      				try {
      					EtudiantDAO DAOetud=new EtudiantDAO();
      	            	Etudiant etud;
      					etud = lire();
      					etud=DAOetud.find(etud);
      					if(etud!=null) {
      						afficherEtud(etud);
      						textField_1.setText(etud.getNom());
      						textField_2.setText(etud.getPrenom());
      						groupeComboBox.setSelectedItem(etud.getGroupe());
      						textField_3.setText(etud.getEmail());
      						textField_4.setText(etud.getNumTel());
      					}
      				} catch (PFEException e1) {
      					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
      				}
                  	
                  }
              }
              );
              
              JScrollPane scrollPane = new JScrollPane();
              scrollPane.setBounds(10, 419, 749, 92);
              paneldroite.add(scrollPane);

              table = new JTable();
              scrollPane.setViewportView(table);
               
              
              
              textField_2 = new JTextField();
              textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
              textField_2.setColumns(10);
              textField_2.setBounds(254, 233, 285, 35);
              paneldroite.add(textField_2);
              
              textField_1 = new JTextField();
              textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
              textField_1.setColumns(10);
              textField_1.setBounds(254, 187, 285, 35);
              paneldroite.add(textField_1);
              
              
              groupeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));
              groupeComboBox.setBounds(254, 279, 285, 35);
              paneldroite.add(groupeComboBox);
              
              JButton btnModifier = new JButton("modifier");
              btnModifier.setForeground(Color.WHITE);
              btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
              btnModifier.setBackground(new Color(62, 133, 140));
              btnModifier.setBounds(254, 373, 285, 35);
              paneldroite.add(btnModifier);
              
              btnModifier.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
      				try {
      					EtudiantDAO DAOetud=new EtudiantDAO();
      	            	Etudiant etud;
      					etud = lire_modification();
      					DAOetud.update(etud);
      					afficherEtud(etud);
      					refreshTable();
      					clearTextFields();
      				} catch (PFEException e1) {
      					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
      				}
                  	
                  }
              }
              );
              
              JLabel lblNewLabel_2_1 = new JLabel("Nom : ");
              lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2_1.setBounds(60, 188, 163, 35);
              paneldroite.add(lblNewLabel_2_1);
              
              JLabel lblNewLabel_2_2 = new JLabel("Prenom : ");
              lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2_2.setBounds(75, 233, 173, 35);
              paneldroite.add(lblNewLabel_2_2);
              
              JLabel lblNewLabel_2_3 = new JLabel("Groupe : ");
              lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2_3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2_3.setBounds(85, 279, 163, 35);
              paneldroite.add(lblNewLabel_2_3);
              
              //pour affichege d l'etudiant trouve
              JScrollPane scrollPane_1 = new JScrollPane();
              scrollPane_1.setBounds(10, 124, 749, 48);
              paneldroite.add(scrollPane_1);
              JLabel lblNewLabel_2_1_1 = new JLabel("Email : ");
              lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2_1_1.setBounds(10, 328, 72, 35);
              paneldroite.add(lblNewLabel_2_1_1);
              
              textField_3 = new JTextField();
              textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
              textField_3.setColumns(10);
              textField_3.setBounds(78, 327, 285, 35);
              paneldroite.add(textField_3);
              
              JLabel lblNewLabel_2_1_2 = new JLabel("Téléphone : ");
              lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
              lblNewLabel_2_1_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
              lblNewLabel_2_1_2.setBounds(373, 328, 126, 35);
              paneldroite.add(lblNewLabel_2_1_2);
              
              textField_4 = new JTextField();
              textField_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
              textField_4.setColumns(10);
              textField_4.setBounds(509, 327, 235, 35);
              paneldroite.add(textField_4);
              
              tableEtud = new JTable();
              scrollPane_1.setViewportView(tableEtud);
              
              modeltableEtud = new DefaultTableModel() {
                  @Override
                  public boolean isCellEditable(int row, int column) {
                      // Rendre toutes les cellules non modifiables
                      return false;
                  }
              };
              modeltableEtud.addColumn("CIN");
              modeltableEtud.addColumn("Nom");
              modeltableEtud.addColumn("Prenom");
              modeltableEtud.addColumn("Groupe");
              modeltableEtud.addColumn("Email");
              modeltableEtud.addColumn("Téléphone");
              tableEtud.setModel(modeltableEtud);
              tableEtud.setRowHeight(25);
              
              //diminuer la colone de cin
              tableEtud.getColumnModel().getColumn(0).setPreferredWidth(80);
              tableEtud.getColumnModel().getColumn(0).setMaxWidth(80);
              //diminuer la colone de grp
              tableEtud.getColumnModel().getColumn(3).setPreferredWidth(240);
              tableEtud.getColumnModel().getColumn(3).setMaxWidth(240);
              //diminuer la colone de email
              tableEtud.getColumnModel().getColumn(4).setPreferredWidth(130);
              tableEtud.getColumnModel().getColumn(4).setMaxWidth(130);
              //diminuer la colone de numtel
              tableEtud.getColumnModel().getColumn(5).setPreferredWidth(80);
              tableEtud.getColumnModel().getColumn(5).setMaxWidth(80);

              // Établir la connexion à la base de données
              try {
				afficher();
			} catch (PFEException e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
			}
          }	   // Méthode pour lire les données de l'étudiant à partir des champs de saisie
    
	public Etudiant lire() throws PFEException {
        // Vérification du format du CIN
        String regex = "\\d{8}";
        if (textField.getText().isEmpty() || !(textField.getText().matches(regex))) {
            throw new PFEException(1);
        }
        // Création d'une instance d'Etudiant
        Etudiant etud = new Etudiant();

        // Récupération et définition des attributs de l'étudiant
        int CIN = Integer.parseInt(textField.getText());
        etud.setCIN(CIN);
        return etud;
    }
	
	public Etudiant lire_modification() throws PFEException {
        // Création d'une instance d'Etudiant
		String regex = "\\d{8}";
        if (textField.getText().isEmpty() || !(textField.getText().matches(regex))) {
            throw new PFEException(1);
        }
        if (textField_1.getText().isEmpty()  || !(textField_1.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(2);
        if (textField_2.getText().isEmpty()  || !(textField_2.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(3);
        // Vérification du champ email
        if (textField_3.getText().isEmpty() || !(textField_3.getText().matches("^\\w+@gmail\\.com$") || textField_3.getText().matches("^\\w+@yahoo\\.com$") ))
            throw new PFEException(46);
        // Vérification du champ numero de tel 
        if (textField_4.getText().isEmpty() || !(textField_4.getText().matches(regex)))
            throw new PFEException(47);
        
        Etudiant etud = new Etudiant();
        int CIN = Integer.parseInt(textField.getText());
        etud.setCIN(CIN);
        etud.setNom(textField_1.getText());
        etud.setPrenom(textField_2.getText());
        etud.setGroupe((Etudiant.Groupe) groupeComboBox.getSelectedItem());
        etud.setEmail(textField_3.getText());
        etud.setNumTel(textField_4.getText());

        return etud;
    }
    public void afficher()throws PFEException {
    	EtudiantDAO etdao=new EtudiantDAO();
    	toutetud=(ArrayList<Etudiant>) etdao.findAll();
	
        // Créer le modèle de tableau avec les données de la base de données
    	DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        int rowCount=toutetud.size();          
    	model.addColumn("N°");
        model.addColumn("CIN");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Groupe");
        model.addColumn("Email");
        model.addColumn("Téléphone");
        for(int j=0;j<rowCount;j++) {
           	Object[] row = new Object[7];
           	row[0] = j + 1;
           	row[1]=toutetud.get(j).getCIN();
           	row[2]=toutetud.get(j).getNom();
           	row[3]=toutetud.get(j).getPrenom();
           	row[4]=toutetud.get(j).getGroupe();
           	row[5]=toutetud.get(j).getEmail();
           	row[6]=toutetud.get(j).getNumTel();
           	model.addRow(row);
           }
           table.setModel(model);
           table.setRowHeight(25);
         //diminuer la colone du numero
           table.getColumnModel().getColumn(0).setPreferredWidth(30);
           table.getColumnModel().getColumn(0).setMaxWidth(30);
           //diminuer la colone de cin
           table.getColumnModel().getColumn(1).setPreferredWidth(80);
           table.getColumnModel().getColumn(1).setMaxWidth(80);
           //diminuer la colone de grp
           table.getColumnModel().getColumn(4).setPreferredWidth(240);
           table.getColumnModel().getColumn(4).setMaxWidth(240);
           //diminuer la colone de email
           table.getColumnModel().getColumn(5).setPreferredWidth(130);
           table.getColumnModel().getColumn(5).setMaxWidth(130);
           //diminuer la colone de numtel
           table.getColumnModel().getColumn(6).setPreferredWidth(80);
           table.getColumnModel().getColumn(6).setMaxWidth(80);
    }
    public void afficherEtud(Etudiant etud) {
    	
        // Créer le modèle de tableau avec les données de la base de données
        

        Object[] row = new Object[6];
        row[0]=etud.getCIN();
        row[1]=etud.getNom();
        row[2]=etud.getPrenom();
        row[3]=etud.getGroupe();
        row[4]=etud.getEmail();
       	row[5]=etud.getNumTel();
        //pour supprimer tout dans le table avant affichage du etudiant modifier 
        modeltableEtud.setRowCount(0);
        modeltableEtud.addRow(row);
           
        tableEtud.setModel(modeltableEtud);
        tableEtud.setRowHeight(25);
      
    }
    
    // Méthode pour rafraîchir le tableau après l'ajout d'un étudiant
    public void refreshTable() throws PFEException{
    	afficher();
    
	}
    private void clearTextFields() {
        textField.setText(""); 
        textField_1.setText(""); 
        textField_2.setText(""); 
        textField_3.setText(""); 
        textField_4.setText("");
        groupeComboBox.setSelectedIndex(0);
        modeltableEtud.setRowCount(0);
    }
}