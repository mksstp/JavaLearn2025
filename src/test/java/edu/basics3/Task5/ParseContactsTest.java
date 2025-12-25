package edu.basics3.Task5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class ParseContactsTest {

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInputASC() {
        // given
        String[] noProcessedContacts = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};

        // when
        Contacts[] resultContacts = ParseContacts.parseContacts(noProcessedContacts, "ASC");
        // then
        Assertions.assertThat(resultContacts).isEqualTo(new Contacts[] {new Contacts("Thomas Aquinas"),
            new Contacts("Rene Descartes"), new Contacts("David Hume"),
            new Contacts("John Locke")});
    }

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInputDESC() {
        // given
        String[] noProcessedContacts = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};

        // when
        Contacts[] resultContacts = ParseContacts.parseContacts(noProcessedContacts, "DESC");
        // then
        Assertions.assertThat(resultContacts).isEqualTo(new Contacts[] {new Contacts("Carl Gauss"),
            new Contacts("Leonhard Euler"), new Contacts("Paul Erdos")});
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testUncorrectInput(String[] argument) {
        Assertions.assertThat(
            ParseContacts.parseContacts(argument, "ASC")).isEqualTo(new Contacts[] {});
    }

}
