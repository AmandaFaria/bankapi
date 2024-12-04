package br.edu.utfpr.bankapi.validations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import br.edu.utfpr.bankapi.model.Account;
import br.edu.utfpr.bankapi.repository.AccountRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import br.edu.utfpr.bankapi.exception.NotFoundException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AvailableAccountValidationTest {
    @Mock
    static AccountRepository accountRepository = Mockito.mock(AccountRepository.class);

    static AvailableAccountValidation availableAccountValidation;

    @BeforeEach
    void before() {
        availableAccountValidation = new AvailableAccountValidation();

        ReflectionTestUtils.setField(availableAccountValidation, "accountRepository", accountRepository);
    }

    @Test
    void deveriaRetornarContaExistente() throws NotFoundException {
        // ARRANGE
        long accountNumber = 12345;
        Account account = new Account("Amanda Faria", accountNumber, 1500.00, 0);
        BDDMockito.given(accountRepository.getByNumber(accountNumber)).willReturn(Optional.of(account));

        // ACT
        Account result = availableAccountValidation.validate(accountNumber);

        // ASSERT
        Assertions.assertEquals(account, result);
    }

    @Test
    void deveriaLancarExcecaoParaContaInexistente() {
        // ARRANGE
        long accountNumber = 12345;
        BDDMockito.given(accountRepository.getByNumber(accountNumber)).willReturn(Optional.empty());

        // ACT & ASSERT
        Assertions.assertThrows(NotFoundException.class, () -> availableAccountValidation.validate(accountNumber));
    }
}
