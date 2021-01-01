package javaioaudasist1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {

    public static void listAllFilesWithRecursion() {

        List<File> allFilesInAllLevels = new ArrayList<>();
        // ArrayList implements List interface (koj obezbed. operacii vrz podat. od tip File)
        // ke chuvame File obj. vo podat. struktura resizable array

        // povik na f-ja za rekurzivno listanje na potfolderi pocnuvajki od starting point
        // vo datotecniot sistem i nivnite contents
        listFilesRecursive("C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020", allFilesInAllLevels);

        // .size() metod - get the number of elements in this list
        System.out.println("Total files and folders in this dir: " + allFilesInAllLevels.size());


        long totalSize = 0;
        // na pocetok e prazna listata


        for (File f : allFilesInAllLevels) {
            // za site file objects vo listata - soberi ja size
            // vo mojot slucaj ke zeme file size na directories Lab, Vezhbi i Predavanje i potoa vo vnatreshnite folderi posebno pak
            // ke ja dodade size + size na sekoj file iako real size e samo prvichnata na trite folderi
            totalSize += f.length();
        }

        System.out.println("Total size of all files in all directories: " + totalSize);
        // povik na metod
        // writeTotalSizeToFile(totalSize);
    }

    //public static void writeTotalSizeToFile(long totalSize) {
    //}


    // REK. METOD za listanje na site potfolder od starting point vo file system-ot
    public static void listFilesRecursive(String currentPath, List<File> allFilesInAllLevels) {
        // vo currentPath imame pateka do Operativni 2020 folderot
        // i lista - resizable array koja chuva dirs/files od tip File - prazna na pochetok

        File osFilesDirectory = new File(currentPath);

        if (!osFilesDirectory.exists())
            return;
        // izlezi od metod ako ne postoi ovoj folder

        // .listFiles() - folderi ili files vo Operativni 2020 (folder pathname vo osFilesDir object) smesti gi vo nizaFiles
        File[] nizaFiles = osFilesDirectory.listFiles();

        for (File f : nizaFiles) {
            // za sekoj obj. od tip File vo taa niza od files/dirs vo osFilesDir folderot
            // APPEND na folder/file na kraj od listata - dodaj go elementot
            allFilesInAllLevels.add(f);
        }

        for (File f : osFilesDirectory.listFiles()) {
            // za sekoj File obj. vo osFilesDir folderot
            if (f.isDirectory()) {
                // dokolku f e folder
                // rekurziven povik na f-ja vlez vo folderot i negovite files da se dodadat vo list -
                // allFiles - pa potoa ako ima folder vnatre vo toj folder povtori ja postapkata
                // otherwise kachi se vo pogorni nivoa i prodolzhi za tie folderi da gi dodavash na array list.
                listFilesRecursive(f.getAbsolutePath(), allFilesInAllLevels);
            }
        }

    }


    public static void main(String[] args) {

        // povik na metodi
        listAllFilesWithRecursion();

    }


}
