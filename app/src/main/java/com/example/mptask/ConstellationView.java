package com.example.mptask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConstellationView extends View {
    
    private Paint starPaint;
    private Paint linePaint;
    private Paint smallStarPaint;
    private Paint selectedStarPaint;
    private Paint starNamePaint;
    private Paint starDescriptionPaint;
    private Paint starDetailPaint;
    private List<Point> stars;
    private List<Point[]> lines;
    private Map<String, Constellation> constellations;
    private String currentConstellation;
    private Random random;
    private List<Point> backgroundStars;
    private Star selectedStar;
    private boolean showStarName = false;
    
    public ConstellationView(Context context) {
        super(context);
        init();
    }
    
    public ConstellationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    public ConstellationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        starPaint = new Paint();
        starPaint.setColor(Color.WHITE);
        starPaint.setStyle(Paint.Style.FILL);
        starPaint.setAntiAlias(true);
        
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#80FFFFFF"));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2f);
        linePaint.setAntiAlias(true);
        
        smallStarPaint = new Paint();
        smallStarPaint.setColor(Color.parseColor("#60FFFFFF"));
        smallStarPaint.setStyle(Paint.Style.FILL);
        smallStarPaint.setAntiAlias(true);
        
        selectedStarPaint = new Paint();
        selectedStarPaint.setColor(Color.parseColor("#FFFFCC00"));
        selectedStarPaint.setStyle(Paint.Style.FILL);
        selectedStarPaint.setAntiAlias(true);
        
        starNamePaint = new Paint();
        starNamePaint.setColor(Color.parseColor("#FFFFCC00"));
        starNamePaint.setTextSize(30f);
        starNamePaint.setAntiAlias(true);
        starNamePaint.setShadowLayer(3f, 1f, 1f, Color.BLACK);
        
        starDescriptionPaint = new Paint();
        starDescriptionPaint.setColor(Color.parseColor("#FFCCFFFF"));
        starDescriptionPaint.setTextSize(24f);
        starDescriptionPaint.setAntiAlias(true);
        starDescriptionPaint.setShadowLayer(2f, 1f, 1f, Color.BLACK);
        
        starDetailPaint = new Paint();
        starDetailPaint.setColor(Color.parseColor("#FFB0E0E6"));
        starDetailPaint.setTextSize(20f);
        starDetailPaint.setAntiAlias(true);
        starDetailPaint.setShadowLayer(2f, 1f, 1f, Color.BLACK);
        
        stars = new ArrayList<>();
        lines = new ArrayList<>();
        constellations = new HashMap<>();
        random = new Random();
        backgroundStars = new ArrayList<>();
        
        initConstellations();
        generateBackgroundStars(200);
    }
    
    private void generateBackgroundStars(int count) {
        backgroundStars.clear();
        for (int i = 0; i < count; i++) {
            // 배경에 작은 별들을 무작위로 생성
            backgroundStars.add(new Point(random.nextInt(1000), random.nextInt(1000)));
        }
    }
    
    private void initConstellations() {
        // 양자리 (3월 21일 ~ 4월 19일)
// 양자리 (3월 21일 ~ 4월 19일)
Constellation aries = new Constellation();

// 주요 별 (통상적으로 가장 잘 알려진 별만 사용)
aries.stars.add(new Star(new Point(280, 200), "하멜",            "양자리의 알파별, 밝은 주황색 별",    2.0f, 8500));
aries.stars.add(new Star(new Point(240, 210), "셰라탄",          "양자리의 베타별, 하멜과 쌍성계",      2.6f, 9000));
aries.stars.add(new Star(new Point(190, 225), "메사팀",          "양자리의 감마별, 양의 뿔 끝",         3.8f, 6900));
aries.stars.add(new Star(new Point(160, 215), "델타 양자리",     "양자리의 델타별, 몸통 부근",         4.3f, 8900));
aries.stars.add(new Star(new Point(220, 195), "에프실론 양자리", "양자리의 에프실론별, 머리 부분",      4.6f, 9800));

// 연결선: 전형적인 별자리 윤곽만 간단히 표현
aries.lines.add(new Point[]{aries.stars.get(0).position, aries.stars.get(1).position}); // 하멜–셰라탄
aries.lines.add(new Point[]{aries.stars.get(1).position, aries.stars.get(2).position}); // 셰라탄–메사팀
aries.lines.add(new Point[]{aries.stars.get(2).position, aries.stars.get(3).position}); // 메사팀–델타


constellations.put("aries", aries);

        
        // 황소자리 (4월 20일 ~ 5월 20일)
Constellation taurus = new Constellation();

// 주요 별 (통상적으로 가장 잘 알려진 별만 사용)
taurus.stars.add(new Star(new Point(200, 180), "알데바란",      "황소의 눈을 나타내는 주황색 거성, 알파별",       0.85f,  3910));
taurus.stars.add(new Star(new Point(250, 120), "엘나트",        "황소의 뿔 끝, 베타별",                         1.65f, 13000));
taurus.stars.add(new Star(new Point(280, 100), "제타 황소자리",  "황소의 뿔 가장 바깥 별",                       3.0f,  11000));
taurus.stars.add(new Star(new Point(220, 160), "에프실론 황소자리","황소의 눈 근처에 위치",                        3.5f,   4450));
taurus.stars.add(new Star(new Point(180, 140), "알시온",        "플레이아데스 성단의 가장 밝은 별",             2.8f,  13000));
taurus.stars.add(new Star(new Point(210, 200), "델타 황소자리",  "황소의 목 부분을 이루는 별",                  3.8f,   5000));

// 연결선: 뿔과 얼굴 윤곽을 간단히 표현
taurus.lines.add(new Point[]{taurus.stars.get(1).position, taurus.stars.get(2).position}); // 엘나트–제타
taurus.lines.add(new Point[]{taurus.stars.get(1).position, taurus.stars.get(0).position}); // 엘나트–알데바란
taurus.lines.add(new Point[]{taurus.stars.get(0).position, taurus.stars.get(3).position}); // 알데바란–에프실론
taurus.lines.add(new Point[]{taurus.stars.get(3).position, taurus.stars.get(2).position}); // 에프실론–제타
taurus.lines.add(new Point[]{taurus.stars.get(0).position, taurus.stars.get(4).position}); // 알데바란–알시온
taurus.lines.add(new Point[]{taurus.stars.get(0).position, taurus.stars.get(5).position}); // 알데바란–델타

constellations.put("taurus", taurus);

// 쌍둥이자리 (5월 21일 ~ 6월 21일)
        Constellation gemini = new Constellation();
        gemini.stars.add(new Star(new Point(200, 150), "카스토르",     "쌍둥이 중 하나, 알파별",              1.58f, 10300));
        gemini.stars.add(new Star(new Point(200, 220), "폴룩스",       "쌍둥이 중 다른 하나, 베타별",           1.14f,  4550));
        gemini.stars.add(new Star(new Point(180, 170), "프로피온",     "카스토르의 발, 에타별",                3.3f,   5050));
        gemini.stars.add(new Star(new Point(180, 260), "알헤나",       "폴룩스의 발, 감마별",                  1.9f,   9200));
        gemini.stars.add(new Star(new Point(160, 190), "테젬",         "카스토르의 무릎, 제타별",               3.4f,   4800));
        gemini.stars.add(new Star(new Point(160, 280), "와사트",       "폴룩스의 무릎, 델타별",                3.5f,   6400));
        gemini.stars.add(new Star(new Point(140, 240), "메뵤타",       "폴룩스의 뒷다리, 입실론별",            3.1f,   5900));
        gemini.stars.add(new Star(new Point(140, 160), "무프리드",     "카스토르의 뒷다리, 무별",              3.2f,   5600));
        gemini.stars.add(new Star(new Point(220, 170), "테아트",       "카스토르의 어깨, 세타별",              3.6f,   7200));
        gemini.stars.add(new Star(new Point(220, 240), "클레아",       "폴룩스의 어깨, 이오타별",              3.8f,   6100));
        gemini.stars.add(new Star(new Point(250, 165), "메이브다",     "카스토르의 머리 위, 람다별",            3.6f,   8900));
        gemini.stars.add(new Star(new Point(250, 235), "우프실론",     "폴룩스의 머리 위, 업실론별",            4.1f,   7300));
        gemini.stars.add(new Star(new Point(120, 200), "1 쌍둥이자리", "카스토르 발 아래",                     4.2f,   5700));
        gemini.stars.add(new Star(new Point(120, 290), "카펠라",       "폴룩스 발 아래",                       4.5f,   4900));

// 쌍둥이자리 연결선 수정 - 두 인물 형태
        gemini.lines.add(new Point[]{gemini.stars.get(0).position, gemini.stars.get(2).position}); // 카스토르-프로피온
        gemini.lines.add(new Point[]{gemini.stars.get(2).position, gemini.stars.get(4).position}); // 프로피온-테젬
        gemini.lines.add(new Point[]{gemini.stars.get(4).position, gemini.stars.get(7).position}); // 테젬-무프리드
        gemini.lines.add(new Point[]{gemini.stars.get(1).position, gemini.stars.get(3).position}); // 폴룩스-알헤나
        gemini.lines.add(new Point[]{gemini.stars.get(3).position, gemini.stars.get(5).position}); // 알헤나-와사트
        gemini.lines.add(new Point[]{gemini.stars.get(5).position, gemini.stars.get(6).position}); // 와사트-메뵤타
        gemini.lines.add(new Point[]{gemini.stars.get(0).position, gemini.stars.get(1).position}); // 카스토르-폴룩스
        gemini.lines.add(new Point[]{gemini.stars.get(0).position, gemini.stars.get(8).position}); // 카스토르-테아트
        gemini.lines.add(new Point[]{gemini.stars.get(1).position, gemini.stars.get(9).position}); // 폴룩스-클레아
        gemini.lines.add(new Point[]{gemini.stars.get(8).position, gemini.stars.get(10).position}); // 테아트-메이브다
        gemini.lines.add(new Point[]{gemini.stars.get(9).position, gemini.stars.get(11).position}); // 클레아-우프실론
        gemini.lines.add(new Point[]{gemini.stars.get(7).position, gemini.stars.get(12).position}); // 무프리드-1 쌍둥이자리
        gemini.lines.add(new Point[]{gemini.stars.get(6).position, gemini.stars.get(13).position}); // 메뵤타-카펠라

        constellations.put("gemini", gemini);

        // 게자리 (6월 22일 ~ 7월 22일)
        Constellation cancer = new Constellation();
        cancer.stars.add(new Star(new Point(270, 180), "아셀루스 보레알리스",   "북쪽 당나귀, 감마별",              4.3f, 7700));
        cancer.stars.add(new Star(new Point(300, 210), "아셀루스 아우스트랄리스","남쪽 당나귀, 델타별",             4.0f, 4500));
        cancer.stars.add(new Star(new Point(250, 220), "프레세페",             "벌집성단, M44라고도 불림",          3.7f, 6900));
        cancer.stars.add(new Star(new Point(200, 180), "알타르프",             "게자리의 알파별",                  3.5f, 6000));
        cancer.stars.add(new Star(new Point(330, 170), "베타 게자리",          "게자리의 베타별",                  3.9f, 7200));
        cancer.stars.add(new Star(new Point(210, 240), "제타 게자리",          "게자리의 남쪽 집게",                4.7f, 5300));
        cancer.stars.add(new Star(new Point(180, 220), "에타 게자리",          "게자리의 남서쪽 별",                5.3f, 6100));
        cancer.stars.add(new Star(new Point(220, 270), "세타 게자리",          "게자리의 남쪽 별",                  5.3f, 5500));
        cancer.stars.add(new Star(new Point(300, 250), "이오타 게자리",        "게자리의 남동쪽 별",                4.0f, 9200));
        cancer.stars.add(new Star(new Point(350, 200), "카파 게자리",          "게자리의 동쪽 집게",                5.2f, 6700));
        cancer.stars.add(new Star(new Point(370, 230), "람다 게자리",          "게자리의 동쪽 별",                  5.9f, 5400));
        cancer.stars.add(new Star(new Point(230, 150), "뮤 게자리",            "게자리의 북쪽 별",                  5.3f, 6600));

// 게자리 연결선 수정 – 벌집성단(M44)을 중심으로 게 형태 강화
        cancer.lines.add(new Point[]{cancer.stars.get(0).position, cancer.stars.get(2).position}); // 감마–M44
        cancer.lines.add(new Point[]{cancer.stars.get(1).position, cancer.stars.get(2).position}); // 델타–M44
        cancer.lines.add(new Point[]{cancer.stars.get(3).position, cancer.stars.get(2).position}); // 알파–M44
        cancer.lines.add(new Point[]{cancer.stars.get(4).position, cancer.stars.get(2).position}); // 베타–M44
        cancer.lines.add(new Point[]{cancer.stars.get(0).position, cancer.stars.get(4).position}); // 감마–베타
        cancer.lines.add(new Point[]{cancer.stars.get(3).position, cancer.stars.get(5).position}); // 알파–제타
        cancer.lines.add(new Point[]{cancer.stars.get(3).position, cancer.stars.get(6).position}); // 알파–에타
        cancer.lines.add(new Point[]{cancer.stars.get(5).position, cancer.stars.get(7).position}); // 제타–세타
        cancer.lines.add(new Point[]{cancer.stars.get(1).position, cancer.stars.get(8).position}); // 델타–이오타
        cancer.lines.add(new Point[]{cancer.stars.get(4).position, cancer.stars.get(9).position}); // 베타–카파
        cancer.lines.add(new Point[]{cancer.stars.get(9).position, cancer.stars.get(10).position}); // 카파–람다
        cancer.lines.add(new Point[]{cancer.stars.get(0).position, cancer.stars.get(11).position}); // 감마–뮤

        constellations.put("cancer", cancer);


        // 사자자리 (7월 23일 ~ 8월 22일)
        Constellation leo = new Constellation();
        leo.stars.add(new Star(new Point(250, 200), "레굴루스",            "사자의 심장, 알파별",          1.35f, 12000));
        leo.stars.add(new Star(new Point(300, 170), "데네볼라",            "사자의 꼬리, 베타별",          2.14f,  8500));
        leo.stars.add(new Star(new Point(200, 170), "알기바",              "사자의 이마, 감마별",          2.10f, 13000));
        leo.stars.add(new Star(new Point(190, 210), "제타 사자자리",       "사자의 목 부분",              3.40f,  9200));
        leo.stars.add(new Star(new Point(230, 240), "세르탄",              "사자의 엉덩이, 델타별",        2.56f,  7800));
        leo.stars.add(new Star(new Point(270, 260), "에프실론 사자자리",   "사자의 뒷다리",               3.00f,  6700));
        leo.stars.add(new Star(new Point(340, 200), "람다 사자자리",       "사자의 얼굴",                 4.30f,  5900));
        leo.stars.add(new Star(new Point(380, 230), "무 사자자리",         "사자의 앞다리",               3.90f,  6100));
        leo.stars.add(new Star(new Point(340, 150), "이오타 사자자리",     "사자의 갈기",                 4.00f,  5400));
        leo.stars.add(new Star(new Point(370, 160), "타우 사자자리",       "사자의 귀",                   4.90f,  5800));
        leo.stars.add(new Star(new Point(190, 240), "에타 사자자리",       "사자의 어깨",                 3.50f,  7600));
        leo.stars.add(new Star(new Point(210, 280), "오미크론 사자자리",   "사자의 앞다리 아래",          3.50f,  5200));
        leo.stars.add(new Star(new Point(280, 290), "시그마 사자자리",     "사자의 뒷다리 아래",          4.10f,  4900));
        leo.stars.add(new Star(new Point(350, 260), "카파 사자자리",       "사자의 배 부분",              4.50f,  5100));

// 사자자리 연결선 수정 – 전통적인 사자 형태
        leo.lines.add(new Point[]{leo.stars.get(0).position, leo.stars.get(2).position});  // 레굴루스–알기바
        leo.lines.add(new Point[]{leo.stars.get(2).position, leo.stars.get(3).position});  // 알기바–제타
        leo.lines.add(new Point[]{leo.stars.get(3).position, leo.stars.get(4).position});  // 제타–델타
        leo.lines.add(new Point[]{leo.stars.get(4).position, leo.stars.get(0).position});  // 델타–레굴루스
        leo.lines.add(new Point[]{leo.stars.get(0).position, leo.stars.get(5).position});  // 레굴루스–에프실론
        leo.lines.add(new Point[]{leo.stars.get(5).position, leo.stars.get(1).position});  // 에프실론–데네볼라
        leo.lines.add(new Point[]{leo.stars.get(2).position, leo.stars.get(6).position});  // 알기바–람다
        leo.lines.add(new Point[]{leo.stars.get(6).position, leo.stars.get(7).position});  // 람다–무
        leo.lines.add(new Point[]{leo.stars.get(2).position, leo.stars.get(8).position});  // 알기바–이오타
        leo.lines.add(new Point[]{leo.stars.get(8).position, leo.stars.get(9).position});  // 이오타–타우
        leo.lines.add(new Point[]{leo.stars.get(3).position, leo.stars.get(10).position}); // 제타–에타
        leo.lines.add(new Point[]{leo.stars.get(10).position, leo.stars.get(11).position}); // 에타–오미크론
        leo.lines.add(new Point[]{leo.stars.get(5).position, leo.stars.get(12).position});  // 에프실론–시그마
        leo.lines.add(new Point[]{leo.stars.get(1).position, leo.stars.get(13).position});  // 데네볼라–카파

        constellations.put("leo", leo);
// 처녀자리 (8월 23일 ~ 9월 22일)
        Constellation virgo = new Constellation();
        virgo.stars.add(new Star(new Point(180, 150), "베라",            "처녀자리 북부 지역의 별",         2.8f, 10500));
        virgo.stars.add(new Star(new Point(230, 180), "스피카",          "처녀자리의 가장 밝은 별",         1.04f,22400));
        virgo.stars.add(new Star(new Point(270, 220), "포리마",          "처녀자리 중앙의 별",               3.9f, 6750));
        virgo.stars.add(new Star(new Point(310, 260), "빈데미아트릭스",  "처녀자리 남부의 별",               2.9f, 9350));
        virgo.stars.add(new Star(new Point(350, 290), "자니아",          "처녀자리 발 부분",                 3.8f, 7900));
        virgo.stars.add(new Star(new Point(330, 220), "한즈",            "처녀자리 중앙의 별",               3.6f, 5940));
        virgo.stars.add(new Star(new Point(380, 180), "사피아",          "처녀자리의 북동쪽 별",             4.1f, 6800));
        virgo.stars.add(new Star(new Point(270, 170), "제타 처녀자리",   "처녀자리 중앙 부분",               3.4f, 7100));
        virgo.stars.add(new Star(new Point(230, 230), "알파 처녀자리",   "처녀자리의 서쪽 별",               3.7f, 6000));
        virgo.stars.add(new Star(new Point(150, 180), "에타 처녀자리",   "처녀자리의 북서쪽 별",             3.9f, 7200));
        virgo.stars.add(new Star(new Point(170, 220), "감마 처녀자리",   "처녀자리의 허리 부분",             2.7f,10500));
        virgo.stars.add(new Star(new Point(200, 260), "델타 처녀자리",   "처녀자리의 치마 부분",             3.4f, 9100));
        virgo.stars.add(new Star(new Point(270, 280), "카파 처녀자리",   "처녀자리의 무릎 부분",             4.2f, 5700));
        virgo.stars.add(new Star(new Point(310, 200), "이오타 처녀자리", "처녀자리의 팔 부분",               4.1f, 6300));
        virgo.stars.add(new Star(new Point(210, 200), "타우 처녀자리",   "처녀자리의 가슴 부분",             4.3f, 5900));

// 처녀자리 연결선 수정 – 여성 형태
        virgo.lines.add(new Point[]{virgo.stars.get(0).position, virgo.stars.get(1).position}); // 베라–스피카
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(2).position}); // 스피카–포리마
        virgo.lines.add(new Point[]{virgo.stars.get(2).position, virgo.stars.get(3).position}); // 포리마–빈데미아트릭스
        virgo.lines.add(new Point[]{virgo.stars.get(3).position, virgo.stars.get(4).position}); // 빈데미아트릭스–자니아
        virgo.lines.add(new Point[]{virgo.stars.get(2).position, virgo.stars.get(5).position}); // 포리마–한즈
        virgo.lines.add(new Point[]{virgo.stars.get(5).position, virgo.stars.get(6).position}); // 한즈–사피아
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(7).position}); // 스피카–제타 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(8).position}); // 스피카–알파 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(0).position, virgo.stars.get(9).position}); // 베라–에타 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(9).position, virgo.stars.get(10).position}); // 에타 처녀자리–감마 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(10).position, virgo.stars.get(11).position}); // 감마 처녀자리–델타 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(11).position, virgo.stars.get(12).position}); // 델타 처녀자리–카파 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(7).position, virgo.stars.get(13).position}); // 제타 처녀자리–이오타 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(10).position, virgo.stars.get(14).position}); // 감마 처녀자리–타우 처녀자리
        virgo.lines.add(new Point[]{virgo.stars.get(14).position, virgo.stars.get(1).position}); // 타우 처녀자리–스피카

        constellations.put("virgo", virgo);


        // 천칭자리 (9월 23일 ~ 10월 22일)
        Constellation libra = new Constellation();
        libra.stars.add(new Star(new Point(200, 180), "주벤 알게누비", "천칭자리의 북쪽 접시", 2.75f, 8500));
        libra.stars.add(new Star(new Point(280, 180), "주벤 에샤말리", "천칭자리의 남쪽 접시", 2.61f, 9800));
        libra.stars.add(new Star(new Point(240, 220), "주벤 하크라비", "천칭의 균형을 맞추는 중심점", 3.91f, 7600));
        libra.stars.add(new Star(new Point(200, 260), "델타 천칭자리", "천칭자리의 받침대", 4.9f, 7100));
        libra.stars.add(new Star(new Point(280, 260), "감마 천칭자리", "천칭자리의 아래쪽", 3.9f, 11000));
        libra.stars.add(new Star(new Point(240, 280), "우프실론 천칭자리", "천칭자리의 지지대", 3.6f, 5300));
        libra.stars.add(new Star(new Point(180, 150), "이오타 천칭자리", "천칭자리의 북서쪽 별", 4.5f, 5700));
        libra.stars.add(new Star(new Point(300, 150), "타우 천칭자리", "천칭자리의 북동쪽 별", 3.7f, 10200));
        libra.stars.add(new Star(new Point(160, 210), "람다 천칭자리", "천칭자리의 서쪽 지지대", 5.0f, 4900));
        libra.stars.add(new Star(new Point(320, 210), "에프실론 천칭자리", "천칭자리의 동쪽 지지대", 4.9f, 5200));

        // 천칭자리 연결선 수정 - 천칭(저울) 형태
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(1).position}); // 알게누비-에샤말리
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(2).position}); // 알게누비-하크라비
        libra.lines.add(new Point[]{libra.stars.get(1).position, libra.stars.get(2).position}); // 에샤말리-하크라비
        libra.lines.add(new Point[]{libra.stars.get(2).position, libra.stars.get(3).position}); // 하크라비-델타
        libra.lines.add(new Point[]{libra.stars.get(2).position, libra.stars.get(4).position}); // 하크라비-감마
        libra.lines.add(new Point[]{libra.stars.get(3).position, libra.stars.get(5).position}); // 델타-우프실론
        libra.lines.add(new Point[]{libra.stars.get(4).position, libra.stars.get(5).position}); // 감마-우프실론
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(6).position}); // 알게누비-이오타
        libra.lines.add(new Point[]{libra.stars.get(1).position, libra.stars.get(7).position}); // 에샤말리-타우
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(8).position}); // 알게누비-람다
        libra.lines.add(new Point[]{libra.stars.get(1).position, libra.stars.get(9).position}); // 에샤말리-에프실론
        constellations.put("libra", libra);

        // 전갈자리 (10월 23일 ~ 11월 21일)
        Constellation scorpio = new Constellation();
        scorpio.stars.add(new Star(new Point(180, 180), "안타레스", "전갈의 머리, 붉은 초거성", 1.06f, 3500));
        scorpio.stars.add(new Star(new Point(220, 190), "베타 전갈자리", "전갈의 앞부분", 2.5f, 4600));
        scorpio.stars.add(new Star(new Point(260, 200), "델타 전갈자리", "전갈의 몸통", 2.3f, 19000));
        scorpio.stars.add(new Star(new Point(300, 220), "샤울라", "전갈의 꼬리, 매우 밝은 청백색 별", 1.62f, 23000));
        scorpio.stars.add(new Star(new Point(340, 240), "레스 알하구에", "전갈의 꼬리 부분", 2.8f, 21000));
        scorpio.stars.add(new Star(new Point(370, 270), "사르가스", "전갈의 독침 앞부분", 3.0f, 9900));
        scorpio.stars.add(new Star(new Point(390, 300), "에타 전갈자리", "전갈의 독침 끝", 3.3f, 11000));
        scorpio.stars.add(new Star(new Point(400, 330), "제타 전갈자리", "전갈의 꼬리 끝", 3.6f, 10400));
        scorpio.stars.add(new Star(new Point(360, 310), "시그마 전갈자리", "전갈자리의 꼬리 부분", 2.9f, 18600));
        scorpio.lines.add(new Point[]{scorpio.stars.get(0).position, scorpio.stars.get(1).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(1).position, scorpio.stars.get(2).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(2).position, scorpio.stars.get(3).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(3).position, scorpio.stars.get(4).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(4).position, scorpio.stars.get(5).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(5).position, scorpio.stars.get(6).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(6).position, scorpio.stars.get(7).position});
        scorpio.lines.add(new Point[]{scorpio.stars.get(6).position, scorpio.stars.get(8).position});
        constellations.put("scorpio", scorpio);
        
        // 궁수자리 (11월 22일 ~ 12월 21일)
        Constellation sagittarius = new Constellation();
        sagittarius.stars.add(new Star(new Point(200, 180), "루칼다", "궁수의 팔 부분", 2.81f, 9800));
        sagittarius.stars.add(new Star(new Point(240, 200), "카우스 메디아", "궁수의 활 중심", 2.7f, 10000));
        sagittarius.stars.add(new Star(new Point(280, 170), "누나키", "궁수의 어깨", 2.05f, 7800));
        sagittarius.stars.add(new Star(new Point(320, 150), "케우스 보레알리스", "궁수의 머리", 2.8f, 9700));
        sagittarius.stars.add(new Star(new Point(260, 220), "카우스 아우스트랄리스", "궁수의 가슴 부분", 1.85f, 9800));
        sagittarius.stars.add(new Star(new Point(300, 250), "아스켈라", "궁수의 다리", 2.6f, 6700));
        sagittarius.stars.add(new Star(new Point(350, 200), "제타 궁수자리", "궁수의 활 끝", 2.99f, 10200));
        sagittarius.stars.add(new Star(new Point(380, 230), "엡실론 궁수자리", "활을 당기는 팔", 1.85f, 6800));
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(0).position, sagittarius.stars.get(1).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(1).position, sagittarius.stars.get(2).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(2).position, sagittarius.stars.get(3).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(1).position, sagittarius.stars.get(4).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(4).position, sagittarius.stars.get(5).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(2).position, sagittarius.stars.get(6).position});
        sagittarius.lines.add(new Point[]{sagittarius.stars.get(6).position, sagittarius.stars.get(7).position});
        constellations.put("sagittarius", sagittarius);
        
        // 염소자리 (12월 22일 ~ 1월 19일)
        Constellation capricorn = new Constellation();
        capricorn.stars.add(new Star(new Point(200, 180), "알기디", "염소의 머리", 2.85f, 7700));
        capricorn.stars.add(new Star(new Point(240, 150), "다비", "염소의 뿔", 3.1f, 5050));
        capricorn.stars.add(new Star(new Point(280, 160), "나시라", "염소의 목", 3.7f, 5900));
        capricorn.stars.add(new Star(new Point(320, 180), "데니브 알기디", "염소의 등", 2.85f, 9900));
        capricorn.stars.add(new Star(new Point(350, 220), "오미크론 염소자리", "염소의 꼬리 시작", 4.1f, 6800));
        capricorn.stars.add(new Star(new Point(330, 260), "제타 염소자리", "염소의 물고기 꼬리", 3.7f, 9800));
        capricorn.stars.add(new Star(new Point(270, 240), "감마 염소자리", "염소의 배 부분", 3.6f, 6300));
        capricorn.stars.add(new Star(new Point(230, 220), "베타 염소자리", "염소의 앞다리", 3.05f, 9000));
        capricorn.lines.add(new Point[]{capricorn.stars.get(0).position, capricorn.stars.get(1).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(1).position, capricorn.stars.get(2).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(2).position, capricorn.stars.get(3).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(3).position, capricorn.stars.get(4).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(4).position, capricorn.stars.get(5).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(5).position, capricorn.stars.get(6).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(6).position, capricorn.stars.get(7).position});
        capricorn.lines.add(new Point[]{capricorn.stars.get(7).position, capricorn.stars.get(0).position});
        constellations.put("capricorn", capricorn);
        
        // 물병자리 (1월 20일 ~ 2월 18일)
        Constellation aquarius = new Constellation();
        aquarius.stars.add(new Star(new Point(200, 150), "사달메릭", "물병자리의 머리", 2.95f, 6100));
        aquarius.stars.add(new Star(new Point(240, 180), "사달수드", "물병자리의 어깨", 3.0f, 7700));
        aquarius.stars.add(new Star(new Point(280, 200), "사달브라", "물병자리의 물병 위치", 3.8f, 9800));
        aquarius.stars.add(new Star(new Point(320, 220), "사드 알 수우드", "물병자리의 물줄기 시작", 4.0f, 6700));
        aquarius.stars.add(new Star(new Point(290, 250), "알부달리", "물병자리의 물줄기 중간", 3.7f, 5600));
        aquarius.stars.add(new Star(new Point(330, 260), "스카트", "물병자리의 물줄기 끝", 3.3f, 7900));
        aquarius.stars.add(new Star(new Point(250, 250), "앱실론 물병자리", "물병자리의 팔", 3.8f, 15000));
        aquarius.stars.add(new Star(new Point(210, 270), "델타 물병자리", "물병자리의 다리", 3.3f, 11500));
        aquarius.lines.add(new Point[]{aquarius.stars.get(0).position, aquarius.stars.get(1).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(1).position, aquarius.stars.get(2).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(2).position, aquarius.stars.get(3).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(2).position, aquarius.stars.get(4).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(4).position, aquarius.stars.get(5).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(2).position, aquarius.stars.get(6).position});
        aquarius.lines.add(new Point[]{aquarius.stars.get(6).position, aquarius.stars.get(7).position});
        constellations.put("aquarius", aquarius);
        
        // 물고기자리 (2월 19일 ~ 3월 20일)
        Constellation pisces = new Constellation();
        pisces.stars.add(new Star(new Point(220, 180), "알리스카", "서쪽 물고기의 머리, 알파별", 3.8f, 7500));
        pisces.stars.add(new Star(new Point(190, 210), "푸크다", "서쪽 물고기의 입, 감마별", 3.7f, 6400));
        pisces.stars.add(new Star(new Point(240, 210), "오메가 물고기자리", "서쪽 물고기의 꼬리", 4.0f, 5900));
        pisces.stars.add(new Star(new Point(290, 220), "에타 물고기자리", "밧줄의 중간 부분", 3.9f, 6200));
        pisces.stars.add(new Star(new Point(330, 210), "알파라츠", "두 물고기를 연결하는 매듭", 3.8f, 7600));
        pisces.stars.add(new Star(new Point(370, 190), "쿠이토르", "동쪽 물고기의 꼬리, 베타별", 4.5f, 4900));
        pisces.stars.add(new Star(new Point(350, 160), "토를로사", "동쪽 물고기의 몸체", 4.3f, 5300));
        pisces.stars.add(new Star(new Point(320, 150), "델타 물고기자리", "동쪽 물고기의 머리", 4.4f, 5600));
        
        // 물고기자리 연결선
        pisces.lines.add(new Point[]{pisces.stars.get(0).position, pisces.stars.get(1).position}); // 알리스카-푸크다
        pisces.lines.add(new Point[]{pisces.stars.get(1).position, pisces.stars.get(2).position}); // 푸크다-오메가
        pisces.lines.add(new Point[]{pisces.stars.get(2).position, pisces.stars.get(3).position}); // 오메가-에타
        pisces.lines.add(new Point[]{pisces.stars.get(3).position, pisces.stars.get(4).position}); // 에타-알파라츠
        pisces.lines.add(new Point[]{pisces.stars.get(4).position, pisces.stars.get(5).position}); // 알파라츠-쿠이토르
        pisces.lines.add(new Point[]{pisces.stars.get(5).position, pisces.stars.get(6).position}); // 쿠이토르-토를로사
        pisces.lines.add(new Point[]{pisces.stars.get(6).position, pisces.stars.get(7).position}); // 토를로사-델타
        pisces.lines.add(new Point[]{pisces.stars.get(7).position, pisces.stars.get(4).position}); // 델타-알파라츠
        constellations.put("pisces", pisces);
    }
    
    public void setConstellation(String constellation) {
        this.currentConstellation = constellation;
        this.selectedStar = null;
        this.showStarName = false;
        
        // 별자리가 나타날 때 페이드인 애니메이션 적용
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1500);
        fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
        startAnimation(fadeIn);
        
        invalidate();
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        // 뷰 크기가 변경되면 배경 별 위치를 다시 생성
        generateBackgroundStars(200);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 일부 기기에서는 호버가 지원되지 않으므로 터치 이벤트에서도 호버와 유사한 기능 제공
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();
            
            if (currentConstellation != null && constellations.containsKey(currentConstellation)) {
                Constellation constellation = constellations.get(currentConstellation);
                float scaleX = (float) getWidth() / 500f;
                float scaleY = (float) getHeight() / 500f;
                
                // 가장 가까운 별 찾기
                float minDistance = Float.MAX_VALUE;
                Star closestStar = null;
                
                for (Star star : constellation.stars) {
                    float x = star.position.x * scaleX;
                    float y = star.position.y * scaleY;
                    float distance = (float) Math.sqrt(Math.pow(touchX - x, 2) + Math.pow(touchY - y, 2));
                    
                    // 터치 범위 내에 있고, 가장 가까운 별 선택
                    if (distance < minDistance && distance < 50) { // 50px 반경 내에 있어야 선택 가능
                        minDistance = distance;
                        closestStar = star;
                    }
                }
                
                // 별 선택 처리
                if (closestStar != null) {
                    selectedStar = closestStar;
                    showStarName = true;
                    invalidate();
                    return true;
                } else {
                    // 별 영역 밖을 터치하면 선택 해제
                    selectedStar = null;
                    showStarName = false;
                    invalidate();
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            // 터치가 끝나면 선택 상태 유지 (호버처럼 동작하지 않음)
            // 다른 별을 선택하기 위해서는 해당 별을 다시 터치해야 함
        }
        
        return super.onTouchEvent(event);
    }
    
    @Override
    public boolean onHoverEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_HOVER_ENTER:
            case MotionEvent.ACTION_HOVER_MOVE:
                float hoverX = event.getX();
                float hoverY = event.getY();
                
                if (currentConstellation != null && constellations.containsKey(currentConstellation)) {
                    Constellation constellation = constellations.get(currentConstellation);
                    float scaleX = (float) getWidth() / 500f;
                    float scaleY = (float) getHeight() / 500f;
                    
                    // 가장 가까운 별 찾기
                    float minDistance = Float.MAX_VALUE;
                    Star closestStar = null;
                    
                    for (Star star : constellation.stars) {
                        float x = star.position.x * scaleX;
                        float y = star.position.y * scaleY;
                        float distance = (float) Math.sqrt(Math.pow(hoverX - x, 2) + Math.pow(hoverY - y, 2));
                        
                        // 호버 범위 내에 있고, 가장 가까운 별 선택
                        if (distance < minDistance && distance < 50) { // 50px 반경 내에 있어야 선택 가능
                            minDistance = distance;
                            closestStar = star;
                        }
                    }
                    
                    // 별 선택 처리
                    if (closestStar != null) {
                        selectedStar = closestStar;
                        showStarName = true;
                        invalidate();
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                // 호버 영역을 벗어나면 선택 해제
                selectedStar = null;
                showStarName = false;
                invalidate();
                break;
        }
        
        return super.onHoverEvent(event);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        // 배경 별 그리기
        for (Point star : backgroundStars) {
            float x = (float) star.x / 1000f * getWidth();
            float y = (float) star.y / 1000f * getHeight();
            float size = random.nextFloat() * 2f + 1f;
            canvas.drawCircle(x, y, size, smallStarPaint);
        }
        
        if (currentConstellation != null && constellations.containsKey(currentConstellation)) {
            Constellation constellation = constellations.get(currentConstellation);
            float scaleX = (float) getWidth() / 500f;
            float scaleY = (float) getHeight() / 500f;
            
            // 별자리 선 그리기
            for (Point[] line : constellation.lines) {
                float x1 = line[0].x * scaleX;
                float y1 = line[0].y * scaleY;
                float x2 = line[1].x * scaleX;
                float y2 = line[1].y * scaleY;
                canvas.drawLine(x1, y1, x2, y2, linePaint);
            }
            
            // 별 그리기
            for (Star star : constellation.stars) {
                float x = star.position.x * scaleX;
                float y = star.position.y * scaleY;
                float radius = random.nextFloat() * 2f + 3f; // 별 크기
                
                // 선택된 별은 다른 색상으로 표시
                if (selectedStar != null && star == selectedStar) {
                    canvas.drawCircle(x, y, radius + 3f, selectedStarPaint);
                } else {
                    canvas.drawCircle(x, y, radius, starPaint);
                }
            }
            
            // 선택된 별의 이름과 설명 표시
            if (showStarName && selectedStar != null) {
                float x = selectedStar.position.x * scaleX;
                float y = selectedStar.position.y * scaleY;
                
                // 간단하게 이름, 밝기, 온도만 표시
                canvas.drawText(selectedStar.name, x - 60, y - 35, starNamePaint);
                
                // 별 밝기와 온도 정보만 표시
                String detailText = String.format("밝기: %.1f등급 | 온도: %,dK", 
                               selectedStar.magnitude, selectedStar.temperature);
                canvas.drawText(detailText, x - 60, y - 5, starDetailPaint);
            }
        }
    }
    
    // 별 데이터 클래스 - 밝기와 온도 필드 추가
    private static class Star {
        Point position;
        String name;
        String description;
        float magnitude;  // 별의 밝기 (등급, 작을수록 밝음)
        int temperature;  // 별의 온도 (켈빈)
        
        Star(Point position, String name) {
            this.position = position;
            this.name = name;
            this.description = "";
            this.magnitude = 0.0f;
            this.temperature = 0;
        }
        
        Star(Point position, String name, String description) {
            this.position = position;
            this.name = name;
            this.description = description;
            this.magnitude = 0.0f;
            this.temperature = 0;
        }
        
        Star(Point position, String name, String description, float magnitude, int temperature) {
            this.position = position;
            this.name = name;
            this.description = description;
            this.magnitude = magnitude;
            this.temperature = temperature;
        }
    }
    
    // 별자리 데이터 클래스
    private static class Constellation {
        List<Star> stars = new ArrayList<>();
        List<Point[]> lines = new ArrayList<>();
    }

    // 별자리 이름을 한글로 반환하는 메소드
    public static String getConstellationName(String code) {
        switch (code) {
            case "aries": return "양자리";
            case "taurus": return "황소자리";
            case "gemini": return "쌍둥이자리";
            case "cancer": return "게자리";
            case "leo": return "사자자리";
            case "virgo": return "처녀자리";
            case "libra": return "천칭자리";
            case "scorpio": return "전갈자리";
            case "sagittarius": return "궁수자리";
            case "capricorn": return "염소자리";
            case "aquarius": return "물병자리";
            case "pisces": return "물고기자리";
            default: return "";
        }
    }
    
    // 생일로 별자리 코드 가져오기
    public static String getConstellationCodeByDate(int month, int day) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) {
            return "aries"; // 양자리: 3월 21일 ~ 4월 19일
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            return "taurus"; // 황소자리: 4월 20일 ~ 5월 20일
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
            return "gemini"; // 쌍둥이자리: 5월 21일 ~ 6월 21일
        } else if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) {
            return "cancer"; // 게자리: 6월 22일 ~ 7월 22일
        } else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) {
            return "leo"; // 사자자리: 7월 23일 ~ 8월 22일
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            return "virgo"; // 처녀자리: 8월 23일 ~ 9월 22일
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) {
            return "libra"; // 천칭자리: 9월 23일 ~ 10월 22일
        } else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) {
            return "scorpio"; // 전갈자리: 10월 23일 ~ 11월 21일
        } else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) {
            return "sagittarius"; // 궁수자리: 11월 22일 ~ 12월 21일
        } else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) {
            return "capricorn"; // 염소자리: 12월 22일 ~ 1월 19일
        } else if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            return "aquarius"; // 물병자리: 1월 20일 ~ 2월 18일
        } else {
            return "pisces"; // 물고기자리: 2월 19일 ~ 3월 20일
        }
    }
} 