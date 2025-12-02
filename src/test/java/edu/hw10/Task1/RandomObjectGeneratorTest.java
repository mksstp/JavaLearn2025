package edu.hw10.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Генерация объекта через фабричный метод")
    void testFabricMethod() {
        // Given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // When
        MyClass myObject = rog.nextObject(MyClass.class, "create");

        // Then
        assertTrue(myObject.getMyInt() >= -100 && myObject.getMyInt() <= 100);
        assertTrue(myObject.getMyDouble() >= -100 && myObject.getMyDouble() <= 100);
        assertEquals(myObject.getMyString(), "fabricString");
        assertTrue(myObject.getMyBoolean());
    }

    @Test
    @DisplayName("Генерация объекта через конструктор")
    void testConstructor() {
        // Given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // When
        MyClass myObject = rog.nextObject(MyClass.class);

        // Then
        assertTrue(myObject.getMyInt() >= -100 && myObject.getMyInt() <= 100);
        assertTrue(myObject.getMyDouble() >= -100 && myObject.getMyDouble() <= 100);
        assertNotNull(myObject.getMyString());
        assertNotNull(myObject.getMyBoolean());
    }

    @Test
    @DisplayName("Генерация объекта через record")
    void testRecordGeneration() {
        // Given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // When
        MyRecord myRecord = rog.nextObject(MyRecord.class);

        // Then
        assertTrue(myRecord.myInt() >= 1 && myRecord.myInt() <= 100);
        assertTrue(myRecord.myDouble() >= -100 && myRecord.myDouble() <= 100);
        assertNotNull(myRecord.myString());
        assertNotNull(myRecord.myBoolean());
    }
}
