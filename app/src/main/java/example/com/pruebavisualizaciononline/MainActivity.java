package example.com.pruebavisualizaciononline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button _BtnImagen = (Button) findViewById(R.id.btn_img);
        _BtnImagen.setOnClickListener(this);
        Button _BtnAudio = (Button) findViewById(R.id.btn_audio);
        _BtnAudio.setOnClickListener(this);
        Button _BtnVideo = (Button) findViewById(R.id.btn_video);
        _BtnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_img:
                startActivity(new Intent(getBaseContext(), Imagenes.class));
                break;
            case R.id.btn_audio:
                startActivity(new Intent(getBaseContext(), Audios.class));
                break;
            case R.id.btn_video:
                startActivity(new Intent(getBaseContext(), Videos.class));
                break;

        }

    }
}
