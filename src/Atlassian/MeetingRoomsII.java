package Atlassian;

import java.util.PriorityQueue;

/**
 Given an array of meeting time intervals where intervals[i] = [starti, endi], return the minimum number of conference
rooms required.

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
* */

public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        //Min heap who has the rooms with meeting end time only.
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < intervals.length; i++) {

            if (!rooms.isEmpty() && rooms.peek() <= intervals[i][1]) {
                //It means re-use we can use the room available at the root of the heap
                //So replace the room with this meeting interval data
                rooms.poll();
            }
            //Now add the new room in the rooms
            rooms.offer(intervals[i][1]);
        }

        return rooms.size();
    }
    /*
    reuse room - means remove the odd entry and add new entry, simply replacing the room with new meeting data (poll() + offer())
    can't reuse room - means a new room to be added (offer()) only
     */


}
