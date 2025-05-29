package Models;

/**
 * Данные записи лога
 *
 * @param interval Время записи лога
 * @param status   Код состояния
 * @param time     Время ответа
 */
public record LogRecord(String interval, short status, double time) {
}
