package eu.vytenis.skaitvardziai.app.io;

public class SystemOutputFiles {		
	private DecoratingStream out;
	private DecoratingStream err;

	public SystemOutputFiles(DecoratingStream out, DecoratingStream err) {
		this.out = out;
		this.err = err;			
	}
	
	public String getOutText() {
		return out.getText();
	}
	
	public byte[] getOutEncoded() {
		return out.getBytes();
	}
	
	public String getErrText() {
		return err.getText();
	}
	
	public byte[] getErrEncoded() {
		return err.getBytes();
	}
}