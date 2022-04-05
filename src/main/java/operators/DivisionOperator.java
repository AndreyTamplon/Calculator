package operators;

import context.ExecutionContext;
import exceptions.*;

import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DivisionOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(DivisionOperator.class.getName());
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
        if(secondOperand == 0)
        {
            throw new DivisionByZeroException(String.format("Attempt to divide %f by zero", firstOperand));
        }
        Double result = firstOperand / secondOperand;
        deque.push(result);
        logger.log(Level.FINE, "The Division is performed correctly with the result {0}", result);
    }
}