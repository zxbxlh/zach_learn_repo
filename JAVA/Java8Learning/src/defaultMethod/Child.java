package defaultMethod;

public interface Child extends Parent{
    /**
     * 重写默认方法
     */
    @Override
    default void welcome() {
        System.out.println("Child");
        message("Child-implement");
    }
}
