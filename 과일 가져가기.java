import java.util.*;
class Solution {
	//최솟값 구해주기 (최솟값의 유니크함을 편하게 하기 위해.)
	public int MIN(int[] fruit){
		int min = Integer.MAX_VALUE;
		for(int x : fruit){
			min = Math.min(min, x);
		}

		return min;
	}
	
	//최솟값의 인덱스 구하기 -> 서로 교환하려고 하는 두 학생의 최솟값인 과일이 서로 다르게 하기 위해서.
	public int MinIndex(int[] fruit){
		int min = MIN(fruit);
		for(int i=0; i<3; i++){
			if(fruit[i] == min) return i;
		}

		return 0;
	}

	public boolean unique(int[] fruit){
		int cnt = 0;
		int min = MIN(fruit);

		for(int x : fruit){
			if(x == min){ //찾은 최솟값이 한 개뿐이면 유니크함.
				cnt++;
			}
			if(cnt >= 2){
				return false;
			}
		}

		return true; //유니크함
	}
	public int solution(int[][] fruit){
		//최솟값이 유니크
		//최솟값의 index는 모두 다르다.
		int answer = 0;
		int n = fruit.length;
		int[] ch = new int[n];


		for(int i=0; i<n; i++){
			if(!unique(fruit[i])) continue; //유니크하지 않으면 continue;
			if(ch[i] == 1) continue;
			for(int j=i+1; j<n; j++){ //짝지을려면 j=i+1로 해야함.
				if(ch[i] == 1) continue; //교환 2번은 안됨.
				
				int a = MinIndex(fruit[i]); //첫 번째 학생의 최솟값의 인덱스
				int b = MinIndex(fruit[j]); //두 번째 학생의 최솟값의 인덱스

				//교환하기 전 처음에 초기값이 같으면 이득을 보지 못하므로 유니크 해야 함.
				if(!unique(fruit[j])) continue;

				// 같은 과일 교환 X, i번 학생은 b 과일을 줄 때 0보다 커야 줄 수 있다.
				if(a != b && fruit[i][b] > 0 && fruit[j][a] > 0){
					// 1개 증가한 과일의 개수가 그대로 최솟값을 유지 -> (최솟값 + 1) <= X-1
					//교환하고 나서는 최솟값이 서로 같아도 상관없으므로 <=
					if (fruit[i][a] + 1 <= fruit[i][b] - 1 && fruit[j][b] + 1 <= fruit[j][a] - 1) {
						fruit[i][a]++;
						fruit[i][b]--;
						fruit[j][b]++;
						fruit[j][a]--;
						ch[i] = 1;
						ch[j] = 1;
						
					}
				}
			}
		}

		for(int[] x : fruit){
			answer += MIN(x);
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{10, 20, 30}, {12, 15, 20}, {20, 12, 15}, {15, 20, 10}, {10, 15, 10}}));
		System.out.println(T.solution(new int[][]{{10, 9, 11}, {15, 20, 25}}));
		System.out.println(T.solution(new int[][]{{0, 3, 27}, {20, 5, 5}, {19, 5, 6}, {10, 10, 10}, {15, 10, 5}, {3, 7, 20}}));
	}
}


/* 출력
58
24
32

1. 각 학생의 최솟값이 유니크해야 한다.
2. 서로 교환하려고 하는 두 학생의 최솟값인 과일이 서로 달라야 한다.
3. 교환했을 때 1개 증가한 과일의 개수가 그대로 최솟값을 유지해야 한다.
*/
