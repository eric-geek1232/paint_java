package com.example.paint.Features;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paint.Fragments.DialogBottomSheetToolsFragment;
import com.example.paint.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PaintActivity extends AppCompatActivity {
    private PaintView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.drawView = new PaintView(this);
        setContentView(this.drawView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_paint, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_all:
                this.drawView.setClean();
                break;

            case R.id.colors:
                showBottomSheetColorPicker();
                break;

            case R.id.brush:
                this.drawView.setPencil();
                break;

            case R.id.eraser:
                this.drawView.setEraser("#ffffff");
                break;
        }
        return true;
    }


    private void showBottomSheetColorPicker() {
        DialogBottomSheetToolsFragment bottomSheetFragment = DialogBottomSheetToolsFragment.newInstance();
        bottomSheetFragment.setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        Bundle bundle = new Bundle();
        bundle.putInt("size", this.drawView.getPencilSize());
        bottomSheetFragment.setArguments(bundle);
        bottomSheetFragment.show(getSupportFragmentManager(), "color_picker_dialog_fragment");
    }

    public void setColor(String color) {
        this.drawView.setPencilColor(color);
    }

    public void setSize(int size) {
        this.drawView.setPencilSize(size);
    }
}
