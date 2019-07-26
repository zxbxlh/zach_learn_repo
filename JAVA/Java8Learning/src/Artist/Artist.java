package Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * 乐队或歌手
 */
public class Artist {

    private String name;
    private String origin;

    //乐队成员，可能为空
    private List<Artist> members = new ArrayList<Artist>();

    public Artist(){}

    public String getName(){
        return name;
    }

    public List<Artist> getMembers(){
        return members;
    }

    public boolean isSolo(){
        return members.isEmpty();
    }

    public static class Builder{
        Artist artist = new Artist();

        public Builder setName(String name){
            artist.name = name;
            return this;
        }

        public Builder setOrigin(String origin){
            artist.origin = origin;
            return this;
        }

        public Builder setMembers(List<Artist> members){
            if(members != null && !members.isEmpty()){
                artist.members.addAll(members);
            }

            return this;
        }

        public Artist build(){
            return artist;
        }
    }
}
