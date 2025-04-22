package interfacePFE;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import projetPFE.EncadreurSociete;

public class EncadreurSocieteComboboxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof EncadreurSociete) {
        	EncadreurSociete enc = (EncadreurSociete) value;
            setText(enc.toString());
        }
        return this;
    }
}