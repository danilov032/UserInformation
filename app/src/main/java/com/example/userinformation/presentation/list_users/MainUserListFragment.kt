package com.example.userinformation.presentation.list_users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.adapters.UsersAdapter
import com.example.userinformation.presentation.info_user.AllInformationUserFragment
import kotlinx.android.synthetic.main.fragment_main_user_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainUserListFragment : MvpAppCompatFragment(), MainUserContractView {

    @Inject
    lateinit var presenterLazy: dagger.Lazy<MainUserListPresenter>

    @InjectPresenter
    lateinit var presenter: MainUserListPresenter

    @ProvidePresenter
    fun providePresenter(): MainUserListPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule())
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customAdapter
        }
    }

    override fun showUsers(listUsers: List<User>) {
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