package exceptions;

public class InvalidArgumentTypeException extends CalculatorException
{
    public InvalidArgumentTypeException(String message)
    {
        super(message);
    }

    public InvalidArgumentTypeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidArgumentTypeException(String requiredType, String providedType)
    {
        super(requiredType + " is Required, but " + providedType + " was provided");
    }
}
