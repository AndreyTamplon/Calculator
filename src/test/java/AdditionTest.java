import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;
import operators.AdditionOperator;
import operators.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator additionOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        additionOperator = new AdditionOperator();
    }

    @Test
    @DisplayName("The right number of arguments and enough data in the stack")
    void correctNumberOfArgumentCorrectStackData() throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(1.0);
        deque.push(1.0);
        additionOperator.execute(executionContext, arguments);
        assertEquals(1.0 + 1.0, deque.peekFirst());
    }

    @Test
    @DisplayName("Wrong number of arguments and enough data in the stack")
    void wrongNumberOfArgumentCorrectStackData()
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(1.0);
        deque.push(1.0);
        arguments.add("7");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            additionOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Correct number of arguments and not enough data in the stack")
    void correctNumberOfArgumentWrongStackData()
    {
        executionContext.getDeque().push(1.0);
        assertThrows(NoRequiredDataInStackException.class, ()->
        {
            additionOperator.execute(executionContext, arguments);
        });
    }
}
