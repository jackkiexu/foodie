package geym.zbase.ch10.brkparent;

public class DemoA {

    public static void main(String[] args) throws Exception{
        ClassLoader cl = DemoA.class.getClassLoader();
        DemoB demoB = (DemoB)cl.getParent().loadClass("geym.zbase.ch10.brkparent.DemoB").newInstance();
        System.out.println(demoB.getClass().getClassLoader());

    }

}
