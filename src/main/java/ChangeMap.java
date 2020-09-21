import java.util.HashMap;
import java.util.Map;

/**
 * Вспомогательный класс для класса Clean
 * реализует метод changeAndCopyMap
 */
public class ChangeMap {

    /**
     * Метод удаляет ключи из мапы указанные в массиве setObjectsToRemove, и возвращает значения
     * указанные в массиве setObjectsOutput.
     * Если не находит хотя бы один ключ из setObjectsToRemove, то кидает исключение IllegalAccessException
     * @param object объект интерфейса Map
     * @param setObjectsToRemove массив из ключей
     * @param setObjectsOutput массив из значеий
     * @throws IllegalAccessException
     */
    public void changeAndCopyMap(Object object, Object[] setObjectsToRemove, Object[] setObjectsOutput)
            throws IllegalAccessException {
        Map<Object, Object> map = new HashMap<>();
        if (object instanceof Map) {
            map = (Map<Object, Object>) object;
        }

        for (Object obj : setObjectsToRemove) {
            if (!map.entrySet().removeIf(entry -> obj.equals(entry.getKey()))) {
                throw new IllegalAccessException("Такого значения нет в мапе");
            }
        }
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            for (Object obj : setObjectsOutput) {
                if (obj.equals(entry.getValue())) {
                    System.out.println(entry.getValue());
                }
            }
        }
    }
}
