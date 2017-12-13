package logic.dao;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@XmlRootElement
public class SqlMap {
    private Properties prop = new Properties();

    public String getSql(String name) {
        return prop.getProperty(name);
    }

    public void loadXML(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            prop.loadFromXML(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}