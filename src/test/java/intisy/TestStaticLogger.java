package intisy;

import io.github.intisy.simple.logger.LogLevel;
import io.github.intisy.simple.logger.LoggerBuilder;
import io.github.intisy.simple.logger.SimpleLogger;
import io.github.intisy.simple.logger.StaticLogger;
import org.junit.jupiter.api.Test;

public class TestStaticLogger {
    @Test
    public void test() {
        String message = "test";
        SimpleLogger logger = new LoggerBuilder().withLogLevel(LogLevel.DEBUG).build();
        StaticLogger.debug(message);
        logger.debug(message);
        StaticLogger.error(message);
        StaticLogger.warning(message);
        StaticLogger.major(message);
        StaticLogger.note(message);
        StaticLogger.printStackTrace(message);
    }
}
