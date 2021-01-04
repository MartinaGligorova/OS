package javaio;

import java.io.*;


class DirFilter implements FilenameFilter {

    String afn;

    // konstruktor
    // obj. od class DirFilter se incijalizira na String od vlez
    public DirFilter(String afn) {
        this.afn = afn;
    }

    @Override
    public boolean accept(File dir, String name) {
        // dir e parent dir. na file-ot, name e ime na file-ot
        // true - file-ot odreden so name ke bide vraten pri .list() povik
        // false - file-ot nema da bide vraten na izlez

        String zemiTekoven = new File(name).getName();


        return zemiTekoven.indexOf(afn) != -1;
    }
}

public class DirectoryListing {
    // Rekurzivno izlistaj site potfolderi od pocetna tocka vo datotechniot sistem, ispechati file permisii

    // metodot prima apsolutna pateka i prefix na vlez
    public static void listFile(String absolutePath, String prefix) {
        // newObj folder so absolutePath do istiot
        File newObj = new File(absolutePath);

        if (newObj.exists()) {
            // listFiles() niza files(od tip File) na folderot identifik. od newObj(File), otherwise null
            // pr. File[0] = Lab, File[1] = Predavanje etc.
            File[] subfiles = newObj.listFiles();
            for (File f : subfiles) {
                // print the permissions in unix like format
                // getPermissions - permisii za odreden file vo nizata subfiles od nekoj folder newObj - format: rwx ili --- etc.
                System.out.println(prefix + getPermissions(f) + "\t" + f.getName());

                // Rekurzivno
                if (f.isDirectory()) {
                    // rekurziven povik na metodot za odredeniot folder f za pecatenje na subfiles na istiot.

                    // + "\t" zadrzhuvanje na file system structure
                    listFile(f.getAbsolutePath(), prefix + "\t");
                }

            }

        }

    }

    // metod za vrakjanje na permisii na odreden file
    private static String getPermissions(File f) {
        // format() metod, %s%s%s
        // dokolku procesot ima permisii da chita od file f.canRead() ispechati r, inaku -
        return String.format("%s%s%s", f.canRead() ? "r" : "-",
                f.canWrite() ? "w" : '-', f.canExecute() ? "x" : '-');
    }

    // LIST DIRECTORY CONTENT WITH OR WITHOUT A FILTER
    public static void main(String[] args) throws IOException {
        // tekoven folder vo fileOb i.e JavaIOAv1 folderot
        File fileOb = new File(".");
        // .list() metodot vrakja niza od String elementi - contents na folder
        String[] list;
        // args command line args od tip String
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String[] podniza = s.split(" ");
        // podniza[] = "src" test za args.length>0
        // i so 'char' test -> podniza[]={"a"}
        args = podniza;


        if (args.length == 0) {
            list = fileOb.list();
            // .idea, javaioav1.iml, out, src dokolku nema vlez
        } else {
            // site folder != "src" ke gi preskokne
            // filter e obj. od class DirFilter(koja implementira FilenameFilter) koj prima arg od vlez
            // otherwise -> fileOb.list(FilenameFilter f) etc.
            list = fileOb.list(new DirFilter(args[0]));
        }
        for (int i = 0; i < list.length; i++) {
            // vo String[] list e nizata folders i.e iminja na folders
            System.out.println(list[i]);
        }

        // rek. listFile metod

        // folder
        String absolutePath = "C:\\Users\\Martina\\Desktop\\OS2019\\Operativni 2020";
        String prefix = "->";
        listFile(absolutePath, prefix);
    }
}
