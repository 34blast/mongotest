/**
 * 
 */
package rmscott.test.junit;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import rmscott.football.FootballPosition;
import rmscott.football.Player;
import rmscott.test.basic.PlayerMongoCRUDTest;

/**
 * @author rmscott
 *
 */
public class PlayerCRUDJunit {
	public static String ODELL_BECKUM_ID = "1";
	public static String DEZ_BRYANT_ID = "2";
	public static String AJ_GREEN_ID = "3";

	private static MongoClient mongoClient = null;
	private static MongoDatabase db = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase(PlayerMongoCRUDTest.FANTASY_DB_NAME);

		// Delete all rows before testing
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		playerCollection.deleteMany(new Document());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			mongoClient.close(); // close open files
			// Delete all rows before ending
			MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
			playerCollection.deleteMany(new Document());
		} catch (Exception exc) {
			// do nothing
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		playerCollection.deleteMany(new Document());
	}

	@Test
	public void testSinglePlayerCRUD() {

		// get Player Collection
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);

		// Add Odell Beckum
		Player player = PlayerCRUDJunit.getOdellBeckum();
		String jsonInString = null;
		// Get the object as JSON
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonInString = mapper.writeValueAsString(player);
		} catch (JsonProcessingException jsonProcessingExc) {
			System.out.println(jsonProcessingExc);
		}
		// Insert the document
		Document doc = Document.parse(jsonInString);
		playerCollection.insertOne(doc);

		// Verify the values were inserted correctly
		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		BasicDBObject odellFilter = new BasicDBObject();
		odellFilter.put("_id", PlayerCRUDJunit.ODELL_BECKUM_ID);
		List<Document> list = playerCollection.find(odellFilter).into(new ArrayList<>());
		assertTrue("Array returned in not of size 1", (list.size() == 1));

		ObjectMapper mapper = new ObjectMapper();
		Player returnedPlayer = null;
		Document playerDoc = list.iterator().next();
		String playerJson = playerDoc.toJson();
		try {
			returnedPlayer = mapper.readValue(playerJson, Player.class);
		} catch (IOException ioExc) {
			System.err.println("Error converting from JSON to Player");
			System.err.println(ioExc);
			ioExc.printStackTrace();
		}
		assertEquals("Objects are not equal", player, returnedPlayer);

		assertEquals("ID is not equal", player.get_id(), returnedPlayer.get_id());
		assertEquals("position is not equal", player.getPosition(), returnedPlayer.getPosition());
		assertEquals("ranking is not equal", new Float(player.getRanking()), new Float(returnedPlayer.getRanking()));
		assertEquals("firstName is not equal", player.getFirstName(), returnedPlayer.getFirstName());
		assertEquals("lastName is not equal", player.getLastName(), returnedPlayer.getLastName());
		assertEquals("middleName is not equal", player.getMiddleName(), returnedPlayer.getMiddleName());
		assertEquals("nickName is not equal", player.getNickName(), returnedPlayer.getNickName());

		// Update a field
		player = returnedPlayer;
		player.setPosition(FootballPosition.WR);
		UpdateResult result = playerCollection.updateOne(eq("_id", PlayerCRUDJunit.ODELL_BECKUM_ID),
				new Document("$set", new Document("position", FootballPosition.WR)));
		assertEquals("Updated rows are not 1", new Long(1), new Long(result.getModifiedCount()));

		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		list = playerCollection.find(odellFilter).into(new ArrayList<>());
		assertTrue("Array returned in not of size 1", (list.size() == 1));

		mapper = new ObjectMapper();
		playerDoc = list.iterator().next();
		playerJson = playerDoc.toJson();
		try {
			returnedPlayer = mapper.readValue(playerJson, Player.class);
		} catch (IOException ioExc) {
			System.err.println("Error converting from JSON to Player");
			System.err.println(ioExc);
			ioExc.printStackTrace();
		}
		assertEquals("Objects are not equal", player, returnedPlayer);

		// Delete the record
		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		BasicDBObject document = new BasicDBObject();
		document.put("_id", PlayerCRUDJunit.ODELL_BECKUM_ID);
		DeleteResult deleteResult = playerCollection.deleteOne(document);
		assertEquals("Deleted rows are not 1", new Long(1), new Long(deleteResult.getDeletedCount()));

	} // end of testSinglePlayerCRUD

	@Test
	public void testMultiplePlayerCRUD() {

		// get Player Collection
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		Player[] players = PlayerCRUDJunit.getInitialPlayers();

		// Add Players
		for (Player player : players) {
			String jsonInString = null;
			// Get the object as JSON
			try {
				ObjectMapper mapper = new ObjectMapper();
				jsonInString = mapper.writeValueAsString(player);
			} catch (JsonProcessingException jsonProcessingExc) {
				System.out.println(jsonProcessingExc);
			}
			// Insert the document
			Document doc = Document.parse(jsonInString);
			playerCollection.insertOne(doc);
		}

		// Verify the values were inserted correctly
		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		List<Document> list = playerCollection.find(new Document()).into(new ArrayList<>());
		assertTrue("Array returned in not of size 3", (list.size() == 3));
		Iterator<Document> iterator = list.iterator();
		while (iterator.hasNext()) {
			ObjectMapper mapper = new ObjectMapper();
			Player returnedPlayer = null;
			Document playerDoc = iterator.next();
			String playerJson = playerDoc.toJson();
			try {
				returnedPlayer = mapper.readValue(playerJson, Player.class);
			} catch (IOException ioExc) {
				System.err.println("Error converting from JSON to Player");
				System.err.println(ioExc);
				ioExc.printStackTrace();
			}
			if (returnedPlayer.get_id().equals(PlayerCRUDJunit.ODELL_BECKUM_ID)) {
				assertEquals("Odell Beckum not returned correctly", players[0], returnedPlayer);
			} else if (returnedPlayer.get_id().equals(PlayerCRUDJunit.DEZ_BRYANT_ID)) {
				assertEquals("Dez Bryant not returned correctly", players[1], returnedPlayer);
			} else if (returnedPlayer.get_id().equals(PlayerCRUDJunit.AJ_GREEN_ID)) {
				assertEquals("AJ Green not returned correctly", players[2], returnedPlayer);
			} else {
				fail("Player came back with invalid ID");
			}
		}
		// Update Position on 2 rows
		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		// change all positions not equal to WR to wide receiver
		UpdateResult result = playerCollection.updateMany(ne("postion", FootballPosition.WR),
				new Document("$set", new Document("position", FootballPosition.WR)));
		assertEquals("Two exact rows were not updated", new Long(2), new Long(result.getModifiedCount()));

		// Delete a Single the record
		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		BasicDBObject document = new BasicDBObject();
		document.put("_id", PlayerCRUDJunit.ODELL_BECKUM_ID);
		DeleteResult deleteResult = playerCollection.deleteOne(document);
		assertEquals("Deleted rows are not 1", new Long(1), new Long(deleteResult.getDeletedCount()));

		playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		assertEquals("Rows left should be 2", new Long(2), new Long(playerCollection.count()));

		// Delete rest of rows
		playerCollection.deleteMany(new Document());

	} // end of testMultiplePlayerCRUD

	@Test
	public void testSortingPlayers() {

		// get Player Collection
		MongoCollection<Document> playerCollection = db.getCollection(PlayerMongoCRUDTest.PLAYER_COL_NAME);
		Player[] players = PlayerCRUDJunit.getInitialPlayers();

		// Add Players
		for (Player player : players) {
			String jsonInString = null;
			// Get the object as JSON
			try {
				ObjectMapper mapper = new ObjectMapper();
				jsonInString = mapper.writeValueAsString(player);
			} catch (JsonProcessingException jsonProcessingExc) {
				System.out.println(jsonProcessingExc);
			}
			// Insert the document
			Document doc = Document.parse(jsonInString);
			playerCollection.insertOne(doc);
		}

		// Validate highest ranking order
		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add("ranking");
		Bson rankingDescOrderFilter = Sorts.descending(fieldNames);
		FindIterable<Document> foundCollection = playerCollection.find().sort(rankingDescOrderFilter);

		float previousRank = (float) 1000.0;
		for (Document doc : foundCollection) {
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
			float rank = player.getRanking();
			assertTrue("Ranking not in sorted order by highest first", (previousRank > rank));
			previousRank = rank;
		}

	} // end of testMultiplePlayerCRUD

	static public Player getOdellBeckum() {

		Player odellBeckum = null;

		odellBeckum = new Player();
		odellBeckum.set_id(PlayerCRUDJunit.ODELL_BECKUM_ID);
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
		odellBeckum.set_id(PlayerCRUDJunit.ODELL_BECKUM_ID);
		odellBeckum.setFirstName("Odell");
		odellBeckum.setLastName("Beckum");
		odellBeckum.setNotes("Elite Reciever");
		odellBeckum.setRanking((float) 84.3);

		dezBryant = new Player();
		dezBryant.set_id(PlayerCRUDJunit.DEZ_BRYANT_ID);
		dezBryant.setFirstName("Dez");
		dezBryant.setLastName("Bryant");
		dezBryant.setNotes("Elite Reciever");
		dezBryant.setRanking((float) 85.3);
		dezBryant.setPosition("slot");

		ajGreen = new Player();
		ajGreen.set_id(PlayerCRUDJunit.AJ_GREEN_ID);
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

}
