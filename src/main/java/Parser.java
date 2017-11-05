import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    /*Method load - loading file from txt-document. Create List with
    Strings elements and adding file to list line by line.*/

    public List<String> load(String filePath) {

        List<String> socialNetwork = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                socialNetwork.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socialNetwork;
    }
    /*
     * Method parse - parsing List with String elements to Map with Integer as Key (User ID) and List Integer as Value
     * (User`s friends ID`s). In loop splitting by tabulation - get Key. After that remaining splitting by coma -
     * get Values for List Integer.
     * */
    public Map<Integer, List<Integer>> parse(List<String> loadedStrings) {

        Map<Integer, List<Integer>> socialNetworkMap = new HashMap<>(loadedStrings.size());
        for (String element : loadedStrings) {
            String splittedString[] = element.split("\t");
            String splittedFriends [] =  splittedString.length > 1 ? splittedString[1].split(",") : new String[]{};

            //List<Integer> friends = new ArrayList<>(splittedFriends.length);

            List<Integer> friendsJava8 =
                    new ArrayList<>(Arrays.asList(splittedFriends)).stream()
                            .map(Integer::parseInt)
                                .collect(Collectors.toList());

            /*for (String friend:splittedFriends) {
                friends.add(Integer.parseInt(friend));
            }
*/

            socialNetworkMap.put(
                    Integer.parseInt(splittedString[0]), friendsJava8 );
        }

        //sout(socialNetworkMap);
        return socialNetworkMap;
    }

    public void sout (Map<Integer, List<Integer>> socialNetworkMap){

        for (Integer key:socialNetworkMap.keySet()) {
            System.out.println(key.toString() + " " + socialNetworkMap.get(key));
        }
    }

}
