

import java.util.*;

public class NumberGuesser {

    private int guesses = 10;
    private int warnings=4;
    private final int[] alreadyTold = new int[10];

    private int specialWarningForInvalidGuess = 1;
    int index = 0;
    int range = 100;



    public Scanner inputInt(){
        System.out.print("------------------\n Enter Your Guess:");
        return new Scanner(System.in);
    }

    public boolean isValid(Scanner inp){
        return inp.hasNextInt();
    }

    public int randomNo(int range){
        Random random = new Random();
        return random.nextInt(range);
    }

    public void setAlreadyTold(int guess) {
        // if the number is not in the array already add it
        this.alreadyTold[this.index] = guess;
        this.index++;
    }

    public Boolean isAlreadyTold(int guess) {
        // if the number is already in told or not
        for(int i = 0; i < 10; i++){
            if(guess == this.alreadyTold[i]){
                return true;
            }
        }
        return false;
    }

    public void printGuessed(){
        int i = 0;
        while(i<10){
            if(alreadyTold[i] != -1)
                System.out.print(alreadyTold[i] + " ");
            i++;
        }
    }

    public void setGuessedToNull(){
        int i = 0;
        while(i<10){
            alreadyTold[i] = -1;
            i++;
        }
    }

    //****** complete the game using the unfinished functions ******//
    public void play() {

        /* few things to do here
        1. pick secret number and set guessed accordingly
        2. tell user the range of number guessed and total guesses and warnings
        3. ask user for guess check if guess is invalid or repeated user loses a warning and if no warning is left user loses a guess
        5. in case of valid guess check if the guess is equal or not to the guessed number and show appropiate message
        4. continue the game unless user wins or have consumed all guesses
         */
        setGuessedToNull();
        int secretNum = randomNo(range);
        System.out.println("I am thinking of a number between 0-"+ range + ".");
        Scanner guessChecker;
        int guess;
        boolean boolcounter = true;
        do{
            System.out.print("Remaining guesses: "+this.guesses
                    +"\nRemaining warnings: "+this.warnings +
                    "\nGuessed Numbers: "); printGuessed();
            guessChecker = inputInt();
            if(isValid(guessChecker)){
                guess = guessChecker.nextInt();
                if(guess == secretNum) {
                    System.out.println("Hurray!!!! you have guessed correct number.");
                    boolcounter = false;
                }
                else if (this.guesses > 0) {
                    if(isAlreadyTold(guess)){
                        System.out.println("You have already guessed this number");
                        this.guesses--;
                    }else if(guess < secretNum){
                        System.out.println("your guess is smaller.");
                        setAlreadyTold(guess);
                        this.guesses--;
                    }else {
                        System.out.println("your guess is bigger.");
                        setAlreadyTold(guess);
                        this.guesses--;}

                }else {
                    System.out.println("Game Over!");
                    boolcounter = false;}
            }else {
                System.out.println("Invalid Input!!!");
                this.specialWarningForInvalidGuess--;}
            if (this.specialWarningForInvalidGuess < 0){
                System.out.println("You have used your all warning!!!"+"\nGame Over!");
                this.guesses--;
                break;
            }

        }while(boolcounter);
    }


    public static void main(String[] args) {
        NumberGuesser n = new NumberGuesser();
        n.play();
    }
}
