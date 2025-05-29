package Unit;

import LogfileAnalysis.LogParser;
import Models.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Тесты парсера строк лог файла
 */
class LogParserTest {

    /**
     * Проверка парса записи лога и создания объекта данных лога
     */
    @ParameterizedTest
    @MethodSource("logsProvider")
    void parse_output(String log, LogRecord expected) {
        var parser = new LogParser();
        var actual = parser.parse(log);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> logsProvider() {
        return Stream.of(
                arguments(
                        "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0",
                        new LogRecord("16:47:03", (short) 200, 64.277784)
                ),
                arguments(
                        "192.168.32.181 - - [14/06/2017:21:43:14 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=dd80e5f9 HTTP/1.1\" 404 2 37 \"-\" \"@list-item-updater\" prio:0",
                        new LogRecord("21:43:14", (short) 404, 37)
                ),
                arguments(
                        "192.168.32.181 - - [14/06/2017:12:13:14.123 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0",
                        null
                ),
                arguments(
                        "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" ASD 2 64.277784 \"-\" \"@list-item-updater\" prio:0",
                        null
                ),
                arguments(
                        "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 700 2 64,121 \"-\" \"@list-item-updater\" prio:0",
                        null
                )
        );
    }
}