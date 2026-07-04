//https://cses.fi/problemset/task/1194/
import java.io.*;
import java.util.*;

public class Demo {

  
    static char[][] adj;
    static List<Integer> cycle;
    static char[] dir ={'U','R','D','L'};
    static int[] drow={-1,0,1,0};
    static int[] dcol={0,1,0,-1};


    public static boolean isBoundary(int row,int col,int n,int m){
        return (row==n-1 || row==0 || col==0 || col==m-1);
    }

    public static boolean isValid(int r,int c,int n,int m){
        return (r>=0 && r<n && c>=0 && c<m);
    }

    
    
  

    public static void solve(Scanner sc) {
      

        int n = sc.nextInt();
        int m = sc.nextInt();

          adj = new char[n][m];

    

        for (int i = 0; i < n; i++) {
            String line=sc.next();
           
          for(int j=0;j<line.length();j++){
            adj[i][j]=line.charAt(j);
          }
         
        }

        boolean[][] vis =new boolean[n][m];

    
       Queue<int[]> MonsterQueue =new LinkedList<>();

        int[][] dist_monster=new int[n][m];

    int INF=Integer.MAX_VALUE;

    for(int[] r:dist_monster){
        Arrays.fill(r, INF);
    }

       for(int i=0;i<n;i++){
         for(int j=0;j<m;j++){
            if(adj[i][j]=='M'){
                MonsterQueue.add(new int[]{i,j});
                vis[i][j]=true;
                dist_monster[i][j]=0;
         }else if(adj[i][j]=='#'){
            vis[i][j]=true;
         }
       } 
    }



   


    while(!MonsterQueue.isEmpty()){
        int size=MonsterQueue.size();

        while (size-->0) {
            int[] curr=MonsterQueue.poll();

            int row=curr[0];
            int col=curr[1];

            for(int k=0;k<4;k++){
               int nr=row+drow[k];
               int nc=col+dcol[k];
                if(isValid(nr,nc,n,m) && !vis[nr][nc]){
                    vis[nr][nc]=true;
                    MonsterQueue.add(new int[]{nr,nc});
                    dist_monster[nr][nc]=dist_monster[row][col]+1;
                }
            }
        }
    }


     Queue<int[]> Queue =new LinkedList<>();

        int[][] dist=new int[n][m];

    char[][] parent=new char[n][m];

     boolean[][] CharVis =new boolean[n][m];
      int[] st={-1,-1};

  

       for(int i=0;i<n;i++){
         for(int j=0;j<m;j++){
            if(adj[i][j]=='A'){
                Queue.add(new int[]{i,j});
                CharVis[i][j]=true;
                st[0]=i;
                st[1]=j;
                dist[i][j]=0;
         }else if(adj[i][j]=='#'){
            CharVis[i][j]=true;
         }
       } 
    }

   
   int[] end={-1,-1};
   

    boolean isPossible=false;
    while(!Queue.isEmpty()){
        int size=Queue.size();

        while (size-->0) {
            int[] curr=Queue.poll();

            int row=curr[0];
            int col=curr[1];

            if(isBoundary(row,col,n,m)){
                end[0]=row;
                end[1]=col;
                isPossible=true;
                break;
            }

            for(int k=0;k<4;k++){
               int nr=row+drow[k];
               int nc=col+dcol[k];
                if(isValid(nr,nc,n,m) && !CharVis[nr][nc] && dist[row][col]+1<dist_monster[nr][nc]){
                    parent[nr][nc]=dir[k];
                    CharVis[nr][nc]=true;
                    Queue.add(new int[]{nr,nc});
                    dist[nr][nc]=dist[row][col]+1;
                }
            }
        }
    }



    if(!isPossible){
        System.out.println("NO");
        return;
    }

    
    int row=end[0];
    int col=end[1];

    StringBuilder sb=new StringBuilder("");

    while(!(row==st[0] && col==st[1])){
        char c =parent[row][col];
       
       sb.append(c);

        if(c=='U')row++;
        else if(c=='R')col--;
        else if(c=='D')row--;
        else if(c=='L')col++;
    }

    System.out.println("YES");
    System.out.println(sb.length());
    System.out.println(sb.reverse().toString().trim());
    

  





    





 


   
        
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
