package defaultMethod;

public class ParentImpl implements Parent{
    @Override
    public void message(String message) {
        System.out.println(message);
    }
}
