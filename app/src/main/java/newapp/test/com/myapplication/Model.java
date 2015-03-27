package newapp.test.com.myapplication;

import android.os.Parcel;

import org.parceler.ParcelPropertyConverter;
import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

import java.util.ArrayList;
import java.util.List;


@org.parceler.Parcel
public class Model {

    public int value;
    @ParcelPropertyConverter(ItemListParcelConverter.class)
    public List<MyInterface> content = new ArrayList<>();


    public static class ItemListParcelConverter extends ArrayListParcelConverter<MyInterface> {
        @Override
        public void itemToParcel(MyInterface input, Parcel parcel) {
            parcel.writeParcelable(Parcels.wrap(input), 0);
        }

        @Override
        public MyInterface itemFromParcel(Parcel parcel) {
            return Parcels.unwrap(parcel.readParcelable(MyInterface.class.getClassLoader()));
        }
    }

}
