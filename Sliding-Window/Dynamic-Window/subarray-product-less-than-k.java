//https://leetcode.com/problems/subarray-product-less-than-k/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n=nums.length;

      //  if(k==0)return 0;

        int count=0;
        int left=0;
        int right=0;
        int mul =1;

        while(left<=right && right<n){
            mul*=nums[right];

            while(left<=right && mul>=k){
                mul/=nums[left];
                left++;
            }

            count+=(right-left+1);
            right++;
        }

        return count;
    }
}
