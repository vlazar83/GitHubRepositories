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

    public static List<Contributor> fetchContributorData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Contributor> contributors = extractContributors(jsonResponse);

        return contributors;
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

