package prvalabos2021;

import java.io.*;

public class ReadContentOfFile {

    public static final String inputFilePath = "C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020\\Predavanje\\Predavanje1Notes.txt";

    public void writeToStdOutput(String inputFilePath) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        // p-livi od tip BufferedReader/Writer - prevent physical read/write - character oriented
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath)));
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
            // destinacja - ekran
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
                // kako sto citas od file - zapisuvaj
            }
        } finally {
            if (reader != null) reader.close();
            // zatvori inputstream - oslobodi sistemski resursi
            if (writer != null) {
                writer.flush();
                // praznenje na bafer
                writer.close();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // ischitaj sodrzhina od file - cija pateka se naogja vo promenlivata inputFilePath i ispechati ja na izlez
        // povik na metod
        ReadContentOfFile obj = new ReadContentOfFile();
        obj.writeToStdOutput(inputFilePath);
    }
}
