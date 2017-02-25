package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.math.BigInteger;

import eu.vytenis.skaitvardziai.checks.Checks;

public class FormaIrSkaiciai implements Cloneable {
	private final Forma forma;
	private final BigInteger pradinisSveikasisSkaicius;
	private final BigInteger sveikasisSkaicius;

	public FormaIrSkaiciai(Forma forma, BigInteger sveikasisSkaicius, BigInteger pradinisSveikasisSkaicius) {
		Checks.checkNotNull("forma", forma);
		this.forma = forma;
		this.sveikasisSkaicius = sveikasisSkaicius;
		this.pradinisSveikasisSkaicius = pradinisSveikasisSkaicius;
	}

	private FormaIrSkaiciai(FormaIrSkaiciai formaIrSkaiciai, Forma forma) {
		this.forma = forma;
		this.pradinisSveikasisSkaicius = formaIrSkaiciai.pradinisSveikasisSkaicius;
		this.sveikasisSkaicius = formaIrSkaiciai.sveikasisSkaicius;
	}

	private FormaIrSkaiciai(FormaIrSkaiciai formaIrSkaiciai, BigInteger sveikasisSkaicius) {
		this.forma = formaIrSkaiciai.forma;
		this.pradinisSveikasisSkaicius = formaIrSkaiciai.pradinisSveikasisSkaicius;
		this.sveikasisSkaicius = sveikasisSkaicius;
	}

	public Forma getForma() {
		return forma;
	}

	public BigInteger getPradinisSveikasisSkaicius() {
		return pradinisSveikasisSkaicius;
	}

	public BigInteger getSveikasisSkaicius() {
		return sveikasisSkaicius;
	}

	public FormaIrSkaiciai poskyris(Poskyris poskyris) {
		return new FormaIrSkaiciai(this, forma.poskyris(poskyris));
	}

	public FormaIrSkaiciai gimine(Gimine gimine) {
		return new FormaIrSkaiciai(this, forma.gimine(gimine));
	}

	public FormaIrSkaiciai sveikasSkaicius(BigInteger naujasSkaicius) {
		return new FormaIrSkaiciai(this, naujasSkaicius);
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
