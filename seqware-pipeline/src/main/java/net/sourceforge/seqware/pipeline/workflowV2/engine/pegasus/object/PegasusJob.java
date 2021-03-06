package net.sourceforge.seqware.pipeline.workflowV2.engine.pegasus.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jdom.Element;
import net.sourceforge.seqware.pipeline.workflowV2.model.AbstractJob;
import net.sourceforge.seqware.pipeline.workflowV2.model.BashJob;
import net.sourceforge.seqware.pipeline.workflowV2.model.Command;
import net.sourceforge.seqware.pipeline.workflowV2.model.JavaJob;
import net.sourceforge.seqware.pipeline.workflowV2.model.JavaSeqwareModuleJob;
import net.sourceforge.seqware.pipeline.workflowV2.model.PerlJob;
import net.sourceforge.seqware.pipeline.workflowV2.model.Requirement;

/**
 * a pegasusjob object and its subclasses match to the <job> element in a dax
 * 
 * @author yliang
 * 
 */
public class PegasusJob {
  protected AbstractJob jobObj;
  private int id;
  protected String basedir;
  private List<PegasusJob> parents;
  private List<PegasusJob> children;
  protected static String NS = "seqware";
  private boolean metadataWriteback;
  protected List<String> parentAccessions;
  protected String wfrAccession;
  protected boolean wfrAncesstor;
  protected List<String> parentAccessionFiles;
  private String sqw_version;

  public PegasusJob(AbstractJob job, String basedir, String sqwVersion) {
    this.jobObj = job;
    this.basedir = basedir;
    this.sqw_version = sqwVersion;
    this.parents = new ArrayList<PegasusJob>();
    this.children = new ArrayList<PegasusJob>();
    this.parentAccessionFiles = new ArrayList<String>();
    this.parentAccessions = new ArrayList<String>();
  }

  public Element serializeXML() {
    String name = jobObj.isLocal() ? "java_local" : "java";
    // FIXME should not hardcode here
    String version = "1.6.0";

    Element element = new Element("job", Adag.NAMESPACE);
    element.setAttribute("id", this.jobObj.getAlgo() + "_" + this.id);
    element.setAttribute("name", name);
    element.setAttribute("namespace", NS);
    element.setAttribute("version", version);

    element.addContent(this.getArgumentElement());
    for (Requirement r : this.jobObj.getRequirements()) {
      Element profileE = new Element("profile", Adag.NAMESPACE);
      profileE.setAttribute("namespace", r.getNamespace());
      profileE.setAttribute("key", r.getType().toString().toLowerCase());
      profileE.setText(r.getValue());
      element.addContent(profileE);
    }

    if (jobObj.isLocal()){
      Element profileE = new Element("profile", Adag.NAMESPACE);
      profileE.setAttribute("namespace", "hints");
      profileE.setAttribute("key", "executionPool");
      profileE.setText("local");
      element.addContent(profileE);
    }

    return element;
  }

  /**
   * get the job id
   * 
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * set the job id
   * 
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  private Element getArgumentElement() {
    Element argumentE = new Element("argument", Adag.NAMESPACE);
    // set command argument
    StringBuilder sb = new StringBuilder();
    sb.append(this.buildCommandString());
    sb.append("\n");
    argumentE.setText(sb.toString());
    return argumentE;
  }

  /**
   * for overridden by a specific job type
   * 
   * @return
   */
  protected String buildCommandString() {
    StringBuilder sb = new StringBuilder();
    // add memory, classpath, module for bash

    sb.append("-Xmx").append(this.jobObj.getCommand().getMaxMemory()).append("\n");
    sb.append("-classpath ").append(basedir).append("/lib/").append(this.getPipelinePath()).append("\n");
    sb.append("net.sourceforge.seqware.pipeline.runner.Runner").append("\n");
    sb.append(this.buildMetadataString());
    sb.append("--module net.sourceforge.seqware.pipeline.modules.GenericCommandRunner").append("\n");
    sb.append("--").append("\n");
    sb.append("--gcr-algorithm ").append(this.jobObj.getAlgo()).append("\n");

    Command cmd = this.jobObj.getCommand();
    if (cmd.toString().isEmpty() == false) {
      // append these setting first
      // gcr-output-file
      // gcr-skip-if-output-exists
      // gcr-skip-if-missing
      if (cmd.getGcrOutputFile() != null
          && cmd.getGcrOutputFile().isEmpty() == false) {
        sb.append("--gcr-output-file " + cmd.getGcrOutputFile() + "\n");
      }
      if (cmd.isGcrSkipIfMissing()) {
        sb.append("--gcr-skip-if-missing true");
      }
      if (cmd.isGcrSkipIfOutputExists()) {
        sb.append("--gcr-skip-if-output-exists true");
      }
      sb.append("--gcr-command").append("\n");
      sb.append(this.jobObj.getCommand().toString());
    }
    return sb.toString();
  }

  public AbstractJob getJobObject() {
    return this.jobObj;
  }

  public Collection<PegasusJob> getParents() {
    return this.parents;
  }

  public Collection<PegasusJob> getChildren() {
    return this.children;
  }

  public void addParent(PegasusJob parent) {
    if (!this.parents.contains(parent))
      this.parents.add(parent);
    if (!parent.getChildren().contains(this))
      parent.getChildren().add(this);
  }

  public Element getDependentElement(PegasusJob parent) {
    Element element = new Element("child", Adag.NAMESPACE);
    element.setAttribute("ref", this.jobObj.getAlgo() + "_" + this.getId());
    Element parentE = new Element("parent", Adag.NAMESPACE);
    parentE.setAttribute("ref",
                         parent.getJobObject().getAlgo() + "_" + parent.getId());
    element.addContent(parentE);
    return element;
  }

  public boolean hasMetadataWriteback() {
    return metadataWriteback;
  }

  public void setMetadataWriteback(boolean metadataWriteback) {
    this.metadataWriteback = metadataWriteback;
  }

  public void setParentAccessions(Collection<String> parentAccessions) {
    this.parentAccessions.addAll(parentAccessions);
  }

  public String getWorkflowRunAccession() {
    return wfrAccession;
  }

  public void setWorkflowRunAccession(String wfrAccession) {
    this.wfrAccession = wfrAccession;
  }

  public boolean isWorkflowRunAncesstor() {
    return wfrAncesstor;
  }

  public void setWorkflowRunAncesstor(boolean wfrAncesstor) {
    this.wfrAncesstor = wfrAncesstor;
  }

  public String getAccessionFile() {
    return this.jobObj.getAlgo() + "_" + this.getId() + "_accession";
  }

  public void addParentAccessionFile(String paf) {
    if (!this.parentAccessionFiles.contains(paf))
      this.parentAccessionFiles.add(paf);
  }

  protected String buildMetadataString() {
    StringBuilder sb = new StringBuilder();
    if (this.hasMetadataWriteback()) {
      sb.append("--metadata").append("\n");
    } else {
      sb.append("--no-metadata").append("\n");
    }

    if (!this.parentAccessions.isEmpty()) {
      for (String pa : this.parentAccessions) {
        sb.append("--metadata-parent-accession " + pa).append("\n");
      }
    }
    if (this.wfrAccession != null) {
      if (!this.wfrAncesstor) {
        sb.append("--metadata-workflow-run-ancestor-accession "
                      + this.wfrAccession).append("\n");
      } else {
        sb.append("--metadata-workflow-run-accession " + this.wfrAccession).append("\n");
      }
    }

    if (!this.parentAccessionFiles.isEmpty()) {
      for (String paf : this.parentAccessionFiles) {
        sb.append("--metadata-parent-accession-file " + paf).append("\n");
      }
    }
    sb.append("--metadata-processing-accession-file " + this.getAccessionFile()).append("\n");
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj instanceof PegasusJob == false)
      return false;
    if (obj == this)
      return true;
    PegasusJob rhs = (PegasusJob) obj;
    return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.getAccessionFile(),
                                                                     rhs.getAccessionFile()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.getAccessionFile()).toHashCode();
  }

  String getPipelinePath() {
    return "seqware-distribution-" + this.sqw_version + "-full.jar";
  }
}
