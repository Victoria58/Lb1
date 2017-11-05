import java.util.*;

public class Recommendation {

    public List<FriendsUnion> getFriendsUnions(Map<Integer, List<Integer>> socialNetworkMap) {
        /*
         * Class getFriendsUnions creating List based on entity FriendsUnion
         * */
        List<FriendsUnion> friendsUnions = new ArrayList<>();
        for (Integer key : socialNetworkMap.keySet()) {
            List<Integer> friendsIDs = socialNetworkMap.get(key);

            friendsIDs.forEach(e -> {
                FriendsUnion union = new FriendsUnion();
                union.setOldKey(key);
                union.setSecondPartKey(e);
                union.setFriends(friendsIDs);
                friendsUnions.add(union);
            });


            /*
            for (Integer friendsID : friendsIDs) {

                FriendsUnion union = new FriendsUnion();
                union.setOldKey(key);
                union.setSecondPartKey(friendsID);
                union.setFriends(friendsIDs);
                friendsUnions.add(union);

            }*/
        }
        return friendsUnions;
    }

    public List<FriendsUnion> getCommonFriends (List<FriendsUnion> friendsUnions){

        List<FriendsUnion> commonFriends2 = new ArrayList<>();

        friendsUnions.stream().filter(f -> !f.getFriends().isEmpty()).forEach(coincidentElement1 -> {
            FriendsUnion coincidentElement2 = friendsUnions.stream()
                    .filter(f2 -> coincidentElement1.getOldKey().equals(f2.getSecondPartKey()) && f2.getOldKey().equals(coincidentElement1.getSecondPartKey())
                    ).findFirst().get();
            if (!coincidentElement1.getFriends().contains(coincidentElement2.getOldKey())) {
                FriendsUnion commonFriendsUnion = new FriendsUnion();
                commonFriendsUnion.setOldKey(coincidentElement1.getOldKey());
                commonFriendsUnion.setSecondPartKey(coincidentElement2.getOldKey());

                List<Integer> friendsWithDuplicates = new ArrayList<>(coincidentElement1.getFriends().size() +
                        coincidentElement2.getFriends().size());

                friendsWithDuplicates.addAll(coincidentElement1.getFriends());
                friendsWithDuplicates.addAll(coincidentElement2.getFriends());

                commonFriendsUnion.setFriends(findDuplicates(friendsWithDuplicates));
                commonFriends2.add(commonFriendsUnion);

            }

        });



        /*List<FriendsUnion> commonFriends = new ArrayList<>();
        for (FriendsUnion coincidentElement1: friendsUnions) {

            if (!coincidentElement1.getFriends().isEmpty()) {

                for (FriendsUnion coincidentElement2 : friendsUnions) {

                    if (!coincidentElement2.getFriends().isEmpty()) {

                        if (coincidentElement1.getOldKey().equals(coincidentElement2.getSecondPartKey()) &&
                                coincidentElement1.getSecondPartKey().equals(coincidentElement2.getOldKey())){
                            FriendsUnion commonFriendsUnion = new FriendsUnion();
                            commonFriendsUnion.setOldKey(coincidentElement1.getOldKey());
                            commonFriendsUnion.setSecondPartKey(coincidentElement1.getSecondPartKey());
                            List<Integer> friendsWithDuplicates = new ArrayList<>(coincidentElement1.getFriends().size() +
                                    coincidentElement2.getFriends().size());

                            friendsWithDuplicates.addAll(coincidentElement1.getFriends());
                            friendsWithDuplicates.addAll(coincidentElement2.getFriends());

                            commonFriendsUnion.setFriends(findDuplicates(friendsWithDuplicates));
                            commonFriends.add(commonFriendsUnion);
                        }
                    }
                }
            }

        }*/
        return commonFriends2;
    }

    public List<Integer> findDuplicates(List<Integer> listContainingDuplicates)
    {
        final List<Integer> listToReturn = new ArrayList<>();
        final Set<Integer> set1 = new HashSet<>();

        for (Integer yourInt : listContainingDuplicates)
        {
            if (!set1.add(yourInt))
            {
                listToReturn.add(yourInt);
            }
        }
        return listToReturn;
    }
}
