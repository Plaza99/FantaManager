package it.unipi.lsmsd;

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
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Quale giornata vuoi calcolare?" );

        Scanner scanner=new Scanner(System.in);

        retrieve_info_matchday(scanner.nextInt());


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
        /*JSONParser jsonParser = new JSONParser();

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
            pla.forEach( player_understat -> parseUnderstatObject( (JSONObject) player_understat) );

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        //update collection
        //dovremo mettere "name" al posto di "Player" qui sotto
        //UpdateResult player1 = coll.updateOne(eq("Player", name),set("matchday"+matchday, matchday_ins));
        Bson filter=Filters.eq("Player",name);
        Bson update=Updates.set("statistiche.matchday"+matchday,matchday_ins);
        UpdateOptions options = new UpdateOptions().upsert(true);
        System.out.println(coll.updateOne(filter, update, options));

    }

    private static void parseUnderstatObject(JSONObject player_understat/*, Integer matchday, JSONObject matchday_ins*/) {
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
        String key= playerObject.keySet().toString();
        System.out.println(key);

        String[] keys=key.split(", ");

        for(int i=0;i<keys.length;i++){

            System.out.println(keys[i]);

            JSONObject elem=(JSONObject) playerObject.get(keys[i]);
            System.out.println(elem);

            //String  xG= (String) elem.get("xG");
            //System.out.println(xG);
            /*
            String  xA= (String) elem.get("xA");
            System.out.println(xA);



            String  xGBuildup= (String) elem.get("xGBuildup");
            System.out.println(xGBuildup);*/

        }


        //JSONObject elem=(JSONObject) playerObject.get(key);

        //System.out.println(elem);

    }

}
