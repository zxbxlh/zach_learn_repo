import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

public class Main {

    public static void wordCount(){
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("wordCount");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        //read input
        JavaRDD<String> input = sc.textFile("wordCount.txt");
        //split word
        JavaRDD<String> words = input.flatMap(
                new FlatMapFunction<String, String>() {
                    public Iterable<String> call(String s) throws Exception {
                        return Arrays.asList(s.split(" "));
                    }
                }
        );

        //switch to kv and count
        JavaPairRDD<String,Integer> counts = words.mapToPair(
                new PairFunction<String, String, Integer>() {
                    public Tuple2<String, Integer> call(String s) throws Exception {
                        return new Tuple2(s,1);
                    }
                }
        ).reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        //save result
        counts.saveAsTextFile("resultFile.txt");
    }


    public static void main(String[] args){
        //SparkTest sparkTest = new SparkTest();
        //sparkTest.test();

        PairRDDTest pairRDDTest = new PairRDDTest();
        pairRDDTest.test();
    }
}
