package javaTask.task;

public class Person {
    private String personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;

    public Person(String personId, String firstName, String lastName, String mobile, String email, String pesel) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
    }
}

