import context.ExecutionContext;
import exceptions.CalculatorException;
import exceptions.MismatchWithOperatorSignatureException;
import exceptions.NoRequiredDataInStackException;
import exceptions.ViolationOfFunctionDomainException;
import operators.Operator;
import operators.SQRTOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class SQRTTest
{
    private ExecutionContext executionContext;
    private List<String> arguments;
    private Operator SQRTOperator;

    @BeforeEach
    public void initAll()
    {
        executionContext = new ExecutionContext();
        arguments = new LinkedList<>();
        SQRTOperator = new SQRTOperator();
    }

    @Test
    @DisplayName("The right number of arguments and enough data in the stack")
    void correctNumberOfArgumentCorrectStackData() throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(3.0);
        SQRTOperator.execute(executionContext, arguments);
        assertEquals(sqrt(3.0), deque.peekFirst());
    }

    @Test
    @DisplayName("Wrong number of arguments and enough data in the stack")
    void wrongNumberOfArgumentCorrectStackData()
    {
        Deque<Double> deque = executionContext.getDeque();
        deque.push(3.0);
        arguments.add("7");
        assertThrows(MismatchWithOperatorSignatureException.class, ()->
        {
            SQRTOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Correct number of arguments and not enough data in the stack")
    void correctNumberOfArgumentWrongStackData()
    {
        assertThrows(NoRequiredDataInStackException.class, ()->
        {
            SQRTOperator.execute(executionContext, arguments);
        });
    }

    @Test
    @DisplayName("Extracting the square root of a negative number")
    void negativeNumberRoot()
    {
        executionContext.getDeque().push(-1.0);
        assertThrows(ViolationOfFunctionDomainException.class, ()->
        {
            SQRTOperator.execute(executionContext, arguments);
        });
    }
}
