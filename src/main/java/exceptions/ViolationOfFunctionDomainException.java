package exceptions;

public class ViolationOfFunctionDomainException extends CalculatorException
{
    public ViolationOfFunctionDomainException(String message)
    {
        super(message);
    }

    public ViolationOfFunctionDomainException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
