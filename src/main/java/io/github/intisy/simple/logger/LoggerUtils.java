package io.github.intisy.simple.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

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
    public static void response(SimpleLogger logger, HttpURLConnection response) throws IOException {
        BufferedReader errorIn = new BufferedReader(new InputStreamReader(response.getErrorStream()));
        String inputLine;
        StringBuilder errorResponse = new StringBuilder();
        while ((inputLine = errorIn.readLine()) != null) {
            errorResponse.append(inputLine);
        }
        errorIn.close();
        logger.note("Response Code: " + response.getResponseCode());
        logger.note("Response Body: " + errorResponse);
    }
}
