package edu.basics2.Task3;

import edu.basics2.Task3.ConnectionManager.DefaultConnectionManager;
import edu.basics2.Task3.ConnectionManager.FaultyConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int MAX_ATTEMPTS = 3;

    private Main() {
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        LOGGER.info("Hello and welcome!");
        PopularCommandExecutor testDefault = new PopularCommandExecutor(new DefaultConnectionManager(), MAX_ATTEMPTS);
        PopularCommandExecutor testFaulty = new PopularCommandExecutor(new FaultyConnectionManager(), MAX_ATTEMPTS);
        testDefault.updatePackages();
        testFaulty.updatePackages();

    }
}
