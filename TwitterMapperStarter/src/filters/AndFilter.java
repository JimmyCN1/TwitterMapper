package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
    private Filter child1;
    private Filter child2;

    public AndFilter(Filter child1, Filter child2) {
        this.child1 = child1;
        this.child2 = child2;
    }

    @Override
    public boolean matches(Status s) {
        return child1.matches(s) && child2.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> t = new ArrayList<>();
        t.addAll(child1.terms());
        t.addAll(child2.terms());
        return t;
    }

    public String toString() {
        return String.format("%s and %s", child1, child2);
    }
}
