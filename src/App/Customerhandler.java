package App;

import java.util.*;

public class Customerhandler {
    public void addcustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer name");
        String name=sc.nextLine();
        System.out.println("Enter password");
        String pw = sc.nextLine();
        System.out.println("Re-Type password");
        String rpw = sc.nextLine();
        if (!pw.equals(rpw)) {
            System.out.println("Incorrect password");
            return;
        }
        Bank.refaccid++;
        Bank.refid++;
        if(!isvalid(pw)){
            System.out.println("Provide valid password");
            return;
        }
        Customer  c=new Customer(Bank.refid,Bank.refaccid,name,Bank.BALANCE,encrypt(pw));
        Bank.customers.add(c);
        Bank.map.put(c.id,c);
        Filehandler.getinstance().addcustomer(c);
        logtransaction(c.id);

    }
    private void logtransaction(int id) {
        Transcation action=new Transcation(1,"Opening",10000,10000);
        TransactionHandler handler=new TransactionHandler();
        handler.write(id,action);
    }
    private boolean isvalid(String  pass){
        char[] arr=pass.toCharArray();
        for (char ch: arr){
            if((ch>= 97 && ch<=122) || (ch>=65 && ch<=90)|| (ch>=48 && ch<=57)){
            }
            else{
                return false;
            }
        }
        return true;
    }
    private String encrypt(String pass){
        char[] arc=pass.toCharArray();
        for(int i=0;i<arc.length;i++){
            if((arc[i]=='z') || (arc[i]=='Z') || (arc[i]=='9')){
                switch (arc[i]){
                    case 'z':
                        arc[i]='a';
                        break;
                    case 'Z':
                        arc[i]='A';
                        break;
                    case '9':
                        arc[i]='0';
                        break;
                }
            }
            else{
                arc[i]=(char) (arc[i]+1);
            }
        }
        String encrypt=String.valueOf(arc);
       return encrypt;
    }
    public boolean authenticate(int id,String password){
        String encrypted=encrypt(password);
        Customer c=Bank.map.get(id);
        if(c==null){
            System.out.println("Invalid user ID");
            return false;
        }
        if(encrypted.equals(c.password)){
            System.out.println(" Valid User");
            return true;
        }
        else{
            System.out.println("Invalid User");
             return false;
        }
    }

    }
