package prvalabos2021;

import java.io.*;

public class CopyReversedContent {
    public static final String sourcePath = "C:\\Users\\Martina\\Desktop\\lab1os2021\\izvor.txt";
    public static final String destinationPath = "C:\\Users\\Martina\\Desktop\\lab1os2021\\destinacija.txt";

    public void copyReversed(File from, File to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;


        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to)));

            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.reverse();
                writer.write(String.valueOf(sb));
                writer.newLine();
                // nov red
                sb.delete(0, sb.length());

            }
        } finally {
            if (reader != null)
                reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File from = new File(sourcePath);
        File to = new File(destinationPath);
        CopyReversedContent obj = new CopyReversedContent();
        obj.copyReversed(from, to);

    }
}
