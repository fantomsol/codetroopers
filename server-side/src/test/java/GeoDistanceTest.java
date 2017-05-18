import GameModel.GameUtils.Exceptions.GameException;
import GameModel.GameUtils.GeoDistance;
import GameModel.GameUtils.GeoPos;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by latiif on 3/30/17.
 */
public class GeoDistanceTest {
	@Test
	public void distanceTest(){
		GeoPos p1;
		GeoPos p2;

		for (int i=0;i<50;i++){
			try {
				p1=new GeoPos(Math.random() * 90 + -90,Math.random() * 180 + -180);
				p2=new GeoPos(Math.random() * 90 + -90,Math.random() * 180 + -180);

				Assert.assertEquals(GeoDistance.getDistance(p2,p1), GeoDistance.getDistance(p1,p2));
			} catch (GameException e) {
				//Should not get here
				Assert.assertTrue(1==2);
			}

		}
	}

	@Test
	public void invalidGeoPosVariableTest(){
		try {
			GeoPos point = new GeoPos(888.0,75.0);
		} catch (GameException e) {
			//Should throw and exception here
			Assert.assertTrue(true);
		}

		try {
			GeoPos point = new GeoPos(-88.0,125.0);
		} catch (GameException e) {
			//Should throw and exception here
			Assert.assertTrue(true);
		}


	}
}
