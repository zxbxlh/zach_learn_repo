package FiveTest;

import Artist.Artist;

import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRef {
    private void test(){
        Artist artist = new Artist.Builder()
                .setName("zachzeng")
                .build();

        testF(artist,artist1 -> {
            return artist.getName();
        });

        /**
         * 使用方法引用：Artist:getName
         */
        testF(artist,Artist::getName);

        /**
         * 使用方法引用：Artist::new
         * 创建对象
         */
        Supplier<Artist> artistSupplier = Artist::new;
        Artist artist2 = artistSupplier.get();
    }

    /**
     * @param f 函数接口
     */
    private void testF(Artist artist, Function<Artist,String> f){
        //调用函数
        String res = f.apply(artist);
        System.out.println("testF res: "+res);
    }
}
