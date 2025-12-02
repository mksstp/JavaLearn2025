package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUFFER_SIZE = 1024;
    private static final List<String> CLIENT_MESSAGES = List.of("личности", "оскорбления", "глупый", "интеллект");
    private final InetSocketAddress hostAddress;

    public Client(String host, int port) {
        this.hostAddress = new InetSocketAddress(host, port);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void connect(String messageToSend) {
        try (SocketChannel server = SocketChannel.open(hostAddress)) {
            ByteBuffer buffer = ByteBuffer.wrap(messageToSend.getBytes(StandardCharsets.UTF_8));
            while (buffer.hasRemaining()) {
                server.write(buffer);
            }
            buffer.flip();
            buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int bytesRead;
            while ((bytesRead = server.read(buffer)) != -1) {
                if (bytesRead == 0) {
                    continue;
                }
                System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
                break;
            }
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    private static String getRandomMessage() {
        Random random = ThreadLocalRandom.current();
        return CLIENT_MESSAGES.get(random.nextInt(CLIENT_MESSAGES.size()));
    }
}
