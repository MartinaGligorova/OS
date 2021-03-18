package prvalabos2021;

import java.io.File;
import java.io.FileNotFoundException;

public class TestPermisii {


    public boolean getXPermission(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        if (!f.isDirectory()) {
            throw new FileNotFoundException();
        }
        return f.canWrite();


    }

    public static void main(String[] args) throws FileNotFoundException {
        // povik na metod za test na permisii na datoteka
        TestPermisii obj = new TestPermisii();
        String filePath = "C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020";
        System.out.println(obj.getXPermission(filePath));


    }
}
