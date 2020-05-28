// The following is the work of HersheyCactus
// 2020
// AP Computer Science A

/**
* The MineGUI class is what the user eventually interacts with.
* It creates a window with buttons, representing the minesweeper board.
* The user plays the game by interacting with this window.
*
* @author HersheyCactus
* @version 1.0
* @since 2020-27-5
*/

package proj;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MineGUI extends JFrame{
    
    /**
    * Experimental layout
    * <p>
    * used to plan the size of buttons and the starting
    * window length and width. Not seen by user, but used as a template.
    *
    * Taken from the Java example program GridLayoutDemo. Functionality
    * is also the same as in that example program.
    */
    private GridLayout experimentLayout;

    /**
    * The x and y values of the board
    * <p>
    * Represent the length and width.
    * These are imported from when Board initializes MineGUI.
    */
    private static int xL, yL;

    /**
    * A boolean used to toggle the flagging system.
    */
    private static boolean flag;

    /**
    * Booleans used to trigger the end result of the game
    * <p>
    * Won determines whether the player has won or lost
    * End determines whether the game has come to an end
    * Both are initialized to false to begin.
    */
    private static boolean won, end;

    /**
    * The userBoard carried over from the Board object
    * <p>
    * The board that contains the values of each square.
    * A 1 means that there is 1 mine in the 8 surrounding it, a 2 would
    * mean that there are 2 mines in the 8 surrounding.
    * A value of -1 would mean that the square itself is a mine.
    */
    private static int[][] userBoard;

    /**
    * The mineBoard carried over from the Board object.
    * <p>
    * A board of booleans representing what is and isn't a mine.
    * Generated randomly with every Board object. Any given
    * square has a 20% chance of being a mine.
    */
    private static boolean[][] mineBoard;

    /**
    * The board of what is and isn't flagged
    * <p>
    * Initialized with everything false. Used to check if the player has
    * won or not and to keep track of what the player has and hasn't flagged.
    */
    private static boolean[][] flagBoard;

    /**
    * A 2 Dimensional array to keep track of the squares that have been played
    * <p>
    * Set up to have mines as true. Any time a player akes a move, the index
    * with corresponding x and y values in played[][] is set to true.
    */
    private static boolean[][] played;

    /**
    * The actual grid that the user interacts with
    * <p>
    * A 2 Dimensional array of JButtons. The user clicks on these buttons to 
    * progress in the game.
    * Each JButton in the array has properties stored inside of the object,
    * which are its x position, y position, and value stored.
    */
    private static JButton[][] buttons;


    /**
    * Constructs a MineGUI that is xL by yL
    * <p>
    * Constructs a GridLayout that is xL by yL, creating the GridLayout
    * template later used to organize the buttons.
    * Sets local variables xL and yL to the given parameters.
    *
    * @param xL number of rows
    * @param yL number of columns
    */
    public MineGUI(int xL, int yL) {
        this.xL = xL;
        this.yL = yL;
        experimentLayout = new GridLayout(xL,yL);
    }


    /**
    * Initializes the local userBoard and mineBoard
    * <p>
    * Takes in the userBoard and mineBoard from Board and sets the local
    * userBoard and mineBoard to their values
    *
    * Additionally sets up the played, flagBoard, and button arrays by
    * allocating the memory. xL and yL are used to determine the lengths
    * of each array.
    *
    * Won and end are both set to false.
    *
    * Played is copied (via a deep copy to avoid the same references) from
    * the mineBoard array
    *
    * Once finished, gets ready to show the GUI.
    *
    * @param uB userboard from board object
    * @param mB mineboard from board object
    */
    public static void setArrs(int[][] uB, boolean[][] mB){
        userBoard = uB;
        mineBoard = mB;
        played = new boolean[xL][yL];
        flagBoard = new boolean[xL][yL];
        buttons = new JButton[xL][yL];
        won = false; end = false;

        for(int i = 0; i < xL; i++){
            for(int j = 0; j < yL; j++){
                played[i][j] = mineBoard[i][j];
            }
        }
        createAndShowGUI();
    }

    // Private methods (Everything past this point) are not present in
    // the Javadoc due to 'private' status
    
    /**
    * Creates the window shown to the user
    * <p>
    * Creates the panel and window that the user sees with the correct
    * dimensions
    *
    * Additionally sets up the GridLayout of JButtons that the user
    * interacts with, and adds each JButton to a 2 Dimensional array
    * to be easily accessible.
    * Each JButton also is set up to hold clientproperties of its
    * X and Y positions, as well as the value it holds corresponding
    * to the userBoard.
    *
    * Adds the grid and other elements of the screen (title and flag
    * button) to the window and displays everything.
    *
    * Beginning code (before the first for loop) has been taken directly
    * from the Java example program GridLayoutDemo, included in the
    * zip.
    * 
    * Final lines of method, when adding components to pane to display,
    * are heavily modeled on the respective lines in the Java example
    * program GridLayoutDemo, included in the zip.
    *
    * @param pane the window, created using a container
    */
    private void addComponentsToPane(final Container pane){
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(xL,yL));

        JButton b = new JButton("Placeholder");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)((buttonSize.getWidth() * 2.5)+20 * xL),
                (int)(buttonSize.getHeight() * 3.5)+20 * yL));
        
        for (int i = 0; i < xL; i++) {
            for (int j = 0; j < yL; j++) {
                JButton added = new JButton("");

                added.putClientProperty("x", i);
                added.putClientProperty("y", j);
                added.putClientProperty("value", userBoard[i][j]);
                added.setBackground(new Color(200,200,200));

                added.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Prints coords
                        //(Integer)((JButton)e.getSource()).getClientProperty("x")
                        //(Integer)((JButton)e.getSource()).getClientProperty("y")
                        if(!end){
                            if(!(click((Integer)((JButton)e.getSource()).getClientProperty("x"), 
                                       (Integer)((JButton)e.getSource()).getClientProperty("y")))){
                                end = true;
                                for(int k = 0; k < xL; k++){
                                    for(int l = 0; l < yL; l++){
                                        buttons[k][l].setText("X");
                                        buttons[k][l].setBackground(new Color(200,200,200));
                                        buttons[k][l].setForeground(new Color(150,0,0));
                                    }
                                }
                            }
                        } else{
                            Board b = new Board(xL, yL);
                            end = false;
                            won = false;
                        }
                    }
                });
                compsToExperiment.add(added);
                buttons[i][j] = added;
            }
        }

        experimentLayout.setHgap(1);
        experimentLayout.setVgap(1);

        pane.add(new JSeparator(), BorderLayout.NORTH);

        Box topText =  Box.createHorizontalBox();

        JLabel title = new JLabel("Minesweeper Board: " +  xL + "x" + yL + "      ");
        title.setFont(title.getFont().deriveFont(16.0f));
        JLabel credits = new JLabel("Created by HersheyCactus using the Java Swing library");
        credits.setFont(credits.getFont().deriveFont(10.0f));

        topText.add(title);
        topText.add(credits);

        pane.add(topText, BorderLayout.NORTH);
        pane.add(new JSeparator());
        pane.add(compsToExperiment, BorderLayout.CENTER);
        pane.add(new JSeparator(), BorderLayout.SOUTH);

        JButton flagger = new JButton("Click to enable flagging");
        flagger.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = !flag;
                if(flag){
                    flagger.setText("Flagging enabled");
                } else{
                    flagger.setText("Flagging disabled");
                }
            }
        });
        pane.add(flagger, BorderLayout.SOUTH);
    }

    /**
    * Creates the GUI and shows it.
    * <p>
    * This method was taken directly from the Java example program
    * GridLayoutDemo, included in the zip.
    * 
    * Given documentation:
    * For thread safety, 
    * this method should be invoked from the 
    * event-dispatching thread.
    */
    private static void createAndShowGUI() {

        //Create and set up the content pane.
        MineGUI frame = new MineGUI(xL, yL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
    * Lets the user interact with the board
    * <p>
    * Registers when a user clicks on a button and reacts appropriately.
    * 
    * Checks if the player is flagging or checking a square and then
    * flags or checks that square, respectively. 
    * 
    * Checks that if the player is checking a square, it's not a mine. If
    * it is, ends the game while communicating the player has lost.
    * Checks whether the game is finished after the player checks a square.
    * If it is, ends the game while communicating the player has won.
    * 
    * @param x row number to check
    * @param y column number to check
    * @return whether the move was successful
    */
    private boolean click(int x, int y){

        if(mineBoard[x][y] && !flagBoard[x][y] && !flag){
            //game lost, mine clicked on
            return false;
        } 
        else{
            if(flag){
                flagBoard[x][y] = !flagBoard[x][y];
                if(!flagBoard[x][y]){
                    buttons[x][y].setText(" ");
                    buttons[x][y].setForeground(new Color(0,0,0));
                    buttons[x][y].setBackground(new Color(200,200,200));
                } else{
                    buttons[x][y].setText("F");
                    buttons[x][y].setForeground(new Color(255, 0, 0));
                    buttons[x][y].setBackground(new Color(200,200,200));
                }     
            } else{
                if(!flagBoard[x][y]){
                    buttons[x][y].setForeground(new Color(0,0,0));
                    buttons[x][y].setText((buttons[x][y].getClientProperty("value")).toString());
                    buttons[x][y].setBackground(new Color(150,150,150));
                    played[x][y] = true;
                    if(userBoard[x][y] == 0){
                        //This looks awful and is incredibly inefficient, but it works. Too bad!
                        try{
                            if(!played[x-1][y-1])
                                click(x-1, y-1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                        try{
                            if(!played[x-1][y])
                                click(x-1, y);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                        try{
                            if(!played[x-1][y+1])
                                click(x-1, y+1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }

                        //bottom
                        try{
                            if(!played[x+1][y-1])
                                click(x+1,y-1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                        try{
                            if(!played[x+1][y])
                                click(x+1, y);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                        try{
                            if(!played[x+1][y+1])
                                click(x+1, y+1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }

                        //mid
                        try{
                            if(!played[x][y+1])
                                click(x, y+1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                        try{
                            if(!played[x][y-1])
                                click(x,y-1);
                        }catch(IndexOutOfBoundsException e){
                            //too far
                        }
                    }
                }
            }
        }
        
        checkFinished();
        if(end && won){
            for(int k = 0; k < xL; k++){
                for(int l = 0; l < yL; l++){
                    buttons[k][l].setText("W");
                    buttons[k][l].setBackground(new Color(200,200,200));
                    buttons[k][l].setForeground(new Color(0,150,0));
                }
            }
        }

        return true;
    }

    /**
    * Checks whether the game has finished
    * <p>
    * Only used in the case of the player hypothetically winning, this
    * counts the number of squares that have been played or clicked on.
    * 
    * If the number of played or flagged squares is equal to the total
    * amount of squares, then the player is said to have won
    * and the respective variables are updated to show this.
    */
    private void checkFinished(){
        int count = 0;

        for(int i = 0; i < xL; i++){
            for(int j = 0; j < yL; j++){
                if(played[i][j] || flagBoard[i][j])
                    count++;
            }
        }
        if(count == (xL * yL)){
            won = true;
            end = true;
            return;
        }
        won = false;
        end = false;
        return;
    }
}