import exceptions.CalculatorException;
import operation_executor.OperationProcessor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Application
{
    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws IOException, CalculatorException
    {
        try
        {
            LogManager.getLogManager().readConfiguration(Application.class.getResourceAsStream("logger.properties"));
        }
        catch (NullPointerException e)
        {
            logger.log(Level.SEVERE, "Couldn't configure logger", e);
        }
        logger.info("The Calculator has started its execution");
        try
        {
            OperationProcessor operationProcessor = new OperationProcessor("/Operations.txt", "/Configuration.properties");
            operationProcessor.executeOperations();
        }
        catch (CalculatorException e)
        {
            logger.log(Level.SEVERE, "Couldn't execute operations", e);
        }
    }
}
