//https://leetcode.com/problems/fruit-into-baskets/
class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> map =new HashMap<>();

        int n=fruits.length;

        int left=0;
        int right=0;

        int maxi=-1;

        while(left<=right && right<n){

            map.put(fruits[right],map.getOrDefault(fruits[right],0)+1);

            while(left<=right && map.size()>2){
                map.put(fruits[left],map.get(fruits[left])-1);
                if(map.get(fruits[left])==0)map.remove(fruits[left]);
                left++;
            }

            maxi=Math.max(maxi,right-left+1);
            right++;
        }

        return maxi;
    }
}
