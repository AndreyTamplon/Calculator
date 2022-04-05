package operators;

import context.ExecutionContext;
import exceptions.*;

import java.io.*;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintOperator implements Operator<ExecutionContext, List<String>>
{
    private static final Logger logger = Logger.getLogger(PrintOperator.class.getName());
    @Override
    public void execute(ExecutionContext executionContext, List<String> arguments) throws CalculatorException
    {
        Deque<Double> deque = executionContext.getDeque();
        if (arguments.isEmpty())
        {
            if (deque.isEmpty())
            {
                throw new NoRequiredDataInStackException(1, 0);
            }
            System.out.println(deque.peekFirst());
        }
        else if(arguments.size() == 1)
        {
            File file = new File(arguments.get(0));
            if(file.isFile())
            {
                try(FileWriter out = new FileWriter(file))
                {
                    out.append(deque.peekFirst().toString());
                } catch (IOException e)
                {
                    throw new OutputFileNotFound("Couldn't print result in provided file, because it wasn't found");
                }
            }
        }
        else
        {
            throw new MismatchWithOperatorSignatureException(String.format("Operator requires 0 or 1 arguments, but %d are provided", arguments.size()));
        }
        logger.log(Level.FINE, "The Print is performed correctly. Value {0} was printed", deque.peekFirst());
    }
}