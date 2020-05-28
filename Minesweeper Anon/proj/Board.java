// The following is the work of HersheyCactus
// 2020
// AP Computer Science A

/**
* The Board class is used to initialize and create a minesweeper board.
* It randomly generates a board of spaces and mines, and then proceeds
* to calculate what the values of each hidden square should be.
*
* @author HersheyCactus
* @version 1.0
* @since 2020-27-5
*/

package proj;
import java.util.Random;

public class Board{

	/**
	* The length and width of the minesweeper board.
	*/
	private int x, y;

	/**
	* The board that contains the values of each square.
	* <p>
	* A 1 means that there is 1 mine in the 8 surrounding it, a 2 would
	* mean that there are 2 mines in the 8 surrounding.
	* A value of -1 would mean that the square itself is a mine.
	*/
	private int[][] userBoard; 

	/**
	* A board of booleans representing what is and isn't a mine.
	* <p>
	* Generated randomly with every Board object. Any given
	* square has a 20% chance of being a mine.
	*/
	private boolean[][] mineBoard;

	/**
	* A MineGUI object created using mineBoard and userBoard. What the user
	* eventually interacts with.
	*/
	private MineGUI g;


	/**
	* Creates a board that is x by y.
	* <p>
	* Creates two arrays, userBoard and mineBoard, from the provided x and y 
	* parameters. Also sets the local x and y variables to the x and y 
	* given parameters.
	*
	* Calls createMines() to set up mineBoard. Once finished, calls method
	* calculateUB() to set up and calculate userBoard.
	* Using params x and y, creates a new MineGUI object.
	* Using userBoard and mineBoard, finishes setting up the MineGUI object.
	*
	* @param x the length of the board
	* @param y the width of the board
	*/
	public Board(int x, int y){ //initialises board x by y
		this.x = x;
		this.y = y;

		userBoard = new int[x][y];
		mineBoard = new boolean[x][y];

		createMines();

		calculateUB();
		
		g = new MineGUI(x, y);
		g.setArrs(userBoard, mineBoard);
	}

	/**
	* Creates a square board. 
	* <p>
	* Takes in the length of one side of the board, and 
	* creates a square-shaped board using that.
	*
	* @param x the length of one side of the board
	*/
	public Board(int x){
		this(x, x);
	}

	/**
	* Creates a default sized board, 10 by 10. 
	*/
	public Board(){
		this(10);
	}


	/**
	* Creates the mineBoard
	* <p>
	* Creates an array of booleans used to represent mines. Each square has a
	* 20% chance of becoming a mine. 
	*/
	private void createMines(){ 
		Random rand = new Random();
		float num = 0;

		for(int i = 0; i < x; i++){

			for(int j = 0; j < y; j++){

				num = rand.nextFloat();

				if(num < 0.2){
					mineBoard[i][j] = true;
				} 
				else {
					mineBoard[i][j] = false;
				}

			}
		}

		return;
	}

	/**
	* Creates the userBoard
	* <p>
	* Calculates what values are stored in the userBoard.
	* No calculations are done in this method itself. Rather, the method calls 
	* helper method calcNum() which does the calculations for it.
	*/
	private void calculateUB(){ 
		int count;

		for(int i = 0; i < x; i++){

			for(int j = 0; j < y; j++){
				
				userBoard[i][j] = calcNum(i, j);
				
			}

		}
	}


	/**
	* Prints the mineBoard
	* <p>
	* Prints the formatted version of the array of booleans representing
	* the layout of the mines.
	* A 'true' represents a mine while a 'false' represents a space.
	* 
	* This method is only used in testing the initialization of the mineBoard.
	*/
	private void printMineBoard(){

		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				System.out.print(mineBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	* Prints the userBoard
	* <p>
	* Prints the formatted version of the array of integers representing
	* the layout of the board that the user would be interacting with.
	* 
	* Numbers 1-8 are used to represent how many mines border a square, while
	* an 'M' is used to represent a mine.
	*/
	private void printUserBoard(){ //prints board (for testing)

		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(userBoard[i][j] == -1){
					System.out.print("M ");
				} else{
					System.out.print(userBoard[i][j] + " ");
				}
				
			}
			System.out.println();
		}
	}

	/**
	* Calculates the number of bordering mines for the userBoard
	* <p>
	* Takes in the row and column number for an index of mineBoard. This index
	* is compared with the bordering indeces, with a local integer 'count'
	* keeping track of how many bordering mines have been found.
	*
	* Has a lot of try/catch statements to avoid IndexOutOfBoundsExceptions
	* when checking indeces on the border.
	*
	* @param i row number
	* @param j column number 
	* @return the number of bordering mines
	*/
	private int calcNum(int i, int j){
		int count = 0;
		//System.out.println("a");
		if(mineBoard[i][j] == true){ //check if mine
			count = -1;
		} else{
			try{
				if(mineBoard[i-1][j-1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
			try{
				if(mineBoard[i-1][j]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
			try{
				if(mineBoard[i-1][j+1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}

			//bottom
			try{
				if(mineBoard[i+1][j-1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
			try{
				if(mineBoard[i+1][j]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
			try{
				if(mineBoard[i+1][j+1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}

			//mid
			try{
				if(mineBoard[i][j+1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
			try{
				if(mineBoard[i][j-1]){
					count++;
				}
			}catch(IndexOutOfBoundsException e){
				//too far
			}
		}

		return count;

	}

}