package interfacePFE;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.io.exceptions.IOException;

import DAO.EtudiantDAO;
import DAO.SoutenanceDAO;
import pdf.infoSoutenance;
import projetPFE.Etudiant;
import projetPFE.PFEException;
import projetPFE.Soutenance;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PdfinfoSoutenance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tableEtud;
	private DefaultTableModel modeltableEtud;


	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PdfinfoSoutenance frame = new PdfinfoSoutenance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public PdfinfoSoutenance() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
        setResizable(false); // annuler la redimension
        setTitle("Téléchargement du pdf inforamation soutenance");
        setLocationRelativeTo(null);

        ImageIcon icone = new ImageIcon("degree.png");
        setIconImage(icone.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(115, 186, 193));
		panel.setBounds(0, 0, 986, 28);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Telechargement PDF");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 0, 516, 25);
		panel.add(lblNewLabel);
		
		JPanel paneldroite = new JPanel();
		paneldroite.setLayout(null);
		paneldroite.setBounds(0, 28, 992, 533);
		contentPane.add(paneldroite);
		
		
		JLabel lblNewLabel_2 = new JLabel("Choisissez un étudiant:");
		lblNewLabel_2.setForeground(new Color(64, 128, 128));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_2.setBounds(31, 29, 530, 54);
		paneldroite.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("CIN :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(287, 127, 87, 39);
		paneldroite.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(393, 127, 267, 39);
		paneldroite.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(87, 259, 810, 73);
		paneldroite.add(scrollPane_1);
		
		JButton btnNewButton_1 = new JButton("Verifier");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(447, 187, 132, 48);
		btnNewButton_1.setBackground(new Color(62, 133, 140));
		paneldroite.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					EtudiantDAO DAOetud=new EtudiantDAO();
	            	Etudiant etud;
					etud = lire();
					etud=DAOetud.find(etud);
					if(etud!=null) {
						afficherEtud(etud);
					}
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );
		
		JButton btnNewButton_1_1 = new JButton("Télécharger");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(447, 375, 132, 48);
		btnNewButton_1_1.setBackground(new Color(62, 133, 140));
		paneldroite.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				try {
					EtudiantDAO DAOetud=new EtudiantDAO();
	            	Etudiant etud;
					etud = lire();
					etud=DAOetud.find(etud);
					
					SoutenanceDAO rechsout=new SoutenanceDAO();
					ArrayList<Soutenance> souts=(ArrayList<Soutenance>) rechsout.findAllByStudent(etud);
					if(souts.size()==0)
						throw new PFEException(50);
					else {
						try {
				            new infoSoutenance().infoSoutenance(souts.get(0),etud);
				            JOptionPane.showMessageDialog(null,"PDF créée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
				        } catch (IOException e2) {
				            e2.printStackTrace();
				        } catch (java.io.IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (PFEException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
            	
            }
        }
        );
		
		        
		        //////////////////////////////////////////////////////////////////////
		      //TODO  ////////////////////////////////
		        
		        addWindowListener(new WindowAdapter() {
		            public void windowClosing(WindowEvent e) {
		                // Ouvrir la nouvelle interface ici
		            	Acceuil frame = new Acceuil();
						frame.setVisible(true);
		            }
		        });
		        
		        //////////////////////////////////////////////////////////////////////
		        modeltableEtud = new DefaultTableModel() {
	                  @Override
	                  public boolean isCellEditable(int row, int column) {
	                      // Rendre toutes les cellules non modifiables
	                      return false;
	                  }
	              };
	              
	              tableEtud = new JTable();
	              scrollPane_1.setViewportView(tableEtud);
	              modeltableEtud.addColumn("CIN");
	              modeltableEtud.addColumn("Nom");
	              modeltableEtud.addColumn("Prenom");
	              modeltableEtud.addColumn("Groupe");
	              modeltableEtud.addColumn("Email");
	              modeltableEtud.addColumn("Téléphone");
	              tableEtud.setModel(modeltableEtud);
	              //tableEtud.setRowHeight(30);
	              
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

	}
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
        tableEtud.setRowHeight(50);
      
    }
}
