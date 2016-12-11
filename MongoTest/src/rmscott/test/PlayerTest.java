/**
 * RMSCOTT Prototype
 */
package rmscott.test;

import rmscott.common.Player;
import rmscott.football.FootballPosition;

/**
 * @author rmscott
 *
 */
public class PlayerTest {

	static protected Player ajGreen = null;
	static protected Player odellBeckum = null;
	static protected Player dezBryant = null;

	/**
	 * Constructor for the class
	 */
	public PlayerTest() {

	}

	public void testAddPlayers() {

	} // end of testAddPlayers

	public void testReadPlayers() {

	} // end of testReadPlayers

	public void testRanklayers() {

	} // end of testRankPlayers

	public void testUpdatePlayers() {

	} // end of testUpdatePlayers()

	public void testDeletePlayers() {

	} // end of testDeletePlayers

	static public void setUp() {

		odellBeckum = new Player();
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever,");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);

		dezBryant = new Player();
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever,");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition(FootballPosition.WR);

		ajGreen = new Player();
		ajGreen.setFirstName("Adriel");
		ajGreen.setMiddleName("Jeremiah");
		ajGreen.setLastName("Green");
		ajGreen.setNotes("Elite Reciever,");
		ajGreen.setRanking((float) 91.3);
		ajGreen.setPosition(FootballPosition.WR);

	} // end of setup

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerCRUDTest.main() : ..... begin execution ..... ");
		System.out.println();

		PlayerTest.setUp();
		PlayerTest test = new PlayerTest();

		System.out.println(odellBeckum);
		System.out.println();
		System.out.println(ajGreen);
		System.out.println();
		System.out.println(dezBryant);
		System.out.println();

		System.out.println("PlayerTEst.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
