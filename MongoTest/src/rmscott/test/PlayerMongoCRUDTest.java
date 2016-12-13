/**
 * RMSCOTT Prototype
 */
package rmscott.test;

import rmscott.football.FootballPosition;
import rmscott.football.Player;

/**
 * @author rmscott
 *
 */
public class PlayerMongoCRUDTest {
	
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

	public Player[] getInitialPlayers() {

		Player ajGreen = null;
		Player odellBeckum = null;
		Player dezBryant = null;

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
		
		Player[] players = new Player[3];
		players[0] = odellBeckum;
		players[1] = dezBryant;
		players[2] = ajGreen;
		
		return players;

	} // end of getInitialPlayers

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerMongoCRUDTest.main() : ..... begin execution ..... ");
		System.out.println();
		
		Player odellBeckum = new Player();
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever,");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);
		
		System.out.println(odellBeckum);
		System.out.println();
		
		System.out.println("PlayerMongoCRUDTest.main() : ..... ending execution ..... ");
		System.exit(0);;

	}

}
