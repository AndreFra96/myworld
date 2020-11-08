package myworld;

import java.util.Objects;

/**
 * OVERVIEW: le istanze di questa classe rappresentano l'abilita' in un certo campo.
 * le istanze di Ability sono mutabili.
 * un tipico Ability è: "forza" : 10
 * 
 * Funzione di astrazione: Ability rappresenta un'abilità attraverso il suo nome ed un valore intero ad esso collegato
 * 
 * Invariante di rappresentazione: il valore di una abilità deve essere sempre maggiore di 0 ed il suo nome diverso da null
 */
public class Ability {
    private final String name;
    private int value;

    /**
     * Post-condizioni: costruisce una nuova instanza di Ability, solleva
     * IllegalArgumentException se il valore in input è minore di 1
     */
    public Ability(String name, int value) {
        if (value < 1)
            throw new IllegalArgumentException("Il valore minimo per un'abilità è 1");
        this.name = Objects.requireNonNull(name);
        this.value = value;
    }

    /**
     * Post-condizioni: restituisce il valore di questa abilità
     */
    public int getValue() {
        return value;
    }

    /**
     * Post-condizioni: restituisce il nome di questa abilità
     */
    public String getName() {
        return this.name;
    }

    /**
     * Effetti-collaterali: potrebbe modificare this Post-condizioni: Aumenta di
     * incr l'abilità
     */
    public void increase(int incr) {
        if (incr < 0)
            throw new IllegalArgumentException("incr deve essere positivo");
        this.value += incr;
    }

    /**
     * Effetti-collaterali: potrebbe modificare this Post-condizioni: Diminiusce di
     * decr l'abilità
     */
    public void decrease(int decr) {
        if (decr < 0)
            throw new IllegalArgumentException("decr deve essere positivo");
        if(this.value - decr < 1) throw new NegativeAbilityException("Il valore minimo per una abilità è 1");
        this.value -= decr;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Ability)) return false;
        Ability temp = (Ability) obj;
        return temp.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName() + " = " + getValue();
    }
}
