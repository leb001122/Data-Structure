package DataStructure.SinglyLinkedList;

import java.util.StringTokenizer;

public class ClubList extends SingleList<String>
{
    public ClubList(String clubs)
    {
        StringTokenizer st = new StringTokenizer(clubs, "/");
        while(st.hasMoreTokens())
            addFirst(st.nextToken());
    }

    public String toString()
    {
        if(size == 0)
            return null;

        String str = "";
        Node<String> club = head;
        for(int i=0; i<size; i++, club=club.getNext())
        {
            if(i==size-1)
                str += club;
            else
                str += club+"/";
        }
        return str;
    }
}
