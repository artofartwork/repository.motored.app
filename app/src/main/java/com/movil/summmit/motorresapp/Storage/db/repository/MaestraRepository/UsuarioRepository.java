package com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Usuario;
import com.movil.summmit.motorresapp.Storage.db.DatabaseHelper;
import com.movil.summmit.motorresapp.Storage.db.manager.DatabaseManager;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class UsuarioRepository {

    private DatabaseHelper dbHelper;
    private Dao<Usuario, Integer> entidadDao;

    public UsuarioRepository(Context context) {
        DatabaseManager dbManager = new DatabaseManager();
        dbHelper = dbManager.getHelper(context);
        try {
            entidadDao = dbHelper.getUsuarioDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Usuario entidad) {
        try {
            entidadDao.create(entidad);
            return entidad.getIdUsuario();
//            dbHelper.getDatab
        } catch (SQLException e) {
//            e.printStackTrace();
            return 0;
        }
    }


    public Usuario getById(int id) {
        try {
            return entidadDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> findAll() {
        try {
            return entidadDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteAllRows()
    {
        DeleteBuilder del = entidadDao.deleteBuilder();
        try
        {
            del.where().ge("IdUsuario", 0);
            del.delete();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    public Usuario LoginUser(String user, String pass) {
        try {

            PreparedQuery<Usuario> query = entidadDao.queryBuilder().where().eq("UserName",
                    user).and().eq("PasswordHash", pass).prepare();

            return entidadDao.queryForFirst(query);

           // QueryBuilder qb = entidadDao.queryBuilder();
          /*  qb.where()
                    .eq("UserName", user)
                    .and()
                    .eq("PasswordHash", pass);

            return (Usuario) qb.queryForFirst();*/
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
