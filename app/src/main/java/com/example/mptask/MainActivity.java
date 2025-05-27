package com.example.mptask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private EditText monthEditText;
    private EditText dayEditText;
    private Button showConstellationButton;
    private Button seasonConstellationButton;
    
    private String currentConstellationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 생일 별자리 관련 뷰 초기화
        monthEditText = findViewById(R.id.monthEditText);
        dayEditText = findViewById(R.id.dayEditText);
        showConstellationButton = findViewById(R.id.showConstellationButton);
        seasonConstellationButton = findViewById(R.id.seasonConstellationButton);
        
        // 버튼 클릭 리스너 설정
        showConstellationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConstellation();
            }
        });
        
        // 계절별 별자리 버튼 클릭 리스너
        seasonConstellationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeasonConstellationScreen();
            }
        });
    }
    
    // 계절별 별자리 화면 열기
    private void openSeasonConstellationScreen() {
        Intent intent = new Intent(this, SeasonConstellationActivity.class);
        startActivity(intent);
    }
    
    private void showConstellation() {
        // 입력값 가져오기
        String monthStr = monthEditText.getText().toString().trim();
        String dayStr = dayEditText.getText().toString().trim();
        
        // 입력값 검증
        if (monthStr.isEmpty() || dayStr.isEmpty()) {
            Toast.makeText(this, "월과 일을 모두 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 문자열을 정수로 변환
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);
        
        // 유효한 날짜 확인
        if (month < 1 || month > 12) {
            Toast.makeText(this, "월은 1에서 12 사이의 값이어야 합니다", Toast.LENGTH_SHORT).show();
            return;
        }
        
        int maxDay = 31;
        if (month == 2) {
            maxDay = 29; // 간단하게 2월은 29일까지
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        }
        
        if (day < 1 || day > maxDay) {
            Toast.makeText(this, "일은 1에서 " + maxDay + " 사이의 값이어야 합니다", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 별자리 코드 가져오기
        String constellationCode = ConstellationView.getConstellationCodeByDate(month, day);
        currentConstellationCode = constellationCode;
        
        // 별자리 상세 화면으로 이동
        Intent intent = new Intent(this, ConstellationDetailActivity.class);
        intent.putExtra("constellation_code", constellationCode);
        startActivity(intent);
    }
}