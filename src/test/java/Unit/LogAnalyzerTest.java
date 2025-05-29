package Unit;

import LogfileAnalysis.LogAnalyzer;
import Models.LogRecord;
import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Тесты анализатора списка данных логов
 */
class LogAnalyzerTest {
    /**
     * Проверка анализа списка логов
     *
     * @see LogAnalyzer
     */
    @ParameterizedTest
    @MethodSource({"logRecordsProvider"})
    void analyze_output(ArrayList<LogRecord> logs, double maxTime, double minAccessibility, LogsAnalyzeResult expected) {
        var analyzer = new LogAnalyzer();
        var actual = analyzer.analyze(logs, maxTime, minAccessibility);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> logRecordsProvider() {
        var logsArray = new LogRecord[]{
                new LogRecord("16:47:07", (short) 200, 42.12313),
                new LogRecord("16:47:07", (short) 567, 36.87432),
                new LogRecord("16:47:07", (short) 350, 65.465),
                new LogRecord("16:47:07", (short) 200, 33.33613),
                new LogRecord("16:47:07", (short) 200, 658.336411),
                new LogRecord("16:47:07", (short) 500, 16.131276),
                new LogRecord("16:47:07", (short) 200, 26.761276),
                new LogRecord("16:47:07", (short) 200, 22.411336)
        };

        var logs = new ArrayList<>(Arrays.asList(logsArray));

        return Stream.of(
                arguments(logs, 70.2, 70, new LogsAnalyzeResult(3, "16:47:07", 62.5)),
                arguments(logs, 45, 50, null)
        );
    }
}