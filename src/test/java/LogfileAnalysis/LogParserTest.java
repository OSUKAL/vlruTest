package LogfileAnalysis;

import Models.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogParserTest {

    @Test
    void validDataParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        var expected = new LogRecord("16:47:03", (short) 200, 64.277784);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void integerResponseTimeParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:05 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=dd80e5f9 HTTP/1.1\" 404 2 37 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        var expected = new LogRecord("16:47:05", (short) 404, 37);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidIntervalParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03.123 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }

    @Test
    void invalidStatusParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" asdas 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }

    @Test
    void invalidResponseTimeParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 asdasd \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }
}