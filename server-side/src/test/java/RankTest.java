import GameModel.Item.Weapons.WeaponsDirectory;
import GameModel.Item.Weapons.WeaponsFactory;
import GameModel.GameUtils.GeoPos;
import GameModel.Player.IPlayer;
import GameModel.Player.Player;
import GameModel.WorldPackage.World;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lumo on 05/05/17.
 */
public class RankTest {

    @Test
    public void killEqual(){
        IPlayer Nemo = new Player("Nemo");
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
    public void attackUnArmed(){
        IPlayer kalle=new Player("kalle", new GeoPos(0.0,0.0));
        IPlayer palle= new Player("palle", new GeoPos(0.0,0.0));

        palle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.WHITEFLAG));
        palle.switchWeapon(WeaponsDirectory.WHITEFLAG);

        kalle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));
        kalle.switchWeapon(WeaponsDirectory.SHOTGUN);

        World world= new World();
        world.registerPlayer(kalle);
        world.registerPlayer(palle);

        int exp=kalle.getExp();

        world.performAttack("kalle","palle");

        Assert.assertTrue(kalle.getExp()==(exp-10));

    }

    @Test
    public void killUnArmed(){
        IPlayer kalle=new Player("kalle", new GeoPos(0.0,0.0));
        IPlayer palle= new Player("palle", new GeoPos(0.0,0.0));

        palle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.WHITEFLAG));
        palle.switchWeapon(WeaponsDirectory.WHITEFLAG);
        palle.setHp(1.0);

        kalle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));
        kalle.switchWeapon(WeaponsDirectory.SHOTGUN);

        World world= new World();
        world.registerPlayer(kalle);
        world.registerPlayer(palle);

        int exp=kalle.getExp();

        world.performAttack("kalle","palle");

        Assert.assertTrue(palle.getIsAlive()==false);
        Assert.assertTrue(kalle.getExp()==(exp-10));

    }


    @Test
    public void killStrong(){
        IPlayer Nemo = new Player("Nemo");
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
