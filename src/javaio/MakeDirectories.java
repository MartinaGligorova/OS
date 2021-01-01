package javaio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeDirectories {
    // directory manipulation

    // final var - constant
    private final static String usage = "Usage: MakeDirectories path1 ...\n" +
            "Creates each path\n" + "Usage: MakeDirectories -d path1 ...\n" +
            "Deletes each path\n" + "Usage: MakeDirectories -r path1 path2\n" +
            "Renames from path1 to path2\n";

    // metod
    private static void usage() {
        // print error messages
        System.err.println(usage);
        // unsuccessful/abnormal termination
        System.exit(1);
    }

    // metod
    private static void fileData(File f) {
        // metod prima obj. od tip File (file/dir) na vlez
        System.out.println("Absolute path: " + f.getAbsolutePath() +
                "\n Can read: " + f.canRead() + "\n Can write: " + f.canWrite() + "\n getName: " + f.getName() +
                "\n getParent: " + f.getParent() + "\n getPath: " + f.getPath() + "\n length: " + f.length() +
                "\n lastModified: " + f.lastModified());

        if (f.isFile())
            System.out.println("It's a file");
        else if (f.isDirectory())
            System.out.println("It's a directory");
    }

    public static void main(String[] args) throws IOException {
        // read stream of characters from input
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        String [] podniza = s.split("  ");
        // args type is String[]
        args = podniza;
        if (args.length < 1)
            usage();
        // print error msgs - pomosh pri kreiranje na vlez
        if (args[0].equals("-r")) {
            if (args.length != 3)
                // za uspeshno renaming na vlez-> -r, path1, path2
                usage();
            // old pathname
            File old = new File(args[1]);
            // new pathname
            File rename = new File(args[2]);
            old.renameTo(rename);
            // povik na metod fileData(File f) za old file informacii pechati
            fileData(old);
            fileData(rename);
            return; // Exit main
        }

        int count = 0;
        boolean del = false;
        if (args[0].equals("-d")) {
            // na vlez -d path1 ...
            count++;
            del = true;
        }
        for (; count < args.length; count++) {
            // ischitaj go vlezot - paths
            // args[count] = path1 ...
            File f = new File(args[count]);
            if (f.exists()) {
                // f e identified so folder path -> i.e toa e na izlez + exists
                System.out.println(f + " exists");

                if (del) {
                    // del = true
                    // delete file/dir denoted by path
                    System.out.println("deleting..." + f);
                    f.delete();
                }
            } else { // Doesn't exist
                if(!del){
                    // del = false
                    // kreiraj files/dirs denoted by pathname
                    f.mkdirs();
                    System.out.println(" created " + f);
                }
            }
            // metod - pechati info za file/dir
            fileData(f);

        }
    }
}
