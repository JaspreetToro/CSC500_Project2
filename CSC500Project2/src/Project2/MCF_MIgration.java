package Project2;

public class MCF_MIgration {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//generate mcf_migration.inp file
		//call other class to begin generating the file. Pass over user input.
		int[] inputs = SDN.GetInput();

		MCFGenerator.GenerateFile(0);
		//get user input
		//int[] inputs = SDN.GetInput();
		
		//							   k, j, i
		int temp = SDN.ShorestDistance(0, 1, 2);	
		//Set up for testing
		int minCost = MinCost.SetUpMinCost(4,2,2,4,1,1);

		//generate mcf_migration.inp file
		MCFGenerator.GenerateFile(minCost);
	}
}
