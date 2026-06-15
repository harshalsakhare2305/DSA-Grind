public class Solution {
    public static int lowerbound(int[] nums,int target){
        int n= nums.length;
        int ans=n;
        int si =0;
        int ei=n-1;
        while(si<=ei){

            int mid =si+(ei-si)/2;
            if(nums[mid]>=target){
                ans=mid;
                ei=mid-1;
            }else{
                si=mid+1;
            }
        }

        return ans;

    }


    public static int upperbound(int[] nums,int target){
        int n= nums.length;
        int ans=n;
        int si =0;
        int ei=n-1;
        while(si<=ei){

            int mid =si+(ei-si)/2;
            if(nums[mid]>target){
                ans=mid;
                ei=mid-1;
            }else{
                si=mid+1;
            }
        }

        return ans;

    }

    public static int count(int arr[], int n, int x) {
       
       int lb =lowerbound(arr,x);
       int ub =upperbound(arr,x);
       
        return ub-lb;
       
    }
}
