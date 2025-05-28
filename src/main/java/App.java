import Infrastructure.ResultPrinter;
import LogfileAnalysis.LogfileAnalyzeHandler;

import java.io.File;

public class App {
    public static void main(String... args) {
//        Args commandLineArgs = new Args();
//        JCommander jc = new JCommander(commandLineArgs);
//
//        try {
//            jc.parse(args);
//        } catch (Exception exception) {
//            jc.usage();
//        }
//
//        if (commandLineArgs.help) {
//            jc.usage();
//            System.exit(0);
//        }
//
//        if(commandLineArgs.logfile == null) {
//            System.out.println("Execute with -help to get info about running app");
//            return;
//        }

        //File logfile = new File(commandLineArgs.logfile);

        File logfile = new File("C:\\Users\\ZUVUYER\\Desktop\\generated_log.log");
        if (!logfile.exists()) {
            System.err.println("Log file does not exist");
            return;
        }

        var handler = new LogfileAnalyzeHandler();
        var results = handler.handle(logfile, 45, 99);
        if (results.isEmpty()) {
            System.out.println("No issues found");
        }

        ResultPrinter.print(results);
    }
}
