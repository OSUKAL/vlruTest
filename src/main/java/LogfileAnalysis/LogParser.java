package LogfileAnalysis;

import Models.LogRecord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер для строк лог файла
 */
public class LogParser {
    /** Регулярное выражение для парсинга времени записи лога */
    private static final Pattern intervalRegex = Pattern.compile("(:\\d{2}){3}\\s");
    /** Регулярное выражение для парсинга кода состояния лога */
    private static final Pattern statusRegex = Pattern.compile("\\s\\d{3}\\s");
    /** Регулярное выражение для парсинга времени ответа лога */
    private static final Pattern timeRegex = Pattern.compile("\\s\\d+\\.\\d+\\s\"|\\s\\d+\\s\"");

    /**
     * Парсинг строки лог файла
     *
     * @param line Строка лог файла
     */
    public LogRecord parse(String line) {
        try {
            Matcher recordIntervalRaw = intervalRegex.matcher(line);
            Matcher recordStatusRaw = statusRegex.matcher(line);
            Matcher recordTimeRaw = timeRegex.matcher(line);

            if (!recordIntervalRaw.find() || !recordStatusRaw.find() || !recordTimeRaw.find())
                return null;

            var recordInterval = recordIntervalRaw.group().substring(1, recordIntervalRaw.group().length() - 1);
            var recordStatus = Short.parseShort(recordStatusRaw.group().substring(1, recordStatusRaw.group().length() - 1));
            var recordTime = Double.parseDouble(recordTimeRaw.group().substring(1, recordTimeRaw.group().length() - 1));

            return new LogRecord(recordInterval, recordStatus, recordTime);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}