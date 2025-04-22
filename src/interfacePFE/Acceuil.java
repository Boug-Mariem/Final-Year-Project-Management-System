package interfacePFE;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class Acceuil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Acceuil frame = new Acceuil();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    public Acceuil() {
        setTitle("Acceuil");
        ImageIcon icon = new ImageIcon("home.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        setResizable(false);
        //setLocation(170, 80);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 984, 561);
        contentPane.add(panel);
        panel.setLayout(null);
        

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(114, 184, 184));
        panel_1.setBounds(89, 180, 806, 359);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
     // Use the resizeIcon method to get the resized ImageIcon while maintaining the aspect ratio
        ImageIcon originalIcon = new ImageIcon("etudiant.png");
        ImageIcon newIcon = resizeIcon(originalIcon, 150, 150); // Assuming 150x150 is the size you want for the icon

        // Create lblNewLabel with the newIcon
        JLabel lblNewLabel = new JLabel(newIcon);
        lblNewLabel.setBounds(89, 38, 150, 150); // Set the bounds where you want the label to appear
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_etudiant
                Acceuil_Etudiant EtudiantFrame = new Acceuil_Etudiant();
                EtudiantFrame.setVisible(true);
                Acceuil.this.setVisible(false);
            }
        });
        panel_1.add(lblNewLabel);
        
        
        ImageIcon originalIcon1 = new ImageIcon("professor.png");
        ImageIcon newIcon1 = resizeIcon(originalIcon1, 150, 150); // Assuming 150x150 is the size you want for the icon
        JLabel lblNewLabel_1 = new JLabel(newIcon1);
        lblNewLabel_1.setBounds(311, 38, 150, 150);
        lblNewLabel_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Ajouter_enseignant
            	Acceuil_Enseignant EnseignantFrame = new Acceuil_Enseignant();
            	EnseignantFrame.setVisible(true);
            	Acceuil.this.setVisible(false);
            }
        });
        
        panel_1.add(lblNewLabel_1);

        ImageIcon originalIcon2 = new ImageIcon("encadreur_societe.png");
        ImageIcon newIcon2 = resizeIcon(originalIcon2, 150, 150);
        JLabel lblNewLabel_2 = new JLabel(newIcon2);
        lblNewLabel_2.setBounds(560, 38, 150, 150);
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Afficher_EncadreurSociete
                Acceuil_EncadreurSociete EncadreurSocieteFrame = new Acceuil_EncadreurSociete();
                EncadreurSocieteFrame.setVisible(true);
                Acceuil.this.setVisible(false);
            }
        });
        panel_1.add(lblNewLabel_2);

        ImageIcon originalIcon3 = new ImageIcon("pfe.png");
        ImageIcon newIcon3 = resizeIcon(originalIcon3, 150, 150);
        JLabel lblNewLabel_3 = new JLabel(newIcon3);
        lblNewLabel_3.setBounds(89, 184, 150, 150);
        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Afficher_enseignant
                Acceuil_Projet projetFrame = new Acceuil_Projet();
                projetFrame.setVisible(true);
                Acceuil.this.setVisible(false);
            }
        });
        panel_1.add(lblNewLabel_3);

        ImageIcon originalIcon5 = new ImageIcon("graduation.png");
        ImageIcon newIcon5 = resizeIcon(originalIcon5, 150, 150);
        JLabel lblNewLabel_5 = new JLabel(newIcon5);
        lblNewLabel_5.setBounds(311, 184, 150, 150);
        lblNewLabel_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ouvrir la fenêtre Afficher_enseignant
                Acceuil_Soutenance soutenanceFrame = new Acceuil_Soutenance();
                soutenanceFrame.setVisible(true); 
                Acceuil.this.setVisible(false);}
        });
        panel_1.add(lblNewLabel_5);

        ImageIcon originalIcon4 = new ImageIcon("degree.png");
        ImageIcon newIcon4 = resizeIcon(originalIcon4, 150, 150);
        JLabel lblNewLabel_4 = new JLabel(newIcon4);
        lblNewLabel_4.setBounds(560, 184, 150, 150);
        lblNewLabel_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PdfinfoSoutenance PdfinfoSoutenanceFrame = new PdfinfoSoutenance();
            	PdfinfoSoutenanceFrame.setVisible(true); 
                Acceuil.this.setVisible(false);
            }
        });
        panel_1.add(lblNewLabel_4);

        
    
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(114, 184, 184));
        panel_2.setBounds(0, 0, 984, 60);
        panel.add(panel_2);
        panel_2.setLayout(null);
        


  // Then use it to resize and add isimm with "isimm.png"
  ImageIcon icon7 = new ImageIcon("isimm.png"); // Load the original icon
  // Resize the image to match the JLabel's dimensions while preserving aspect ratio
  ImageIcon resizedIcon7 = resizeIcon(icon7, 742, 59);
  JLabel isimm = new JLabel(resizedIcon7); // Create the JLabel with the resized icon
  isimm.setBounds(121, 0, 742, 59); // Set the bounds of the JLabel
  panel_2.add(isimm); // Add the JLabel to the panel

     
  // Ensure the file name is correct
     ImageIcon logisim = new ImageIcon("logoisim.png"); // Load the original icon
     // Resize the image to match the JLabel's dimensions while preserving aspect ratio
     ImageIcon resizedlogisim = resizeIcon(logisim, 800, 118);
     JLabel lblLogisim = new JLabel(resizedlogisim); // Create the JLabel with the resized icon
     // Set the bounds of the JLabel to the size intended
     lblLogisim.setBounds(121, 0, 742, 59); // Set the bounds of the JLabel
     panel.add(lblLogisim); // Add the JLabel to the panel
     
     
        JLabel lblNewLabel_8 = new JLabel("   Gestion des projets de Fin d'Etudes (PFE)");
        lblNewLabel_8.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
        lblNewLabel_8.setBounds(121, 81, 742, 60);
        panel.add(lblNewLabel_8);
        lblLogisim = new JLabel(resizedlogisim);
        lblLogisim.setBounds(0, 60, 128, 121);
        panel.add(lblLogisim);
        
        

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
    
    
    private void openNewFrame() {
        JFrame newFrame = new JFrame();
        newFrame.setSize(300, 200);
        newFrame.setVisible(true);
    }
}
