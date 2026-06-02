/*
-------------------------------------------------------
Problem ID   : LC981
Title        : Time Based Key-Value Store
Topic        : Binary Search
Pattern      : HashMap + Binary Search
Difficulty   : Medium

Problem Summary:
Design a time-based key-value store.

Operations:

set(key, value, timestamp)
    Store value for key at timestamp

get(key, timestamp)
    Return value associated with largest timestamp
    <= given timestamp

Example:

set("foo", "bar", 1)

get("foo", 1) → "bar"
get("foo", 3) → "bar"

set("foo", "bar2", 4)

get("foo", 4) → "bar2"
get("foo", 5) → "bar2"

-------------------------------------------------------

Approach 1: Linear Search

- Store all entries
- Search backwards

Time:
set -> O(1)
get -> O(n)

-------------------------------------------------------

Approach 2: HashMap + Binary Search (Optimal)

- HashMap<Key, List<Entry>>
- Timestamps are increasing
- Binary search latest timestamp <= target

Time:
set -> O(1)
get -> O(log n)

Space:
O(n)

-------------------------------------------------------
*/

import java.util.*;

public class Q50_LC981_TimeBasedKeyValueStore {

    static class Pair {

        String value;
        int timestamp;

        Pair(String value, int timestamp){
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    private Map<String, List<Pair>> map;

    public Q50_LC981_TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    /*
    -------------------------------------------------------
    set()
    -------------------------------------------------------
    */
    public void set(String key, String value, int timestamp){

        map.putIfAbsent(key, new ArrayList<>());

        map.get(key).add(
                new Pair(value, timestamp)
        );
    }

    /*
    -------------------------------------------------------
    get()
    -------------------------------------------------------
    */
    public String get(String key, int timestamp){

        if(!map.containsKey(key))
            return "";

        List<Pair> list = map.get(key);

        int left = 0;
        int right = list.size() - 1;

        String answer = "";

        while(left <= right){

            int mid = left + (right - left) / 2;

            if(list.get(mid).timestamp <= timestamp){

                answer = list.get(mid).value;
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return answer;
    }

    // Optional testing
    public static void main(String[] args){

        Q50_LC981_TimeBasedKeyValueStore kv =
                new Q50_LC981_TimeBasedKeyValueStore();

        kv.set("foo", "bar", 1);

        System.out.println(
                kv.get("foo", 1)
        );

        System.out.println(
                kv.get("foo", 3)
        );

        kv.set("foo", "bar2", 4);

        System.out.println(
                kv.get("foo", 4)
        );

        System.out.println(
                kv.get("foo", 5)
        );
    }
}