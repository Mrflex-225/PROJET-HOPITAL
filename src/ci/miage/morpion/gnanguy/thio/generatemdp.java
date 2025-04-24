package ci.miage.morpion.gnanguy.thio;
import java.util.Random;
import java.util.random.*;
public class generatemdp {
    public static String generationmdpadmin(String nom){
        Random numberRandom = new Random();
        char c = (char)(numberRandom.nextInt(26)+97);
        int nombre = numberRandom.nextInt(999999999)+1;
        int nombre2 = numberRandom.nextInt(999999999)+1;
        return nom+"/"+String.valueOf(nombre)+"_"+String.valueOf(nombre2)+"-"+c;
    }

    public static String generationmdpdocteur(String nom){
        Random numberRandom = new Random();
        int nombre = numberRandom.nextInt(999999999)+1;
        int nombre2 = numberRandom.nextInt(999999999)+1;
        return nom+"/"+String.valueOf(nombre)+"_"+String.valueOf(nombre2);
    }

}
