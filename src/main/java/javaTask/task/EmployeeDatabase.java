package javaTask.task;

public class EmployeeDatabase {

    public EmployeeDatabase() {}

    public Person find(Type type, String firstName, String lastName, String mobile) {
        Person foundEmployee = FileManagement.findEmployee(type, firstName, lastName, mobile);
        if (foundEmployee != null) {
            System.out.println("Znaleziono pracownika:");
            System.out.println("Imię: " + foundEmployee.getFirstName());
            System.out.println("Nazwisko: " + foundEmployee.getLastName());
            System.out.println("Numer telefonu: " + foundEmployee.getMobile());
            System.out.println("Email: " + foundEmployee.getEmail());
            System.out.println("PESEL: " + foundEmployee.getPesel());
        } else {
            System.out.println("Nie znaleziono pracownika.");
        }
        return foundEmployee;
    }

    public void create(Person person) {
        if (person != null) {
            FileManagement.addEmployeeToFile(person, Type.INTERNAL);
        }
    }

    public boolean remove(String personId) {
        boolean isEmployeeRemoved = FileManagement.removeEmployeeFile(personId);
        if (isEmployeeRemoved) {
            System.out.println("Pracownik został usunięty.");
        } else {
            System.out.println("Pracownik nie został usunięty.");
        }
        return isEmployeeRemoved;
    }


    public void modify(Person person) {
        if (person != null) {
            FileManagement.modifyEmployee(person);
        }
    }

    public enum Type {
        EXTERNAL,
        INTERNAL
    }
}

