package edu.basics2.Task3.ConnectionManager;

import edu.basics2.Task3.Connection.Connection;
import edu.basics2.Task3.Connection.FaultyConnection;
import edu.basics2.Task3.Connection.StableConnection;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final double FAULTY_CONNECTION_PROBABILITY = 0.7;

    @Override
    public Connection getConnection() {
        boolean isFaultyConnection = new Random().nextDouble() <= FAULTY_CONNECTION_PROBABILITY;
        if (isFaultyConnection) {
            LOGGER.info("Faulty connection found");
            return new FaultyConnection();
        } else {
            LOGGER.info("Stable connection found");
            return new StableConnection();
        }
    }
}
