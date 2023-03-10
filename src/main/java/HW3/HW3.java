//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном
// порядке, разделенные пробелом:Фамилия Имя Отчество дата_рождения номер_телефона пол
//Форматы данных:
//  фамилия, имя, отчество - строки
//  дата_рождения - строка формата dd.mm.yyyy
//  номер_телефона - целое беззнаковое число без форматирования
//  пол - символ латиницей f или m.
//Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым,
//вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных,
//чем требуется.
//Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
//Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
//Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано,
//пользователю выведено сообщение с информацией, что именно неверно.
//Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну
//строку должны записаться полученные данные, вида
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//Не забудьте закрыть соединение с файлом.
//При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
// пользователь должен увидеть стектрейс ошибки.
package HW3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
        int correctQuantityData = 6;
        System.out.println("Введите следующие данные в произвольном порядке, разделенные пробелом:\n" +
                "Фамилия Имя Отчество дата_рождения номер_телефона пол\n" +
                "Форматы данных:\n" +
                "фамилия, имя, отчество - строки\n" +
                "дата_рождения - строка формата dd.mm.yyyy\n" +
                "номер_телефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.\n");
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        int dataCount = Test.CheckDataEntered(String.valueOf(str), correctQuantityData);
        if (dataCount == 0) {
            System.out.println("Проверка по количеству введенных данных пройдена...");
            try {
                User user = Parser.UserParse(String.valueOf(str));
                System.out.println("Данные распознаны...");
                Path file = Paths.get(user.getLastName() + ".txt");
                try {
                    Files.writeString(file, user.toString() + '\n',
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    System.out.println("Данные записаны в файл...");
                } catch (IOException e) {
                    System.out.println("Ошибка записи в файл! Данные не записаны!");
                    e.printStackTrace(System.out);
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Данные не распознаны...");
                ex.printStackTrace(System.out);
            }
        } else {
            if (dataCount > 0)
                System.out.print("Превышено количество требуемых данных!");
            else
                System.out.print("Количество введеных данных недостаточно!");
        }
    }
}
