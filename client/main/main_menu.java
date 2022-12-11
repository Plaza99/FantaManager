package main;
import player_classes.*;

import com.mongodb.client.*;
import org.bson.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONObject;

import com.google.gson.Gson;
import static com.mongodb.client.model.Aggregates.set;
import static com.mongodb.client.model.Filters.eq;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.*;
import com.mongodb.client.model.*;

import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class main_menu{

	
	public static void main(String[] args) {
		
		/*
		//retrieving players 
		try {
			
			File f = new File("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\player_list_dump.txt");
			Scanner myReader = new Scanner(f);
			while(myReader.hasNextLine()){
				//System.out.println(myReader.nextLine());
			}
			myReader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		*/
		
		//connecting to mongoDB 
		String uri = "mongodb://localhost:27017";
		MongoClient myClient = MongoClients.create(uri);
		MongoDatabase database = myClient.getDatabase("FantaManager");
		MongoCollection<Document> collection = database.getCollection("Player_Java_Final");
		System.out.print("Connected to mongoDB");	
				
		
		/*
		//INSERT ONE
		//creating the player class
		player_class player = new player_class("Rafael de Andrade Bittencourt Pinheir", 020);
		general_statistics_class gen_stats = new general_statistics_class();
		statistics_class stats = new statistics_class();
		matchday_class match0 = new matchday_class();
		shots_info_class shot0 = new shots_info_class();
		match0.shots_info.add(shot0);
		stats.matchday.add(match0);
		
		player.general_statistics = gen_stats;
		player.statistics = stats;
				
		//turning the player class into a json file
		Gson gson = new Gson();
		String json = gson.toJson(player);
		System.out.println(json);
		Document doc = Document.parse(json); //converting the json to document
		
		try {
			//passing the json into mongoDB
			collection.insertOne(doc);
		    System.out.print("done"); 
		}
		catch(Exception e) {
			System.out.print("MongoDB error!");
		}
		*/
		/*
		//UPDATE
		//collection.updateOne(Filters.eq("fullname", "Danilo"),Updates.set("fullname","Danilone"));
		try {
			File f = new File("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\Database\\APIFootball.json");
			Scanner myReader = new Scanner(f);
			String[] players = myReader.nextLine().split("}}]}");
			for(int i=0; i<players.length; i++){
				players[i] = players[i] + "}}]}";
				System.out.println(players[i]);
				
				
				
				ObjectMapper mapper = new ObjectMapper();
				player_class p = mapper.readValue(players[i], player_class.class);
				String player_name = p.lastname;
				int p_age = p.age;
				System.out.print(p_age);
				collection.updateOne(Filters.eq("lastname", player_name),Updates.set("age",p_age));
			}
			myReader.close();
			
		}
		catch(Exception e){
			System.out.print("errore");
		}	
		*/
		
		try{
			Path file_path = Path.of("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\Database\\APIFootball.json");
			String file_text = Files.readString(file_path);
			
			//read JSON file
			JSONArray playerList = new JSONArray(file_text);
			
			for(int i=0; i<playerList.length(); i++) {
	        	
	        	JSONObject player = playerList.getJSONObject(i);			 //get the player json
	        	
	        	JSONObject player_info = player.getJSONObject("player"); 	//getting the playe info object 'player'
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
	        	
	        	//creating the new player json file with only the needed informations
	        	player_class new_player = new player_class(playerFullname, playerFirstname, playerLastname, i, playerAge, playerBirthDate, playerBirthPlace, playerBirthCountry, playerNationality,
	        			playerHeight, playerWeight, playerPosition, playerTeam, "empty career", 30);
	    		general_statistics_class new_gen_stats = new general_statistics_class();
	    		statistics_class new_stats = new statistics_class();
	    		matchday_class match0 = new matchday_class();
	    		shots_info_class shot0 = new shots_info_class();
	    		match0.shots_info.add(shot0);
	    		new_stats.matchday.add(match0);
	    		new_player.general_statistics = new_gen_stats;
	    		new_player.statistics = new_stats;
	    		
	    			
	    		//turning the new created player class into a json file
	    		Gson gson = new Gson();
	    		String json = gson.toJson(new_player);
	    		Document doc = Document.parse(json); //converting the json to document
	    		
	    		try {
	    			//passing the json into mongoDB
	    			collection.insertOne(doc);
	    		    System.out.print("player inserted \n"); 
	    		}
	    		catch(Exception e) {
	    			System.out.print("MongoDB error, player not inserted \n");
	    		}
	    		
	    		
			}
			
	       
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		myClient.close();
	}

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
