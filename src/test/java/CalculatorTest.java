import exceptions.CouldNotConfigureException;
import factory.Calculator;
import factory.SimpleCalculator;
import operation_executor.Operation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest
{
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Calculator calculator;
    private Operation operation;
    private Operation printOperation;
    private List<String> arguments;

    @BeforeAll
    static void setUpEnvironment()
    {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void initAll() throws CouldNotConfigureException
    {
        calculator = new SimpleCalculator("/Configuration.properties");
        arguments = new LinkedList<>();
        printOperation = new Operation("PRINT", Collections.emptyList());
    }

    @Test
    @DisplayName("Testing calculators ability to create operators and execute it")
    void createAndExecuteOperator()
    {
        arguments.add("7");
        operation = new Operation("PUSH", arguments);
        calculator.executeOperation(operation);
        calculator.executeOperation(printOperation);
        assertEquals("7.0\r\n", outContent.toString());
    }
}
