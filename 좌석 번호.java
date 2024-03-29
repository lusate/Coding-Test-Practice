import java.util.*;
class Solution {
	public int[] solution(int c, int r, int k){
		int[] answer = new int[2];
		//배열로 생각하기 위해 90도 회전한 상태라 생각하고 시작.
		int[][] map = new int[c][r];
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int x = 0, y = 0, cnt = 1, d = 1; //처음 앉는 좌석 위치, count는 k번째 사람, d는 방향(3시 방향부터 움직이기 때문)

		while(cnt < k){ //이동한 횟수 k보다 작을 때까지 반복
			int nx = x + dx[d];
			int ny = y + dy[d];

			//이동 가능하면 
			if(nx >= 0 && nx < c && ny >= 0 && ny < r && map[nx][ny] == 0){
				cnt++;
				map[x][y] = cnt;

				//이동 가능하므로 위치 초기화.
				x = nx;
				y = ny;
			}

			//이동 불가능하면 방향 바꾸기.
			else{
				d = (d + 1) % 4;
			}
		}

		answer[0] = x + 1;
		answer[1] = y + 1;

		if(k > c*r) return new int[] {0, 0};
		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(6, 5, 12)));	
		System.out.println(Arrays.toString(T.solution(6, 5, 20)));
		System.out.println(Arrays.toString(T.solution(6, 5, 30)));
	}
}

/* 출력
[6, 3]
[2, 3]
[4, 3]
*/
