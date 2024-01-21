import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        // Создание объекта класса Scanner для считывания ввода пользователя
        Scanner scanner = new Scanner(System.in, "ibm866");

        // Запрос ввода данных у пользователя
        System.out.println("Введите фамилию, имя, отчество, дату рождения, номер телефона и пол через пробел:");

        // Массивы для хранения полученных данных
        String[] data = new String[6];
       
        char gender;


        // Считывание ввода пользователя
        for (int i = 0; i < 6; i++) {
            data[i] = scanner.next();
        }

        try {
            // Проверка форматов данных
            if (!validateData(data)) {
                throw new Exception("Некорректный ввод данных");
            }
            // Разделение введенных данных на отдельные параметры
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];

            String birthDate = data[3];
            int phoneNumber = Integer.parseInt(data[4]);
            
            gender = data[5].charAt(0);
           

            // Создание файла с именем введенной фамилии
            File file = new File(lastName + ".txt");

            if (file.exists()) {
                // file.delete();
            }
            file.createNewFile();

            // Запись данных в файл
            writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            System.out.printf("Данные введены корректно.\nФайл с фамилией %s создан", lastName);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
            
        }
    }

    // Методы проверки и записи данных
private static boolean validateData(String[] data) {
    String lastName = data[0].trim();
    String firstName = data[1].trim();
    String middleName = data[2].trim();
    String birthDateString = data[3].trim();
      
    if (!birthDateString.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
        System.out.println("Дата рождения не корректна");
        return false;
        }
    int phoneNumberInt = Integer.parseInt(data[4].trim());
    char genderChar = data[5].trim().charAt(0);
    return (lastName.length() > 0 && firstName.length() > 0
    && middleName.length() > 0 && birthDateString.length() == 10
    && phoneNumberInt > 0 && genderChar == 'м' || genderChar == 'ж');
}
   

    private static void writeToFile(String lastName, String firstName,
            String middleName, String birthDate, int phoneNumber, char gender)
            throws Exception {
        FileWriter writer;
        try {
            writer = new FileWriter(lastName + ".txt", true);
            writer.write(lastName + " " + firstName + " "
                    + middleName + " " +  birthDate + " "
                    + String.valueOf(phoneNumber) + " " + gender + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
