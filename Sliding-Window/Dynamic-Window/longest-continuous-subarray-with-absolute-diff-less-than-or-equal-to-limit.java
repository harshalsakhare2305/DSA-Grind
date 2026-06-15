//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> max = new ArrayDeque<>();
            Deque<Integer> min = new ArrayDeque<>();

            int n=nums.length;
            int maxi=-1;
            int left=0;
            int right=0;

            while(left<=right && right<n){
                 
                 while(!max.isEmpty() && nums[max.peekLast()]<=nums[right]){
                    max.pollLast();
                 }

                 while(!min.isEmpty() && nums[min.peekLast()]>=nums[right]){
                    min.pollLast();
                 }

                 max.addLast(right);
                 min.addLast(right);


                    int diff =Math.abs(nums[max.peekFirst()]-nums[min.peekFirst()]);

                while(left<=right && diff > limit ){
                     
                   left++;

                   while(!max.isEmpty() && max.peekFirst()< left){
                    max.pollFirst();
                   }

                   while(!min.isEmpty() && min.peekFirst() < left){
                    min.pollFirst();
                   }


                  diff =Math.abs(nums[max.peekFirst()]-nums[min.peekFirst()]);
                }


                maxi=Math.max(maxi,right-left+1);

                right++;
                 

            }

            return maxi;


    }
}
