import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.InvalidArgumentTypeException;
import exceptions.MismatchWithOperatorSignatureException;
import operators.DefineOperator;
import operators.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefineTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator defineOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        defineOperator = new DefineOperator();
    }

    @Test
    @DisplayName("The correct number of arguments and the correct type of all of them")
    void correctNumberOfArgumentCorrectArgumentsType() throws CalculatorException
    {
        arguments.add("a");
        arguments.add("1.0");
        defineOperator.execute(executionContext, arguments);
        assertEquals(1.0, executionContext.getParameters().get("a"));
    }

    @Test
    @DisplayName("The wrong number of arguments and the correct type of all of them")
    void wrongNumberOfArgumentWrongArgumentsType()
    {
        arguments.add("a");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            defineOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Wrong name of parameter")
    void wrongParameterName()
    {
        arguments.add("( ͡° ͜ʖ ͡°)");
        arguments.add("1.0");
        assertThrows(InvalidArgumentTypeException.class, ()->
        {
            defineOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Wrong type of value")
    void wrongArgumentType()
    {
        arguments.add("a");
        arguments.add("_6");
        assertThrows(InvalidArgumentTypeException.class, ()->
        {
            defineOperator.execute(executionContext, arguments);
        });
    }
}
