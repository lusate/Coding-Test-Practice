import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int sum = 0;
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i];
            while(true){ //안 하면 추가를 안 하게 됨.
                if (q.isEmpty()) {
                    q.add(truck);
                    sum += truck;
                    time++;
                    break; // break 해주지 않으면 q에 있는 값으로만 계속 반복하게 됨.
                    // 다음 truck 무게를 q에 넣지 못함.
                }
                else if (q.size() < bridge_length) {
                    // 트럭을 추가할 수 있음. 근데 무게 조건을 만족 못하면 추가 X.
                    if (sum + truck <= weight) {
                        q.add(truck);
                        sum += truck;
                        time++;
                        break;
                    }
                    else { //추가 못함
                        q.add(0);
                        time++;
                    }
                }
                else if(q.size() == bridge_length){
                    sum -= q.poll();
                }
            }
        }
        answer = time + bridge_length;
        // 그 후 다리 위에서 1칸씩 움직일 때마다 역시 1초가 흘러감. (문제가 그럼.)
        return answer;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(T.solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(T.solution(100, 100, new int[]{10}));
        System.out.println(T.solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }
}


/* 출력
8
101
110
*/



// -----------------------------------------------------------------------------------------------------------------------------------------------

// 다른 방법
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            q.add(0);
        }
        // 처음에 그냥 0을 삽입.

        int idx = 0;
        int nowWeight = 0; // 다리에 있는 현재 트럭의 무게

        while(idx < truck_weights.length){
            // 현재 다리에 있는 트럭 무게에서 나갈 트럭의 무게를 빼줌.
            nowWeight -= q.poll();
            answer++; // 새 트럭이 들어옴.

            // 현재 다리에 있는 트럭 무게와 곧 들어올 트럭 무게의 합과 비교
            if (nowWeight + truck_weights[idx] <= weight) { // 트럭 추가 가능
                q.add(truck_weights[idx]);
                nowWeight += truck_weights[idx++];
            }
            else{ // 0을 추가
                q.offer(0);
            }
        }

        answer += bridge_length;
        return answer;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(T.solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(T.solution(100, 100, new int[]{10}));
        System.out.println(T.solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }
}
