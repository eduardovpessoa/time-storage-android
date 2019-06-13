package br.fef.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.fef.R
import br.fef.data.persistence.AppDatabase
import br.fef.data.persistence.dao.UserDao
import br.fef.data.persistence.entity.User
import br.fef.ui.fragment.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var user: User
    private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        appDatabase = AppDatabase.getDatabase(this)
        userDao = appDatabase.userDao()
        user = userDao.queryAll()[0]

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        if (user.tipo == 1) {
            nav_view.inflateMenu(R.menu.activity_main_admin)
        } else {
            nav_view.inflateMenu(R.menu.activity_main_user)
        }
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navigationView.getHeaderView(0)
        val navTxtUser: TextView = headerView.findViewById(R.id.txtUser)
        val navTxtEmail: TextView = headerView.findViewById(R.id.txtEmail)
        navTxtUser.text = user.nome
        navTxtEmail.text = user.email
        fragment = HomeFragment()
        replaceFragment(fragment)

        fab.setOnClickListener {
            if (fragment is AutorFragment) {
                val intent = Intent(this, CadastrarAutorActivity::class.java)
                startActivityForResult(intent, REQUEST_CATEGORIA)
            } else if (fragment is CategoriaFragment) {
                val intent = Intent(this, CadastrarCategoriaActivity::class.java)
                startActivityForResult(intent, REQUEST_CATEGORIA)
            } else if (fragment is DocumentoFragment) {
                //TODO CADASTRAR DOCUMENTO
            } else if (fragment is EditoraFragment) {
                val intent = Intent(this, CadastrarEditoraActivity::class.java)
                startActivityForResult(intent, REQUEST_EDITORA)
            } else if (fragment is GeneroFragment) {
                val intent = Intent(this, CadastrarGeneroActivity::class.java)
                startActivityForResult(intent, REQUEST_GENERO)
            }

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            title = "Time Storage - Início"
            replaceFragment(HomeFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val args = Bundle()
        args.putInt("codigo", user.cod)
        args.putInt("tipo", user.tipo)
        when (item.itemId) {
            R.id.nav_home -> {
                fab.visibility = FloatingActionButton.INVISIBLE
                title = "Time Storage - Início"
                fragment = HomeFragment()
            }
            R.id.nav_doc -> {
                fab.visibility = FloatingActionButton.VISIBLE
                title = "Time Storage - Documentos"
                fragment = DocumentoFragment()
                fragment.arguments = args
            }
            R.id.nav_fav -> {
                fab.visibility = FloatingActionButton.INVISIBLE
                title = "Time Storage - Favoritos"
                //fragment = GeneroFragment()
            }
            R.id.nav_autor -> {
                fab.visibility = FloatingActionButton.VISIBLE
                title = "Time Storage - Autores"
                fragment = AutorFragment()
            }
            R.id.nav_categoria -> {
                fab.visibility = FloatingActionButton.VISIBLE
                title = "Time Storage - Categorias"
                fragment = CategoriaFragment()
            }
            R.id.nav_editora -> {
                fab.visibility = FloatingActionButton.VISIBLE
                title = "Time Storage - Editoras"
                fragment = EditoraFragment()
            }
            R.id.nav_genero -> {
                fab.visibility = FloatingActionButton.VISIBLE
                title = "Time Storage - Gêneros"
                fragment = GeneroFragment()
            }
            R.id.nav_about -> {
                Toast.makeText(this, "Time Storage - v.1.0\nCopyright 2019", Toast.LENGTH_LONG).show()
            }
            R.id.nav_sair -> {
                userDao.deleteAll()
                finish()
            }
        }
        replaceFragment(fragment)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }

    companion object {
        const val REQUEST_AUTOR: Int = 110
        const val REQUEST_CATEGORIA: Int = 120
        const val REQUEST_DOCUMENTO: Int = 130
        const val REQUEST_EDITORA: Int = 140
        const val REQUEST_GENERO: Int = 150
    }


}
