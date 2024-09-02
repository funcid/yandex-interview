## Секция 1 - Предварительная секция

### Условия

Время 1 час</br>
Писать решение в простом online-редакторе без подсказок</br>
Обсудить предыдущий опыт работы и мотивацию

### Задача

Реализовать интерфейс уникального множества с операциями, чтобы каждый метод выполнялся за O(1). 

```java
public interface UniqueSet<T> {

    /**
     * Добавляет указанный элемент в набор.
     * Если элемент уже присутствует более одного раза, он не считается уникальным.
     */
    void add(T object);

    /**
     * Возвращает уникальный элемент из набора, если он существует.
     * Если присутствует несколько уникальных элементов, выбор зависит от реализации.
     * Возвращает null, если уникальные элементы отсутствуют.
     */
    T getUnique();

    /**
     * Удаляет указанный элемент из набора.
     */
    void remove(T object);

}
```

### Решение

<a href="./preliminary/src/main/java/me/func/section/UniqueSet.java">Интерфейс уникального множества</a></br>
<a href="./preliminary/src/main/java/me/func/section/UnsafeUniqueSet.java">Реализация Thread-Unsafe структуры данных UniqueSet</a></br>
<a href="./preliminary/src/main/java/me/func/section/ConcurrentUniqueSet.java">Реализация Thread-Safe структуры данных UniqueSet</a></br>
<a href="./preliminary/src/test/java/me/func/section/UniqueSetTest.java">Unit-тесты различных сценариев использования</a></br>

### Результат

Решение верное

## Секция 2 - Практическая секция

### Условия

Время 1 час</br>
Писать решение в IDE без code-ассистентов с веб камерой и демонстрацией экрана.</br>
Нужно написать решение и Unit-тесты

### Задача

Реализовать метод для изъятия средств из банкомата, который использует
готовое SDK 

```java
public interface Sdk {

    /**
     * Долгая операция, выполняется более 10 секунд
     */
    int countBanknotes(int banknote);

    /**
     * Переместить банкноту в окно выдачи банкомата
     */
    void moveBanknoteToDispenser(int banknote, int count);

    /**
     * Открыть окно выдачи банкомата
     */
    void openDispenser();
}
```

### Решение

<a href="./practical/src/main/java/me/func/section/ATM.java">Реализация банкомата для изъятия средств</a></br>
<a href="./practical/src/test/java/me/func/section/ATMTest.java">Unit-тесты различных сценариев использования</a></br>

### Результат

Решение имеет множество критичных недоработок