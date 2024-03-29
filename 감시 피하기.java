import java.util.*;

class Main{
    static int n;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void dfs(int cnt) {
        if (cnt == 3) { // 장애물 개수 3개
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 'X') {
                    map[i][j] = 'O';
                    dfs(cnt+1);
                    map[i][j] = 'X';
                }
            }
        }

    }
    public static void bfs() {
        Queue<int[]> q = new LinkedList();
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 'T') {
                    q.add(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }


        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];

                while(nx>=0 && ny>=0 && nx<n && ny<n) {
                    if(map[nx][ny] != 'O') {
                        visit[nx][ny] = true;
                        nx += dx[k];
                        ny += dy[k];

                    }else break;
                }
            }
        }

        if (check(visit)) {
            System.out.println("YES");
            System.exit(0);
        }
    }
    public static boolean check(boolean[][] visit) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'S') {
                    if (visit[i][j])
                        return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.next().charAt(0);
            }
        }

        dfs(0);
        System.out.println("NO");
    }
}


/* 입력
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X


4
S S S T
X X X X
X X X X
T T T X
*/


/* 출력
"YES"
"NO"
*/
