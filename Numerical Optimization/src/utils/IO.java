package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IO {

    public static String folder;

    public static File[] listFiles(String directoryName) {

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        return fList;
    }

    public static void appendToFile(String append, String path) {
        FileWriter fstream = null;
        try {
            fstream = new FileWriter(path, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(append);
            out.newLine();
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fstream.close();
            } catch (IOException ex) {
                Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String textFileToString(String file) {
        String ret = "";
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                    file)))) {
                while (reader.ready()) {
                    ret += reader.readLine();
                }
            }
            return ret;
        } catch (IOException ex) {
        }
        return ret;
    }

    public static void writeToFile(InputStream inputStream, String file) throws FileNotFoundException, IOException {

        OutputStream outputStream = null;

        outputStream = new FileOutputStream(new File(file));
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        outputStream.close();
        inputStream.close();

    }

    public static void writeToLog(String file, String content) {
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println(content);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(IO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static byte[] fileToByteArray(String filePath) throws FileNotFoundException, IOException {

        File file = new File(filePath);

        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }

        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
