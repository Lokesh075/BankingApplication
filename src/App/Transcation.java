package App;

public class Transcation {
     int id;
     String type;
    int amount;
    int balance;
    public Transcation(int id,String type,int amount,int balance) {
        this.balance = balance;
        this.type=type;
        this.amount=amount;
        this.id=id;

    }
    @Override
    public String toString() {
        return id+
                "\t"+type
                +"\t"+amount
                +"\t"+balance;
    }
}
