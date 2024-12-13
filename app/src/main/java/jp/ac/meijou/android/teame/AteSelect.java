package jp.ac.meijou.android.teame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                    Button clickedButton = (Button) view;
                    String genre = clickedButton.getText().toString(); // ボタンのテキスト（ジャンル名）

                    // 全てのボタンを元の状態にリセット
                    for (Button b : genreButtons) {
                        b.setAlpha(1.0f);
                    }

                    // 押されたボタンのみ薄くする
                    clickedButton.setAlpha(0.5f);
                    viewGenre.setText(genre); // ジャンル名をTextViewに表示
                    selectedGenre = genre; // 選択されたジャンルを保存

                    // 店名を保存（ジャンルに対応する店名）
                    switch (genre) {
                        case "ステーキ":
                            selectedStore = "どっきりステーキ";
                            break;
                        case "寿司":
                            selectedStore = "寿司太";
                            break;
                        case "丼ぶり":
                            selectedStore = "名城丼";
                            break;
                        case "ラーメン":
                            selectedStore = "名名軒";
                            break;
                        case "洋食":
                            selectedStore = "キャッスル";
                            break;
                        case "和食":
                            selectedStore = "しおがま";
                            break;
                        case "定食":
                            selectedStore = "名城食堂";
                            break;
                        case "ジャンクフード":
                            selectedStore = "メック";
                            break;
                        case "デザート":
                            selectedStore = "メ・イジョウユニバ";
                            break;
                        default:
                            selectedStore = "";
                            break;
                    }
                }
            });
        }

        // 確認ボタンをクリックしたときに遷移処理
        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedGenre.isEmpty()) {
                    // ジャンルが選択されていない場合のメッセージ表示
                    Toast.makeText(AteSelect.this, "ジャンルを選択してください", Toast.LENGTH_SHORT).show();
                } else {
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
            }
        });
    }
}
