package eu.vytenis.skaitvardziai.skaiciai;

public interface SkaitineReiksme {
	<T> T accept(SkaitineReiksmeVisitor<T> visitor);
}
