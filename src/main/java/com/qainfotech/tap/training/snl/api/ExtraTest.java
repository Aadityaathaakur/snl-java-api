package com.qainfotech.tap.training.snl.api;

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
	GameInProgressException gipe;
	InvalidTurnException itc;
	
	MaxPlayersReachedExeption mpre;
	NoUserWithSuchUUIDException nuwsue;
	PlayerExistsException existplayer;
	JSONObject newPlayer;
	public ExtraTest()
	{
		
	}

	@BeforeClass
	public void oneTimeSetUp() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		br = new Board();
		
		
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
	
		
		@Test (priority=1)
		public void delete_player_with_uuid() {
			UUID uid=null;//
			uid = UUID.randomUUID();
			String name="Adityaa";
			try
			{
				br.registerPlayer(name);
				 uid = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
			System.out.println("uid2"+uid);
			br.deletePlayer(uid);
			}
			catch (Exception e) {
			//e.printStackTrace();
			System.out.println("mmessage  ="+e.getMessage());	
	            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
	        }

		
	
	}
		
		@Test (priority=3)
		public void delete_player_without_register() {
			UUID uid=null;//
			uid = UUID.randomUUID();
			
			try
			{
//			
				// uid = (UUID) br.getData().getJSONArray("players").getJSONObject(0).get("uuid");
			
			    br.deletePlayer(uid);
			}
			catch (Exception e) {
	            System.out.println(e.getMessage());
	            Assert.assertEquals(e.getClass(), new NoUserWithSuchUUIDException(uid.toString()).getClass());
	        }

		
	
	}
		
		
	
}
