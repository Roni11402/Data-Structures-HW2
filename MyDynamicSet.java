/**
 * @param <T> The type of the satellite data of the elements in the dynamic-set.
 */
public class MyDynamicSet<T> {
    private MySortedLinkedList set;


    /**
     * The constructor should initiate an empty dynamic-set.
     *
     * @param N The maximum number of elements in the dynamic set at each time.
     */
    public MyDynamicSet(int N) {
        this.set = new MySortedLinkedList();
    }

    public Element<T> search(int k) {
        if (set.head() == null) {
            return null;
        } else {
            ListLink currElement = set.head();
            while (currElement != null) {
                if (currElement.key() == k) {
                    return (Element<T>) currElement;
                } else if (currElement.key() > k) {
                    return null;
                } else {
                    currElement = currElement.getNext();
                }
            }
        }
        return null;
    }

    public void insert(Element<T> x) {
        ListLink newX = new ListLink(x.key(), x.satelliteData());
        this.set.insert(newX);
    }

    public void delete(Element<T> x) {
        this.set.delete((ListLink) x);
    }

    public Element<T> minimum() {
        return set.head();
    }

    public Element<T> maximum() {
        return set.tail();
    }

    public Element<T> successor(Element<T> x) {
        ListLink newX = (ListLink) x;
        return newX.getNext();
    }

    public Element<T> predecessor(Element<T> x) {
        ListLink newX = (ListLink) x;
        return newX.getPrev();
    }
}
