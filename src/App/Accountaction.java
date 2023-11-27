package App;

import java.io.File;
import java.io.IOException;

public class Accountaction {
    public void deposit(int id, int amount,boolean yes) {
        if (amount < 0) {
            return;
        }
        Customer c = Bank.map.get(id);
        c.balance += amount;
        Bank.map.put(id, c);
        TransactionHandler trans = new TransactionHandler();
        int lastid = trans.getlastId(c.id);
        lastid++;
        if(yes){
            Transcation act = new Transcation(lastid, "Deposit",amount, c.balance);
            trans.write(c.id, act);
        }
        Transcation act = new Transcation(lastid, "Transfer",amount, c.balance);
        trans.write(c.id, act);
    }

    public boolean withdraw(int id, int amount,boolean yes) {
        Customer c = Bank.map.get(id);
        int newb = c.balance - amount;
        if (newb < 1000) {
            System.out.println("Insufficient balance");
            return false;
        } else {
            c.balance = newb;
            Bank.map.put(id, c);
            TransactionHandler trans = new TransactionHandler();
            int lastid = trans.getlastId(c.id);
            lastid++;
            if(yes){
                Transcation act = new Transcation(lastid, "WithDraw", amount, newb);
                trans.write(c.id, act);
                return true;
            }
            Transcation act = new Transcation(lastid, "Transfer", -amount, newb);
            trans.write(c.id, act);
            return true;
        }

    }

    public void transfer(int fromid, int toid, int amt) {
        Customer c = Bank.map.get(toid);
        if (c == null) {
            System.out.println("Give valid account-ID to transfer");
            return;
        }
        Customer from = Bank.map.get(fromid);
        boolean success = withdraw(from.id, amt,false);
        if (success) {
            deposit(toid, amt,false);
        }

    }
}
