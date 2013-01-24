package eu.vytenis.skaitvardziai.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiTextParser;

// TODO suformuoti vykdomąjį JAR
// TODO pridėti parametrą analogišką "echo -n"
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
	
	public static void main(String[] args) throws IOException, ParseException {
		Options options = new Options();
		options.addOption("h", "help", false, "show help");
		
		CommandLineParser parser = new PosixParser();
		CommandLine cli = parser.parse(options, args);
		if (cli.hasOption("h")) {
			help();
			return;
		}
		
		Reader r = null;
		if (cli.getArgs().length == 0) {
			r = new InputStreamReader(System.in);
		} else if (cli.getArgs().length == 1) {
			r = new StringReader(cli.getArgs()[0]);
		} else {
			usage();
			return;
		}
		List<String> params = new ArrayList<String>();
		for (Option o : cli.getOptions()) {
			params.add(o.getArgName());			
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(params.toArray(new String[]{}), null);
		BufferedReader br = new BufferedReader(r);
		String line;
		while ((line = br.readLine()) != null) {
			SkaitineReiksme sr = SkaitvardziaiTextParser.get().parseSkaicius(line);
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
