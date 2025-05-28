package Infrastructure;

import com.beust.jcommander.Parameter;

/**
 * Класс пользовательских параметров командной строки
 */
public class Args {
    /** Вызов списка параметров командной строки с описанием*/
    @Parameter(
            names = "-help",
            description = "To run app enter command java -jar <this_app_jar_file_path> -[l|logfile] <logfile_to_analyze_path> -[t|time] <time_in_milliseconds> -u <accessibility_percentage_up_to_100.0>"
            ,
            help = true
    )
    public boolean help;

    /** Путь к лог файлу для анализа */
    @Parameter(names = {"-logfile", "-l"}, description = "Log file path")
    public String logfile;

    /** Минимальный уровень доступности по интервалу записи логов */
    @Parameter(names = "-u", description = "Min accessibility percentage. e.g. 99.9") //Почему -u
    public Double minAccessibility;

    /** Максимальное время ответа в миллисекундах */
    @Parameter(names = {"-time", "-t"}, description = "Max processing time in milliseconds in range. e.g. 45.0")
    public Integer maxProcessingTime;
}
