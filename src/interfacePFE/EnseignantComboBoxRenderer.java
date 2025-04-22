package interfacePFE;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import projetPFE.Enseignant;

public class EnseignantComboBoxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Enseignant) {
            Enseignant enseignant = (Enseignant) value;
            setText(enseignant.toString());
        }
        return this;
    }
}
