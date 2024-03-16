import javaTask.task.EmployeeDatabase;
import javaTask.task.Person;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class EmployeeDatabaseTest {
    private EmployeeDatabase database;
    private final String testId = "veryLongIdThatIsItVerySureThatNeverHappenInRealLifeSoItIsGoodForTestOnly";

    @Before
    public void setUp() {
        database = new EmployeeDatabase();
        database.create(new Person(testId, "John", "Doe", "123456789", "john.doe@example.com", "12345678901"));
    }

    @After
    public void clean() {
        database.remove(testId);
    }

    @Test
    public void testFind() {

        Person foundPerson = database.find(EmployeeDatabase.Type.INTERNAL, "John", "Doe", "123456789");
        assertNotNull(foundPerson);
        assertEquals("John", foundPerson.getFirstName());
        assertEquals("Doe", foundPerson.getLastName());
        assertEquals("123456789", foundPerson.getMobile());

        assertNull(database.find(EmployeeDatabase.Type.INTERNAL, "Nonexistent", "Employee", "000000000"));
    }

    @Test
    public void testCreate() {

        String filePath = "internal/" + testId + ".xml";
        File file = new File(filePath);
        assertTrue(file.exists());
    }


    @Test
    public void testRemove() {

        assertTrue(database.remove(testId));
        assertFalse(database.remove("Nonexistent"));
    }

    @Test
    public void testModify() {

        Person updatedPerson = new Person(testId, "Kobe", "Bryant", "888888888", "kobe.bryant@example.com", "88888888808");
        database.modify(updatedPerson);

        Person foundPerson = database.find(EmployeeDatabase.Type.INTERNAL, "Kobe", "Bryant", "888888888");
        assertNotNull(foundPerson);
        assertEquals("Kobe", foundPerson.getFirstName());
        assertEquals("Bryant", foundPerson.getLastName());
        assertEquals("888888888", foundPerson.getMobile());
    }
}
