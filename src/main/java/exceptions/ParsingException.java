package exceptions;

public class ParsingException extends CalculatorException
{
    public ParsingException(String message)
    {
        super(message);
    }

    public ParsingException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
