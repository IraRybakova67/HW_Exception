package option_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class hw_2 {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in, "ibm866")) {
            System.out.print("Введите данные через пробел: ");
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length <5) {
            throw new IllegalArgumentException("Неверное количество данных!");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            String gender = data[5];

            validateData(lastName, firstName, middleName, birthDate, phoneNumber, gender);



            try {
                // Создание файла, если он не существует
                File file = new File(lastName + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                
                try (FileWriter writer = new FileWriter(file.getAbsoluteFile())) {
                    writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " +
                            phoneNumber + " " + gender);
                    writer.flush();

                } catch (Exception e) {
                    System.err.println("Ошибка при записи в файл");
                }
            } catch (Exception e) {
                System.err.println("Ошибка создания файла");
            }
        }
    }

    private static void validateData(String lastName, String firstName,
            String middleName, String birthDate, long phoneNumber, String gender) {

        if (!isValidDate(birthDate)) {
            throw new IllegalArgumentException("Дата рождения введена неверно!");
        } else if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Номер телефона введен неверно!");
        } else if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Пол введен неверно!");
        }
    }

    private static boolean isValidDate(String birthDate) {
        String[] date = birthDate.split("\\.");
        if (date.length != 3) {
            return false;
        }

        for (String d : date) {
            try {
                Integer.parseInt(d);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidPhoneNumber(long phoneNumber) {
        return phoneNumber > 0;
    }

    private static boolean isValidGender(String gender) {
        if (gender.equalsIgnoreCase("м") || gender.equalsIgnoreCase("ж")) {
            return true;
        } else {
            return false;

        }

        
}
    
}
