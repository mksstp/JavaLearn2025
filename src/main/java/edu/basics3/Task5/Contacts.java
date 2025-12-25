package edu.basics3.Task5;

public class Contacts {
    private final String name;
    private final String surname;

    public Contacts(String notProcessedContact) {
        if (notProcessedContact.contains(" ")) {
            name = notProcessedContact.split(" ")[0];
            surname = notProcessedContact.split(" ")[1];
        } else {
            name = notProcessedContact;
            surname = null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Contacts comparingObj = (Contacts) obj;
        if (this.surname == null) {
            if (comparingObj.surname == null) {
                return this.name.equals(comparingObj.name);
            }
            return false;
        }
        return this.name.equals(comparingObj.name) && this.surname.equals(comparingObj.surname);
    }

    @Override
    public int hashCode() {
        return (name.length() * 3 + surname.length() * 5);
    }

    public String getBaseName() {
        if (surname == null) {
            return name;
        }
        return surname;
    }
}
