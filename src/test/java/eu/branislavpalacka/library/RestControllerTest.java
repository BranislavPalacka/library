package eu.branislavpalacka.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void user() throws Exception{
        User user = new User("Fero","Mrkvicka", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14",0,0,0);

        // add user
        String id = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        user.setId(objectMapper.readValue(id,Integer.class));


        // get user
        String userJson = mockMvc.perform(get("/user/"+user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        User userReturned = objectMapper.readValue(userJson,User.class);

        Assert.assertEquals(user,userReturned);

        // get all users
        String userListJson = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<User> userList = objectMapper.readValue(userListJson, new TypeReference<List<User>>() {});
        assert userList.size() == 1;
        Assert.assertEquals(user,userList.get(0));

    }

    @Test
    public void employee() throws Exception{
        Employee employee = new Employee("Peetr","Jablko", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14");

        // add employee
        String id = mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        employee.setId(objectMapper.readValue(id,Integer.class));

        // get employee
        String employeeJson = mockMvc.perform(get("/employee/"+employee.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Employee employeeReturned = objectMapper.readValue(employeeJson,Employee.class);

        Assert.assertEquals(employee,employeeReturned);

        // get all employees
        String employeeListJson = mockMvc.perform(get("/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Employee> employeeList = objectMapper.readValue(employeeListJson, new TypeReference<List<Employee>>() {});

        assert employeeList.size() == 1;
        Assert.assertEquals(employee,employeeList.get(0));

    }

    @Test
    public void book() throws Exception{
        Book book = new Book("Nova kniha", "jeste jsem ji necetl", "images/NK.gif", 1, 2, 13);

        // add book
        String id = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        book.setId(objectMapper.readValue(id,Integer.class));

        // get book
        String bookJson = mockMvc.perform(get("/book/"+book.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Book bookReturned = objectMapper.readValue(bookJson,Book.class);

        Assert.assertEquals(book,bookReturned);

        // get all books
        String bookListJson = mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Book> bookList = objectMapper.readValue(bookListJson, new TypeReference<List<Book>>() {});

        Assert.assertEquals(1,bookList.size());
        Assert.assertEquals(book,bookList.get(0));
    }

}
