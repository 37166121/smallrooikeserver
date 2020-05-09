package user.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/11 13:11
 */
public class Base64Util {
    /**
     * String To Base64
     * @param str
     * @return
     */
    public static String strConvertBase(String str) {
        if(null != str){
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(str.getBytes());
        }
        return null;
    }

    /**
     * Base64 To String
     * @param str
     * @return
     */
    public static String baseConvertStr(String str) {
        if (null != str) {
            Base64.Decoder decoder = Base64.getDecoder();
            try {
                return new String(decoder.decode(str.getBytes()), "GBK");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return null;
    }
}
