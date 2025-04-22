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
import DAO.EncadreurSocieteDAO;
import DAO.EtudiantDAO;
import projetPFE.EncadreurSociete;
import projetPFE.Etudiant;
import projetPFE.PFEException;

public class RechercheEncadreurSociete_N extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private ArrayList<EncadreurSociete> tousLesEncadreurSocietes;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	RechercheEncadreurSociete_N RechercheEncadreurSociete_N = new RechercheEncadreurSociete_N();
                	RechercheEncadreurSociete_N.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public RechercheEncadreurSociete_N() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Chercher Encadreur Societe par nom et prénom");
        setLocationRelativeTo(null);

        ImageIcon icone = new ImageIcon("encadreur_societe.png");
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
        lblNewLabel_1_1.setBounds(0, 10, 40, 40);
        panel_1.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("");
        lblNewLabel_1_1_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_1.setBounds(0, 70, 40, 40);
        panel_1.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("");
        lblNewLabel_1_1_2.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_2.setBounds(0, 130, 40, 40);
        panel_1.add(lblNewLabel_1_1_2);

        JLabel lblChercherEncadreurSociete = new JLabel("chercher et modifier");
        lblChercherEncadreurSociete.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherEncadreurSociete.setBounds(51, 180, 159, 61);
        panel_1.add(lblChercherEncadreurSociete);


        JLabel lblNewLabel_1_1_1_1 = new JLabel("");
        lblNewLabel_1_1_1_1.setBackground(new Color(135, 206, 250));
        lblNewLabel_1_1_1_1.setBounds(0, 190, 40, 40);
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


        JPanel paneldroite = new JPanel();
        paneldroite.setBounds(215, 28, 769, 533);
        contentPane.add(paneldroite);
        paneldroite.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(115, 186, 193));
        panel.setBounds(215, 0, 769, 28);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("espace EncadreurSociete");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(10, 0, 280, 25);
        panel.add(lblNewLabel);

        
        ///////////////////////////////////////////////////
        lblAfficherEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Afficher_EncadreurSociete
                Afficher_EncadreurSociete afficherEncadreurSocieteFrame = new Afficher_EncadreurSociete();
                afficherEncadreurSocieteFrame.setVisible(true);
                RechercheEncadreurSociete_N.this.setVisible(false);
            }
        });
        
        lblAjouterEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_encadreurSociete
                Ajouter_EncadreurSociete ajouterEncadreurSocieteFrame = new Ajouter_EncadreurSociete();
                ajouterEncadreurSocieteFrame.setVisible(true);
                RechercheEncadreurSociete_N.this.setVisible(false);
            }
        });
        
        lblSupprimerEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Supprimer_EncadreurSociete
                Supprimer_EncadreurSociete supprimerEncadreurSocieteFrame = new Supprimer_EncadreurSociete();
                supprimerEncadreurSocieteFrame.setVisible(true);
                RechercheEncadreurSociete_N.this.setVisible(false);
            }
        });
        
        lblChercherEncadreurSociete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Chercher_EncadreurSociete
                Chercher_EncadreurSociete chercherEncadreurSocieteFrame = new Chercher_EncadreurSociete();
                chercherEncadreurSocieteFrame.setVisible(true);
                RechercheEncadreurSociete_N.this.setVisible(false);
            }
        });
        /*
        lblChercherEncadreurSociete_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Chercher_EncadreurSociete
                RechercheEncadreurSociete_N RechercheEncadreurSociete_NFrame = new RechercheEncadreurSociete_N();
                RechercheEncadreurSociete_NFrame.setVisible(true);
            }
        });*/
      //TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil_EncadreurSociete frame = new Acceuil_EncadreurSociete();
				frame.setVisible(true);
            }
        });
        
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////
  //TODO      
        
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 263, 719, 259);
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField.setBounds(242, 67, 285, 35);
        paneldroite.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Nom : ");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(79, 68, 163, 35);
        paneldroite.add(lblNewLabel_2);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_1.setColumns(10);
        textField_1.setBounds(242, 113, 285, 35);
        paneldroite.add(textField_1);
        
        JLabel lblNewLabel_2_1 = new JLabel("Prenom : ");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2_1.setBounds(79, 114, 163, 35);
        paneldroite.add(lblNewLabel_2_1);
        

        
        
        JButton btnNewButton = new JButton("chercher");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(62, 133, 140));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(242, 184, 285, 35);
        paneldroite.add(btnNewButton);
 


        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					
						EncadreurSociete en;
		            	if(textField_1.getText().isEmpty()) {
		            		en = lireNom();
		            		afficherNom(en);
		            	}
		            	else if(textField.getText().isEmpty()) {
		            		en = lirePrenom();
		            		afficherPrenom(en);
		            	}
		            	else {
		            		en = lireNomPrenom();
		            		afficherNomPrenom(en);
		            	}
		            	
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );


    }

    // Méthode pour lire les données de l'EncadreurSociete à partir des champs de saisie
    public EncadreurSociete lireNom() throws PFEException {
        // Vérification du champ nom
        if (textField.getText().isEmpty()  || !(textField.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(2);

        // Création d'une instance d'Etudiant
        EncadreurSociete e = new EncadreurSociete();

        // Récupération et définition des attributs de l'étudiant
     
        e.setNom(textField.getText());
        return e;
    }
    
    public EncadreurSociete lirePrenom() throws PFEException {
        // Vérification du champ prenom
        if (textField_1.getText().isEmpty()  || !(textField_1.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(3);

        // Création d'une instance d'Etudiant
        EncadreurSociete e = new EncadreurSociete();

        // Récupération et définition des attributs de l'étudiant
     
        e.setPrenom(textField_1.getText());
        return e;
    }
    
    public EncadreurSociete lireNomPrenom() throws PFEException {
    	// Vérification du champ nom
        if (textField.getText().isEmpty()  || !(textField.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(2);
    	// Vérification du champ prenom
        if (textField_1.getText().isEmpty()  || !(textField_1.getText().matches("[a-zA-Z ]+")))
            throw new PFEException(3);

        // Création d'une instance d'Etudiant
        EncadreurSociete etud = new EncadreurSociete();

        // Récupération et définition des attributs de l'étudiant
        etud.setNom(textField.getText());
        etud.setPrenom(textField_1.getText());
        return etud;
    }


    public void afficherNom(EncadreurSociete e) throws PFEException{
    	EncadreurSocieteDAO etdao=new EncadreurSocieteDAO();
    	tousLesEncadreurSocietes=(ArrayList<EncadreurSociete>) etdao.findByNom(e);
	
        // Créer le modèle de tableau avec les données de la base de données
    	DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        int rowCount=tousLesEncadreurSocietes.size();  
    	if (rowCount == 0) {
    		model.addColumn("Information");
    		Object[] noDataMessage = {"Aucun encadreur soiete trouvé"};
            model.addRow(noDataMessage);
            
            table.setModel(model);
        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.setRowHeight(236);
            
    	}
    	else {
    		model.addColumn("N°");
            model.addColumn("CIN");
            model.addColumn("Nom");
            model.addColumn("Prenom");
            for(int j=0;j<rowCount;j++) {
               	Object[] row = new Object[4];
               	row[0] = j + 1;
               	row[1]=tousLesEncadreurSocietes.get(j).getCIN();
               	row[2]=tousLesEncadreurSocietes.get(j).getNom();
               	row[3]=tousLesEncadreurSocietes.get(j).getPrenom();
               	model.addRow(row);
               }
               table.setModel(model);
               table.setRowHeight(25);
               table.getColumnModel().getColumn(0).setPreferredWidth(30);
               table.getColumnModel().getColumn(0).setMaxWidth(30);
               table.setRowHeight(20);
        }
    }
    
    public void afficherPrenom(EncadreurSociete e)throws PFEException {
    	EncadreurSocieteDAO etdao=new EncadreurSocieteDAO();
    	tousLesEncadreurSocietes=(ArrayList<EncadreurSociete>) etdao.findByPrenom(e);
	
        // Créer le modèle de tableau avec les données de la base de données
    	 DefaultTableModel model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 // Rendre toutes les cellules non modifiables
                 return false;
             }
         };
         int rowCount=tousLesEncadreurSocietes.size();  
    	if (rowCount == 0) {
    		model.addColumn("Information");
    		Object[] noDataMessage = {"Aucun encadreur soiete trouvé"};
            model.addRow(noDataMessage);
            
            table.setModel(model);
        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.setRowHeight(236);
            
    	}
    	else {
    		model.addColumn("N°");
            model.addColumn("CIN");
            model.addColumn("Nom");
            model.addColumn("Prenom");
            for(int j=0;j<rowCount;j++) {
               	Object[] row = new Object[4];
               	row[0] = j + 1;
               	row[1]=tousLesEncadreurSocietes.get(j).getCIN();
               	row[2]=tousLesEncadreurSocietes.get(j).getNom();
               	row[3]=tousLesEncadreurSocietes.get(j).getPrenom();
               	model.addRow(row);
               }
               table.setModel(model);
               table.getColumnModel().getColumn(0).setPreferredWidth(30);
               table.getColumnModel().getColumn(0).setMaxWidth(30);
               table.setRowHeight(20);
        }
    }
    
    
    public void afficherNomPrenom(EncadreurSociete e) throws PFEException{
    	EncadreurSocieteDAO etdao=new EncadreurSocieteDAO();
    	tousLesEncadreurSocietes=(ArrayList<EncadreurSociete>) etdao.findByNomPrenom(e);
	
        // Créer le modèle de tableau avec les données de la base de données
    	 DefaultTableModel model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 // Rendre toutes les cellules non modifiables
                 return false;
             }
         };
         int rowCount=tousLesEncadreurSocietes.size();  
    	if (rowCount == 0) {
    		model.addColumn("Information");
    		Object[] noDataMessage = {"Aucun encadreur soiete trouvé"};
            model.addRow(noDataMessage);
            
            table.setModel(model);
        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.setRowHeight(236);
            
    	}
    	else {
    		model.addColumn("N°");
            model.addColumn("CIN");
            model.addColumn("Nom");
            model.addColumn("Prenom");
            for(int j=0;j<rowCount;j++) {
               	Object[] row = new Object[4];
               	row[0] = j + 1;
               	row[1]=tousLesEncadreurSocietes.get(j).getCIN();
               	row[2]=tousLesEncadreurSocietes.get(j).getNom();
               	row[3]=tousLesEncadreurSocietes.get(j).getPrenom();
               	model.addRow(row);
               }
               table.setModel(model);
               table.getColumnModel().getColumn(0).setPreferredWidth(30);
               table.getColumnModel().getColumn(0).setMaxWidth(30);
               table.setRowHeight(20);
        }
    }

}