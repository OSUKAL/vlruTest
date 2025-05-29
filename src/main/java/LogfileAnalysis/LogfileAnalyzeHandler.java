package LogfileAnalysis;

import Models.LogRecord;
import Models.LogsAnalyzeResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Обработчик анализа лог файлов по заданным параметрам
 */
public class LogfileAnalyzeHandler {
    private final LogAnalyzer logAnalyzer;
    private final LogParser parser;

    /**
     * Обработчик анализа лог файлов
     */
    public LogfileAnalyzeHandler() {
        this.logAnalyzer = new LogAnalyzer();
        this.parser = new LogParser();
    }

    /**
     * Обработка анализа лог файла
     *
     * @param logfile          Лог файл
     * @param maxTime          Максимальное время ответа
     * @param minAccessibility Минимальный уровень доступности
     */
    public ArrayList<LogsAnalyzeResult> handle(File logfile, double maxTime, double minAccessibility) {
        try (var reader = new BufferedReader(new FileReader(logfile))) {
            String line;

            var results = new ArrayList<LogsAnalyzeResult>();
            var intervalLogs = new ArrayList<LogRecord>();
            String interval = null;

            while ((line = reader.readLine()) != null) {
                var record = parser.parse(line);
                if (record == null)
                    continue;

                if (interval == null) {
                    interval = record.interval();
                    intervalLogs.add(record);
                    continue;
                }

                if (record.interval().compareTo(interval) != 0) {
                    var result = logAnalyzer.analyze(intervalLogs, maxTime, minAccessibility);
                    if (result != null)
                        results.add(result);

                    intervalLogs.clear();
                    interval = record.interval();
                }

                intervalLogs.add(record);
            }
            var result = logAnalyzer.analyze(intervalLogs, maxTime, minAccessibility);
            if (result != null)
                results.add(result);

            return results;

        } catch (IOException exception) {
            return null;
        }
    }
}
