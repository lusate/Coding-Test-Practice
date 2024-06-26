import java.util.*;
public class Solution {
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};
	public int solution(int[][] board){
		int answer = 0;
		int n = 10;
		int x1=0, y1=0, x2=0, y2=0;
		int d1 = 0, d2 = 0;
		int time = 0;

		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(board[i][j] == 2){ //현수 위치.
					x1 = i;
					y1 = j;
				}
				else if(board[i][j] == 3){ //강아지 위치
					x2 = i;
					y2 = j;
				}
			}
		}

		
		
		while(time < 10000){
			time++;
			int nx1 = x1 + dx[d1]; //현수
			int ny1 = y1 + dy[d1];

			int nx2 = x2 + dx[d2]; //강아지
			int ny2 = y2 + dy[d2];

			// if(nx1<0 || nx1>=n || ny1<0 || ny1>=n || board[nx1][ny1] == 1){
			// 	d1 = (d1+1) % 4;
			// }
			// else{
			// 	x1 = nx1;
			// 	y1 = ny1;
			// }

			// if(nx2<0 || nx2>=n || ny2<0 || ny2>=n || board[nx2][ny2] == 1){
			// 	d2 = (d2+1) % 4;
			// }
			// else{
			// 	x2 = nx2;
			// 	y2 = ny2;
			// }
			if (nx1 >= 0 && ny1 >= 0 && nx1 < n && ny1 < n && board[nx1][ny1] != 1) { // 0 만이 아니라 2나 3의 위치로도 이동이 가능하기 때문에 != 1 로 해줌
				x1 = nx1;
				y1 = ny1;
			}else{
				d1 = (d1 + 1) % 4;
			}
		
			if (nx2 >= 0 && ny2 >= 0 && nx2 < n && ny2 < n && board[nx2][ny2] != 1) {
				x2 = nx2;
				y2 = ny2;
			} else{
				d2 = (d2 + 1) % 4;
			}


			if(x1 == x2 && y1 == y2){
				answer = time;
				break;
			}
		}

		if(time >= 10000){
			return 0;
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution T = new Solution();
		int[][] board = new int[][]{
			{0,0,0,0,0,0,1,0,0,0},
			{0,0,0,0,1,0,0,0,0,0},
			{0,0,0,1,0,0,0,1,0,0}, 
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,2,0,0},
			{1,0,0,0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,3,0,0,0,1},
			{0,0,0,1,0,1,0,0,0,0},
			{0,1,0,1,0,0,0,0,0,0}};

		System.out.println(T.solution(board));
	}
}


/* 출력
51
*/
