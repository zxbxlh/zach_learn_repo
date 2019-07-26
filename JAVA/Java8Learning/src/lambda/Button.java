package lambda;

public class Button {

    private ActionListener mActionListener = null;

    public void click(String actionText){
        if(mActionListener != null){
            mActionListener.onClick(actionText);
        }
    }

    public void setActionListener(ActionListener listener){
        this.mActionListener = listener;
    }
}
