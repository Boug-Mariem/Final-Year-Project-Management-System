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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import DAO.EnseignantDAO;
import DAO.JurysDAO;
import DAO.ProjetDAO;
import DAO.SoutenanceDAO;
import projetPFE.EncadreurSociete;
import projetPFE.Enseignant;
import projetPFE.Etudiant;
import projetPFE.Jurys;
import projetPFE.PFEException;
import projetPFE.Projet;
import projetPFE.Soutenance;

public class Chercher_Soutenance extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    private ArrayList<Soutenance> tous_les_soutenance;
    private ArrayList<Enseignant> ToutEnseigant;
    private JTextField textField_titreprojet;
    private JCalendar jCalendar; 
    private JSpinner spinnerHeure;
    private DefaultTableModel model ;    
    
    private JComboBox<Enseignant> comboBoxPresident;
    private JComboBox<Enseignant> comboBoxRapporteur;
    private JComboBox<Enseignant> comboBoxExaminateur;
    private JComboBox<Soutenance.Local> comboBoxLocaux;


    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Chercher_Soutenance ajouterEnseignant = new Chercher_Soutenance();
                    ajouterEnseignant.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    
    public Chercher_Soutenance() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Modifier soutenance ");
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
        
        
        JLabel lblChercherParDate = new JLabel("chercher par date");
        lblChercherParDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChercherParDate.setBounds(48, 312, 159, 52);
        panel_1.add(lblChercherParDate);
        
        JLabel lbl_Chercher_par_salle = new JLabel("chercher par salle");
        lbl_Chercher_par_salle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_Chercher_par_salle.setBounds(48, 260, 159, 52);
        panel_1.add(lbl_Chercher_par_salle);
        

        
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
        
        textField_titreprojet = new JTextField();
        textField_titreprojet.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField_titreprojet.setColumns(10);
        textField_titreprojet.setBounds(236, 11, 297, 35);
        paneldroite.add(textField_titreprojet);
        
        JLabel lbl_titreprojet = new JLabel("Titre Projet : ");
        lbl_titreprojet.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_titreprojet.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_titreprojet.setBounds(57, 12, 177, 35);
        paneldroite.add(lbl_titreprojet);
        
        JLabel lbl_date = new JLabel("Date  : ");
        lbl_date.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_date.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_date.setBounds(75, 90, 105, 35);
        paneldroite.add(lbl_date);
        
        JLabel lbl_jurys = new JLabel("Jurys : ");
        lbl_jurys.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_jurys.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_jurys.setBounds(0, 272, 76, 35);
        paneldroite.add(lbl_jurys);
        

        jCalendar = new JCalendar();
        jCalendar.getDayChooser().setBackground(SystemColor.inactiveCaption);
        //jCalendar.getDayChooser().getDayPanel().setBounds(16, 0, 184, 130);
        jCalendar.getDayChooser().getDayPanel().setBounds(0, 0, 200, 130);
        // Ajustez 280, 80, 200, et 150 selon vos besoins
        jCalendar.setBounds(190, 95, 200, 150);//initale 190, 111, 200, 150

        // Ajoutez le JCalendar à un conteneur (par exemple, paneldroite).
        paneldroite.add(jCalendar);

        jCalendar.getDayChooser().setLayout(null);
        
        
        JLabel lbl_heure = new JLabel("Heure : ");
        lbl_heure.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_heure.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_heure.setBounds(449, 145, 121, 35);
        paneldroite.add(lbl_heure);
        

        // Ajoutez ici le sélecteur d'heure
        JPanel panelHeure = new JPanel();
        panelHeure.setBounds(570, 145, 121, 35); // Ajustez les coordonnées selon vos besoins
        paneldroite.add(panelHeure);
        
        SpinnerDateModel modelHeure = new SpinnerDateModel();
        panelHeure.setLayout(new GridLayout(0, 1, 0, 0));
        spinnerHeure = new JSpinner(modelHeure);
        
        panelHeure.add(spinnerHeure);
        spinnerHeure.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JSpinner.DateEditor editorHeure = new JSpinner.DateEditor(spinnerHeure, "HH:mm:ss");
        spinnerHeure.setEditor(editorHeure);
        
        
        
        JLabel lbl_locale = new JLabel("Locale : ");
        lbl_locale.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_locale.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lbl_locale.setBounds(20, 245, 177, 35);//initale 57, 272, 177, 35
        paneldroite.add(lbl_locale);
        
        // Créez le JComboBox pour les locaux
        comboBoxLocaux = new JComboBox<>(Soutenance.Local.values());
        comboBoxLocaux.setFont(new Font("Tahoma", Font.PLAIN, 19));
        comboBoxLocaux.setBounds(236, 245, 297, 35); // Ajustez les dimensions et la position selon vos besoins
        paneldroite.add(comboBoxLocaux);

        
        
        
        JLabel lbl_presidentJ1 = new JLabel("Président : ");
        lbl_presidentJ1.setForeground(new Color(64, 128, 128));
        lbl_presidentJ1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_presidentJ1.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        lbl_presidentJ1.setBounds(41, 290, 214, 35);
        paneldroite.add(lbl_presidentJ1);
        
        JLabel lbl_rapporteurJ2 = new JLabel("Rapporteur : ");
        lbl_rapporteurJ2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_rapporteurJ2.setForeground(new Color(64, 128, 128));
        lbl_rapporteurJ2.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        lbl_rapporteurJ2.setBounds(288, 290, 214, 35);
        paneldroite.add(lbl_rapporteurJ2);
        
        JLabel lbl_examinateurJ3 = new JLabel("examinateur : ");
        lbl_examinateurJ3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_examinateurJ3.setForeground(new Color(64, 128, 128));
        lbl_examinateurJ3.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        lbl_examinateurJ3.setBounds(530, 290, 214, 35);
        paneldroite.add(lbl_examinateurJ3);
        
       // aporter les enseignant de la base de donne
        EnseignantDAO rechens=new EnseignantDAO();
        try {
			ToutEnseigant=(ArrayList<Enseignant>) rechens.findAll();
		} catch (PFEException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
		}
        //regalge du comobox president
        comboBoxPresident= new JComboBox(ToutEnseigant.toArray(new Enseignant[0]));
        comboBoxPresident.setRenderer(new EnseignantComboBoxRenderer());
        comboBoxPresident.setBounds(41, 320, 214, 35);
        paneldroite.add(comboBoxPresident);
        
        comboBoxRapporteur= new JComboBox(ToutEnseigant.toArray(new Enseignant[0]));
        comboBoxRapporteur.setRenderer(new EnseignantComboBoxRenderer());
        comboBoxRapporteur.setBounds(288, 320, 214, 35);
        paneldroite.add(comboBoxRapporteur);
        
        comboBoxExaminateur = new JComboBox(ToutEnseigant.toArray(new Enseignant[0]));
        comboBoxExaminateur.setRenderer(new EnseignantComboBoxRenderer());
        comboBoxExaminateur.setBounds(530, 320, 214, 35);
        paneldroite.add(comboBoxExaminateur);
        
        JButton btnChercher = new JButton("chercher");
        btnChercher.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		try {
         			SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
	            	Soutenance soutenance;
	            	soutenance = lire();
	            	
	            	soutenance=DAOsoutenance.findByIDpr(soutenance);
    					if(soutenance!=null) {
    						affichersout(soutenance);
    						comboBoxLocaux.setSelectedItem(soutenance.getlocalsout());
    						comboBoxPresident.setSelectedItem(soutenance.getJurys().getPresident());
    						comboBoxRapporteur.setSelectedItem(soutenance.getJurys().getRapporteur());
    						comboBoxExaminateur.setSelectedItem(soutenance.getJurys().getExaminateur());
    						
    						//
    						java.sql.Date date = java.sql.Date.valueOf(soutenance.getDateSoutenance());
    						jCalendar.setDate(date);
    						
    						//
    						LocalDateTime dateTime = LocalDateTime.of(soutenance.getDateSoutenance(), soutenance.getHeureSoutenance());
    						Date dateHeure = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    						spinnerHeure.setValue(dateHeure);
    						
    					}
    				} catch (PFEException e1) {
    					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    				}
         	}
         });
        btnChercher.setForeground(Color.WHITE);
        btnChercher.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnChercher.setBackground(new Color(62, 133, 140));
        btnChercher.setBounds(238, 50, 295, 35);//initale 238, 50, 295, 35
        paneldroite.add(btnChercher);
        
        
        JButton btnModifier = new JButton("modifier");
        btnModifier.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		try {
         			SoutenanceDAO DAOsoutenance=new SoutenanceDAO();
	            	Soutenance soutenance;
	            	soutenance = lireModification();
	            	//System.out.print(soutenance.getJurys());
	            	DAOsoutenance.update(soutenance);
	            	affichersout(soutenance);
	            	clearTextFields();
    				} catch (PFEException e1) {
    					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    				}
         	}
         });
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModifier.setBackground(new Color(62, 133, 140));
        btnModifier.setBounds(236, 362, 285, 35);
        paneldroite.add(btnModifier);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 400, 760, 132);//initale 10, 441, 749, 92
        paneldroite.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Rendre toutes les cellules non modifiables
                return false;
            }
        };
        
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

        
        
         ///////////////////////////////////////////////////
         // Action pour "afficher soutenances"
            lblAfficherer_soutenance.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Afficher_Soutenance afficherSoutenancesFrame = new Afficher_Soutenance();
                    afficherSoutenancesFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);

                }
            });

            // Action pour "creer soutenance"
            lblCreer_soutenance.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	Creation_Soutenance creerSoutenanceFrame = new Creation_Soutenance();
                    creerSoutenanceFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);
                }
            });

            // Action pour "supprimer soutenance"
            lblSupprimer_soutenance.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	Supprimer_Soutenance supprimerSoutenanceFrame = new Supprimer_Soutenance();
                    supprimerSoutenanceFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);
                }
            });

            // Action pour "chercher et modifier"
 /*           lblChercher_et_modifier.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	Chercher_Soutenance chercherEtModifierFrame = new Chercher_Soutenance();
                    chercherEtModifierFrame.setVisible(true);
                }
            });*/

            // Action pour "chercher par étudiant"
            lblChercher_par_etudiant.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	RechercheSoutenance_etudiant chercherParEtudiantFrame = new RechercheSoutenance_etudiant();
                    chercherParEtudiantFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);
                }
            });
            
            // Action pour "chercher par salle"
            lbl_Chercher_par_salle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	RechercheSoutenance_salle chercherParEtudiantFrame = new RechercheSoutenance_salle();
                    chercherParEtudiantFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);
                }
            });
            
            
            // Action pour "chercher par date"
            lblChercherParDate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	RechercheSoutenance_date chercherPardateFrame = new RechercheSoutenance_date();
                	chercherPardateFrame.setVisible(true);
                	Chercher_Soutenance.this.setVisible(false);
                }
            });

            // Action pour "attribuer Note"
            lblAttribuerNote.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Note attribuerNoteFrame = new Note();
                    attribuerNoteFrame.setVisible(true);
                    Chercher_Soutenance.this.setVisible(false);
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



        //////////////////////////////////////////
        
       
}
    
    public Soutenance lire() throws PFEException{
    	Soutenance sout=new Soutenance();
    	// Validate project title
        if (textField_titreprojet.getText().isEmpty()|| !(textField_titreprojet.getText().matches("[a-zA-Z ]+"))) {
            throw new PFEException(40); // Assuming error code 12 is for an empty project name
        }
        
        Projet pr=new Projet();
        pr.setTitre(textField_titreprojet.getText());
        
        ProjetDAO rechpr=new ProjetDAO();
        Projet p=rechpr.findByTitre(pr);
        sout.setProjet(p);
        return sout;
    }
    
    public Soutenance lireModification() throws PFEException {
    	
    	// Validate project title
        if (textField_titreprojet.getText().isEmpty()|| !(textField_titreprojet.getText().matches("[a-zA-Z ]+"))) {
            throw new PFEException(40); // Assuming error code 12 is for an empty project name
        }

        // Convertir la chaîne de date en LocalDate
        Date selectedDate = jCalendar.getDate();
        Instant instant = selectedDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        
        
        Soutenance.Local locale = comboBoxLocaux.getSelectedItem() != null ? (Soutenance.Local) comboBoxLocaux.getSelectedItem() : null;
                
        // Extrait le temps du spinnerHeure
        Date heure = (Date) spinnerHeure.getValue();
        LocalTime localTime = heure.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        //enlever les seconde de LocalTime
        LocalTime heurefinale=localTime.withSecond(0).withNano(0);
        
        // Création d'une instance president
    	Enseignant pr = new Enseignant();
    	Enseignant prSelectionne = (Enseignant) comboBoxPresident.getSelectedItem();
        /*if (prSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = prSelectionne.getCIN();
            pr.setCIN(CIN);
        }
        else {
        	throw new PFEException(9);
        }*/
        
        // Création d'une instance rapporteur
    	Enseignant rap = new Enseignant();
    	Enseignant rapSelectionne = (Enseignant) comboBoxRapporteur.getSelectedItem();
        /*if (rapSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = rapSelectionne.getCIN();
            rap.setCIN(CIN);
        }
        else {
        	throw new PFEException(9);
        }*/
        
       // Création d'une instance examinateur
    	Enseignant ex = new Enseignant();
    	Enseignant exSelectionne = (Enseignant) comboBoxExaminateur.getSelectedItem();
        /*if (exSelectionne != null) {
            // Récupération et définition des attributs de l'étudiant
            int CIN = exSelectionne.getCIN();
            ex.setCIN(CIN);
        }
        else {
        	throw new PFEException(9);
        }*/
        
        
        // Create a Soutenance instance
        Soutenance soutenance = new Soutenance();
        Projet projet=new Projet();
        Jurys jury=new Jurys();
        
        
        jury.setPresident(prSelectionne);
        jury.setRapporteur(rapSelectionne);
        jury.setExaminateur(exSelectionne);
        
        
        projet.setTitre(textField_titreprojet.getText());
        //recherche dans la base de ce projet
        ProjetDAO rechp=new ProjetDAO();
        Projet projetf=rechp.findByTitre(projet);
        
       //recherche dans la base de ce jurys
        JurysDAO rechj=new JurysDAO();
        Jurys jurysf=rechj.create(jury);
        System.out.println("jurysf= "+jurysf);
        //
        SoutenanceDAO rechsout=new SoutenanceDAO();
        
        soutenance.setJurys(jurysf);
        soutenance.setProjet(projetf);
        soutenance.setlocalsout(locale);
        soutenance.setDateSoutenance(localDate);
        soutenance.setHeureSoutenance(heurefinale);
        soutenance.setId(rechsout.findByIDpr(soutenance).getId());

        return soutenance;
    }
    
    public void affichersout(Soutenance soutenance) {
        
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
	private void clearTextFields() {
		textField_titreprojet.setText(""); 
        comboBoxLocaux.setSelectedIndex(0);
        comboBoxPresident.setSelectedIndex(0);
        comboBoxRapporteur.setSelectedIndex(0);
        comboBoxExaminateur.setSelectedIndex(0);
    }
}
