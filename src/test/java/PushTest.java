import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.InvalidArgumentTypeException;
import exceptions.MismatchWithOperatorSignatureException;
import operators.Operator;
import operators.PushOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PushTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator pushOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        pushOperator = new PushOperator();
    }

    @Test
    @DisplayName("The correct number of arguments and the correct type of all of them")
    void correctNumberOfArgumentCorrectArgumentsType() throws CalculatorException
    {
        arguments.add("7");
        pushOperator.execute(executionContext, arguments);
        assertEquals(7.0, executionContext.getDeque().peekFirst());
    }

    @Test
    @DisplayName("The correct number of arguments and the wrong type of some of them")
    void correctNumberOfArgumentWrongArgumentsType()
    {
        arguments.add("WrongArgument");
        assertThrows(InvalidArgumentTypeException.class, ()->
        {
            pushOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("The wrong number of arguments and the correct type of all of them")
    void wrongNumberOfArgumentCorrectArgumentsType()
    {
        arguments.add("1.0");
        arguments.add("2.0");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            pushOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("The correct number of arguments and the correct type of all of them and argument is parameterized")
    void correctNumberOfArgumentCorrectArgumentsTypeParametrized() throws CalculatorException
    {
        executionContext.getParameters().put("a", 1.0);
        arguments.add("a");
        pushOperator.execute(executionContext, arguments);
        assertEquals(1.0, executionContext.getDeque().peekFirst());
    }

    @Test
    @DisplayName("Attempt to push a parameter that is not in the parameter list")
    void noSuchParameter()
    {
        executionContext.getParameters().put("b", 1.0);
        arguments.add("a");
        assertThrows(InvalidArgumentTypeException.class, ()->
        {
            pushOperator.execute(executionContext, arguments);
        });
    }


    @Test
    @DisplayName("Wrong name of parameter")
    void wrongParameterName()
    {
        executionContext.getParameters().put("¯\\_(ツ)_/¯", 1.0);
        arguments.add("¯\\_(ツ)_/¯");
        assertThrows(InvalidArgumentTypeException.class, ()->
        {
            pushOperator.execute(executionContext, arguments);
        });
    }
}
