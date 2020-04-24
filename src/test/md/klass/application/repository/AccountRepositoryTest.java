package md.klass.application.repository;

import md.klass.application.models.Account;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountRepositoryTest {
    AccountRepository accountRepository=new AccountRepository();
    @Test
    void should_find_account_with_id_1() throws SQLException {
        Account account=accountRepository.findOne("ok");
        if (account!=null){
            assertEquals(1,account.getId());
        }
    }
    @Test
    void should_find_no_account() throws SQLException{
        Account account=accountRepository.findOne("offk");
        assertNull(account);
    }
}
