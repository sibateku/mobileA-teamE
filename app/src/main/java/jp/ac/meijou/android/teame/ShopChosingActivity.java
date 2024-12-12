package jp.ac.meijou.android.teame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShopChosingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_chosing);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop_chosing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 店名とジャンルを表示
        TextView textShopTitle = findViewById(R.id.textShopTitle);
        TextView textShopDetails = findViewById(R.id.textShopDetails);

        // 店名と詳細をIntentから取得
        String storeName = getIntent().getStringExtra("storeName");
        String genre = getIntent().getStringExtra("selectedGenre");

        if (storeName != null && genre != null) {
            textShopTitle.setText(storeName);
            textShopDetails.setText("ジャンル: " + genre);
        }

        // 再検索ボタン
        Button buttonReSearch = findViewById(R.id.buttonReSearch);
        buttonReSearch.setOnClickListener(v -> {
            // activity_introductionに遷移
            Intent intent = new Intent(ShopChosingActivity.this, AteSelect.class);
            startActivity(intent);
        });

        // ホームに戻るボタン
        Button buttonGoHome = findViewById(R.id.buttonGoHome);
        buttonGoHome.setOnClickListener(v -> {
            // ホーム画面に遷移
            Intent homeIntent = new Intent(ShopChosingActivity.this, MainActivity.class);
            startActivity(homeIntent);
        });
    }
}