package edu.hw8.Task3;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParallelDatabaseHacker extends AbstractDatabaseHacker {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final int WORDS_PER_THREAD = 3;
    private final ConcurrentMap<String, String> leakedDatabase;
    private final ConcurrentMap<String, String> hackedData;
    private final ExecutorService executor;
    private final CountDownLatch latch;

    public ParallelDatabaseHacker(Map<String, String> leakedDatabase) {
        this.leakedDatabase = new ConcurrentHashMap<>(leakedDatabase);
        this.hackedData = new ConcurrentHashMap<>();
        this.executor = Executors.newCachedThreadPool();
        this.latch = new CountDownLatch(leakedDatabase.size());
    }

    @Override
    public Map<String, String> hack() {
        for (int wordLength = MIN_PASSWORD_LENGTH; wordLength <= MAX_PASSWORD_LENGTH; wordLength++) {
            for (int i = 0; i < ALF.length(); i += WORDS_PER_THREAD) {
                final int wordLengthCopy = wordLength;
                final int startIndex = i;
                executor.execute(() -> generatePasswords(wordLengthCopy, startIndex));
            }
        }
        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }
        return hackedData;
    }

    private void generatePasswords(int wordLength, int startIndex) {
        int[] index = new int[wordLength];
        Arrays.fill(index, startIndex);
        while (index[0] != (startIndex - WORDS_PER_THREAD) && !leakedDatabase.isEmpty()) {

            StringBuilder word = new StringBuilder();
            for (Integer integer : index) {
                word.append(alfCharacters.get(integer));
            }

            String generatedPass = word.toString();
            processGeneratedPassword(generatedPass);

            for (int i = index.length - 1; ; --i) {
                if (i < 0) {
                    return;
                }
                index[i]++;
                if (index[i] == alfCharacters.size()) {
                    index[i] = 0;
                } else {
                    break;
                }
            }

        }
    }

    private void processGeneratedPassword(String generatedPassword) {
        String md5Pass = DigestUtils.md5Hex(generatedPassword);
        if (isPasswordInLeakedDatabase(md5Pass)) {
            addToHackedData(leakedDatabase.get(md5Pass), generatedPassword);
            leakedDatabase.remove(md5Pass);
            latch.countDown();
        }
    }

    private boolean isPasswordInLeakedDatabase(String md5hex) {
        return leakedDatabase.containsKey(md5hex);
    }

    private void addToHackedData(String username, String password) {
        hackedData.putIfAbsent(username, password);
    }
}
