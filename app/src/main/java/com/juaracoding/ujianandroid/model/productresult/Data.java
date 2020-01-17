
package com.juaracoding.ujianandroid.model.productresult;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("product")
    @Expose
    private List<Product> product = null;
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
    private final static long serialVersionUID = -7009929246150543886L;

    protected Data(Parcel in) {
        in.readList(this.product, (com.juaracoding.ujianandroid.model.productresult.Product.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param product
     */
    public Data(List<Product> product) {
        super();
        this.product = product;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(product);
    }

    public int describeContents() {
        return  0;
    }

}
