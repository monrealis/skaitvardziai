package eu.vytenis.skaitvardziai.parser.methods.jjt;

import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;

interface ParameterHandler {
	Object getValue(SimpleNode node);
}