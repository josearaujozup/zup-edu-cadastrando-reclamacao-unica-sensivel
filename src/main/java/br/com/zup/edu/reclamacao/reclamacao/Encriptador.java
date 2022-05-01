package br.com.zup.edu.reclamacao.reclamacao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptador {
	
	public static String hash(String telefone) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hash = digest.digest(telefone.getBytes(StandardCharsets.UTF_8));
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um telefone: " + telefone);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }
	
}
