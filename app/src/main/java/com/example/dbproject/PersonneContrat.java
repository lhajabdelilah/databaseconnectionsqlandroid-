package com.example.dbproject;

import android.provider.BaseColumns;

public class PersonneContrat {

    public  PersonneContrat(){}
    public static class Personne implements BaseColumns {
        public  final static  String TABLE_NAME="Personne";
        public  final  static String TABLE_COLUM_NAME_NOM="Nom";
        public  final  static String TABLE_COLUM_NAME_AGE="Age";
        public  final  static String TABLE_COLUM_NAME_DESCRIPTION="description";
        public  final  static String TABLE_COLUM_NAME_POSTE="post";

    }
}
