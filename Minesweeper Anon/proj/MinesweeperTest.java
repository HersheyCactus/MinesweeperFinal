// The following is the work of HersheyCactus
// 2020
// AP Computer Science A

/**
* The MinesweeperTest class is used to run the rest of the project. It
* is only used once, upon launch, and it simply takes in the amount
* of rows and columns the user wants and creates an object of type
* Board.
*
* @author HersheyCactus
* @version 1.0
* @since 2020-27-5
*/

package proj;
import java.util.Scanner;


public class MinesweeperTest{

	/**
	* This is the main method. It constructs a Board object, which then
	* leads to the rest of the program being run.
	*
	* @param args unused
	*/

	public static void main(String[] args){
		int y, x;
		Scanner input = new Scanner(System.in);
		System.out.println("What would you like the dimensions of the board to be?");
		while(true){
			System.out.print("x: ");
			try{
				x = input.nextInt();
				if(x <= 0){
					System.out.println("Please enter a value >= 1");
				} else{
					break;
				}
			} catch(Exception e){
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
		}
		while(true){
			System.out.print("y: ");
			try{
				y = input.nextInt();
				if(y <= 0){
					System.out.println("Please enter a value >= 1");
				} else{
					break;
				}
			} catch(Exception e){
				System.out.println("Please enter an integer.");
				input.nextLine();
			}
		}	

		//Creates GUI
		Board xy = new Board(y, x);
	}
}
