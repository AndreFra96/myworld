package myworld;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

/**
 * OVERVIEW: Un istanza di questa classe rappresenta una persona, ogni persona è
 * diversa. Una persona è mutabile.
 * 
 * <p>
 * {@code Funzione di astrazione:} AF(sex,age,abilities) rappresenta una persona
 * di sesso {@code sex} di anni {@code age} con {@code abilities} abilità
 * 
 * <p>
 * {@code Invariante di rappresentazione:} una Persona deve avere age >= 0, una
 * lista di abilita (possibilmente vuota) diversa da null
 */
public class Persona {
    private char sex;
    private int age;
    private Map<String, Ability> abilities;

    /**
     * Post-condizioni: inizializza una nuova Persona con i valori in input. solleva
     * IllegalArgumentException se age è minore di 0 solleva NullPointerException se
     * abilities è null
     */
    public Persona(char sex, int age, Map<String, Ability> abilities) {
        if (age < 0)
            throw new IllegalArgumentException("L'età deve essere maggiore o uguale a 0");
        this.abilities = Objects.requireNonNull(abilities);
        this.sex = sex;
        this.age = age;
    }

    /**
     * Post-condizioni: inizializza una nuova Persona senza alcuna abilità. Solleva
     * IllegalArgumentException se age è minore di 0
     */
    public Persona(char sex, int age) {
        if (age < 0)
            throw new IllegalArgumentException("L'età deve essere maggiore o uguale a 0");
        this.sex = sex;
        this.age = age;
        this.abilities = new HashMap<>();
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * <p>
     * Post-condizioni: incrementa l'abilità di this in {@code ability}, se this non
     * possiede questa abilità viene aggiunta
     */
    public void increaseAbility(String ability, int value) {
        if (abilities.containsKey(ability)) {
            abilities.get(ability).increase(value);
        } else {
            abilities.put(ability, new Ability(ability, value));
        }
    }

    /**
    * Effetti-collaterali: potrebbe modificare this
    * <p>
    * Post-condizioni: incrementa l'abilità di this in {@code ability}, se this non
    * possiede questa abilità viene aggiunta
    */
    public void increaseAbility(Ability ability, int value){
        if (abilities.containsKey(ability.getName())) {
            abilities.get(ability.getName()).increase(value);
        } else {
            abilities.put(ability.getName(), ability);
        }
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * <p>
     * Post-condizioni: decrementa l'abilità di this in {@code ability}
     */
    public void decreaseAbility(String ability, int value) {
        if (abilities.containsKey(ability)) {
            if(abilities.get(ability).getValue() - value < 1){
                abilities.remove(ability);
            }else{
                abilities.get(ability).decrease(value);
            }
        }
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * <p>
     * Post-condizioni: decrementa l'abilità di this in {@code ability}
     */
    public void decreaseAbility(Ability ability, int value) {
        if (abilities.containsKey(ability.getName())) {
            if(abilities.get(ability.getName()).getValue() - value < 1){
                abilities.remove(ability.getName());
            }else{
                abilities.get(ability.getName()).decrease(value);
            }
        }
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: rimuove ability dalle abilità della persona
     */
    public void removeAbility(Ability ability){
        if(abilities.containsKey(ability.getName())){
            abilities.remove(ability.getName());
        }
    }

    /**
     * Effetti-collaterali: potrebbe modificare this
     * Post-condizioni: rimuove ability dalle abilità della persona
     */
    public void removeAbility(String ability){
        if(abilities.containsKey(ability)){
            abilities.remove(ability);
        }
    }

    public void viviEvento(Evento e){
        for(Ability actual : e.getBenefits()){
            increaseAbility(actual.getName(), actual.getValue());
        }
        for(Ability actual : e.getDamages()){
            decreaseAbility(actual.getName(), actual.getValue());
        }
    }

    public void invecchia(int time){
        this.age += time;
    }

    public int life(){
        Iterator<Entry<String, Ability>> iterator = this.abilities.entrySet().iterator();
        int life = 0;
        while(iterator.hasNext()){
            Entry<String, Ability> actual = iterator.next();
            life += actual.getValue().getValue();
        }
        return life;
    }

    public char getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        String returnString =  "Persona "+hashCode() +
        ", sex: "+getSex() +
        ", age: "+getAge()+
        "\nlife: "+life()+
        "\nAbilities:\n";
        Iterator<Entry<String, Ability>> iterator = this.abilities.entrySet().iterator();

        while(iterator.hasNext()){
            Entry<String, Ability> actual = iterator.next();
            returnString += actual.getValue()+"\n";
        }

        return returnString;

    }

}