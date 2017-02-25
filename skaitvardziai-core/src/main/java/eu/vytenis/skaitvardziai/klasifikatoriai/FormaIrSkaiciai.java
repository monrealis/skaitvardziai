package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.math.BigInteger;

import eu.vytenis.skaitvardziai.checks.Checks;

public class FormaIrSkaiciai implements Cloneable {
	private Forma forma = new Forma();
	private BigInteger pradinisSveikasisSkaicius;
	private BigInteger sveikasisSkaicius;

	public FormaIrSkaiciai() {
	}

	public FormaIrSkaiciai(Forma forma) {
		Checks.checkNotNull("forma", forma);
		this.forma = forma;
	}

	public FormaIrSkaiciai(Forma forma, BigInteger sveikasisSkaicius, BigInteger pradinisSveikasisSkaicius) {
		Checks.checkNotNull("forma", forma);
		this.forma = forma;
		this.sveikasisSkaicius = sveikasisSkaicius;
		this.pradinisSveikasisSkaicius = pradinisSveikasisSkaicius;
	}

	public Forma getForma() {
		return forma;
	}

	public BigInteger getPradinisSveikasisSkaicius() {
		return pradinisSveikasisSkaicius;
	}

	public void setPradinisSveikasisSkaicius(BigInteger pradinisSkaicius) {
		this.pradinisSveikasisSkaicius = pradinisSkaicius;
	}

	public BigInteger getSveikasisSkaicius() {
		return sveikasisSkaicius;
	}

	public void setSveikasisSkaicius(BigInteger skaicius) {
		this.sveikasisSkaicius = skaicius;
	}

	public FormaIrSkaiciai poskyris(Poskyris poskyris) {
		forma = forma.poskyris(poskyris);
		return this;
	}

	public FormaIrSkaiciai gimine(Gimine gimine) {
		forma = forma.gimine(gimine);
		return this;
	}

	public FormaIrSkaiciai sveikasSkaicius(BigInteger naujasSkaicius) {
		setSveikasisSkaicius(naujasSkaicius);
		return this;
	}

	@Override
	public FormaIrSkaiciai clone() {
		try {
			FormaIrSkaiciai fs = (FormaIrSkaiciai) super.clone();
			return fs;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
