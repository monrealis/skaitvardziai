package eu.vytenis.skaitvardziai.parser.methods;

import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;

interface ParameterHandler {
	Object getValue(SimpleNode node);
}