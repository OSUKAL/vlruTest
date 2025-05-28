package LogfileAnalysis;

import Models.LogRecord;
import Models.LogsAnalyzeResult;

import java.util.ArrayList;

/**
 * Анализатор списка записей логов
 */
public class LogAnalyzer {
    private static final short SUCCESS_STATUS = 200;

    /**
     * Анализ списка записей логов
     *
     * @param records          Список записей логов
     * @param maxTime          Максимальное время ответа
     * @param minAccessibility Минимальный уровень доступности
     */
    public LogsAnalyzeResult analyze(ArrayList<LogRecord> records, double maxTime, double minAccessibility) {
        var issuesCount = 0;

        for (LogRecord record : records) {
            if (record.status() != SUCCESS_STATUS || record.time() > maxTime)
                issuesCount++;

        }

        var accessibility = (1 - ((double) issuesCount / records.size())) * 100;
        if (accessibility > minAccessibility)
            return null;

        var interval = records.getFirst().interval();

        return new LogsAnalyzeResult(issuesCount, interval, accessibility);
    }

}
