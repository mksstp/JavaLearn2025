package edu.project1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DictionaryTest {

    private static final String FILENAME = "russianWords.txt";
    private static final String UNCORRECTFILENAME = "Words.txt";
    private static final int LENGTH_WORD = 10;

    @Test
    @DisplayName("Проверка поиска слова подходящей длины")
    void findWordCorrectLength() {
        // given
        Dictionary dictionary = new Dictionary(FILENAME);
        // when
        String word = dictionary.getRandomWord(LENGTH_WORD, LENGTH_WORD);
        // then
        Assertions.assertThat(word).isEqualTo("реальность");
    }

    @Test
    @DisplayName("Проверка наличия файла")
    void fileExistence() {
        // given
        // when
        // then
        Assertions.assertThatThrownBy(() ->
            new Dictionary(UNCORRECTFILENAME)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Проверка поведения при отсутствии слов заданной длины")
    void emptyListOfCorrectWords() {
        // given
        Dictionary dictionary = new Dictionary(FILENAME);
        // when
        // then
        Assertions.assertThatThrownBy(() ->
            dictionary.getRandomWord(2, 4)).isInstanceOf(RuntimeException.class);
    }

}
