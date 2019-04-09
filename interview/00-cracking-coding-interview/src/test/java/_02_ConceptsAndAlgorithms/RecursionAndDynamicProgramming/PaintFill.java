package _02_ConceptsAndAlgorithms.RecursionAndDynamicProgramming;

import org.junit.Test;

/*
  Q: implement the "paint fill" function
 */
public class PaintFill {

    @Test
    public void Test() {
        int N = 10;
        Color[][] screen = new Color[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                screen[i][j] = Color.Black;
            }
        }
        for(int i = 0; i < 100; i++){
            screen[randomInt(N)][randomInt(N)] = Color.Green;
        }
        printScreen(screen);
        paintFill(screen, 2, 2, Color.White);
        System.out.println("");
        printScreen(screen);
    }

    enum Color{ Black, White, Red, Yellow, Green }

    static String printColor(Color c){
        switch (c){
            case Black: return "B";
            case White: return "W";
            case Red: return "R";
            case Yellow: return "Y";
            case Green: return "G";
        }
        return "X";
    }
    static void printScreen(Color[][] screen){
        for(int i = 0; i < screen.length; i++){
            for(int j = 0; j < screen[0].length; j++){
                System.out.print(printColor(screen[i][j]));
            }
            System.out.println("");
        }
    }
    static int randomInt(int n){ return (int)(Math.random() * n); }
    static boolean paintFill(Color[][] screen, int x, int y, Color ocolor, Color ncolor){
        if(x < 0 || x >= screen[0].length || y < 0 || y >= screen.length) return false;
        if(screen[y][x] == ocolor){
            screen[y][x] = ncolor;
            paintFill(screen, x - 1, y, ocolor, ncolor); // left
            paintFill(screen, x + 1, y, ocolor, ncolor); // right
            paintFill(screen, x, y - 1, ocolor, ncolor); // top
            paintFill(screen, x, y + 1, ocolor, ncolor); // bottom
        }
        return true;
    }
    static boolean paintFill(Color[][] screen, int x, int y, Color ncolor){
        if(screen[y][x] == ncolor) return false;
        return paintFill(screen, x, y, screen[y][x], ncolor);
    }
}
/*
output:
GGGBGBGGGB
GGBGGBGGGG
BGBBGGBBGB
GBBGGBGGGG
GGGGGGGBGB
BBBBGGBGGB
BGGGBGBGBG
GGGGGGGBGB
GBGGGGGGGG
GBBGGGGBBB

GGGBGBGGGB
GGWGGBGGGG
BGWWGGBBGB
GWWGGBGGGG
GGGGGGGBGB
BBBBGGBGGB
BGGGBGBGBG
GGGGGGGBGB
GBGGGGGGGG
GBBGGGGBBB
 */
