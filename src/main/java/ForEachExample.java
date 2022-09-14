import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ForEachExample {
    private static final int MAGIC_NUMBER = 10;

    public static void main(String[] args) {
        List<Integer> numbers = wannaBeJava8Way();
        System.out.println(numbers);
    }

    private static List<Integer> oldSchoolWay() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            numbers.add(someHeavyTask(i));
        }
        return numbers;
    }

    private static List<Integer> wannaBeJava8Way() {
        List<Integer> numbers = new ArrayList<>();

        IntStream.range(0, MAGIC_NUMBER)
                .map(i -> someHeavyTask(i))
                .forEachOrdered(i -> numbers.add(i));

        return numbers;
    }

    private static int someHeavyTask(int i) {
        return i; // very heavy task...
    }
}
