package cz.deriva.commons;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * Created by jirka on 12.03.18.
 *
 * @author Jiri Nemec
 */
public final class CryptoUtils {

    private final static SecureRandom random = new SecureRandom();

    private CryptoUtils() {
    }

    public static int registrationCode() {

        Random r = new Random();
        return r.ints(100000, (999999 + 1)).findFirst().getAsInt();

    }

    public static boolean matchesHash(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    public static boolean hashCompare(String value1, String value2) {
        return StringUtils.equals(value1, value2);
    }

    public static byte[] getRandomBytes(int bytesCount) {
        byte[] bytes = new byte[bytesCount];
        CryptoUtils.random.nextBytes(bytes);
        return bytes;
    }


    public static String obfuscate(String secretValue) {
        return "[SECURED]";
    }

    public static byte[] sha512Key(char[] password, byte[] salt, int iterations) {
        return CryptoUtils.derivateKey(password, salt, iterations, 512, "PBKDF2WithHmacSHA512");
    }

    public static byte[] sha256Key(char[] password, byte[] salt, int iterations) {
        return CryptoUtils.derivateKey(password, salt, iterations, 256, "PBKDF2WithHmacSHA256");
    }

    private static byte[] derivateKey(char[] password, byte[] salt, int iterations, int keyLength, String algorithm) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Could not appendInternalInformation hash", e);
        }

    }

}
