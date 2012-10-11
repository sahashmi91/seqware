package net.sourceforge.seqware.common.metadata;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import net.sourceforge.seqware.common.model.Study;
import net.sourceforge.seqware.common.util.Log;

import net.sourceforge.seqware.common.util.MD5Generator;
import net.sourceforge.seqware.common.util.configtools.ConfigTools;

public abstract class Metadata implements MetadataInterface {
  public static final String SUCCESS = "success";
  public static final String FAILED = "failed";
  public static final String PENDING = "pending";
  public static final String RUNNING = "running";
  public static final String SUBMITTED = "submitted";
  public static final String RESUBMITTED = "resubmitted";
  public static final String COMPLETED = "completed";
  public static final String UNKNOWN = "unknown";

  protected String getMD5Hash(String filename) {
    String hash = null;
    try {
      if ("true".equalsIgnoreCase(ConfigTools.getSettings().get("CALC_MD5_FOR_ALL_FILES"))) {
        filename = locateFile(filename);
        if (filename != null) {
          hash = new MD5Generator().md5sum(filename);
        } else {
          Log.info("Unable to locate file.  Skipping MD5 calculation");
        }
      }
    } catch (Exception e) {
      // Log error and return null. No need to abort processing over MD5 hash
      // error.
      Log.stderr("Error generating md5sum for [" + filename + "]");
      e.printStackTrace();
    }
    return hash;
  }

  protected String locateFile(String filename) {
    if (filename != null) {
      File file = new File(filename);
      if (!file.exists()) {
        Log.info("Cannot find [" + filename + "].  Will attempt to check local directory.");
        int rootDirIdx = filename.indexOf('/');
        if ((rootDirIdx > -1) && (filename.length() > rootDirIdx + 1)) {
          filename = filename.substring(rootDirIdx + 1);
          file = new File(filename);
          if (!file.exists()) {
            Log.info("Cannot find [" + filename + "]");
            filename = null;
          }
        } else {
          filename = null;
        }
      }
    }
    return filename;
  }
}
