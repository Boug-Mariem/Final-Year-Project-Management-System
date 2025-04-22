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
import javax.swing.table.DefaultTableModel;
import DAO.EnseignantDAO;
import projetPFE.Enseignant;
import projetPFE.PFEException;

public class Ajouter_enseignant extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private ArrayList<Enseignant> tousLesEnseignants;
    private JTextField textField_3;
    private JTextField textField_4;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ajouter_enseignant ajouterEnseignant = new Ajouter_enseignant();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Ajouter_enseignant() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Ajouter enseignant");
        setLocationRelativeTo(null);
        
        ImageIcon icone = new ImageIcon("professor.png");
        setIconImage(icone.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelgauche = new JPanel();
		panelgauche.setLayout(null);
		panelgauche.setBackground(new Color(62, 133, 140));
		panelgauche.setBounds(0, 0, 224, 561);
		contentPane.add(panelgauche);
		
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1.setBounds(45, 0, 130, 130);
        panelgauche.add(lblNewLabel_1);

        ImageIcon icon = new ImageIcon("professor.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        lblNewLabel_1.setIcon(newIcon);

        JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 146, 204, 359);
		panelgauche.add(panel_1);
		
		JLabel lblAfficherenseignant = new JLabel("afficher enseignant");
		lblAfficherenseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAfficherenseignant.setBounds(47, 0, 143, 61);
		panel_1.add(lblAfficherenseignant);
		
		JLabel lblAjouterenseignant = new JLabel("ajouter enseignant");
		lblAjouterenseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAjouterenseignant.setBounds(47, 60, 143, 61);
		panel_1.add(lblAjouterenseignant);
		
		JLabel lblSupprimerenseignant = new JLabel("supprimer enseignant");
		lblSupprimerenseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupprimerenseignant.setBounds(47, 120, 162, 61);
		panel_1.add(lblSupprimerenseignant);
		
		
		JLabel lblChercherenseignant = new JLabel("chercher et modifier");
		lblChercherenseignant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChercherenseignant.setBounds(47, 180, 159, 61);
		panel_1.add(lblChercherenseignant);
		

	    JLabel lblChercherenseignant_N = new JLabel("chercher par N/P");
	    lblChercherenseignant_N.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblChercherenseignant_N.setBounds(47, 240, 159, 61);
	    panel_1.add(lblChercherenseignant_N);
	     
	    JLabel lblEmploi = new JLabel("Emploi enseignant");
	    lblEmploi.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblEmploi.setBounds(47, 300, 159, 61);
	    panel_1.add(lblEmploi);


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

		
		//////////////// Créez une instance de ImageIcon en chargeant l'image depuis un fichier
		ImageIcon imageIcon6 = new ImageIcon("calendrier.png"); // Remplacez "icone6.png" par le chemin de votre image

		// Redimensionnez l'icône pour qu'elle ait la même taille que le JLabel
		Image image6 = imageIcon6.getImage(); // Obtenez l'image de l'icône
		Image nouvelleImage6 = image6.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // Redimensionnez l'image
		imageIcon6 = new ImageIcon(nouvelleImage6); // Créez une nouvelle ImageIcon avec l'image redimensionnée

		// Créez le JLabel avec l'image chargée et redimensionnée
		JLabel icon6 = new JLabel(imageIcon6);
		icon6.setBounds(3, 310, 40, 40);
		panel_1.add(icon6);



		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(115, 186, 193));
		panel.setBounds(223, 0, 761, 28);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("espace enseignant");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 0, 280, 25);
		panel.add(lblNewLabel);
		
		JPanel paneldroite = new JPanel();
		paneldroite.setLayout(null);
		paneldroite.setBounds(223, 28, 769, 533);
		contentPane.add(paneldroite);
		
        
        ///////////////////////////////////////////////////
        lblAfficherenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Afficher_enseignant afficherenseignantFrame = new Afficher_enseignant();
                afficherenseignantFrame.setVisible(true);
                Ajouter_enseignant.this.setVisible(false);
            }
        });
        /*
        lblAjouterenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Ajouter_enseignant ajouterenseignantFrame = new Ajouter_enseignant();
                ajouterenseignantFrame.setVisible(true);
            }
        });*/
        
        lblSupprimerenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Supprimer_enseignant supprimerenseignantFrame = new Supprimer_enseignant();
                supprimerenseignantFrame.setVisible(true);
                Ajouter_enseignant.this.setVisible(false);
            }
        });
        
        lblChercherenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Chercher_enseignant chercherenseignantFrame = new Chercher_enseignant();
                chercherenseignantFrame.setVisible(true);
                Ajouter_enseignant.this.setVisible(false);
            }
        });
        //recherche par nom et prenom
        lblChercherenseignant_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                RechercheEnseignant_N RechercheEnseignant_NFrame = new RechercheEnseignant_N();
                RechercheEnseignant_NFrame.setVisible(true);
                Ajouter_enseignant.this.setVisible(false);
            }
        });
        // emploi
        lblEmploi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Emploi_Enseignant Emploi_EnseignantFrame = new Emploi_Enseignant();
                Emploi_EnseignantFrame.setVisible(true);
                Ajouter_enseignant.this.setVisible(false);
            }
        });
//TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil_Enseignant frame = new Acceuil_Enseignant();
				frame.setVisible(true);
            }
        });
        
        //////////////////////////////////////////////////////////////////////    
        

        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 312, 749, 210);
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField.setBounds(242, 67, 285, 35);
        paneldroite.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("CIN : ");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(43, 68, 163, 35);
        paneldroite.add(lblNewLabel_2);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_1.setColumns(10);
        textField_1.setBounds(242, 113, 285, 35);
        paneldroite.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_2.setColumns(10);
        textField_2.setBounds(242, 159, 285, 35);
        paneldroite.add(textField_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Nom : ");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_1.setBounds(53, 114, 163, 35);
        paneldroite.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Prenom : ");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_2.setBounds(69, 160, 173, 35);
        paneldroite.add(lblNewLabel_2_2);
        

        
        
        JButton btnNewButton = new JButton("ajouter");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(62, 133, 140));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(242, 257, 285, 35);
        paneldroite.add(btnNewButton);
        
        JLabel lblNewLabel_2_3 = new JLabel("Email : ");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_3.setBounds(10, 212, 78, 35);
        paneldroite.add(lblNewLabel_2_3);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_3.setColumns(10);
        textField_3.setBounds(98, 211, 285, 35);
        paneldroite.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_4.setColumns(10);
        textField_4.setBounds(508, 211, 233, 35);
        paneldroite.add(textField_4);
        
        JLabel lblNewLabel_2_3_1 = new JLabel("Téléphone : ");
        lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_3_1.setBounds(393, 211, 119, 35);
        paneldroite.add(lblNewLabel_2_3_1);
 


        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					EnseignantDAO DAOens=new EnseignantDAO();
	            	Enseignant ens;
					ens = lire();
					DAOens.create(ens);
                    refreshTable();
                    clearTextFields();
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );

        // Initialiser la liste d'enseignants
        tousLesEnseignants = new ArrayList<>();

        // Afficher les enseignants dans le tableau
        try {
			afficherEnseignants();
		} catch (PFEException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
		}

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Méthode pour lire les données de l'enseignant à partir des champs de saisie
    public Enseignant lire() throws PFEException {
        // Vérification du format du CIN
        String regex = "\\d{8}";
        if (textField.getText().isEmpty()  || !(textField.getText().matches(regex))) {
            throw new PFEException(1);
        }

        // Vérification du champ nom
        if (textField_1.getText().isEmpty() || !(textField_1.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(2);

        // Vérification du champ prénom
        if (textField_2.getText().isEmpty() || !(textField_2.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(3);
        
        // Vérification du champ email
        if (textField_3.getText().isEmpty() || !(textField_3.getText().matches("^\\w+@gmail\\.com$") || textField_3.getText().matches("^\\w+@yahoo\\.com$") ))
            throw new PFEException(46);
        // Vérification du champ numero de tel 
        if (textField_4.getText().isEmpty() || !(textField_4.getText().matches(regex)))
            throw new PFEException(47);

        
        // Création d'une instance d'Etudiant
        Enseignant ens = new Enseignant();

        // Récupération et définition des attributs de l'étudiant
        int CIN = Integer.parseInt(textField.getText());
        ens.setCIN(CIN);
        ens.setNom(textField_1.getText());
        ens.setPrenom(textField_2.getText());
        ens.setEmail(textField_3.getText());
        ens.setNumTel(textField_4.getText());

        return ens;
    }
    // Méthode pour afficher les enseignants dans le tableau
    public void afficherEnseignants() throws PFEException{
    	EnseignantDAO ensDao = new EnseignantDAO();
        tousLesEnseignants = (ArrayList<Enseignant>) ensDao.findAll();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        int rowCount=tousLesEnseignants.size();
        model.addColumn("N°");
        model.addColumn("CIN");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Email");
        model.addColumn("Téléphone");

        for(int j=0;j<rowCount;j++) {
           	Object[] row = new Object[6];
           	row[0] = j + 1;
           	row[1]=tousLesEnseignants.get(j).getCIN();
           	row[2]=tousLesEnseignants.get(j).getNom();
           	row[3]=tousLesEnseignants.get(j).getPrenom();
           	row[4]=tousLesEnseignants.get(j).getEmail();
           	row[5]=tousLesEnseignants.get(j).getNumTel();
           	
           	model.addRow(row);
           }
           table.setModel(model);
           table.setRowHeight(25);
           table.getColumnModel().getColumn(0).setPreferredWidth(30);
           table.getColumnModel().getColumn(0).setMaxWidth(30);
           //cin
           table.getColumnModel().getColumn(1).setPreferredWidth(80);
           table.getColumnModel().getColumn(1).setMaxWidth(80);
           
           //email
           table.getColumnModel().getColumn(4).setPreferredWidth(200);
           table.getColumnModel().getColumn(4).setMaxWidth(200);
    }

    // Méthode pour rafraîchir le tableau après l'ajout d'un enseignant
    public void refreshTable() throws PFEException{
        afficherEnseignants();
    }
    private void clearTextFields() {
        textField.setText(""); 
        textField_1.setText(""); 
        textField_2.setText("");
        textField_3.setText(""); 
        textField_4.setText(""); 
    }
}