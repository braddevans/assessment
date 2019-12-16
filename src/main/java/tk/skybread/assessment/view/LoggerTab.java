package tk.skybread.assessment.view;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class LoggerTab{
    private static JTextArea textArea = new JTextArea(15, 30);

    public static void Logger(Level level, String log) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        textArea.append( "[" + dateFormat.format(date) + "] " + "[" + level + "] " + log + "\n");
    }


    public static JPanel panel() {
        //JPanel instance
        JPanel panel = new JPanel(false);

        //int
        JScrollPane textAreaConsole = new JScrollPane(textArea);
        textAreaConsole.setBounds(10,60,780,500);
        textAreaConsole.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        //set layout
        panel.setLayout(new GridLayout(1, 1));
        panel.add(textAreaConsole);
        textArea.setEditable(false);

        return panel;
    }
}
