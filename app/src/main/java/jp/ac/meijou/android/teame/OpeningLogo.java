package jp.ac.meijou.android.teame;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.teame.databinding.ActivityOpeningLogoBinding;

public class OpeningLogo extends AppCompatActivity {

    private ActivityOpeningLogoBinding binding;
    private final Handler handler = new Handler();
    private final int interval = 1000; // 1秒ごとに表示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityOpeningLogoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setLogoImage();
        setLoadingImages();
    }

    private void setLogoImage() {
        binding.openingLogoImage.setImageResource(R.drawable.baseline_fastfood_24);
    }

    private void setLoadingImages() {
        // 1枚目の画像
        handler.postDelayed(() -> {
            binding.loadImage1.setImageResource(R.drawable.baseline_fastfood_24);
            binding.loadImage1.setVisibility(ImageView.VISIBLE);
        }, interval);

        // 2枚目の画像
        handler.postDelayed(() -> {
            binding.loadImage2.setImageResource(R.drawable.baseline_fastfood_24);
            binding.loadImage2.setVisibility(ImageView.VISIBLE);
        }, interval * 2);

        // 3枚目の画像
        handler.postDelayed(() -> {
            binding.loadImage3.setImageResource(R.drawable.baseline_fastfood_24);
            binding.loadImage3.setVisibility(ImageView.VISIBLE);
        }, interval * 3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
