package defaultMethod;

public class OverridingParent extends ParentImpl{
    /**
     * 重写接口默认方法
     * 优先级高于接口默认实现
     */
    @Override
    public void welcome() {
        System.out.println("OverridingParent");
        message("OverridingParent-implement");
    }
}
