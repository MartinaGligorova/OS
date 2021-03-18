package prvalabos2021;

import java.io.File;
import java.io.FileNotFoundException;

public class Lab {

    public static final String filePath = "C:\\Users\\Martina\\Desktop\\OS2019";


    public static void main(String[] args) throws FileNotFoundException {

        File f = new File(filePath);
        if(!f.isDirectory()){
            throw new FileNotFoundException();
            // ova ke predizvika vo signature na metod da se frli istiot iskl.
        }

        File[] files = f.listFiles();


        for(File file : files){
            if(file.isDirectory()){
                continue;
                // ne vleguvame vo potfolderi - ako nesto e folder prodolzhi - prvo nivo
            }
            if(file.isFile() && file.getName().endsWith(".dat")){
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
