package Project2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MinCost {
	/*
	Get user input --- I belive they are all int's but i may be wrong
	   	can you change if you think they are not.
	int k - number of ports each switch has, or number of PODs in this 
	  		fattree data center;
	int l -  number of VM pairs that are randomly placed onto the PMs 
	  		initially
	int m - number of middleboxes, of different type, in the data center
	int rc - the initial resource capacity of each PM
	int lambda -  the communication frequency of each VM pair is a
	 		random number between [0, lambda]
	int migration - migration coefficient
	 */ 

	public static void FindBestPMLocation(int k, int vmPairs,int boxes, int resCap,int fequency,int migration){

		List<Integer> list = VMPairsLocation(vmPairs,k,resCap);



	}

	private static List<Integer> VMPairsLocation(int vmPairs, int k,int resCap){

		int numPms = (int)Math.pow(k, 3)/4;
		List<Integer> list = new ArrayList<Integer>();

		Random r = new Random();

		for(int i = 0;i < vmPairs * 2;i++) {

			int result = r.nextInt(numPms-0) + 0;

			if( Collections.frequency(list, result) > resCap ) {
				result = r.nextInt(numPms-0) + 0;
			}

			list.add(result);
		}

		return list;

	}
}
