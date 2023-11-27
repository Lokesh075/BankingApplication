package App;

import java.io.File;

public class Customer {
    public int id;
    public String name;
    int accountid;
    String password;
    int balance;
    public static File file;
    public Customer(int id,int accountid,String name, int balance,String password) {
        this.id=id;
        this.name = name;
        this.accountid=accountid;
        this.password=password;
        this.balance=balance;

    }




    @Override
    public String toString() {
        return id+" "+accountid+" "+name+" "+balance+" "+password;
    }
}
