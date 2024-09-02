package me.func.section;

public interface Sdk {

    int countBanknotes(int banknote);

    void moveBanknoteToDispenser(int banknote, int count);

    void openDispenser();
}