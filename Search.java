import java.io.*;
import java.util.*;

public class Search {

	public static void main(String[] args) {
	
		String filePath="";
		HashSet<String> li= new HashSet<String>();
		int i=0;
		if(args.length==0) {
			System.out.println("No Arguments Passed");
			return;
		}
		for (String s: args) {
			if(i==0) {
				filePath=s;i++;
			}
			else {
				li.add(s); // Store only unique search terms  using HashSet
			}
        }
		ParseCSV(filePath,li); // Function call to Parse CSV
	}
	
	//Read CSV line by line and compare search terms with the tile and body
	public static void ParseCSV(String filePath, HashSet<String> li) {
		String csvFile = filePath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) { 
                String[] str = line.split(cvsSplitBy);
                Iterator<String> itr=li.iterator();  
                while(itr.hasNext())
                {
                	String st= itr.next();
                    if((str[2].toUpperCase().contains(st.toUpperCase()))
                    		||(str[1].toUpperCase().contains(st.toUpperCase())) )
                    {
                    	System.out.println("id= " + str[0] + " , title= " + str[1]);
                    	break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        	System.out.println("File not found please check the first argument");
        } catch (IOException e) {
            //e.printStackTrace();
        	System.out.println("Error reading local csv file");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                	System.out.println("Unable to close the connection");
                }
            }
        }
	}

}
