package exceptions;

public class InvalidOperator extends CalculatorException
{
    public InvalidOperator(String message)
    {
        super(message);
    }

    public InvalidOperator(String message, Throwable e)
    {
        super(message, e);
    }
}
