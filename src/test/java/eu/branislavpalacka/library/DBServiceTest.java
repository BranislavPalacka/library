package eu.branislavpalacka.library;

import eu.branislavpalacka.library.domain.User;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void user(){
        User user = new User("Fero","Mrkvicka", "za sedmero rekami", "email@email.com", "700 60 61 62","uplneNevim14",0,0,0);
        Integer id = userService.add(user);

        assert id != null;
        user.setId(id);

        User userFromDb = userService.get(id);
        Assert.assertEquals(user,userFromDb);

        List<User> users = userService.getUsers();
        Assert.assertEquals(1,users.size());
        Assert.assertEquals(user,users.get(0));

    }
}
