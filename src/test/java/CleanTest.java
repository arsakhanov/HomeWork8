import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CleanTest {
    private Example example;


    //Убедимся, действительно ли cleaup меняет поля объекта
    @Test
    public void testCleanup() throws IllegalAccessException {
        this.example = new Example(25, "Ivan", true);
        Set<String> fieldsToCleanup = new HashSet<>();
        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanup.add("age");
        fieldsToCleanup.add("name");
        fieldsToCleanup.add("example");

        fieldsToOutput.add("age");
        fieldsToOutput.add("name");
        fieldsToOutput.add("example");

        Clean.cleanup(example, fieldsToCleanup, fieldsToOutput);
        Assert.assertEquals(0, example.getAge());
    }

    //Убедимся, действительно ли cleaup кидает исключение
    //если у объекта нет полей указанных в множестве fieldsToCleanup
    @Test(expected = IllegalAccessException.class)
    public void testCleanupError() throws IllegalAccessException {
        this.example = new Example(25, "Ivan", true);
        Set<String> fieldsToCleanup = new HashSet<>();
        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanup.add("notAge");
        fieldsToCleanup.add("name");
        fieldsToCleanup.add("example");

        fieldsToOutput.add("age");
        fieldsToOutput.add("name");
        fieldsToOutput.add("example");

        Clean.cleanup(example, fieldsToCleanup, fieldsToOutput);
    }


    //Убедимся, действительно ли cleanup меняет поля объекта
    //если объектом является map
    @Test
    public void testCleanupMap() throws IllegalAccessException {
        Set<String> fieldsToCleanup = new HashSet<>();
        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanup.add("Russia");
        fieldsToCleanup.add("Ukraine");

        fieldsToOutput.add("Tokyo1");
        fieldsToOutput.add("Tokyo");

        Map<String, String> map = new HashMap<>();
        map.put("Russia", "Moscow");
        map.put("Ukraine", "Kiev");
        map.put("Japan", "Tokyo");
        Clean.cleanup(map, fieldsToCleanup, fieldsToOutput);
        Assert.assertNull(map.get("Russia"));
        System.out.println(map);
    }

    //Убедимся, действительно ли cleaup кидает исключение
    //если у мапы нет ключей указанных в множестве fieldsToCleanup
    @Test(expected = IllegalAccessException.class)
    public void testCleanupMapError() throws IllegalAccessException {
        this.example = new Example(25, "Ivan", true);
        Set<String> fieldsToCleanup = new HashSet<>();
        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanup.add("Russia");
        fieldsToCleanup.add("Ukraine1");


        fieldsToOutput.add("Tokyo");

        Map<String, String> map = new HashMap<>();
        map.put("Russia", "Moscow");
        map.put("Ukraine", "Kiev");
        map.put("Japan", "Tokyo");
        Clean.cleanup(map, fieldsToCleanup, fieldsToOutput);
    }
}



