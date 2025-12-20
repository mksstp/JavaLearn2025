package edu.basics2.Task3;

import edu.basics2.Task3.ConnectionManager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    private final static Logger LOGGER = LogManager.getLogger();

    private final ConnectionManager manager;
    private final int maxAttempts;
    private ConnectionException exception = null;

    public void updatePackages() {
        var possibleException = tryExecute("apt update && apt upgrade -y");
        if (possibleException != null) {
            throw possibleException;
        } else {
            LOGGER.info("Packages have been successful update!");
        }
    }

    ConnectionException tryExecute(String command) {
        var connection = manager.getConnection();

        for (int currAttempt = 1; currAttempt <= maxAttempts; currAttempt++) {

            LOGGER.info("Attempt {}.Trying to connect...\n", currAttempt);

            try (connection) {
                connection.execute(command);
            } catch (ConnectionException e) {
                LOGGER.info("Connection failed!");
                if (exception == null) {
                    exception = e;
                } else {
                    exception.addSuppressed(e);
                }
                continue;
            } catch (Exception e) {
                LOGGER.info("Other exception..");
                continue;
            }

            return null;
        }
        return exception;
    }
}
