package com.example.biblioteca.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

public class Utils {

    public static String encodeUserPassword (String userPassword){

        return configEncryptor().encrypt(userPassword);

    }

    public static String decodeUserPassword (String encodedUserPassword){

        return configEncryptor().decrypt(encodedUserPassword);

    }

    private static StandardPBEStringEncryptor configEncryptor(){

        StandardPBEStringEncryptor passwordHandler = new StandardPBEStringEncryptor();
        passwordHandler.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        passwordHandler.setPassword("TeamRocket");
        passwordHandler.setIvGenerator(new RandomIvGenerator());
        return passwordHandler;

    }
}

