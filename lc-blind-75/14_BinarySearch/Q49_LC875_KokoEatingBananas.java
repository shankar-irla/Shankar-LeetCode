/*
-------------------------------------------------------
Problem ID   : LC875
Title        : Koko Eating Bananas
Topic        : Binary Search
Pattern      : Binary Search on Answer
Difficulty   : Medium

Problem Summary:
Koko loves bananas.

Given:
- piles[i] = bananas in ith pile
- h = total hours available

Return the minimum eating speed k such that
Koko can finish all bananas within h hours.

Example:
Input  : piles = [3,6,7,11], h = 8
Output : 4

-------------------------------------------------------

Approach 1: Brute Force

- Try every speed from 1 to maxPile
- Check if possible

Time  : O(maxPile * n)
Space : O(1)

-------------------------------------------------------

Approach 2: Binary Search on Answer (Optimal)

Idea:
- Minimum speed = 1
- Maximum speed = max pile
- Check if speed k is feasible

If feasible:
    try smaller speed
Else:
    increase speed

Time  : O(n log maxPile)
Space : O(1)

-------------------------------------------------------
*/

public class Q49_LC875_KokoEatingBananas {

    /*
    -------------------------------------------------------
    Approach 1: Brute Force
    -------------------------------------------------------
    */
    public static int minEatingSpeedBrute(int[] piles, int h){

        int maxPile = 0;

        for(int pile : piles){
            maxPile = Math.max(maxPile, pile);
        }

        for(int speed = 1; speed <= maxPile; speed++){

            if(canFinish(piles, h, speed)){
                return speed;
            }
        }

        return maxPile;
    }

    /*
    -------------------------------------------------------
    Approach 2: Binary Search (Optimal)
    -------------------------------------------------------
    */
    public static int minEatingSpeed(int[] piles, int h){

        int left = 1;
        int right = 0;

        for(int pile : piles){
            right = Math.max(right, pile);
        }

        int answer = right;

        while(left <= right){

            int mid = left + (right - left) / 2;

            if(canFinish(piles, h, mid)){

                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canFinish(int[] piles, int h, int speed){

        long hours = 0;

        for(int pile : piles){

            hours += (pile + speed - 1) / speed;

            if(hours > h){
                return false;
            }
        }

        return true;
    }

    // Optional testing
    public static void main(String[] args){

        int[] piles = {3,6,7,11};
        int h = 8;

        System.out.println(minEatingSpeed(piles, h));

        /*
        // Brute Force
        System.out.println(minEatingSpeedBrute(piles, h));
        */
    }
}