package eetac.dsa.patterns.factory;

import java.lang.*;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CommandFactory {

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(CommandFactory.class);

    private static CommandFactory instance; //atributs

    private HashMap<String, Command> cache; // hashmap per guardar a la cache

    private CommandFactory() {
        this.cache=new HashMap<String, Command>();
    }

    public static CommandFactory getInstance(){
        //retorna la instancia de eetac.dsa.patterns.factory.CommandFactory
        if(instance == null)
            instance = new CommandFactory();
        return instance;
    }
/*
    public static eetac.dsa.patterns.factory.Command getCommand(String name){

        if(name.equals("eetac.dsa.patterns.factory.C1"))
            c = new eetac.dsa.patterns.factory.C1();
        else
            c= new eetac.dsa.patterns.factory.C2();
        return c;
    }
*/
    public Command getCommand(String name) {
        //exercici semblant al ExerciciSingleton
        log.info("nom: "+name);
        Command cmd = cache.get(name); //mirem si a la cache esta el nom

        if (cmd==null) {
            log.info("Classloader:: name "+name); //obrim el nom de la classe
            cmd = this.getCommand2(name); //entrem a getCommand2 que fa el try i el catch
            cache.put(name, cmd);
        } else log.info ("CACHE!!!!"); //ho agafa directament de la cache perque ja esta guardat

        return cmd;
    }


    public Command getCommand2(String name){
        log.info("Carregador de classes>:  name "+name);
        Command cmd = null;
        Class c = null;
        try{
            c = Class.forName("eetac.dsa.patterns.factory."+name); //agafem el nom de la classe
            cmd = (Command)c.newInstance(); //i el guardem en el cmd
        }catch (Throwable t){
            log.error("ERROR!!!!!!!!!!! "+t.getMessage()); //llença un missatge d'error si no troba el nom de la classe
        }
        return cmd;
    }

    public static void main(String[]args){
        Command c = CommandFactory.getInstance().getCommand("eetac.dsa.patterns.factory.C1");
        c.execute();
        c = CommandFactory.getInstance().getCommand("eetac.dsa.patterns.factory.C2");
        c.execute();

        CommandFactory.getInstance().getCommand2("eetac.dsa.patterns.factory.C2").execute();
    }
}
