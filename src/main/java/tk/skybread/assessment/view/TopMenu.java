package tk.skybread.assessment.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class TopMenu {

    public static JMenuBar mbar() {
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);

        //m1 buttons [File]
        JMenuItem m11 = new JMenuItem("Save as");
        m1.add(m11);

        //m2 [Help]
        JMenuItem m21 = new JMenuItem("About");
        m2.add(m21);

        //add button listeners
        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoggerTab.Logger(Level.INFO, "Save-As button clicked");
            }
        });

        m21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoggerTab.Logger(Level.INFO, "About button clicked");
            }
        });

        // return menu bar to frame.add()
        return mb;
    }
}
