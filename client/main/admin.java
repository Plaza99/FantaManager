package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.*;

import org.bson.conversions.Bson;

import player_classes.general_statistics_class;
import player_classes.player_class;
import player_classes.statistics_class;


import java.util.Scanner;

public class admin{

	
	int last_updated_matchday; //<- maybe added as an entity in key-valueDB instead of a variable in java?
	
	public void createPlayerDocuments(){
	
		//connecting to mongoDB 
		String uri = "mongodb://localhost:27017";
		MongoClient myClient = MongoClients.create(uri);
		MongoDatabase database = myClient.getDatabase("FantaManager");
		MongoCollection<Document> collection = database.getCollection("Player_Java_Final");
		System.out.print("Connected to mongoDB...\n");	
				
		try{
			Path file_path = Path.of("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\Database\\APIFootball.json");
			String file_text = Files.readString(file_path);
					
			//read JSON file
			JSONArray playerList = new JSONArray(file_text);
					
			for(int i=0; i<playerList.length(); i++) {
			        	
				JSONObject player = playerList.getJSONObject(i);			 //get the player json
			    JSONObject player_info = player.getJSONObject("player"); 	//getting the playe info object 'player'
			    
			    //getting player's informations
			    String playerFullname = checkValueStr(player_info, "name");
			    String playerFirstname = checkValueStr(player_info, "firstname");
			    String playerLastname = checkValueStr(player_info, "lastname");
			    int playerAge = checkValueInt(player_info, "age");
			    String playerNationality = checkValueStr(player_info, "nationality");
			    String playerHeight = checkValueStr(player_info, "height");
		    	String playerWeight = checkValueStr(player_info, "weight");
			        	
	        	JSONObject player_birth = player_info.getJSONObject("birth");
			    String playerBirthDate = checkValueStr(player_birth, "date");
			    String playerBirthPlace = checkValueStr(player_birth, "place");
			    String playerBirthCountry = checkValueStr(player_birth, "country");
			        	
			    JSONArray statistics = player.getJSONArray("statistics"); 	//getting the player's statistics array 'statistics'
		    	JSONObject stats = statistics.getJSONObject(0); 			//retrieving the only element in the array
			        	
			    JSONObject team = stats.getJSONObject("team");
			    String playerTeam = checkValueStr(team, "name");
			        	
			    JSONObject games = stats.getJSONObject("games");
			    String playerPosition = checkValueStr(games, "position");;
			    
			    //retrieve career if exists
			    String player_career;
			    try{
			    	player.getString("carrier").equals(null);
			    	player_career = player.getString("carrier");
				}
			    catch(Exception e){
			    	player_career = "missing";
			    }
			    
			    //creating the new player json file with only the needed informations
			    player_class new_player = new player_class(playerFullname, playerFirstname, playerLastname, i, playerAge, playerBirthDate, playerBirthPlace, playerBirthCountry, playerNationality,
			    		playerHeight, playerWeight, playerPosition, playerTeam, player_career, 30);
			    general_statistics_class new_gen_stats = new general_statistics_class();
				statistics_class new_stats = new statistics_class();
				new_player.general_statistics = new_gen_stats;
			    new_player.statistics = new_stats;
			    
			    	
			    //turning the new created player class into a json file
			    Gson gson = new Gson();
			    String json = gson.toJson(new_player);
			    Document doc = Document.parse(json); //converting the json to document
			    
			    //passing the json into mongoDB
			    try {
			    	collection.insertOne(doc);
			    }
			    catch(Exception e) {
			    	System.out.print("MongoDB error, player " + playerFullname + " not inserted \n");
			    }  
			    
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
						
		myClient.close();
		System.out.print("'Player' DB created...\n");
	}
	
	//function that calculate the matchday
	public void calculateMatchday(){
		
		System.out.println( "Witch Matchday do you want to calculate?" );
		Scanner scanner=new Scanner(System.in);
		
		int day = scanner.nextInt();
		if(day < 1 || day > 38) {
			System.out.println("Matchday number out of range!");
		}
		else {
			retrieve_info_matchday(day);
		}
		
		scanner.close();
	}
	
	//opening the matchday file
	 public static void retrieve_info_matchday(Integer matchday_input){
		 	
		//connecting to mongoDB 
		String uri = "mongodb://localhost:27017";
		MongoClient myClient = MongoClients.create(uri);
		MongoDatabase database = myClient.getDatabase("FantaManager");
		MongoCollection<Document> collection = database.getCollection("Player_Java_Final");
		System.out.print("Connected to mongoDB...\n");	

		
		try{
			int total_players_updated = 0;
			Path file_path = Path.of("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\Database\\kickest_final.json");
			String file_text = Files.readString(file_path);	//Read JSON file
			JSONArray playerList = new JSONArray(file_text);
			for(int i=0; i<playerList.length(); i++) {
				
				JSONObject player = playerList.getJSONObject(i);
				total_players_updated += parsePlayerObject( player, matchday_input ,collection);
				
			}
				System.out.print("Matchday calculated for "+ total_players_updated + " players.\n");

	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    } 
		myClient.close();
 }

	 //get matchday's info from Kickest's dump
	 private static int parsePlayerObject(JSONObject player, Integer matchday, MongoCollection<Document> coll)
	    {

	        String name = player.getString("Player");
	        //Get player object within list
	        JSONObject playerObject = player.getJSONObject("statistiche");

	        //Get player's matchday info from file
	        JSONObject matchday_ins = playerObject.getJSONObject("matchday"+matchday);
	        String json = matchday_ins.toString();
		    Document doc = Document.parse(json); //converting the json to document
	        
		    //update collection
	        try {
		    	Bson filter = Filters.eq("fullname",name);
		    	Bson update = Updates.push("statistics.matchday",doc);
		    	Bson update_last = Updates.set("statistics.last_updated_matchday",matchday);
		    	UpdateOptions options = new UpdateOptions().upsert(false);
		    	coll.updateOne(filter, update_last, options);
		    	coll.updateOne(filter, update, options);
		    	return 1;
		    }
		    catch(Exception e) {
		    	return 0;
		    }
	    }
	 
	 
	public void deleteMore() {
		
		//connecting to mongoDB 
		String uri = "mongodb://localhost:27017";
		MongoClient myClient = MongoClients.create(uri);
		MongoDatabase database = myClient.getDatabase("FantaManager");
		MongoCollection<Document> collection = database.getCollection("Player_Java_Final");
		System.out.print("Connected to mongoDB...\n");	
		
		//deleting player with no updated statistics
		try {
	    	Bson filter = Filters.eq("statistics.last_updated_matchday",0);
	    	DeleteResult deleteResult = collection.deleteMany(filter);
	    	System.out.println(deleteResult.getDeletedCount()+" player documents deleted.\n");
	    }
	    catch(Exception e) {
	    	System.out.print("Error on 'delete' process.\n");
	    }
		myClient.close();
	} 
	 
	
	//function that checks if a string variable is null
	//(without the check done with this function, if the variable is null, the read operation from file will return as an error)
	public static String checkValueStr(JSONObject obj, String variable) {
	
		String value;
		if( obj.get(variable).equals(null)) {
		value = "missing";
		}
		else {
			value = obj.getString(variable);
		}	
		return value;
	}

	//function that checks if an integer variable is null
	//(same as above but with an int)
	public static int checkValueInt(JSONObject obj, String variable) {
	
		int value;
		if( obj.get(variable).equals(null)) {
			value = -1;
		}
		else {
			value = obj.getInt(variable);
		}	
		return value;
	}	

}
