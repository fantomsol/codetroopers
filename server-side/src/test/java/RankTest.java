import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.*;
import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Beings.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lumo on 05/05/17.
 */
public class RankTest {

    @Test
    public void killEqual() throws FactoryException, CooldownException, CombatException, GeographicalException {
        Player Nemo = new Player("Nemo", new GeoPos(0.0, 0.0));
        Nemo.setHp(1.0);
        Nemo.setExp(400);
        Player Fafne = new Player("Fafne", new GeoPos(0.0, 0.0));
        Fafne.setHp(1.0);
        Fafne.setExp(400);

        Nemo.attackPlayer(Fafne);
        Assert.assertTrue(Nemo.getExp() == 450);
        Assert.assertTrue(Fafne.getExp() == 360);
    }
/*
    @Test
    public void attackUnArmed() throws GameException {
        Player kalle = new Player("kalle", new GeoPos(0.0, 0.0));
        Player palle = new Player("palle", new GeoPos(0.0, 0.0));

        palle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.WHITEFLAG));
        palle.switchWeapon(WeaponsDirectory.WHITEFLAG);

        kalle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));
        kalle.switchWeapon(WeaponsDirectory.SHOTGUN);

        World world = new MultiplayerWorld();
        world.registerPlayer(kalle);
        world.registerPlayer(palle);

        int exp = kalle.getExp();

        world.performAttack("kalle", "palle");

        Assert.assertTrue(kalle.getExp() == (exp - 10));

    }

    @Test
    public void killUnArmed() throws GameException {
        Player kalle = new Player("kalle", new GeoPos(0.0, 0.0));
        Player palle = new Player("palle", new GeoPos(0.0, 0.0));

        palle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.WHITEFLAG));
        palle.switchWeapon(WeaponsDirectory.WHITEFLAG);
        palle.setHp(1.0);

        kalle.grantWeapon(WeaponsFactory.createWeapon(WeaponsDirectory.SHOTGUN));
        kalle.switchWeapon(WeaponsDirectory.SHOTGUN);

        World world = new MultiplayerWorld();
        world.registerPlayer(kalle);
        world.registerPlayer(palle);

        int exp = kalle.getExp();

        world.performAttack("kalle", "palle");

        Assert.assertTrue(!palle.getIsAlive());
        Assert.assertTrue(kalle.getExp().equals(exp - 10));

    }
*/

    @Test
    public void killStrong() throws FactoryException, CooldownException, CombatException, GeographicalException {
        Player Nemo = new Player("Nemo", new GeoPos(0.0, 0.0));
        Nemo.setHp(1.0);
        Nemo.setExp(400);
        Player Fafne = new Player("Fafne", new GeoPos(0.0, 0.0));
        Fafne.setHp(1.0);
        Fafne.setExp(800);

        Nemo.attackPlayer(Fafne);
        Assert.assertTrue(Nemo.getExp() == 600);
        Assert.assertTrue(Fafne.getExp() == 720);
    }



}
