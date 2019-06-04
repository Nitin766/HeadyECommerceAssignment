package com.assignment.ecommerce.database.eCommerceDatabase;

import android.arch.persistence.room.Room;
import android.content.Context;
 
public class DatabaseClient {
 
    private Context mCtx;
    private static DatabaseClient mInstance;
    private String DB_NAME = "ECommerceDB";
    
    //our app database object
    private ECommerceDatabase appDatabase;
 
    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, ECommerceDatabase.class, DB_NAME).build();
    }
 
    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }
 
    public ECommerceDatabase getAppDatabase() {
        return appDatabase;
    }
}