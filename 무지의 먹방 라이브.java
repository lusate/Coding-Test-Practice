import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
		int[] copy = new int[food_times.length + 1];
        System.arraycopy(food_times, 0, copy, 1, food_times.length);
		Arrays.sort(copy);

		int rest = food_times.length;
		for(int i=1; i<copy.length; i++){
			long time = (long) rest * (copy[i] - copy[i-1]);
			if(k < time){
				long idx = k % rest;
				int cnt = 0;
				for(int j=0; j<food_times.length; j++){
					if(food_times[j] >= copy[i]){
						if(cnt == idx) return j+1;
						cnt++;
					}
				}
			}

			else{
				k -= time;
				rest--;
			}
		}
      
		return -1;
    }

	private static void main(String args[]){
		Solution T = new Solution();
		System.out.println(new int[]{3,1,2}, 5);
	}
}


/* 출력
1
*/
