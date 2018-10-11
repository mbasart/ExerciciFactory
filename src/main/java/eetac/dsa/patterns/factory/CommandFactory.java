package eetac.dsa.patterns.factory;

import java.lang.*;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CommandFactory {

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(CommandFactory.class);

    private static CommandFactory instance; //atributs

    private HashMap<String, Command> cache; // hashmap per guardar a la cache

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
        Command cmd = cache.get(name);
        if (cmd==null) {
            cmd = this.getCommand2(name);
            cache.put(name, cmd);
        }

        return cmd;
    }


    public Command getCommand2(String name){
        log.info("name "+name);
        Command cmd = null;
        Class c = null;
        try{
            c = Class.forName("eetac.dsa.patterns.factory"+name);
            cmd = (Command)c.newInstance();
        }catch (Exception e){
            e.printStackTrace();
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
