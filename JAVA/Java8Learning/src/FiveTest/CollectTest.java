package FiveTest;

import Artist.Artist;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectTest {

    private void numberTest(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        //收集器生成list
        List<Integer> sameOrder = numbers.stream().collect(Collectors.toList());
    }

    private List<Artist> createArtists(){
        List<Artist> artists = new ArrayList<Artist>();

        for(int i=0;i<20;i++){

            List<Artist> members = new ArrayList<Artist>();
            for(int j = 0;j<i;j++){
                Artist item = new Artist.Builder()
                        .setName("j_"+j)
                        .setOrigin("from_"+j)
                        .build();
                members.add(item);
            }

            Artist item = new Artist.Builder()
                    .setName("i_"+i)
                    .setOrigin("from_"+i)
                    .setMembers(members)
                    .build();
            artists.add(item);
        }

        return artists;
    }


    /**
     * 使用流找出最大members
     */
    public void findMax(){
        List<Artist> artists = createArtists();

        /**
         * 函数接口：筛选条件
         */
        Function<Artist,Integer> findMax = artist -> artist.getMembers().size();

        //
        Optional<Artist> optional = artists.stream()
                .collect(Collectors.maxBy(Comparator.comparing(findMax)));

        System.out.println(optional.get().getName());
    }


    /**
     * 分组
     */
    public void part(){
        List<Artist> artists = createArtists();
        Map<Boolean,List<Artist>> res = artists.stream().collect(
                Collectors.partitioningBy(Artist::isSolo));

        //map流式遍历
        res.forEach((isSolo,artistList)->{
            System.out.println("isSolo: "+isSolo);
            artistList.forEach(artist -> System.out.println(artist.getName()));
        });
    }


    /**
     * 串联名字
     * 老方式
     */
    public void serialName(){
        List<Artist> artists = createArtists();
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        artists.forEach(artist -> sb.append(artist.getName()).append(","));

        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        System.out.println(sb.toString());
    }


    /**
     * 串联名字
     * 流自带收集器实现
     */
    public void serialName1(){
        List<Artist> artists = createArtists();
        String res = artists.stream()
                .map(Artist::getName)
                .collect(Collectors.joining(",","[","]"));

        System.out.println(res);
    }

    /**
     * 串联名字
     * 自实现收集器
     */
    public void serialName2(){
        List<Artist> artists = createArtists();
        String res = "";

        //重构
//        StringBuffer sb = new StringBuffer("[");
//        artists.stream()
//                .map(Artist::getName)   //映射称name
//                .forEach(name -> {
//                    if(sb.length()>1) sb.append(",");
//                    sb.append(name);
//                });
//        sb.append("]");
//        res = sb.toString();


        /************** reduce实现 *****************/

        BiFunction<StringBuilder,String,StringBuilder> add = (builder,name) ->{
            if(builder.length() > 0){
                builder.append(",");
            }

            builder.append(name);
            return builder;
        };

        BinaryOperator<StringBuilder> merge = (left,right) -> left.append(right);

        StringBuilder sb = artists.stream()
                .map(Artist::getName)
                .reduce(new StringBuilder(),//初值
                        add,
                        merge);
        sb.insert(0,"[");
        sb.append("]");

        System.out.println(sb.toString());

        /************** reduce + StringCombiner实现 *****************/
         res = artists.stream()
                .map(Artist::getName)
                .reduce(new StringCombiner("[",",","]"),
                        StringCombiner::add,
                        StringCombiner::merge)
                 .toString();

        /************** collect + 自实现收集器StringCollector实现 *****************/
        res = artists.stream()
                .map(Artist::getName)
                .collect(new StringCollector("[",",","]"));

        System.out.println(res);
    }
}
