package edu.hw8.Task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestHandler implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUFFER_SIZE = 1024;
    private static final String CLIENT = "Клиент: ";
    private static final String SERVER = "Сервер: ";
    private static final String NO_RESPONSE_MESSAGE = "Нет ответа на данное сообщение :(";
    private static final Map<String, String> RESPONSES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );
    private final SocketChannel client;
    private final Semaphore semaphore;

    public RequestHandler(SocketChannel client, Semaphore semaphore) {
        this.client = client;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try (Selector selector = Selector.open()) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            while (client.isOpen()) {
                selector.select();
                for (SelectionKey key : selector.keys()) {
                    if (key.isReadable()) {
                        processRequest();
                        client.close();
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        } finally {
            semaphore.release();
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    private void processRequest() {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int bytesRead = 0;
        try {
            bytesRead = client.read(buffer);
        } catch (IOException e) {
            LOGGER.info(e.getStackTrace());
        }
        if (bytesRead > 0) {
            String request = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(CLIENT + request);
            sendResponse(request);
        }
    }

    private void sendResponse(String request) {
        String responseMessage;
        responseMessage = RESPONSES.getOrDefault(
            request.trim(),
            NO_RESPONSE_MESSAGE
        );
        ByteBuffer response =
            ByteBuffer.wrap((SERVER
                + responseMessage).getBytes(StandardCharsets.UTF_8));
        while (response.hasRemaining()) {
            try {
                client.write(response);
            } catch (IOException e) {
                LOGGER.error(e.getStackTrace());
            }
        }
    }
}
