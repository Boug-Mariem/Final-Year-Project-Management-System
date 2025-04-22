package interfacePFE;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.EtudiantDAO;
import DAO.UtilisateurDAO;
import projetPFE.Etudiant;
import projetPFE.PFEException;
import projetPFE.Utilisateur;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }*/

    public Login() {
        setTitle("Se Connecter");
        ImageIcon icon = new ImageIcon("login.png");
        setIconImage(icon.getImage());
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Création d'un JSplitPane pour diviser le contenu
        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(0.3);
        splitPane.setDividerSize(1);

        // Charger l'image depuis le fichier
        ImageIcon icon1 = new ImageIcon("desk.png");

        // Redimensionner l'image
        int width = 300; // Largeur souhaitée
        int height = 250; // Hauteur souhaitée
        Image img = icon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Créer une nouvelle ImageIcon avec l'image redimensionnée
        ImageIcon resizedIcon = new ImageIcon(img);

        // Panneau gauche pour l'image
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(62, 133, 140));
        splitPane.setLeftComponent(leftPanel);
        leftPanel.setLayout(new BorderLayout(0, 0));

        // Créer le JLabel avec l'image redimensionnée
        JLabel lblImage = new JLabel(resizedIcon);
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);

        // Ajouter le JLabel au JPanel
        leftPanel.add(lblImage, BorderLayout.CENTER);

        // Panneau droit pour le formulaire de connexion
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(62, 133, 140));
        splitPane.setRightComponent(rightPanel);
        rightPanel.setLayout(new BorderLayout(0, 0));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(62, 133, 140));
        formPanel.setLayout(new GridBagLayout());
        rightPanel.add(formPanel, BorderLayout.CENTER);

        // GridBagConstraints pour chaque composant
        GridBagConstraints gbcLblUsername = new GridBagConstraints();
        gbcLblUsername.gridx = 0;
        gbcLblUsername.gridy = 1;
        gbcLblUsername.insets = new Insets(5, 10, 5, 10);  //(padding)
        gbcLblUsername.anchor = GridBagConstraints.WEST;

        GridBagConstraints gbcTextFieldUsername = new GridBagConstraints();
        gbcTextFieldUsername.insets = new Insets(5, 10, 5, 10);
        gbcTextFieldUsername.anchor = GridBagConstraints.WEST;
        gbcTextFieldUsername.gridx = 1;
        gbcTextFieldUsername.gridy = 1;
        gbcTextFieldUsername.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints gbcLblPassword = new GridBagConstraints();
        gbcLblPassword.insets = new Insets(5, 10, 5, 10);
        gbcLblPassword.anchor = GridBagConstraints.WEST;
        gbcLblPassword.gridx = 0;
        gbcLblPassword.gridy = 2;

        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.insets = new Insets(5, 10, 5, 10);
        gbcPasswordField.anchor = GridBagConstraints.WEST;
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 2;
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL;

       /* GridBagConstraints gbcChckbxRememberPassword = new GridBagConstraints();
        gbcChckbxRememberPassword.insets = new Insets(5, 10, 5, 10);
        gbcChckbxRememberPassword.anchor = GridBagConstraints.WEST;
        gbcChckbxRememberPassword.gridx = 0;
        gbcChckbxRememberPassword.gridy = 2;
        gbcChckbxRememberPassword.gridwidth = 2;*/

        GridBagConstraints gbcBtnLogin = new GridBagConstraints();
        //TODO 
        gbcBtnLogin.insets = new Insets(60, 150,0, 10);
        gbcBtnLogin.anchor = GridBagConstraints.CENTER;
        gbcBtnLogin.gridx = 0;
        gbcBtnLogin.gridy = 4;
        gbcBtnLogin.gridwidth = 2;
        gbcBtnLogin.fill = GridBagConstraints.NONE;
        
                JLabel lblLogin = new JLabel("Se connecter");
                GridBagConstraints gbc_lblLogin = new GridBagConstraints();
                gbc_lblLogin.insets = new Insets(0, 0, 50, 5);
                gbc_lblLogin.gridx = 1;
                gbc_lblLogin.gridy = 0;
                gbc_lblLogin.anchor = GridBagConstraints.PAGE_START; // Aligner en haut
     			gbc_lblLogin.fill = GridBagConstraints.HORIZONTAL;
                formPanel.add(lblLogin, gbc_lblLogin);
                lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
                lblLogin.setFont(new Font("Tahoma", Font.BOLD, 40));
                lblLogin.setForeground(Color.WHITE);
                lblLogin.setOpaque(true);
                lblLogin.setBackground(new Color(62, 133, 140));



        JLabel lblUsername = new JLabel("Nom d'utilisateur :");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblUsername.setForeground(Color.WHITE);
        formPanel.add(lblUsername, gbcLblUsername);

        textFieldUsername = new JTextField();
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textFieldUsername.setPreferredSize(new Dimension(280, 40));
        formPanel.add(textFieldUsername, gbcTextFieldUsername);

        JLabel lblPassword = new JLabel("Mot de passe:");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblPassword.setForeground(Color.WHITE);
        formPanel.add(lblPassword, gbcLblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setPreferredSize(new Dimension(280, 40));
        formPanel.add(passwordField, gbcPasswordField);

        // Charger l'image originale
        ImageIcon originalIcon = new ImageIcon("eye.png");

        // Redimensionner l'image en utilisant Image.getScaledInstance()
        // Remplacez 20, 20 par la largeur et la hauteur souhaitées.
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        // Créer une nouvelle ImageIcon avec l'image redimensionnée
        ImageIcon image = new ImageIcon(scaledImage);

        // Créez le JLabel et ajoutez l'icône à celui-ci.
        JLabel lblNewLabel = new JLabel("", image, JLabel.CENTER);

        // Aligner le texte et l'image selon vos besoins.
        lblNewLabel.setHorizontalTextPosition(JLabel.CENTER);
        lblNewLabel.setVerticalTextPosition(JLabel.BOTTOM);

        // Ajoutez un écouteur d'événements pour détecter les clics sur l'icône de l'œil
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Vérifiez si le mot de passe est actuellement caché
                if(passwordField.getEchoChar() != 0) {
                    // Si oui, montrez-le
                    passwordField.setEchoChar((char) 0);
                } else {
                    // Sinon, cachez-le à nouveau
                    passwordField.setEchoChar('\u2022'); // Vous pouvez utiliser '\u25cf' pour un cercle plein si nécessaire
                }
            }
        });

        // Ajoutez ensuite le label à votre panneau (formPanel) en utilisant les contraintes de GridBagConstraints.
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 2;
        formPanel.add(lblNewLabel, gbc_lblNewLabel);

        // Rafraîchir le panneau pour montrer les changements
        formPanel.revalidate();
        formPanel.repaint();

        /*chckbxRememberPassword = new JCheckBox("Remember password");
        chckbxRememberPassword.setVerticalAlignment(SwingConstants.BOTTOM);
        chckbxRememberPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chckbxRememberPassword.setBackground(new Color(62, 133, 140));
        chckbxRememberPassword.setForeground(new Color(255, 255, 240));
        formPanel.add(chckbxRememberPassword, gbcChckbxRememberPassword);*/

        JButton btnLogin = new JButton("Se connecter");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnLogin.setForeground(new Color(62, 133, 140));
        btnLogin.setBackground(new Color(255, 255, 255));
        formPanel.add(btnLogin, gbcBtnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					UtilisateurDAO DAOutil=new UtilisateurDAO();
		           	Utilisateur util;
					util = lire();
					
					boolean testConnectivite=DAOutil.connecter(util);
					if(testConnectivite) {
						//JOptionPane.showMessageDialog(null,"Connexion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
						//if(testConnectivite) ouvrir une autre interface
						Acceuil Acceuilframe=new Acceuil();
						Acceuilframe.setVisible(true);
						Login.this.setVisible(false);

					}
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
            }
        }
        );

        
        
        /*btnLogin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Login functionality not implemented yet!");
        });*/

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(62, 133, 140));
        rightPanel.add(southPanel, BorderLayout.SOUTH);

        JLabel lblSignUp = new JLabel("Vous n'avez pas de compte ? S'inscrire");
        lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSignUp.setForeground(new Color(255, 255, 224));
        lblSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Creer_Compte creerCompteFrame =new Creer_Compte();
                creerCompteFrame.setVisible(true);
                Login.this.setVisible(false);

            }
        });
        southPanel.add(lblSignUp);
    }
    
    /*public Utilisateur lire() {
        // Création d'une instance utilisateur
    	String password = new String(passwordField.getPassword());
        Utilisateur util = new Utilisateur();
        util.setUsername(textFieldUsername.getText());
        util.setPassword(password);
        return util;
    }*/
    
    public Utilisateur lire() throws PFEException {
    	
        Utilisateur u =new Utilisateur();
        u.setPassword(passwordField.getText());
        u.setUsername(textFieldUsername.getText());
        
        return u;
    }
}

