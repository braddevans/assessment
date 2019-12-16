package tk.skybread.assessment.Utils;

import javax.swing.*;
import java.awt.*;

public class NewTabUtil {

    private static JTabbedPane tabbedPane = new JTabbedPane();

    public static JTabbedPane add(String tabLabel, JPanel content) {
        JPanel tab = new JPanel();
        tab.setOpaque(false);
        tab.add(JlabelUtils.fromString(tabLabel), BorderLayout.WEST);

        tabbedPane.addTab(null, content);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tab);

        tabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);

        return tabbedPane;
    }
}
