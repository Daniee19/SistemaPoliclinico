package com.practicandoweb.sistemaclinica.modelo.clasesMetodos;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes {

    static String llave = "esponja";

    //Generar clave
    public static SecretKeySpec generarClave(String llave, byte[] salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        /**
         * Se utiliza SecretKeySpec, para poder poder guardar internamente la
         * clave que se encuentra en un array de tipo byte[], con el tipo de
         * algoritmo que se va a tener que realizar que en este caso es "AES".
         */
        try {
            //Implementar claves aleatorias
            //Combinar salt con la llave
            byte[] llaveBytes = llave.getBytes("UTF-8");
            byte[] combined = new byte[(salt.length + llaveBytes.length)];
            /**
             * Mostrar salt
             */
            String saltTipear = Base64.getEncoder().encodeToString(salt);
            System.out.println("El salt es el siguiente: -> " + saltTipear);
            /**
             * Mostrar llaveBytes
             */
            String tipear_llaveBytes = Base64.getEncoder().encodeToString(llaveBytes);
            System.out.println("El llaveBytes es el siguiente: -> " + tipear_llaveBytes);

            //arrayOrigen, posicionOrigen, arrayDestino, posicionDesino, tamaño
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(llaveBytes, 0, combined, salt.length, llaveBytes.length);

            /**
             * Mostrar Combined
             */
            String base64Salt = Base64.getEncoder().encodeToString(combined);
            System.out.println("El combined es el siguiente: -> " + base64Salt);
            /**
             * Devuelve un nuevo array de bytes por medio del algoritmo SHA-256
             */
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            /*
            Obtiene la ubicación Hash
             */
            byte[] hash = md.digest(combined);
            //Crea una nueva matriz
            byte[] claveAES = Arrays.copyOf(hash, 16);
            //claveAES es la clave la cual obtuvo diferentes cambios para poder hacer mucho mas segura la contraseña
            //AES viene a ser el algoritmo que se va a aplicar en esa clave
            return new SecretKeySpec(claveAES, "AES");

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;

        }

    }

    //Encriptar
    public static String encriptar(String encriptar) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        try {
            //Traemos 
            SecureRandom sr = new SecureRandom();
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            String base64Salt = Base64.getEncoder().encodeToString(salt);
            System.out.println(base64Salt);
            //Generar una clave con respecto al salt y la contraseña que en este caso sera estatica
            //La key viene a ser la llave para poder añadirle esa particularidad
            SecretKeySpec key = generarClave(llave, salt); //Hey key, ya te di el tipo de cifrado y la clave identificadora de este
            byte[] iv = new byte[16];
            sr.nextBytes(iv); // Generar un IV aleatorio para su uso unico y exclusivo por cada encriptacion

            //Cifrar datos usando AES
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encriptado = cipher.doFinal(encriptar.getBytes("UTF-8"));
            byte[] encriptadoConSalt = new byte[salt.length + iv.length + encriptado.length];
            System.arraycopy(salt, 0, encriptadoConSalt, 0, salt.length);
            System.arraycopy(iv, 0, encriptadoConSalt, salt.length, iv.length);
            System.arraycopy(encriptado, 0, encriptadoConSalt, salt.length + iv.length, encriptado.length);
            //Concantenar salt y el texto cifrado

            //Codificar en base64 para el almacenamiento
            return Base64.getEncoder().encodeToString(encriptadoConSalt);
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Desencriptar
    public static String decrypt(String valorEncriptado) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //Decodificar desde Base64
        byte[] valorByte = Base64.getDecoder().decode(valorEncriptado); //decoder->de String a byte[]-> de daeadfnjafa a binario
        //Extraer salt, agregar un padding y extraer el texto cifrado
        byte[] salt = Arrays.copyOfRange(valorByte, 0, 16);
        byte[] iv = Arrays.copyOfRange(valorByte, 16, 32);
        byte[] encriptado = Arrays.copyOfRange(valorByte, 32, valorByte.length);

        //Generar clave y contraseña (para ello necesitamos la misma contraseña con la que hemos cifrado).
        SecretKeySpec key = generarClave(llave, salt);

        //Decifrar datos
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        byte[] desencriptado = cipher.doFinal(encriptado);

        return new String(desencriptado);
    }
}
