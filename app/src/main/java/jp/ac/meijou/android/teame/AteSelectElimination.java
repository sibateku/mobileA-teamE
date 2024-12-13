package jp.ac.meijou.android.teame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AteSelectElimination extends AppCompatActivity {

    private TextView viewGenre; // ジャンル名を表示するTextView
    private Button[] genreButtons; // 9つのボタンを配列で管理
    private String selectedGenre = ""; // 選択されたジャンル名を格納する変数
    private Object buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ate_select_elimination);

        viewGenre = findViewById(R.id.viewGenre); // ジャンル表示用のTextView

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

        for (Button button : genreButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;

                    // ボタンの選択状態を切り替える
                    if (clickedButton.getAlpha() == 1.0f) {
                        clickedButton.setAlpha(0.5f); // ボタンを薄くする
                    } else {
                        clickedButton.setAlpha(1.0f); // ボタンの色を元に戻す
                    }

                    // 選択されたジャンルを表示（ただし、既存のテキストは保持）
                    String genre = clickedButton.getText().toString();
                    if (clickedButton.getAlpha() == 0.5f) {
                        viewGenre.append("\n" + genre); // 新しいジャンルを追加
                    }
                }
            });
        }

// 確認ボタンをクリックしたときの処理
        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShopChosingActivity への遷移
                Intent intent = new Intent(AteSelectElimination.this, ShopChosingActivity.class);
                startActivity(intent);
            }
        });
        
    }
}
