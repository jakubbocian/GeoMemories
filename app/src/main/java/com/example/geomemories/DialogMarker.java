package com.example.geomemories;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DialogMarker extends DialogFragment {

    Marker marker;
    private FirebaseAuth mAuth;

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Cancellare il marker?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //delete the marker
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        Double lat = marker.getPosition().latitude;
                        Double lng = marker.getPosition().longitude;
                        Posizione posizione = new Posizione(lat, lng);
                        StorageReference storageRef = storage.getReference(mAuth.getUid()).child(posizione.format() + ";" + marker.getTitle() + ".jpg");
                        marker.remove();
                        storageRef.delete();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //delete the dialog fragment
                        DialogMarker.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
