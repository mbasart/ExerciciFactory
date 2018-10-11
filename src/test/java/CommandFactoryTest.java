import eetac.dsa.patterns.factory.C1;
import eetac.dsa.patterns.factory.C2;
import eetac.dsa.patterns.factory.Command;
import eetac.dsa.patterns.factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFactoryTest {
    //aqui farem el test


    @Test
    public void testGetCommand() {
        Command c = CommandFactory.getInstance().getCommand("C1");
        Assert.assertEquals (C1.class, c.getClass());

        c = CommandFactory.getInstance().getCommand("C1");
        Assert.assertEquals (C1.class, c.getClass());

        c = CommandFactory.getInstance().getCommand("C2");
        Assert.assertEquals (C2.class, c.getClass());
    }

}
