import java.util.Comparator;

public class BreadthFirstComparator implements Comparator<StateNode> {
    @Override
    public int compare(StateNode x, StateNode y)
    {
        if (x.getNumber() < y.getNumber())
        {
            return -1;
        }
        if (x.getNumber() > y.getNumber())
        {
            return 1;
        }
        return 0;
    }
}