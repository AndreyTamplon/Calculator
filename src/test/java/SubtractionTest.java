import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;
import operators.SubtractionOperator;
import operators.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator subtractionOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        subtractionOperator = new SubtractionOperator();
    }

    @Test
    @DisplayName("The right number of arguments and enough data in the stack")
    void correctNumberOfArgumentCorrectStackData() throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(1.0);
        deque.push(3.0);
        subtractionOperator.execute(executionContext, arguments);
        assertEquals(3.0 - 1.0, deque.peekFirst());
    }

    @Test
    @DisplayName("Wrong number of arguments and enough data in the stack")
    void wrongNumberOfArgumentCorrectStackData()
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(1.0);
        deque.push(3.0);
        arguments.add("7");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            subtractionOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Correct number of arguments and not enough data in the stack")
    void correctNumberOfArgumentWrongStackData()
    {
        executionContext.getDeque().push(1.0);
        assertThrows(NoRequiredDataInStackException.class, ()->
        {
            subtractionOperator.execute(executionContext, arguments);
        });
    }
}
