package org.astoret;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class EmailAnalyzerTest {

    private static EmailAnalyzer analyzer;

    @BeforeAll
    static void beforeAll() {
        analyzer = new EmailAnalyzer();
    }

    @Test
    void countEmailDomains() {
        String input = "test@example.com\ntest@example.com\nanother@domain.com";
        Map<String, Integer> domainCounts = countEmailDomains(input);

        assertEquals(2, domainCounts.size());
        assertEquals(2, domainCounts.get("example.com"));
        assertEquals(1, domainCounts.get("domain.com"));
    }

    @Test
    void countEmailDomains_ExtraNewLines() {
        String input = "\ntest@example.com\n\n\ntest2@example.com\nanother@domain.com\n";
        Map<String, Integer> domainCounts = countEmailDomains(input);

        assertEquals(2, domainCounts.size());
        assertEquals(2, domainCounts.get("example.com"));
        assertEquals(1, domainCounts.get("domain.com"));
    }

    @Test
    void countEmailDomains_EmptyInput() {
        String input = "";
        Map<String, Integer> domainCounts = countEmailDomains(input);

        assertTrue(domainCounts.isEmpty());
    }

    @Test
    void countEmailDomains_NewLineOnlyInput() {
        String input = "\n\n\n";
        Map<String, Integer> domainCounts = countEmailDomains(input);

        assertTrue(domainCounts.isEmpty());
    }

    @Test
    public void getTopDomains_WithEmptyMap() {
        EmailAnalyzer emailAnalyzer = new EmailAnalyzer();

        Map<String, Integer> emptyMap = new HashMap<>();
        List<Map.Entry<String, Integer>> result = emailAnalyzer.getTopDomains(emptyMap, 5);

        assertTrue(result.isEmpty());
    }

    @Test
    public void getTopDomains_WithFewerEntriesThanTopCount() {
        EmailAnalyzer emailAnalyzer = new EmailAnalyzer();

        Map<String, Integer> domainCounts = new HashMap<>();
        domainCounts.put("domain1.com", 10);
        domainCounts.put("domain2.com", 5);
        domainCounts.put("domain3.com", 8);

        List<Map.Entry<String, Integer>> result = emailAnalyzer.getTopDomains(domainCounts, 5);

        assertEquals(3, result.size());
        assertEquals("domain1.com", result.get(0).getKey());
        assertEquals("domain3.com", result.get(1).getKey());
        assertEquals("domain2.com", result.get(2).getKey());
    }

    @Test
    public void getTopDomains_WithMoreEntriesThanTopCount() {
        EmailAnalyzer emailAnalyzer = new EmailAnalyzer();

        Map<String, Integer> domainCounts = new HashMap<>();
        domainCounts.put("domain1.com", 10);
        domainCounts.put("domain2.com", 5);
        domainCounts.put("domain3.com", 8);
        domainCounts.put("domain4.com", 15);
        domainCounts.put("domain5.com", 12);

        List<Map.Entry<String, Integer>> result = emailAnalyzer.getTopDomains(domainCounts, 3);

        assertEquals(3, result.size());
        assertEquals("domain4.com", result.get(0).getKey());
        assertEquals("domain5.com", result.get(1).getKey());
        assertEquals("domain1.com", result.get(2).getKey());
    }

    private Map<String, Integer> countEmailDomains(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        return analyzer.countEmailDomains(scanner);
    }
}