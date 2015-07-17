package module5;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.IdentityMapper;
import org.apache.hadoop.mapred.lib.IdentityReducer;

public class YahooDriver {

  private static String INPUT_PATH = "testdata";
  private static String OUTPUT_PATH = "outputdata";

  public static void main(String [] args) {
    JobConf conf = new JobConf();

    conf.setInputPath(new Path(INPUT_PATH));
    conf.setOutputPath(new Path(OUTPUT_PATH));
    conf.setOutputValueClass(Point3D.class);
    conf.setOutputKeyClass(Text.class);
    conf.setInputFormat(ObjectPositionInputFormat.class);

    conf.setMapperClass(IdentityMapper.class);
    conf.setReducerClass(IdentityReducer.class);

    try {
      JobClient.runJob(conf);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
