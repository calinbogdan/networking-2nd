package com.calin;

import java.util.ArrayList;
import java.util.List;

public class Token {
    public String destination;
    public String source;
    private List<String> history = new ArrayList<>();
    public String message;
    public boolean isEmpty;
    public boolean messageHasArrived;

    public void addToHistory(String ip) {
        history.add(ip);
    }

    public String getHistory() {
        return String.join(" -> ", history);
    }
}
