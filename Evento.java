package myworld;

import java.util.List;
import java.util.Objects;

/**
 * OVERVIEW: un instanza di questa classe rappresenta un evento nel mondo
 * attuale e il modo in cui quell'evento può migliorare o peggiorare alcune
 * abilità. un evento non è modificabile.
 * 
 * <p>
 * Funzione di astrazione:
 * <p>
 * Invariante di rappresentazione:
 */
public class Evento {
    private final String name;
    private final List<Ability> benefits;
    private final List<Ability> damages;

    public Evento(String name, List<Ability> benefits, List<Ability> damages) {
        this.name = Objects.requireNonNull(name);
        this.benefits = Objects.requireNonNull(benefits);
        this.damages = Objects.requireNonNull(damages);
    }

    public String getName() {
        return name;
    }

    public List<Ability> getDamages() {
        return damages;
    }

    public List<Ability> getBenefits() {
        return benefits;
    }

    @Override
    public String toString() {
        String returnString = "Evento: " + this.name + "\n\nBenefici:\n";
        for (Ability actual : benefits) {
            returnString += actual + "\n";
        }
        returnString += "\nDanni:\n";
        for (Ability actual : damages) {
            returnString += actual + "\n";
        }
        return returnString;
    }

}