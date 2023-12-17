package edu.miu.cs473.foodiepal

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import edu.miu.cs473.foodiepal.adapter.ViewPagerAdapter
import edu.miu.cs473.foodiepal.databinding.ActivityHomeBinding
import edu.miu.cs473.foodiepal.fragment.AboutMeFragment
import edu.miu.cs473.foodiepal.fragment.BlogFragment
import edu.miu.cs473.foodiepal.fragment.ContactFragment
import edu.miu.cs473.foodiepal.fragment.MealPlannerFragment
import edu.miu.cs473.foodiepal.fragment.RecipeFragment
import edu.miu.cs473.foodiepal.model.User


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myPrefs = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (!myPrefs.contains("authUser")) {
            fallBackToLogin()
        }
        val authUserJson = myPrefs.getString("authUser", "")
        val authUser = Gson().fromJson(authUserJson, User::class.java)
        if (authUser != null) {
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val viewPager: ViewPager2 = binding.viewPager
            val tabLayout: TabLayout = binding.tabLayout
            val fragments: List<Fragment> = listOf(
                RecipeFragment(),
                MealPlannerFragment(),
                BlogFragment(),
                ContactFragment(),
                AboutMeFragment()
            )
            val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(
                fragments,
                supportFragmentManager,
                lifecycle
            )
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, i: Int ->
                when(i){
                    0 -> tab.text = getText(R.string.recipe_fragment_text)
                    1 -> tab.text = getText(R.string.meal_planner_fragment_text)
                    2 -> tab.text = getText(R.string.blog_fragment_text)
                    3 -> tab.text = getText(R.string.contact_fragment_text)
                    4 -> tab.text = getText(R.string.about_me_fragment_text)
                }
            }.attach()

            binding.bottomNav.setOnItemSelectedListener{
                when (it.itemId) {
                    R.id.recipe -> {
                        viewPager.currentItem = 0
                        true
                    }
                    R.id.mealplanner -> {
                        viewPager.currentItem = 1
                        true
                    }
                    R.id.blog -> {
                        viewPager.currentItem = 2
                        true
                    }
                    else -> {
                        viewPager.currentItem = 0
                        true
                    }
                }
            }
        } else {
            fallBackToLogin()
        }
    }

    private fun fallBackToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}