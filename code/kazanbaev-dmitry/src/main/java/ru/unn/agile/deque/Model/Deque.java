package deque.Model;

import java.util.ArrayList;
import java.util.List;

public class Deque<T> {
    private final List<T> list;

    public Deque() {
        list = new ArrayList<>();
    }

    public Deque(final List<T> list) {
        this.list = list;
    }
}
