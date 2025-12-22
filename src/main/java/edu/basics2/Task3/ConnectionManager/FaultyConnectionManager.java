package edu.basics2.Task3.ConnectionManager;

import edu.basics2.Task3.Connection.Connection;
import edu.basics2.Task3.Connection.FaultyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Connection getConnection() {
        LOGGER.info("Faulty connection found");
        return new FaultyConnection();
    }
}
