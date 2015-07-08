
import java.util.Map;
import java.util.HashMap;

public class WordsCounter {

    private int countWord = 0;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    void countWord(String w) {
        if (map.containsKey(w)) {
            int c = map.get(w);
            map.put(w, ++c);
        } else {
            map.put(w, 1);
        }
        countWord++;
    }

    int getCountForWord(String w) {
        return map.containsKey(w) ? map.get(w) : 0;
    }

    int getTotalCount() {
        return countWord;
    }

    void clear() {
        countWord = 0;
        map.clear();
    }

    public static void main(String[] args) {
        WordsCounter cnt = new WordsCounter();
        cnt.countWord("crazy");
        cnt.countWord("crazy");
        cnt.countWord("hedgehogs");
        cnt.countWord("all");
        cnt.countWord("over");
        cnt.countWord("the");
        cnt.countWord("crazy");
        cnt.countWord("world");

        System.out.println(cnt.getTotalCount());
        System.out.println(cnt.getCountForWord("world"));
        System.out.println(cnt.getCountForWord("hedgehogs"));
        System.out.println(cnt.getCountForWord("crazy"));
    }

}
