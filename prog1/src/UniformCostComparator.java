import java.util.Comparator;

public class UniformCostComparator implements Comparator<StateNode> {
    @Override
    public int compare(StateNode x, StateNode y)
    {
        if (x.getPathCost() < y.getPathCost())
        {
            return -1;
        }
        if (x.getPathCost() > y.getPathCost())
        {
            return 1;
        }
        return 0;
    }
}
