package com.nk.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-06-10 10:26
 * 项目名称: KunPro
 * 文件名称: CardAesUtil
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Slf4j
public class CardAesUtil {

    private static final String ALGORITHM = "AES";

    private static Cipher cipher = null;

    private static IvParameterSpec ivParameterSpec = null;

    private static SecretKeySpec skeySpec = null;

    /**
     * 密匙
     */
    private static byte[] KEY = null;

    /**
     * IV对称加密
     */
    private static byte[] IVBytes = null;
    /**
     * 协议数据标识
     */
    public static byte[] PROTOCOL_END_MARK = null;


    /**
     * 初始化
     *
     * @throws Exception
     */
    public static void init() throws Exception {
        KEY = decodeHex("96B6715EF50FA4557F6CF977178E86C9");
        IVBytes = decodeHex("11C500740BE44D4EE5BDAED03CE76FFF");
        PROTOCOL_END_MARK = "#kdsjafjalsdjg#170".getBytes(StandardCharsets.UTF_8);

        skeySpec = new SecretKeySpec(KEY, ALGORITHM);
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        ivParameterSpec = new IvParameterSpec(IVBytes);
    }


    /**
     * 下发数据加密
     *
     * @param sendStr
     * @return
     */
    public static byte[] encryptBsj(byte[] sendStr) {
        try {
            byte[] encrypt = encrypt(sendStr);
            byte[] protocolEndMark = PROTOCOL_END_MARK;
            byte[] needSend = concatAll(encrypt, protocolEndMark);
            return needSend;
        } catch (Exception e) {
            log.error("数据加密错误:" + e.getMessage());
            return null;
        }
    }

    /**
     * 上传数据解密
     *
     * @param sSrc
     * @return
     */
    public static String decryptBsj(String sSrc) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            //先用base64解密
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original);
        } catch (Exception ex) {
            log.error("数据解密有问题:" + sSrc);
            return null;
        }
    }

    /**
     * 加密
     *
     * @param sSrc
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] sSrc) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(sSrc);
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return Base64.getEncoder().encode(encrypted);
    }

    /**
     * 组合
     *
     * @param a
     * @param b
     * @return
     */
    private static byte[] concatAll(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 十六进制转字节数组
     *
     * @param data
     * @return
     * @throws DecoderException
     */
    public static byte[] decodeHex(final String data) throws DecoderException {
        return decodeHex(data.toCharArray());
    }

    /**
     * Converts an array of characters representing hexadecimal values into an array of bytes of those same values. The
     * returned array will be half the length of the passed array, as it takes two characters to represent any given
     * byte. An exception is thrown if the passed char array has an odd number of elements.
     *
     * @param data An array of characters containing hexadecimal digits
     * @return A byte array containing binary data decoded from the supplied char array.
     * @throws DecoderException Thrown if an odd number or illegal of characters is supplied
     */
    public static byte[] decodeHex(final char[] data) throws DecoderException {

        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new DecoderException("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * Converts a hexadecimal character to an integer.
     *
     * @param ch    A character to convert to an integer digit
     * @param index The index of the character in the source
     * @return An integer
     * @throws DecoderException Thrown if ch is an illegal hex character
     */
    protected static int toDigit(final char ch, final int index) throws DecoderException {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static void main(String[] args) throws Exception {
        String str = "jlGLftaC2iuQFsz8YPxqxozkfbJOJmWZ4PSr+ErhWo5s7rB89EnpXvYtr8f4ae92SqY40YClZtClnEObWbSLNpZFjFRODBmd2UmVbh5jvvcB8HjEYBSJeA2Hk5cSkioj";
        CardAesUtil.init();
        String decrypt = CardAesUtil.decryptBsj(str);
        System.out.println("解密：" + decrypt);
        byte[] needSend = CardAesUtil.encryptBsj(decrypt.getBytes());
        System.out.println("加密#厂商标识#协议版本号：" + new String(needSend));
    }


}
