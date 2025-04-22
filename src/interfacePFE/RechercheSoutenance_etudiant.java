package interfacePFE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import DAO.EtudiantDAO;
import DAO.SoutenanceDAO;
import projetPFE.EncadreurSociete;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.PFEException;
import projetPFE.Soutenance;


public class RechercheSoutenance_etudiant extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
        
    private JTextField textField;
    private JTable table;
    private JTable tableEtud;
    private DefaultTableModel modeltableEtud;



 /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RechercheSoutenance_etudiant ajouterEnseignant = new RechercheSoutenance_etudiant();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public RechercheSoutenance_etudiant() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Chercher soutenance par etudiant ");
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

//////////////////////
        
        JLabel lblChercherParDate = new JLabel("chercher par date");
        lblChercherParDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherParDate.setBounds(48, 312, 159, 52);
        panel_1.add(lblChercherParDate);
        
        JLabel lbl_Chercher_par_salle = new JLabel("chercher par salle");
        lbl_Chercher_par_salle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_Chercher_par_salle.setBounds(48, 260, 159, 52);
        panel_1.add(lbl_Chercher_par_salle);
    	
        ImageIcon originalIcon3 = new ImageIcon("graduation.png");
        ImageIcon newIcon3 = resizeIcon(originalIcon3, 350, 350);
    	
    	
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
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField.setColumns(10);
        textField.setBounds(242, 32, 285, 35);
        paneldroite.add(textField);
        
        JLabel lblNewLabel_2 = new JLabel("CIN :  ");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblNewLabel_2.setBounds(89, 33, 143, 35);
        paneldroite.add(lblNewLabel_2);
        
        JButton btnChercher = new JButton("chercher");
        btnChercher.setForeground(Color.WHITE);
        btnChercher.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnChercher.setBackground(new Color(62, 133, 140));
        btnChercher.setBounds(242, 89, 285, 35);
        paneldroite.add(btnChercher);
        btnChercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					EtudiantDAO DAOetud=new EtudiantDAO();
	            	Etudiant etud;
					etud = lire_etudiant();
					etud=DAOetud.find(etud);
					afficherEtud(etud);

				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 315, 707, 188);
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        
        JButton btnAfficher = new JButton("afficher soutenance");
        btnAfficher.setForeground(Color.WHITE);
        btnAfficher.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAfficher.setBackground(new Color(62, 133, 140));
        btnAfficher.setBounds(242, 264, 285, 35);
        paneldroite.add(btnAfficher);
        
        btnAfficher.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		try {
         			SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
         			List<Soutenance> soutenances;
         			soutenances = lire();
	            	affichersout(soutenances);
    				} catch (PFEException e1) {
    					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    				}
         	}
         });
        
        
        //pour affichege d l'etudiant trouve
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(31, 146, 707, 48);
        paneldroite.add(scrollPane_1);
        
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
        tableEtud.setModel(modeltableEtud);
      //diminuer la colone de cin
        tableEtud.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableEtud.getColumnModel().getColumn(0).setMaxWidth(100);
        //diminuer la colone de nom
        tableEtud.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableEtud.getColumnModel().getColumn(1).setMaxWidth(120);
        //diminuer la colone de prenom
        tableEtud.getColumnModel().getColumn(2).setPreferredWidth(300);
        tableEtud.getColumnModel().getColumn(2).setMaxWidth(120);
        tableEtud.setRowHeight(25);


        
        ///////////////////////////////////////////////////
     // Action pour "afficher soutenances"
        lblAfficherer_soutenance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Afficher_Soutenance afficherSoutenancesFrame = new Afficher_Soutenance();
                afficherSoutenancesFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });

        // Action pour "creer soutenance"
        lblCreer_soutenance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Creation_Soutenance creerSoutenanceFrame = new Creation_Soutenance();
                creerSoutenanceFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });

        // Action pour "supprimer soutenance"
        lblSupprimer_soutenance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Supprimer_Soutenance supprimerSoutenanceFrame = new Supprimer_Soutenance();
                supprimerSoutenanceFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });

        // Action pour "chercher et modifier"
        lblChercher_et_modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	Chercher_Soutenance chercherEtModifierFrame = new Chercher_Soutenance();
                chercherEtModifierFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });

        // Action pour "chercher par étudiant"
 /*       lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	RechercheSoutenance_etudiant chercherParEtudiantFrame = new RechercheSoutenance_etudiant();
                chercherParEtudiantFrame.setVisible(true);
            }
        });
        */
        // Action pour "chercher par salle"
        lbl_Chercher_par_salle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	RechercheSoutenance_salle chercherParEtudiantFrame = new RechercheSoutenance_salle();
                chercherParEtudiantFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });
        
        
        // Action pour "chercher par date"
        lblChercherParDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	RechercheSoutenance_date chercherPardateFrame = new RechercheSoutenance_date();
            	chercherPardateFrame.setVisible(true);
            	RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });

        // Action pour "attribuer Note"
        lblAttribuerNote.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Note attribuerNoteFrame = new Note();
                attribuerNoteFrame.setVisible(true);
                RechercheSoutenance_etudiant.this.setVisible(false);
            }
        });
//TODO  ////////////////////////////////
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Ouvrir la nouvelle interface ici
            	Acceuil_Soutenance frame = new Acceuil_Soutenance();
				frame.setVisible(true);
            }
        });



}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Etudiant lire_etudiant() throws PFEException {
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
	
	
	
	public List<Soutenance> lire() throws PFEException {
	    // Vérification du format du CIN
	    String regex = "\\d{8}";
	    if (textField.getText().isEmpty() || !(textField.getText().matches(regex))) {
	        throw new PFEException(1);
	    }
	    
        // Récupérer le CIN de l'étudiant
        int CIN = Integer.parseInt(textField.getText());

        // Créer une instance d'Étudiant
        Etudiant etud = new Etudiant();
        etud.setCIN(CIN);

        // Utiliser le DAO pour rechercher la soutenance par identifiant de l'étudiant
        SoutenanceDAO soutenanceDAO = new SoutenanceDAO();
        List<Soutenance> sout = soutenanceDAO.findAllByStudent(etud);

	    return sout;
	}

    public void afficherEtud(Etudiant etud) {
    	
        // Créer le modèle de tableau avec les données de la base de données
        

        Object[] row = new Object[4];
        row[0]=etud.getCIN();
        row[1]=etud.getNom();
        row[2]=etud.getPrenom();
        row[3]=etud.getGroupe();
        //pour supprimer tout dans le table avant affichage du etudiant modifier 
        modeltableEtud.setRowCount(0);
        modeltableEtud.addRow(row);
           
        tableEtud.setModel(modeltableEtud);
        tableEtud.setRowHeight(25);
       
      
    }

    
    public void affichersout(List<Soutenance> listeSoutenances) {
    	DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
    	if(listeSoutenances.size()==0) {
        	model.addColumn("Information");
    		Object[] noDataMessage = {"Aucune soutenance trouvée"};
            model.addRow(noDataMessage);
            
            table.setModel(model);
        	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.setRowHeight(164);
        }
        else {
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
            table.setModel(model);
            //////////id  
    		 table.getColumnModel().getColumn(0).setPreferredWidth(30);
    		 table.getColumnModel().getColumn(0).setMaxWidth(30);
    		//diminuer la taille du colone date
             table.getColumnModel().getColumn(3).setPreferredWidth(70);
             table.getColumnModel().getColumn(3).setMaxWidth(70);
    		//diminuer la taille du colone heure
             table.getColumnModel().getColumn(4).setPreferredWidth(50);
             table.getColumnModel().getColumn(4).setMaxWidth(50);
             //diminuer la taille du colone locale
             table.getColumnModel().getColumn(5).setPreferredWidth(45);
             table.getColumnModel().getColumn(5).setMaxWidth(45);
             //diminuer la taille du colone validation
             table.getColumnModel().getColumn(9).setPreferredWidth(58);
             table.getColumnModel().getColumn(9).setMaxWidth(58);
             //diminuer la taille du colone note
             table.getColumnModel().getColumn(10).setPreferredWidth(40);
             table.getColumnModel().getColumn(10).setMaxWidth(40);
           //agrandir la taille du header du table
             table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));

    	 for (Soutenance soutenance : listeSoutenances) {
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

        Object[] row = new Object[11]; // Nombre de colonnes dans le modèle de tableau
        row[0] = soutenance.getId();
        row[1] = titreProjet;
        if(soutenance.getProjet().getSecondEtudiant()!=null) {
        	String lesEtudinats=String.format("<html>%s<br>%s<br>%s<br>%s<br>%s<br>%s</html>","1/",Etud1.getNom(),Etud1.getPrenom(),"2/",Etud2.getNom(),Etud2.getPrenom());
        	row[2]=lesEtudinats;
        }
        else {
        	String lesEtudinats=String.format("<html>%s<br>%s</html>",Etud1.getNom(),Etud1.getPrenom());
        	row[2]=lesEtudinats;
        }
        row[3] = soutenance.getDateSoutenance();
        row[4] = soutenance.getHeureSoutenance().withSecond(0).withNano(0);
        row[5] = soutenance.getlocalsout();
    	String Stringencadreur=String.format("<html>%s<br>%s</html>",encadreur.getNom(),encadreur.getPrenom());
    	row[6] = Stringencadreur;
    	String StringencadreurSoc=String.format("<html>%s<br>%s</html>",encadreurSoc.getNom(),encadreurSoc.getPrenom());
    	row[7] = StringencadreurSoc;
    	String Stringjurys=String.format("<html>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br>%s<br></html>","(P):",president.getNom(),president.getPrenom(),"(R):",rapporteur.getNom(),rapporteur.getPrenom(),"(E):",examinateur.getNom(),examinateur.getPrenom());
    	row[8] = Stringjurys;
    	if(soutenance.getNote()>=10) {
    		row[9] = "Validé"; // Remplacez par la valeur de validation appropriée
    		row[10] = soutenance.getNote();
    	}
    	else if(soutenance.getNote()<10 && soutenance.getNote()>=0) {
        	String nonValide=String.format("<html>%s<br>%s</html>","Non","Validé");
    		row[9] = nonValide; // Remplacez par la valeur de validation appropriée
    		row[10] = soutenance.getNote();
    	}
    	else {
    		row[9] = "***"; // Remplacez par la valeur de validation appropriée
    		row[10] = "***";
    	}
    	model.setRowCount(0);
        model.addRow(row);
    	 }
     
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