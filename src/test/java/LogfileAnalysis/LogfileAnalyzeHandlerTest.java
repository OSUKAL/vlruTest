package LogfileAnalysis;

import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Тесты обработчика лог файла
 *
 * <p>Возвращает из переданного лог файла список записей анализа интервалов с доступностью ниже указанной
 *
 * @see Infrastructure.Args
 */
class LogfileAnalyzeHandlerTest {

    /**
     * Обработка лог файла с выходным результатом анализа
     */
    @Test
    void logsWithIssuesFoundTest() {
        File logfile = new File("src/test/TestLogfiles/handlerTest.log");
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, 45.0, 96);

        var expected = new ArrayList<LogsAnalyzeResult>();
        expected.add(new LogsAnalyzeResult(2, "16:48:08", 95.83333333333334));
        expected.add(new LogsAnalyzeResult(3, "16:48:09", 95.23809523809523));

        Assertions.assertEquals(actual, expected);
    }

    /**
     * Обработка лог файла без выходного результата анализа
     */
    @Test
    void noIssuesFoundTest() {
        File logfile = new File("src/test/TestLogfiles/handlerTest.log");
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, 45.0, 80);

        Assertions.assertTrue(actual.isEmpty());
    }

    /**
     * Тест поведения при возникновении IOException во время обработки лог файла
     */
    @Test
    void exceptionOccurredTest() {
        File logfile = new File("src/test/TestLogfiles/nonexistent.log");
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, 45.0, 94.9);

        Assertions.assertNull(actual);
    }
}