package com.client.riseup_labs_assessment.models.contents;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "id",
        "url",
        "name",
        "type",
        "language",
        "genres",
        "status",
        "runtime",
        "premiered",
        "schedule",
        "rating",
        "weight",
        "network"
})

public class Content implements Parcelable {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("language")
    private String language;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("runtime")
    private Integer runtime;
    @JsonProperty("premiered")
    private String premiered;
    @JsonProperty("schedule")
    private Schedule schedule;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("network")
    private Network network;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    @JsonProperty("genres")
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("runtime")
    public Integer getRuntime() {
        return runtime;
    }

    @JsonProperty("runtime")
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("premiered")
    public String getPremiered() {
        return premiered;
    }

    @JsonProperty("premiered")
    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    @JsonProperty("schedule")
    public Schedule getSchedule() {
        return schedule;
    }

    @JsonProperty("schedule")
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @JsonProperty("rating")
    public Rating getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @JsonProperty("weight")
    public Integer getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @JsonProperty("network")
    public Network getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(Network network) {
        this.network = network;
    }


    protected Content(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        url = in.readString();
        name = in.readString();
        type = in.readString();
        language = in.readString();
        genres = in.createStringArrayList();
        status = in.readString();
        if (in.readByte() == 0) {
            runtime = null;
        } else {
            runtime = in.readInt();
        }
        premiered = in.readString();
        if (in.readByte() == 0) {
            weight = null;
        } else {
            weight = in.readInt();
        }
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(language);
        dest.writeStringList(genres);
        dest.writeString(status);
        if (runtime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(runtime);
        }
        dest.writeString(premiered);
        if (weight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(weight);
        }
    }
}
