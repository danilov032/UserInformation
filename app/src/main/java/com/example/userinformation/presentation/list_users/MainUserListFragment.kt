package com.example.userinformation.presentation.list_users

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.modeles.CellUserInfo
import com.example.userinformation.presentation.adapters.UsersAdapter
import com.example.userinformation.presentation.info_user.AllInformationUserFragment
import kotlinx.android.synthetic.main.fragment_main_user_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class MainUserListFragment : MvpAppCompatFragment(), MainUserContractView {

    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainUserListPresenter>

    @InjectPresenter
    lateinit var presenter: MainUserListPresenter

    @ProvidePresenter
    fun providePresenter(): MainUserListPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectMainUserListFragment(this)

        return presenterLazy.get()
    }

    private val customAdapter: UsersAdapter by lazy {
        UsersAdapter { user -> presenter.onClickUser(user) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_user_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customAdapter
        }
//HH:mm dd.MM.yy
//"2016-02-14T09:26:27 -03:00"

//        var date = LocalDate.parse("2016-02-14T09:26:27 -03:00")


//        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss z")
//        var date = LocalDate.parse("2016-02-14T09:26:27 -03:00", formatter)
//
//
//        Log.d("AAAA",date.toString())
//        var formatter1 = DateTimeFormatter.ofPattern("HH:mm dd.MM.yy")
//        var formattedDate = date.format(formatter1)
    }


    override fun showUsers(listUsers: List<CellUserInfo>) {
        customAdapter.updateItems(listUsers)
    }

    override fun showError(messageError: String) {
        Toast.makeText(requireContext(), messageError, Toast.LENGTH_SHORT).show()
    }

    override fun showDetailedInformationAboutUser(id: Int) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, AllInformationUserFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainUserListFragment()
    }
}