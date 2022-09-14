import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class AdvancedCollectors {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Sara", 20),
                new Person("Sara", 22),
                new Person("Sara", 20),
                new Person("Paula", 32),
                new Person("Paul", 32),
                new Person("Jack", 2),
                new Person("Jack", 72),
                new Person("Jill", 12)
        );

        /*
            for each name get all ages:
                Sara -> [20, 22, 20],
                Paula -> [32],
                Paul -> [32],
                Jack -> [2, 72]
                Jill -> [12]
         */
        maxExample(people);
    }

    private static void imperativeWay(List<Person> people) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (Person person : people) {
            List<Integer> ages = null;

            if (map.containsKey(person.getName())) {
                ages = map.get(person.getName());
            } else {
                ages = new ArrayList<>();
            }

            ages.add(person.getAge());
            map.put(person.getName(), ages);
        }

        System.out.println(map);
    }

    private static void declarativeWayUsingCollectors(List<Person> people) {
        Map<String, List<Person>> map = people.stream()
                .collect(groupingBy(Person::getName));

        System.out.println(map);
    }

    private static void declarativeWayUsingCollectorsV2(List<Person> people) {
        Map<String, List<Integer>> map = people.stream()
                .collect(groupingBy(Person::getName, mapping(Person::getAge, toList())));

        System.out.println(map);
    }

    private static void countingWithRepetitionsExample(List<Person> people) {
        Map<String, Long> map = people.stream()
                .collect(groupingBy(Person::getName, counting()));

        System.out.println(map);
    }

    private static void countingWithoutRepetitionsExample(List<Person> people) {
        Map<String, Integer> map = people.stream()
                .collect(
                        groupingBy(
                                Person::getName,
                                collectingAndThen(
                                        mapping(Person::getAge, toSet()),
                                        Set::size
                                )
                        )
                );

        System.out.println(map);
    }

    private static void maxExample(List<Person> people) {
        Map<String, Optional<Person>> map = people.stream()
                .collect(groupingBy(Person::getName, maxBy(comparing(Person::getAge))));

        System.out.println(map);
    }

    private static void sumExample(List<Person> people) {
        Map<String, Integer> map = people.stream()
                .collect(groupingBy(Person::getName, summingInt(Person::getAge)));

        System.out.println(map);
    }


    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
