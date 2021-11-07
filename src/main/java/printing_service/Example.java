package printing_service;

public class Example {

    private int inner = 0;

    public int getInner(){
        return inner;
    }

    public int getAndIncrementInner(){
        int old = inner;
        incrementInner();
        return old;
    }

    public void incrementInner(){ inner++; }

    public static String horOrCold(int temp){

        if(temp > 20)
            return "HOT";
        return "COLD";
    }

    public static int otherObject(Example e, boolean b){
        if(b){
            return e.getInner();
        }

        return 200;
    }
}
