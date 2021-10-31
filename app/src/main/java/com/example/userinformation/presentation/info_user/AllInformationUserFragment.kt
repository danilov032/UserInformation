package com.example.userinformation.presentation.info_user

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userinformation.R
import com.example.userinformation.di.AppModule
import com.example.userinformation.di.DaggerAppComponent
import com.example.userinformation.domain.modeles.CellUserInfo
import com.example.userinformation.domain.modeles.User
import com.example.userinformation.presentation.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_all_information_user.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

private const val ARG_ID_CURRENT = "idCurrent"

class AllInformationUserFragment : MvpAppCompatFragment(), AllInformationUserContractView {
    private var idCurrent: Int = 0

    @Inject
    lateinit var presenterLazy: dagger.Lazy<AllInformationUserPresenter>

    @InjectPresenter
    lateinit var presenter: AllInformationUserPresenter

    @ProvidePresenter
    fun providePresenter(): AllInformationUserPresenter {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .injectAllInformationUserFragment(this)

        return presenterLazy.get()
    }

    private val customAdapter: UsersAdapter by lazy {
        UsersAdapter { user ->
            presenter.onClickUser(
                user
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCurrent = it.getInt(ARG_ID_CURRENT)
        }

        presenter.getCurrentUser(idCurrent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_information_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_friends.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customAdapter
        }
    }

    override fun showInfoUser(user: User) {
        Log.d("AAA", "showInfoUser")
        tv_name.text = user.name
        tv_age.text = user.age.toString()
        tv_company.text = user.company
        tv_email.text = user.email
        tv_phone.text = user.phone
        tv_address.text = user.address
        tv_about.text = user.about
        tv_registered.text = user.registered
        tv_latitude.text = user.latitude
        tv_longitude.text = user.longitude

        var color = ""
        when(user.eyeColor) {
            "blue" -> color = "#2408f1"
            "green" -> color = "#03fb25"
            "brown" -> color = "#5c4213"
        }

        image_eye_color.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))

        var drawable = 1
        when(user.favoriteFruit) {
            "apple" -> drawable = R.drawable.apple
            "banana" -> drawable = R.drawable.bananas
            "strawberry" -> drawable = R.drawable.strawberry
        }

        image_fruit.setImageDrawable(ResourcesCompat.getDrawable(resources,drawable, null))
    }

    override fun showFriends(listFriends: List<CellUserInfo>) {
        customAdapter.updateItems(listFriends)
    }

    override fun showDetailedInformationAboutFriendUser(id: Int) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container, AllInformationUserFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun showError(messageError: String) {
        Toast.makeText(requireContext(), messageError, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(idCurrent: Int) =
            AllInformationUserFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_CURRENT, idCurrent)
                }
            }
    }
}