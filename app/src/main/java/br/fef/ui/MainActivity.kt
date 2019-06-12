package br.fef.ui

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.fef.R
import br.fef.data.persistence.AppDatabase
import br.fef.data.persistence.dao.UserDao
import br.fef.data.persistence.entity.User
import br.fef.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var user: User

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
        replaceFragment(HomeFragment())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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
        when (item.itemId) {
            R.id.nav_home -> {
                title = "Time Storage - Início"
                replaceFragment(HomeFragment())
            }
            R.id.nav_autor -> {
                title = "Time Storage - Autores"
                replaceFragment(AutorFragment())
            }
            R.id.nav_categoria -> {
                title = "Time Storage - Categorias"
                replaceFragment(CategoriaFragment())
            }
            R.id.nav_editora -> {
                title = "Time Storage - Editoras"
                replaceFragment(EditoraFragment())
            }
            R.id.nav_genero -> {
                title = "Time Storage - Gêneros"
                replaceFragment(GeneroFragment())
            }
            R.id.nav_sair -> {
                userDao.deleteAll()
                finish()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }
}
