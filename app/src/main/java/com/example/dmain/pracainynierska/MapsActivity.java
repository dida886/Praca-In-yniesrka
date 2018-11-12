package com.example.dmain.pracainynierska;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.PolyUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * An activity that displays a Google map with polylines to represent paths or routes,
 * and polygons to represent areas.
 */
public class MapsActivity extends AppCompatActivity
        implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        GoogleMap.OnPolygonClickListener {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect;
    private ImageView ivImage;

    private String userChoosenTask;
    private GoogleMap mMap;
    private Polygon mPolygon;



    public String address ;
    public String city ;
    public String state ;
    public String country ;
    public String postalCode;
    public String knownName;




    private Marker marker;
    List<Polygon> polyList = new ArrayList<>();

    Geocoder geocoder;
    List<Address> addresses;
    Dialog myDialog;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDialog = new Dialog(this);
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        // Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {


        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        List<LatLng> newPolygon = new ArrayList<>();
        newPolygon.add(	new LatLng(50.8966097, 18.3996026)	);
        newPolygon.add(	new LatLng(50.8965291, 18.3995749)	);
        newPolygon.add(	new LatLng(50.8965258, 18.3995006)	);
        newPolygon.add(	new LatLng(50.8951388, 18.3986084)	);
        newPolygon.add(	new LatLng(50.8935325, 18.3975161)	);
        newPolygon.add(	new LatLng(50.8932742, 18.3975471)	);
        newPolygon.add(	new LatLng(50.8933449, 18.3974589)	);
        newPolygon.add(	new LatLng(50.893287, 18.3973849)	);
        newPolygon.add(	new LatLng(50.8932647, 18.3973563)	);
        newPolygon.add(	new LatLng(50.8933051, 18.3973028)	);
        newPolygon.add(	new LatLng(50.8930454, 18.3971031)	);
        newPolygon.add(	new LatLng(50.8925877, 18.3967547)	);
        newPolygon.add(	new LatLng(50.8918294, 18.3963983)	);
        newPolygon.add(	new LatLng(50.8895885, 18.3955239)	);
        newPolygon.add(	new LatLng(50.8887611, 18.3957971)	);
        newPolygon.add(	new LatLng(50.8876233, 18.3953052)	);
        newPolygon.add(	new LatLng(50.8878991, 18.3944854)	);
        newPolygon.add(	new LatLng(50.8875544, 18.3930098)	);
        newPolygon.add(	new LatLng(50.8845638, 18.39457)	);
        newPolygon.add(	new LatLng(50.8821065, 18.3958518)	);
        newPolygon.add(	new LatLng(50.8815203, 18.3945401)	);
        newPolygon.add(	new LatLng(50.8804513, 18.3960157)	);
        newPolygon.add(	new LatLng(50.8796237, 18.396289)	);
        newPolygon.add(	new LatLng(50.8788306, 18.3983112)	);
        newPolygon.add(	new LatLng(50.8782298, 18.3976763)	);
        newPolygon.add(	new LatLng(50.878003, 18.3974367)	);
        newPolygon.add(	new LatLng(50.8779492, 18.3972497)	);
        newPolygon.add(	new LatLng(50.8777387, 18.3965201)	);
        newPolygon.add(	new LatLng(50.8770659, 18.395215)	);
        newPolygon.add(	new LatLng(50.8770049, 18.3947714)	);
        newPolygon.add(	new LatLng(50.876894, 18.3936376)	);
        newPolygon.add(	new LatLng(50.8764541, 18.3886361)	);
        newPolygon.add(	new LatLng(50.8764216, 18.3882852)	);
        newPolygon.add(	new LatLng(50.8763015, 18.387422)	);
        newPolygon.add(	new LatLng(50.8751751, 18.3868886)	);
        newPolygon.add(	new LatLng(50.8746994, 18.3862016)	);
        newPolygon.add(	new LatLng(50.874509, 18.3860339)	);
        newPolygon.add(	new LatLng(50.8742194, 18.3856903)	);
        newPolygon.add(	new LatLng(50.874134, 18.3856197)	);
        newPolygon.add(	new LatLng(50.8739732, 18.3855278)	);
        newPolygon.add(	new LatLng(50.8731384, 18.3850794)	);
        newPolygon.add(	new LatLng(50.8721044, 18.3847099)	);
        newPolygon.add(	new LatLng(50.8716378, 18.3849276)	);
        newPolygon.add(	new LatLng(50.8671044, 18.3872711)	);
        newPolygon.add(	new LatLng(50.8623786, 18.3890747)	);
        newPolygon.add(	new LatLng(50.863034, 18.3965076)	);
        newPolygon.add(	new LatLng(50.863448, 18.3998962)	);
        newPolygon.add(	new LatLng(50.8633445, 18.4011532)	);
        newPolygon.add(	new LatLng(50.8623786, 18.4013718)	);
        newPolygon.add(	new LatLng(50.8599293, 18.4013172)	);
        newPolygon.add(	new LatLng(50.8587218, 18.4007706)	);
        newPolygon.add(	new LatLng(50.8587218, 18.4038312)	);
        newPolygon.add(	new LatLng(50.8574108, 18.4027928)	);
        newPolygon.add(	new LatLng(50.8571369, 18.4027796)	);
        newPolygon.add(	new LatLng(50.8571979, 18.4038423)	);
        newPolygon.add(	new LatLng(50.857698, 18.404538)	);
        newPolygon.add(	new LatLng(50.8569661, 18.4053688)	);
        newPolygon.add(	new LatLng(50.8567031, 18.4059003)	);
        newPolygon.add(	new LatLng(50.8566124, 18.4060838)	);
        newPolygon.add(	new LatLng(50.8563806, 18.4068181)	);
        newPolygon.add(	new LatLng(50.8567588, 18.4072625)	);
        newPolygon.add(	new LatLng(50.8571857, 18.4075717)	);
        newPolygon.add(	new LatLng(50.8568563, 18.4083446)	);
        newPolygon.add(	new LatLng(50.8563599, 18.410372)	);
        newPolygon.add(	new LatLng(50.8562746, 18.4108025)	);
        newPolygon.add(	new LatLng(50.8561752, 18.4112087)	);
        newPolygon.add(	new LatLng(50.8561417, 18.4112624)	);
        newPolygon.add(	new LatLng(50.8559465, 18.4113397)	);
        newPolygon.add(	new LatLng(50.8547267, 18.4186824)	);
        newPolygon.add(	new LatLng(50.8542022, 18.4204467)	);
        newPolygon.add(	new LatLng(50.8524349, 18.4266033)	);
        newPolygon.add(	new LatLng(50.8503301, 18.4351293)	);
        newPolygon.add(	new LatLng(50.8524349, 18.436605)	);
        newPolygon.add(	new LatLng(50.8550227, 18.4372608)	);
        newPolygon.add(	new LatLng(50.8586797, 18.4381353)	);
        newPolygon.add(	new LatLng(50.8609911, 18.4379167)	);
        newPolygon.add(	new LatLng(50.8636129, 18.4373701)	);
        newPolygon.add(	new LatLng(50.8657516, 18.436441)	);
        newPolygon.add(	new LatLng(50.8671658, 18.4362224)	);
        newPolygon.add(	new LatLng(50.8666484, 18.439283)	);
        newPolygon.add(	new LatLng(50.8648202, 18.4398842)	);
        newPolygon.add(	new LatLng(50.8652687, 18.4408133)	);
        newPolygon.add(	new LatLng(50.868649, 18.440704)	);
        newPolygon.add(	new LatLng(50.8718222, 18.4413052)	);
        newPolygon.add(	new LatLng(50.8726844, 18.4396109)	);
        newPolygon.add(	new LatLng(50.872786, 18.4396469)	);
        newPolygon.add(	new LatLng(50.8763746, 18.4409226)	);
        newPolygon.add(	new LatLng(50.8767641, 18.4392474)	);
        newPolygon.add(	new LatLng(50.8768427, 18.4389094)	);
        newPolygon.add(	new LatLng(50.8768574, 18.4388458)	);
        newPolygon.add(	new LatLng(50.8777885, 18.437862)	);
        newPolygon.add(	new LatLng(50.8778926, 18.4378558)	);
        newPolygon.add(	new LatLng(50.8784064, 18.4378168)	);
        newPolygon.add(	new LatLng(50.8786118, 18.437813)	);
        newPolygon.add(	new LatLng(50.8787196, 18.4378074)	);
        newPolygon.add(	new LatLng(50.8787296, 18.4378106)	);
        newPolygon.add(	new LatLng(50.8788681, 18.4378125)	);
        newPolygon.add(	new LatLng(50.8789057, 18.4378129)	);
        newPolygon.add(	new LatLng(50.8789171, 18.4378132)	);
        newPolygon.add(	new LatLng(50.8788045, 18.4374619)	);
        newPolygon.add(	new LatLng(50.8787467, 18.4372474)	);
        newPolygon.add(	new LatLng(50.8788415, 18.4372017)	);
        newPolygon.add(	new LatLng(50.8787805, 18.4367573)	);
        newPolygon.add(	new LatLng(50.8791611, 18.4367383)	);
        newPolygon.add(	new LatLng(50.8792804, 18.436738)	);
        newPolygon.add(	new LatLng(50.8797803, 18.4364095)	);
        newPolygon.add(	new LatLng(50.8803411, 18.4356945)	);
        newPolygon.add(	new LatLng(50.8803495, 18.435671)	);
        newPolygon.add(	new LatLng(50.8805118, 18.4346704)	);
        newPolygon.add(	new LatLng(50.880841, 18.4350955)	);
        newPolygon.add(	new LatLng(50.882182, 18.435424)	);
        newPolygon.add(	new LatLng(50.8828052, 18.4356602)	);
        newPolygon.add(	new LatLng(50.8833036, 18.4358491)	);
        newPolygon.add(	new LatLng(50.8883819, 18.436696)	);
        newPolygon.add(	new LatLng(50.8907653, 18.436657)	);
        newPolygon.add(	new LatLng(50.8931112, 18.4366187)	);
        newPolygon.add(	new LatLng(50.894135, 18.4365028)	);
        newPolygon.add(	new LatLng(50.8941107, 18.4340681)	);
        newPolygon.add(	new LatLng(50.8945738, 18.4317107)	);
        newPolygon.add(	new LatLng(50.8952076, 18.4319425)	);
        newPolygon.add(	new LatLng(50.8955245, 18.4303194)	);
        newPolygon.add(	new LatLng(50.9015692, 18.432329)	);
        newPolygon.add(	new LatLng(50.9005212, 18.4247544)	);
        newPolygon.add(	new LatLng(50.8979944, 18.403468)	);
        newPolygon.add(	new LatLng(50.8966097, 18.3996026)	);

        //set up the points in the Polygon.......
        Polygon p = mMap.addPolygon(new PolygonOptions()
                .addAll(newPolygon)
                .strokeColor(Color.RED));
        polyList.add(p);



        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                String s = point.toString();
                String[] latLng = s.substring(10, s.length() - 1).split(",");
                String sLat = latLng[0];
                String sLng = latLng[1];
                double Lat =  Double.parseDouble(sLat);
                double Lng =  Double.parseDouble(sLng);

                for (Polygon pObj : polyList) {

                    //find Polygon user tapped inside of
                    if (PolyUtil.containsLocation(point, pObj.getPoints(), false)) {
                        //first case, no Marker
                        if (marker == null) {

                            //Add Marker to current Polygon
                            marker = mMap.addMarker(new MarkerOptions()
                                    .position(point)
                                    .title("Zgłoś tutaj")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            Log.v("Marker", "ADDing first Marker");
                            getAddress(Lat,Lng);

                            break;

                        }
                        else if (PolyUtil.containsLocation(marker.getPosition(), pObj.getPoints(), false)) {
                            //Marker exists already in a different Polygon
                            //remove Marker from previous Polygon
                            marker.remove();
                            //Add Marker to current Polygon
                            marker = mMap.addMarker(new MarkerOptions()
                                    .position(point)
                                    .title("test")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            Log.v("Marker", "Moving Marker to new Polygon");
                            getAddress(Lat,Lng);
                            break;
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Miejsce znajduje się po za granicą", Toast.LENGTH_SHORT).show();
                    }
                }


            }

        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        // Wyświetlenie na mapie lokalizacji Olesno


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.875549, 18.414801), 13));

        // Set listeners for click events.

        googleMap.setOnPolygonClickListener(this);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);



    }




        @Override
        public void onPolygonClick(Polygon polygon){
            //do whatever with polygon!

        }


    public void getAddress(double Lat, double Lng) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(Lat, Lng, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                String TAG = null;

                Log.d(TAG, "getAddress:  address" + address);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);

                popupMap(address,city,state,postalCode,knownName);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void popupMap(String address,String city,String state, String postalCode, String knownName){
    myDialog.setContentView(R.layout.popup_map);
    Button takePictureButton = myDialog.findViewById(R.id.btn_select_photo);
    final EditText title = myDialog.findViewById(R.id.txt_Title);
    final EditText description = myDialog.findViewById(R.id.txt_Description);
    final TextView addres = myDialog.findViewById(R.id.txt_adress);

    addres.setText(address);



    title.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            title.setText("");
        }
    });
    description.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            description.setText("");
        }
    });


    takePictureButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectImage();
        }
    });


    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        takePictureButton.setEnabled(false);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }


    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    myDialog.show();
}

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Zrób zdjęcie"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Wybierz z galerii"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Zrób zdjęcie", "Wybierz z galerii",
                "Wstecz"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Dodaj zdjęcie!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(MapsActivity.this);

                if (items[item].equals("Zrób zdjęcie")) {
                    userChoosenTask = "Zrób zdjęcie";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Wybierz z galerii")) {
                    userChoosenTask = "Wybierz z galerii";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Wstecz")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        ivImage = myDialog.findViewById(R.id.imageView);

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        ivImage = myDialog.findViewById(R.id.imageView);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_ok);
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (bm != null) {
            ivImage.setImageBitmap(bitmap);
        }


    }




}


