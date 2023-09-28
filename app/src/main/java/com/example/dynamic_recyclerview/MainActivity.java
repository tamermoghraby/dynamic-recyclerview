package com.example.dynamic_recyclerview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_recyclerview.adapter.CustomItemAnimator;
import com.example.dynamic_recyclerview.adapter.DynamicAdapter;
import com.example.dynamic_recyclerview.model.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DynamicAdapter adapter;
    private boolean isGridMode = true;
    private List<TextView> categoryButtons = new ArrayList<>();
    private List<RecyclerViewItem> recyclerViewItems;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.mainRecyclerView);
        TextView toggleButton = findViewById(R.id.toggleButton);
        TextView buttonAll = findViewById(R.id.buttonAll);
        TextView buttonFastFood = findViewById(R.id.buttonFastFood);
        TextView buttonCoffee = findViewById(R.id.buttonCoffee);
        TextView buttonSandwich = findViewById(R.id.buttonSandwich);

        categoryButtons.add(buttonAll);
        categoryButtons.add(buttonFastFood);
        categoryButtons.add(buttonCoffee);
        categoryButtons.add(buttonSandwich);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new CustomItemAnimator());
        recyclerViewItems = generateDummyData();
        adapter = new DynamicAdapter(recyclerViewItems, isGridMode);
        recyclerView.setAdapter(adapter);

        toggleButton.setOnClickListener(view -> toggleView());

        for (TextView button : categoryButtons) {
            button.setOnClickListener(view -> {
                String category = (String) view.getTag();
                filterByCategory(category);
            });
        }
    }

    private List<RecyclerViewItem> generateDummyData() {
        List<RecyclerViewItem> dummyData = new ArrayList<>();

        String[] restaurantNames = {
                "McDonald's", "Burger King", "Pizza Hut", "KFC", "Subway",
                "Taco Bell", "Domino's Pizza", "Starbucks", "Dunkin' Donuts", "Wendy's",
                "Chipotle", "Panera Bread", "Papa John's", "Five Guys", "Chili's"
        };

        String[] categories = {
                "Fast Food", "Fast Food", "Pizza", "Fast Food", "Sandwich",
                "Mexican", "Pizza", "Coffee", "Coffee", "Fast Food",
                "Mexican", "Sandwich", "Pizza", "Coffee", "Fast Food"
        };

        String[] offers = {
                "BUY 1 GET 1 FREE", "UP TO 50% OFF", "FREE DELIVERY", "SPECIAL COMBO DEAL",
                "BUY 1 GET 1 FREE", "UP TO 40% OFF", "2 Pizzas at the price of 1", "BUY 1 GET 1 FREE",
                "SPECIAL COFFEE OFFER", "FREE DONUT with any purchase",
                "50% OFF on Burritos", "FREE CHIPS with any sandwich", "2 Medium Pizzas for $15",
                "FREE DRINK with every burger", "FREE FRIES with any purchase"
        };

        String[] imageUrls = {
                "https://assets.stickpng.com/images/6128ffece3a15c00041a8e44.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlDQezbnNtQebOomG7eOzBrf1cNv1aFF50Pp3ynxQu&s",
                "https://upload.wikimedia.org/wikipedia/sco/thumb/d/d2/Pizza_Hut_logo.svg/2177px-Pizza_Hut_logo.svg.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9v5-bdQ4a-mEVLFMkZui0_vvLOsGafaGS8WqYLs0&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSdNbbDI2a-YirqHx1HUut-XLND_EFAI7yeIxNcfY&s",
                "https://cdn.cookielaw.org/logos/94ba57b5-e5fc-4459-a91d-28bc381b6185/4d184cf0-49a3-428c-bd2a-a761d4b9a22d/9b0f8204-596e-41aa-bb2d-e929c0663932/Tacobell.comLogo.png",
                "https://prnewswire2-a.akamaihd.net/p/1893751/sp/189375100/thumbnail/entry_id/0_cxe8zcu5/def_height/2700/def_width/2700/version/100012/type/1",
                "https://upload.wikimedia.org/wikipedia/en/thumb/d/d3/Starbucks_Corporation_Logo_2011.svg/800px-Starbucks_Corporation_Logo_2011.svg.png",
                "https://seeklogo.com/images/D/dunkin-donuts-logo-1E269BA8F1-seeklogo.com.png",
                "https://i.pinimg.com/1200x/67/40/d5/6740d50dd14ff6f45d2e458942b9dc79.jpg",
                "https://upload.wikimedia.org/wikipedia/en/thumb/3/3b/Chipotle_Mexican_Grill_logo.svg/1200px-Chipotle_Mexican_Grill_logo.svg.png",
                "https://hackthemenu.com/wp-content/uploads/2013/11/panera-bread-logo.jpg",
                "https://m.media-amazon.com/images/I/51lGuDbUm8L.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRB5P6SDKIwYbe6OVAkUUYuZD5WEvDJ3waArw&usqp=CAU",
                "https://logowik.com/content/uploads/images/770_chilis.jpg"
        };

        int numItems = restaurantNames.length;

        for (int i = 0; i < numItems; i++) {
            dummyData.add(new RecyclerViewItem(restaurantNames[i], offers[i], imageUrls[i], categories[i]));
        }

        return dummyData;
    }


    private void filterByCategory(String category) {
        List<RecyclerViewItem> filteredList = new ArrayList<>();

        boolean isAlreadySelected = category.equals(selectedCategory);

        if (isAlreadySelected) category = "All";

        for (TextView button : categoryButtons) {
            String buttonCategory = (String) button.getTag();
            if (buttonCategory.equals(category)) {
                button.setBackgroundResource(R.drawable.selected_filter_background);
                button.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else {
                button.setBackgroundResource(R.drawable.ripple_background);
                button.setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        }

        if (category.equals("All")) {
            selectedCategory = "All";
            filteredList.addAll(recyclerViewItems);
        } else {
            selectedCategory = category;
            for (RecyclerViewItem item : recyclerViewItems) {
                if (item.getCategory().equals(category)) {
                    filteredList.add(item);
                }
            }
        }

        adapter.updateDataSet(filteredList);
    }

    private void toggleView() {
        isGridMode = !isGridMode;
        if(isGridMode) {
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        adapter.setGridMode(isGridMode);
        recyclerView.setAdapter(adapter);
    }
}