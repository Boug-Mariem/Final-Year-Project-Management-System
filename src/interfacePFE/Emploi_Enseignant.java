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
import DAO.EnseignantDAO;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.PFEException;
import projetPFE.Soutenance;

public class Emploi_Enseignant extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private ArrayList<Soutenance> EnseignantSoutenance;
    private ArrayList<Enseignant> ToutEnseigant;
    private JComboBox<Enseignant> comboBox;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Emploi_Enseignant Emploi_Enseignant = new Emploi_Enseignant();
                	Emploi_Enseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Emploi_Enseignant() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Emploi enseignant");
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
		
		
        JLabel lblNewLabel_2 = new JLabel("Enseignant :  ");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setBounds(79, 67, 143, 28);
        paneldroite.add(lblNewLabel_2);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(33, 205, 707, 293);
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);

        
        JButton btnemploi = new JButton("emploi");
        btnemploi.setForeground(Color.WHITE);
        btnemploi.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnemploi.setBackground(new Color(62, 133, 140));
        btnemploi.setBounds(244, 129, 285, 35);
        paneldroite.add(btnemploi);
        
        //reglage du combobox
        EnseignantDAO rechens=new EnseignantDAO();
        try {
			ToutEnseigant=(ArrayList<Enseignant>) rechens.findAll();
		} catch (PFEException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
		}
        comboBox = new JComboBox<>(ToutEnseigant.toArray(new Enseignant[0]));
        comboBox.setRenderer(new EnseignantComboBoxRenderer());
        comboBox.setBounds(244, 60, 285, 40);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 19));
        paneldroite.add(comboBox);
        
        btnemploi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					Enseignant ens=lire();
					afficher(ens);
                    
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );
        
        ///////////////////////////////////////////////////
        lblAfficherenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Afficher_enseignant afficherenseignantFrame = new Afficher_enseignant();
                afficherenseignantFrame.setVisible(true);
                Emploi_Enseignant.this.setVisible(false);
            }
        });
        
        lblAjouterenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Ajouter_enseignant ajouterenseignantFrame = new Ajouter_enseignant();
                ajouterenseignantFrame.setVisible(true);
                Emploi_Enseignant.this.setVisible(false);
            }
        });
        
        lblSupprimerenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Supprimer_enseignant supprimerenseignantFrame = new Supprimer_enseignant();
                supprimerenseignantFrame.setVisible(true);
                Emploi_Enseignant.this.setVisible(false);
            }
        });
        
        lblChercherenseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Chercher_enseignant chercherenseignantFrame = new Chercher_enseignant();
                chercherenseignantFrame.setVisible(true);
                Emploi_Enseignant.this.setVisible(false);
            }
        });
        //recherche par nom et prenom
        lblChercherenseignant_N.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                RechercheEnseignant_N RechercheEnseignant_NFrame = new RechercheEnseignant_N();
                RechercheEnseignant_NFrame.setVisible(true);
                Emploi_Enseignant.this.setVisible(false);
            }
        });
        // emploi
        /*lblEmploi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
                Emploi_Enseignant Emploi_EnseignantFrame = new Emploi_Enseignant();
                Emploi_EnseignantFrame.setVisible(true);
            }
        });*/
//TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil_Enseignant frame = new Acceuil_Enseignant();
				frame.setVisible(true);
            }
        });
        
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////
        // Initialiser la liste d'enseignants        

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // Méthode pour lire les données de l'enseignant à partir des champs de saisie
    public Enseignant lire() throws PFEException {
    	// Création d'une instance d'Etudiant
    	Enseignant ens = new Enseignant();
    	Enseignant enseignantSelectionne = (Enseignant) comboBox.getSelectedItem();
        if (enseignantSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = enseignantSelectionne.getCIN();
            ens.setCIN(CIN);
        }
        else {
        	throw new PFEException(9);
        }

        return ens;
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
    // Méthode pour afficher les enseignants dans le tableau
    public void afficher(Enseignant ens) throws PFEException{
        EnseignantDAO ensdao=new EnseignantDAO();
        EnseignantSoutenance=(ArrayList<Soutenance>) ensdao.emploiEns(ens);
        
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };  
        int rowCount=EnseignantSoutenance.size();
    	if (rowCount == 0) {
    		model.addColumn("Information");
    		Object[] noDataMessage = {"Aucun emploi trouvé"};
            model.addRow(noDataMessage);
            
            table.setModel(model);
        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.setRowHeight(270);
            
    	}
    	else {
    		model.addColumn("N°");
            model.addColumn("ID");
            String colprojet=String.format("<html>%s<br>%s</html>","Titre","projet");
            model.addColumn(colprojet);
            String coletud1=String.format("<html>%s<br>%s</html>","1ere","Etudiant");
            model.addColumn(coletud1);
            String coletud2=String.format("<html>%s<br>%s</html>","2eme","Etudiant");
            model.addColumn(coletud2);
            model.addColumn("Date");
            model.addColumn("Heure");
            model.addColumn("Locale");
            model.addColumn("Encadreur");
            model.addColumn("President");
            model.addColumn("Rapporteur");
            model.addColumn("Examinateur");
            
            for(int j=0;j<rowCount;j++) {
               	Object[] row = new Object[12];
               	row[0] = j + 1;
               	row[1]=EnseignantSoutenance.get(j).getId();
               	row[2]=EnseignantSoutenance.get(j).getProjet().getTitre();
               	Etudiant firstEtudiant = EnseignantSoutenance.get(j).getProjet().getFirstEtudiant();
               	row[3]=String.format("<html>%s<br>%s</html>",firstEtudiant.getNom(), firstEtudiant.getPrenom());
     
               	Etudiant secondetudiant = EnseignantSoutenance.get(j).getProjet().getSecondEtudiant();
               	if(secondetudiant!=null)
               		row[4]=String.format("<html>%s<br>%s</html>",secondetudiant.getNom(), secondetudiant.getPrenom());
               	else
               		row[4]="********";
               		
               	row[5]=EnseignantSoutenance.get(j).getDateSoutenance();
               	row[6]=EnseignantSoutenance.get(j).getHeureSoutenance();
               	row[7]=EnseignantSoutenance.get(j).getlocalsout();
               	Enseignant encadreur = EnseignantSoutenance.get(j).getProjet().getEncadreur();
               	row[8]=String.format("<html>%s<br>%s</html>",encadreur.getNom(), encadreur.getPrenom());
               	Enseignant president = EnseignantSoutenance.get(j).getJurys().getPresident();
               	row[9]=String.format("<html>%s<br>%s</html>",president.getNom(), president.getPrenom());
               	Enseignant rapporteur = EnseignantSoutenance.get(j).getJurys().getRapporteur();
               	row[10]=String.format("<html>%s<br>%s</html>",rapporteur.getNom(), rapporteur.getPrenom());
               	Enseignant examinateur = EnseignantSoutenance.get(j).getJurys().getExaminateur();
               	row[11]=String.format("<html>%s<br>%s</html>",examinateur.getNom(), examinateur.getPrenom());
               	model.addRow(row);
               }
               table.setModel(model);
               //diminuer la taille du colone numero
               table.getColumnModel().getColumn(0).setPreferredWidth(25);
               table.getColumnModel().getColumn(0).setMaxWidth(25);
               //diminuer la taille du colone id
               table.getColumnModel().getColumn(1).setPreferredWidth(25);
               table.getColumnModel().getColumn(1).setMaxWidth(25);
              //diminuer la taille du colone heure
               table.getColumnModel().getColumn(6).setPreferredWidth(55);
               table.getColumnModel().getColumn(6).setMaxWidth(55);
               //diminuer la taille du colone locale
               table.getColumnModel().getColumn(7).setPreferredWidth(45);
               table.getColumnModel().getColumn(7).setMaxWidth(45);
               //agrandir la taille du header du table
               table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
               
               table.setRowHeight(25);
        }
    	
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


