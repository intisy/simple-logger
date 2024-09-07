package intisy;

import io.github.intisy.StaticLogger;
import org.junit.jupiter.api.Test;

public class TestStaticLogger {
    @Test
    public void test() {
        String message = "test";
        StaticLogger.debug(message);
        StaticLogger.error(message);
        StaticLogger.warning(message);
        StaticLogger.major(message);
        StaticLogger.note(message);
        StaticLogger.printStackTrace(message);
    }
}
