package ProgrammePrincipale;

import java.awt.EventQueue;

import interfacePFE.Login;

public class GestionPFE {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

	}

}
