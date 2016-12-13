/**
 * RMSCOTT Prototype
 */
package rmscott.test;

import java.sql.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import rmscott.football.FootballPosition;
import rmscott.football.Player;

/**
 * @author rmscott
 *
 */
public class PlayerMongoCRUDTest {
	static public String FANTASY_DB_NAME = "fantasy";
	static public String PLAYER_COL_NAME = "player";
	private MongoClient mongoClient = null;
	private MongoDatabase db = null;

	public PlayerMongoCRUDTest() {
		this.initialize();
	}

	protected void initialize() {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase(PlayerMongoCRUDTest.FANTASY_DB_NAME);

	}

	protected void finalize() throws Throwable {
		try {
			mongoClient.close(); // close open files
		} finally {
			super.finalize();
		}
	}

	public void testAddPlayers() {
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME); 
		
		BasicDBObject document = new BasicDBObject();
		/*
		Gson gson = new Gson();
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(emp));

		document.put("name", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());
		table.insert(document);
		db.createCollection(player);
		*/
	} // end
																																																																																		// of
																																																																																		// testAddPlayers

	public void testReadPlayers() {

	} // end of testReadPlayers

	public void testRanklayers() {

	} // end of testRankPlayers

	public void testUpdatePlayers() {

	} // end of testUpdatePlayers()

	public void testDeletePlayers() {

	} // end of testDeletePlayers

	public Player getOnePlayer() {

		Player odellBeckum = null;

		odellBeckum = new Player();
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever,");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition(FootballPosition.WR);

		return odellBeckum;

	}

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

		PlayerMongoCRUDTest test = new PlayerMongoCRUDTest();

		System.out.println();

		System.out.println("PlayerMongoCRUDTest.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
