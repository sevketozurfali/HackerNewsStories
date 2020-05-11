package com.oztech.hackernewsstories.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Story implements Parcelable {

    @NonNull
    @SerializedName("id")
    private int id;
    @SerializedName("deleted")
    private boolean deleted;
    @SerializedName("type")
    private String type;
    @SerializedName("by")
    private String by;
    @SerializedName("time")
    private int time;
    @SerializedName("text")
    private String text;
    @SerializedName("dead")
    private boolean dead;
    @SerializedName("parent")
    private int parent;
    @SerializedName("poll")
    private int poll;
    @SerializedName("kids")
    private List<Integer> kids;
    @SerializedName("url")
    private String url;
    @SerializedName("score")
    private int score;
    @SerializedName("title")
    private String title;
    @SerializedName("parts")
    private ArrayList<Integer> parts;
    @SerializedName("descendants")
    private int descendants;

    public Story(@NonNull int id, boolean deleted, String type, String by, int time, String text, boolean dead, int parent, int poll, ArrayList<Integer> kids, String url, int score, String title, ArrayList<Integer> parts, int descendants) {
        this.id = id;
        this.deleted = deleted;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.dead = dead;
        this.parent = parent;
        this.poll = poll;
        this.kids = kids;
        this.url = url;
        this.score = score;
        this.title = title;
        this.parts = parts;
        this.descendants = descendants;
    }

    //default story item
    public Story(int id, String type, String by, int time, ArrayList<Integer> kids, String url, int score, String title, int descendants) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.time = time;
        this.kids = kids;
        this.url = url;
        this.score = score;
        this.title = title;
        this.descendants = descendants;
    }

    //comment
    public Story(int id, String type, String by, int time, String text, int parent, ArrayList<Integer> kids) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.parent = parent;
        this.kids = kids;
    }

    //ask
    public Story(int id, String type, String by, int time, String text, ArrayList<Integer> kids, String url, int score, String title, int descendants) {
        this.id = id;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.kids = kids;
        this.url = url;
        this.score = score;
        this.title = title;
        this.descendants = descendants;
    }

    public Story(int id) {
        this.id = id;
    }

    public Story() {
    }


    protected Story(Parcel in) {
        id = in.readInt();
        deleted = in.readByte() != 0;
        type = in.readString();
        by = in.readString();
        time = in.readInt();
        text = in.readString();
        dead = in.readByte() != 0;
        parent = in.readInt();
        poll = in.readInt();
        url = in.readString();
        score = in.readInt();
        title = in.readString();
        descendants = in.readInt();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByte((byte) (deleted ? 1 : 0));
        parcel.writeString(type);
        parcel.writeString(by);
        parcel.writeInt(time);
        parcel.writeString(text);
        parcel.writeByte((byte) (dead ? 1 : 0));
        parcel.writeInt(parent);
        parcel.writeInt(poll);
        parcel.writeString(url);
        parcel.writeInt(score);
        parcel.writeString(title);
        parcel.writeInt(descendants);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getPoll() {
        return poll;
    }

    public void setPoll(Integer poll) {
        this.poll = poll;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Integer> parts) {
        this.parts = parts;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }
}
