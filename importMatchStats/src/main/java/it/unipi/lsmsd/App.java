/*package it.unipi.lsmsd;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.*;
import com.mongodb.client.model.*;

import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Date;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
/*
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Quale giornata vuoi calcolare?" );

        Scanner scanner=new Scanner(System.in);

        retrieve_info_matchday(scanner.nextInt());

        //take_from_understat(scanner.nextInt());


    }

    public static void retrieve_info_matchday(Integer matchday){
        String url = "mongodb://localhost:27017";
        MongoClient mongoClient2 = MongoClients.create(url);

        // Access a Database
        MongoDatabase database2 = mongoClient2.getDatabase("fantamongo");

        // Access a Collection
        MongoCollection<Document> coll = database2.getCollection("fantacalcio");

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\DataMiningJupyter\\fantacalcio_LS5.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;
            //System.out.println(playerList);

            //Iterate over player array
            playerList.forEach( player -> parsePlayerObject( (JSONObject) player, matchday ,coll) );

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parsePlayerObject(JSONObject player, Integer matchday, MongoCollection<Document> coll)
    {

        //JSONObject playerObj = (JSONObject) player.get("Player");
        String name = (String) player.get("Player");
        System.out.println(name);

        //Get player object within list
        JSONObject playerObject = (JSONObject) player.get("statistiche");

        //Get player first name
        JSONObject matchday_ins = (JSONObject) playerObject.get("matchday"+matchday);
        System.out.println(matchday_ins);

        //take info from understat
        //take_from_understat(matchday,matchday_ins);


        //update collection
        //dovremo mettere "name" al posto di "Player" qui sotto
        //UpdateResult player1 = coll.updateOne(eq("Player", name),set("matchday"+matchday, matchday_ins));
        Bson filter=Filters.eq("name",name);
        Bson update=Updates.set("statistiche.matchday"+matchday,matchday_ins);
        UpdateOptions options = new UpdateOptions().upsert(true);
        System.out.println(coll.updateOne(filter, update, options));

    }

    public static void take_from_understat(Integer matchday,JSONObject matchday_ins){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\understat_file\\statsUnderstatMatchday"+matchday+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;

            JSONObject playerObj = (JSONObject) playerList.get(0);

            JSONArray pla= (JSONArray) playerObj.get("stats");
            //System.out.println(playerObj);
            //JSONArray pla=(JSONArray) match.get("a");


            //Iterate over player array
            pla.forEach( player_understat -> parseUnderstatObject( (JSONObject) player_understat,matchday,matchday_ins ));

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUnderstatObject(JSONObject player_understat, Integer matchday, JSONObject matchday_ins) {
        //JSONObject playerObj = (JSONObject) player.get("Player");
        //String name = (String) player_understat.get("stats");
        //System.out.println(name);

        //System.out.println(player_understat);

        //Get player object within list
        JSONObject playerObject = (JSONObject) player_understat.get("a");

        //System.out.println(playerObject);

        //Get player first name

        String name=(String) matchday_ins.get("Player");
        //System.out.println(name);

        //ArrayList<String> key= new ArrayList<>();
        String key= playerObject.keySet().toString();
        //System.out.println(key);

        key=key.substring(1,key.length()-1);

        String[] keys=key.split(", ");

        for(int i=0;i<keys.length;i++){

            //System.out.println(keys[i]);

            JSONObject elem=(JSONObject) playerObject.get(keys[i]);
            //System.out.println(elem);

            String  player= (String) elem.get("player");  //DEVI TRASFORMARE QUESTO Lorenzo De Silvestri, in L.De Silvestri
            //System.out.println(player);

            player=trasformation(player);

            if(name==player) {

                String xG = (String) elem.get("xG");
                System.out.println(xG);

                String xA = (String) elem.get("xA");
                System.out.println(xA);

                String xGChain = (String) elem.get("xGChain");
                System.out.println(xGChain);

                String xGBuildup = (String) elem.get("xGBuildup");
                System.out.println(xGBuildup);

                matchday_ins.put("xG",xG);
                matchday_ins.put("xA",xA);
                matchday_ins.put("xGChain",xGChain);
                matchday_ins.put("xGBuildup",xGBuildup);

                System.out.println(matchday_ins);
            }

        }


        //JSONObject elem=(JSONObject) playerObject.get(key);

        //System.out.println(elem);

    }

    private static String trasformation(String player) {

                String[] words=player.split(" ");
                //System.out.println(words[0]);
                if(words.length>1) {
                    player = words[0].charAt(0) + ". " + words[1];
                }
                else{
                    player=words[0];
                }
                System.out.println(player);
        return player;
    }

}
*/

package it.unipi.lsmsd;
import static com.mongodb.client.model.Indexes.descending;

import com.mongodb.client.*;
import com.mongodb.client.model.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Quale giornata vuoi calcolare?");

        Scanner scanner = new Scanner(System.in);

        retrieve_info_matchday(scanner.nextInt());

        //take_from_undershots(1,(JSONObject) null,"");

    }

    public static void retrieve_info_matchday(Integer matchday) {
        String url = "mongodb://localhost:27017";
        MongoClient mongoClient2 = MongoClients.create(url);

        // Access a Database
        MongoDatabase database2 = mongoClient2.getDatabase("fantamongo");

        // Access a Collection
        MongoCollection<Document> coll = database2.getCollection("fantacalcio");

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\DataMiningJupyter\\fantacalcio_LS5.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;
            //System.out.println(playerList);

            //Iterate over player array
            playerList.forEach(player -> parsePlayerObject((JSONObject) player, matchday, coll));

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parsePlayerObject(JSONObject player, Integer matchday, MongoCollection<Document> coll) {

        //JSONObject playerObj = (JSONObject) player.get("Player");
        String name = (String) player.get("Player");
        System.out.println(name);

        //Get player object within list
        JSONObject playerObject = (JSONObject) player.get("statistiche");

        //Get player first name
        JSONObject matchday_ins = (JSONObject) playerObject.get("matchday" + matchday);
        matchday_ins.put("xG", 0);
        matchday_ins.put("xA", 0);
        matchday_ins.put("xGChain", 0);
        matchday_ins.put("xGBuildup", 0);
        /*JSONObject matchday_ins_shots = new JSONObject();

        matchday_ins_shots.put("X", 0);
        matchday_ins_shots.put("Y", 0);
        matchday_ins_shots.put("xG", 0);
        matchday_ins_shots.put("min", 0);
        matchday_ins_shots.put("results", "");
        matchday_ins_shots.put("situation", "");
        matchday_ins_shots.put("shotType", "");

        JSONObject matchday_ins_shots_assist = new JSONObject();
        matchday_ins_shots_assist.put("player", "");
        matchday_ins_shots_assist.put("action", "");
        matchday_ins_shots.put("assist", matchday_ins_shots_assist);*/
        JSONArray shots=new JSONArray();


        System.out.println(matchday_ins);

        //take info from understat
        take_from_understat(matchday, matchday_ins, name);

        //take info from undershots
        take_from_undershots(matchday, shots, name);

        matchday_ins.put("shotsInfo", shots);

        System.out.println("AFTER UNDERSTAT" + matchday_ins);
        //update collection
        //dovremo mettere "name" al posto di "Player" qui sotto
        //UpdateResult player1 = coll.updateOne(eq("Player", name),set("matchday"+matchday, matchday_ins));
        Bson filter = Filters.eq("Player", name);
        Bson update = Updates.set("statistiche.matchday" + matchday, matchday_ins);
        UpdateOptions options = new UpdateOptions().upsert(true);
        System.out.println(coll.updateOne(filter, update, options));

    }


    public static void take_from_understat(Integer matchday, JSONObject matchday_ins, String name_player) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\understat_file\\statsUnderstatMatchday" + matchday + ".json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;

            JSONObject playerObj = (JSONObject) playerList.get(0);

            JSONArray pla = (JSONArray) playerObj.get("stats");
            //System.out.println(playerObj);
            //JSONArray pla=(JSONArray) match.get("a");


            //Iterate over player array
            pla.forEach(player_understat -> parseUnderstatObject((JSONObject) player_understat, matchday, matchday_ins, name_player));


        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUnderstatObject(JSONObject player_understat, Integer matchday, JSONObject matchday_ins, String name) {
        //JSONObject playerObj = (JSONObject) player.get("Player");
        //String name = (String) player_understat.get("stats");
        //System.out.println(name);

        //System.out.println(player_understat);

        //Get player object within list
        JSONObject playerObject = (JSONObject) player_understat.get("a");

        System.out.println(playerObject);

        //Get player first name
        //JSONObject mat = (JSONObject) playerObject.get("matchday"+matchday);
        //System.out.println(matchday_ins);
        //ArrayList<String> key= new ArrayList<>();
        String key = playerObject.keySet().toString();
        System.out.println(key);

        key = key.substring(1, key.length() - 1);
        String[] keys = key.split(", ");

        for (int i = 0; i < keys.length; i++) {

            //System.out.println(keys[i]);

            JSONObject elem = (JSONObject) playerObject.get(keys[i]);
            //System.out.println(elem);

            String player = (String) elem.get("player");  //DEVI TRASFORMARE QUESTO Lorenzo De Silvestri, in L.De Silvestri
            //System.out.println(player);

            player = trasformation(player);
            System.out.println(name);

            System.out.println(player.length());
            System.out.println(name.length());

            if (name.equals(player)) {

                System.out.println("MATCHHHHHHH----------------------------------------------------");

                String xG = (String) elem.get("xG");
                System.out.println(xG);

                String xA = (String) elem.get("xA");
                System.out.println(xA);

                String xGChain = (String) elem.get("xGChain");
                System.out.println(xGChain);

                String xGBuildup = (String) elem.get("xGBuildup");
                System.out.println(xGBuildup);

                matchday_ins.put("xG", xG);
                matchday_ins.put("xA", xA);
                matchday_ins.put("xGChain", xGChain);
                matchday_ins.put("xGBuildup", xGBuildup);



                System.out.println(matchday_ins);

                break;
            }

        }


        //JSONObject elem=(JSONObject) playerObject.get(key);

        //System.out.println(elem);

    }

    private static String trasformation(String player) {

        String[] words = player.split(" ");
        //System.out.println(words[0]);
        if (words.length > 1) {
            player = words[0].charAt(0) + ". " + words[1];
        } else {
            player = words[0];
        }
        System.out.println("PLAYER TRASFORM:" + player);
        return player;
    }


    private static void take_from_undershots(Integer matchday, JSONArray shots, String name_player) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\shots_file\\shotsUnderstatMatchday" + matchday + ".json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;

            JSONObject playerObj = (JSONObject) playerList.get(0);

            JSONArray pla = (JSONArray) playerObj.get("shots");
            //System.out.println(playerObj);
            //JSONArray pla=(JSONArray) match.get("a");


            //Iterate over player array
            pla.forEach(player_undershot -> parseUndershotObject((JSONObject) player_undershot, matchday, shots, name_player));


        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static void parseUndershotObject(JSONObject player_understat, Integer matchday, JSONArray shots, String name) {
        //JSONObject playerObj = (JSONObject) player.get("Player");
        //String name = (String) player_understat.get("stats");
        //System.out.println(name);

        //System.out.println(player_understat);

        //Get player object within list
        JSONArray playerObject = (JSONArray) player_understat.get("a");

        playerObject.forEach(it ->{
                JSONObject play=(JSONObject) it;
                System.out.println(play);
                String player= (String) play.get("player");
                System.out.println(player);

                player = trasformation(player);
                System.out.println(name);

            if (name.equals(player)) {

                System.out.println("MATCHHHHHHH----------------------------------------------------");


                String X = (String) play.get("X");
                System.out.println(X);

                String Y = (String) play.get("Y");
                System.out.println(Y);

                String xG = (String) play.get("xG");
                System.out.println(xG);

                String min = (String) play.get("minute");
                System.out.println(min);

                String result = (String) play.get("result");
                System.out.println(result);

                String situation = (String) play.get("situation");
                System.out.println(situation);

                String shotType = (String) play.get("shotType");
                System.out.println(shotType);

                String player_assist = (String) play.get("player_assisted");
                System.out.println(player_assist);

                String Action = (String) play.get("lastAction");
                System.out.println(Action);

                /*

                */
                //JSONArray matchday_shots=new JSONArray();

                JSONObject matchday_ins_shots = new JSONObject();

                matchday_ins_shots.put("X", X);
                matchday_ins_shots.put("Y", Y);
                matchday_ins_shots.put("xG", xG);
                matchday_ins_shots.put("min", min);
                matchday_ins_shots.put("results",result);
                matchday_ins_shots.put("situation", situation);
                matchday_ins_shots.put("shotType", shotType);

                JSONObject matchday_ins_shots_assist = new JSONObject();
                matchday_ins_shots_assist.put("player", player_assist);
                matchday_ins_shots_assist.put("action", Action);
                matchday_ins_shots.put("assist", matchday_ins_shots_assist);

                shots.add(matchday_ins_shots);

                System.out.println(shots);

                //matchday_ins.put("shotsInfo", matchday_shots);

                //System.out.println(matchday_ins);

            }
        });


        }
}