
public class MySecondDataStructure {

    private Product[] array;
    private Product[] mostExpensive;
    private int size;
    private int[] priceRaise;
    private int[] qualityCount;

    /***
     * This function is the Init function.
     * @param N The maximum number of elements in the data structure at each time.
     */
    public MySecondDataStructure(int N) {
        this.array = new Product[N];
        this.mostExpensive = new Product[6];
        this.priceRaise = new int[6];
        this.qualityCount = new int[6];
        for (int i = 0; i < 6; i++) {
            priceRaise[i] = 0;
            qualityCount[i] = 0;
            mostExpensive[i] = null;
        }
        this.size = 0;

    }

    public void insert(Product product) {
        this.array[size] = product;
        qualityCount[product.quality()] = qualityCount[product.quality()] + 1;
        size = size + 1;
        if (mostExpensive[product.quality()] != null) {
            if (product.price() > mostExpensive[product.quality()].price()) {
                mostExpensive[product.quality()] = product;
            }
        } else {
            mostExpensive[product.quality()] = product;
        }
    }

    public void findAndRemove(int id) {
        Product toRemove = null;
        for (int i = 0; i < size; i++) {
            if (array[i].id() == id) {
                toRemove = array[i];
                qualityCount[toRemove.quality()] = qualityCount[toRemove.quality()] - 1;
                array[i] = array[size - 1];
                size = size - 1;
                if (toRemove.price() == mostExpensive[toRemove.quality()].price()) {
                    Product newMax = null;
                    for (int j = 0; j < size; j++) {
                        if (newMax == null && array[j].quality() == toRemove.quality()) {
                            newMax = array[j];
                        } else if (array[j].quality() == toRemove.quality() && array[j].price() > newMax.price()) {
                            newMax = array[j];
                        }
                    }
                    mostExpensive[toRemove.quality()] = newMax;
                }
            }
        }

    }


    public int medianQuality() {
        if (size == 0) {
            return -1;
        }
        int sum = 0;
        int median = 0;
        while (median < 6 && sum < (size / 2) + 1) {
            sum = sum + qualityCount[median];
            median = median + 1;
        }
        return median - 1;
    }


    public double avgQuality() {
        if (size == 0) {
            return -1;
        }
        double avg = 0;
        for (int i = 0; i < 6; i++) {
            avg = avg + (qualityCount[i] * i);
        }
        return avg / size;
    }

    public void raisePrice(int raise, int quality) {
        this.priceRaise[quality] = priceRaise[quality] + raise;
    }

    public Product mostExpensive() {
        Product max = null;
        for (int i = 0; i < 6; i++) {
            if (mostExpensive[i] != null) {
                if (max == null) {
                    max = mostExpensive[i];
                } else if (mostExpensive[i].price() + priceRaise[i] > max.price() + priceRaise[max.quality()]) {
                    max = mostExpensive[i];
                }
            }
        }
        return max;
    }

}
