import java.io.*;
import java.util.Collections;  
import java.util.Comparator;  
import java.util.HashMap;  
import java.util.LinkedHashMap;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry; 
import java.util.Iterator;

public class Player_Ratings {

static String filePath = "C:/Users/Arnab/Music/Player Ratings.csv";
// writing csv file function
public static void main (String[] args) {

Player_Ratings crw = new Player_Ratings();
crw.readcsv(filePath);
}

public void readcsv(String filePath) {

// reading csv file
try {
BufferedReader readFile = new BufferedReader(new FileReader(filePath));
Map<Integer, Integer> hash_map = new HashMap<Integer, Integer>(); 
String readFilerow; int count = 0; int sum = 0; float average_rating; double max_age_to_rating_ratio = 0; String player_name = ""; int max_rating = 0; int players = 0; String[] player_names = new String[100]; 
System.out.println("\n Details of 40+ aged players \n");
while ((readFilerow = readFile.readLine()) != null) {
      String[] data = readFilerow.split(",");
      if (!data[2].equals("Age (Years)")) {
          double age_to_rating_ratio = Double.parseDouble(data[2])/Double.parseDouble(data[3]);
          if (age_to_rating_ratio > max_age_to_rating_ratio) {
              max_age_to_rating_ratio = age_to_rating_ratio;
              player_name = data[1];
          }
              hash_map.put(players,Integer.parseInt(data[3]));
              player_names[players] = data[1];
          if (Integer.parseInt(data[2]) > 40) {
              System.out.println(data[1] + " having rating of " + data[3]);
              sum = sum + Integer.parseInt(data[3]); 
              count++;
          }
          players++;
       }
}
average_rating = sum/count; players = 0;

System.out.println("\n Average rating of all 40+ aged players = " + average_rating + "\n");
System.out.println(player_name + " is having maximum age to rating ratio"+"\n");

Map<Integer, Integer> hm = sortbyvalue(hash_map);
System.out.println ("Players having least 3 ratings are:"+"\n");

Iterator<Integer> itr = hm.keySet().iterator();
        while (itr.hasNext() && players < 3) {
            System.out.println(player_names[itr.next()]);
            players++;
        }
readFile.close();
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
}

public Map sortbyvalue(Map hash_map)   
{  

//convert HashMap into List   
List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(hash_map.entrySet());  

//sorting the list elements  
Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
{  
public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
{   
//compare two object and return an integer  
return o1.getValue().compareTo(o2.getValue()); 
}  
});
Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();  
for (Entry<Integer, Integer> entry : list)   
{  
sortedMap.put(entry.getKey(), entry.getValue());  
}  
return sortedMap;  
}  
}