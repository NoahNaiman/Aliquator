import java.util.Scanner;
public class AliquatorTester{
	public static void main(String[] args) {
		AliquatorAdder adder = new AliquatorAdder();
		Scanner input = new Scanner(System.in);

		System.out.println("Would you like to enter something? Y/N:\n");
		String yesNo = input.nextLine();
		do{
			adder.addTo();
			System.out.println("Would you like to enter something else? Y/N:\n");
			yesNo = input.nextLine();
		}while(!yesNo.equalsIgnoreCase("n"));
		System.out.println("Goodbye.");
	}
}