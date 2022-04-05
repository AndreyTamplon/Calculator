package operators;

import exceptions.*;

public interface Operator<U, A>
{
    void execute(U executionContext, A arguments) throws CalculatorException;
}
