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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("PlayerTEst.main() : ..... begin execution ..... ");
		System.out.println();
		
		Player odellBeckum = new Player();
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever,");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);
		
		System.out.println(odellBeckum);
		System.out.println();
		
		System.out.println("PlayerTEst.main() : ..... ending execution ..... ");
		System.exit(0);;

	}

}