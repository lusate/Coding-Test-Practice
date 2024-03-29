import java.util.*;
class Solution{
	int[] ryan;
	int[] res;
	int max = Integer.MIN_VALUE;
	public void dfs(int n, int cnt, int[] info){
		if(cnt == n){ //cnt는 발사한 화살 개수
			int apeachSum = 0; //어피치 점수 합
			int ryanSum = 0; //라이언 점수 합
			for(int i=0; i<=10; i++){
// 				if(info[i] != 0 || ryan[i] != 0){
// 					if(info[i] < ryan[i]) ryanSum += 10 - i;
// 					else apeachSum += 10 - i; //맞힌 개수가 같아도 어피치가 이김
// 				}
				if(ryan[i] == 0 && info[i] == 0) continue;
				if(ryan[i] > info[i]) ryanSum += 10 - i;
				else apeachSum += 10 - i;
			}

			if(apeachSum < ryanSum){
				int dif = ryanSum - apeachSum;
				if(dif >= max){ //최대값보다 커야함.
					res = ryan.clone();
					max = dif;
				}
			}
			return;
		}

		else{
			for(int i=0; i<=10 && ryan[i] <= info[i]; i++){
				ryan[i]++;
				dfs(n, cnt+1, info);
				ryan[i]--;
			}
		}
		
	}
	public int[] solution(int n, int[] info) {
		ryan = new int[11];
		res = new int[]{-1};
		dfs(n, 0, info);
		return res;
	}
	
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(T.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(T.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
	}
}


/* 출력
[0,2,2,0,1,0,0,0,0,0,0]
[-1]
[1,1,1,1,1,1,1,1,0,0,2]
*/


-----------------------------------------------------------------------------------------------------
import java.util.*;
class Solution {
	//이진수를 바탕으로 1이면 Win, 0이면 Lose로 적용
	//ex) 3 -> 0000000011 -> W, W, L, L, L, L, L, L, L, L
	// 22 -> 0000010110 -> L, W, W, L, W, L, L, L, L, L
    public int[] solution(int n, int[] info) {
		int[] answer = new int[11];
		int[] tmp = new int[11]; //라이언이 쏜 화살 개수를 저장.

		int maxDif = 0;
		for(int i=1; i<(1 << 10); i++){ //이진수 이므로 2 ** 10까지
			int ryan = 0, apeach = 0;
			int cnt = 0; //라이언이 쏜 화살 카운트

			for(int j=0; j<10; j++){
				if((i & (1 << j)) != 0){ //부분 집합이 존재하는지. -> 0이 아니면
					//라이언이 이길 때 -> 22의 이진수 01101000000 에서 1이면 라이언이 이김. (앞에서부터 10점.)
					ryan += 10 - j;
					tmp[j] = info[j] + 1; //apeach가 맞힌 화살 개수보다 1만 더 크면 라이언이 이김.
					cnt += tmp[j];
				}
				else{ //비기거나 apeach가 이기는 경우
					tmp[j] = 0; //라이언이 한 발도 안 쏘게 함.
					if(info[j] > 0){ //apeach가 맞추면 apeach가 점수 가져감.
						apeach += 10 - j;
					}
				}
			}

			//apeach가 ryan보다 화살을 더 쓰면 안됨.
			if(cnt > n) continue;

			tmp[10] = n - cnt; //남은 화살 개수를 저장.  tmp[10] 이면 0점.

			//maxDif보다 더 큰 값을 찾음
			
       		
			//라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러가지인 경우 가장 낮은 점수를 더 많이 맞힌 경우를 return
			if(ryan - apeach == maxDif){
				for(int k=10; k>=0; k--){ //가장 낮은 점수가 마지막 인덱스에 있기 때문에 거꾸로 for문 돌림
					if(tmp[k] > answer[k]){
						maxDif = ryan - apeach;
						answer = Arrays.copyOf(tmp, tmp.length);
						break;
					}
					else if(tmp[k] < answer[k]){
						break;
					}
				}
				
			}
			else if(ryan - apeach > maxDif){
				maxDif = ryan - apeach;
				answer = Arrays.copyOf(tmp, tmp.length);
			}
		}

		//라이언이 못 이기는 경우
		if(maxDif == 0) return new int[]{-1};

		
        return answer;
    }

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(T.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
		System.out.println(Arrays.toString(T.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
	}
}
