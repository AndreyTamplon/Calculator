package factory;

import context.ExecutionContext;
import exceptions.InvalidOperator;
import operation_executor.Operation;
import operators.Operator;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Calculator
{
    private static final Logger logger = Logger.getLogger(Calculator.class.getName());
    protected ExecutionContext executionContext;

    protected Calculator()
    {
        executionContext = new ExecutionContext();
    }

    public void executeOperation(Operation operation)
    {
        String operatorName = operation.getOperatorName();
        logger.log(Level.INFO, "Executing {0}", operatorName);
        try
        {
            Operator operator = createOperator(operatorName);
            logger.fine(String.format("%s successfully created", operatorName));
            operator.execute(executionContext, operation.getArguments());
        }
        catch (InvalidOperator e)
        {
            logger.log(Level.SEVERE, String.format("Failed to create an %s due to", operatorName), e);
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, String.format("Failed to execute an %s due to", operatorName), e);
        }

    }
    protected abstract Operator createOperator(String operatorName)
            throws InvalidOperator;
}
