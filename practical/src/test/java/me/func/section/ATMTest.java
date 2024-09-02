package me.func.section;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    private ATM atm;
    private final Sdk sdk = Mockito.spy(new StubSdk());

    @BeforeEach
    public void beforeEach() {
        Map<Nominal, Long> banknotes = new HashMap<>();

        banknotes.put(
                new Nominal(new BigDecimal(100L)), 10L
        );

        banknotes.put(
                new Nominal(new BigDecimal(500L)), 1L
        );

        atm = new ATM(sdk, banknotes);
    }

    @Test
    public void happyWayTestWithdraw() {
        List<Nominal> banknotes = atm.withdraw(new BigDecimal(200L));

        Nominal oneHundred = new Nominal(new BigDecimal(100L));

        assertEquals(2, banknotes.size());
        assertEquals(oneHundred, banknotes.get(0));
        assertEquals(oneHundred, banknotes.get(1));
    }

    @Test
    public void unhappyWayTestWithdraw() {
        assertDoesNotThrow(() -> atm.withdraw(new BigDecimal(900L)));
        assertThrows(RuntimeException.class, () -> atm.withdraw(new BigDecimal(900L)));
    }

    @Test
    public void happyWayTestDifferentNominalsWithdraw() {
        List<Nominal> banknotes = atm.withdraw(new BigDecimal(700L));

        Nominal oneHundred = new Nominal(new BigDecimal(100L));

        assertTrue(banknotes.contains(oneHundred));

        banknotes.remove(oneHundred);

        assertTrue(banknotes.contains(oneHundred));
    }
}
