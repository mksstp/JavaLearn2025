package edu.basics4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    @SuppressWarnings("MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public static List<Animal> task1(List<Animal> animalList) {
        return animalList.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> task2(List<Animal> animalList, int limit) {
        return animalList.stream()
            .sorted((o1, o2) -> Integer.compare(o2.weight(), o1.weight()))
            .limit(limit)
            .toList();
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animalList) {
        return animalList.stream()
            .collect(groupingBy(
                Animal::type,
                collectingAndThen(counting(), Long::intValue)
            ));
    }

    public static Optional<Animal> task4(List<Animal> animalList) {
        return animalList.stream()
            .max(Comparator.comparingInt(o1 -> o1.name.length()));
    }

    public static Sex task5(List<Animal> animalList) {
        return animalList.stream()
            .map(a -> (a.sex() == Sex.M) ? 1 : -1)
            .reduce(0, Integer::sum) >= 0 ? Sex.M : Sex.F;
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animalList) {
        return animalList.stream()
            .collect(toMap(
                Animal::type,
                Function.identity(), (o1, o2) -> o1.weight() > o2.weight() ? o1 : o2
            ));
    }

    public static Optional<Animal> task7(List<Animal> animalList, int k) {
        return animalList.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .limit(k)
            .reduce((o1, o2) -> o2);
    }

    public static Optional<Animal> task8(List<Animal> animalList, int k) {
        return animalList.stream()
            .filter(o1 -> o1.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(List<Animal> animalList) {
        return animalList.stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task10(List<Animal> animalList) {
        return animalList.stream()
            .filter(o1 -> o1.age() != o1.paws())
            .toList();
    }

    public static List<Animal> task11(List<Animal> animalList) {
        final int MIN_HEIGHT = 100;
        return animalList.stream()
            .filter(o1 -> o1.bites() && o1.height() > MIN_HEIGHT)
            .toList();
    }

    public static Integer task12(List<Animal> animalList) {
        return animalList.stream()
            .filter(o1 -> o1.weight() > o1.height())
            .map(o -> 1)
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task13(List<Animal> animalList) {
        final int TWO_WORDS_BORDER = 2;
        return animalList.stream()
            .filter(o1 -> o1.name().split(" ").length > TWO_WORDS_BORDER)
            .toList();
    }

    public static boolean task14(List<Animal> animalList, int minHeight) {
        return animalList.stream()
            .anyMatch(o1 -> o1.type() == Type.DOG && o1.height() > minHeight);
    }

    public static Map<Animal.Type, Integer> task15(List<Animal> animalList, int lowBorder, int highBorder) {
        return animalList.stream()
            .filter(o1 -> o1.age() >= lowBorder && o1.age <= highBorder)
            .collect(groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    public static List<Animal> task16(List<Animal> animalList) {
        return animalList.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static Boolean task17(List<Animal> animalList) {
        if (animalList.stream().noneMatch(t -> t.type() == Animal.Type.SPIDER || t.type == Animal.Type.DOG)) {
            return false;
        }
        Double averageSpidersBites = animalList.stream()
            .filter(o -> o.type() == Type.SPIDER)
            .collect(Collectors.averagingDouble(o -> o.bites() ? 1 : 0));
        Double averageDogsBites = animalList.stream()
            .filter(o -> o.type() == Type.DOG)
            .collect(Collectors.averagingDouble(o -> o.bites() ? 1 : 0));
        return averageSpidersBites > averageDogsBites;
    }

    @SafeVarargs public static Optional<Animal> task18(List<Animal>... animalLists) {
        return Stream.of(animalLists)
            .flatMap(Collection::stream)
            .filter(o -> o.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animalList) {
        return animalList.stream()
            .filter(o -> !Validator.getErrors(o).isEmpty())
            .collect(Collectors.toMap(Animal::name, Validator::getErrors));
    }

    public static Map<String, String> task20(List<Animal> animalList) {
        return animalList.stream()
            .filter(o -> !Validator.getErrors(o).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                o -> Validator.getErrors(o).stream()
                    .map(ValidationError::getMessage)
                    .collect(Collectors.joining("\n"))
            ));
    }
}
