package context;

import java.util.*;

public class ExecutionContext
{
    private final Deque<Double> deque;
    private final Map<String, Double> parameters;

    public ExecutionContext()
    {
        deque = new ArrayDeque<>();
        parameters = new HashMap<>();
    }

    public Deque<Double> getDeque()
    {
        return deque;
    }

    public Map<String, Double> getParameters()
    {
        return parameters;
    } // возможно стоит удалить
}
