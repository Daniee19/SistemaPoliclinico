/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practicandoweb.sistemaclinica.prueba;

import com.practicandoweb.sistemaclinica.modelo.clasesMetodos.Aes;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author danie
 */
public class Prueba {

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {


        String texto = "hola";
        String valor = Aes.encriptar(texto);
        System.out.println("sdadsa a" +valor);
        System.out.println("-------");
        String valor_des = Aes.decrypt(valor);
        System.out.println(valor_des);
//        String base64Salt = Base64.getEncoder().encodeToString(valor);

//        System.out.println(base64Salt);

    }
}
