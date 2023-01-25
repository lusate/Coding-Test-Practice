import java.util.*;
class Solution {
    public int solution(String[] board) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		int n = board.length;
		int m = board[0].length();

		char[][] chars = new char[n][m];
		int cnt = 0;
		for(int i=0; i<n; i++){ //현재 키가 몇 개인지
			for(int j=0; j<m; j++){
				char c = board[i].charAt(j);
				chars[i][j] = c;
				if(c >= 97 && c <= 122){
					cnt++;
				}
			}
		}

		int keys = (1 << cnt) - 1;
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visit = new boolean[n][m][keys + 1];
		q.offer(new int[]{0,0,0});
		visit[0][0][0] = true;
		int L = 0;
		while(!q.isEmpty()){
			int len = q.size();
			for(int i=0; i<len; i++){
				int[] tmp = q.poll();
				
				for(int k=0; k<4; k++){
					int nx = tmp[0] + dx[k];
					int ny = tmp[1] + dy[k];

					if(nx < 0 || nx >= n || ny < 0 || ny >= m || chars[nx][ny] == '#') continue;

					char c = chars[nx][ny];
					//tmp[2]는 이동하면서 얻은 현재 키의 개수
					//c는 갈려고 하는 지점으로 갈 때 소문자라면 키를 흭득함.
					int ks = addKey(tmp[2], c); //tmp[2]는 내가 갖고 있는 키
					if(ks == keys) return L+1;
					if(visit[nx][ny][ks]) continue;


					//이제 자물쇠에 도착. tmp[2]는 내가 갖고 있는 키, c는 자물쇠
					if(c >= 65 && c <= 90 && !unlock(tmp[2], c)) continue; //못 열면 continue
					visit[nx][ny][ks] = true;
					q.offer(new int[]{nx, ny, ks});
				}
			}

			L++;
		}

		return 0;
    }

	public int addKey(int keys, int c){
		if(c >= 97 && c <= 122){
			int index = c - 97;
			return keys | (1 << index);
		}

		return keys;
	}

	public boolean unlock(int keys, int c){
		int index = c - 65;
		return (keys & (1 << index)) > 0; //0보다 크면 열 수 있음. unlock이 참이면 열 수 있음.
	}


	public static void main(String args[]){
		Solution T = new Solution();
		System.out.println(T.solution(new String[]{"..a.b", "###B#", "..#A.", ".cC..", "....."}));
		// System.out.println(T.solution(new String[]{"@.a..", "###.#", "b.A.B"}));
    // System.out.println(T.solution(new String[]{"...aA", "..B#.", "....b"}));
	}
}


/* 출력
12
8
6
*/
