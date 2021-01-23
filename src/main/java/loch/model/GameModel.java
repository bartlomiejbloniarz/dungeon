package loch.model;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    char[][] board;
    int height, width;
    int heroPosX, heroPosY;
    boolean astral, passed;
    File file;
    List<Pair> doors, astralDoors;
    Pair exit;

    public GameModel(File file){
        try {
            this.file = file;
            this.passed = false;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = reader.readLine();
            this.height = Integer.parseInt(line.split(" ")[0]);
            this.width = Integer.parseInt(line.split(" ")[1]);
            this.board = new char[height][width];
            this.astral = false;
            this.doors = new ArrayList<>();
            this.astralDoors = new ArrayList<>();
            for (int i=0; i<height; i++){
                line = reader.readLine();
                for (int j=0; j<width; j++) {
                    board[i][j] = line.charAt(j);
                    if (board[i][j] == '@'){
                        this.heroPosX = i;
                        this.heroPosY = j;
                        board[i][j] = '.';
                    }
                    else if (board[i][j] == '>')
                        exit = new Pair(i, j);
                    else if (board[i][j] == 'o')
                        astralDoors.add(new Pair(i, j));
                    else if (board[i][j] == '+')
                        doors.add(new Pair(i, j));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeroPosX() {
        return heroPosX;
    }

    public int getHeroPosY() {
        return heroPosY;
    }

    public void setHeroPosX(int heroPosX) {
        this.heroPosX = heroPosX;
    }

    public void setHeroPosY(int heroPosY) {
        this.heroPosY = heroPosY;
    }

    public void setAstral(boolean astral) {
        this.astral = astral;
    }

    public boolean isAstral() {
        return astral;
    }

    public File getFile(){
        return file;
    }

    public List<Pair> getDoors(){
        return doors;
    }

    public List<Pair> getAstralDoors() {
        return astralDoors;
    }

    public Pair getExit() {
        return exit;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public static class Pair{
        public int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
