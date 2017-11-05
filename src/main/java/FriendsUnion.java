import java.util.List;

public class FriendsUnion {

    /*
     * Create class for describing entity "FriendUnion" with Keys and List Friends
     * */
    //for newKey

    private Integer oldKey;
    private Integer secondPartKey;

    private List<Integer> friends ;

    public Integer getOldKey() {
        return oldKey;
    }

    public void setOldKey(Integer oldKey) {
        this.oldKey = oldKey;
    }

    public Integer getSecondPartKey() {
        return secondPartKey;
    }

    public void setSecondPartKey(Integer secondPartKey) {
        this.secondPartKey = secondPartKey;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }
}
