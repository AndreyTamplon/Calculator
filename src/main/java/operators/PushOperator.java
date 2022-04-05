package operators;

import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.InvalidArgumentTypeException;
import exceptions.MismatchWithOperatorSignatureException;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import static org.apache.commons.lang3.math.NumberUtils.createDouble;
import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class PushOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(PushOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        if (arguments.size() != 1)
        {
            throw new MismatchWithOperatorSignatureException(1, arguments.size());
        }
        Deque<Double> deque = executionContext.getDeque();
        Map<String, Double> parameters = executionContext.getParameters();
        Double result;
        if(isParsable(arguments.get(0)))
        {
            result = createDouble(arguments.get(0));
        }
        else if(arguments.get(0).matches("[a-zA-Z_]+") && parameters.containsKey(arguments.get(0)))
        {
            result = parameters.get(arguments.get(0));
        }
        else
        {
            throw new InvalidArgumentTypeException("Double", "Something else");
        }
        deque.push(result);
        logger.log(Level.FINE, "The Push is performed correctly and put {0} on stack", result);
    }
}
