package wildepizza.com.github.simplelogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class Logger {
    private final int logLevel;
    private int percent = 0;
    private final boolean enablePercent;
    private long lastTime = 0;
    private final long startTime;
    String major;
    String last = "";
    private final boolean enableShortLog;
    final List<String> logs = new ArrayList<>();
    public Logger(int logLevel, boolean enableShortLog, boolean enablePercent) {
        this.enablePercent = enablePercent;
        this.logLevel = logLevel;
        this.enableShortLog = enableShortLog;
        this.startTime = System.currentTimeMillis();
    }
    public void setPercent(int percent) {
        this.percent = percent;
    }
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public void addPercent(int percent) {
        this.percent += percent;
    }
    public int getPercent() {
        return this.percent;
    }
    public void error(Object log) {
        System.out.println(LogColor.RED.apply(String.valueOf(log)));
    }
    public void exception(Exception exception) {
        warning(
                "Cause:" + exception.getCause().toString()
                        + "\nMessage" + exception.getMessage()
                        + "\nStacktrace:" + Arrays.toString(exception.getStackTrace())
        );
    }
    public void horrible(Object log) {horrible(log, "");}
    public void horrible(Object log, String message) {
        if (log instanceof Exception)
            log = "Cause:" + ((Exception) log).getCause().toString()
                    + "\nMessage" + ((Exception) log).getMessage()
                    + "\nStacktrace:" + Arrays.toString(((Exception) log).getStackTrace());
        System.out.println(LogColor.RED.apply(message + log));
        System.exit(0);
    }
    public void debug(Object log) {
        if (logLevel >= 5)
            log(LogColor.WHITE.apply(String.valueOf(log)), false);
    }
    public void note(Object log) {
        if (logLevel >= 4)
            log(LogColor.GRAY.apply(String.valueOf(log)), false);
    }
    public void success(Object log) {
        if (logLevel >= 3)
            log(LogColor.GREEN.apply(String.valueOf(log)), false);
    }
    public void warning(Object log) {
        if (logLevel >= 2)
            log(LogColor.YELLOW.apply(String.valueOf(log)), false);
    }
    public void major(Object log) {
        if (logLevel >= 1)
            log(LogColor.BLUE_BACKGROUND.apply(String.valueOf(log)), true);
    }
    public void log(Object log, boolean m) {
        if (enablePercent)
            if (lastTime != 0)
                log = "(" + ((int) ((double) (System.currentTimeMillis()-startTime)/lastTime*100)) + "%) " + log.toString();
            else
                log = "(" + percent + "%) " + log.toString();
        if (!last.equals(String.valueOf(log))) {
            last = String.valueOf(log);
            if (enableShortLog)
                System.out.println(LogColor.WHITE.apply(String.valueOf(log)));
            else {
                if (m)
                    major = String.valueOf(log);
                else
                    logs.add(String.valueOf(log));
                List<String> finalLogs = logs;
                if (major != null)
                    if (finalLogs.size() > 12) {
                        if (finalLogs.size() > 13)
                            logs.remove(0);
                        finalLogs.set(finalLogs.size() - 13, major);
                    } else
                        finalLogs.set(0, major);
                for (String v : finalLogs)
                    System.out.println(v);
            }
        }
    }
}
