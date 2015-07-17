package module5;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class ObjectPositionInputFormat extends FileInputFormat<Text, Point3D> {

  public RecordReader<Text, Point3D> getRecordReader(InputSplit input,
      JobConf job, Reporter reporter) throws IOException {

    reporter.setStatus(input.toString());
    return new ObjPosRecordReader(job, (FileSplit) input);
  }
}
