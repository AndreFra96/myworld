package myworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Mondo {
    private List<Persona> persone;
    private Evento[] eventiPossibili;

    public Mondo(List<Persona> persone, Evento[] eventiPossibili) {
        this.persone = Objects.requireNonNull(persone);
        this.eventiPossibili = Objects.requireNonNull(eventiPossibili);
    }

    public List<Evento> vita(int years) {
        List<Evento> eventiVissuti = new ArrayList<>();
        for (int i = 0; i < years; i++) {
            Random r = new Random();
            for (Persona actual : persone) {
                actual.invecchia(1);
                for (int j = 0; j < r.nextInt(5); j++) {
                    Evento eventoRandom = eventiPossibili[r.nextInt(eventiPossibili.length)];
                    eventiVissuti.add(eventoRandom);
                    actual.viviEvento(eventoRandom);
                }
            }
        }
        return eventiVissuti;
    }

    @Override
    public String toString() {
        String returnString = "Persone: \n";
        for (Persona actual : persone) {
            returnString += actual + "\n";
        }
        return returnString;
    }
}
