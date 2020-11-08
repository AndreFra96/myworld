package myworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

public class TestRunner {
    public static void main(String[] args) {
        HashMap<String,Ability> a = new HashMap<>();
        Random random = new Random();
        List<Persona> persone = new ArrayList<>();

        try (Scanner s = new Scanner(new FileInputStream(new File("abilities.txt")))) {
            while (s.hasNextLine()) {
                String nextLine = s.nextLine();
                a.put(nextLine, new Ability(nextLine, 1));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        List<Evento> eventi = new ArrayList<Evento>();
        try (Scanner s = new Scanner(new FileInputStream(new File("events.txt")))) {
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split("@");

                String[] abilities = line[1].split(",");
                List<Ability> benefits = new ArrayList<>();
                List<Ability> damages = new ArrayList<>();
                for(String actual: abilities){
                    String[] ability = actual.split(" ");
                    int value = Integer.parseInt(ability[1]);
                    if(value > 0){
                        benefits.add(new Ability(ability[0],value));
                    }else{
                        damages.add(new Ability(ability[0],value*-1));
                    }
                }
                eventi.add(new Evento(line[0],benefits,damages));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        Evento[] ev = new Evento[eventi.size()];
        for(int i = 0; i < eventi.size(); i++){
           ev[i] = eventi.get(i);
        }
       

        for(int i = 1; i <= Integer.parseInt(args[0]); i++){
            HashMap<String,Ability> newab = new HashMap<>();
            Iterator<Entry<String,Ability>> iterator = a.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,Ability> next = iterator.next();
                Ability ab = a.get(next.getKey());
                ab = new Ability(ab.getName(),random.nextInt(100) + 1);
                newab.put(ab.getName(), ab);
            }
            persone.add(new Persona('m',random.nextInt(100) + 1,newab));
        }

        
        Mondo world = new Mondo(persone,ev);
        System.out.println(world);
        List<Evento> eventiVissuti = world.vita(Integer.parseInt(args[1]));
        String events = "Eventi vissuti: \n";
        for(Evento actual : eventiVissuti){
            events += actual.getName()+"\n";
        }
        System.out.println(events);
        System.out.println(world);
    }
}
