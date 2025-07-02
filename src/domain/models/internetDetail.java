package domain.models;

import java.util.ArrayList;
import java.util.List;

public class internetDetail {
    private String url;
    private final List<String> history;

    public internetDetail() {
        this.history = new ArrayList<>();
    }

    public void setUrl(String url) {
        this.url = url;
        history.add(url);
    }

    public String getUrl(){
        return url;
    }

    public boolean hasNext() {
        var index = getIndexUrl();
        return index >= 0 && index < history.size() - 1;
    }

    public boolean hasBefore(){
        var index = getIndexUrl();
        return index > 0 && index <= history.size() - 1;
    }

    public void next(){
        var index = getIndexUrl();
        url = history.get(index + 1);
    }

    public void before(){
        var index = getIndexUrl();
        url = history.get(index - 1);
    }

    private int getIndexUrl(){
        var site = history.stream()
                .filter(x -> x.equalsIgnoreCase(url))
                .findFirst();

        return site.map(history::indexOf).orElse(-1);
    }
}
