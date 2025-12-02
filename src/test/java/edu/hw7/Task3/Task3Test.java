package edu.hw7.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

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

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);

            // Assert
            assertEquals(person1, personDatabase.findByName("Anna"));
            assertEquals(person1, personDatabase.findByAddress("Pushkina 12"));
            assertEquals(person1, personDatabase.findByPhone("+72282282288"));
            assertNull(personDatabase.findByName("Alexey"));
        }

        private void testBody2(Supplier<PersonDatabaseInterface> constructor) {
            // Arrange
            PersonDatabaseInterface personDatabase = constructor.get();
            Person person1 = new Person(1, "Anna", "Pushkina 12", "+72282282288");
            Person person2 = new Person(2, "Andrey", "Lenina 13", "+71112223344");

            // Act
            personDatabase.add(person1);
            personDatabase.add(person2);
            personDatabase.delete(1);

            // Assert
            assertNull(personDatabase.findByPhone("+72282282288"));
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
