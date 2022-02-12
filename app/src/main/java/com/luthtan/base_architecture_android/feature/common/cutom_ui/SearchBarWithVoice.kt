package com.luthtan.base_architecture_android.feature.common.cutom_ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.luthtan.base_architecture_android.databinding.ViewSearchbarBinding
import java.util.*

class SearchBarWithVoice @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewSearchbarBinding =
        ViewSearchbarBinding.inflate(LayoutInflater.from(context), this, true)

    private var resultLauncher: ActivityResultLauncher<Intent>? = null
    private var fragment: Fragment? = null
    private var listener: OnSearchListener? = null

    init {
        binding.btnVoice.setOnClickListener {
            resultLauncher?.let {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...")
                it.launch(intent)
            } ?: kotlin.run {
                Toast.makeText(context, "Register your searchbar first", Toast.LENGTH_SHORT).show()
            }
        }

        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                listener?.onSearch(v.text.toString())
            }
            false
        }
    }

    fun registerSearchBar(fragment: Fragment, listener: OnSearchListener) {
        this.listener = listener
        this.fragment = fragment
        resultLauncher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val res: ArrayList<String> =
                        result.data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                    listener?.onSearch(res[0])
                    binding.edtSearch.setText(res[0])
                }
            }
    }

    fun setHint(hint: String) {
        binding.edtSearch.hint = hint
    }

    interface OnSearchListener {
        fun onSearch(query: String)
    }
}

object SearchBarWithVoiceBindingAdapters {
    @BindingAdapter("sb_hint")
    @JvmStatic
    fun SearchBarWithVoice.bindHint(sb_hint: String) {
        this.setHint(sb_hint)
    }
} 