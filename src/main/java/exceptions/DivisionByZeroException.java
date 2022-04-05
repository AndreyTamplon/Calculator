package exceptions;

public class DivisionByZeroException extends CalculatorException
{
    public DivisionByZeroException(String message)
    {
        super(message);
    }

    public DivisionByZeroException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
