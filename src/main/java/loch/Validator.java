package loch;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Validator {

    public static class FileCorruptedException extends Exception {}
    public static class BoardTooLargeException extends Exception {}

    static class point
    {
        int x;
        int y;
        int z;

        public point(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public point(){
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }

    static void check (int[][][] A, Queue<point> Q, int x, int y, int z, point top)
    {
        point L = new point(x, y, z);
        if (A[z][x][y]==0)
        {
            Q.add(L);
            A[z][x][y]=A[top.z][top.x][top.y]+1;
        }
        if (A[z][x][y]==-3)
            A[z][x][y]=A[top.z][top.x][top.y]+1;
        if (A[z][x][y]==-2)
        {
            point LL = new point(x, y, z);
            LL.z=1-L.z;
            Q.add(LL);
            Q.add(L);
            A[z][x][y]=A[top.z][top.x][top.y]+1;
            A[1-z][x][y]=A[top.z][top.x][top.y]+1;
        }
    }

    static void checker(int[][][] A, Queue<point> Q, int x, int y, int z, point top)
    {
        check(A, Q, x-1, y, z, top);
        check(A, Q, x+1, y, z, top);
        check(A, Q, x, y-1, z, top);
        check(A, Q, x, y+1, z, top);
    }

    static int BFS(int[][][] A, point a, point b)
    {
        Queue<point> Q = new LinkedList<>();
        Q.add(a);
        point top;
        while(!Q.isEmpty())
        {
            top=Q.remove();
            checker(A, Q, top.x, top.y, top.z, top);
            if(A[b.z][b.x][b.y]!=-3)
                return A[b.z][b.x][b.y];
        }
        return -1;
    }

    public static int validate(File file) throws IOException, FileCorruptedException, BoardTooLargeException {
        point start = new point();
        point end = new point();
        boolean heroFound = false, exitFound = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = reader.readLine();
        int h = 0, w = 0;
        try {
            h = Integer.parseInt(line.split(" ")[0]);
            w = Integer.parseInt(line.split(" ")[1]);
        } catch (NumberFormatException e){
            throw new FileCorruptedException();
        }
        if (h > 25 || w > 50)
            throw new BoardTooLargeException();
        int[][][] A = new int[2][h + 2][w + 2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < h + 2; j++) {
                for (int k = 0; k < w + 2; k++) {
                    A[i][j][k] = -1;
                }
            }
        }
        char s;
        for (int i=1; i<=h; i++) {
            line = reader.readLine();
            for (int j=1; j<=w; j++) {
                s = line.charAt(j-1);
                if (s=='.') {
                    A[0][i][j]=0;
                    A[1][i][j]=0;
                }
                else if (s=='+')
                    A[0][i][j]=0;
                else if (s=='o')
                    A[1][i][j]=0;
                else if (s=='@') {
                    if (heroFound)
                        throw new FileCorruptedException();
                    else
                        heroFound = true;
                    A[0][i][j]=0;
                    A[1][i][j]=0;
                    start.x=i;
                    start.y=j;
                    start.z=0;
                }
                else if (s=='>')
                {
                    if (exitFound)
                        throw new FileCorruptedException();
                    else
                        exitFound = true;
                    A[0][i][j]=-3;
                    A[1][i][j]=0;
                    end.x=i;
                    end.y=j;
                    end.z=0;
                }
                else if (s=='%')
                {
                    A[0][i][j]=-2;
                    A[1][i][j]=-2;
                }
                else if (s != '#')
                    throw new FileCorruptedException();
            }
        }
        if (!exitFound || !heroFound)
            throw new FileCorruptedException();
        return BFS(A, start, end);
    }

}
