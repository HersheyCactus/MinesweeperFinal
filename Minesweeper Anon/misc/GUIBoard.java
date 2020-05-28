// The following is the work of HersheyCactus
// 2020
// AP Computer Science A

// This is the object that will be used as reference (a stripped PlayingBoard of sorts)
// for MineGUI's logic.

// This object is not used in the final project, and as such, complete documentation
// has not been created.

import java.util.Scanner;

public class GUIBoard{
	private int x, y;
	private int[][] userBoard; //what the user sees (values calc'd)
	private boolean[][] mineBoard; //true = mine present, false = no mine;
	private boolean[][] flagBoard; //true = something has been flagged, false = no flag;
	private boolean[][] played;
	private boolean won;

	public GUIBoard (int[][] userBoard, boolean[][] mineBoard, int x, int y){ //initialises board x by y
		Scanner input = new Scanner(System.in);
		
		this.userBoard = userBoard;
		this.mineBoard = mineBoard;
		this.x = x;
		this.y = y;

		played = new boolean[x][y];
		flagBoard = new boolean[x][y];

		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				played[i][j] = mineBoard[i][j];
			}
		}

		printGUIBoard();

		int xCheck, yCheck;
		String checkFlag;
		//while(true){
			// What should be here:
			// Button for checking vs flagging
			// Checking if the game is finished
			// Getting the x and y, using that to change arrs
		//}

		if(won){
			System.out.println("Congratulations! You've won.");
			printUserBoard();
			System.exit(0);
		} else{
			System.out.println("You've lost, sorry.\nHere is the board.");
			printUserBoard();
			System.exit(0);
		}
	}

	private void printGUIBoard(){ //prints hidden board
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(played[i][j]){
					if(flagBoard[i][j]){
						System.out.print("[F]");
					}
					else if(userBoard[i][j] == -1){
						System.out.print("[ ]");
					} else {
						System.out.print("[" + userBoard[i][j] + "]");
					}
				} else {
					if(!flagBoard[i][j])
						System.out.print("[ ]");
					else
						System.out.print("[F]");
				}
			}
			System.out.println();
		}
		return;
	}

	private boolean checkFinished(){
		int count = 0;
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(played[i][j] || flagBoard[i][j])
					count++;
			}
		}
		if(count == (x * y)){
			return true;
		}
		return false;
	}

	private void printUserBoard(){ //prints board (for testing)

		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if(userBoard[i][j] == -1){
					System.out.print("[M]");
				} else{
					System.out.print("[" + userBoard[i][j] + "]");
				}
				
			}
			System.out.println();
		}
	}

	private boolean move(int x, int y){
		if(mineBoard[x][y]){
			//game lost, mine clicked on
			return false;
		} else{
			played[x][y] = true;
			if(userBoard[x][y] == 0){
				//This looks awful and is incredibly inefficient, but it works. Too bad!
				try{
					if(!played[x-1][y-1])
						move(x-1, y-1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
				try{
					if(!played[x-1][y])
						move(x-1, y);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
				try{
					if(!played[x-1][y+1])
						move(x-1, y+1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}

				//bottom
				try{
					if(!played[x+1][y-1])
						move(x+1,y-1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
				try{
					if(!played[x+1][y])
						move(x+1, y);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
				try{
					if(!played[x+1][y+1])
						move(x+1, y+1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}

				//mid
				try{
					if(!played[x][y+1])
						move(x, y+1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
				try{
					if(!played[x][y+1])
						move(x,y-1);
				}catch(IndexOutOfBoundsException e){
					//too far
				}
			}
		}
		return true;
	}

	private boolean flag(int x, int y){
		flagBoard[x][y] = !flagBoard[x][y];
		played[x][y] = !played[x][y];
		return true;
	}
}