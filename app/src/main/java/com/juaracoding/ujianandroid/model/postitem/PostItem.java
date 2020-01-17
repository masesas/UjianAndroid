
package com.juaracoding.ujianandroid.model.postitem;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostItem implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<PostItem> CREATOR = new Creator<PostItem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostItem createFromParcel(Parcel in) {
            return new PostItem(in);
        }

        public PostItem[] newArray(int size) {
            return (new PostItem[size]);
        }

    }
    ;
    private final static long serialVersionUID = -6484348112152833347L;

    protected PostItem(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PostItem() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public PostItem(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
