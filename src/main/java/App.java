import java.util.*;

public class App {
    public static void main(String[] args) {

        Parser parser = new Parser();
        List <String> loadedStrings = parser.load("src/main/resources/soc-LiveJournal1Adj.txt");
        Map<Integer, List<Integer>> str = parser.parse(loadedStrings);

        Recommendator recommendator = new Recommendator();

        List<Integer> idsToCheck = new ArrayList<>(Arrays.asList(new Integer []{942, 8941, 8942, 9019, 9020, 9990, 9992, 9993}));

        List<FriendsUnion> recommendationFriends = recommendator.getFriendsUnions(str, idsToCheck);

        Map<Integer, List<Integer>> recomendations = recommendator.getRecommendations(recommendationFriends);

        // sort
        List<Integer> userIds = new ArrayList<>(recomendations.keySet());
        Collections.sort(userIds);
        for(Integer key : userIds) {
            System.out.println(key + "\t" + recomendations.get(key));
        }
        //recomendations.keySet().forEach(e -> System.out.println(e + "\t" + recomendations.get(e).toString()));


        /*List<FriendsUnion> friendsUnionList = recommendation.getFriendsUnions(str);
        List<FriendsUnion> commonFriends = recommendation.getCommonFriends(friendsUnionList);*/
    }
}
