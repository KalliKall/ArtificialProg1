import java.util.Comparator;

public class BreadthFirstComparator implements Comparator<String> {
    @Override
    public int compare(String x, String y)
    {
        if (x.length() < y.length())
        {
            return -1;
        }
        if (x.length() > y.length())
        {
            return 1;
        }
        return 0;
    }
}