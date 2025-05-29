package Integration;

import LogfileAnalysis.LogfileAnalyzeHandler;
import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Тесты обработчика лог файла
 */
class LogfileAnalyzeHandlerTest {
    /**
     * Обработка лог файла с возвратом списка результатов анализа
     */
    @ParameterizedTest
    @MethodSource("logfilesProvider")
    void handle_output(File logfile, double maxTime, double minAccessibility, ArrayList<LogsAnalyzeResult> expected) {
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, maxTime, minAccessibility);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> logfilesProvider() {
        var correctExpected = new LogsAnalyzeResult[]{
                new LogsAnalyzeResult(2, "16:48:08", 95.83333333333334),
                new LogsAnalyzeResult(3, "16:48:09", 95.23809523809523)
        };
        return Stream.of(
                arguments(
                        new File("src/test/TestLogfiles/handlerTest.log"),
                        45.0,
                        96,
                        new ArrayList<>(Arrays.asList(correctExpected))
                ),
                arguments(
                        new File("src/test/TestLogfiles/handlerTest.log"),
                        45.0,
                        80,
                        new ArrayList<LogsAnalyzeResult>()
                ),
                arguments(
                        new File("src/test/TestLogfiles/nonexistent.log"),
                        45.0,
                        94.9,
                        null
                )
        );
    }
}