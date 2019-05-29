package com.example.i060663.githubrepositories;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    /**
     * Sample JSON response for a USGS query
     */
    private static final String SAMPLE_JSON_RESPONSE = "{\n" +
            "    \"total_count\": 54510,\n" +
            "    \"incomplete_results\": false,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"id\": 10425992,\n" +
            "            \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMDQyNTk5Mg==\",\n" +
            "            \"name\": \"css-element-queries\",\n" +
            "            \"full_name\": \"marcj/css-element-queries\",\n" +
            "            \"private\": false,\n" +
            "            \"owner\": {\n" +
            "                \"login\": \"marcj\",\n" +
            "                \"id\": 450980,\n" +
            "                \"node_id\": \"MDQ6VXNlcjQ1MDk4MA==\",\n" +
            "                \"avatar_url\": \"https://avatars0.githubusercontent.com/u/450980?v=4\",\n" +
            "                \"gravatar_id\": \"\",\n" +
            "                \"url\": \"https://api.github.com/users/marcj\",\n" +
            "                \"html_url\": \"https://github.com/marcj\",\n" +
            "                \"followers_url\": \"https://api.github.com/users/marcj/followers\",\n" +
            "                \"following_url\": \"https://api.github.com/users/marcj/following{/other_user}\",\n" +
            "                \"gists_url\": \"https://api.github.com/users/marcj/gists{/gist_id}\",\n" +
            "                \"starred_url\": \"https://api.github.com/users/marcj/starred{/owner}{/repo}\",\n" +
            "                \"subscriptions_url\": \"https://api.github.com/users/marcj/subscriptions\",\n" +
            "                \"organizations_url\": \"https://api.github.com/users/marcj/orgs\",\n" +
            "                \"repos_url\": \"https://api.github.com/users/marcj/repos\",\n" +
            "                \"events_url\": \"https://api.github.com/users/marcj/events{/privacy}\",\n" +
            "                \"received_events_url\": \"https://api.github.com/users/marcj/received_events\",\n" +
            "                \"type\": \"User\",\n" +
            "                \"site_admin\": false\n" +
            "            },\n" +
            "            \"html_url\": \"https://github.com/marcj/css-element-queries\",\n" +
            "            \"description\": \"CSS Element-Queries aka Container Queries. High-speed element dimension/media queries in valid css.\",\n" +
            "            \"fork\": false,\n" +
            "            \"url\": \"https://api.github.com/repos/marcj/css-element-queries\",\n" +
            "            \"forks_url\": \"https://api.github.com/repos/marcj/css-element-queries/forks\",\n" +
            "            \"keys_url\": \"https://api.github.com/repos/marcj/css-element-queries/keys{/key_id}\",\n" +
            "            \"collaborators_url\": \"https://api.github.com/repos/marcj/css-element-queries/collaborators{/collaborator}\",\n" +
            "            \"teams_url\": \"https://api.github.com/repos/marcj/css-element-queries/teams\",\n" +
            "            \"hooks_url\": \"https://api.github.com/repos/marcj/css-element-queries/hooks\",\n" +
            "            \"issue_events_url\": \"https://api.github.com/repos/marcj/css-element-queries/issues/events{/number}\",\n" +
            "            \"events_url\": \"https://api.github.com/repos/marcj/css-element-queries/events\",\n" +
            "            \"assignees_url\": \"https://api.github.com/repos/marcj/css-element-queries/assignees{/user}\",\n" +
            "            \"branches_url\": \"https://api.github.com/repos/marcj/css-element-queries/branches{/branch}\",\n" +
            "            \"tags_url\": \"https://api.github.com/repos/marcj/css-element-queries/tags\",\n" +
            "            \"blobs_url\": \"https://api.github.com/repos/marcj/css-element-queries/git/blobs{/sha}\",\n" +
            "            \"git_tags_url\": \"https://api.github.com/repos/marcj/css-element-queries/git/tags{/sha}\",\n" +
            "            \"git_refs_url\": \"https://api.github.com/repos/marcj/css-element-queries/git/refs{/sha}\",\n" +
            "            \"trees_url\": \"https://api.github.com/repos/marcj/css-element-queries/git/trees{/sha}\",\n" +
            "            \"statuses_url\": \"https://api.github.com/repos/marcj/css-element-queries/statuses/{sha}\",\n" +
            "            \"languages_url\": \"https://api.github.com/repos/marcj/css-element-queries/languages\",\n" +
            "            \"stargazers_url\": \"https://api.github.com/repos/marcj/css-element-queries/stargazers\",\n" +
            "            \"contributors_url\": \"https://api.github.com/repos/marcj/css-element-queries/contributors\",\n" +
            "            \"subscribers_url\": \"https://api.github.com/repos/marcj/css-element-queries/subscribers\",\n" +
            "            \"subscription_url\": \"https://api.github.com/repos/marcj/css-element-queries/subscription\",\n" +
            "            \"commits_url\": \"https://api.github.com/repos/marcj/css-element-queries/commits{/sha}\",\n" +
            "            \"git_commits_url\": \"https://api.github.com/repos/marcj/css-element-queries/git/commits{/sha}\",\n" +
            "            \"comments_url\": \"https://api.github.com/repos/marcj/css-element-queries/comments{/number}\",\n" +
            "            \"issue_comment_url\": \"https://api.github.com/repos/marcj/css-element-queries/issues/comments{/number}\",\n" +
            "            \"contents_url\": \"https://api.github.com/repos/marcj/css-element-queries/contents/{+path}\",\n" +
            "            \"compare_url\": \"https://api.github.com/repos/marcj/css-element-queries/compare/{base}...{head}\",\n" +
            "            \"merges_url\": \"https://api.github.com/repos/marcj/css-element-queries/merges\",\n" +
            "            \"archive_url\": \"https://api.github.com/repos/marcj/css-element-queries/{archive_format}{/ref}\",\n" +
            "            \"downloads_url\": \"https://api.github.com/repos/marcj/css-element-queries/downloads\",\n" +
            "            \"issues_url\": \"https://api.github.com/repos/marcj/css-element-queries/issues{/number}\",\n" +
            "            \"pulls_url\": \"https://api.github.com/repos/marcj/css-element-queries/pulls{/number}\",\n" +
            "            \"milestones_url\": \"https://api.github.com/repos/marcj/css-element-queries/milestones{/number}\",\n" +
            "            \"notifications_url\": \"https://api.github.com/repos/marcj/css-element-queries/notifications{?since,all,participating}\",\n" +
            "            \"labels_url\": \"https://api.github.com/repos/marcj/css-element-queries/labels{/name}\",\n" +
            "            \"releases_url\": \"https://api.github.com/repos/marcj/css-element-queries/releases{/id}\",\n" +
            "            \"deployments_url\": \"https://api.github.com/repos/marcj/css-element-queries/deployments\",\n" +
            "            \"created_at\": \"2013-06-01T17:48:50Z\",\n" +
            "            \"updated_at\": \"2019-05-28T10:35:19Z\",\n" +
            "            \"pushed_at\": \"2019-05-25T18:36:25Z\",\n" +
            "            \"git_url\": \"git://github.com/marcj/css-element-queries.git\",\n" +
            "            \"ssh_url\": \"git@github.com:marcj/css-element-queries.git\",\n" +
            "            \"clone_url\": \"https://github.com/marcj/css-element-queries.git\",\n" +
            "            \"svn_url\": \"https://github.com/marcj/css-element-queries\",\n" +
            "            \"homepage\": \"http://marcj.github.io/css-element-queries/\",\n" +
            "            \"size\": 3063,\n" +
            "            \"stargazers_count\": 3427,\n" +
            "            \"watchers_count\": 3427,\n" +
            "            \"language\": \"JavaScript\",\n" +
            "            \"has_issues\": true,\n" +
            "            \"has_projects\": true,\n" +
            "            \"has_downloads\": true,\n" +
            "            \"has_wiki\": false,\n" +
            "            \"has_pages\": true,\n" +
            "            \"forks_count\": 413,\n" +
            "            \"mirror_url\": null,\n" +
            "            \"archived\": false,\n" +
            "            \"disabled\": false,\n" +
            "            \"open_issues_count\": 43,\n" +
            "            \"license\": {\n" +
            "                \"key\": \"mit\",\n" +
            "                \"name\": \"MIT License\",\n" +
            "                \"spdx_id\": \"MIT\",\n" +
            "                \"url\": \"https://api.github.com/licenses/mit\",\n" +
            "                \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
            "            },\n" +
            "            \"forks\": 413,\n" +
            "            \"open_issues\": 43,\n" +
            "            \"watchers\": 3427,\n" +
            "            \"default_branch\": \"master\",\n" +
            "            \"score\": 91.18619\n" +
            "        },{\n" +
            "            \"id\": 23282991,\n" +
            "            \"node_id\": \"MDEwOlJlcG9zaXRvcnkyMzI4Mjk5MQ==\",\n" +
            "            \"name\": \"elasticsearch-sql\",\n" +
            "            \"full_name\": \"NLPchina/elasticsearch-sql\",\n" +
            "            \"private\": false,\n" +
            "            \"owner\": {\n" +
            "                \"login\": \"NLPchina\",\n" +
            "                \"id\": 7099924,\n" +
            "                \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjcwOTk5MjQ=\",\n" +
            "                \"avatar_url\": \"https://avatars0.githubusercontent.com/u/7099924?v=4\",\n" +
            "                \"gravatar_id\": \"\",\n" +
            "                \"url\": \"https://api.github.com/users/NLPchina\",\n" +
            "                \"html_url\": \"https://github.com/NLPchina\",\n" +
            "                \"followers_url\": \"https://api.github.com/users/NLPchina/followers\",\n" +
            "                \"following_url\": \"https://api.github.com/users/NLPchina/following{/other_user}\",\n" +
            "                \"gists_url\": \"https://api.github.com/users/NLPchina/gists{/gist_id}\",\n" +
            "                \"starred_url\": \"https://api.github.com/users/NLPchina/starred{/owner}{/repo}\",\n" +
            "                \"subscriptions_url\": \"https://api.github.com/users/NLPchina/subscriptions\",\n" +
            "                \"organizations_url\": \"https://api.github.com/users/NLPchina/orgs\",\n" +
            "                \"repos_url\": \"https://api.github.com/users/NLPchina/repos\",\n" +
            "                \"events_url\": \"https://api.github.com/users/NLPchina/events{/privacy}\",\n" +
            "                \"received_events_url\": \"https://api.github.com/users/NLPchina/received_events\",\n" +
            "                \"type\": \"Organization\",\n" +
            "                \"site_admin\": false\n" +
            "            },\n" +
            "            \"html_url\": \"https://github.com/NLPchina/elasticsearch-sql\",\n" +
            "            \"description\": \"Use SQL to query Elasticsearch\",\n" +
            "            \"fork\": false,\n" +
            "            \"url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql\",\n" +
            "            \"forks_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/forks\",\n" +
            "            \"keys_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/keys{/key_id}\",\n" +
            "            \"collaborators_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/collaborators{/collaborator}\",\n" +
            "            \"teams_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/teams\",\n" +
            "            \"hooks_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/hooks\",\n" +
            "            \"issue_events_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/issues/events{/number}\",\n" +
            "            \"events_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/events\",\n" +
            "            \"assignees_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/assignees{/user}\",\n" +
            "            \"branches_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/branches{/branch}\",\n" +
            "            \"tags_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/tags\",\n" +
            "            \"blobs_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/git/blobs{/sha}\",\n" +
            "            \"git_tags_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/git/tags{/sha}\",\n" +
            "            \"git_refs_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/git/refs{/sha}\",\n" +
            "            \"trees_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/git/trees{/sha}\",\n" +
            "            \"statuses_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/statuses/{sha}\",\n" +
            "            \"languages_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/languages\",\n" +
            "            \"stargazers_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/stargazers\",\n" +
            "            \"contributors_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/contributors\",\n" +
            "            \"subscribers_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/subscribers\",\n" +
            "            \"subscription_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/subscription\",\n" +
            "            \"commits_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/commits{/sha}\",\n" +
            "            \"git_commits_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/git/commits{/sha}\",\n" +
            "            \"comments_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/comments{/number}\",\n" +
            "            \"issue_comment_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/issues/comments{/number}\",\n" +
            "            \"contents_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/contents/{+path}\",\n" +
            "            \"compare_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/compare/{base}...{head}\",\n" +
            "            \"merges_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/merges\",\n" +
            "            \"archive_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/{archive_format}{/ref}\",\n" +
            "            \"downloads_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/downloads\",\n" +
            "            \"issues_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/issues{/number}\",\n" +
            "            \"pulls_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/pulls{/number}\",\n" +
            "            \"milestones_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/milestones{/number}\",\n" +
            "            \"notifications_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/notifications{?since,all,participating}\",\n" +
            "            \"labels_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/labels{/name}\",\n" +
            "            \"releases_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/releases{/id}\",\n" +
            "            \"deployments_url\": \"https://api.github.com/repos/NLPchina/elasticsearch-sql/deployments\",\n" +
            "            \"created_at\": \"2014-08-24T14:26:31Z\",\n" +
            "            \"updated_at\": \"2019-05-28T09:46:40Z\",\n" +
            "            \"pushed_at\": \"2019-05-12T01:03:29Z\",\n" +
            "            \"git_url\": \"git://github.com/NLPchina/elasticsearch-sql.git\",\n" +
            "            \"ssh_url\": \"git@github.com:NLPchina/elasticsearch-sql.git\",\n" +
            "            \"clone_url\": \"https://github.com/NLPchina/elasticsearch-sql.git\",\n" +
            "            \"svn_url\": \"https://github.com/NLPchina/elasticsearch-sql\",\n" +
            "            \"homepage\": \"\",\n" +
            "            \"size\": 5844,\n" +
            "            \"stargazers_count\": 5015,\n" +
            "            \"watchers_count\": 5015,\n" +
            "            \"language\": \"Java\",\n" +
            "            \"has_issues\": true,\n" +
            "            \"has_projects\": true,\n" +
            "            \"has_downloads\": true,\n" +
            "            \"has_wiki\": true,\n" +
            "            \"has_pages\": false,\n" +
            "            \"forks_count\": 1163,\n" +
            "            \"mirror_url\": null,\n" +
            "            \"archived\": false,\n" +
            "            \"disabled\": false,\n" +
            "            \"open_issues_count\": 195,\n" +
            "            \"license\": {\n" +
            "                \"key\": \"apache-2.0\",\n" +
            "                \"name\": \"Apache License 2.0\",\n" +
            "                \"spdx_id\": \"Apache-2.0\",\n" +
            "                \"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
            "                \"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
            "            },\n" +
            "            \"forks\": 1163,\n" +
            "            \"open_issues\": 195,\n" +
            "            \"watchers\": 5015,\n" +
            "            \"default_branch\": \"master\",\n" +
            "            \"score\": 90.65765\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {

    }

    public static ArrayList<Repository> extractEarthquakes() {

        ArrayList<Repository> repositories = new ArrayList<>();

        try {

            // Create a JSONObject from the SAMPLE_JSON_RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray repositoryArray = baseJsonResponse.getJSONArray("items");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < repositoryArray.length(); i++) {

                JSONObject currentRepository = repositoryArray.getJSONObject(i);

                String name = currentRepository.getString("name");
                String fullName = currentRepository.getString("full_name");
                int size = currentRepository.getInt("size");
                int stargazersCount = currentRepository.getInt("stargazers_count");
                int forksCount = currentRepository.getInt("forks");
                String contributorsURL = currentRepository.getString("contributors_url");

                Repository repository = new Repository(name, fullName, size, stargazersCount, forksCount, contributorsURL);

                // Add the new {@link Earthquake} to the list of earthquakes.
                repositories.add(repository);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the repositories JSON results", e);
        }

        return repositories;

    }
}

