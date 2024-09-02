package me.func.section;

import java.math.BigDecimal;
import java.util.*;

public class ATM {

    private final Map<Nominal, Long> banknotes;
    private final Sdk sdk;

    public ATM(Sdk sdk, Map<Nominal, Long> banknotes) {
        this.sdk = sdk;
        this.banknotes = banknotes;
    }

    public List<Nominal> withdraw(BigDecimal amount) {
        Set<Map.Entry<Nominal, Long>> entries = banknotes.entrySet();
        List<Nominal> nominals = new ArrayList<>();

        while (amount.compareTo(BigDecimal.ZERO) > 0) {

            if (entries.isEmpty()) {
                throw new RuntimeException("ATM is empty");
            }
            BigDecimal finalAmount = amount;
            if (entries.stream().allMatch(it -> it.getKey().amount().compareTo(finalAmount) > 0)) {
                throw new RuntimeException("ATM not valid banknotes");
            }

            Map.Entry<Nominal, Long> entry = entries.stream()
                    .sorted(Comparator.comparing(e -> e.getKey().amount().negate()))
                    .findAny()
                    .get();

            if (amount.compareTo(entry.getKey().amount()) >= 0) {
                entry.setValue(entry.getValue() - 1);

                if (entry.getValue() == 0) {
                    entries.remove(entry);
                }

                amount = amount.subtract(entry.getKey().amount());
                nominals.add(entry.getKey());
            }
        }

        nominals.forEach(entry -> sdk.moveBanknoteToDispenser(entry.amount().intValue(), 1));
        sdk.openDispenser();

        return nominals;
    }
}
