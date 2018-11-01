package com.example.nimishgupta.internshala;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class workshop extends Fragment {
    TextView myOutput;
    SQLiteDatabase database;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        myOutput = (TextView).getView().find
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack( "tag" ).commit();
        workshopDatabases();
        Cursor c = database.rawQuery("Select * from WorkshopInfo",null);
        int infoNumber = c.getColumnIndex("Name");
        c.moveToFirst();
        String s="";
        if(c != null){
            do{
                s += c.getString(infoNumber) +" ";
            }
            while (c.moveToNext());

        }
        myOutput.setText(s);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workshop, container, false);
        myOutput = (TextView) getView().findViewById(R.id.output);
        return view;
    }
    public void workshopDatabases(){
        try {
            database = getActivity().openOrCreateDatabase("WORKSHOP",Context.MODE_PRIVATE,null);
            database.execSQL("Create table if not exists WorkshopInfo(Name nvarchar(40), Location nvarchar(30), Time nvarchar(20), Day nvarchar(10),Details nvarchar(50))");
            String sql = "Insert into WorkshopInfo (Name, Location, Time, Day, Details) values ('Internshala','Jaipur','2:00 PM','Monday','It is great')";
//            database.execSQL("Insert into WorkshopInfo (Name, Location, Time, Day, Details) values ('Internshala','Jaipur','2:00 PM','Monday','It is great'));
            database.execSQL(sql);
            Log.w("TAG","It is working");
        }
        catch (Exception e){
            Toast.makeText(getContext(),"Catch",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
//    public void showDatabases(){
//        Cursor c = database.rawQuery("Select * from WorkshopInfo",null);
//        int infoNumber = c.getColumnIndex("Name");
//        c.moveToFirst();
//        String s="";
//        if(c != null){
//            do{
//                s += c.getString(infoNumber) +" ";
//            }
//            while (c.moveToNext());
//
//        }
//        myOutput.setText(s);
//    }
}
