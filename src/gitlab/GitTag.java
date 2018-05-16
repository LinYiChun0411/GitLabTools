package gitlab;

public class GitTag {
	private String tagName;
	private String repository;
	private String project;
	private String branch;
	
	
	public GitTag(String tagName, String repository, String project, String branch) {
		super();
		this.tagName = tagName;
		this.repository = repository;
		this.project = project;
		this.branch = branch;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	

}
