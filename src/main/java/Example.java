/**
 * Класс "пример" созданный для демонстрации метода cleanup класса Clean
 */
public class Example {
    private final int age;
    String name;
    boolean example;

    public Example(int age, String name, boolean example) {
        this.age = age;
        this.name = name;
        this.example = example;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean getExample() {
        return example;
    }

    @Override
    public String toString() {
        return "Example{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", example=" + example +
                '}';
    }
}
