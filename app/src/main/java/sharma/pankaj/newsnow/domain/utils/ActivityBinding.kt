package sharma.pankaj.newsnow.domain.utils

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class ActivityBinding<T : ViewDataBinding>(@LayoutRes private val layout: Int) :
    ReadWriteProperty<AppCompatActivity, T> {

    private lateinit var binding: T

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.setContentView(thisRef, layout)
            binding.lifecycleOwner = thisRef
        }
        return binding
    }

    override fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: T) {
        throw Exception("Can not assign binding")
    }
}

fun <T : ViewDataBinding> activityBindings(@LayoutRes layout: Int) = ActivityBinding<T>(layout)


class FragmentBinding<T : ViewDataBinding>(@LayoutRes private val layout: Int) :
    ReadWriteProperty<Fragment, T> {

    private lateinit var binding: T

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(thisRef.requireContext()),
                layout,
                null,
                false
            )
            binding.lifecycleOwner = thisRef.viewLifecycleOwner
        }
        return binding
    }
    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        throw Exception("Can not assign binding")
    }
}

fun <T : ViewDataBinding> fragmentBindings(@LayoutRes layout: Int) = FragmentBinding<T>(layout)