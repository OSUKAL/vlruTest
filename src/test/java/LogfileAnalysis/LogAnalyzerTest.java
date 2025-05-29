package LogfileAnalysis;

import Models.LogRecord;
import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Тесты анализатора списка данных логов
 *
 * <p>Возвращает результат анализа интервала записи логов если уровень доступности НИЖЕ (<) переданного значения доступности
 *
 * @see Infrastructure.Args
 */
class LogAnalyzerTest {

    /**
     * Анализ валидного списка данных логов
     *
     * <p>Проверка счетчика несоответствий условиям. Раздельная и одновременная проверка статус кода и времени записи
     *
     * <p>Проверка вычисления доступности
     */
    @Test
    void analyzeResultOutputLogs() {
        var logs = new ArrayList<LogRecord>();
        logs.add(new LogRecord("16:47:07", (short)200, 42.12313));
        logs.add(new LogRecord("16:47:07", (short)567, 36.87432));
        logs.add(new LogRecord("16:47:07", (short)350, 65.465));
        logs.add(new LogRecord("16:47:07", (short)200, 33.33613));
        logs.add(new LogRecord("16:47:07", (short)200, 658.336411));
        logs.add(new LogRecord("16:47:07", (short)500, 76.131276));
        logs.add(new LogRecord("16:47:07", (short)200, 26.761276));
        logs.add(new LogRecord("16:47:07", (short)200, 22.411336));

        var analyzer = new LogAnalyzer();
        var actual = analyzer.analyze(logs, 70.2, 70);

        var expected = new LogsAnalyzeResult(3, "16:47:07", 62.5);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Анализ невалидного списка данных логов
     *
     * <p>Проверка поведения при пограничном значении доступности (полное совпадение)
     */
    @Test
    void analyzeNullOutputLogs() {
        var logs = new ArrayList<LogRecord>();
        logs.add(new LogRecord("16:47:07", (short)200, 42.12313));
        logs.add(new LogRecord("16:47:07", (short)567, 36.87432));
        logs.add(new LogRecord("16:47:07", (short)350, 65.465));
        logs.add(new LogRecord("16:47:07", (short)200, 33.33613));
        logs.add(new LogRecord("16:47:07", (short)200, 658.336411));
        logs.add(new LogRecord("16:47:07", (short)500, 16.131276));
        logs.add(new LogRecord("16:47:07", (short)200, 26.761276));
        logs.add(new LogRecord("16:47:07", (short)200, 22.411336));

        var analyzer = new LogAnalyzer();
        var actual = analyzer.analyze(logs, 45, 50.0);

        Assertions.assertNull(actual);
    }
}