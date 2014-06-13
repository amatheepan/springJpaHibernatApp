/**
 * 
 */
package org.own.ste.admin.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Provides methods for introspecting classes.
 * 
 * @author Karthik.
 * 
 */
public class ReflectionUtil {
    /**
     * Private Constructor to make this an utility class.
     */
    private ReflectionUtil() {
        // Empty
    }

    /**
     * Fetches the valu in the object for the given Annotation.
     * 
     * @param obj
     *            Object - The object.
     * @param annoClazz
     *            Class<?> - The class for the given value.
     * @return T - The value.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnnotatedValue(Object obj,
            Class<? extends Annotation> annoClazz) {
        T retVal = null;
        if (obj != null) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(annoClazz)) {
                    retVal = (T) getFieldValue(obj, field);
                    break;
                }
            }
        }
        return retVal;
    }

    /**
     * Fetches the value of a given field.
     * 
     * @param obj
     *            Object - The object from which we fetch the field.
     * @param field
     *            Field - Field.
     * @return Object - Null if the field is not found or the value is null.
     */
    public static Object getFieldValue(Object obj, Field field) {
        Object retVal = null;
        try {
            String fieldName = field.getName();
            StringBuilder methName = new StringBuilder("get"); //$NON-NLS-1$
            methName.append(fieldName.substring(0, 1).toUpperCase(
                    Locale.ENGLISH));
            methName.append(fieldName.substring(1));
            Method meth = getMethod(methName.toString(), obj.getClass());
            if (meth != null) {
                retVal = meth.invoke(obj);
            }
        } catch (Throwable t) {
            // Pass
        }
        return retVal;
    }

    /**
     * Fetches the given method.
     * 
     * @param methodName
     *            String - Name of method to be called.
     * @param clazz
     *            Class<?> - Class to be invoked.
     * @return Method - If method is found, else Null.
     */
    public static Method getMethod(String methodName, Class<?> clazz) {
        Method retVal = null;
        for (Method meth : clazz.getDeclaredMethods()) {
            if (meth.getName().equals(methodName)) {
                retVal = meth;
                break;
            }
        }
        Class<?> superClazz = clazz.getSuperclass();
        if (retVal == null && superClazz != null
                && !superClazz.equals(Object.class)) {
            retVal = getMethod(methodName, superClazz);
        }
        return retVal;
    }

}
