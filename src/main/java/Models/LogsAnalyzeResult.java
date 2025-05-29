package Models;

/**
 * Данные анализа интервала логов
 *
 * @param issueCount    Количество несоответствий параметрам анализа
 * @param interval      Время записи логов
 * @param accessibility Уровень доступности
 */
public record LogsAnalyzeResult(int issueCount, String interval, double accessibility) {
}