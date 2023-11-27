package App;
import java.io.*;
import java.util.*;
public class Filehandler {
     static Filehandler handler;
    public static Filehandler getinstance() {
        if (handler == null) {
            handler = new Filehandler();
        }
        return handler;
    }
    private static final String filename = "Bank.txt";
    public void initialize() throws IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String customerinfo= reader.readLine();
        do {
            Customer cus = convert(customerinfo);
            Bank.map.put(cus.id, cus);
            Bank.customers.add(cus);
            File file1=new File(cus.id+".txt");
            if(!file1.exists()){
                Transcation act=new Transcation(1,"Opening",0,cus.balance);
                TransactionHandler hadler=new TransactionHandler();
                hadler.write(cus.id, act);
            }
            customerinfo = reader.readLine();
        } while (customerinfo != null);

        reader.close();
        int refpos = Bank.customers.size() - 1;

        Bank.refaccid = Bank.customers.get(refpos).accountid;
        Bank.refid = Bank.customers.get(refpos).id;
    }

    public void addcustomer(Customer customer) {
        File file = new File(filename);
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.write("\n" + customer.toString());
        } catch (IOException e) {

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void finalise(){
        File file = new File(filename);
        FileWriter writer = null;
            try {
            writer = new FileWriter(file);
            Set keyset=Bank.map.keySet();
            Iterator itr= keyset.iterator();
            while(itr.hasNext()){
                int cusid=(int) itr.next();
                Customer cust=Bank.map.get(cusid);
                writer.write("\n" + cust.toString());
            }
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Customer convert(String customerinfo) {
        String[] info = customerinfo.split("\\s");
        Customer customer = new Customer(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Integer.parseInt(info[3]), info[4]);
        return customer;
    }

}