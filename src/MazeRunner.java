import java.util.Scanner;

public class MazeRunner {
    public static Maze myMap = new Maze();
    private static int moveCounter = 0;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        intro();
        while (!myMap.didIWin() && moveCounter < 100) {
            userMove();
        }

        myMap.printMap();
        movesMessage(moveCounter);

        input.close();
    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!" + '\n' + "Here is your Current Position");
    }

    public static String userMove() {

        boolean availbeMove = false;
        System.out.println("Where would you like to move? (Right, Left, Up, Down)");
        String direction;

        do {
            direction = input.nextLine();
            if (direction.matches("[UDLR]")) {
                switch (direction) {
                    case "U": {
                        availbeMove = myMap.canIMoveUp();
                        if (availbeMove)
                            myMap.moveUp();
                        break;
                    }
                    case "L": {
                        availbeMove = myMap.canIMoveLeft();
                        if (availbeMove)
                            myMap.moveLeft();

                        break;
                    }
                    case "R": {
                        availbeMove = myMap.canIMoveRight();
                        if (availbeMove)
                            myMap.moveRight();

                        break;
                    }
                    case "D": {
                        availbeMove = myMap.canIMoveDown();
                        if (availbeMove)
                            myMap.moveDown();

                        break;
                    }
                    default: {
                        availbeMove = false;
                    }
                }

                if (!availbeMove){
                    if (myMap.isThereAPit(direction))
                        navigatePit(direction);
                    else
                        System.out.println("Sorry, you’ve hit a wall - Pick new direction");
                }

                movesMessage(++moveCounter);
            } else
                System.out.println("Not a Direction ! Choose only U/D/L/R");


            myMap.printMap();
        } while (direction.length() > 1
                || !availbeMove
                || !myMap.didIWin());

        return direction;
    }

    public static void movesMessage(int moves) {
        switch (moves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                break;
            case 101:
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                System.exit(0);
                break;
            default:
                break;
        }

        if (myMap.didIWin())
            System.out.println("You Won! in " + moves + " Steps");
    }

    public static void navigatePit(String direction) {
        String answer;

        if (myMap.isThereAPit(direction)) {
            System.out.println("Watch out! There's a pit ahead, jump it?");
            answer = input.nextLine();

            if (answer.startsWith("y") || answer.startsWith("Y")) {
                myMap.jumpOverPit(direction);
            } else {
                System.out.println("Please Choose other direction");
            }

        } else
            System.out.println("You hit a Wall!");
    }
}