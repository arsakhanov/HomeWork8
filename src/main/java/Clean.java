
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Класс реализует метод cleanup, который изменяет поля в объекте переданной ей
 * на значение null или 0
 */
public class Clean {

    /**
     * метод cleanup принимает любой объект и две коллекции строк.
     * В объекте, посредством reflection поля, перечисленные в fieldsToClenup устанавливает в значение null,
     * или, если поля примитивных типов в их значение по умолчанию.
     * Поля, перечисленные в fieldsToOutput конвертирует в строку (вызвав toString у объектов, или
     * String.valueOf для примитивных типов) и выводит результат преобразования в консоль.
     * Если переданный первым параметром объект является реализацией интерфейса Map,
     * то проделает аналогичные операции - для списка fieldsToCleanup удаляет ключи из мапы,
     * для fieldsToOutput выводит в консоль значения, хранящиеся в мапе.
     * При отсутствии в объекте/мапе нужных полей/ключей - кидает IllegalArgumentException,
     * оставив объект неизменным.
     * @param object объект любого типа
     * @param fieldsToCleanup список полей/ключей
     * @param fieldsToOutput список полей/значений
     * @throws IllegalAccessException
     */
    public static void cleanup(Object object,
                               Set<String> fieldsToCleanup,
                               Set<String> fieldsToOutput) throws IllegalAccessException {
        ChangeObject change = new ChangeObject();
        ChangeMap changeMap = new ChangeMap();

        Object[] setObjects = fieldsToCleanup.toArray();
        Object[] setObjectsOutput = fieldsToOutput.toArray();

        Class<?> field = object.getClass();
        Field[] fields = field.getDeclaredFields();


        Class<?>[] interfaces = field.getInterfaces();
        if (interfaces.length == 0) {
            isContainsObj(fields, setObjects);
            change.changeObject(object, fields, setObjects);
            change.covertObjects(object, fields, setObjectsOutput);
        } else {
            for (Class<?> interfaceMap : interfaces) {
                String strInterface = String.valueOf(interfaceMap);
                if (strInterface.equals("interface java.util.Map")) {
                    changeMap.changeAndCopyMap(object, setObjects, setObjectsOutput);
                }
            }
        }
    }

    //метод проверяет есть ли поля в объекте
    private static void isContainsObj(Field[] declaredFields, Object[] setObjects) throws IllegalAccessException {
        boolean isContainsObj = false;
        for (Field decField : declaredFields) {
            for (Object obj : setObjects) {
                if (obj.equals(decField.getName())) {
                    isContainsObj = true;
                    break;
                }
            }
            if (!isContainsObj) {
                throw new IllegalAccessException("Такого поля не сущетсвует в объекте");
            }
        }
    }
}
