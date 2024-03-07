import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    public static void main(String[] args){
        System.out.println("TicTacToe!...^-^");
        bot();
    }

    public static void bot(){
        Scanner obj = new Scanner(System.in);
        String[] array = new String[11];
        int n=9;
        for(int i=0;i<n;i++){
            array[i] = "-";
        }
        array[10] = "n";
        int win = 0;
        System.out.println("Easy(E) or Hard(H) Mode : ");
        try{
            String eh = obj.nextLine();
            if(eh.equals("e") | eh.equals("E")){
                System.out.println("Easy Mode");
                while(win == 0){
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;
                    array = botInput1(array);
                    win = checkForWin(array);
                }
            }
            else if(eh.equals("h") | eh.equals("H")){
                System.out.println("Hard Mode");
                while(win == 0){
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;  // User Input 1
                    array = botInput0(array);
                    win = checkForWin(array);
                    if(win==1) break;  // Computer Input 1 - O is placed at random
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;  // User Input 2
                    array = botInput2(array);
                    win = checkForWin(array);
                    if(win==1) break;  // Computer Input 2 - Checks for whether X wins, or chooses the best position for O
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;  // User Input 3
                    array = botInput3(array);
                    win = checkForWin(array);
                    if(win==1) break;  // Computer Input 3 - Checks whether O can win in next move or Checks whether X wins
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;  // User Input 4
                    array = botInput3(array);
                    win = checkForWin(array);
                    if(win==1) break;  // Computer Input 4 - Checks whether O can win in next move or Checks whether X wins
                    array = userinput(array);
                    win = checkForWin(array);
                    if(win==1) break;  // User Input 5
                }

            }
            else{
                System.out.println("Enter \"E\" or \"H\" ");
                bot();
            }
        }
        catch(Exception e){
            System.out.println("Enter \"E\" or \"H\" ");
            bot();
        }
        tryagain();
         
    }

    public static void tryagain(){
        Scanner obj = new Scanner(System.in);
        System.out.print("Want to try again(Y/N) : ");
        try{
            String agn = obj.nextLine();
            if(agn.equals("Y") || agn.equals("y")){
                bot();
            }
            else if(agn.equals("N") || agn.equals("n")){
            }
            else{
                System.out.println("Enter \"Y\" or \"N\" ");
                tryagain();
            }
        }
        catch(Exception e){
            System.out.println("Enter \"Y\" or \"N\" ");
            tryagain();
        }
         
    }
    public static void printTheGame(String[] array){
        for(int i=0;i<9;i=i+3){
            System.out.println(" "+array[i]+"|"+array[i+1]+"|"+array[i+2]);
        }
    }
    public static String[] userinput(String[] array){
        try{
            Scanner obj = new Scanner(System.in);
            System.out.print("Player : ");
            int x = obj.nextInt();
            if(array[x-1].compareTo("-")!=0){
                System.out.println("Already Filled!!..Try again..");
                userinput(array);
            }
            else array[x-1] = "X";
            printTheGame(array);
             
        }
        catch(Exception e){
            System.out.println("Enter in digits..(1-9)");
            userinput(array);
        }
        return array;
    }
    public static String[] botInput0(String[] array){
        if(array[4].equals("X")){
            return botInput1(array);
        }
        else{
            array[4] = "O";
            System.out.println("Computer : 5");
            printTheGame(array);
            return array;
        }
    }

    public static String[] botInput1(String[] array){
        Random robj = new Random();
        int y = robj.nextInt(1,9);
        if(array[y-1].compareTo("-")!=0){
            botInput1(array);
        }
        else array[y-1] = "O";
        System.out.println("Computer : "+y);
        printTheGame(array);
        return array;
    }
    public static String[] a1(String[] array, int a, int b, int c){
        if(array[a].equals("X") & array[b].equals("X") & array[c].equals("-")){
            System.out.println("Computer : "+ (c+1)); array[c]="O"; printTheGame(array); array[10]="y"; return array;}
        else return array;
    }

    public static String[] a2(String[] array, int a, int b, int c){
        if(array[a].equals("O") & array[b].equals("-") & array[c].equals("-")){
            System.out.println("Computer : "+ (c+1)); array[c]="O"; printTheGame(array); array[10]="y"; return array;}
        else return array;
    }

    public static String[] a3(String[] array, int a, int b, int c){
        if(array[a].equals("O") & array[b].equals("O") & array[c].equals("-")){
            System.out.println("Computer : "+ (c+1)); array[c]="O"; printTheGame(array); array[10]="y"; return array;}
        else return array;
    }

    public static boolean ss(String[] array){
        if(array[10].equals("y")) {array[10] = "n"; return true;}
        else return false;
    }

    public static String[] botInput2(String[] array){
        // checks whether X is going to win and block
        for(int i=0;i<9;i=i+3){
            a1(array, i, i+1, i+2); if(ss(array)) return array;
            a1(array, i, i+2, i+1); if(ss(array)) return array;
            a1(array, i+2, i+1, i); if(ss(array)) return array;
        }
        for(int i=0;i<3;i++){
            a1(array, i, i+3, i+6); if(ss(array)) return array;
            a1(array, i, i+6, i+3); if(ss(array)) return array;
            a1(array, i+6, i+3, i); if(ss(array)) return array;
        }
        a1(array, 0, 4, 8); if(ss(array)) return array;
        a1(array, 0, 8, 4); if(ss(array)) return array;
        a1(array, 8, 4, 0); if(ss(array)) return array;
        
        a1(array, 2, 4, 6); if(ss(array)) return array;
        a1(array, 2, 6, 4); if(ss(array)) return array;
        a1(array, 6, 4, 2); if(ss(array)) return array;

        // Finding suitable place for O to place
        for(int i=0;i<9;i=i+3){
            a2(array, i, i+1, i+2); if(ss(array)) return array;
            a2(array, i, i+2, i+1); if(ss(array)) return array;
            a2(array, i+2, i+1, i); if(ss(array)) return array;
        }
        for(int i=0;i<3;i++){
            a2(array, i, i+3, i+6); if(ss(array)) return array;
            a2(array, i, i+6, i+3); if(ss(array)) return array;
            a2(array, i+6, i+3, i); if(ss(array)) return array;
        }
        a2(array, 0, 4, 8); if(ss(array)) return array;
        a2(array, 0, 8, 4); if(ss(array)) return array;
        a2(array, 8, 4, 0); if(ss(array)) return array;
        
        a2(array, 2, 4, 6); if(ss(array)) return array;
        a2(array, 2, 6, 4); if(ss(array)) return array;
        a2(array, 6, 4, 2); if(ss(array)) return array;
    
        botInput1(array);
        return array;
    }
    public static String[] botInput3(String[] array){
        // Choose Shortcut to Win
        for(int i=0;i<9;i=i+3){
            a3(array, i, i+1, i+2); if(ss(array)) return array;
            a3(array, i, i+2, i+1); if(ss(array)) return array;
            a3(array, i+2, i+1, i); if(ss(array)) return array;
        }
        for(int i=0;i<3;i++){
            a3(array, i, i+3, i+6); if(ss(array)) return array;
            a3(array, i, i+6, i+3); if(ss(array)) return array;
            a3(array, i+6, i+3, i); if(ss(array)) return array;
        }
        a3(array, 0, 4, 8); if(ss(array)) return array;
        a3(array, 0, 8, 4); if(ss(array)) return array;
        a3(array, 8, 4, 0); if(ss(array)) return array;
        
        a3(array, 2, 4, 6); if(ss(array)) return array;
        a3(array, 2, 6, 4); if(ss(array)) return array;
        a3(array, 6, 4, 2); if(ss(array)) return array;

        // Checks whether X is going to win and block
        for(int i=0;i<9;i=i+3){
            a1(array, i, i+1, i+2); if(ss(array)) return array;
            a1(array, i, i+2, i+1); if(ss(array)) return array;
            a1(array, i+2, i+1, i); if(ss(array)) return array;
        }
        for(int i=0;i<3;i++){
            a1(array, i, i+3, i+6); if(ss(array)) return array;
            a1(array, i, i+6, i+3); if(ss(array)) return array;
            a1(array, i+6, i+3, i); if(ss(array)) return array;
        }
        a1(array, 0, 4, 8); if(ss(array)) return array;
        a1(array, 0, 8, 4); if(ss(array)) return array;
        a1(array, 8, 4, 0); if(ss(array)) return array;
        
        a1(array, 2, 4, 6); if(ss(array)) return array;
        a1(array, 2, 6, 4); if(ss(array)) return array;
        a1(array, 6, 4, 2); if(ss(array)) return array;

        botInput2(array);
        return array;
    }
    
    public static Integer checkForWin(String[] array){
        // Checks whether User or Computer won the game
        for(int i=0;i<9;i=i+3){
            if(array[i].equals("X") & array[i+1].equals("X") & array[i+2].equals("X")){
                printPlayerWin(); return 1;}
            if(array[i].equals("O") & array[i+1].equals("O") & array[i+2].equals("O")){
                printComputerWin(); return 1;}
        }
        for(int i=0;i<3;i++){
            if(array[i].equals("X") & array[i+3].equals("X") & array[i+6].equals("X")){
                printPlayerWin(); return 1;}
            if(array[i].equals("O") & array[i+3].equals("O") & array[i+6].equals("O")){
                printComputerWin(); return 1;}
        }
        if(array[0].equals("X") & array[4].equals("X") & array[8].equals("X")){
            printPlayerWin(); return 1;}
        if(array[0].equals("O") & array[4].equals("O") & array[8].equals("O")){
            printComputerWin(); return 1;}
        if(array[2].equals("X") & array[4].equals("X") & array[6].equals("X")){
            printPlayerWin(); return 1;}
        if(array[2].equals("O") & array[4].equals("O") & array[6].equals("O")){
            printComputerWin(); return 1;}
        int z = 0;
        for(int i=0;i<9;i++){
            if(array[i].compareTo("-")!=0){
                z++;
                if(z==9){
                    System.out.println("DRAW Match...");
                    return 1;
                }
            }
        }
        return 0;
    }
    public static void printPlayerWin(){
        System.out.println("Player...WON...^-^."); 
    }
    public static void printComputerWin(){
        System.out.println("Computer...WON...-_-."); 
    }
}
