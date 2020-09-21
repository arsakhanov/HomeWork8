import java.lang.reflect.Field;

/**
 * Вспомогательный класс для класса Clean
 * реализует методы changeObject и covertObjects
 */
public class ChangeObject {
    /**
     * метод устанавливает поля object, указанные в массиве setObjects, в значение null,
     * или, если поля примитивных типов в их значение по умолчанию.
     * @param object объект любого типа
     * @param declaredFields массив полей объекта полученные с помощью рефлексии
     * @param setObjects множество полей, которые надо поменять
     * @throws IllegalAccessException
     */
    public void changeObject(Object object, Field[] declaredFields, Object[] setObjects)
            throws IllegalAccessException {
        for (Field decField : declaredFields) {
            for (Object obj : setObjects) {
                if (decField.getName().equals(obj)) {
                    decField.setAccessible(true);
                    Object typeObj = decField.getType();
                    String strNames = typeObj.toString();
                    switch (strNames) {
                        case "class java.lang.String":
                        case "Object":
                            decField.set(object, null);
                            break;
                        case "boolean":
                            decField.setBoolean(object, false);
                            break;
                        case "short":
                            decField.setShort(object, (short) 0);
                            break;
                        case "byte":
                            decField.setByte(object, (byte) 0);
                            break;
                        case "int":
                            decField.setInt(object, 0);
                            break;
                        case "long":
                            decField.setLong(object, 0);
                            break;
                        case "float":
                            decField.setFloat(object, 0);
                            break;
                        default:
                            decField.setDouble(object, 0);
                            break;
                    }
                }
            }
        }
    }


    /**
     * метод конвертирует поля object в строку, если они совпадают с полями
     * множества setObjectsOutput
     * @param object объект любого типа
     * @param declaredFields массив из полей объекта полученные с помощью рефлексии
     * @param setObjectsOutput множетво полей, которые надо сконвертироват в строку
     * @throws IllegalAccessException
     */
    public void covertObjects(Object object, Field[] declaredFields, Object[] setObjectsOutput)
            throws IllegalAccessException {
        String string;
        for (Field decField : declaredFields) {
            for (Object obj : setObjectsOutput) {
                if (decField.getName().equals(obj)) {
                    decField.setAccessible(true);
                    Object typeObj = decField.getType();
                    String strNames = typeObj.toString();
                    switch (strNames) {
                        case "Object":
                            string = decField.get(object).toString();
                            System.out.println(obj + ": " + string);

                            break;
                        case "class java.lang.String":
                            string = (String) decField.get(object);
                            System.out.println(obj + ": " + string);

                            break;
                        case "boolean":
                            string = String.valueOf(decField.getBoolean(object));
                            System.out.println(obj + ": " + string);

                            break;
                        case "short":
                            string = String.valueOf(decField.getShort(object));
                            System.out.println(obj + ": " + string);

                            break;
                        case "byte":
                            string = String.valueOf(decField.getByte(object));
                            System.out.println(obj + ": " + string);

                            break;
                        case "int":
                            string = String.valueOf(decField.getInt(object));
                            System.out.println(obj + ": " + string);

                            break;
                        case "long":
                            string = String.valueOf(decField.getLong(object));
                            System.out.println(obj + ": " + string);

                            break;
                        case "float":
                            string = String.valueOf(decField.getFloat(object));
                            System.out.println(obj + ": " + string);

                            break;
                        default:
                            string = String.valueOf(decField.getDouble(object));
                            System.out.println(obj + ": " + string);

                            break;
                    }
                }

            }
        }
    }
}
