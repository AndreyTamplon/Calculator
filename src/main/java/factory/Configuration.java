package factory;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    private Properties properties;

    public Configuration(String configurationFileName) throws ConfigurationException
    {
        properties = new Properties();
        try (InputStream inputStream = Configuration.class.getResourceAsStream(configurationFileName))
        {
            properties.load(inputStream);
        }
        catch (NullPointerException | IOException e)
        {
            throw new ConfigurationException("Failed to load configuration file");
        }
    }

    public String getOperatorClassPath(String operatorName)
    {
        return properties.getProperty(operatorName);
    }

    public void setConfiguration(String configurationFileName) throws IOException
    {
        properties.load(Configuration.class.getResourceAsStream(configurationFileName));
    }
}
