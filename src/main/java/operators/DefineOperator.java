package operators;

import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.InvalidArgumentTypeException;
import exceptions.MismatchWithOperatorSignatureException;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang3.math.NumberUtils.createDouble;
import static org.apache.commons.lang3.math.NumberUtils.isParsable;

public class DefineOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(DefineOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        if (arguments.size() != 2)
        {
            throw new MismatchWithOperatorSignatureException(2, arguments.size());
        }
        if(arguments.get(0).matches("[a-zA-Z_]+") && isParsable(arguments.get(1)))
        {
            Map<String, Double> parameters = executionContext.getParameters();
            parameters.put(arguments.get(0), createDouble(arguments.get(1)));
        }
        else
        {
            if(!arguments.get(0).matches("[a-zA-Z_]+"))
            {
                throw new InvalidArgumentTypeException("Parameter as string consisting of uppercase and uppercase letters, as well as a symbol _ ", "Something else");
            }
            throw new InvalidArgumentTypeException("Value as real number", "Something else");
        }
        logger.log(Level.FINE, String.format("The Define is performed correctly and created parameter %s with the value %s", arguments.get(0), arguments.get(1)));
    }
}
