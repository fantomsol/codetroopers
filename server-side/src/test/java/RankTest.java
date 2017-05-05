import GameModel.Player.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lumo on 05/05/17.
 */
public class RankTest {

    @Test
    public void killEqual(){
        Player Nemo = new Player("Nemo");
        Nemo.setHp(1.0);
        Nemo.setExp(400);
        Player Fafne = new Player("Fafne");
        Fafne.setHp(1.0);
        Fafne.setExp(400);

        Nemo.attackOtherPlayer(Fafne);
        Assert.assertTrue(Nemo.getExp()==450);
        Assert.assertTrue(Fafne.getExp()==360);
    }

    @Test
    public void killStrong(){
        Player Nemo = new Player("Nemo");
        Nemo.setHp(1.0);
        Nemo.setExp(400);
        Player Fafne = new Player("Fafne");
        Fafne.setHp(1.0);
        Fafne.setExp(800);

        Nemo.attackOtherPlayer(Fafne);
        Assert.assertTrue(Nemo.getExp()==600);
        Assert.assertTrue(Fafne.getExp()==720);
    }

}
