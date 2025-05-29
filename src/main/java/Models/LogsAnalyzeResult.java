package Models;

/**
 * Данные анализа интервала записи логов
 *
 * @param issueCount    Количество несоответствий параметрам анализа
 * @param interval      Время записи логов
 * @param accessibility Уровень доступности
 */
public record LogsAnalyzeResult(int issueCount, String interval, double accessibility) {
}