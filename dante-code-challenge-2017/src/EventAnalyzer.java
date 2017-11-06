import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class EventAnalyzer {

	// Implement solution here
	public static String mostInterestedNeighborhood(ArrayList<Event> events) {
		HashMap<String,Visitors> neighs = new HashMap<String,Visitors>();

		int place = 0; //The number of different eventTypes encountered
		for (Event e : events) { //For loop through the events
			if (e.getNeighborhood() != null && (e.getEventType().equals("enter")
					|| e.getEventType().equals("exit"))) { //If there's a neighborhood
				String thisNeigh = e.getNeighborhood(); //Get Neigh
				if (!neighs.containsKey(thisNeigh)) { //Doesn't have neigh already
					Visitors hello = new Visitors();
					neighs.put(thisNeigh, hello);
				}//End if for doesn't have neigh
				neighs.get(thisNeigh).add(e.getUserID(),e.getTimeStamp(),e.getEventType());
			}//End if for if there's a neigh
		}//End for loop through the events

		//Now we have a hash of all neighs sent to a hash of unique users
		int max = 0;
		String s = "";
		for (String ss : neighs.keySet()) {
			if (neighs.get(ss).add("hi",-1,"hi") > max) {
				max = neighs.get(ss).add("hi",-1,"hi");
				s = ss;
			}
		}

		return s;

	}//End mostInterestedNeighborhood method

	//Implement solution here
	public static String leastInterestedNeighborhood(ArrayList<Event> events) {
		HashMap<String,Visitors> neighs = new HashMap<String,Visitors>();

		int place = 0; //The number of different eventTypes encountered
		for (Event e : events) { //For loop through the events
			if (e.getNeighborhood() != null && (e.getEventType().equals("enter")
					|| e.getEventType().equals("exit"))) { //If there's a neighborhood
				String thisNeigh = e.getNeighborhood(); //Get Neigh
				if (!neighs.containsKey(thisNeigh)) { //Doesn't have neigh already
					Visitors hello = new Visitors();
					neighs.put(thisNeigh, hello);
				}//End if for doesn't have neigh
				neighs.get(thisNeigh).add(e.getUserID(),e.getTimeStamp(),e.getEventType());
			}//End if for if there's a neigh
		}//End for loop through the events

		//Now we have a hash of all neighs sent to a hash of unique users
		int min = 100000;
		String s = "";
		for (String ss : neighs.keySet()) {
			if (neighs.get(ss).add("hi",-1,"hi") < min) {
				min = neighs.get(ss).add("hi",-1,"hi");
				s = ss;
			}
		}

		return s;
	}

	//Implement solution here
	public static String [] topIndividualsInterestedInLocations(ArrayList<Event> events) {
		Map<String, Person> people = new HashMap<String, Person>();
		SortedSet<String> hoods = new TreeSet<String>(); // all neighborhoods

		List<String> returnVal = new ArrayList<String>();

		for (Event event: events) {
			String userID = event.getUserID();
			if (event.getNeighborhood() != null) {
				hoods.add(event.getNeighborhood());
				if (people.containsKey(userID)) {
					people.get(userID).processEvent(event);
				} else {
					Person p = new Person(userID);
					people.put(userID, p);
					p.processEvent(event);
				}
			}
		}

		Iterator it = people.entrySet().iterator();

		Map<String, Integer> tops = new HashMap<String, Integer>();

		while (it.hasNext()) {

			Map.Entry<String, Person> pair = (Map.Entry<String, Person>) it.next();

			String userID = pair.getKey();
			Person p = pair.getValue();

			// Problem 3
			tops.put(userID, p.getTotalCount());

			it.remove();
		}

		return returnVal.toArray(new String[returnVal.size()]);
	}

	//Implement solution here
	public static String [] wheresWaldo (ArrayList<Event> events) {
		Map<String, Person> people = new HashMap<String, Person>();
		SortedSet<String> hoods = new TreeSet<String>(); // all neighborhoods

		for (Event event: events) {
			String userID = event.getUserID();
			if (event.getNeighborhood() != null) {
				hoods.add(event.getNeighborhood());
				if (people.containsKey(userID)) {
					people.get(userID).processEvent(event);
				} else {
					Person p = new Person(userID);
					people.put(userID, p);
					p.processEvent(event);
				}
			}
		}

		Iterator it = people.entrySet().iterator();

		Map<String, Integer> tops = new HashMap<String, Integer>();

		while (it.hasNext()) {

			Map.Entry<String, Person> pair = (Map.Entry<String, Person>) it.next();

			String userID = pair.getKey();
			Person p = pair.getValue();

			// Problem 3
			tops.put(userID, p.getTotalCount());

			it.remove();
		}

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(tops.entrySet());
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		});

		String[] returnVal = new String[] {
				list.get(3).getKey(),
				list.get(2).getKey(),
				list.get(1).getKey(),
				list.get(0).getKey()
		};

		//System.out.println("LOL" + Arrays.toString(returnVal));

		return returnVal;

	}
}
