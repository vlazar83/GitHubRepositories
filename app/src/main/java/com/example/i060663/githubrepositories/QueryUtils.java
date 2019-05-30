package com.example.i060663.githubrepositories;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static final String SAMPLE_JSON_RESPONSE = "[\n" +
            "    {\n" +
            "        \"login\": \"kpu\",\n" +
            "        \"id\": 247512,\n" +
            "        \"node_id\": \"MDQ6VXNlcjI0NzUxMg==\",\n" +
            "        \"avatar_url\": \"https://avatars1.githubusercontent.com/u/247512?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/kpu\",\n" +
            "        \"html_url\": \"https://github.com/kpu\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/kpu/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/kpu/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/kpu/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/kpu/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/kpu/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/kpu/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/kpu/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/kpu/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/kpu/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 1882\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"skystrife\",\n" +
            "        \"id\": 1378336,\n" +
            "        \"node_id\": \"MDQ6VXNlcjEzNzgzMzY=\",\n" +
            "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1378336?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/skystrife\",\n" +
            "        \"html_url\": \"https://github.com/skystrife\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/skystrife/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/skystrife/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/skystrife/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/skystrife/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/skystrife/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/skystrife/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/skystrife/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/skystrife/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/skystrife/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 39\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"dowobeha\",\n" +
            "        \"id\": 331829,\n" +
            "        \"node_id\": \"MDQ6VXNlcjMzMTgyOQ==\",\n" +
            "        \"avatar_url\": \"https://avatars2.githubusercontent.com/u/331829?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/dowobeha\",\n" +
            "        \"html_url\": \"https://github.com/dowobeha\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/dowobeha/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/dowobeha/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/dowobeha/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/dowobeha/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/dowobeha/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/dowobeha/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/dowobeha/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/dowobeha/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/dowobeha/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 37\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"emjotde\",\n" +
            "        \"id\": 1083125,\n" +
            "        \"node_id\": \"MDQ6VXNlcjEwODMxMjU=\",\n" +
            "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1083125?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/emjotde\",\n" +
            "        \"html_url\": \"https://github.com/emjotde\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/emjotde/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/emjotde/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/emjotde/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/emjotde/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/emjotde/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/emjotde/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/emjotde/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/emjotde/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/emjotde/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 32\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"jhclark\",\n" +
            "        \"id\": 259694,\n" +
            "        \"node_id\": \"MDQ6VXNlcjI1OTY5NA==\",\n" +
            "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/259694?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/jhclark\",\n" +
            "        \"html_url\": \"https://github.com/jhclark\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/jhclark/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/jhclark/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/jhclark/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/jhclark/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/jhclark/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/jhclark/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/jhclark/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/jhclark/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/jhclark/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 21\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"vchahun\",\n" +
            "        \"id\": 470022,\n" +
            "        \"node_id\": \"MDQ6VXNlcjQ3MDAyMg==\",\n" +
            "        \"avatar_url\": \"https://avatars3.githubusercontent.com/u/470022?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/vchahun\",\n" +
            "        \"html_url\": \"https://github.com/vchahun\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/vchahun/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/vchahun/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/vchahun/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/vchahun/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/vchahun/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/vchahun/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/vchahun/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/vchahun/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/vchahun/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 14\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"sandello\",\n" +
            "        \"id\": 14292,\n" +
            "        \"node_id\": \"MDQ6VXNlcjE0Mjky\",\n" +
            "        \"avatar_url\": \"https://avatars2.githubusercontent.com/u/14292?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/sandello\",\n" +
            "        \"html_url\": \"https://github.com/sandello\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/sandello/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/sandello/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/sandello/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/sandello/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/sandello/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/sandello/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/sandello/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/sandello/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/sandello/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 13\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"hieuhoang\",\n" +
            "        \"id\": 691732,\n" +
            "        \"node_id\": \"MDQ6VXNlcjY5MTczMg==\",\n" +
            "        \"avatar_url\": \"https://avatars1.githubusercontent.com/u/691732?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/hieuhoang\",\n" +
            "        \"html_url\": \"https://github.com/hieuhoang\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/hieuhoang/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/hieuhoang/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/hieuhoang/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/hieuhoang/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/hieuhoang/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/hieuhoang/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/hieuhoang/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/hieuhoang/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/hieuhoang/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 6\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"jsedoc\",\n" +
            "        \"id\": 5974598,\n" +
            "        \"node_id\": \"MDQ6VXNlcjU5NzQ1OTg=\",\n" +
            "        \"avatar_url\": \"https://avatars2.githubusercontent.com/u/5974598?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/jsedoc\",\n" +
            "        \"html_url\": \"https://github.com/jsedoc\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/jsedoc/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/jsedoc/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/jsedoc/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/jsedoc/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/jsedoc/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/jsedoc/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/jsedoc/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/jsedoc/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/jsedoc/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 6\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"elmg\",\n" +
            "        \"id\": 14294078,\n" +
            "        \"node_id\": \"MDQ6VXNlcjE0Mjk0MDc4\",\n" +
            "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/14294078?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/elmg\",\n" +
            "        \"html_url\": \"https://github.com/elmg\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/elmg/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/elmg/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/elmg/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/elmg/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/elmg/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/elmg/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/elmg/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/elmg/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/elmg/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 6\n" +
            "    },\n" +
            "    {\n" +
            "        \"login\": \"jozef-mokry\",\n" +
            "        \"id\": 1828285,\n" +
            "        \"node_id\": \"MDQ6VXNlcjE4MjgyODU=\",\n" +
            "        \"avatar_url\": \"https://avatars0.githubusercontent.com/u/1828285?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/jozef-mokry\",\n" +
            "        \"html_url\": \"https://github.com/jozef-mokry\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/jozef-mokry/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/jozef-mokry/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/jozef-mokry/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/jozef-mokry/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/jozef-mokry/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/jozef-mokry/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/jozef-mokry/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/jozef-mokry/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/jozef-mokry/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false,\n" +
            "        \"contributions\": 5\n" +
            "    }" +
            "]";

    private QueryUtils() {

    }

    public static List<Repository> fetchRepositoryData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Repository> repositories = extractRepositories(jsonResponse);

        return repositories;
    }

    public static ArrayList<Contributor> extractContributors(String jsonResponse) {

        ArrayList<Contributor> contributors = new ArrayList<>();

        try {

            JSONArray contributorsArray = new JSONArray(jsonResponse);

            for (int i = 0; i < contributorsArray.length(); i++) {

                JSONObject currentContributor = contributorsArray.getJSONObject(i);

                String login = currentContributor.getString("login");
                String avatarURL = currentContributor.getString("avatar_url");

                Contributor contributor = new Contributor(login, avatarURL);

                contributors.add(contributor);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the contributors JSON results", e);
        }

        return contributors;

    }

    public static ArrayList<Repository> extractRepositories(String jsonResponse) {

        ArrayList<Repository> repositories = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            JSONArray repositoryArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < repositoryArray.length(); i++) {

                JSONObject currentRepository = repositoryArray.getJSONObject(i);

                String name = currentRepository.getString("name");
                String fullName = currentRepository.getString("full_name");
                int size = currentRepository.getInt("size");
                int stargazersCount = currentRepository.getInt("stargazers_count");
                int forksCount = currentRepository.getInt("forks");
                String contributorsURL = currentRepository.getString("contributors_url");

                Repository repository = new Repository(name, fullName, size, stargazersCount, forksCount, contributorsURL);

                repositories.add(repository);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the repositories JSON results", e);
        }

        return repositories;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the repository JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}

