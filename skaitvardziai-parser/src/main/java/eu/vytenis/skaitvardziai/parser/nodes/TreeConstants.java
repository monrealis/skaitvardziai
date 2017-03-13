package eu.vytenis.skaitvardziai.parser.nodes;

import eu.vytenis.skaitvardziai.parser.tree.TreeParserTreeConstants;

public class TreeConstants {
	public static String parameters() {
		return getName(TreeParserTreeConstants.JJTPARAMETERS);
	}

	public static String parameter() {
		return getName(TreeParserTreeConstants.JJTPARAMETER);
	}

	public static String minus() {
		return getName(TreeParserTreeConstants.JJTMINUS);
	}

	public static String unsignedInteger() {
		return getName(TreeParserTreeConstants.JJTUNSIGNEDINTEGER);
	}

	public static String integer() {
		return getName(TreeParserTreeConstants.JJTINTEGER);
	}

	public static String fraction() {
		return getName(TreeParserTreeConstants.JJTFRACTION);
	}

	public static String string() {
		return getName(TreeParserTreeConstants.JJTSTRING);
	}

	public static String Null() {
		return getName(TreeParserTreeConstants.JJTNULL);
	}

	public static String identifier() {
		return getName(TreeParserTreeConstants.JJTIDENTIFIER);
	}

	private static String getName(int id) {
		return TreeParserTreeConstants.jjtNodeName[id];
	}
}
