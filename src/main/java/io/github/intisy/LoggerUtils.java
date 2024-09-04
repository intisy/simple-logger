package io.github.intisy;

public class LoggerUtils {
    public static String exceptionToString(Exception exception) {
        return exceptionToString(exception, null);
    }
    public static String exceptionToString(Exception exception, String message) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(message == null ? "" : message);
        messageBuilder.append("Cause: ").append(exception.getCause());
        messageBuilder.append("\nMessage: ").append(exception.getMessage());
        messageBuilder.append("\nStacktrace: ");
        for (StackTraceElement element: exception.getStackTrace()) {
            messageBuilder.append("\n   ").append(element.toString());
        }
        message = messageBuilder.toString();
        return message;
    }
}
