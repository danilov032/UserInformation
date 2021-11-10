package com.example.userinformation.presentation.list_users

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.models.CellUserInfo
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
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectMainUserListFragment(this)

        return presenterLazy.get()
    }

    private val customAdapter: UsersAdapter by lazy {
        UsersAdapter { user -> presenter.onClickUser(user) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun showUsers(listUsers: List<CellUserInfo>) {
        customAdapter.updateItems(listUsers)
    }

    override fun showToast(message: String?) {
        Toast.makeText(requireContext(), message?: requireContext().getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_user_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorites) {
            presenter.updateDataCache()
        }
        return true
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