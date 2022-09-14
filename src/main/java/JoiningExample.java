import java.util.List;
import java.util.stream.Collectors;

public class JoiningExample {
    /*
        Given a list of strings, print the strings separated by a comma
        i.e. ["Galaxy", "is", "the", "best"] --> Galaxy, is, the, best
     */

    private static final List<String> WORDS = List.of("Galaxy","is", "the", "best");

    public static void main(String[] args) {
        declarativeWithCollectors(WORDS);
    }

    private static void imperativeWay(List<String> words) {
        for (String word : words) {  // use a shortcut!
            System.out.println(word + ", "); // oops...
        }
    }

    private static void imperativeWaySecondTry(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));

            if (i != words.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    private static void declarativeWithCollectors(List<String> words) {
        String sentence = words.stream()
                .collect(Collectors.joining(", "));

        System.out.println(sentence);
    }
}
