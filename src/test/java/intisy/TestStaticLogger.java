package intisy;

import io.github.intisy.simple.logger.LogLevel;
import io.github.intisy.simple.logger.LoggerBuilder;
import io.github.intisy.simple.logger.SimpleLogger;
import io.github.intisy.simple.logger.StaticLogger;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestStaticLogger {
    @Test
    public void test() {
        String message = "test";
        SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).withLogFolder(new File("logs")).build();
        StaticLogger.debug(message);
        logger.debug(message);
        StaticLogger.error(message);
        logger.error(message);
        StaticLogger.warning(message);
        logger.warning(message);
        StaticLogger.major(message);
        logger.major(message);
        StaticLogger.note(message);
        logger.note(message);
        StaticLogger.printStackTrace();
        logger.printStackTrace();
    }

    @Test
    public void testShort() {
        SimpleLogger logger = new SimpleLogger();
        logger.setEnableDuplicateLog(true);
        logger.setEnableShortLog(true);
        logger.setLogLevel(LogLevel.DEBUG);
        logger.major("TEST");
        for (int i = 0; i < 20; i++)
            logger.debug(i);
    }
}
