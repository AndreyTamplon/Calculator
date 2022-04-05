import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;
import operators.Operator;
import operators.PopOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PopTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator popOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        popOperator = new PopOperator();
    }

    @Test
    @DisplayName("The right number of arguments and enough data in the stack")
    public void correctNumberOfArgumentCorrectStackData() throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(1.0);
        Double firstElement = deque.peekFirst();
        popOperator.execute(executionContext, arguments);
        assertNotEquals(firstElement, deque.peekFirst());
    }

    @Test
    @DisplayName("Wrong number of arguments and enough data in the stack")
    public void wrongNumberOfArgumentCorrectStackData()
    {
        executionContext.getDeque().push(1.0);
        arguments.add("7");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            popOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Attempt to remove an element from an empty stack")
    public void EmptyStack()
    {
        assertThrows(NoRequiredDataInStackException.class, ()->
        {
            popOperator.execute(executionContext, arguments);
        });
    }
}
