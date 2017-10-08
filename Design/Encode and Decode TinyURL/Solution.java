//copyright@ https://leetcode.com/problems/encode-and-decode-tinyurl/solution/

public class Codec {
    String alph = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Map<String, String> map = new HashMap<>();
    Random rand = new Random();
    String key = getRand();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = getRand();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alph.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }
    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}
