package operation_executor;
import exceptions.CalculatorException;
import exceptions.ParsingException;
import factory.Calculator;
import factory.SimpleCalculator;
import java.util.List;
import java.util.logging.Logger;

public class OperationProcessor
{
    private static final Logger logger = Logger.getLogger(OperationProcessor.class.getName());
    private String operationsFilePath;
    private final Calculator calculator;

    public OperationProcessor(String operationsFilePath, String configurationFileName) throws CalculatorException
    {

        this.operationsFilePath = operationsFilePath;
        try
        {
            calculator = new SimpleCalculator(configurationFileName);
        }
        catch (CalculatorException e)
        {
            throw new CalculatorException("Failed to create calculator");
        }
    }

    public void setOperationsFilePath(String operationsFilePath)
    {
        this.operationsFilePath = operationsFilePath;
    }

    public void executeOperations() throws CalculatorException
    {
        logger.info("Starting processing operations");
        OperationsFileParser operationsFileParser;
        try
        {
            operationsFileParser = new OperationsFileParser(operationsFilePath);
        }
        catch (ParsingException e)
        {
            throw new ParsingException("Couldn't parse file");
        }

        List<Operation> operationList = operationsFileParser.getOperationsList();
        for(Operation operation : operationList)
        {
            calculator.executeOperation(operation);
        }
    }
}
