import java.util.Set;

public class FriendsUnion {

    /*
     * Create class for describing entity "FriendUnion" with Keys and List Friends
     * */
    //for newKey

    /**
     * userId - whom we recommend
     */
    private Integer userId;

    /**
     * userToId - who we recommend
     */
    private Integer userToId;

    /**
     * common friends between userId and userToId
     */
    private Set<Integer> friends;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserToId() {
        return userToId;
    }

    public void setUserToId(Integer userToId) {
        this.userToId = userToId;
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }
}