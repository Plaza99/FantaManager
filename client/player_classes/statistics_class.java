package player_classes;           
import java.util.ArrayList;

public class statistics_class {

	
	//Every matchday another element will be added here (up to 38 matchdays at the end of the football season)
	public int last_updated_matchday;
	public ArrayList<matchday_class> matchday;
	
	
	
	//CONSTRUCTOR
	public statistics_class(){
		this.last_updated_matchday = 0;
		
		this.matchday = new ArrayList<>();
		
	} 
	
}
