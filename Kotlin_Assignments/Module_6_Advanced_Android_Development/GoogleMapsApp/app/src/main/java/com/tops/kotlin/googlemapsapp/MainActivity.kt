package com.tops.kotlin.googlemapsapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.tops.kotlin.googlemapsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var placesClient: PlacesClient
    private lateinit var searchView: AutoCompleteTextView
    private lateinit var spinner: Spinner
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize Google Places API
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.google_maps_api_key))
        }

        placesClient = Places.createClient(this)

        // Initialize Map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Initialize Views
        searchView = binding.searchView
        spinner = binding.mapTypeSpinner

        // Set up map mode spinner
        val mapTypes = arrayOf("Normal", "Satellite", "Terrain", "Hybrid")
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mapTypes)
        spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                googleMap.mapType = when (position) {
                    1 -> GoogleMap.MAP_TYPE_SATELLITE
                    2 -> GoogleMap.MAP_TYPE_TERRAIN
                    3 -> GoogleMap.MAP_TYPE_HYBRID
                    else -> GoogleMap.MAP_TYPE_NORMAL
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        })

        // Set up place autocomplete
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed for this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.isNotEmpty()) fetchPlaceSuggestions(query)
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) fetchPlaceSuggestions(query)
            }

        })
    }

    private fun fetchPlaceSuggestions(query: String) {
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                val suggestions =
                    response.autocompletePredictions.map {
                        it.getFullText(null).toString()
                    }
                val adapter =
                    ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
                searchView.setAdapter(adapter)

                searchView.setOnItemClickListener { _, _, position, _ ->
                    val placeName = suggestions[position]
                    findPlaceCoordinates(placeName)
                }
            }
    }

    private fun findPlaceCoordinates(placeName: String) {
        // Initialize Geocoder
        val geocoder = Geocoder(this)

        try {
            // Get a list of possible addresses (maxResults = 1 for the first match)
            val addresses = geocoder.getFromLocationName(placeName, 1)

            // Check if any address was returned
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    // Extract the first address's coordinates
                    val location = addresses[0]
                    val latLng = LatLng(location.latitude, location.longitude)

                    // Add a marker on the map
                    googleMap.addMarker(MarkerOptions().position(latLng).title(placeName))

                    // Move and zoom the camera to the marker
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
                } else {
                    // Handle case where no result was found
                    showToast("Location not found")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Error finding location: ${e.message}")
        }
    }

    // Utility function to show a toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        // Enable My Location layer on the map
        googleMap.isMyLocationEnabled = true

        // Get the user's current location
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)

                // Move and zoom the camera to the user's current location
                googleMap.moveCamera(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition.builder()
                            .target(currentLatLng)
                            .zoom(15f) // Adjust zoom level as needed
                            .build()
                    )
                )
            } else {
                // Handle case where location is null (e.g., GPS disabled)
                val defaultLocation = LatLng(20.766500, 72.969704) // Replace with fallback coordinates
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15f))
                showToast("Unable to fetch current location. Default location set.")
            }
        }.addOnFailureListener {
            // Handle failure to get location
            val defaultLocation = LatLng(20.766500, 72.969704) // Replace with fallback coordinates
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15f))
            showToast("Error fetching location. Default location set.")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    googleMap.isMyLocationEnabled = true
                }
            }
        }
    }
}