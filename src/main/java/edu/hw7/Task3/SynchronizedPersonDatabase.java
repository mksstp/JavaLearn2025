package edu.hw7.Task3;

import java.util.HashMap;
import org.jetbrains.annotations.Nullable;

public class SynchronizedPersonDatabase implements PersonDatabaseInterface {

    public synchronized void add(Person person) {
        personById.put(person.id(), person);
        personByName.put(person.name(), person);
        personByAddress.put(person.address(), person);
        personByPhone.put(person.phoneNumber(), person);
    }

    public synchronized void delete(int id) {
        Person person = personById.get(id);

        personById.remove(id);
        personByName.remove(person.name(), person);
        personByAddress.remove(person.address(), person);
        personByPhone.remove(person.phoneNumber(), person);
    }

    @Nullable
    public synchronized Person findByName(String name) {
        return personByName.get(name);
    }

    @Nullable
    public synchronized Person findByAddress(String address) {
        return personByAddress.get(address);
    }

    @Nullable
    public synchronized Person findByPhone(String phone) {
        return personByPhone.get(phone);
    }


    private final HashMap<Integer, Person> personById = new HashMap<>();
    private final HashMap<String, Person> personByName = new HashMap<>();
    private final HashMap<String, Person> personByAddress = new HashMap<>();
    private final HashMap<String, Person> personByPhone = new HashMap<>();
}
