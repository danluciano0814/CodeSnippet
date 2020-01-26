package com.nessotech.moneygment.codesnippet.application;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nessotech.moneygment.codesnippet.dao.UserDao;
import com.nessotech.moneygment.codesnippet.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
