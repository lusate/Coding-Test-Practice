import java.util.*;
class Solution{
	static int[] dy;
	public int dfs(int st, String s){
		//dy[st]에 값이 있다면 (메모이제이션)
		if(dy[st] > 0) return dy[st];

		//인덱스 st인 부분이 0이면 뻗어가지 못하게 0을 리턴
		if(st < s.length() && s.charAt(st) == '0') return 0;
		if(st == s.length()-1 || st == s.length()){
			return 1; //끝까지 도착하면 return 1.
		}
		else{//백하면서 res를 더해줌.
			int res = dfs(st+1, s);  //알파벳으로 복원하는 방법의 가지 수
			int num = Integer.parseInt(s.substring(st, st+2));

			if(num <= 26) res += dfs(st+2, s); //조건 맞으므로 그 다음으로 뻗어나감.
			//res = 2 ,3 ,6
			return dy[st] = res;  //마지막에는 res에서 값을 누적하여 더해서 D(0)의 값을 출력해야 한다.
		}
	}
	public int solution(String s) {
		dy = new int[101];
		int answer = dfs(0, s);
		return answer;
	}
	
	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution("25114"));
		System.out.println(T.solution("23251232"));
		System.out.println(T.solution("21020132"));
		System.out.println(T.solution("21350" ));
	}
}


/* 출력
6
12
2
0
*/
