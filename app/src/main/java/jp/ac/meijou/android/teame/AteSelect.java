package jp.ac.meijou.android.teame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.teame.databinding.ActivityAteSelectBinding;

public class AteSelect extends AppCompatActivity {

    private ActivityAteSelectBinding binding;
    private TextView viewGenre; // ジャンル名を表示するTextView
    private Button[] genreButtons; // 9つのボタンを配列で管理
    private String selectedGenre = ""; // 選択されたジャンル名を格納する変数
    private String selectedStore = ""; // 選択された店名を格納する変数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAteSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        binding = ActivityAteSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewGenre = findViewById(R.id.viewGenre); // ジャンル表示用のTextView

        // 9つのジャンルボタンを配列として管理
        genreButtons = new Button[] {
                findViewById(R.id.buttonGenre1),
                findViewById(R.id.buttonGenre2),
                findViewById(R.id.buttonGenre3),
                findViewById(R.id.buttonGenre4),
                findViewById(R.id.buttonGenre5),
                findViewById(R.id.buttonGenre6),
                findViewById(R.id.buttonGenre7),
                findViewById(R.id.buttonGenre8),
                findViewById(R.id.buttonGenre9)
        };

        // 各ジャンルボタンにクリックリスナーを設定
        for (Button button : genreButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ボタンが押されたとき、ジャンル名をTextViewに設定
                    Button clickedButton = (Button) view;
                    String genre = clickedButton.getText().toString(); // ボタンのテキスト（ジャンル名）


                    // すでに薄くなっているボタンを元の色に戻すか、薄くする
                    if (clickedButton.getAlpha() == 1.0f) {
                        clickedButton.setAlpha(0.5f); // ボタンを薄くする
                        viewGenre.setText(genre); // ジャンル名をTextViewに表示
                        selectedGenre = genre; // 選択されたジャンルを保存
                        // 店名を保存（ジャンルに対応する店名）
                        if (genre.equals("ステーキ")) {
                            selectedStore = "どっきりステーキ";
                        } else if (genre.equals("寿司")) {
                            selectedStore = "寿司太";
                        } else if (genre.equals("丼ぶり")) {
                            selectedStore = "名城丼";
                        } else if (genre.equals("ラーメン")) {
                            selectedStore = "名名軒";
                        } else if (genre.equals("洋食")) {
                            selectedStore = "キャッスル";
                        } else if (genre.equals("和食")) {
                            selectedStore = "しおがま";
                        } else if (genre.equals("定食")) {
                            selectedStore = "名城食堂";
                        } else if (genre.equals("ジャンクフード")) {
                            selectedStore = "メック";
                        } else if (genre.equals("デザート")) {
                            selectedStore = "メ・イジョウユニバ";
                        }
                    } else {
                        clickedButton.setAlpha(1.0f); // ボタンの色を元に戻す
                        // 同じジャンルが選ばれている場合、表示を消去
                        if (viewGenre.getText().toString().equals(genre)) {
                            viewGenre.setText("選択されたジャンル");
                            selectedGenre = ""; // 選択解除
                            selectedStore = ""; // 店名も解除
                        }
                    }
                }
            });
        }

        // 確認ボタンをクリックしたときに遷移処理
        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 選択されたジャンルと店名をIntentに渡してactivity_introductionへ遷移
                Intent intent = new Intent(AteSelect.this, introduction.class);
                intent.putExtra("selectedGenre", selectedGenre); // 選択されたジャンルを渡す
                intent.putExtra("storeName", selectedStore); // 店名を渡す
                startActivity(intent);

                // 1秒後にactivity_shop_chosingへ遷移
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent shopIntent = new Intent(AteSelect.this, ShopChosingActivity.class);
                        startActivity(shopIntent);
                    }
                }, 1000);
            }
        });
    }
}
