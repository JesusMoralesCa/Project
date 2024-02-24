package com.jmorales.springbootreact.Serialice;


import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobUtil {

    public static String convertBlobToBase64(Blob blob) {
        if (blob == null) {
            return null;
        }

        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
            return null;
        }
    }


}
