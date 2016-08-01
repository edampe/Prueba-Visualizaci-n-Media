package example.com.pruebavisualizaciononline;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by desarrollo4 on 21/07/16.
 */
public class ImagenZoom extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_zoom);

        Bundle bundle = getIntent().getExtras();

        String _URL = bundle.getString("URL");

        ImageView _Imagen = (ImageView) findViewById(R.id.img);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });

        builder.build()
                .load(_URL)
                .placeholder(R.drawable.placeholder_imagen)
                .error(R.drawable.no_img)
                .into(_Imagen);
    }
}
