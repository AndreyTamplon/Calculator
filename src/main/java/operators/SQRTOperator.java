package operators;

import context.ExecutionContext;
import exceptions.*;

import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.sqrt;

public class SQRTOperator implements Operator
{
    private static final Logger logger = Logger.getLogger(SQRTOperator.class.getName());
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
        Double operand = deque.pop();
        if(operand < 0)
        {
            throw new ViolationOfFunctionDomainException(String.format("Attempt to take square root of %f", operand));
        }
        Double result = sqrt(operand);
        deque.push(result);
        logger.log(Level.FINE, "The SQRT is performed correctly with the result {0}", result);
    }
}