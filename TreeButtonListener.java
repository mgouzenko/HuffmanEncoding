import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class TreeButtonListener implements ActionListener {

    private JDialog popup; 

    public TreeButtonListener(JDialog p) {
        popup = p; 
    }

    public void actionPerformed(ActionEvent ae) {
        popup.setVisible(true);
    }

}

