package com.example.mptask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeasonConstellationActivity extends AppCompatActivity implements ConstellationView.StarClickListener {
    
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
    
    // 별 정보 관련 변수
    private MaterialCardView starInfoCardView;
    private TextView starNameTextView;
    private TextView starDescriptionTextView;
    private Button closeStarInfoButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_constellation);
        
        // 초기화 과정에서 예외 발생 시 잡기 위한 try-catch 블록 추가
        try {
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
            
            // 별 정보 관련 뷰 초기화
            starInfoCardView = findViewById(R.id.starInfoCardView);
            starNameTextView = findViewById(R.id.starNameTextView);
            starDescriptionTextView = findViewById(R.id.starDescriptionTextView);
            closeStarInfoButton = findViewById(R.id.closeStarInfoButton);
            
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
            
            // 별 정보 닫기 버튼 클릭 리스너
            closeStarInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideStarInfo();
                }
            });
            
            // 별 클릭 이벤트 리스너 설정
            constellationView.setStarClickListener(this);
            
            // 계절별 대표 별자리 설정
            initSeasonConstellations();
            
            // 초기 계절별 별자리 표시
            updateSeasonConstellation();
            
            Toast.makeText(this, "초기화 완료", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // 예외 발생 시 사용자에게 오류 메시지 표시
            Toast.makeText(this, "오류 발생: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    
    // 별 클릭 이벤트 처리
    @Override
    public void onStarClick(ConstellationView.Star star) {
        try {
            showStarInfo(star);
        } catch (Exception e) {
            Toast.makeText(this, "별 정보 표시 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    
    // 별 정보 보여주기
    private void showStarInfo(ConstellationView.Star star) {
        starNameTextView.setText(star.name);
        
        // 별의 설명과 함께 별의 밝기 정보도 표시
        String magnitudeDesc = "";
        if (star.magnitude <= 1.5) {
            magnitudeDesc = "1등성, 매우 밝은 별";
        } else if (star.magnitude <= 2.5) {
            magnitudeDesc = "2등성, 밝은 별";
        } else if (star.magnitude <= 3.5) {
            magnitudeDesc = "3등성, 보통 밝기의 별";
        } else if (star.magnitude <= 4.5) {
            magnitudeDesc = "4등성, 다소 어두운 별";
        } else {
            magnitudeDesc = "5등성 이상, 어두운 별";
        }
        
        starDescriptionTextView.setText(star.description + "\n\n" + magnitudeDesc + " (등급: " + star.magnitude + ")");
        
        // 별 정보 표시
        starInfoCardView.setVisibility(View.VISIBLE);
    }
    
    // 별 정보 숨기기
    private void hideStarInfo() {
        starInfoCardView.setVisibility(View.GONE);
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
        try {
            hideStarInfo(); // 별 정보 창 닫기
            currentSeason = season;
            currentConstellationIndex = 0;
            updateSeasonConstellation();
            
            // 선택된 계절 버튼 하이라이트
            resetSeasonButtonColors();
            highlightSelectedSeasonButton(season);
        } catch (Exception e) {
            Toast.makeText(this, "계절 선택 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    
    // 계절 버튼 색상 초기화
    private void resetSeasonButtonColors() {
        springButton.setAlpha(0.7f);
        summerButton.setAlpha(0.7f);
        fallButton.setAlpha(0.7f);
        winterButton.setAlpha(0.7f);
    }
    
    // 선택된 계절 버튼 하이라이트
    private void highlightSelectedSeasonButton(String season) {
        switch (season) {
            case "봄":
                springButton.setAlpha(1.0f);
                break;
            case "여름":
                summerButton.setAlpha(1.0f);
                break;
            case "가을":
                fallButton.setAlpha(1.0f);
                break;
            case "겨울":
                winterButton.setAlpha(1.0f);
                break;
        }
    }
    
    // 이전 별자리 보기
    private void showPreviousConstellation() {
        try {
            hideStarInfo(); // 별 정보 창 닫기
            List<String> constellations = seasonConstellations.get(currentSeason);
            currentConstellationIndex = (currentConstellationIndex - 1 + constellations.size()) % constellations.size();
            updateSeasonConstellation();
        } catch (Exception e) {
            Toast.makeText(this, "이전 별자리 표시 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    
    // 다음 별자리 보기
    private void showNextConstellation() {
        try {
            hideStarInfo(); // 별 정보 창 닫기
            List<String> constellations = seasonConstellations.get(currentSeason);
            currentConstellationIndex = (currentConstellationIndex + 1) % constellations.size();
            updateSeasonConstellation();
        } catch (Exception e) {
            Toast.makeText(this, "다음 별자리 표시 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    
    // 현재 계절의 별자리 표시 업데이트
    private void updateSeasonConstellation() {
        try {
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
        } catch (Exception e) {
            Toast.makeText(this, "별자리 업데이트 오류: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
} 