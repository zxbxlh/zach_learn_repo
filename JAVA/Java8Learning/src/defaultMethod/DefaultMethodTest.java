package defaultMethod;

import Main.ITest;

public class DefaultMethodTest implements ITest {

    @Override
    public void test() {
        Parent p = new ParentImpl();
        p.welcome();

        Parent c = new ChildImpl();
        c.welcome();

        //类的重写高于接口重写，调用OverridingParent方法
        Parent oc = new OverridingChild();
        oc.welcome();
    }
}
