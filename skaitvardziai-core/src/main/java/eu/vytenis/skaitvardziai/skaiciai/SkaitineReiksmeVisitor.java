package eu.vytenis.skaitvardziai.skaiciai;

public interface SkaitineReiksmeVisitor<T> {
	T visitSveikasisSkaicius();
	
	T visitTrupmena();
}