package ssvv.example;

import domain.Nota;
import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.*;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;

    @Before
    public void before() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator  = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        String studentFile = "src/testFiles/students";
        String assignmentsFile = "src/testFiles/assignments";
        String gradesFile = "src/testFiles/grades";

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, studentFile);
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, assignmentsFile);
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, gradesFile);

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudentValidName() {
        service.deleteStudent("1");
        int result =  service.saveStudent("1", "TestStudent", 937);

        assertEquals(1, result);

        service.deleteStudent("1");

        assertNotNull(service.findAllStudents());
    }

    @Test
    public void testAddStudentEmptyName() {
        int result = service.saveStudent("1", "", 937);

        assertEquals(1, result);

        //assertNull(service.findAllStudents());

        service.deleteStudent("1");
    }

    @Test
    public void testAddStudentValidId() {
        int result = service.saveStudent("1", "TestStudent", 937);

        assertEquals(1, result);

        service.deleteStudent("1");

        assertNotNull(service.findAllStudents());
    }

    @Test
    public void testAddStudentEmptyId() {
        int result = service.saveStudent("", "", 937);

        assertEquals(1, result);
    }

    @Test
    public void testAddStudentValidGroup() {
        int result = service.saveStudent("1", "TestStudent", 937);

        assertEquals(1, result);

        service.deleteStudent("1");

        assertNotNull(service.findAllStudents());

    }

    @Test
    public void testAddStudentInvalidGroup() {
        int result = service.saveStudent("1", "", 100);

        assertEquals(1, result);
    }

    @Test
    public void testAddAssignmentValidDeadline() {
        int result = service.saveTema("1", "TestAssignment", 12, 10);

        assertEquals(1, result);

        service.deleteTema("1");

        assertNotNull(service.findAllTeme());
    }

    @Test
    public void testAddAssignmentInvalidDeadline() {
        int result = service.saveTema("1", "TestAssignment", 15, 10);

        assertEquals(1, result);

        service.deleteTema("1");
    }

    // lab 4

    @Test
    public void testAddStudent() {
        int result = service.saveStudent("5", "John", 937);
        assertEquals(1, result);

        service.deleteStudent("5");
    }

    @Test
    public void testAddAssignmentIntegration() {
        int result = service.saveStudent("6", "Mike", 937);
        assertEquals(1, result);

        int result1 = service.saveTema("6", "Assignment6", 12, 10);
        assertEquals(1, result1);

        service.deleteStudent("6");
        service.deleteTema("6");
    }

    @Test
    public void testAddGradeIntegration() {
        int result = service.saveStudent("9", "Jordan", 937);
        assertEquals(1, result);

        int result1 = service.saveTema("9", "Assignment7", 12, 10);
        assertEquals(1, result1);

        int result2 = service.saveNota("9", "9", 9, 11, "Good Job!");
        assertEquals(1, result2);

        service.deleteStudent("9");
        service.deleteTema("9");
    }

}
