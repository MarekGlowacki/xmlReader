package javaTask.task;

public class Main {
    public static void main(String[] args) {
        EmployeeDatabase database = new EmployeeDatabase();
        Person newEmployee = new Person("2", "Lukasz", "Doe", "123456789", "john.doe@example.com", "12345678901");

        database.create(newEmployee);
        database.find(EmployeeDatabase.Type.EXTERNAL, "Lukasz", "Doe", "123456789");
        database.modify(newEmployee);
        database.remove("1");
    }
}