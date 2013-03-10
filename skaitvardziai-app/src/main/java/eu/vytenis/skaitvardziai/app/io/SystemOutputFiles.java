package eu.vytenis.skaitvardziai.app.io;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemOutputFiles {		
	private DecoratingStream out;
	private DecoratingStream err;

	public SystemOutputFiles(DecoratingStream out, DecoratingStream err) {
		this.out = out;
		this.err = err;			
	}
	
	public String getOutText(Charset charset) {
		try {
			return new String(getOutEncoded(), charset.name());
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
	
	public byte[] getOutEncoded() {
		return out.getCollectedBytes();
	}
	
	public String getErrText(Charset charset) {
		try {
			return new String(getErrEncoded(), charset.name());
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
	
	public byte[] getErrEncoded() {
		return err.getCollectedBytes();
	}
}