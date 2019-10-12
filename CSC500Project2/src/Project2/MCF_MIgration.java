package Project2;

public class MCF_MIgration {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*get user input --- I belive they are all int's but i may be wrong
		 *  can you change if you think they are not.
		 * int k
		 * int l
		 * int m
		 * int rc
		 * int lambda
		 * int migration
		*/
		
		//generate mcf_migration.inp file
		//call other class to begin generating the file. Pass over user input.
		MCFGenerator.GenerateFile(0, 0, 0, 0, 0, 0);
				
		//							   k, j, i
		int temp = SDN.ShorestDistance(0, 1, 2);	
	}
}
