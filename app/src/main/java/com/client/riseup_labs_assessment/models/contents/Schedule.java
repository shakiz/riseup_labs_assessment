package com.client.riseup_labs_assessment.models.contents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "time",
        "days"
})

public class Schedule {
    @JsonProperty("time")
    private String time;
    @JsonProperty("days")
    private List<String> days = null;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("days")
    public List<String> getDays() {
        return days;
    }

    @JsonProperty("days")
    public void setDays(List<String> days) {
        this.days = days;
    }
}
