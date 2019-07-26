package FiveTest;

public class StringCombiner {

    private String prefix;
    private String delim;
    private String suffix;

    private StringBuilder sb = new StringBuilder();

    public StringCombiner(String prefix,String delim,String suffix){
        this.prefix = prefix;
        this.delim = delim;
        this.suffix = suffix;
    }

    public StringCombiner add(String element){
        if(isStart()){
            sb.append(prefix);
        }else {
            sb.append(delim);
        }

        sb.append(element);
        return this;
    }

    public StringCombiner merge(StringCombiner other){
        sb.append(other.sb);
        return this;
    }

    private boolean isStart(){
        return sb.length() == 0;
    }

    @Override
    public String toString() {
        sb.append(suffix);
        return sb.toString();
    }
}
