package tk.skybread.assessment.Utils;

import javax.swing.*;

public class JlabelUtils {

    public static String toString(JLabel jl) {
        String str = jl.toString();
        return str;
    }

    public static JLabel fromString(String str) {
        JLabel label = new JLabel(str);
        return label;
    }

}
