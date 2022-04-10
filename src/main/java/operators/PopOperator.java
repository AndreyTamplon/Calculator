package operators;

import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;

import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PopOperator implements Operator
{
    private static final Logger logger = Logger.getLogger(PopOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        if (!arguments.isEmpty())
        {
            throw new MismatchWithOperatorSignatureException(0, arguments.size());
        }
        Deque<Double> deque = executionContext.getDeque();
        Double result;
        if(deque.isEmpty())
        {
            throw new NoRequiredDataInStackException(1, 0);
        }
        else
        {
            result = deque.pop();
        }
        logger.log(Level.FINE, "The Pop is performed correctly and removed {0} from stack", result);
    }
}
