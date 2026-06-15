//https://leetcode.com/problems/minimum-size-subarray-sum/
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int left=0;
        int right=0;

        int sum=0;
        int mini=n+1;

        while(right<n && left<=right){
            sum+=nums[right];

            while(left<=right && sum>=target){
                mini=Math.min(mini,right-left+1);
                sum-=nums[left];
                left++;
            }

            right++;
        }

        return mini==(n+1)?0:mini;
    }
}
