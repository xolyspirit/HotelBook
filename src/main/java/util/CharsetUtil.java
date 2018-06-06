package util;

import java.io.UnsupportedEncodingException;
/**Технический класс для конвертации символов
 * @version 1.0
 * @author Xolyspirit */
public class CharsetUtil {
    /**Метод, конвертирующий строки в нужный набор символов*/
    public static String convert(String input) throws UnsupportedEncodingException {
        return new String(input.getBytes("ISO-8859-1"), "windows-1251");
    }
}
