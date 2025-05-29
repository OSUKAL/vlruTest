import Infrastructure.Args;
import Infrastructure.ResultPrinter;
import LogfileAnalysis.LogfileAnalyzeHandler;
import com.beust.jcommander.JCommander;

import java.io.File;

public class App {
    public static void main(String... args) {
        Args commandLineArgs = new Args();
        JCommander jc = new JCommander(commandLineArgs);

        try {
            jc.parse(args);
        } catch (Exception exception) {
            jc.usage();
        }

        if (commandLineArgs.help) {
            jc.usage();
            System.exit(0);
        }

        if (commandLineArgs.logfile == null || !commandLineArgs.logfile.endsWith(".log") || commandLineArgs.minAccessibility == null || commandLineArgs.maxProcessingTime == null) {
            System.out.println("Для получения информации о запуске приложения выполните команду с аргументом -help");
            return;
        }

        if (commandLineArgs.minAccessibility > 100) commandLineArgs.minAccessibility = 100.0;

        File logfile = new File(commandLineArgs.logfile);
        if (!logfile.exists()) {
            System.err.println("Лог файл отсутствует по пути " + logfile.getAbsolutePath());
            return;
        }

        var handler = new LogfileAnalyzeHandler();
        var results = handler.handle(logfile, commandLineArgs.maxProcessingTime, commandLineArgs.minAccessibility);
        if (results == null) {
            System.err.println("При обработке лог файла произошла ошибка");
            return;
        }

        if (results.isEmpty())
            System.out.println("Порог отказов при записи логов не превышен.");

        ResultPrinter.print(results);
    }
}
