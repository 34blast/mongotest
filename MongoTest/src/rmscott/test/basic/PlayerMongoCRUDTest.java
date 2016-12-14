/**
 * RMSCOTT Prototype
 */
package rmscott.test.basic;

import static com.mongodb.client.model.Filters.ne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.UpdateResult;

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
		System.out.println();
		System.out.println("testAddPlayers ...................");
		System.out.println();

		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);

		Player[] players = PlayerMongoCRUDTest.getInitialPlayers();
		for (int idx = 0; idx < players.length; idx++) {
			String jsonInString = null;
			// Get the object as JSON
			try {
				ObjectMapper mapper = new ObjectMapper();
				jsonInString = mapper.writeValueAsString(players[idx]);
			} catch (JsonProcessingException jsonProcessingExc) {
				System.out.println(jsonProcessingExc);
			}
			// Insert the document
			Document doc = Document.parse(jsonInString);
			playerCollection.insertOne(doc);
		}

	} // end of testAddPlayers

	public void testReadAllPlayers() {
		System.out.println();
		System.out.println("testReadAllPlayers ...................");

		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		System.out.println("Player Collection Count : " + playerCollection.count());
		FindIterable<Document> foundCollection = playerCollection.find();

		for (Document doc : foundCollection) {
			System.out.println();
			System.out.print("Guts of collection : Player");
			System.out.println("--------------------------------------------------------------");
			String playerJson = doc.toJson();
			ObjectMapper mapper = new ObjectMapper();
			Player player = null;
			try {
				player = mapper.readValue(playerJson, Player.class);
			} catch (IOException ioExc) {
				System.err.println("Error converting from JSON to Player");
				System.err.println(ioExc);
				ioExc.printStackTrace();
			}
			System.out.println(doc.toJson());
			System.out.println(player.toString());			System.out.println();
		}
		System.out.println();

	} // end of testReadAllPlayers

	public void testRanklayers() {
		System.out.println();
		System.out.println("testRanklayers ...................");
		System.out.println();
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("ranking");
		Bson rankingDescOrderFilter = Sorts.descending(fieldNames);
		FindIterable<Document> foundCollection = playerCollection.find().sort(rankingDescOrderFilter);

		for (Document doc : foundCollection) {
			System.out.println();
			System.out.print("Guts of collection : Player");
			System.out.println("--------------------------------------------------------------");
			String playerJson = doc.toJson();
			ObjectMapper mapper = new ObjectMapper();
			Player player = null;
			try {
				player = mapper.readValue(playerJson, Player.class);
			} catch (IOException ioExc) {
				System.err.println("Error converting from JSON to Player");
				System.err.println(ioExc);
				ioExc.printStackTrace();
			}
			System.out.println(doc.toJson());
			System.out.println(player.toString());			System.out.println();
		}
		System.out.println();


	} // end of testRankPlayers

	public void testUpdatePlayers() {
		System.out.println();
		System.out.println("testUpdatePlayers ...................");
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);

		// change all positions not equal to WR to wide receiver
		UpdateResult result = playerCollection.updateMany(ne("postion", FootballPosition.WR),
				new Document("$set", new Document("position", FootballPosition.WR)));

		System.out.println("Player rows updated : " + result.getModifiedCount());
		System.out.println();

	} // end of testUpdatePlayers()

	public void testDeleteOnePlayer() {
		System.out.println();
		System.out.println("testDeleteOnePlayer ...................");
		System.out.println();
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);

		// playerCollection.drop();
		BasicDBObject document = new BasicDBObject();
		document.put("_id", "1");

		playerCollection.deleteOne(document);

	} // end of testDeletePlayers

	public void testDeleteAllPlayers() {
		System.out.println();
		System.out.println("testDeleteAllPlayers ...................");
		System.out.println();

		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);

		// playerCollection.drop();
		playerCollection.deleteMany(new Document());
		/*
		 * Pattern patther = com.mongodb.client.model.Filters.regex("_id",
		 * pattern) (fieldName, value); Bson filter = gte("i", 100);
		 * DeleteResult deleteResult = playerCollection.deleteMany(filter);
		 * System.out.println("PlayerCollection deleted rows : ");
		 * System.out.println(deleteResult.getDeletedCount());
		 */

	} // end of testDeleteAllPlayers

	static public Player getOnePlayer() {

		Player odellBeckum = null;

		odellBeckum = new Player();
		odellBeckum.set_id("1");
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);
		odellBeckum.setPosition("slot");

		return odellBeckum;

	}

	static public Player[] getInitialPlayers() {

		Player ajGreen = null;
		Player odellBeckum = null;
		Player dezBryant = null;

		odellBeckum = new Player();
		odellBeckum.set_id("1");
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);

		dezBryant = new Player();
		dezBryant.set_id("2");
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition("slot");

		ajGreen = new Player();
		ajGreen.set_id("3");
		ajGreen.setFirstName("Adriel");
		ajGreen.setMiddleName("Jeremiah");
		ajGreen.setLastName("Green");
		ajGreen.setNotes("Elite Reciever");
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
		test.testDeleteAllPlayers();
		test.testAddPlayers();
		test.testReadAllPlayers();
		test.testRanklayers();
		test.testUpdatePlayers();
		test.testReadAllPlayers();
		test.testDeleteOnePlayer();
		test.testReadAllPlayers();
		test.testDeleteAllPlayers();
		test.testReadAllPlayers();

		System.out.println();

		System.out.println("PlayerMongoCRUDTest.main() : ..... ending execution ..... ");
		System.exit(0);
		;

	}

}
