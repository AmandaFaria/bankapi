package br.edu.utfpr.bankapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.edu.utfpr.bankapi.service.Test;
import br.edu.utfpr.bankapi.service.TransactionService;

public class TransactionServiceTest {
    private final TransactionService transactionService = new TransactionService();

    @Test
    void testWithdraw() {
        double initialBalance = 100.0;
        double amountToWithdraw = 50.0;

        transactionService.withdraw(initialBalance, amountToWithdraw);

        double finalBalance = transactionService.getBalance();
        assertEquals(50.0, finalBalance);
    }

    @Test
    void testTransfer() {
        
        double initialBalance = 100.0;
        double amountToTransfer = 50.0;

        transactionService.transfer(initialBalance, amountToTransfer);

        double finalBalance = transactionService.getBalance();
        assertEquals(50.0, finalBalance);
    }
}
