import java.util.*;
class Solution {
    public String solution(String X, String Y) {
        int[] mapX = new int[10];
        int[] mapY = new int[10];

        for (char c : X.toCharArray()) {
            mapX[c - 48]++;// 인덱스의 숫자가 X에 몇 개있는지를 저장
            // ex) 100이면 mapX[0]은 0이 100에 2개 있기 때문에 mapX[0] = 2
            // mapX[1] 은 1이 100에 1개 있기 때문에 mapX[1] = 1
        }
        for (char c : Y.toCharArray()) {
            mapY[c - 48]++;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (mapX[i] > 0 && mapY[i] > 0) { // 같은 숫자가 있다는 뜻
                answer.append(i);

                mapX[i]--;
                mapY[i]--;
                // ex) mapX에는 3이라는 숫자가 2개 있고 mapY에는 3이라는 숫자가 3개있을 경우
                // 2개로 해야 함.
            }
        }

        if ("".equals(answer.toString())) {
            return "-1";
        }
        else if (answer.toString().charAt(0) == 48) {
            return "0";
        }
        else return answer.toString();
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution("100", "2345"));
        System.out.println(T.solution("100", "203045"));
        System.out.println(T.solution("100", "123450"));
        System.out.println(T.solution("12321", "42531"));
        System.out.println(T.solution("5525", "1255"));
        System.out.println(T.solution("3403", "13203"));
    }
}


/* 출력
-1
0
10
321
552
330
*/
