package diop.licien.cvbackend.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class PhotoUtility {

    // Méthode compressPhoto
    public static byte[] compressPhoto(byte[] data) {
        // création d'une instance Deflater
        Deflater deflater = new Deflater();
        // configuration du niveau de compression à BEST_COMPRESSION
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        // Initialisation du Deflater avec les données d'image
        deflater.setInput(data);
        // Indique au Deflater de terminer son travail
        deflater.finish();

        // Initialisation d'un ByteArrayOutputStream avec une capacité initiale égale à la taille des données d'origine
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        // Tampon temporaire de 4*1024 bytes
        byte[] tmp = new byte[1000000 * 1024];

        // Boucle de compresion par blocs
        while (!deflater.finished()) {
            // Compression des données dans le tampon temporaire
            int size = deflater.deflate(tmp);
            // Écriture du tampon dans le ByteArrayOutputStream
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) { }
        // Retourne les données compressées sous forme de tableau de bytes
        return outputStream.toByteArray();
    }

    // Méthode decompressImage
    public static byte[] decompressPhoto(byte[] data){
        //Création d'une instance Inflater
        Inflater inflater = new Inflater();
        // Initialisation de l'Inflater avec les données compressées
        inflater.setInput(data);

        // Initialisation d'un ByteArrayOutputStream avec une capacité initiale égale à la taille des données compressées
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        // Tampon temporaire de 4*1024 bytes
        byte[] tmp = new byte[1000000 * 1024];
        try {
                // Boucle de décompression par blocs
            while (!inflater.finished()){
                // Décompression des données dans le tampon temporaire
                int count = inflater.inflate(tmp);
                // Écriture du tampon dans le ByteArrayOutputStream
                outputStream.write(tmp,0,count);
            }
            outputStream.close();
        } catch (Exception e) { }
        // Retourne les données décompressées sous forme de tableau de bytes
        return outputStream.toByteArray();
    }
}
