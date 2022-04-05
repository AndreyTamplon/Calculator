package operation_executor;

import exceptions.ParsingException;
import exceptions.ResourceNotFoundException;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class OperationsFileParser
{
    private String operationsFilePath;
    private List<Operation> operationsList;
    private static final Logger logger = Logger.getLogger(OperationsFileParser.class.getName());
    public OperationsFileParser(String operationsFilePath) throws ParsingException
    {
        this.operationsFilePath = operationsFilePath;
        operationsList = new LinkedList<>();
        parseFile();
    }

    private List<String> readFile() throws ResourceNotFoundException
    {
        logger.info("Reading a file");
        List<String> text = new LinkedList<>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                OperationsFileParser.class.getResourceAsStream(
                                        operationsFilePath)))))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                text.add(line);
            }
        }
        catch (IOException | NullPointerException exception)
        {
            throw new ResourceNotFoundException("The program failed to open the resource");
        }
        logger.fine("File successfully read");
        return text;
    }

    public List<Operation> getOperationsList()
    {
        return operationsList;
    }

    public void setOperationsFilePath(String operationsFilePath)
    {
        this.operationsFilePath = operationsFilePath;
    }

    public void parseFile() throws ParsingException
    {
        logger.info("Starting to process a file with operations");
        List<String> text;
        try
        {
            text = readFile();
        }
        catch (ParsingException e)
        {
            throw new ParsingException("The file could not be parsed because there was a problem with opening it");
        }
        for(String line : text)
        {
            String[] tokens = line.split(" ");
            if(tokens.length < 1)
            {
                logger.warning("Empty line in operations list");
            }
            else if(tokens.length == 1)
            {
                operationsList.add(new Operation(tokens[0]));
            }
            else
            {
                operationsList.add(new Operation(tokens[0], Arrays.asList(tokens).subList(1, tokens.length)));
            }
        }
        logger.fine("Operations successfully parsed");
    }
}
