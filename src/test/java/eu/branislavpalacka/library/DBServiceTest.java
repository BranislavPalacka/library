package eu.branislavpalacka.library;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.services.api.AuthorService;
import eu.branislavpalacka.library.services.api.BookService;
import eu.branislavpalacka.library.services.api.EmployeeService;
import eu.branislavpalacka.library.services.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DBServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void user(){
        Employee employee = new Employee("Peeetr","Jablko", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14",1);
        Integer idE = employeeService.add(employee);

        User user = new User("Fero","Mrkvicka", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14",0,0,idE);
        Integer id = userService.add(user);

        assert id != null;
        user.setId(id);

        User userFromDb = userService.get(id);
        Assert.assertEquals(user,userFromDb);

        List<User> users = userService.getUsers();
        Assert.assertEquals(1,users.size());
        Assert.assertEquals(user,users.get(0));

    }

    @Test
    public void employee(){
        Employee employee = new Employee("Peeetr","Jablko", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14",1);
        Integer id = employeeService.add(employee);

        assert id != null;
        employee.setId(id);

        Employee employeeFromDb = employeeService.get(id);
        Assert.assertEquals(employee,employeeFromDb);

        List<Employee> employees = employeeService.getEmployees();
        Assert.assertEquals(1,employees.size());
        Assert.assertEquals(employee,employees.get(0));
    }

    @Test
    public void book() {
        Book book = new Book("Nová kniha", "ještě jsem nečetl", "images/NK.gif", 1, 2, 13,1);

        Integer id = bookService.add(book);
        assert id != null;
        book.setId(id);

        Book bookFromDb = bookService.get(id);
        Assert.assertEquals(book,bookFromDb);

        List<Book> books = bookService.getBooks();
        Assert.assertEquals(1,books.size());
        Assert.assertEquals(book,books.get(0));
    }

    @Test
    public void author(){
        Author author = new Author("Jaroslav","Hasek","fakt nevim","images/hasek.gif",1);

        Integer id = authorService.add(author);
        assert id != null;
        author.setId(id);

        Author authorFromDB = authorService.get(id);
        Assert.assertEquals(author,authorFromDB);

        List<Author> authors = authorService.getAuthors();
        assert authors.size() == 1;
        Assert.assertEquals(author,authors.get(0));
    }

}
