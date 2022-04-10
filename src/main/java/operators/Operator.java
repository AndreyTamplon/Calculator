package operators;

import context.ExecutionContext;
import exceptions.*;

import java.util.List;

public interface Operator
{
    void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException;
}
