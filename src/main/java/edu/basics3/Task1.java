package edu.basics3;

public final class Task1 {
    private Task1() {
    }

    private static char processingLetter(char letter) {
        char newChar;
        if (Character.isUpperCase(letter)) {
            newChar = (char) (('Z' - letter) + 'A');
        } else {
            newChar = (char) (('z' - letter) + 'a');
        }
        return newChar;
    }

    public static String atbash(String message) {
        if ((message == null) || (message.isEmpty())) {
            throw new IllegalArgumentException("Пустая строка !");
        }
        StringBuilder encryptedMessage = new StringBuilder();
        for (char letter : message.toCharArray()) {
            if (Character.isLetter(letter)) {
                encryptedMessage.append(processingLetter(letter));
            } else {
                encryptedMessage.append(letter);
            }

        }
        return encryptedMessage.toString();
    }
}
