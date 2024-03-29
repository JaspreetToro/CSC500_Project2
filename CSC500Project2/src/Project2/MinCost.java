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
	int k - number of ports each switch has, or number of PODs in this -----------
	  		fattree data center;
	int l -  number of VM pairs that are randomly placed onto the PMs 
	int l -  number of VM pairs that are randomly placed onto the PMs --------------
	  		initially
	int m - number of middleboxes, of different type, in the data center
	int rc - the initial resource capacity of each PM
	int lambda -  the communication frequency of each VM pair is a
	int m - number of middleboxes, of different type, in the data center--------------
	int rc - the initial resource capacity of each PM----------
	int lambda -  the communication frequency of each VM pair is a------------
	 		random number between [0, lambda]
	int migration - migration coefficient
	int migration - migration coefficient---------
	 */ 
	public static int k;
	public static int numPms;									
	public static int numEdges;	
	public static int numAggs;  
	public static int edgeStart;									
	public static int edgeEnd;							
	public static int aggStart;	

	//									4,2,2,4,1,1
	public static int SetUpMinCost(int numPods, int vmPairs,int boxes, int resCap,int frequency,int migration) {

		k = numPods;
		numPms = (int)(Math.pow(k, 3)/4);	//		2 			16										
		numEdges = (int)(Math.pow(k, 2)/2);	// 		2			8
		numAggs = (int)(Math.pow(k, 2)/2);  //		2			8
		edgeStart = numPms ;												// 2			16
		edgeEnd = numPms + numEdges -1;									// 3			23
		aggStart = edgeEnd +1;	
		int minCost = -1;
		List<Integer> vmPairLocation = VMPairsLocation(vmPairs,resCap);
		List<Integer> MBsLocation = MBsLocation(boxes);
		List<Integer> frequencies = GetFrequencies(vmPairs, frequency);

		List<Integer> newVmPairLocation = GetValuesOfList(vmPairLocation);
		newVmPairLocation= FindBestVMLocation(newVmPairLocation,MBsLocation,resCap);
		minCost = GetMinCost(vmPairs, boxes, frequencies, migration);

		return minCost;
	}
	private static List<Integer> GetValuesOfList(List<Integer> list) {
		List<Integer> newList = new ArrayList<Integer>();

		for(int i = 0; i < list.size(); i++) {
			newList.add(list.get(i).intValue());
		}
		return newList;
	}

	private static int GetMinCost( int vmPairs, int boxes, List<Integer> frequencies, int migration) {

		//							   k, j, i
		//	int temp = SDN.ShorestDistance(0, 1, 2);

		return 0;
	}


	private static List<Integer> FindBestVMLocation( List<Integer> vmPairs,List<Integer> boxes, int resCap){

		int mB1 = boxes.get(0);
		int mBn = boxes.get(boxes.size()-1);

		for(int i = 0; i < vmPairs.size(); i++) {

			if(i % 2 == 0){//if first vm of pair

				if(SDN.typeOf(k, mB1) == "Aggregation Switch") {//determine pod formula
					vmPairs.set(i, VmMinCostIndex(mB1,aggStart,vmPairs,resCap));
				}
				else {//mBn will be an edge switch
					vmPairs.set(i, VmMinCostIndex(mB1,edgeStart,vmPairs,resCap));
				}
			}
			else{//if second vm of pair
				if(SDN.typeOf(k, mBn) == "Aggregation Switch") {//determine pod formula
					vmPairs.set(i, VmMinCostIndex(mBn,aggStart,vmPairs,resCap));
				}
				else {//mBn will be an edge switch
					vmPairs.set(i, VmMinCostIndex(mBn,edgeStart,vmPairs,resCap));
				}
			}
		}	
		return vmPairs;
	}

	private static int VmMinCostIndex(int mB, int switchStart,List<Integer> list,int resCap) {
		int pod;
		int set;
		int pVm;//First index of possible VM
		int PPP = numPms/k;//Pms Per Pod
		int PPE = k/2;//Pms Per Edge 
		int leastIndex = -1;
		int testIndex = 0;

		pod = (int)Math.floor((mB - switchStart)/(k/2)); 
		set = (int)((mB - switchStart)%(k/2));
		pVm = (pod * PPP)+(set * PPE);

		int temp = Integer.MAX_VALUE;
		for(int i = 0; i < PPE; i++) {//search all pms possible determine which has the most amount of space
			testIndex = pVm + i;

			if(temp > Collections.frequency(list, testIndex)) {
				temp = Collections.frequency(list, testIndex);
				leastIndex = i;
			}

		}
		int bestIndex =  pVm + leastIndex;
		return bestIndex;
	}

	private static List<Integer> GetFrequencies(int vmPairs, int frequency) {

		List<Integer> list = new ArrayList<Integer>();

		Random r = new Random();

		for(int i = 0;i < vmPairs;i++) {

			int result = r.nextInt(frequency-0) + 0;
			list.add(result);
		}
		return list;
	}

	private static List<Integer> MBsLocation( int boxes) {
		//number of each nodes   				//if 	k = 2		k=4
		int numPms = (int)(Math.pow(k, 3)/4);	//		2 			16										
		int numEdges = (int)(Math.pow(k, 2)/2);	// 		2			8
		int numAggs = (int)(Math.pow(k, 2)/2);  //		2			8

		//start and end points of each type of node   						//if k = 2		k=4
		int edgeStart = numPms;												// 2			16
		int edgeEnd = numPms + numEdges -1;									// 3			23
		int aggStart = edgeEnd +1;											// 4			24
		int aggEnd = aggStart + numAggs -1;									// 5			31


		List<Integer> list = new ArrayList<Integer>();

		Random r = new Random();

		for(int i = 0;i < boxes;i++) {

			int result = r.nextInt(aggEnd-edgeStart) + edgeStart;
			list.add(result);
		}

		return list;
	}

	private static List<Integer> VMPairsLocation(int vmPairs,int resCap){

		int numPms = (int)Math.pow(k, 3)/4;
		List<Integer> list = new ArrayList<Integer>();

		Random r = new Random();
		
		for(int i = 0;i < vmPairs * 2;i++) {

				int result = r.nextInt(numPms-0) + 0;
				list.add(result);

				if( Collections.frequency(list, result) > resCap ) {
					result = r.nextInt(numPms-0) + 0;
					list.set(i, result);
				}
			}

			return list;
	}
}