import java.util.*;
class Point{
	public int x;
	public int y;
	public int dist; //이동거리
	public int wall; //벽을 부시면서 왔는지 아닌지(0|1) 1이면 벽을 부심.

	public Point(int x, int y, int dist, int wall){
		this.x = x;
		this.y = y;
		this.dist = dist; //이동거리
		this.wall = wall; //0이면 벽을 부시지 않음 / 1이면 벽을 부쉼
	}
}
class Main {
	static int n, m;
	static int[][] board;
	static boolean[][][] visited;
	
	public int solution(int x, int y){
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

		Queue<Point> Q = new LinkedList<>();
        Q.add(new Point(0, 0, 1, 0));
		visited[x][y][0] = true; //0은 벽을 부시지 않고 방문한 노드의 방문 여부
        visited[x][y][1] = true; //1은 벽을 부시면서 방문한 노드의 방문 여부
 
        while (!Q.isEmpty()) {
			Point tmp = Q.poll();
			//cur.x , cur.y는 현재 좌표
			if (tmp.x == n - 1 && tmp.y == m - 1) return tmp.dist;
			// 문제에서  (1, 1)과 (N, M)은 항상 0이라고 가정하자. 이동거리를 리턴
	
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
	
				if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if(board[nx][ny] == 0) { //벽이 없을 때
						if (visited[nx][ny][tmp.wall] == false) { 
							//(벽을 부쉈는지 아닌지)으로 방문한 적이 없다면 방문한다.
							visited[nx][ny][tmp.wall] = true;
							Q.offer(new Point(nx, ny, tmp.dist + 1, tmp.wall));
						}
					}    
					else if (board[nx][ny] == 1) { //벽일때
						if (tmp.wall == 0 && visited[nx][ny][1] == false) { //현재까지 벽을 부순적이 없고, 벽을 부숴서 방문한 적이 없다면 방문한다.
							visited[nx][ny][1] = true;
							Q.offer(new Point(nx, ny, tmp.dist + 1, 1));
						}
					}
				}
			}
        }
 
        return -1;
	}
		
	public static void main(String[] args){
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
 
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        
        board = new int[n][m];
        for (int i = 0; i < n ; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
 
        visited = new boolean[n][m][2];
        System.out.println(T.solution(1, 1));
	}
}
