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

//import org.apache.commons.text.StringEscapeUtils;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unipi.lsmsd.player_classes.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        System.out.println("Quale giornata vuoi calcolare?");

        Scanner scanner = new Scanner(System.in);

        int matchday=scanner.nextInt();

        createPlayer();

        retrieve_info_matchday(matchday);

        calculate_matchday(matchday);  // dopo che tutte le statistiche sono al posto giusto

    }

    public static void createPlayer(){

        //connecting to mongoDB
        String uri = "mongodb://localhost:27017";
        MongoClient myClient = MongoClients.create(uri);
        MongoDatabase database = myClient.getDatabase("fantamongo");
        MongoCollection<Document> collection = database.getCollection("player_after_unicode");
        System.out.print("Connected to mongoDB...\n");

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\DataMiningJupyter\\fantacalcio_LSKickest_completo_portiere.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;
            //System.out.println(playerList);

            //Iterate over player array
            playerList.forEach(player -> findPlayerAPI((JSONObject) player,collection));

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        myClient.close();
        System.out.print("'Player' DB created...\n");

    }

    private static void findPlayerAPI(JSONObject player_kickest, MongoCollection<Document> collection) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\json_finali\\APIFootball.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;
            //System.out.println(playerList);

            for(int i=0; i<playerList.size(); i++) {

                JSONObject player_API = (JSONObject) playerList.get(i);             //get the player json
                JSONObject player_info = (JSONObject) player_API.get("player");    //getting the player info object 'player'

                //getting player's information
                String player_API_name = (String) player_info.get("name");  //ricezione name from API
                //unicode for strange characters
                //player_API_name=StringEscapeUtils.unescapeJava(player_API_name);


                String player_kickest_name=(String)player_kickest.get("Player");  //ricezione from kickest

                JSONArray player_info_stats=(JSONArray) player_API.get("statistics");
                JSONObject player_info_statistica=(JSONObject) player_info_stats.get(0);
                String player_API_team=(String) player_info_statistica.get("team");
                String player_kickest_team=(String)player_kickest.get("Team");

                if(player_API_name.equals(player_kickest_name) && player_API_team.equals(convert_team(player_kickest_team))){  //per risolvere problema G.Pezzella
                    System.out.println(player_API_name);
                    createPlayerDocuments(player_kickest,player_API,i,collection);
                }
                else{
                        //potrebbe non esserci o avere quelle stringhe strane
                    //player_API_name=StringEscapeUtils.unescapeJava(player_API_name);
                    player_API_name=trasformation(player_API_name);

                    if(player_API_name.equals(player_kickest_name) && player_API_team.equals(convert_team(player_kickest_team))){  //per risolvere problema G.Pezzella
                        System.out.println(player_API_name);
                        createPlayerDocuments(player_kickest,player_API,i,collection);
                    }
                }


            }

        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    /*private static int control_presence_unicode(String player_api_name) {
            String[] str = player_api_name.split(" ");

            for(int j=0;j<str.length;j++) {

                for (int i = 0; i < str[j].length(); i++) {
                        String a= String.valueOf(str[j].charAt(i));
                        if(a.equals("\\")){
                            return 1;
                        }
                }

            }
            return 0;
    }

    private static String unicode_trasf(String player_api_name) {
        String finale="";
        String[] str = player_api_name.split(" ");
        for(int j=0;j<str.length;j++) {
            str[j] = str[j].replace("\\", "");
            String[] arr = str[j].split("u");
            String text = "";
            for (int i = 1; i < arr.length; i++) {
                int hexVal = Integer.parseInt(arr[i], 16);
                text += (char) hexVal;
            }
            finale+=text+" ";
        }
        finale.substring(0,finale.length()-1);  //cosi gli levo l' ultimo spazio inserito sopra
        return finale;
    }*/

    public static void createPlayerDocuments(JSONObject player_kickest, JSONObject player_API, int id, MongoCollection<Document> collection){



        //JSONParser jsonParser = new JSONParser();


        //try (FileReader reader = new FileReader("C:\\Users\\matte\\DataMiningJupyter\\players_mergeEElim.json")){
            //Path file_path = Path.of("C:\\Users\\emman\\Desktop\\Large-scale and multi-structured db\\FantaManager\\Database\\APIFootball.json");  //emm
            //String file_text = Files.readString(file_path); //emm

           // Object obj = jsonParser.parse(reader);

            //JSONArray playerList = (JSONArray) obj;

            //read JSON file
            //JSONArray playerList = new JSONArray(file_text);  //emm



            //for(int i=0; i<playerList.size(); i++) {

                //JSONObject player = (JSONObject) playerList.get(i);			 //get the player json
                JSONObject player_info = (JSONObject) player_API.get("player"); 	//getting the player info object 'player'

                //getting player's information
                String playerFullname = (String)player_kickest.get("Player");  //sostituito ricezione name from APIFootball con ricezione name from kickest (matteo)
                //System.out.println(playerFullname);
                String playerFirstname = (String)player_info.get("firstname");
                String playerLastname = (String)player_info.get("lastname");
                Long playerAge = (Long) player_info.get("age");
                if(playerAge==null){
                        //continue; //cosi ci leviamo i giocatori senza eta (in quanto probabilmente non hanno informazioni adeguate e non saranno presenti su kickest)
                }
                String playerNationality = (String)player_info.get("nationality");
                String playerHeight = (String)player_info.get("height");
                String playerWeight = (String)player_info.get("weight");

                JSONObject player_birth = (JSONObject) player_info.get("birth");
                String playerBirthDate = (String)player_birth.get("date");
                String playerBirthPlace = (String)player_birth.get("place");
                String playerBirthCountry =(String)player_birth.get("country");

                JSONArray statistics = (JSONArray)player_API.get("statistics"); 	//getting the player's statistics array 'statistics'
                JSONObject stats = (JSONObject)statistics.get(0); 			//retrieving the only element in the array

                //JSONObject team = (JSONObject)stats.get("team");
                String playerTeam = (String)stats.get("team");

                JSONObject games = (JSONObject)stats.get("games");
                String playerPosition =(String)games.get("position");

                //retrieve career if exists
                String player_career;
                try{
                    //player.get("carrier").equals(null);
                    player_career = (String) player_API.get("carrier");
                }
                catch(Exception e){
                    player_career = "missing";
                }

                //creating the new player json file with only the needed information
                player_class new_player = new player_class(playerFullname, playerFirstname, playerLastname, id, Math.toIntExact(playerAge), playerBirthDate, playerBirthPlace, playerBirthCountry, playerNationality,
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

            //}
        /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/


    }

    private static void calculate_matchday(int matchday) {
        String url = "mongodb://localhost:27017";
        MongoClient mongoClient2 = MongoClients.create(url);

        // Access a Database
        MongoDatabase database2 = mongoClient2.getDatabase("fantamongo");

        // Access a Collection
        MongoCollection<Document> coll = database2.getCollection("player_after_unicode");

        //scorre tutti gli elementi del Document
        try(MongoCursor<Document> cursor=coll.find().iterator()){
            while(cursor.hasNext()){
                    String player=cursor.next().toJson();
                    //System.out.println(player);
                    JSONParser parser = new JSONParser();
                    JSONObject json_player = (JSONObject) parser.parse(player);

                    String player_name= (String) json_player.get("fullname");
                    System.out.println(player_name);

                    String team= (String) json_player.get("team");

                    JSONObject playerObject = (JSONObject) json_player.get("statistics");
                    JSONObject playermatch = (JSONObject) playerObject.get("matchday");
                    JSONObject matchday_K = (JSONObject) playermatch.get("matchday" + matchday);
                    JSONObject matchday_stat = (JSONObject) matchday_K.get("stats");

                    //take all statistics for matchday of that player
                    String cr= (String) matchday_stat.get("CR");
                    String plus= (String) matchday_stat.get("Plus");
                    String apps= (String) matchday_stat.get("Apps");
                    String starter= (String) matchday_stat.get("Starter");
                    String mins= (String) matchday_stat.get("Mins");
                    String goals= (String) matchday_stat.get("Goals");
                    String shots= (String) matchday_stat.get("Shots");
                    String on_tar_shots= (String) matchday_stat.get("On Tar. Shots");
                    String pen_goals= (String) matchday_stat.get("Pen Goals");
                    String successful_dribbles= (String) matchday_stat.get("Successful Dribbles");
                    //String ast= (String) matchday_stat.get("Ast");  //gia presente assist
                    String acc_pass= (String) matchday_stat.get("Acc Pass");
                    String key_pass= (String) matchday_stat.get("Key Pass");
                    String fouls= (String) matchday_stat.get("Fouls");
                    String was_fouled= (String) matchday_stat.get("Was Fouled");
                    String yc= (String) matchday_stat.get("YC");
                    String rc= (String) matchday_stat.get("RC");
                    String rec_ball= (String) matchday_stat.get("Rec Ball");
                    String tackles= (String) matchday_stat.get("Tackles");
                    String clean_sheets= (String) matchday_stat.get("Clean Sheets");
                    String woods= (String) matchday_stat.get("Woods");
                    String headed_goals= (String) matchday_stat.get("Headed Goals");
                    String freekick_goals= (String) matchday_stat.get("Freekick Goals");
                    String big_chance_missed= (String) matchday_stat.get("Big Chance Missed");
                    String goal_min= (String) matchday_stat.get("Goal/Min");
                    String shot_con_rate= (String) matchday_stat.get("Shot Con Rate");
                    String total_dribbles= (String) matchday_stat.get("Total Dribbles");
                    String assists= (String) matchday_stat.get("Assists");
                    String passes= (String) matchday_stat.get("Passes");
                    String acc_pass_per= (String) matchday_stat.get("Acc Pass %");
                    String big_chance_created= (String) matchday_stat.get("Big Chance Created");
                    String cross= (String) matchday_stat.get("Cross");
                    String acc_cross= (String) matchday_stat.get("Acc Cross");
                    String cross_no_corner= (String) matchday_stat.get("Cross no Corner");
                    String second_yc= (String) matchday_stat.get("2nd YC");
                    String err_leading_to_goal= (String) matchday_stat.get("Err leading to Goal");
                    String og= (String) matchday_stat.get("OG");
                    String interception= (String) matchday_stat.get("Interception");
                    String won_duels= (String) matchday_stat.get("Won Duels");
                    String lost_duels= (String) matchday_stat.get("Lost Duels");
                    String aer_duels_won= (String) matchday_stat.get("Aer. Duels Won");
                    String aer_duels_lost= (String) matchday_stat.get("Aer. Duels Lost");
                    String saves= (String) matchday_stat.get("Saves");
                    String goals_conceded= (String) matchday_stat.get("Goals Conceded");
                    String pen_saves= (String) matchday_stat.get("Pen Saves");
                    String pen_goals_conceded= (String) matchday_stat.get("Pen Goals Conceded");

                    float score;

                    if(Integer.parseInt(mins)>14) {
                        score =   Float.parseFloat(plus) + Float.parseFloat(apps) + Float.parseFloat(starter)/2 + 3*Float.parseFloat(goals) + Float.parseFloat(shots)/2 + Float.parseFloat(on_tar_shots)
                                + Float.parseFloat(pen_goals)/3 + Float.parseFloat(successful_dribbles) + Float.parseFloat(assists) + Float.parseFloat(acc_pass)/100 + Float.parseFloat(key_pass)/2 - Float.parseFloat(fouls)/2
                                + Float.parseFloat(was_fouled)/2 + Float.parseFloat(yc) + Float.parseFloat(rc) + Float.parseFloat(rec_ball)/3 + Float.parseFloat(tackles)/5 + Float.parseFloat(clean_sheets)
                                + Float.parseFloat(woods) + Float.parseFloat(headed_goals)/10 + Float.parseFloat(freekick_goals)/3 - Float.parseFloat(big_chance_missed)/4 + Float.parseFloat(goal_min)/100 + Float.parseFloat(shot_con_rate)/100
                                + Float.parseFloat(total_dribbles)/4 +  Float.parseFloat(passes)/100 + Float.parseFloat(acc_pass_per)/100
                                + Float.parseFloat(big_chance_created)/4 + Float.parseFloat(cross)/4 + Float.parseFloat(acc_cross)
                                + Float.parseFloat(cross_no_corner) + Float.parseFloat(second_yc) - Float.parseFloat(err_leading_to_goal) + Float.parseFloat(og) + Float.parseFloat(interception)/2 + Float.parseFloat(won_duels)/3
                                - Float.parseFloat(lost_duels)/3 + Float.parseFloat(aer_duels_won)/3 - Float.parseFloat(aer_duels_lost)/3 + Float.parseFloat(saves)/4 - Float.parseFloat(goals_conceded)/3 + Float.parseFloat(pen_saves)*3
                                - Float.parseFloat(pen_goals_conceded)/2;
                    }
                    else{
                        score=0;
                    }

                    System.out.println(score);
                    float mod_value=calculate_mod_value(score);
                    //matchday_stat.put("score",score);  //inserisce score nel matchday
                    //System.out.println(matchday_stat.get("score"));

                    JSONObject score_value=new JSONObject();
                    score_value.put("score",score);
                    score_value.put("modif_value",mod_value);

                    //Bson filter = Filters.eq("fullname", player_name);   //change 1045
                    Bson filter=Filters.and(eq("fullname", player_name),eq("team", team));  //AND per risolvere problema L.Pellegrini
                    Bson update = Updates.set("statistics.matchday.matchday" + matchday+".score-value", score_value);
                    UpdateOptions options = new UpdateOptions().upsert(true);
                    System.out.println(coll.updateOne(filter, update, options));



                    //Bson filter2 = Filters.eq("fullname", player_name);   //change 1045
                    /*Bson filter2=Filters.and(eq("fullname", player_name),eq("team", team));  //AND per risolvere problema L.Pellegrini
                    Bson update2 = Updates.set("statistics.matchday.matchday" + matchday+".modif_value", mod_value);
                    UpdateOptions options2 = new UpdateOptions().upsert(true);
                    System.out.println(coll.updateOne(filter2, update2, options2));*/

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private static float calculate_mod_value(float score) {  //in base allo score della giornata calcola di quanto deve essere modificato il valore del giocatore

            if(score<=0){
                return -1;
            }
            else{
                if(score<7){
                        return 0;
                }
                else{
                        return 1;
                }
            }
    }

    public static void retrieve_info_matchday(Integer matchday) {
        String url = "mongodb://localhost:27017";
        MongoClient mongoClient2 = MongoClients.create(url);

        // Access a Database
        MongoDatabase database2 = mongoClient2.getDatabase("fantamongo");

        // Access a Collection
        MongoCollection<Document> coll = database2.getCollection("player_after_unicode");

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\DataMiningJupyter\\fantacalcio_LSKickest_completo_portiere.json")) {
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

        String team=(String) player.get("Team");

        team=convert_team(team);

        String name_puntato;

        if(name.charAt(1)!='.'){
            String[] words = name.split(" ");
            if(words.length>1){
                    name_puntato=trasformation(name); //lo fa diventare puntato anche se si chiama Mario Rui (M. Rui)
            }
            else{
                name_puntato=name;
            }
        }
        else{
            name_puntato=name;
        }

        //Get player object within list
        JSONObject playerObject = (JSONObject) player.get("statistiche"); //qui prende da file kickest

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


        //System.out.println(matchday_ins);

        //take info from understat
        take_from_understat(matchday, matchday_ins, name_puntato);

        //take info from undershots
        take_from_undershots(matchday, shots, name_puntato);

        matchday_ins.put("shotsInfo", shots);

        System.out.println("AFTER UNDERSTAT" + matchday_ins);
        //update collection
        //dovremo mettere "name" al posto di "Player" qui sotto
        //UpdateResult player1 = coll.updateOne(eq("Player", name),set("matchday"+matchday, matchday_ins));
        //Bson filter = Filters.eq("fullname", name);
        //Bson filter2 = Filters.eq("team", team);
        Bson filter=Filters.and(eq("fullname", name),eq("team", team));  //AND per risolvere problema L.Pellegrini
        Bson update = Updates.set("statistics.matchday.matchday" + matchday+".stats", matchday_ins);
        UpdateOptions options = new UpdateOptions().upsert(true);
        System.out.println(coll.updateOne(filter, update, options));

    }



    public static void take_from_understat(Integer matchday, JSONObject matchday_ins, String name_player) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\json_finali\\matchday_stats\\understat_file\\statsUnderstatMatchday" + matchday + ".json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray playerList = (JSONArray) obj;

            JSONObject playerObj = (JSONObject) playerList.get(0); //perchè questi file sono array di un elemento, al cui interno poi ci sono altri array, infatti ha una quadra in cima e una in fondo

            JSONArray pla = (JSONArray) playerObj.get("stats"); //ok, prende tutte e 10 le partite

            //System.out.println(pla.size());
            //JSONArray pla=(JSONArray) match.get("a");


            //Iterate over player array
            pla.forEach(match_understat -> parseUnderstatObject((JSONObject) match_understat, matchday, matchday_ins, name_player));


        } //catch (FileNotFoundException e) {
        //e.printStackTrace();
        //}
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUnderstatObject(JSONObject match_understat, Integer matchday, JSONObject matchday_ins, String name) {
        //JSONObject playerObj = (JSONObject) player.get("Player");
        //String name = (String) player_understat.get("stats");
        //System.out.println(name);
        int match=0; //se ha fatto match deve smettere di iterare

        //System.out.println(player_understat);

        //Get player object within list
        JSONObject playerObject1 = (JSONObject) match_understat.get("a");
        JSONObject playerObject2 = (JSONObject) match_understat.get("h");

        //System.out.println(playerObject);

        //Get player first name
        //JSONObject mat = (JSONObject) playerObject.get("matchday"+matchday);
        //System.out.println(matchday_ins);
        //ArrayList<String> key= new ArrayList<>();
        String key = playerObject1.keySet().toString();
        //System.out.println(key);

        key = key.substring(1, key.length() - 1);

        String key2 = playerObject2.keySet().toString();
        //System.out.println(key2);

        key2 = key2.substring(1, key2.length() - 1);


        String[] keys1 = key.split(", ");
        String[] keys2= key2.split(", ");
        
        //String[] keys = new String[0];

        //System.arraycopy(keys1, 0, keys, 0, keys1.length);
        //System.arraycopy(keys2, 0, keys, keys1.length, keys1.length+keys2.length);

        for (int i = 0; i < keys1.length; i++) {

            //System.out.println(keys[i]);

            JSONObject elem = (JSONObject) playerObject1.get(keys1[i]);
            //System.out.println(elem);

            String player = (String) elem.get("player");  //DEVI TRASFORMARE QUESTO Lorenzo De Silvestri, in L.De Silvestri
            //System.out.println(player);

            String[] words = name.split(" ");

            if(words.length>1) {
                player = trasformation(player);
            }
            else{
                //System.out.println(name);
                String[] words2 = player.split(" ");
                if(words2.length>1) {
                    if (name.equals(words2[0])) {
                        System.out.println(words2[0]);
                        System.out.println(words2[1]);
                        player = words2[0];
                    } else{
                        //System.out.println(words2[1]);
                        player = words2[1];
                    }
                }
            }
            //System.out.println(player);

           // System.out.println(player.length());
            //System.out.println(name.length());

            if (name.equals(player)) {

                System.out.println("MATCHHHHHHH----------------------------------------------------");

                String xG = (String) elem.get("xG");
                //System.out.println(xG);

                String xA = (String) elem.get("xA");
                //System.out.println(xA);

                String xGChain = (String) elem.get("xGChain");
                //System.out.println(xGChain);

                String xGBuildup = (String) elem.get("xGBuildup");
                //System.out.println(xGBuildup);

                matchday_ins.put("xG", xG);
                matchday_ins.put("xA", xA);
                matchday_ins.put("xGChain", xGChain);
                matchday_ins.put("xGBuildup", xGBuildup);

                match=1;  //DOVREI FARE IN MODO CHE SMETTA DI ITERARE SE L' HA TROVATO

                //System.out.println(matchday_ins);

                //break;
            }
            if(match==1){
                break;
            }

        }
        //adesso per quelli che hanno etichetta h
        if(match==0) {
            for (int i = 0; i < keys2.length; i++) {

                //System.out.println(keys[i]);

                JSONObject elem = (JSONObject) playerObject2.get(keys2[i]);
                //System.out.println(elem);

                String player = (String) elem.get("player");  //DEVI TRASFORMARE QUESTO Lorenzo De Silvestri, in L.De Silvestri
                //System.out.println(player);

                String[] words = name.split(" ");

                if(words.length>1) {
                    player = trasformation(player);
                }
                else{
                    //System.out.println(name);
                    String[] words2 = player.split(" ");
                    if(words2.length>1) {
                        if (name.equals(words2[0])) {
                            System.out.println(words2[0]);
                            System.out.println(words2[1]);
                            player = words2[0];
                        } else{
                            //System.out.println(words2[1]);
                            player = words2[1];
                        }
                    }
                }

                if (name.equals(player)) {

                    System.out.println("MATCHHHHHHH----------------------------------------------------");

                    String xG = (String) elem.get("xG");
                    //System.out.println(xG);

                    String xA = (String) elem.get("xA");
                    //System.out.println(xA);

                    String xGChain = (String) elem.get("xGChain");
                    //System.out.println(xGChain);

                    String xGBuildup = (String) elem.get("xGBuildup");
                    //System.out.println(xGBuildup);

                    matchday_ins.put("xG", xG);
                    matchday_ins.put("xA", xA);
                    matchday_ins.put("xGChain", xGChain);
                    matchday_ins.put("xGBuildup", xGBuildup);

                    //match=1;  //DOVREI FARE IN MODO CHE SMETTA DI ITERARE SE L' HA TROVATO

                    //System.out.println(matchday_ins);

                    //break;
                }
                if(match==1){
                    break;
                }
            }
        }



        //JSONObject elem=(JSONObject) playerObject.get(key);

        //System.out.println(elem);

    }

    private static String trasformation(String player) {

        String[] words = player.split(" ");
        //System.out.println(words[0]);
        if (words.length > 1) {
            if(words.length==2)
                player = words[0].charAt(0) + ". " + words[1];
            else
                player=words[0].charAt(0) + ". " + words[1]+" "+words[2];
        } else {
            player = words[0];
        }
        //System.out.println("PLAYER TRASFORM:" + player);
        return player;
    }


    private static String convert_team(String team) {

            switch(team){
                case "ATA":
                    team="Atalanta";
                    break;
                case "BOL":
                    team="Bologna";
                    break;
                case "CAG":
                    team="Cagliari";
                    break;
                case "EMP":
                    team="Empoli";
                    break;
                case "FIO":
                    team="Fiorentina";
                    break;
                case "GEN":
                    team="Genoa";
                    break;
                case "INT":
                    team="Inter";
                    break;
                case "JUV":
                    team="Juventus";
                    break;
                case "LAZ":
                    team="Lazio";
                    break;
                case "MIL":
                    team="AC Milan";
                    break;
                case "NAP":
                    team="Napoli";
                    break;
                case "ROM":
                    team="AS Roma";
                    break;
                case "SAL":
                    team="Salernitana";
                    break;
                case "SAM":
                    team="Sampdoria";
                    break;
                case "SAS":
                    team="Sassuolo";
                    break;
                case "SPE":
                    team="Spezia";
                    break;
                case "TOR":
                    team="Torino";
                    break;
                case "UDI":
                    team="Udinese";
                    break;
                case "VEN":
                    team="Venezia";
                    break;
                case "VER":
                    team="Verona";
                    break;


            }
            return team;
    }



    private static void take_from_undershots(Integer matchday, JSONArray shots, String name_player) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\matte\\OneDrive\\Desktop\\LargeScale\\jsonEdo\\json_finali\\matchday_stats\\shots_file\\shotsUnderstatMatchday" + matchday + ".json")) {
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
                //System.out.println(play);
                String player= (String) play.get("player");
                //System.out.println(player);

                player = trasformation(player);
                //System.out.println(name);

            if (name.equals(player)) {

                System.out.println("MATCHHHHHHHSHOTS----------------------------------------------------");


                String X = (String) play.get("X");
                //System.out.println(X);

                String Y = (String) play.get("Y");
                //System.out.println(Y);

                String xG = (String) play.get("xG");
                //System.out.println(xG);

                String min = (String) play.get("minute");
                //System.out.println(min);

                String result = (String) play.get("result");
                //System.out.println(result);

                String situation = (String) play.get("situation");
                //System.out.println(situation);

                String shotType = (String) play.get("shotType");
                //System.out.println(shotType);

                String player_assist = (String) play.get("player_assisted");
                //System.out.println(player_assist);

                String Action = (String) play.get("lastAction");
                //System.out.println(Action);

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

                //System.out.println(shots);

                //matchday_ins.put("shotsInfo", matchday_shots);

                //System.out.println(matchday_ins);

            }
        });


        }
}