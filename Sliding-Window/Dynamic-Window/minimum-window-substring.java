
class Solution {
    public String minWindow(String s, String t) {

           if (s.isEmpty() || t.isEmpty()) {
            return "";
        } 

        int n = s.length();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        int[] ans = { -1, 0, 0 };
        int formed = 0;
        int required = tMap.size();

        HashMap<Character, Integer> WindowMap = new HashMap<>();

        int left = 0;

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);

            int count = WindowMap.getOrDefault(ch, 0);
            WindowMap.put(ch, count + 1);

            if (tMap.containsKey(ch) && WindowMap.get(ch).intValue() == tMap.get(ch).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                char c = s.charAt(left);

                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                WindowMap.put(c, WindowMap.get(c) - 1);
                if (tMap.containsKey(c) && WindowMap.get(c).intValue() < tMap.get(c).intValue()) {
                    formed--;
                }

                left++;

            }

        }

        return ans[0]==-1?"":s.substring(ans[1],ans[2]+1);
    }
}
