import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class PairRDDTest implements Serializable {

    transient SparkConf sparkConf = new SparkConf()
            .setMaster("local")
            .setAppName("pair-rdd-test");

    transient JavaSparkContext sparkContext  = new JavaSparkContext(sparkConf);


    public void test(){
        //createPairRDD();
        reduceByKey();
    }


    private void reduceByKey(){
        JavaRDD<String> data = sparkContext.parallelize(Arrays.asList("this is zach test","who ary you","this is zach test","ha ha ha"));
        JavaRDD<String> words = data.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });

        //System.out.println(StringUtils.join(words.collect(),","));
        JavaPairRDD<String,Integer> res = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        System.out.println(StringUtils.join(res.collect(),","));
    }

    private JavaPairRDD<String,String> createPairRDD(){
        JavaRDD<String> rdd = sparkContext.parallelize(Arrays.asList("hello world","zach test"));

        PairFunction<String,String,String> keyData = new PairFunction<String, String, String>() {
            @Override
            public Tuple2<String, String> call(String s) throws Exception {
                return new Tuple2<String, String>(s.split(" ")[0],s);
            }
        };

        JavaPairRDD<String,String> pairs = rdd.mapToPair(keyData);

        System.out.println(StringUtils.join(pairs.collect(),","));
        //print
        //printPairs(pairs);
        return pairs;
    }


    private void printPairs(JavaPairRDD<String,String> pairs){
        List<Tuple2<String,String>> data = pairs.collect();
        for(Tuple2<String,String> item : data){
            System.out.println(item._1+","+item._2);
        }
    }
}
