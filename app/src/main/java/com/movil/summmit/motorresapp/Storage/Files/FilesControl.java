package com.movil.summmit.motorresapp.Storage.Files;

import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.movil.summmit.motorresapp.Storage.PreferencesHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by TELEFONICA on 4/11/2017.
 */

public class FilesControl {

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), albumName);
        if (!file.exists())
        {
            if (!file.mkdirs()) {
                Log.d("LOG_FILE", "Directory not created");
            }
        }

        return file;
    }

    public File getAlbumStorageDirEstacion(String nombreCarpeta) {
        // Get the directory for the user's public pictures directory.

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/CARPETA_MOTORRED"), nombreCarpeta);
        if (!file.exists())
        {
            if (!file.mkdirs()) {
                Log.d("LOG_FILE", "Directory not created");
            }
        }

        return file;
    }

    public File getFile(String id, String fileName) {


        String carpetanombre = "INFORME_" + id;

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/CARPETA_MOTORRED"), carpetanombre + "/" + fileName);

        return file;
    }

    public File getpruebauno() {
        // Get the directory for the user's public pictures directory.

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/CARPETA_MOTORRED"), "INFORME_1/sisale.txt");
        return file;
    }

    public File getpruebados() {
        // Get the directory for the user's public pictures directory.

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/CARPETA_MOTORRED"), "INFORME_1/otromas.txt");
        return file;
    }

    public File getpruebatres() {
        // Get the directory for the user's public pictures directory.

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/CARPETA_MOTORRED"), "INFORME_1/otrofile.txt");
        return file;
    }

    public String getFileName(String path)
    {
        return path.substring(path.lastIndexOf("/")+1);
    }

    public void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


}
