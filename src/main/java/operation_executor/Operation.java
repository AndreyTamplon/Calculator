package operation_executor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Operation
{
    private String operatorName;
    private List<String> arguments;

    public Operation(String operatorName, List<String> arguments)
    {
        this.operatorName = operatorName;
        this.arguments = arguments;
    }

    public Operation(String operatorName)
    {
        this.operatorName = operatorName;
        this.arguments = Collections.emptyList();
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public void setOperatorName(String operatorName)
    {
        this.operatorName = operatorName;
    }

    public List<String> getArguments()
    {
        return arguments;
    }

    public void setArguments(List<String> arguments)
    {
        this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Operation operation = (Operation) o;
        return Objects.equals(operatorName, operation.operatorName) && Objects.equals(arguments, operation.arguments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(operatorName, arguments);
    }
}
