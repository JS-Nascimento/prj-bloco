package br.edu.infnet.prjbloco.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityWords {


    public static String criptografar(String texto, String key) throws Exception {

        SecretKeySpec chaveSecreta = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta);
        byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
        return Base64.getEncoder().encodeToString(textoCriptografado);
    }

    public static String descriptografar(String textoCriptografado, String key) throws Exception {
        SecretKeySpec chaveSecreta = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chaveSecreta);
        byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder().decode(textoCriptografado));
        return new String(textoDescriptografado);
    }

    public static String keyGenerator() {
        try {

            KeyGenerator geradorChave = KeyGenerator.getInstance("AES");
            geradorChave.init(128);


            SecretKey chave = geradorChave.generateKey();
            String chaveBase64 = Base64.getEncoder().encodeToString(chave.getEncoded());

            return chaveBase64;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erro ao criar gerador de chave: " + e.getMessage());
            return null;
        }
    }
}
