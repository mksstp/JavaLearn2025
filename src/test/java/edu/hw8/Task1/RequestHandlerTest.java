package edu.hw8.Task1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RequestHandlerTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Client and server basic test")
    public void run_shouldProcessRequestFromClient() {
        Client client = new Client(HOST, PORT);
        startServer();
        client.connect("личности");
        assertThat(outputStream.toString().replaceAll("\u0000", "").trim()).
            isEqualTo("Клиент: личности" + System.lineSeparator()
                + "Сервер: Не переходи на личности там, где их нет");
    }

    private void startServer() {
        Server server = new Server(HOST, PORT);
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.info(e.getStackTrace());
        }
    }

}
