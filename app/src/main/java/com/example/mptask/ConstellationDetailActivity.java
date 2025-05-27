package com.example.mptask;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConstellationDetailActivity extends AppCompatActivity {
    
    private ConstellationView constellationView;
    private TextView constellationNameTextView;
    private Button backButton;
    private Button showHoroscopeButton;
    
    // 운세 관련 변수
    private ScrollView horoscopeScrollView;
    private TextView horoscopeConstellationTextView;
    private TextView horoscopeContentTextView;
    private Button closeHoroscopeButton;
    private String currentConstellationCode;
    private Random random = new Random();
    
    // 모든 별자리에 공통으로 사용할 랜덤 운세 목록
    private List<String> randomHoroscopes = Arrays.asList(
        "오늘은 특별한 날입니다. 당신의 직감을 믿고 중요한 결정을 내려보세요.",
        "에너지가 넘치는 하루입니다. 새로운 도전을 시작하기에 완벽한 시기입니다.",
        "오늘은 휴식이 필요한 날입니다. 자신을 위한 충분한 시간을 가지세요.",
        "금전운이 상승하는 날입니다. 재정적인 기회에 주목하세요.",
        "인간관계에 집중하는 것이 좋은 날입니다. 오래된 친구와 연락해보세요.",
        "직감이 예민해지는 날입니다. 중요한 선택의 순간에 내면의 소리에 귀 기울이세요.",
        "창의적인 아이디어가 샘솟는 날입니다. 예술적인 활동에 참여해보세요.",
        "건강에 신경써야 하는 날입니다. 가벼운 운동과 균형 잡힌 식단을 유지하세요.",
        "커뮤니케이션이 중요한 날입니다. 솔직하고 분명하게 의사를 표현해보세요.",
        "새로운 시작을 위한 완벽한 날입니다. 과거는 과감히 내려놓고 미래에 집중하세요.",
        "오늘은 성찰의 시간이 필요합니다. 인생의 중요한 목표를 재점검해보세요.",
        "주변 환경의 변화가 행운을 가져올 것입니다. 새로운 기회에 열린 마음을 가지세요.",
        "감정적인 균형을 찾는 것이 중요한 날입니다. 명상이나 요가를 통해 마음의 안정을 찾아보세요.",
        "타인의 조언에 귀 기울이는 것이 도움이 되는 날입니다. 다양한 관점을 고려해보세요.",
        "자신감이 빛나는 날입니다. 오랫동안 미루어 왔던 일에 도전해보세요.",
        "우정이 중요한 역할을 하는 날입니다. 친구들과의 시간을 소중히 여기세요.",
        "가족과의 유대감이 중요해지는 날입니다. 소중한 사람들과 시간을 보내세요.",
        "자기 계발에 집중하기 좋은 날입니다. 새로운 기술을 배우거나 책을 읽어보세요.",
        "오늘은 행운이 당신을 기다리고 있습니다. 긍정적인 마음가짐이 성공의 열쇠입니다.",
        "작은 일에도 감사함을 느끼는 하루가 될 것입니다. 감사 일기를 써보는 건 어떨까요?"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation_detail);
        
        // 인텐트에서 데이터 가져오기
        Intent intent = getIntent();
        currentConstellationCode = intent.getStringExtra("constellation_code");
        
        // 뷰 초기화
        constellationView = findViewById(R.id.constellationView);
        constellationNameTextView = findViewById(R.id.constellationNameTextView);
        backButton = findViewById(R.id.backButton);
        showHoroscopeButton = findViewById(R.id.showHoroscopeButton);
        
        // 운세 관련 뷰 초기화
        horoscopeScrollView = findViewById(R.id.horoscopeScrollView);
        horoscopeConstellationTextView = findViewById(R.id.horoscopeConstellationTextView);
        horoscopeContentTextView = findViewById(R.id.horoscopeContentTextView);
        closeHoroscopeButton = findViewById(R.id.closeHoroscopeButton);
        
        // 버튼 클릭 리스너 설정
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        // 운세 보기 버튼 클릭 리스너
        showHoroscopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHoroscope();
            }
        });
        
        // 운세 닫기 버튼 클릭 리스너
        closeHoroscopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideHoroscope();
            }
        });
        
        // 별자리 표시
        if (currentConstellationCode != null && !currentConstellationCode.isEmpty()) {
            // 별자리 이름 가져오기
            String constellationName = ConstellationView.getConstellationName(currentConstellationCode);
            
            // 별자리 표시
            constellationView.setVisibility(View.VISIBLE);
            constellationView.setConstellation(currentConstellationCode);
            
            // 별자리 이름 표시
            constellationNameTextView.setText(constellationName);
            constellationNameTextView.setVisibility(View.VISIBLE);
            
            // 운세 버튼 활성화
            showHoroscopeButton.setEnabled(true);
        } else {
            Toast.makeText(this, "별자리 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    
    // 운세 표시
    private void showHoroscope() {
        if (currentConstellationCode == null || currentConstellationCode.isEmpty()) {
            Toast.makeText(this, "먼저 별자리를 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 랜덤으로 운세 선택
        String horoscope = randomHoroscopes.get(random.nextInt(randomHoroscopes.size()));
        
        // 별자리 이름 가져오기
        String constellationName = ConstellationView.getConstellationName(currentConstellationCode);
        
        // 화면에 표시
        horoscopeConstellationTextView.setText(constellationName);
        horoscopeContentTextView.setText(horoscope);
        
        // 별자리 뷰 숨기고 운세 뷰 표시
        constellationView.setVisibility(View.GONE);
        constellationNameTextView.setVisibility(View.GONE);
        horoscopeScrollView.setVisibility(View.VISIBLE);
    }
    
    // 운세 화면 닫기
    private void hideHoroscope() {
        horoscopeScrollView.setVisibility(View.GONE);
        constellationView.setVisibility(View.VISIBLE);
        constellationNameTextView.setVisibility(View.VISIBLE);
    }
} 