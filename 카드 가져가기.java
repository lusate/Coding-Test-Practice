import java.util.*;
class Main {
	public int solution(int[] nums, int k){
		int m = nums.length - k;
		int answer = 0;
		int sum = 0;
		//윈도우 크기 nums.length - k

		for(int i=0; i<m; i++){
			sum += nums[i];
		}//윈도우 sum

		for(int i=0; i<nums.length; i++){
			answer += nums[i];
		}//전체 sum
		
		int mins = sum;
		//윈도우 크기에서 합이 가장 작은 값을 구함
		for(int i=m; i<nums.length; i++){
			sum += nums[i] - nums[i-m];
			mins = Math.min(mins, sum);
		}
		//윈도우 sum을 구하고 뒤 원소 하나를 더한 후 맨 앞 원소를 빼준다.
		//2,3,7,1,2,1,5 일 때
		//sum = 12, 여기서 sum = 12 + 1 - 2 = 11이 된다.
		//다음은 11 + 2 - 3 = 10
		
		//최솟값을 구했으므로 전체 sum에서 mins를 빼줌
		return answer - mins;
	}

	public static void main(String[] args){
		Main T = new Main();
		int[] arr = new int[]{2, 3, 7, 1, 2, 1, 5};
		System.out.println(T.solution(arr, 4));
		int[] arr2 = new int[]{1, 2, 3, 5, 6, 7, 1, 3, 9};
		System.out.println(T.solution(arr2, 5));
	}
}


//답
//17
//26
