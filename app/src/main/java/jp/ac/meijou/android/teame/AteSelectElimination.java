package jp.ac.meijou.android.teame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AteSelectElimination extends AppCompatActivity {

    private TextView viewGenre; // ジャンル名を表示するTextView
    private Button[] genreButtons; // 9つのボタンを配列で管理
    private String selectedGenre = ""; // 選択されたジャンル名を格納する変数

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
                    String genre = clickedButton.getText().toString(); // ボタンのテキスト（ジャンル名）

                    if (clickedButton.getAlpha() == 1.0f) {
                        clickedButton.setAlpha(0.5f); // ボタンを薄くする
                        viewGenre.setText("選択中: " + genre); // ジャンル名をTextViewに表示
                        selectedGenre = genre; // 選択されたジャンルを保存
                    } else {
                        clickedButton.setAlpha(1.0f); // ボタンの色を元に戻す
                        viewGenre.setText("選択されたジャンル");
                        selectedGenre = ""; // 選択解除
                    }
                }
            });
        }

        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 選択されたジャンルをactivity_introductionへ渡す
                // 遷移処理を書くことができます
            }
        });
    }
}
