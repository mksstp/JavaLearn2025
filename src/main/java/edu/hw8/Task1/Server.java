package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_THREADS = 8;
    private static final int MAX_CONNECTIONS = 3;
    private final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS, true);
    private final InetSocketAddress address;

    public Server(String host, int port) {
        this.address = new InetSocketAddress(host, port);
    }

    public void start() {
        try (Selector selector = Selector.open();
             ServerSocketChannel channel = ServerSocketChannel.open()) {
            channel.configureBlocking(false);
            channel.socket().bind(address);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (channel.isOpen()) {
                selector.select();
                for (SelectionKey key : selector.keys()) {
                    if (semaphore.tryAcquire()) {
                        if (key.isAcceptable()) {
                            startExecution(channel.accept());
                        }
                        selector.selectedKeys().remove(key);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    private void startExecution(SocketChannel client) {
        executor.execute(new RequestHandler(client, semaphore));
    }
}
