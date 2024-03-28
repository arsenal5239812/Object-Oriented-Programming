package lecture03;

public class A {
    int x;
    public int getX(){//getter
        return x;
    }
    public void setX(int x){//setter
        this.x = x;
    }
    private int x;
    static int y;
}
public class TestCircleWithStaticMembers{
    /*Main method*/
    public static void main(String[] args){
        A o1 = new A();
        o1.setX(10);
        o1.y = 15;
        System.out.println(o1.y);

        A o2 = new A();
        o2.setX(20);
        System.out.println(o2.getX());
    }
}