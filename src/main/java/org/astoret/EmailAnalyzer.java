package org.astoret;

import java.util.*;

/**
 * The EmailAnalyzer class provides methods to analyze email addresses, including counting the occurrences of domains
 * and retrieving the top domains based on their counts.
 */
public class EmailAnalyzer {

    /**
     * Counts the occurrences of each domain in the given Scanner input.
     *
     * @param scanner The Scanner object providing the input containing email addresses.
     * @return A Map representing the counts of each domain.
     */
    public Map<String, Integer> countEmailDomains(Scanner scanner) {
        Map<String, Integer> domainCounts = new HashMap<>();
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            if(!email.isEmpty()) {
                String domain = extractDomain(email);
                domainCounts.put(domain, domainCounts.getOrDefault(domain, 0) + 1);
            }
        }
        return domainCounts;
    }

    /**
     * Retrieves the top domains based on their counts from the provided domainCounts map.
     *
     * @param domainCounts A Map representing the counts of each domain.
     * @param topCount     The number of top domains to retrieve.
     * @return A List of Map.Entry objects representing the top domains and their counts.
     */
    public List<Map.Entry<String, Integer>> getTopDomains(Map<String, Integer> domainCounts, int topCount) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        for (Map.Entry<String, Integer> entry : domainCounts.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > topCount) {
                minHeap.poll();
            }
        }

        List<Map.Entry<String, Integer>> topEntries = new ArrayList<>(minHeap.size());
        while (!minHeap.isEmpty()) {
            topEntries.add(minHeap.poll());
        }

        Collections.reverse(topEntries); // Reverse the list to get the top entries in descending order
        return topEntries;
    }

    /**
     * Extracts the domain from the given email address.
     *
     * @param email The email address from which to extract the domain.
     * @return The extracted domain.
     */
    private String extractDomain(String email) {
        // Simple domain extraction assuming email is in a valid format
        return email.substring(email.lastIndexOf('@') + 1);
    }
}

