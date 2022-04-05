package operators;

import context.ExecutionContext;
import exceptions.*;

import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(PrintOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        if (!arguments.isEmpty())
        {
            throw new MismatchWithOperatorSignatureException(0, arguments.size());
        }
        Deque<Double> deque = executionContext.getDeque();
        if (deque.isEmpty())
        {
            throw new NoRequiredDataInStackException(1, 0);
        }
        System.out.println(deque.peekFirst());
        logger.log(Level.FINE, "The Print is performed correctly. Value {0} was printed", deque.peekFirst());
    }
}