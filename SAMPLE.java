
public class SAMPLE{
    int ROW = 13;
    int COL = 20;
    char[][] type = new char[ROW][COL];
    boolean[][] visited = new boolean[ROW][COL];
    int TARGET = 57;
    int ORIGINALX = 4;
    int ORIGINALY=10;
    char[] DIRECTION = new char[4];
    boolean SOLVEDSOLVED=false;
    public SAMPLE(){
        DIRECTION[0]='L';
        DIRECTION[1]='U';
        DIRECTION[2]='R';
        DIRECTION[3]='D';
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                type[i][j] = 'N';
            }
        }
        String black="0 9 0 10 0 16 1 3 3 8 3 13 4 5 4 11 4 19 5 3 6 7 7 10 "+
            "7 13 8 7 8 17 9 2 9 6 9 13 9 16 10 5 10 17 11 14";
        type[0][9]='B';
        type[0][10]='B';
        type[0][16]='B';
        type[1][3]='B';
        type[3][8]='B';
        type[3][13]='B';
        type[4][5]='B';
        type[4][11]='B';
        type[4][19]='B';
        type[5][3]='B';
        type[6][7]='B';
        type[7][10]='B';
        type[7][13]='B';
        type[8][7]='B';
        type[8][17]='B';
        type[9][2]='B';
        type[9][6]='B';
        type[9][13]='B';
        type[9][16]='B';
        type[10][5]='B';
        type[10][17]='B';
        type[11][14]='B';
        String white="1 1 1 5 1 6 1 7 1 12 1 14 1 18 2 6 2 18 3 0 3 2 3 "+
            "4 3 10 3 16 4 2 5 0 5 15 5 17 6 9 6 13 6 16 6 17 6 18 7 2 7 "+
            "4 8 1 8 9 10 19 11 0 11 2 11 4 11 6 11 8 11 11 12 8";
        type[1][1]='W';
        type[1][5]='W';
        type[1][6]='W';
        type[1][7]='W';
        type[1][12]='W';
        type[1][14]='W';
        type[1][18]='W';
        type[2][6]='W';
        type[2][18]='W';
        type[3][0]='W';
        type[3][2]='W';
        type[3][4]='W';
        type[3][10]='W';
        type[3][16]='W';
        type[4][2]='W';
        type[5][0]='W';
        type[5][15]='W';
        type[5][17]='W';
        type[6][9]='W';
        type[6][13]='W';
        type[6][16]='W';
        type[6][17]='W';
        type[6][18]='W';
        type[7][2]='W';
        type[7][4]='W';
        type[8][1]='W';
        type[8][9]='W';
        type[10][19]='W';
        type[11][0]='W';
        type[11][2]='W';
        type[11][4]='W';
        type[11][6]='W';
        type[11][8]='W';
        type[11][11]='W';
        type[12][8]='W';
        
        visited[4][10]=true;
        char previousdirection='L';
        
    }
    public void solve(char previousdirection, int X, int Y, 
                      char previouscolor, int circle, int step, boolean previousturned,
                      boolean turned_two_steps_ago){
        if(circle%10 == 0){
            System.out.println(step+" "+circle);
        }
        if(X == 4 && Y == 10 && circle == TARGET){
            System.out.println("FOUND!!!!!");
            SOLVEDSOLVED=true;
            throw new IllegalArgumentException("SOLVED I AM SO HAPPY");
        }
        if(X<0 || X>=13 || Y<0 || Y>=20){
            return;
        }
        if(visited[X][Y]){
            return;
        }
        if(previousturned && type[X][Y]=='B'){
            return;
        }
        
        
        
        
        
        boolean forcedturn=false;
        boolean forcedstraight=false;
        int nextcircle=circle;
        if(type[X][Y]!='N'){
            nextcircle++;
        }
        if(type[X][Y]=='B'){
            forcedturn=true;
        }
        if(previouscolor=='B'){
            forcedstraight=true;
        }
        if(type[X][Y]=='W'){
            forcedstraight=true;
        }
        if(previouscolor=='W' && !turned_two_steps_ago){
            forcedturn=true;
        }
        if(forcedturn && forcedstraight){
            return;
        }
        boolean[] next= new boolean[4];
        next[0]=true;
        next[1]=true;
        next[2]=true;
        next[3]=true;
        switch(previousdirection){
            case 'R': next[0]=false;break;
            case 'D': next[1]=false;break;
            case 'L': next[2]=false;break;
            case 'U': next[3]=false;break;
            default: throw new IllegalArgumentException();
        }
        if(forcedturn){
            switch(previousdirection){
                case 'L':next[0]=false;break;
                case 'U':next[1]=false;break;
                case 'R':next[2]=false;break;
                case 'D':next[3]=false;break;
                default: throw new IllegalArgumentException();
            }
        }
        if(forcedstraight){
            next = new boolean[4];
            switch(previousdirection){
                case 'L':next[0]=true;break;
                case 'U':next[1]=true;break;
                case 'R':next[2]=true;break;
                case 'D':next[3]=true;break;
                default: throw new IllegalArgumentException();
            }
        }
        boolean[] turned_this_turn=new boolean[4];
        switch(previousdirection){
                case 'L':turned_this_turn[1]=true;turned_this_turn[3]=true;break;
                case 'U':turned_this_turn[0]=true;turned_this_turn[2]=true;break;
                case 'R':turned_this_turn[1]=true;turned_this_turn[3]=true;break;
                case 'D':turned_this_turn[0]=true;turned_this_turn[2]=true;break;
                default: throw new IllegalArgumentException();
        }
        visited[X][Y]=true;
        for(int i = 0; i < 4; i++){
            int nextX=X;
            int nextY=Y;
            switch(i){
                case 0:nextY--;break;
                case 1:nextX--;break;
                case 2:nextY++;break;
                case 3:nextX++;break;
                default: throw new IllegalArgumentException();
            }
            if(next[i]){
                solve(DIRECTION[i],nextX,nextY,type[X][Y],nextcircle,step+1,
                      turned_this_turn[i],previousturned);
            }
        }
        
        visited[X][Y]=false;
    }
    
    public static void main(String[] args) {
        SAMPLE hello=new SAMPLE();
        hello.solve('L',4,9,'N',0,0,false,false);
    }
}