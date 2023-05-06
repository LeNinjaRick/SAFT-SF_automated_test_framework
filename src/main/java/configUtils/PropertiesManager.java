package configUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private Properties props = new Properties();
    private String filePath;

    public PropertiesManager(String filePath) {
        this.filePath = filePath;
    }

    public Properties getProps() {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(this.filePath);
            fileInputStream = new FileInputStream(file);
            this.props.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception var11) {
            System.err.println(var11.getMessage());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException var10) {
                    System.err.println(var10.getMessage());
                }
            }

        }

        return this.props;
    }
}