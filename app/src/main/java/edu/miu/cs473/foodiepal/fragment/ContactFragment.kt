package edu.miu.cs473.foodiepal.fragment

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import edu.miu.cs473.foodiepal.R

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val callBtn = view.findViewById<Button>(R.id.callMeBtn)
        val emailBtn = view.findViewById<Button>(R.id.emailBtn)

        callBtn.setOnClickListener {
            if (checkIfCallPermissionGranted()) {
                callMe()
            } else {
                launchPermissionDialog(CALL_PHONE)
            }
        }

        emailBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:hassanraza632@gmail.com?subject=Contacting from FoodiePal App")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return view
    }

    private fun callMe() {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:+16698327275")
        }
        startActivity(intent)
    }

    private fun checkIfCallPermissionGranted():Boolean {
        return requireContext()?.let {
            ContextCompat.checkSelfPermission(
                it,
                CALL_PHONE
            )
        } == PackageManager.PERMISSION_GRANTED
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("Permission: ", "Granted")
            callMe()

        } else {
            Log.i("Permission: ", "Denied")
        }
    }

    private fun launchPermissionDialog(manifestPermission: String){
        requestPermissionLauncher.launch(
            manifestPermission
        )
    }

}