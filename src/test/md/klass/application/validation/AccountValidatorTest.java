package md.klass.application.validation;

import md.klass.application.models.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountValidatorTest {
    private AccountValidator accountValidator = new AccountValidator();
    @Test
    void should_be_valid_account(){
        Account account=new Account("Olga", "123qwe");
        assertEquals(0, accountValidator.validate(account).size());
    }
    @Test
    void username_too_short(){
        Account account=new Account("O", "123qwe");
        assertEquals("Username too short", accountValidator.validate(account).get(0));
    }
    @Test
    void username_too_short_and_password_too_short_and_password_not_valid(){
        Account account=new Account("O", "123");
        assertEquals("Username too short", accountValidator.validate(account).get(0));
        assertEquals("Password should not be less than 5 symbols", accountValidator.validate(account).get(1));
        assertEquals("The password should contain al least one character and one digit", accountValidator.validate(account).get(2));

    }
}
