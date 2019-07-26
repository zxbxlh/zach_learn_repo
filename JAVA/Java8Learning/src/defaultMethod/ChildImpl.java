package defaultMethod;

public class ChildImpl implements Child{
    @Override
    public void message(String message) {
        System.out.println(message);
    }
}
