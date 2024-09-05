package io.github.intisy;

public class StaticLogger {
    private static final SimpleLogger logger = new SimpleLogger();
    public static void debug(Object string) {
        debug(string, true);
    }
    public static void debug(Object string, boolean log) {
        if (log)
            logger.debug(string);
    }
    public static void note(Object string) {
        logger.note(string);
    }
    public static void success(Object string) {
        logger.success(string);
    }
    public static void warning(Object string) {
        logger.warning(string);
    }
    public static void major(Object string) {
        logger.major(string);
    }
}
