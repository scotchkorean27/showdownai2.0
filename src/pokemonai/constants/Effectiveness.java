package pokemonai.constants;

public enum Effectiveness {
	SUPER, RESIST, IMMUNE, NORMAL;

	public static Effectiveness getEffectiveness(double typemult) {
		if (typemult == 1) {
			return Effectiveness.NORMAL;
		} else if (typemult > 1) {
			return Effectiveness.SUPER;
		} else if (typemult == 0) {
			return Effectiveness.IMMUNE;
		} else {
			return Effectiveness.RESIST;
		}
	}
}
