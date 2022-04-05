package exceptions;

public class NoRequiredDataInStackException extends CalculatorException
{
    public NoRequiredDataInStackException(String message)
    {
        super(message);
    }

    public NoRequiredDataInStackException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NoRequiredDataInStackException(Integer numberOfElementsInStackRequired, Integer numberOfElementsInStackProvided)
    {
        super("For correct operation, it is required at least " + numberOfElementsInStackRequired + " elements in stack, but " +
                numberOfElementsInStackProvided + " are provided");
    }


}
