package edu.basics7.Task3;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Nested
    @DisplayName("Checking raceCondition on PersonDatabases")
    class raceCondition {

        private void testBody(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            AtomicInteger errorsCount = new AtomicInteger(0);
            final int triesCount = 10000;

            // Act
            for (int i = 0; i < triesCount; i++) {

                PersonDatabaseInterface personDatabase = constructor.get();
                Thread adder = new Thread(() -> {
                    Person person1 = new Person(1, "Anna", "Pushkina 12", "+72282282288");
                    Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");

                    personDatabase.add(person1);
                    personDatabase.add(person2);
                });

                Thread checker1 = new Thread(() -> {

                    if (personDatabase.findByName("Andrey") != null) {
                        if (personDatabase.findByAddress("Lenina 13") == null) {
                            errorsCount.incrementAndGet();
                        }
                    }
                });

                Thread checker2 = new Thread(() -> {
                    if (personDatabase.findByAddress("Pushkina 12") != null) {
                        if (personDatabase.findByPhone("+72282282288") == null) {
                            errorsCount.incrementAndGet();
                        }
                    }
                });

                adder.start();
                checker1.start();
                checker2.start();
                try {
                    adder.join();
                    checker1.join();
                    checker2.join();
                } catch (InterruptedException e) {
                    Logger.getLogger("myLogger").info("Error while multithreading");
                }
            }

            // Assert
            assertEquals(0, errorsCount.get());
        }

        @Test
        void synchronizedPersonDatabaseTest() {
            testBody(SynchronizedPersonDatabase::new);
        }

        @Test
        void ReadWriteLockPersonDatabaseTest() {
            testBody(RWLPersonDatabase::new);
        }
    }

    @Nested
    @DisplayName("Checking PersonDatabases correctness")
    class personDatabaseCorrectnessTests {

        private void testBody1(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            PersonDatabaseInterface personDatabase = constructor.get();
            Person person1 = new Person(1, "Anna", "Pushkina 12", "+72282282288");
            Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");
            Person person3 = new Person(3, "Anna", "Lenina 13", "+71112223344");
            Person person4 = new Person(4, "Sergey", "Pushkina 12", "+71112223344");

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);
            personDatabase.add(person3);
            personDatabase.add(person4);

            // Assert
            assertEquals(List.of(person1, person3), personDatabase.findByName("Anna"));
            assertEquals(List.of(person1, person4), personDatabase.findByAddress("Pushkina 12"));
            assertEquals(List.of(person2, person3, person4), personDatabase.findByPhone("+71112223344"));
            assertEquals(Collections.EMPTY_LIST, personDatabase.findByName("Alexey"));
        }

        private void testBody2(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            PersonDatabaseInterface personDatabase = constructor.get();
            Person person1 = new Person(1, "Anna", "Pushkina 12", "+72282282288");
            Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");
            Person person3 = new Person(3, "Anna", "Lenina 13", "+71112223344");
            Person person4 = new Person(4, "Sergey", "Pushkina 12", "+71112223344");

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);
            personDatabase.add(person3);
            personDatabase.add(person4);
            personDatabase.delete(1);

            // Assert
            assertEquals(Collections.EMPTY_LIST, personDatabase.findByPhone("+72282282288"));
        }

        @Test
        void synchronizedPersonDatabaseTest1() {
            testBody1(SynchronizedPersonDatabase::new);
        }

        @Test
        void ReadWriteLockPersonDatabaseTest1() {
            testBody1(RWLPersonDatabase::new);
        }

        @Test
        void synchronizedPersonDatabaseTest2() {
            testBody2(SynchronizedPersonDatabase::new);
        }

        @Test
        void ReadWriteLockPersonDatabaseTest2() {
            testBody2(RWLPersonDatabase::new);
        }
    }
}
