import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.DivisionByZeroException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;
import operators.DivisionOperator;
import operators.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator divisionOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        divisionOperator = new DivisionOperator();
    }

    @Test
    @DisplayName("The right number of arguments and enough data in the stack")
    void correctNumberOfArgumentCorrectStackData() throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(2.0);
        deque.push(7.0);
        divisionOperator.execute(executionContext, arguments);
        assertEquals(7.0 / 2.0, deque.peekFirst());
    }

    @Test
    @DisplayName("Wrong number of arguments and enough data in the stack")
    void wrongNumberOfArgumentCorrectStackData()
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(2.0);
        deque.push(7.0);
        arguments.add("7");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            divisionOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Correct number of arguments and not enough data in the stack")
    void correctNumberOfArgumentWrongStackData()
    {
        executionContext.getDeque().push(7.0);
        assertThrows(NoRequiredDataInStackException.class, ()->
        {
            divisionOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Division by zero")
    void divisionByZero()
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(0.0);
        deque.push(7.0);
        assertThrows(DivisionByZeroException.class, ()->
        {
            divisionOperator.execute(executionContext, arguments);
        });
    }
}
