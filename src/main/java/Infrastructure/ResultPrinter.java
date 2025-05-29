package Infrastructure;

import Models.LogsAnalyzeResult;

import java.util.ArrayList;

/**
 * Принтер результатов анализа лог файла на консоль
 */
public class ResultPrinter {
    /**
     * Вывод списка результатов анализа лог файла на консоль
     *
     * @param results Список результатов анализа лог файла
     */
    public static void print(ArrayList<LogsAnalyzeResult> results) {
        for (var result : results) {
            var issues = String.format("%03d", result.issueCount());
            System.out.printf(issues + " " + result.interval() + " " + "%.2f\n", result.accessibility());
        }
    }
}
