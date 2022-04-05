package operators;

import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;

import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiplicationOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(MultiplicationOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        if (!arguments.isEmpty())
        {
            throw new MismatchWithOperatorSignatureException(0, arguments.size());
        }
        Deque<Double> deque = executionContext.getDeque();
        if (deque.size() < 2)
        {
            throw new NoRequiredDataInStackException(2, deque.size());
        }
        Double firstOperand = deque.pop();
        Double secondOperand = deque.pop();
        Double result = firstOperand * secondOperand;
        deque.push(result);
        logger.log(Level.FINE, "The Multiplication is performed correctly with the result {0}", result);
    }
}
