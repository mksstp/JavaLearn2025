package edu.project1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserWordTest {

    @Test
    @DisplayName("Проверка маскировки слова")
    void testMaskWord() {
        // given
        String word = "проверка";
        char[] wordAnswer = {'*', '*', '*', '*', '*', '*', '*', '*'};
        // when
        UserWord userWord = new UserWord(word);
        // then
        Assertions.assertThat(userWord.getUserWord()).isEqualTo(wordAnswer);
    }

    @Test
    @DisplayName("Встреча одной буквы несколько раз в слове")
    void openManyLetter() {
        // given
        String word = "проверка";
        char[] wordAnswer = {'*', 'р', '*', '*', '*', 'р', '*', '*'};
        // when
        UserWord userWord = new UserWord(word);
        userWord.openLetter("р");
        // then
        Assertions.assertThat(userWord.getUserWord()).isEqualTo(wordAnswer);
    }

}
