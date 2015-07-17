
import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;

public class DFSHelloWorld {

  public static final String theFilename = "hello.txt";
  public static final String message = "Hello, world!\n";

  public static void main (String [] args) throws IOException {

    Configuration conf = new Configuration();
    FileSystem fs = FileSystem.get(conf);

    Path filenamePath = new Path(theFilename);

    try {
      if (fs.exists(filenamePath)) {
        // must remove the file first
        fs.delete(filenamePath, true);
      }

      FSDataOutputStream out = fs.create(filenamePath);
      out.writeBytes(message);
      out.close();

      FSDataInputStream in = fs.open(filenamePath);
      byte [] buf = new byte[256];
      in.read(buf);
      String messageOut = new String(buf);
      System.out.print(messageOut);
      in.close();
    } catch (IOException ioe) {
      System.err.println("IOException during operation: " + ioe.toString());
      System.exit(1);
    }
  }
}

