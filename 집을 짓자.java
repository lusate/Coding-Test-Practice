import java.util.*;
class Solution {
	public int solution(int[][] board){
		int answer = 0;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int n = board.length;
		int[][] dist = new int[n][n];
		Queue<int[]> Q = new LinkedList<>();
		int emptyLand = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				//문제에서처럼 출발해야 하는 곳이 여러 곳이면 꼭 이중 for문으로 하기.
				if(board[i][j] == 1){
					answer = Integer.MAX_VALUE;
					Q.offer(new int[]{i, j});
					int L = 0; 
					while(!Q.isEmpty()){
						int len = Q.size();
						L++;
						for(int r = 0; r < len; r++){
							int[] cur = Q.poll();
							for(int k = 0; k < 4; k++){
								int nx = cur[0] + dx[k];
								int ny = cur[1] + dy[k];
								if(nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == emptyLand){
									board[nx][ny]--;
									dist[nx][ny] += L;
									Q.offer(new int[]{nx, ny});
									answer = Math.min(answer, dist[nx][ny]);
								}
							}
						}
					}
					emptyLand--;
				}	
			}
		}
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}
		
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1},
			{0, 0, 0, 0, 0}, 
			{0, 0, 1, 0, 0}, 
			{0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0}}));

		System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, 
			{0, 0, 1, 2}, 
			{0, 2, 0, 0}, 
			{0, 2, 1, 0}}));
	}
}


/* 출력
7
6
-1
-1
*/
