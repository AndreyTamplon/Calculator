package factory;

import exceptions.CouldNotConfigureException;
import exceptions.InvalidOperator;
import operators.Operator;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleCalculator extends Calculator
{
    private static final Logger logger = Logger.getLogger(SimpleCalculator.class.getName());
    private Configuration configuration;
    public SimpleCalculator(String configurationFileName)
            throws CouldNotConfigureException
    {
        super();
        try
        {
            configuration = new Configuration(configurationFileName);
        }
        catch (ConfigurationException e)
        {
            throw new CouldNotConfigureException("The Calculator could not be created because it could not be configured");
        }
    }

    public void setConfiguration(String configurationFileName) throws IOException
    {
        configuration.setConfiguration(configurationFileName);
    }

    @Override
    protected Operator createOperator(String operatorName)
            throws InvalidOperator
    {
        logger.log(Level.INFO, "creating {0}", operatorName);
        try
        {
            return (Operator) Class.forName(configuration.getOperatorClassPath(operatorName)).newInstance();
        }
        catch (ClassNotFoundException | NullPointerException e)
        {
            throw new InvalidOperator("Could not find an operator with the name " + operatorName);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new InvalidOperator("Could not create an operator with the name " + operatorName, e);
        }
    }
}
