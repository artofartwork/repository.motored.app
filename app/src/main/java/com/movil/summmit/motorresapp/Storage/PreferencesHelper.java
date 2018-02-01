package com.movil.summmit.motorresapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {

    private static final String MYNOTES_PREFERENCES = "appMotorredPreferences";
    private static final String PREFERENCES_USERNAME = MYNOTES_PREFERENCES + ".username";
    private static final String PREFERENCES_PASSWORD = MYNOTES_PREFERENCES + ".password";
    private static final String PREFERENCES_IDUSUARIO = MYNOTES_PREFERENCES + ".idusuario";
    private static final String PREFERENCES_CARPETA = MYNOTES_PREFERENCES + ".carpeta";
    private PreferencesHelper() {
        //no instance
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_USERNAME);
        editor.remove(PREFERENCES_PASSWORD);
        editor.remove(PREFERENCES_IDUSUARIO);
        editor.apply();
    }

    public static void saveSession(Context context,String username,String password, Integer IdUsuario)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.putInt(PREFERENCES_IDUSUARIO, IdUsuario);
        editor.putString(PREFERENCES_CARPETA, "CARPETA_MOTORRED");
        editor.apply();
    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String username= sharedPreferences.getString(PREFERENCES_USERNAME,null);

        return username;
    }
    public static Integer getUserIDSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        Integer userid= sharedPreferences.getInt(PREFERENCES_IDUSUARIO,0);

        return userid;
    }

    public static String getCarpetaGeneralNombre(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String carpeta= sharedPreferences.getString(PREFERENCES_CARPETA,null);

        return carpeta;
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_USERNAME) &&
                preferences.contains(PREFERENCES_PASSWORD) && preferences.contains(PREFERENCES_IDUSUARIO);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MYNOTES_PREFERENCES, Context.MODE_PRIVATE);
    }
}
