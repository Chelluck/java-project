package reflection;

import java.lang.reflect.Field;


public class ReflectionHelper {

    public static Object instantiateObject(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (IllegalArgumentException | SecurityException | InstantiationException |
                 IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void assignFieldValue(Object obj, String fieldName, String value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            if (field.getType().equals(String.class)) {
                field.set(obj, value);
            } else if (field.getType().equals(int.class)) {
                field.set(obj, Integer.decode(value));
            }

            field.setAccessible(false);
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

