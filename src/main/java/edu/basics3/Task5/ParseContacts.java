package edu.basics3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParseContacts {
    private ParseContacts() {
    }

    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    public static Contacts[] parseContacts(String[] notProcessedContacts, String sortMethod) {
        if (notProcessedContacts == null) {
            return new Contacts[] {};
        }
        if (!sortMethod.equals(ASC) && !sortMethod.equals(DESC)) {
            throw new IllegalArgumentException("Некорректный тип сортировки!");
        }
        List<Contacts> contactsList = new ArrayList<>();
        for (String contact : notProcessedContacts) {
            contactsList.add(new Contacts(contact));
        }

        if (sortMethod.equals(ASC)) {
            contactsList.sort(Comparator.comparing(Contacts::getBaseName));
        } else {
            contactsList.sort(Comparator.comparing(Contacts::getBaseName).reversed());
        }
        return contactsList.toArray(new Contacts[notProcessedContacts.length]);
    }
}
