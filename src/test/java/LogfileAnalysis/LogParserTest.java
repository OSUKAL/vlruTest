package LogfileAnalysis;

import Models.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты парсера строк лог файла
 *
 * <p> Возвращает данные из записи лога если все регулярные выражения нашли необходимую подстроку
 */
class LogParserTest {

    /**
     * Парсинг валидной строки
     *
     * <p>Одновременная проверка всех регулярных выражений
     */
    @Test
    void validDataParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        var expected = new LogRecord("16:47:03", (short) 200, 64.277784);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Парсинг валидной строки
     *
     * <p>Проверка регулярного выражения для парса целочисленного значения времени ответа
     */
    @Test
    void integerResponseTimeParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:21:43:14 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=dd80e5f9 HTTP/1.1\" 404 2 37 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        var expected = new LogRecord("21:43:14", (short) 404, 37);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Парсинг невалидной строки
     *
     * <p>Проверка регулярного выражения для парса времени записи лога
     */
    @Test
    void invalidIntervalParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:12:13:14.123 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 200 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }

    /**
     * Парсинг невалидной строки
     *
     * <p>Проверка регулярного выражения для парса кода состояния
     */
    @Test
    void invalidStatusParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" ASD 2 64.277784 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }

    /**
     * Парсинг невалидной строки
     *
     * <p>Проверка регулярного выражения для парса времени ответа
     */
    @Test
    void invalidResponseTimeParse() {
        var testLogString = "192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bb79e85f HTTP/1.1\" 700 2 64,121 \"-\" \"@list-item-updater\" prio:0";
        var parser = new LogParser();
        var actual = parser.parse(testLogString);

        Assertions.assertNull(actual);
    }
}