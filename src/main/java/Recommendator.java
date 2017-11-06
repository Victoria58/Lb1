import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Victoria on 06.11.2017.
 */
public class Recommendator {

    /**
     *
     * @param socialNetworkMap - map based on <UserId, List of user friends>
     * @param idsToCheck - optional list of ids, to check concrete people
     * @return list of objects {@FriendsUnion}
     * which means userId - whom we recommend
     * userToId - who we recommend
     * friend - common friends between
     */
    public List<FriendsUnion> getFriendsUnions(Map<Integer, List<Integer>> socialNetworkMap, List<Integer> idsToCheck) {
        List<FriendsUnion> theoreticalFriends = new ArrayList<>();
        Set<Integer> userIds = new HashSet<>(socialNetworkMap.keySet().stream().filter(idsToCheck::contains).collect(Collectors.toSet()));

        /*
         *  apply idsToCheck for concrete people
         */
        Set<Integer> filteredIds = idsToCheck != null ? userIds.stream().filter(idsToCheck::contains).collect(Collectors.toSet()) : userIds;


        filteredIds.forEach(user1Id -> {
            List<Integer> user1Friends = socialNetworkMap.get(user1Id);
            socialNetworkMap.keySet().forEach(user2Id -> {
                // check user1Id and user2Id avoid situation when its same people or friends
                if (!user1Id.equals(user2Id) && !user1Friends.contains(user2Id)) {
                    List<Integer> friends = new ArrayList<>(user1Friends);
                    friends.addAll(socialNetworkMap.get(user2Id));
                    Set<Integer> commonFriends = getDuplicates(friends);

                    // if they don`t have common friends - don`t create recommendation
                    if (!commonFriends.isEmpty()) {
                        FriendsUnion friendsUnion = new FriendsUnion();
                        friendsUnion.setUserId(user1Id);
                        friendsUnion.setUserToId(user2Id);
                        friendsUnion.setFriends(commonFriends);
                        theoreticalFriends.add(friendsUnion);
                    }
                }
            });
        });
        return theoreticalFriends;
    }

    public Map<Integer, List<Integer>> getRecommendations(List<FriendsUnion> friendsUnions) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        friendsUnions.forEach(friendsUnion -> {
                List<Integer> recommendations;
                // if we already have elem with this key - do not add
                if (result.containsKey(friendsUnion.getUserId())) {
                    recommendations = result.get(friendsUnion.getUserId());
                } else {
                    recommendations = new ArrayList<>();
                }
                recommendations.add(friendsUnion.getUserToId());
                result.put(friendsUnion.getUserId(), recommendations);
        });
        return result;
    }

    private Set<Integer> getDuplicates(List<Integer> items) {
        return items.stream().filter(i -> Collections.frequency(items, i) > 1)
                .collect(Collectors.toSet());
    }

}
