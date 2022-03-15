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
       int result =  service.saveStudent("1", "TestStudent", 100);

        assertEquals(1, result);

        service.deleteStudent("1");

        assertNull(service.findAllStudents());
    }

    @Test
    public void testAddStudentEmptyName() {
        int result = service.saveStudent("1", "", 100);

        assertEquals(0, result);
        assertNull(service.findAllStudents());
    }
}
