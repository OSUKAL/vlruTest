package LogfileAnalysis;

import Models.LogRecord;
import Models.LogsAnalyzeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class LogAnalyzerTest {

    @Test
    void analyzeResultOutputLogs() {
        var logs = new ArrayList<LogRecord>();
        logs.add(new LogRecord("16:47:07", (short)200, 42));
        logs.add(new LogRecord("16:47:07", (short)567, 36.87432));
        logs.add(new LogRecord("16:47:07", (short)300, 65.465));
        logs.add(new LogRecord("16:47:07", (short)400, 73.33613));
        logs.add(new LogRecord("16:47:07", (short)500, 78.336411));
        logs.add(new LogRecord("16:47:07", (short)200, 66.131276));
        logs.add(new LogRecord("16:47:07", (short)523, 26.761276));
        logs.add(new LogRecord("16:47:07", (short)200, 22.411336));

        var analyzer = new LogAnalyzer();
        var actual = analyzer.analyze(logs, 70.1, 90);

        var expected = new LogsAnalyzeResult(4, "16:47:07", 50.0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void analyzeNullOutputLogs() {
        var logs = new ArrayList<LogRecord>();
        logs.add(new LogRecord("16:47:07", (short)200, 42.413613));
        logs.add(new LogRecord("16:47:07", (short)200, 36.87432));
        logs.add(new LogRecord("16:47:07", (short)200, 65.465436));
        logs.add(new LogRecord("16:47:07", (short)200, 43.33613));
        logs.add(new LogRecord("16:47:07", (short)200, 78.336411));
        logs.add(new LogRecord("16:47:07", (short)200, 86.131276));
        logs.add(new LogRecord("16:47:07", (short)200, 26.761276));
        logs.add(new LogRecord("16:47:07", (short)200, 22.411336));

        var analyzer = new LogAnalyzer();
        var actual = analyzer.analyze(logs, 70.1, 65.0);

        Assertions.assertNull(actual);
    }
}