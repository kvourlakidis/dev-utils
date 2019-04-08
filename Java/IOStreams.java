import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.BiConsumer;

/**
 * Examples of using base Java IO Streams.
 * Mostly taken from:
 * https://docs.oracle.com/javase/tutorial/essential/io/buffers.html
 */
public class IOStreams {
    public static String inputFile = "xanada.txt";
    public static String outputFile = "output.txt";


    public static void main(String[] args) {
        // validateAndRun(IOStreams::copyBytes, inputFile, outputFile);
        // validateAndRun(IOStreams::copyCharacters, inputFile, outputFile);
        validateAndRun(IOStreams::copyLines, inputFile, outputFile);
    }

    /**
     * Copy the contents of a into b using FileInputStream and FileOutputStream classes.
     * Reads one byte at a time from 'a' into an int type, and writes same byte to 'b'.
     */
    public static void copyBytes(File a, File b) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(a);
            fos = new FileOutputStream(b);
            int c;
            while((c = fis.read()) != -1) {
                fos.write(c);
                System.out.println(c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void copyCharacters(File a, File b) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(a);
            fw = new FileWriter(b);
            int c;
            while((c = fr.read()) != -1) {
                fw.write(c);
                System.out.println(c);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void copyLines(File a, File b) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new FileReader(a));
            pw = new PrintWriter(b);
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
                ex.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (pw != null) pw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void validateAndRun(BiConsumer<File,File> func, String file1, String file2) {
        File a = new File(file1);
        File b = new File(file2);
        if (!a.exists()) {
            System.err.println(file1 + " does not exist. Exiting.");
            return;
        }
        if (b.exists()) {
            System.out.println(file2 + " exists. Deleting.");
            if (b.delete()) {
                System.out.println(file2 + " was successfully deleted.");
            } else {
                System.err.println(file2 + " could not be deleted. Exiting.");
                return;
            }
        }
        func.accept(a, b);
        System.out.println(func + " call finished.");
    }
}