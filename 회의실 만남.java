import java.util.*;
class Solution {
    public int solution(int[] enter, int[] exit) {
		int n = enter.length;

		for(int i=0; i<n; i++){
			enter[i]--;
			exit[i]--;
		}

		int[] enterIdx = new int[n];

		for(int i=0; i<n; i++){ // enter의 인덱스 순서
			enterIdx[enter[i]] = i;
		}
		
		int[] enterT = new int[n];
		int[] exitT = new int[n];

		int cnt = 0;
		int j = 0;
		for(int i=0; i<n; i++){
			while(j < n && j <= enterIdx[exit[i]]){
				enterT[enter[j]] = cnt++; //들어올 때 시간 +
				j++;
			}

			exitT[exit[i]] = cnt++; //들어오자마자 나가도록하고 시간 +
		}

		int[] answer = new int[n];
		for(int i=0; i<n; i++){
			for(j = i+1; j<n; j++){
				if(exitT[i] < enterT[j] || exitT[j] < enterT[i]){
					answer[i]++;
					answer[j]++;
				}
			}
		}

		return answer;
    }


	public static void main(String args[]){
		Solution T = new Solution();
		System.out.println(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
		System.out.println(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5}));
		System.out.println(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7}));
	}
}


/* 출력
[3, 1, 2, 2]
[3, 1, 2, 1, 3]
[6, 2, 2, 4, 2, 3, 4, 1]
*/
