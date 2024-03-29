import java.util.*;
class Main{
	int answer=0, m, target;
	//m : nums 배열 사이즈, target : 목표값(입력한 매개변수)
	ArrayList<Integer> nums = new ArrayList<>();
	Stack<Integer> pm = new Stack<>();
	int[] ch; //체크
	boolean flag = false;
	
	public void DFS(int L){
		if(flag) return;
		if(L==m){
			answer=0; //매번 수열이 완성되면 초기화
			for(int x : pm) answer=answer*10+x; //10진수로 함
			if(answer > target){ //답을 발견하는 순간 true
				flag = true;
			}
		}
		else{
			for(int i=0; i<m; i++){ //n은 입력할 자연수고 m은 nums 배열의 사이즈
				if(ch[i] == 0){ //체크가 안되어있는 경우
					ch[i] = 1; //체크를 1로 해주고 push
					pm.push(nums.get(i));
					//nums는 배열이 아닌 ArrayList 이므로 get을 함
					DFS(L+1);
					//다시 초기화 시켜주고 pop
					ch[i] = 0;
					pm.pop();
				}
			}
		}
	}

	public int solution(int n){
		int tmp = n;
		target = n;
		while(tmp > 0){
			int t=tmp%10;
			nums.add(t);
			tmp=tmp/10;
		} //nums에 3,1,2가 들어감
		//nums의 각 자릿수 하나하나를 모두 순열하기 위해서 함

		//for(int x : nums) System.out.print(x + ' ');
		Collections.sort(nums);
		//[1, 2, 3]

		m = nums.size();
		//m = 3
		ch = new int[m];
		DFS(0);

		if(flag == false){ //321 일 경우 발견된 수가 없으므로 -1 리턴
			return -1;
		}
		//n보다 크면 답
		return answer;
	}
	public static void main(String[] args){
		Main T = new Main();
		System.out.println(T.solution(213));
	}
}
