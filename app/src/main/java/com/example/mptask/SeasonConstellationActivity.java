package com.example.mptask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeasonConstellationActivity extends AppCompatActivity {
    
    private ConstellationView constellationView;
    private TextView constellationNameTextView;
    
    // 계절별 별자리 표시 관련 변수
    private Button previousButton;
    private Button nextButton;
    private Button backButton;
    private Button springButton;
    private Button summerButton;
    private Button fallButton;
    private Button winterButton;
    private TextView seasonNameTextView;
    private String currentSeason = "봄";
    private int currentConstellationIndex = 0;
    private List<String> seasons = Arrays.asList("봄", "여름", "가을", "겨울");
    private Map<String, List<String>> seasonConstellations = new HashMap<>();
    private String currentConstellationCode;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_constellation);
        
        // 뷰 초기화
        constellationView = findViewById(R.id.constellationView);
        constellationNameTextView = findViewById(R.id.constellationNameTextView);
        
        // 계절별 별자리 관련 뷰 초기화
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        springButton = findViewById(R.id.springButton);
        summerButton = findViewById(R.id.summerButton);
        fallButton = findViewById(R.id.fallButton);
        winterButton = findViewById(R.id.winterButton);
        seasonNameTextView = findViewById(R.id.seasonNameTextView);
        
        // 이전 별자리 버튼 클릭 리스너
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousConstellation();
            }
        });
        
        // 다음 별자리 버튼 클릭 리스너
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextConstellation();
            }
        });
        
        // 뒤로가기 버튼 클릭 리스너
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티 종료
            }
        });
        
        // 계절 선택 버튼 클릭 리스너
        springButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSeason("봄");
            }
        });
        
        summerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSeason("여름");
            }
        });
        
        fallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSeason("가을");
            }
        });
        
        winterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSeason("겨울");
            }
        });
        
        // 계절별 대표 별자리 설정
        initSeasonConstellations();
        
        // 초기 계절별 별자리 표시
        updateSeasonConstellation();
    }
    
    // 계절별 대표 별자리 초기화
    private void initSeasonConstellations() {
        // 봄 (3월~5월)
        List<String> springConstellations = Arrays.asList(
            "leo",      // 사자자리
            "virgo",    // 처녀자리
            "gemini",   // 쌍둥이자리
            "cancer",   // 게자리
            "libra"     // 천칭자리
        );
        seasonConstellations.put("봄", springConstellations);
        
        // 여름 (6월~8월)
        List<String> summerConstellations = Arrays.asList(
            "scorpio",      // 전갈자리
            "sagittarius",  // 궁수자리
            "capricorn",    // 염소자리
            "aquarius",     // 물병자리
            "cancer"        // 게자리
        );
        seasonConstellations.put("여름", summerConstellations);
        
        // 가을 (9월~11월)
        List<String> fallConstellations = Arrays.asList(
            "libra",        // 천칭자리
            "scorpio",      // 전갈자리
            "sagittarius",  // 궁수자리
            "pisces",       // 물고기자리
            "aries"         // 양자리
        );
        seasonConstellations.put("가을", fallConstellations);
        
        // 겨울 (12월~2월)
        List<String> winterConstellations = Arrays.asList(
            "pisces",       // 물고기자리
            "aries",        // 양자리
            "taurus",       // 황소자리
            "capricorn",    // 염소자리
            "aquarius"      // 물병자리
        );
        seasonConstellations.put("겨울", winterConstellations);
    }
    
    // 계절 선택
    private void selectSeason(String season) {
        currentSeason = season;
        currentConstellationIndex = 0;
        updateSeasonConstellation();
        
        // 선택된 계절 버튼 하이라이트
        resetSeasonButtonColors();
        highlightSelectedSeasonButton(season);
    }
    
    // 계절 버튼 색상 초기화
    private void resetSeasonButtonColors() {
        springButton.animate().alpha(0.8f).scaleX(1.0f).scaleY(1.0f).setDuration(300);
        summerButton.animate().alpha(0.8f).scaleX(1.0f).scaleY(1.0f).setDuration(300);
        fallButton.animate().alpha(0.8f).scaleX(1.0f).scaleY(1.0f).setDuration(300);
        winterButton.animate().alpha(0.8f).scaleX(1.0f).scaleY(1.0f).setDuration(300);
        
        springButton.setElevation(5f);
        summerButton.setElevation(5f);
        fallButton.setElevation(5f);
        winterButton.setElevation(5f);
    }
    
    // 선택된 계절 버튼 하이라이트
    private void highlightSelectedSeasonButton(String season) {
        switch (season) {
            case "봄":
                springButton.animate().alpha(1.0f).scaleX(1.15f).scaleY(1.15f).setDuration(300);
                springButton.setElevation(12f);
                break;
            case "여름":
                summerButton.animate().alpha(1.0f).scaleX(1.15f).scaleY(1.15f).setDuration(300);
                summerButton.setElevation(12f);
                break;
            case "가을":
                fallButton.animate().alpha(1.0f).scaleX(1.15f).scaleY(1.15f).setDuration(300);
                fallButton.setElevation(12f);
                break;
            case "겨울":
                winterButton.animate().alpha(1.0f).scaleX(1.15f).scaleY(1.15f).setDuration(300);
                winterButton.setElevation(12f);
                break;
        }
    }
    
    // 이전 별자리 보기
    private void showPreviousConstellation() {
        List<String> constellations = seasonConstellations.get(currentSeason);
        currentConstellationIndex = (currentConstellationIndex - 1 + constellations.size()) % constellations.size();
        updateSeasonConstellation();
    }
    
    // 다음 별자리 보기
    private void showNextConstellation() {
        List<String> constellations = seasonConstellations.get(currentSeason);
        currentConstellationIndex = (currentConstellationIndex + 1) % constellations.size();
        updateSeasonConstellation();
    }
    
    // 현재 계절의 별자리 표시 업데이트
    private void updateSeasonConstellation() {
        seasonNameTextView.setText(currentSeason);
        
        // 현재 계절의 별자리 목록
        List<String> constellations = seasonConstellations.get(currentSeason);
        
        // 현재 별자리 코드
        String constellationCode = constellations.get(currentConstellationIndex);
        currentConstellationCode = constellationCode;
        
        // 별자리 이름 가져오기
        String constellationName = ConstellationView.getConstellationName(constellationCode);
        
        // 별자리 표시
        constellationView.setVisibility(View.VISIBLE);
        constellationView.setConstellation(constellationCode);
        
        // 별자리 이름 표시
        constellationNameTextView.setText(currentSeason + " - " + constellationName + " (" + (currentConstellationIndex + 1) + "/" + constellations.size() + ")");
        constellationNameTextView.setVisibility(View.VISIBLE);
        
        // 선택된 계절 버튼 하이라이트
        resetSeasonButtonColors();
        highlightSelectedSeasonButton(currentSeason);
    }
} 