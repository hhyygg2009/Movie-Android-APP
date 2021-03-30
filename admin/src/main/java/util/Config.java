package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    static Properties p;

    static {
        try {
            p = new Properties();
            System.out.println(new File(".").getAbsolutePath());
            p.load(new FileInputStream("./config/sqlserver.properties"));

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "∂¡»° ˝æ›ø‚≈‰÷√¥ÌŒÛ");
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return p.getProperty(key);
    }
}
