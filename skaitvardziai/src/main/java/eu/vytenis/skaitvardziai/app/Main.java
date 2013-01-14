package eu.vytenis.skaitvardziai.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctions;

public class Main {
	
	private static ThreadLocal<Writer> systemOut = new ThreadLocal<Writer>();
	private static ThreadLocal<Writer> systemErr = new ThreadLocal<Writer>();
	
	public static void setOut(Writer writer) {
		systemOut.set(writer);
	}

	public static void setErr(Writer writer) {
		systemErr.set(writer);
	}
	
	private static void errPrintln(String text) throws IOException {
		Writer w = systemErr.get();
		if (w != null) {
			w.write(text + "\n");
		} else {
			System.err.println(text);
		}
	}
	
	private static void outPrintln(String text) throws IOException {
		Writer w = systemOut.get();
		if (w != null) {
			w.write(text + "\n");
		} else {
			System.out.println(text);
		}
	}
	
	private static void help() throws IOException {
		outPrintln("Pagalba: ltsk --help");
	}
	
	private static void usage() throws IOException {
		errPrintln("Neteisingai nurodyti parametrai. Naudojimas: 'ltsk [PARAMETRAI] [BYLA]'");
	}
	public static void main(String[] args) throws IOException {
		String parametrai = "";
		Reader r = null;
		if (args.length == 1) {
			if ("--help".equals(args[0])) {
				help();
				return;
			}
			if (args[0].startsWith("-")) {
				parametrai = args[0].substring(1);
				r = new InputStreamReader(System.in);
			} else {
				r = new StringReader(args[0]);
			}
		} else if (args.length == 2) {
			if (args[0].startsWith("-")) {
				parametrai = args[0].substring(1);
				r = new StringReader(args[1]);
			} else {
				parametrai = args[1].substring(1);
				r = new StringReader(args[0]);
			}			
		} else {
			usage();
			return;
		}
		Forma f = SkaiciusXPathFunctions.Utils.parse(parametrai, null);
		BufferedReader br = new BufferedReader(r);
		String line;
		while ((line = br.readLine()) != null) {
			SkaitineReiksme sr = SkaiciusXPathFunctions.Utils.parse(line);
			if (sr instanceof SveikasisSkaicius) {
				SveikasisSkaicius ss = (SveikasisSkaicius) sr;
				outPrintln(ss.toString(f));
			} else if (sr instanceof Trupmena) {
				outPrintln(((Trupmena) sr).toString(f.getLinksnis()));
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

}
