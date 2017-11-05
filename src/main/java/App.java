import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Parser parser = new Parser();
        Recommendation recommendation= new Recommendation();
        List <String> loadedStrings = parser.load("src/main/resources/soc-LiveJournal1Adj.txt");
        Map<Integer, List<Integer>> str = parser.parse(loadedStrings);
        List<FriendsUnion> friendsUnionList = recommendation.getFriendsUnions(str);
        List<FriendsUnion> commonFriends = recommendation.getCommonFriends(friendsUnionList);
    }
}
