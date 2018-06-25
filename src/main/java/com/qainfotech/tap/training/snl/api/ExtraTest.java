
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExtraTest {
	Board br;
	 JSONObject data;
	 UUID uid;
	public ExtraTest() throws IOException
	{
		// 
	}

	@BeforeClass
	public void oneTimeSetUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		br = new Board();
		 uid=UUID.randomUUID();
		  BoardModel.init(uid);
	        data = BoardModel.data(uid);
		
	}
//game started
	@Test (priority=2)
	public void Game_In_Progress_Exception_GAme_started()  {
		
		UUID uid1;
		String name="Aditya";
		
		try
		{
			br.registerPlayer(name);
		uid1 = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
		System.out.println("uid"+uid1);
		br.rollDice(uid1);
		br.registerPlayer(name+"x");
		}
		catch (Exception e) {
		//e.printStackTrace();
		System.out.println("game in progress 1 ="+e.getMessage());	
            Assert.assertEquals(e.getClass(), new GameInProgressException().getClass());
        }
	}
	
		
		@Test 
		public void delete_player_with_uuid() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
			
			String name="Adityaa";
			
			try
			{
				
				
				//JSONArray registered =br.registerPlayer(name);
				
				
				JSONArray registered =br.registerPlayer(name);
				
				JSONObject obj= registered.getJSONObject(0);
				System.out.println("jsonObj Values" +registered);
				
				uid = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
				
				System.out.println("uid"+uid);
				
				br.deletePlayer(uid);
				//check if deleted
				br.deletePlayer(uid);
				
			}
			catch (Exception e) {
			
			System.out.println("message delete  ="+e.getMessage());	
	            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
	       
			}
			

		
	
	}
		
		@Test (expectedExceptions  = NoUserWithSuchUUIDException.class)
		public void delete_player_without_register() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
			UUID uid=null;
			uid = UUID.randomUUID();
			 br.deletePlayer(uid);
			 
			/*try
			{
			
				
			
			    br.deletePlayer(uid);
			}
			catch (Exception e) {
	            System.out.println(e.getMessage());
	            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
	        }

		*/
	
	}
		
		
		@Test 
		public void invalid_turn() throws NoUserWithSuchUUIDException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
           String name="Adityaa";
           UUID uid1=null;;
           UUID uid2=null;
			int i=1;
			try{
				
			br.registerPlayer(name +i++);
			uid1 = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
			uid2 = (UUID) br.getData().getJSONArray("players").getJSONObject(1).get("uuid");
			System.out.println(uid1);
			System.out.println(uid2);
			br.rollDice(uid1);
			br.rollDice(uid2);
			br.rollDice(uid2);
			
		}
		catch (Exception e) {
            System.out.println("error in turn" +e.getMessage());
            Assert.assertEquals(e.getClass(), new InvalidTurnException(uid2).getClass());
        }


	}
		
	
}
