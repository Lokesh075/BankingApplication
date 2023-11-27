package App;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class TransactionHandler {
    public void write(int customerid, Transcation transcation) {
        String filename = customerid + ".txt";
        try {
            File file = new File(filename);
            boolean isnew = false;
            if (!file.exists()) {
                file.createNewFile();
                isnew = true;
            }
            FileWriter writer = new FileWriter(file, true);
            if (!isnew) {
                writer.write("\n");
            }
            writer.write(transcation.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

    }
    public void printhistory(int id) {
        String filename = id + ".txt";
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("No Transaction yet");
                return;
            }
            Scanner sc = new Scanner(file);
            String trans = "";
            while (sc.hasNext()) {
                trans = sc.nextLine();
                System.out.println(trans);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
    public int getlastId(int customerid) {
        String filename = customerid + ".txt";
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return 0;
            }
            Scanner sc = new Scanner(file);
            String trans = "";
            while (sc.hasNext()) {
                trans = sc.nextLine();
            }
            sc.close();
            Transcation newtrans = caststring(trans);
            return newtrans.id;

        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return 0;
    }
    private Transcation caststring(String trans) {
        String[] arr = trans.split("\t");
        return new Transcation(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
    }
}
