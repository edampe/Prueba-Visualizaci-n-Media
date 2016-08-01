package example.com.pruebavisualizaciononline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Clase para el manejo del layout categorias
 */
public class Imagenes extends Activity {

    static ArrayList<ImagenesBin> _ImageArry = new ArrayList<>();

    static AdaptadorImagenes _Adapter;

    Context _Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);
        _Context = this;

        _ImageArry.clear();


        for (int i = 0; i < 100; i++) {
            ImagenesBin _URLs = new ImagenesBin();
            _URLs._UrlImg = "http://lorempixel.com/400/200/sports/" + (i + 1) + "/";

            _ImageArry.add(_URLs);

        }

        _Adapter = new AdaptadorImagenes(_Context, R.layout.item_imagenes, _ImageArry);

        ListView listView = (ListView) findViewById(R.id.list_view_recorrido);

        listView.setAdapter(_Adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent _Intent = new Intent(_Context, ImagenZoom.class);
                _Intent.putExtra("URL", _ImageArry.get(position)._UrlImg);
                startActivity(_Intent);
            }

        });
    }


}
