import java.util.*;

public class Q47_LC3_LongestSubstringSlidingWindow {

    public static int lengthOfLongestSubstringSet(String s){

        Set<Character> set = new HashSet<>();
        int left = 0, maxLen = 0;

        for(int right = 0; right < s.length(); right++){

            while(set.contains(s.charAt(right))){
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static int lengthOfLongestSubstringMap(String s){

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;

        for(int right = 0; right < s.length(); right++){

            char ch = s.charAt(right);

            if(map.containsKey(ch)){
                left = Math.max(left, map.get(ch) + 1);
            }

            map.put(ch, right);

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}