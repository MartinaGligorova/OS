package javaio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FileClassOperations {
    // java.io package, classes that deal with input / output, abstract classes, stream oriented, methods and
    // subclasses which allow data to be read from and written to files.
    // InputStream and OutputStream central classes in this package.
    // File class - library utility - used for manipulation of the file system that holds the files and dirs.

    // File class obezbeduva metodi za manipulacija na datotechniot sistem.

    public static void main(String[] args) throws IOException, InterruptedException {
        // kreirame objekt od tip File za pristap do metodite od File class.
        // pri kreiranje se prinesuva String ime na file-ot - sekoj file e identifikuvan preku patekata do nego vo file system
        File fileObj = new File("C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020\\Vezhbi\\newfilecreated.txt");
        // nov obj. od tip file so folder "PrvFolderKreiran" identified so pathname
        File fileObj2 = new File("C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020\\Vezhbi\\PrvFolderKreiran");
        // nov obj. od tip file so nov folder + novi parent dirs.
        File fileObj3 = new File("C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020\\Vezhbi\\PrvParentFolder\\prvFolderVoFolder");


        // KREIRAJ NOV, PRAZEN FILE oznachen preku pathname na fileObj ako i samo ako ne postoi takov file preth.
        fileObj.createNewFile();
        // KREIRAJ FOLDER (koj e identified so pathname)
        fileObj2.mkdir();
        // KREIRAJ FOLDER + PARENT FOLDERS (DOKOLKU NE POSTOJAT) identified so pathname na object.
        fileObj3.mkdirs();


        if (fileObj.exists()) {
            // proverka dali postoi kreiraniot file
            System.out.println("Exists");
            // isDirectory() metod - testira dali file-ot e folder - ako e folder vrakja TRUE, ako ne e vrakja FALSE
            System.out.println("Dali e folder: " + fileObj.isDirectory());
            // test na permisii na procesot(aktivnost vo izvrsh.) vrz file-ot (koi permisii gi ima procesot vrz file)
            // .canRead() dali mozhe da se chita ovoj file - true/false
            System.out.println("Dali mozhe da se prochita file: " + fileObj.canRead());
            // .canWrite() dali mozhe da se modificira ovoj file (od str. na procesot)
            System.out.println("Dali mozhe da se zapishuva vo file: " + fileObj.canWrite());
            // .canExecute() dali mozhe da se izvrshuva file-ot od str. na procesot - true/false izlez
            System.out.println("Dali mozhe da se izvrshuva ovoj file: " + fileObj.canExecute());
            // imeto na file
            System.out.println("Imeto na file-ot: " + fileObj.getName());
            // apsolutna pateka na file-ot definirana vo pathname ili null dokolku ne e navedeno koi se parent dirs.
            System.out.println("Apsolutna pateka na file-ot: " + fileObj.getAbsolutePath());
            // roditelska apsolutna pateka od koren do prv parent node na file-ot dokolku se definirani vo pathname null ako ne se
            System.out.println("Roditelska pateka na file-ot: " + fileObj.getParent());
            // vreme od tip long promenliva - koga file-ot bil izmenet posledno ili 0L ako ne postoi toj file - ms
            System.out.println("Posledno modificiran: " + fileObj.lastModified());
            // returns the length of the file - tip long - prazen file: izlez 0
            // pr. vo file-ot od tip .txt ima: Martina G. -> length ke vrati 10 - broi i prazni mesta
            System.out.println("Dolzhina na file-ot: " + fileObj.length());
            // izbrishi file
            // fileObj.delete()

            // FOLDERS fileObj2 i mkdir etc.


            // setLastModified()
            long broj = 2000000;
            fileObj.setLastModified(broj);
            System.out.println("Koga e posl. modificiran file-ot: " + fileObj.lastModified());

            System.out.println("Dali e hidden file-ot: " + fileObj.isHidden());


        }

        // testing za renaming na file
        Path oldPath = Paths.get("C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020\\Vezhbi\\newfilecreated.txt");
        Files.move(oldPath, oldPath.resolveSibling("renamedfile.txt"));
        // do tuka file e successfully renamed - dokolku sakash da prodolzis so operacii vrz toj file->
        fileObj = new File(String.valueOf(oldPath.resolveSibling("renamedfile.txt")));

        // permisii na procesot vrz file-ot se: read only
        fileObj.setReadOnly();

        System.out.println("Dali e file: " + fileObj.isFile());


        // povik na metodi
        // directoryListing();

    }
}
