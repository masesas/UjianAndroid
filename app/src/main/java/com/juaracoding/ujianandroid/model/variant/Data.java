
package com.juaracoding.ujianandroid.model.variant;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("variant")
    @Expose
    private List<Variant> variant = null;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2083090420772695363L;

    protected Data(Parcel in) {
        in.readList(this.variant, (com.juaracoding.ujianandroid.model.variant.Variant.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param variant
     */
    public Data(List<Variant> variant) {
        super();
        this.variant = variant;
    }

    public List<Variant> getVariant() {
        return variant;
    }

    public void setVariant(List<Variant> variant) {
        this.variant = variant;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(variant);
    }

    public int describeContents() {
        return  0;
    }

}
