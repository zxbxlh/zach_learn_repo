import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import scala.Int;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SparkTest implements Serializable {

    //spark config
    transient SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("spark-test");
    transient JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);


    private void map(){
        //List<Integer> data = Arrays.asList(1,2,3,4,6);
        JavaRDD<Integer> rdd = sparkContext.parallelize(Arrays.asList(1,2,3,4,5));

        JavaRDD<Integer> res = rdd.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer*integer;
            }
        });

        System.out.println(StringUtils.join(res.collect(),","));
    }


    private void flatMap(){
        JavaRDD<String> lines = sparkContext.parallelize(Arrays.asList("zach test","hello world","hi"));
        JavaRDD<String> res = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });

        System.out.println(StringUtils.join(res.collect(),","));
    }

    private void reduce(){
        JavaRDD<Integer> rdd = sparkContext.parallelize(Arrays.asList(1,2,3,4));
        Integer sum = rdd.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        System.out.println(sum);
    }

    private void fold(){
        JavaRDD<Integer> rdd = sparkContext.parallelize(Arrays.asList(1,2,3,4,5));
        Integer sum = rdd.fold(1, new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });

        System.out.println(sum);
    }


    private void mapToDouble(){
        JavaRDD<Integer> rdd = sparkContext.parallelize(Arrays.asList(1,2,3));
        JavaDoubleRDD doubleRDD = rdd.mapToDouble(new DoubleFunction<Integer>() {
            @Override
            public double call(Integer integer) throws Exception {
                return (double)integer*integer;
            }
        });

        System.out.println(doubleRDD.mean());
    }

    /****************** test aggregate()，can change return type***********/

    class AvgCount implements Serializable{
        public int total;
        public int num;
        public AvgCount(int total,int num){
            this.total = total;
            this.num = num;
        }

        public double avg(){
            return total/(double)num;
        }
    }

    Function2<AvgCount,Integer,AvgCount> addAndCount = new Function2<AvgCount, Integer, AvgCount>() {
                @Override
                public AvgCount call(AvgCount avgCount, Integer integer) throws Exception {
                    avgCount.total += integer;
                    avgCount.num++;
                    return avgCount;
                }
            };

    Function2<AvgCount,AvgCount,AvgCount> combine = new Function2<AvgCount, AvgCount, AvgCount>() {
        @Override
        public AvgCount call(AvgCount avgCount, AvgCount avgCount2) throws Exception {
            avgCount.total += avgCount2.total;
            avgCount.num += avgCount2.num;
            return avgCount;
        }
    };

    private void aggregate(){
        JavaRDD<Integer> rdd = sparkContext.parallelize(Arrays.asList(1,2,3,4));
        AvgCount initial = new AvgCount(0,0);
        AvgCount res = rdd.aggregate(initial,addAndCount,combine);
        System.out.println(res.avg());
    }


    public void test(){
//        //test map
//        map();
//        //test flatMap
//        flatMap();
//        //test reduce
//        reduce();
//        //test aggregate
//        aggregate();

        //test fold
//        fold();

        //test mapToDouble
        mapToDouble();
    }
}
