package defaultMethod;

public interface Parent {

    void message(String message);

    /**
     * 默认方法，用于接口后续变动向上兼容
     */
    public default void welcome(){
        message("Parent Hi");
    }
}

