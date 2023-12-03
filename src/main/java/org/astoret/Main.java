package org.astoret;

import java.util.*;

public class Main {

    /**
     * The default number of top domains to be printed.
     */
    public static final int DEFAULT_TOP_COUNT = 10;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EmailAnalyzer emailAnalyzer = new EmailAnalyzer();
            Map<String, Integer> domainCounts = emailAnalyzer.countEmailDomains(scanner);
            List<Map.Entry<String, Integer>> entries = emailAnalyzer.getTopDomains(domainCounts, DEFAULT_TOP_COUNT);

            for (Map.Entry<String, Integer> entry : entries) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
