
# Тестовое задание VL.RU

Консольное приложение для локализации интервалов записи логов с превышением заданного уровня отказов.

Результатом работы приложения является построчный вывод на консоль интервалов записи в которые была превышена доля отказов в формате:
```<количество_отказов> <интервал_записи_логов> <уровень_доступности_сервиса_за_интервал>```





## Установка
  1. Установите [JDK 21](https://www.oracle.com/cis/java/technologies/downloads/#java21)
  2. Установите [Maven](https://maven.apache.org/install.html)
  4. Склонируйте репозиторий ``` git clone https://github.com/OSUKAL/vlruTest ```
  5. Соберите проект ``` mvn clean install ```

После этого в корневой папке проекта cгенерируется новая папка target, в которой будет находится исполняемый .jar файл.  
Откройте консоль в папке с исполняемым jar файлом, выполните команду и следуйте инструкции чтобы начать анализ лог файла
``` java -jar vlruTest.jar -help  ```
Вы также можете выполнить команду открыв консоль в другом расположении, но в таком случае вместо vlruTest.jar нужно указать расположение jar файла.
