package prvalabos2021;

import java.io.File;
import java.io.FileNotFoundException;

public class FindLargestDoc {

    public static final String filePath = "C:\\Users\\Martina\\Desktop\\OS2019";


    public static void main(String[] args) throws FileNotFoundException {
        // direktno vo main namesto poseben metod
        File f = new File(filePath);
        if (!f.isDirectory()) {
            throw new FileNotFoundException();
            // ako f ne e folder...
        }
        File[] files = f.listFiles();
        // lista files vo foldert

        long largest = 0;
        String fileName = "";

        for (File file : files) {
            if (!file.isFile()) {
                continue;
                // samo files vo folderot ne interesiraat
            }
            if (file.isFile()) {
                if (file.length() > largest) {
                    largest = file.length();
                    fileName = file.getName();
                }
            }

        }
        System.out.println("Ime na najgolemiot file vo folderot: " + fileName + ", golemina: " + largest);


    }
}
