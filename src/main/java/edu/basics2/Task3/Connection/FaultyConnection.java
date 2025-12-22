package edu.basics2.Task3.Connection;

import edu.basics2.Task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final double SUCCESS_PROBABILITY = 0.3;

    @Override
    public void execute(String command) throws ConnectionException {
        LOGGER.info("Pending connection : {}", this);

        boolean isStable = new Random().nextDouble() <= SUCCESS_PROBABILITY;
        if (isStable) {
            LOGGER.info("Executed command:\n{}", command);
        } else {
            throw new ConnectionException();
        }

    }

    @Override
    public void close() {
        LOGGER.info("Connection {} is closed.", this);
    }
}
