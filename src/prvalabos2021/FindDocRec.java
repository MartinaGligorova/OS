package prvalabos2021;

import java.io.*;

public class FindDocRec {

    public void findDocsInFolder(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();

        }
        if (!file.isDirectory()) {
            throw new FileNotFoundException();
        }

        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                findDocsInFolder(f);
                // rek povik za folder
            }
            if (f.isFile() && (f.getName().endsWith(".txt") || f.getName().endsWith(".out")) && (f.length() > 1000 && f.length() < 100000)) {
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // InpStrReader converts bytes into chars
        String filePath = bf.readLine();
        File f = new File(filePath);
        FindDocRec obj = new FindDocRec();
        obj.findDocsInFolder(f);

        bf.close();

    }
}
