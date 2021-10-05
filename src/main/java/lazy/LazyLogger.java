package lazy;

import java.util.logging.Logger;

public class LazyLogger {
    private static final Logger logger = Logger.getLogger(LazyLogger.class.getName());

    private static String getLogMessage() {
        System.out.println("Inside getLogMessage");
        return "log message";
    }

    public static void main(String[] args) {
        logger.fine(LazyLogger::getLogMessage);
    }
}
