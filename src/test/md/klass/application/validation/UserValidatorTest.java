package md.klass.application.validation;

import md.klass.application.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidatorTest {
    UserValidator userValidator=new UserValidator();
    @Test
     void user_should_be_valid(){
        User user =new User("Olga", "Klassen");
        assertEquals(0, userValidator.validate(user).size());
    }
    @Test
    void first_and_last_names_too_short(){
        User user =new User("O", "K");
        assertEquals("First name too short", userValidator.validate(user).get(0));
        assertEquals("Last name too short", userValidator.validate(user).get(1));
    }
}
