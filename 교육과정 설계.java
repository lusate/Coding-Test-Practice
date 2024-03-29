import java.util.*;
class Main {
	public ArrayList<String> solution(String need, String[] plans){
		ArrayList<String> answer = new ArrayList<>();
		//큐를 plans 개수만큼 넣어줘야 하므로 여기가 아닌 for문 안에 넣는다.
		
		for(String plan : plans){
			Queue<Character> Q = new LinkedList<>();
			boolean flag = true;
			for(char x : need.toCharArray()){
				Q.offer(x); //CBA
			}

			//x 는 과목들
			for(char x : plan.toCharArray()){
				//x는 plans의 모든 문자들
				if(Q.contains(x)){//Q에 포함되어 있는지 확인
					if(Q.poll() != x){ //Q에서 제외한게 x와 다르면 NO, 즉 CBA 순서대로 있는지 확인하기 위함
						answer.add("NO");
						flag = false;
						break;
					}
				}
			}
			//Q에 포함되어 있지 않고 Q가 비어있지 않으면 NO
			if(flag && !Q.isEmpty()){
				answer.add("NO");
			}

			if(Q.isEmpty()){
				answer.add("YES");
			}
		}
		return answer;
		//FGCDAB의 경우 순서가 A,B로 맞지도 않고 Q가 비어있지도 않기 때문에 
		//둘 다 만족해서 NO가 2번 나옴 그래서 flag로 한 번만 나오도록 해줌
	}
	public static void main(String[] args){
		Main T = new Main();
		String[] plans = new String[]{"CBDAGE", "FGCDAB", "CTSBDEA"};
		System.out.println(T.solution("CBA", plans));
	}
}


//답
//[YES, NO, YES]
