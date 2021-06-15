package Graph_Algorithms.Breadth_First_Search.WebCrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Write a java program to implement a webCrawler program.
WebCrawler uses BFS algorithm in its execution.
*/
/*
Author: Sayantan Paul
Regd. No: 1841012233
CSE-E 4th Sem 2020
*/
public class webCrawler {
    public static void main(String[] args) {
        webCrawl crawl = new webCrawl();

        String readURL = "https://github.com/belikesayantan";
        System.out.println("-----------------------------------");
        System.out.println("\t\t\tWEB CRAWLER");
        System.out.println("-----------------------------------");
        crawl.DiscoveredWebsites(readURL);
    }
}

class webCrawl {

    private Queue<String> urlQueue;
    private List<String> discoveredWebsites;

    public webCrawl() {
        this.urlQueue = new LinkedList<>();
        this.discoveredWebsites = new ArrayList<>();

    }

    public void DiscoveredWebsites(String url) {
        this.urlQueue.add(url);
        this.discoveredWebsites.add(url);

        while (!urlQueue.isEmpty()) {
            String website = this.urlQueue.remove();

            String html = readURL(website);

            String regex = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(html);

            while (matcher.find()) {
                String actualURL = matcher.group();

                if (!discoveredWebsites.contains(actualURL)) {
                    discoveredWebsites.add(actualURL);
                    System.out.println("Website found: " + actualURL);
                    urlQueue.add(actualURL);
                }
            }
        }
    }

    private String readURL(String website) {

        StringBuilder rawHTML = new StringBuilder();

        try {

            URL url = new URL(website);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                rawHTML.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rawHTML.toString();
    }
}