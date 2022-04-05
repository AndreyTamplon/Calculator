package exceptions;


import java.util.Arrays;

public class MismatchWithOperatorSignatureException extends CalculatorException
{
    public MismatchWithOperatorSignatureException(String message)
    {
        super(message);
    }

    public MismatchWithOperatorSignatureException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public MismatchWithOperatorSignatureException(Integer numberOfArgumentsRequired, Integer numberOfArgumentsProvided)
    {
        super("Operator requires " + numberOfArgumentsRequired + " arguments, but " + numberOfArgumentsProvided + " are provided");
    }

}
