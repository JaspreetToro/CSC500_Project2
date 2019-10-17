package Project2;

public class MCF_MIgration {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*Get user input --- I belive they are all int's but i may be wrong
		 *  	can you change if you think they are not.
		 * int k - number of ports each switch has, or number of PODs in this 
		 * 		fattree data center;
		 * int l -  number of VM pairs that are randomly placed onto the PMs 
		 * 		initially
		 * int m - number of middleboxes, of different type, in the data center
		 * int rc - the initial resource capacity of each PM
		 * int lambda -  the communication frequency of each VM pair is a
		 *		random number between [0, lambda]
		 * int migration - migration coefficient
		 * 
		*/
		
		//generate mcf_migration.inp file
		//call other class to begin generating the file. Pass over user input.
		int[] inputs = SDN.GetInput();

		MCFGenerator.GenerateFile(0, 0, 0, 0, 0, 0);
		
		//							   k, j, i
		int temp = SDN.ShorestDistance(0, 1, 2);	
	}
}
