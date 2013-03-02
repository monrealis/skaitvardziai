package eu.vytenis.skaitvardziai.app;

public class SystemOutputFiles {		
	private String out;
	private String err;

	public SystemOutputFiles(String out, String err) {
		this.out = out;
		this.err = err;			
	}
	
	public String getOut() {
		return out;
	}
	
	public String getErr() {
		return err;
	}
}