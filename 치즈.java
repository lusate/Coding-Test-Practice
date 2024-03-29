import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cheeseNum = 0, time = 0;
    static int[][] map;
    static int[][] air;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1)
                    cheeseNum++;
            }
        }

        while (cheeseNum != 0) {
            air = new int[N][M];

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            air[0][0] = -1; //시작 부분만 -1로 초기화 -> 바깥 공기

            while(!q.isEmpty()){
                int[] now = q.poll();

                for(int i = 0; i<4; i++){
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];

                    // air은 외부 공기와 내부 공기의 상태
                    if (nx >= 0 && ny >=0 && nx < N && ny < M) {
                        if (map[nx][ny] == 1) { // 치즈가 있는 곳으로 갔을 때 air을 +해줌.
                            air[nx][ny]++;
                        }

                        // -1이면 바깥 공기가 존재하는 곳, 바깥 공기가 퍼져나감.
                        if(map[nx][ny] == 0 && air[nx][ny] == 0){
                            air[nx][ny] = -1;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(air[i][j] >= 2){
                        cheeseNum--;
                        map[i][j] = 0; // 치즈 없앰.
                    }
                }
            }
            time++;
        }

        System.out.println(time);
    }
}

/* 입력
8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
*/


/* 출력
4
*/
