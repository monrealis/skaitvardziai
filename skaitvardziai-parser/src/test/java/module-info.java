module eu.vytenis.skaitvardziai.parser {
	exports eu.vytenis.skaitvardziai.parser.methods;
	exports eu.vytenis.skaitvardziai.parser.nodes;
	exports eu.vytenis.skaitvardziai.parser.methods.antrl4;
	exports eu.vytenis.skaitvardziai.parser.methods.jjt;
	exports eu.vytenis.skaitvardziai.parser.antlr4;
	exports eu.vytenis.skaitvardziai.parser.tree;

	requires eu.vytenis.skaitvardziai.core;
	requires org.antlr.antlr4.runtime;

	requires junit;
}