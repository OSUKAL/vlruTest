package LogfileAnalysis;

import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

class LogfileAnalyzeHandlerTest {

    @Test
    void existingFileTest() {
        File logfile = new File("src/test/TestLogfiles/test.log");
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, 45.0, 94.9);

        var expected = new ArrayList<LogsAnalyzeResult>();
        expected.add(new LogsAnalyzeResult(3, "16:47:07", 62.50));

        Assertions.assertEquals(actual, expected);
    }

    @Test
    void nonexistentFileTest() {
        File logfile = new File("src/test/TestLogfiles/nonexistent.log");
        LogfileAnalyzeHandler handler = new LogfileAnalyzeHandler();
        var actual = handler.handle(logfile, 45.0, 94.9);

        var expected = new ArrayList<LogsAnalyzeResult>();

        Assertions.assertEquals(actual, expected);
    }
}