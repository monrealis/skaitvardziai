package eu.vytenis.skaitvardziai.parser.nodes;

import eu.vytenis.skaitvardziai.parser.tree.TreeParserTreeConstants;

public class TreeConstants {

	public static String getParameters() {
		return getName(TreeParserTreeConstants.JJTPARAMETERS);
	}

	public static String getParameter() {
		return getName(TreeParserTreeConstants.JJTPARAMETER);
	}

	public static String getMinus() {
		return getName(TreeParserTreeConstants.JJTMINUS);
	}

	public static String getUnsignedInteger() {
		return getName(TreeParserTreeConstants.JJTUNSIGNEDINTEGER);
	}

	public static String getInteger() {
		return getName(TreeParserTreeConstants.JJTINTEGER);
	}

	public static String getFraction() {
		return getName(TreeParserTreeConstants.JJTFRACTION);
	}

	public static String getString() {
		return getName(TreeParserTreeConstants.JJTSTRING);
	}

	public static String getNull() {
		return getName(TreeParserTreeConstants.JJTNULL);
	}

	public static String getIdentifier() {
		return getName(TreeParserTreeConstants.JJTIDENTIFIER);
	}

	public static String getName(int id) {
		return TreeParserTreeConstants.jjtNodeName[id];
	}

}
