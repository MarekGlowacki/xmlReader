package javaTask.task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManagement {
    private static final String EXTERNAL_FOLDER = "external";
    private static final String INTERNAL_FOLDER = "internal";

    public static void addEmployeeToFile(Person person, EmployeeDatabase.Type type) {
        String folder = (type == EmployeeDatabase.Type.EXTERNAL) ? EXTERNAL_FOLDER : INTERNAL_FOLDER;
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = folder + "/" + person.getPersonId() + ".xml";
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("Cannot assign person with duplicate ID: " + person.getPersonId());
        }

        try {
            FileWriter writer = getFileWriter(person, filePath);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileWriter getFileWriter(Person person, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write("<person>\n");
        writer.write("    <personId>" + person.getPersonId() + "</personId>\n");
        writer.write("    <firstName>" + person.getFirstName() + "</firstName>\n");
        writer.write("    <lastName>" + person.getLastName() + "</lastName>\n");
        writer.write("    <mobile>" + person.getMobile() + "</mobile>\n");
        writer.write("    <email>" + person.getEmail() + "</email>\n");
        writer.write("    <pesel>" + person.getPesel() + "</pesel>\n");
        writer.write("</person>");
        return writer;
    }

    public static boolean removeEmployeeFile(String personId) {
        File externalDirectory = new File(EXTERNAL_FOLDER);
        File internalDirectory = new File(INTERNAL_FOLDER);

        File[] externalFiles = externalDirectory.listFiles();
        if (externalFiles != null) {
            for (File file : externalFiles) {
                if (file.isFile() && file.getName().equals(personId + ".xml")) {
                    file.delete();
                    return true;
                }
            }
        }

        File[] internalFiles = internalDirectory.listFiles();
        if (internalFiles != null) {
            for (File file : internalFiles) {
                if (file.isFile() && file.getName().equals(personId + ".xml")) {
                    file.delete();
                    return true;
                }
            }
        }

        return false;
    }

    public static Person findEmployee(EmployeeDatabase.Type type,  String firstName, String lastName, String mobile) {
        String folder = (type == EmployeeDatabase.Type.EXTERNAL) ? EXTERNAL_FOLDER : INTERNAL_FOLDER;
        File directory = new File(folder);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Person person = readEmployeeFromFile(file);
                    if (person != null && matchesSearchTerm(person, firstName, lastName, mobile)) {
                        return person;
                    }
                }
            }
        }
        return null;
    }



    public static Person findById(String id, EmployeeDatabase.Type folderType) {
        String folder = (folderType == EmployeeDatabase.Type.EXTERNAL) ? EXTERNAL_FOLDER : INTERNAL_FOLDER;
        File directory = new File(folder);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    Person person = readEmployeeFromFile(file);
                    if (person != null && matchesId(person, id)) {
                        return person;
                    }
                }
            }
        }
        return null;
    }

    private static boolean matchesId(Person person, String id) {
        return person.getPersonId().equalsIgnoreCase(id);
    }

    private static Person readEmployeeFromFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder data = new StringBuilder();
            while (scanner.hasNextLine()) {
                data.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            String xmlData = data.toString();
            String personId = getData(xmlData, "personId");
            String firstName = getData(xmlData, "firstName");
            String lastName = getData(xmlData, "lastName");
            String mobile = getData(xmlData, "mobile");
            String email = getData(xmlData, "email");
            String pesel = getData(xmlData, "pesel");

            return new Person(personId, firstName, lastName, mobile, email, pesel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getData(String xmlData, String tag) {
        int start = xmlData.indexOf("<" + tag + ">") + tag.length() + 2;
        int end = xmlData.indexOf("</" + tag + ">");
        return xmlData.substring(start, end);
    }

    private static boolean matchesSearchTerm(Person person, String firstName, String lastName, String mobile) {
        return person.getFirstName().equalsIgnoreCase(firstName) &&
                person.getLastName().equalsIgnoreCase(lastName) &&
                person.getMobile().equals(mobile);
    }

    public static void modifyEmployee(Person person) {
        modifyEmployeeInFolder(person, EmployeeDatabase.Type.EXTERNAL);
        modifyEmployeeInFolder(person, EmployeeDatabase.Type.INTERNAL);
    }

    private static void modifyEmployeeInFolder(Person person, EmployeeDatabase.Type folder) {
        Person foundedPerson = findById(person.getPersonId(), folder);
        if (foundedPerson != null) {
            String filePath = folder + "/" + person.getPersonId() + ".xml";
            try {
                FileWriter writer = getFileWriter(person, filePath);
                writer.close();
                System.out.println("zmodyfikowano użytkownika z ID: " + person.getPersonId() + " w folderze: " + folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
