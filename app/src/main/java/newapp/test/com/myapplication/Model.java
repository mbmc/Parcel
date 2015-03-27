package newapp.test.com.myapplication;

import android.os.Parcel;

import org.parceler.ParcelConverter;
import org.parceler.ParcelPropertyConverter;
import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

import java.util.ArrayList;
import java.util.List;


@org.parceler.Parcel
public class Model {

    public int value;
    @ParcelPropertyConverter(ItemListParcelConverter.class)
    //@ParcelPropertyConverter(MyInterfaceParcelConverter.class)
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

    public static final class MyInterfaceParcelConverter implements ParcelConverter<List<MyInterface>> {
        public void toParcel(List<MyInterface> input, Parcel parcel) {
            parcel.writeList(input);
        }

        public List<MyInterface> fromParcel(Parcel parcel) {
            return parcel.readArrayList(MyInterface.class.getClassLoader());
        }
    }

}
