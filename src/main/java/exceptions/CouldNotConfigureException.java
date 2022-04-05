package exceptions;


public class CouldNotConfigureException extends CalculatorException
{
    public CouldNotConfigureException(String message)
    {
        super(message);
    }

    public CouldNotConfigureException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
