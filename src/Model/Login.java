package Model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class Login {
    private ArrayList<String> userNames = new ArrayList<>();
    private HashMap<String, String> passwords = new HashMap<>();
    private static SecretKeySpec secretKey;
    private final static String keyString = "QeThWmZq4t7w!z%C*F-JaNcRfUjXn2r5";
    private static byte[] key;

    protected Login() { }

    public void newUser(String userName, String password) {
        userNames.add(userName);
        passwords.put(userName, encrypt(password));
    }

    public void removeUser(String userName, String password) {
        if (userNames.contains(userName)) {
            if (decrypt(passwords.get(userName)).equals(password)) {
                passwords.remove(userName);
                userNames.remove(userName);
            }
        }
    }

    public boolean isLoginInformationCorrect(String userName, String password) {
        if (userNames.contains(userName)) {
            if (decrypt(passwords.get(userName)).equals(password)) {
                return true;
            }
        }
        return false;
    }

    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String strToEncrypt) {
        try {
            setKey(keyString);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    private static String decrypt(String strToDecrypt) {
        try {
            setKey(keyString);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
