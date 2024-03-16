package javaTask.task;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EmployeeDatabase database = new EmployeeDatabase();

//        Person newEmployee = new Person("2", "Maniek", "Doe", "123456789", "john.doe@example.com", "12345678901");

//        database.create(newEmployee);

//        try {
//            database.create(new Person("1", "John", "Doe", "123456789", "john.doe@example.com", "12345678901"));
//            database.create(new Person("11", "Jane", "Smith", "987654321", "jane.smith@example.com", "98765432109"));
//            database.create(new Person("3", "Alice", "Johnson", "111222333", "alice.johnson@example.com", "11122233307"));
//            database.create(new Person("4", "Bob", "Brown", "444555666", "bob.brown@example.com", "44455566605"));
//            database.create(new Person("5", "Emily", "Taylor", "777888999", "emily.taylor@example.com", "77788899903"));
//            database.create(new Person("6", "Michael", "Wilson", "222333444", "michael.wilson@example.com", "22233344408"));
//            database.create(new Person("7", "Sarah", "Martinez", "555666777", "sarah.martinez@example.com", "55566677702"));
//            database.create(new Person("8", "David", "Anderson", "888999000", "david.anderson@example.com", "88899900006"));
//            database.create(new Person("9", "Olivia", "Garcia", "333444555", "olivia.garcia@example.com", "33344455504"));
//            database.create(new Person("10", "James", "Lopez", "000111222", "james.lopez@example.com", "00011122200"));
//
//            System.out.println("Dodano kilka przykładowych pracowników do bazy danych.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Person foundEmployee = database.find(EmployeeDatabase.Type.EXTERNAL, "Lukasz", "Doe", "123456789");

//        database.modify(newEmployee);

        boolean removed = database.remove("1");
        if (removed) {
            System.out.println("Pracownik został usunięty.");
        } else {
            System.out.println("Nie udało się usunąć pracownika.");
        }
    }
}