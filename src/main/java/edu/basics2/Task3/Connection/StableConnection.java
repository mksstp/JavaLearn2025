package edu.basics2.Task3.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Pending connection : {}", this);
        LOGGER.info("Executed command:\n{}", command);
    }

    @Override
    public void close() {
        LOGGER.info("Connection {} is closed.", this);
    }
}
