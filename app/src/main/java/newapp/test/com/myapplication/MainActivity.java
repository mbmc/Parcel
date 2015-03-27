package newapp.test.com.myapplication;

import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Model model = new Model();
        model.value = 15;
        List<MyInterface> content = new ArrayList<>();
        content.add(new TypeA());
        TypeA typeA = new TypeA();
        typeA.name = "A";
        content.add(typeA);
        content.add(new TypeA());
        content.add(new TypeA());
        model.content = content;

        Parcelable wrapped = Parcels.wrap(model);

        Model unwrapped = Parcels.unwrap(wrapped);
        if (unwrapped.value != model.value) {
            throw new RuntimeException("Not same value");
        }
        if (unwrapped.content.size() != model.content.size()) {
            throw new RuntimeException("Not same size");
        }
        if (!((TypeA) unwrapped.content.get(1)).name.equals(((TypeA) model.content.get(1)).name)) {
            throw new RuntimeException("Not same name");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
