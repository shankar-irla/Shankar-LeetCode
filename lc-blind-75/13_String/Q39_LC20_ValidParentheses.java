/*
-------------------------------------------------------
Problem ID   : LC20
Title        : Valid Parentheses
Topic        : String
Pattern      : Stack
Difficulty   : Easy

Problem Summary:
Given a string containing '(', ')', '{', '}', '[', ']',
determine if the input string is valid.

Rules:
- Open brackets must be closed by same type
- In correct order

Example:
Input  : "()[]{}"
Output : true

Input  : "(]"
Output : false

-------------------------------------------------------

Approach 1: Stack (Optimal)

Idea:
- Push opening brackets
- On closing bracket → check top of stack
- If mismatch → invalid

Time  : O(n)
Space : O(n)

-------------------------------------------------------

Approach 2: Using Map + Stack

- Use map for bracket pairs
- Cleaner code

Time  : O(n)
Space : O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q39_LC20_ValidParentheses {

    /*
    -------------------------------------------------------
    Approach 1: Stack
    -------------------------------------------------------
    */
    public static boolean isValid(String s){

        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){

            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }
            else{

                if(stack.isEmpty())
                    return false;

                char top = stack.pop();

                if((ch == ')' && top != '(') ||
                   (ch == '}' && top != '{') ||
                   (ch == ']' && top != '[')){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /*
    -------------------------------------------------------
    Approach 2: Map + Stack
    -------------------------------------------------------
    */
    public static boolean isValidMap(String s){

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){

            if(map.containsKey(ch)){

                if(stack.isEmpty() || stack.pop() != map.get(ch))
                    return false;
            }
            else{
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

    // Optional testing
    public static void main(String[] args){

        String s = "()[]{}";

        System.out.println(isValid(s));

        /*
        // Map version
        System.out.println(isValidMap(s));
        */
    }
}

git add lc-blind-75/13_String/Q39_LC20_ValidParentheses.java
git commit -m "39/75: LC20 Valid Parentheses (Stack + Map)"
git push