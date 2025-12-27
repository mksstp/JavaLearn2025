package edu.basics4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnimalTest {

    Animal dog_one = new Animal("dog_one", Animal.Type.DOG, Animal.Sex.M, 7, 60, 10000, true);
    Animal dog_two = new Animal("dog_two", Animal.Type.DOG, Animal.Sex.M, 12, 120, 15000, true);
    Animal dog_three = new Animal("dog_three", Animal.Type.DOG, Animal.Sex.F, 11, 105, 12000, false);
    Animal cat_one = new Animal("cat_one", Animal.Type.CAT, Animal.Sex.M, 5, 40, 5000, false);
    Animal cat_two = new Animal("cat_two", Animal.Type.CAT, Animal.Sex.F, 2, 40, 4000, false);
    Animal bird_one = new Animal("bird_one bird_one", Animal.Type.BIRD, Animal.Sex.F, 2, 15, 100, false);
    Animal bird_two = new Animal("bird_two bird_two bird_two", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 5, false);
    Animal fish_one = new Animal("fish_one", Animal.Type.FISH, Animal.Sex.M, 3, 20, 300, false);
    Animal fish_two = new Animal("fish_two", Animal.Type.FISH, Animal.Sex.M, 25, 7000, 10000000, false);
    Animal uncorrect_fish = new Animal("uncorrect_fish", Animal.Type.FISH, Animal.Sex.F, -2, 0, 10000000, true);
    Animal spider_one = new Animal("spider_one", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 30, true);

    @Test
    @DisplayName("Проверка вызова Task1")
    void task1Test() {
        // given
        List<Animal> listAnimal = List.of(cat_two, bird_one, dog_one, fish_one);
        // when
        List<Animal> processedList = Animal.task1(listAnimal);
        // then
        Assertions.assertThat(processedList).isEqualTo(List.of(bird_one, fish_one, cat_two, dog_one));
    }

    @Test
    @DisplayName("Проверка вызова Task2")
    void task2Test() {
        //given
        List<Animal> listAnimal = List.of(cat_two, bird_one, dog_one, fish_one);
        //when
        List<Animal> processedList = Animal.task2(listAnimal, 2);
        //then
        Assertions.assertThat(processedList).isEqualTo(List.of(dog_one, cat_two));
    }

    @Test
    @DisplayName("Проверка вызова Task3")
    void task3Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, dog_one, cat_two);
        //when
        Map<Animal.Type, Integer> processedList = Animal.task3(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(new HashMap<>() {{
            put(Animal.Type.CAT, 2);
            put(Animal.Type.DOG, 1);
        }});
    }

    @Test
    @DisplayName("Проверка вызова Task4")
    void task4Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, cat_two, bird_one, spider_one);
        //when
        var processedList = Animal.task4(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(Optional.of(bird_one));
    }

    @Test
    @DisplayName("Проверка вызова Task5")
    void task5Test() {
        //given
        List<Animal> listAnimal = List.of(cat_two, dog_one, bird_one);
        //when
        var processedList = Animal.task5(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Проверка вызова Task6")
    void task6Test() {
        //given
        List<Animal> listAnimal = List.of(cat_two, cat_one, dog_one);
        //when
        var processedList = Animal.task6(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(new HashMap<>() {{
            put(Animal.Type.CAT, cat_one);
            put(Animal.Type.DOG, dog_one);
        }});
    }

    @Test
    @DisplayName("Проверка вызова Task7")
    void task7Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, cat_two, dog_one, fish_one, spider_one);
        //when
        var processedList = Animal.task7(listAnimal, 2);
        //then
        Assertions.assertThat(processedList).isEqualTo(Optional.of(cat_one));
    }

    @Test
    @DisplayName("Проверка вызова Task8")
    void task8Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, cat_two, dog_one);
        //when
        var processedList = Animal.task8(listAnimal, 60);
        //then
        Assertions.assertThat(processedList).isEqualTo(Optional.of(cat_one));
    }

    @Test
    @DisplayName("Проверка вызова Task9")
    void task9Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, cat_two, bird_one, spider_one);
        //when
        var processedList = Animal.task9(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(18);
    }

    @Test
    @DisplayName("Проверка вызова Task10")
    void task10Test() {
        //given
        List<Animal> listAnimal = List.of(bird_one, cat_one);
        //when
        var processedList = Animal.task10(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(List.of(cat_one));
    }

    @Test
    @DisplayName("Проверка вызова Task11")
    void task11Test() {
        //given
        List<Animal> listAnimal = List.of(bird_one, cat_one, dog_two, dog_three);
        //when
        var processedList = Animal.task11(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(List.of(dog_two));
    }

    @Test
    @DisplayName("Проверка вызова Task12")
    void task12Test() {
        //given
        List<Animal> listAnimal = List.of(bird_two, cat_one, dog_two, dog_three);
        //when
        var processedList = Animal.task12(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка вызова Task13")
    void task13Test() {
        //given
        List<Animal> listAnimal = List.of(bird_one, cat_one, dog_two, dog_three, bird_two);
        //when
        var processedList = Animal.task13(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(List.of(bird_two));
    }

    @Test
    @DisplayName("Проверка вызова Task14")
    void task14Test() {
        //given
        List<Animal> listAnimal = List.of(bird_one, cat_one, dog_two, dog_three, bird_two);
        //when
        var processedList = Animal.task14(listAnimal, 115);
        //then
        Assertions.assertThat(processedList).isEqualTo(true);

    }

    @Test
    @DisplayName("Проверка вызова Task15")
    void task15Test() {
        //given
        List<Animal> listAnimal = List.of(bird_one, cat_one, dog_two, cat_two, dog_one);
        //when
        var processedList = Animal.task15(listAnimal, 5, 7);
        //then
        Assertions.assertThat(processedList).isEqualTo(Map.of(Animal.Type.DOG, 10000, Animal.Type.CAT, 5000));
    }

    @Test
    @DisplayName("Проверка вызова Task16")
    void task16Test() {
        //given
        List<Animal> listAnimal = List.of(cat_one, cat_two, dog_two, bird_two, dog_one, dog_three);
        //when
        var processedList = Animal.task16(listAnimal);
        //then
        Assertions.assertThat(processedList)
            .isEqualTo(List.of(cat_one, cat_two, dog_one, dog_two, dog_three, bird_two));
    }

    @Test
    @DisplayName("Проверка вызова Task17")
    void task17Test() {
        //given
        List<Animal> listAnimal = List.of(spider_one, dog_two, dog_three);
        //when
        var processedList = Animal.task17(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка вызова Task18")
    void task18Test() {
        //given
        //when
        var processedList = Animal.task18(
            List.of(cat_one, cat_two),
            List.of(fish_one, dog_two),
            List.of(bird_two, dog_one, dog_three, fish_two)
        );
        //then
        Assertions.assertThat(processedList).isEqualTo(Optional.of(fish_two));
    }

    @Test
    @DisplayName("Проверка вызова Task19")
    void task19Test() {
        //given
        List<Animal> listAnimal = List.of(uncorrect_fish, fish_two);
        //when
        var processedList = Animal.task19(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(new HashMap<String, Set<ValidationError>>() {{
            put(
                uncorrect_fish.name(), new HashSet<>() {{
                    add(new ValidationError("Animal can't have negative age"));
                    add(new ValidationError("Animal can't have negative or zero height"));
                }}
            );
        }});
    }

    @Test
    @DisplayName("Проверка вызова Task20")
    void task20Test() {
        //given
        List<Animal> listAnimal = List.of(uncorrect_fish, fish_two);
        //when
        var processedList = Animal.task20(listAnimal);
        //then
        Assertions.assertThat(processedList).isEqualTo(new HashMap<String, String>() {{
            put(uncorrect_fish.name(), "Animal can't have negative age\nAnimal can't have negative or zero height");
        }});
    }

}
