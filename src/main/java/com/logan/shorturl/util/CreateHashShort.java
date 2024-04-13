package com.logan.shorturl.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateHashShort {
    public static String crearHash(String texto) {
        try {
            byte[] hashBytes = calcularSHA256(texto);

            String hashCorto = obtenerHashCorto(hashBytes);

            return hashCorto;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] calcularSHA256(String texto) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(texto.getBytes(StandardCharsets.UTF_8));
    }

    private static String obtenerHashCorto(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.substring(0, 8) + hexString.substring(hexString.length() - 3);
    }
}
