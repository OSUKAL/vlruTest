package Infrastructure;

import com.beust.jcommander.Parameter;

/**
 * Класс пользовательских параметров командной строки
 */
public class Args {
    /** Вызов списка параметров командной строки с описанием*/
    @Parameter(
            names = "-help",
            description = "Для запуска приложения выполните команду java -jar <расположение_jar_файла> -[l|logfile] <расположение_анализируемого_лог_файла> -[t|time] <время_в_миллисекундах> -u <минимальный_уровень_доступности> порядок атрибутов не важен"
            ,
            help = true
    )
    public boolean help;

    /** Путь к лог файлу для анализа */
    @Parameter(names = {"-logfile", "-l"}, description = "Log file path")
    public String logfile;

    /** Минимальный уровень доступности по интервалу записи логов */
    @Parameter(names = "-u", description = "Минимальный уровень доступности за интервал записи логов от 0.0-100.0") //Почему -u
    public Double minAccessibility;

    /** Максимальное время ответа в миллисекундах */
    @Parameter(names = {"-time", "-t"}, description = "Максимальное время ответа в миллисекундах")
    public Integer maxProcessingTime;
}
