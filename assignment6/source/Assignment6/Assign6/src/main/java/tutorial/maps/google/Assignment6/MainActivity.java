package tutorial.maps.google.Assignment6;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private double Latitude, Longitude;
    private ImageView user ;
    private Bitmap p;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getActionBar().setTitle("Home page");
        LocationManager ucl = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener uclListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {}

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        ucl.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, uclListener);
        Latitude = ucl
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLatitude();
        Longitude = ucl
                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                .getLongitude();


        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                Intent send=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);

            }
        });
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        TextView Address = (TextView)findViewById(R.id.address);
        try {
            List<Address> addresses = geocoder.getFromLocation(Latitude, Longitude, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(" ");
                }
                Address.setText(strReturnedAddress.toString());
            }
            else{

            }
        } catch (IOException e) {

            e.printStackTrace();

        }


        user = (ImageView) findViewById(R.id.img);

        user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            p = (Bitmap) data.getExtras().get("data");
            user.setImageBitmap(p);
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri img = data.getData();
            try {
                p = BitmapFactory.decodeStream(getContentResolver().openInputStream(img));

            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setImageBitmap(p);
        }
    }
}

