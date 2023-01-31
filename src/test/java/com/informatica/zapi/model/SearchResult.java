package com.informatica.zapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchResult {
    @JsonProperty("startAt")
    private  Integer startIndex;
    @JsonProperty("maxResults")
    private  Integer maxResults;
    @JsonProperty("total")
    private  Integer total;
    @JsonProperty("issues")
    private List<Issue> issues = new ArrayList<>(0);

    public SearchResult()
    {

    }

    public SearchResult(Integer startIndex, Integer maxResults, Integer total, List<Issue> issues) {
        this.startIndex = startIndex;
        this.maxResults = maxResults;
        this.total = total;
        this.issues = issues;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void addIssue(Issue issue) {
        this.getIssues().add(issue);
    }


    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    /**
     * @return 0-based start index of the returned issues (e.g. "3" means that 4th, 5th...maxResults issues matching given query
     *         have been returned.
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * @return maximum page size (the window to results).
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * @return total number of issues (regardless of current maxResults and startIndex) matching given criteria.
     *         Query JIRA another time with different startIndex to get subsequent issues
     */
    public Integer getTotal() {
        return total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class SearchResult {\n");

        sb.append("    startIndex: ").append(toIndentedString(startIndex)).append("\n");
        sb.append("    maxResults: ").append(toIndentedString(maxResults)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    issues: ").append(toIndentedString(issues)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SearchResult) {
            SearchResult that = (SearchResult) obj;
            return Objects.equals(this.startIndex, that.startIndex)
                    && Objects.equals(this.maxResults, that.maxResults)
                    && Objects.equals(this.total, that.total)
                    && Objects.equals(this.issues, that.issues);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, maxResults, total, issues);
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
