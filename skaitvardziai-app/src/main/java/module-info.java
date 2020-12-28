module eu.vytenis.skaitvardziai.app {
	exports eu.vytenis.skaitvardziai.app.template;
	exports eu.vytenis.skaitvardziai.app.echo;
	exports eu.vytenis.skaitvardziai.app.io;
	exports eu.vytenis.skaitvardziai.app.processors;
	exports eu.vytenis.skaitvardziai.app.cli;
	exports eu.vytenis.skaitvardziai.app.exc;
	exports eu.vytenis.skaitvardziai.app.main;

	requires commons.cli;
	requires eu.vytenis.skaitvardziai.core;
	requires eu.vytenis.skaitvardziai.parser;

	requires junit;
}