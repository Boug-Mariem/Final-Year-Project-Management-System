package pdf;
import java.io.IOException;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import projetPFE.Etudiant;
import projetPFE.Soutenance;

public class infoSoutenance {

    public static final String DEST = "information_soutenance.pdf";
    private Document document;
/*
    public static void main(String[] args) {
        try {
            new infoSoutenance().infoSoutenance();
            System.out.println("Invitation à la soutenance créée avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void infoSoutenance(Soutenance sout,Etudiant etud) throws IOException {
        // Création d'un nouveau document PDF
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DEST));
        document = new Document(pdfDocument);
        
        // Ajout du contenu à l'invitation
        
        addCenteredText("Informations Soutenance");
        addText("");
        addText("");
        addgrandText("Etudiant: ");
        addText("CIN étudiant: "+etud.getCIN());
        addText("Nom étudiant: "+etud.getNom());
        addText("Prénom étudiant: "+etud.getPrenom());
        addText("Groupe étudiant: "+etud.getGroupe());
        addText("Email étudiant: "+etud.getEmail());
        addText("Numero de Téléphone étudiant: "+etud.getNumTel());
        addText("");
        addText("");
        addText("Nom projet: "+sout.getProjet().getTitre());
        addText("Date soutenance: "+sout.getDateSoutenance());
        addText("Heure soutenance: "+sout.getHeureSoutenance());
        addText("Local soutenance: "+sout.getlocalsout());
        addText("");
        addText("");
        addgrandText("Encadreurs: ");
        addText("Nom encadreur: "+sout.getProjet().getEncadreur().getNom());
        addText("Prénom encadreur: "+sout.getProjet().getEncadreur().getPrenom());
        addText("Nom encadreurSoc: "+sout.getProjet().getEncadreurSoc().getNom());
        addText("Prénom encadreurSoc: "+sout.getProjet().getEncadreurSoc().getPrenom());
        addText("");
        addText("");
        addgrandText("Jurys: ");
        addText("Nom president: "+sout.getJurys().getPresident().getNom());
        addText("Prénom President : "+sout.getJurys().getPresident().getPrenom());
        
        addText("Nom rapporteur: "+sout.getJurys().getRapporteur().getNom());
        addText("Prénom rapporteur : "+sout.getJurys().getRapporteur().getNom());
        
        addText("Nom examinateur: "+sout.getJurys().getExaminateur().getNom());
        addText("Prénom examinateur : "+sout.getJurys().getExaminateur().getNom());
        
       
        // Fermeture du document
        document.close();
    }

    private void addCenteredText(String text) {
        // Crée un paragraphe centré avec une grande taille de police et une couleur rouge
    	Color customColor = new DeviceRgb(242, 188, 148);
    	Paragraph paragraph = new Paragraph(text)
            .setFontSize(40)
            //.setFontColor(ColorConstants.RED);
            .setTextAlignment(TextAlignment.CENTER);
    	paragraph.setFontColor(customColor);
        // Ajoute le paragraphe au document
        document.add(paragraph);
    }
    
    private void addgrandText(String text) {
        // Crée un paragraphe centré avec une grande taille de police et une couleur rouge
    	Color darkBlue = new DeviceRgb(0, 0, 139);
    	Paragraph paragraph = new Paragraph(text)
        		.setBold()
        		.setFontSize(20);
    	paragraph.setFontColor(darkBlue);

        // Ajoute le paragraphe au document
        document.add(paragraph);
    }


    private void addText(String text) {
        // Ajoute un paragraphe simple
        document.add(new Paragraph(text));
    }
 
}
