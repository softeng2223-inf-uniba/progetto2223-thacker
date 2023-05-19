package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;
/**
 * Classe del comando /help.
 * Contiene il codice da eseguire con il comando /help.
 */
public class Help implements NoArgs {
    private static final int TEXT_DELAY = 100;
    private static final String TEXT =
            "Benvenuto in BattleShip!\n\n"
                    + "Scopo del gioco: affondare tutte le navi prima di aver terminato il numero di tentativi fallibili.\n"
                    + "Per giocare, utilizzare i seguenti comandi:\n\n"
                    + "/gioca \n"
                    + "/facile \n"
                    + "/medio \n"
                    + "/difficile \n"
                    + "/mostralivello \n"
                    + "/svelagriglia \n"
                    + "/mostranavi \n"
                    + "/esci";

    /**
     * Il metodo esegue il codice del comando /help.
     * Stampa una breve descrizione
     * che contiene i comandi di gioco in stile
     * "macchina da scrivere".
     */
    public void execute(){
        for(int i=0; i<TEXT.length(); i++){
            System.out.print(TEXT.charAt(i));
            try{
                Thread.sleep(TEXT_DELAY);
            }catch (InterruptedException err){
                System.out.println("InterruptedException: " + err);
            }
        }
        System.out.println("\n");
    }
}
