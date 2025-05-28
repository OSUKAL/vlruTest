package LogfileAnalysis;

import Models.LogRecord;
import Models.LogsAnalyzeResult;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Анализатор списка записей логов
 */
public class LogAnalyzer {
    private static final Short[] FAILURE_STATUSES = new Short[] {500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 520, 521, 522, 523, 524, 525, 526};

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
            if (Arrays.asList(FAILURE_STATUSES).contains(record.status()) || record.time() > maxTime)
                issuesCount++;
        }

        var accessibility = (1 - ((double) issuesCount / records.size())) * 100;
        if (accessibility > minAccessibility)
            return null;

        var interval = records.getFirst().interval();

        return new LogsAnalyzeResult(issuesCount, interval, accessibility);
    }

}
