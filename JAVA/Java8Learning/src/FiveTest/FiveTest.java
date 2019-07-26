package FiveTest;
import Main.ITest;

public class FiveTest implements ITest {


    @Override
    public void test() {

        CollectTest ct = new CollectTest();
        //ct.findMax();
        //ct.part();
        ct.serialName2();
    }
}



