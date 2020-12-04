package com.client.riseup_labs_assessment.models.contents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "average"
})
public class Rating {
    @JsonProperty("average")
    private Integer average;

    @JsonProperty("average")
    public Integer getAverage() {
        return average;
    }

    @JsonProperty("average")
    public void setAverage(Integer average) {
        this.average = average;
    }
}
