import java.util.*;
import java.lang.*;
import org.apache.log4j.Logger;

import static java.awt.PageAttributes.MediaType.C1;
import static java.awt.PageAttributes.MediaType.C2;

public class CommandFactory {

    //mostra informacio amb log4j
    final Logger log = Logger.getLogger(CommandFactory.class);

    private static CommandFactory instance; //atributs

    public static CommandFactory getInstance(){
        //retorna la instancia de CommandFactory
        if(instance == null)
            instance = new CommandFactory();
        return instance;
    }

    public static Command getCommand(String name){
        Command c = null;
        if(name.equals("C1"))
            c = new C1();
        else
            c= new C2();
        return c;
    }

    public static Command getCommand2(String name){
        Command cmd = null;
        Class c = null;
        try{
            c = Class.forName("eetac.ea.patterns.factory"+name);
            cmd = (Command)c.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cmd;
    }

    public static void main(String[]args){
        Command c = CommandFactory.getInstance().getCommand("C1");
        c.execute();
        c = CommandFactory.getInstance().getCommand("C2");
        c.execute();

        CommandFactory.getInstance().getCommand2("C2").execute();
    }
}
