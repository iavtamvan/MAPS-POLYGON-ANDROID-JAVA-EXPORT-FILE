package com.iavariav.polylineandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_main);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
        savingFileKML();
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(-27.457, 153.040),
                        new LatLng(-33.852, 151.211),
                        new LatLng(-37.813, 144.962),
                        new LatLng(-34.928, 138.599)));
        polygon1.setTag("alpha");
        int COLOR_BLACK_ARGB = 0xff000000;
        int COLOR_WHITE_ARGB = 0xffffffff;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        polygon1.setStrokeColor(strokeColor);
        polygon1.setFillColor(fillColor);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));

        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);


    }

    private void savingFileKML() {

//        File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File root = Environment.getExternalStorageDirectory();
        Log.d("savefile", "savingFileKML: " + "\nExternal file system root: " + root);

        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File(root.getAbsolutePath() + "/SYAHDU", "myData.txt");

        try {
            dir.createNewFile();

            FileOutputStream f = new FileOutputStream(dir);
            PrintWriter pw = new PrintWriter(f);
            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
            pw.println("<Document>\n" +
                    "    <name>Paths</name>\n" +
                    "    <description>Examples of paths. Note that the tessellate tag is by default\n" +
                    "      set to 0. If you want to create tessellated lines, they must be authored\n" +
                    "      (or edited) directly in KML.</description>\n" +
                    "    <Style id=\"yellowLineGreenPoly\">");
            pw.println("<LineStyle>\n" +
                    "        <color>7f00ffff</color>\n" +
                    "        <width>4</width>\n" +
                    "      </LineStyle>\n" +
                    "      <PolyStyle>\n" +
                    "        <color>7f00ff00</color>\n" +
                    "      </PolyStyle>\n" +
                    "    </Style>\n" +
                    "    <Placemark>\n" +
                    "      <name>Absolute Extruded</name>\n" +
                    "      <description>Transparent green wall with yellow outlines</description>\n" +
                    "      <styleUrl>#yellowLineGreenPoly</styleUrl>\n" +
                    "      <LineString>\n" +
                    "        <extrude>1</extrude>\n" +
                    "        <tessellate>1</tessellate>\n" +
                    "        <altitudeMode>absolute</altitudeMode>");
            pw.println(" <coordinates>");
            pw.println(""); // disini array untuk lat long nya
            pw.println("</coordinates>\n" +
                    "      </LineString>\n" +
                    "    </Placemark>\n" +
                    "  </Document>\n" +
                    "</kml>");
            pw.println("Hello");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("savefile", "******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?" + e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("savefile", "savingFileKML: " + "\n\nFile written to " + dir);
    }
//        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root + "/COBA-MAPS");
//        myDir.mkdirs();
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String fname = "kml_" + timeStamp + ".kml";
//
//        File file = new File(myDir, fname);
//        Log.d("savefile", "savingFileKML: " + file);
////        imagePath = String.valueOf(file);
//        if (file.exists()) file.delete();
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//
//            //Menulis Data Baru dan Mengkonversinya kedalam bentuk byte
//            out.write("Latlongkuuuuuuuu auwuwuwu".getBytes());
////            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
