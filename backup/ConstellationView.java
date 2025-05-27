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
    private Paint starInfoPaint;
    private List<Point> stars;
    private List<Point[]> lines;
    private Map<String, Constellation> constellations;
    private String currentConstellation;
    private Random random;
    private List<Point> backgroundStars;
    private Star selectedStar;
    private StarClickListener starClickListener;
    
    public interface StarClickListener {
        void onStarClick(Star star);
    }
    
    public void setStarClickListener(StarClickListener listener) {
        this.starClickListener = listener;
    }
    
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
        selectedStarPaint.setColor(Color.parseColor("#FFEA00"));
        selectedStarPaint.setStyle(Paint.Style.FILL);
        selectedStarPaint.setAntiAlias(true);
        
        starInfoPaint = new Paint();
        starInfoPaint.setColor(Color.parseColor("#FFFFFF"));
        starInfoPaint.setTextSize(30f);
        starInfoPaint.setAntiAlias(true);
        
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
        Constellation aries = new Constellation();
        aries.stars.add(new Star("하멜", "양자리의 가장 밝은 별", new Point(200, 200), 3.9f));
        aries.stars.add(new Star("셰라탄", "양자리의 베타별", new Point(250, 180), 2.7f));
        aries.stars.add(new Star("메사팀", "양자리의 감마별", new Point(320, 170), 3.8f));
        aries.stars.add(new Star("보테인", "양자리의 델타별", new Point(380, 190), 4.3f));
        aries.lines.add(new Point[]{aries.stars.get(0).position, aries.stars.get(1).position});
        aries.lines.add(new Point[]{aries.stars.get(1).position, aries.stars.get(2).position});
        aries.lines.add(new Point[]{aries.stars.get(2).position, aries.stars.get(3).position});
        constellations.put("aries", aries);
        
        // 황소자리 (4월 20일 ~ 5월 20일)
        Constellation taurus = new Constellation();
        taurus.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(180, 200), 0.9f));
        taurus.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(230, 180), 1.7f));
        taurus.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(280, 190), 2.9f));
        taurus.stars.add(new Star("아틱", "황소자리의 제타별", new Point(330, 170), 3.0f));
        taurus.stars.add(new Star("테제트", "황소자리의 에타별", new Point(300, 240), 2.9f));
        taurus.stars.add(new Star("아인", "황소의 눈을 표현", new Point(350, 230), 3.5f));
        taurus.stars.add(new Star("참프", "황소의 머리 부분", new Point(400, 250), 4.3f));
        taurus.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(250, 260), 3.8f));
        taurus.lines.add(new Point[]{taurus.stars.get(0).position, taurus.stars.get(1).position});
        taurus.lines.add(new Point[]{taurus.stars.get(1).position, taurus.stars.get(2).position});
        taurus.lines.add(new Point[]{taurus.stars.get(2).position, taurus.stars.get(3).position});
        taurus.lines.add(new Point[]{taurus.stars.get(2).position, taurus.stars.get(4).position});
        taurus.lines.add(new Point[]{taurus.stars.get(4).position, taurus.stars.get(5).position});
        taurus.lines.add(new Point[]{taurus.stars.get(5).position, taurus.stars.get(6).position});
        taurus.lines.add(new Point[]{taurus.stars.get(4).position, taurus.stars.get(7).position});
        constellations.put("taurus", taurus);
        
        // 쌍둥이자리 (5월 21일 ~ 6월 21일)
        Constellation gemini = new Constellation();
        gemini.stars.add(new Star("카스토르", "쌍둥이의 한 명을 표현, 다중성", new Point(200, 150), 1.6f));
        gemini.stars.add(new Star("폴룩스", "쌍둥이의 한 명을 표현, 주황색", new Point(200, 200), 1.1f));
        gemini.stars.add(new Star("알헤나", "쌍둥이자리의 감마별", new Point(200, 250), 1.9f));
        gemini.stars.add(new Star("와삿", "쌍둥이의 발 부분", new Point(200, 300), 3.6f));
        gemini.stars.add(new Star("메부타", "폴룩스의 오른쪽 별", new Point(300, 150), 2.9f));
        gemini.stars.add(new Star("테드자트", "쌍둥이의 오른쪽 몸통", new Point(300, 200), 3.0f));
        gemini.stars.add(new Star("프로피온", "쌍둥이의 무릎 부분", new Point(300, 250), 3.3f));
        gemini.stars.add(new Star("알주르자", "쌍둥이의 발 부분", new Point(300, 300), 3.0f));
        gemini.stars.add(new Star("멩칼리난", "두 쌍둥이를 연결하는 별", new Point(250, 180), 4.1f));
        gemini.lines.add(new Point[]{gemini.stars.get(0).position, gemini.stars.get(1).position});
        gemini.lines.add(new Point[]{gemini.stars.get(1).position, gemini.stars.get(2).position});
        gemini.lines.add(new Point[]{gemini.stars.get(2).position, gemini.stars.get(3).position});
        gemini.lines.add(new Point[]{gemini.stars.get(4).position, gemini.stars.get(5).position});
        gemini.lines.add(new Point[]{gemini.stars.get(5).position, gemini.stars.get(6).position});
        gemini.lines.add(new Point[]{gemini.stars.get(6).position, gemini.stars.get(7).position});
        gemini.lines.add(new Point[]{gemini.stars.get(1).position, gemini.stars.get(8).position});
        gemini.lines.add(new Point[]{gemini.stars.get(8).position, gemini.stars.get(5).position});
        constellations.put("gemini", gemini);
        
        // 게자리 (6월 22일 ~ 7월 22일)
        Constellation cancer = new Constellation();
        cancer.stars.add(new Star("아크루벤스", "게자리의 알파별", new Point(250, 180), 4.3f));
        cancer.stars.add(new Star("알타르프", "게자리의 베타별", new Point(300, 150), 3.5f));
        cancer.stars.add(new Star("아셀루스 보레알리스", "북쪽 당나귀별", new Point(350, 180), 4.7f));
        cancer.stars.add(new Star("아셀루스 아우스트랄리스", "남쪽 당나귀별", new Point(370, 240), 3.9f));
        cancer.stars.add(new Star("테구민", "게자리의 제타별", new Point(300, 250), 4.7f));
        cancer.stars.add(new Star("아셀루스", "게자리의 작은 별", new Point(230, 240), 5.1f));
        cancer.lines.add(new Point[]{cancer.stars.get(0).position, cancer.stars.get(1).position});
        cancer.lines.add(new Point[]{cancer.stars.get(1).position, cancer.stars.get(2).position});
        cancer.lines.add(new Point[]{cancer.stars.get(2).position, cancer.stars.get(3).position});
        cancer.lines.add(new Point[]{cancer.stars.get(3).position, cancer.stars.get(4).position});
        cancer.lines.add(new Point[]{cancer.stars.get(4).position, cancer.stars.get(5).position});
        cancer.lines.add(new Point[]{cancer.stars.get(5).position, cancer.stars.get(0).position});
        constellations.put("cancer", cancer);
        
        // 사자자리 (7월 23일 ~ 8월 22일)
        Constellation leo = new Constellation();
        leo.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(180, 200), 0.9f));
        leo.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(230, 170), 1.7f));
        leo.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(280, 180), 2.9f));
        leo.stars.add(new Star("아틱", "황소자리의 제타별", new Point(340, 160), 3.0f));
        leo.stars.add(new Star("테제트", "황소자리의 에타별", new Point(390, 200), 2.9f));
        leo.stars.add(new Star("아인", "황소의 눈을 표현", new Point(360, 250), 3.5f));
        leo.stars.add(new Star("참프", "황소의 머리 부분", new Point(300, 270), 4.3f));
        leo.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(250, 260), 3.8f));
        leo.stars.add(new Star("멩칼리난", "두 쌍둥이를 연결하는 별", new Point(280, 220), 4.1f));
        leo.lines.add(new Point[]{leo.stars.get(0).position, leo.stars.get(1).position});
        leo.lines.add(new Point[]{leo.stars.get(1).position, leo.stars.get(2).position});
        leo.lines.add(new Point[]{leo.stars.get(2).position, leo.stars.get(3).position});
        leo.lines.add(new Point[]{leo.stars.get(3).position, leo.stars.get(4).position});
        leo.lines.add(new Point[]{leo.stars.get(4).position, leo.stars.get(5).position});
        leo.lines.add(new Point[]{leo.stars.get(5).position, leo.stars.get(6).position});
        leo.lines.add(new Point[]{leo.stars.get(6).position, leo.stars.get(7).position});
        leo.lines.add(new Point[]{leo.stars.get(2).position, leo.stars.get(8).position});
        leo.lines.add(new Point[]{leo.stars.get(8).position, leo.stars.get(6).position});
        constellations.put("leo", leo);
        
        // 처녀자리 (8월 23일 ~ 9월 22일)
        Constellation virgo = new Constellation();
        virgo.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(180, 150), 0.9f));
        virgo.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(230, 180), 1.7f));
        virgo.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(270, 220), 2.9f));
        virgo.stars.add(new Star("아틱", "황소자리의 제타별", new Point(310, 260), 3.0f));
        virgo.stars.add(new Star("테제트", "황소자리의 에타별", new Point(350, 290), 2.9f));
        virgo.stars.add(new Star("아인", "황소의 눈을 표현", new Point(330, 220), 3.5f));
        virgo.stars.add(new Star("참프", "황소의 머리 부분", new Point(380, 180), 4.3f));
        virgo.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(270, 170), 3.8f));
        virgo.stars.add(new Star("멩칼리난", "두 쌍둥이를 연결하는 별", new Point(230, 230), 4.1f));
        virgo.lines.add(new Point[]{virgo.stars.get(0).position, virgo.stars.get(1).position});
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(2).position});
        virgo.lines.add(new Point[]{virgo.stars.get(2).position, virgo.stars.get(3).position});
        virgo.lines.add(new Point[]{virgo.stars.get(3).position, virgo.stars.get(4).position});
        virgo.lines.add(new Point[]{virgo.stars.get(2).position, virgo.stars.get(5).position});
        virgo.lines.add(new Point[]{virgo.stars.get(5).position, virgo.stars.get(6).position});
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(7).position});
        virgo.lines.add(new Point[]{virgo.stars.get(1).position, virgo.stars.get(8).position});
        constellations.put("virgo", virgo);
        
        // 천칭자리 (9월 23일 ~ 10월 22일)
        Constellation libra = new Constellation();
        libra.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(200, 180), 0.9f));
        libra.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(280, 180), 1.7f));
        libra.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(240, 220), 2.9f));
        libra.stars.add(new Star("아틱", "황소자리의 제타별", new Point(200, 260), 3.0f));
        libra.stars.add(new Star("테제트", "황소자리의 에타별", new Point(280, 260), 2.9f));
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(1).position});
        libra.lines.add(new Point[]{libra.stars.get(0).position, libra.stars.get(2).position});
        libra.lines.add(new Point[]{libra.stars.get(1).position, libra.stars.get(2).position});
        libra.lines.add(new Point[]{libra.stars.get(2).position, libra.stars.get(3).position});
        libra.lines.add(new Point[]{libra.stars.get(2).position, libra.stars.get(4).position});
        constellations.put("libra", libra);
        
        // 전갈자리 (10월 23일 ~ 11월 21일)
        Constellation scorpio = new Constellation();
        scorpio.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(180, 180), 0.9f));
        scorpio.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(220, 190), 1.7f));
        scorpio.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(260, 200), 2.9f));
        scorpio.stars.add(new Star("아틱", "황소자리의 제타별", new Point(300, 220), 3.0f));
        scorpio.stars.add(new Star("테제트", "황소자리의 에타별", new Point(340, 240), 2.9f));
        scorpio.stars.add(new Star("아인", "황소의 눈을 표현", new Point(370, 270), 3.5f));
        scorpio.stars.add(new Star("참프", "황소의 앞다리 부분", new Point(390, 300), 4.3f));
        scorpio.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(400, 330), 3.8f));
        scorpio.stars.add(new Star("아인", "황소의 눈을 표현", new Point(360, 310), 3.5f));
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
        sagittarius.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(200, 180), 0.9f));
        sagittarius.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(240, 200), 1.7f));
        sagittarius.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(280, 170), 2.9f));
        sagittarius.stars.add(new Star("아틱", "황소자리의 제타별", new Point(320, 150), 3.0f));
        sagittarius.stars.add(new Star("테제트", "황소자리의 에타별", new Point(260, 220), 2.9f));
        sagittarius.stars.add(new Star("아인", "황소의 눈을 표현", new Point(300, 250), 3.5f));
        sagittarius.stars.add(new Star("참프", "황소의 머리 부분", new Point(350, 200), 4.3f));
        sagittarius.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(380, 230), 3.8f));
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
        capricorn.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(200, 180), 0.9f));
        capricorn.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(240, 150), 1.7f));
        capricorn.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(280, 160), 2.9f));
        capricorn.stars.add(new Star("아틱", "황소자리의 제타별", new Point(320, 180), 3.0f));
        capricorn.stars.add(new Star("테제트", "황소자리의 에타별", new Point(350, 220), 2.9f));
        capricorn.stars.add(new Star("아인", "황소의 눈을 표현", new Point(330, 260), 3.5f));
        capricorn.stars.add(new Star("참프", "황소의 머리 부분", new Point(270, 240), 4.3f));
        capricorn.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(230, 220), 3.8f));
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
        aquarius.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(200, 150), 0.9f));
        aquarius.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(240, 180), 1.7f));
        aquarius.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(280, 200), 2.9f));
        aquarius.stars.add(new Star("아틱", "황소자리의 제타별", new Point(320, 220), 3.0f));
        aquarius.stars.add(new Star("테제트", "황소자리의 에타별", new Point(290, 250), 2.9f));
        aquarius.stars.add(new Star("아인", "황소의 눈을 표현", new Point(330, 260), 3.5f));
        aquarius.stars.add(new Star("참프", "황소의 머리 부분", new Point(250, 250), 4.3f));
        aquarius.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(210, 270), 3.8f));
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
        pisces.stars.add(new Star("알데바란", "붉은 색을 띄는 1등성", new Point(180, 180), 0.9f));
        pisces.stars.add(new Star("엘나트", "황소의 뿔 끝에 위치", new Point(220, 160), 1.7f));
        pisces.stars.add(new Star("알시오네", "플레이아데스 성단의 별", new Point(250, 170), 2.9f));
        pisces.stars.add(new Star("아틱", "황소자리의 제타별", new Point(280, 190), 3.0f));
        pisces.stars.add(new Star("테제트", "황소자리의 에타별", new Point(320, 200), 2.9f));
        pisces.stars.add(new Star("아인", "황소의 눈을 표현", new Point(350, 180), 3.5f));
        pisces.stars.add(new Star("참프", "황소의 앞다리 부분", new Point(380, 190), 4.3f));
        pisces.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(350, 230), 3.8f));
        pisces.stars.add(new Star("아인", "황소의 눈을 표현", new Point(320, 240), 3.5f));
        pisces.stars.add(new Star("참프", "황소의 앞다리 부분", new Point(280, 220), 4.3f));
        pisces.stars.add(new Star("호드", "황소의 앞다리 부분", new Point(250, 230), 3.8f));
        pisces.stars.add(new Star("아인", "황소의 눈을 표현", new Point(220, 210), 3.5f));
        pisces.lines.add(new Point[]{pisces.stars.get(0).position, pisces.stars.get(1).position});
        pisces.lines.add(new Point[]{pisces.stars.get(1).position, pisces.stars.get(2).position});
        pisces.lines.add(new Point[]{pisces.stars.get(2).position, pisces.stars.get(3).position});
        pisces.lines.add(new Point[]{pisces.stars.get(3).position, pisces.stars.get(4).position});
        pisces.lines.add(new Point[]{pisces.stars.get(4).position, pisces.stars.get(5).position});
        pisces.lines.add(new Point[]{pisces.stars.get(5).position, pisces.stars.get(6).position});
        pisces.lines.add(new Point[]{pisces.stars.get(4).position, pisces.stars.get(7).position});
        pisces.lines.add(new Point[]{pisces.stars.get(7).position, pisces.stars.get(8).position});
        pisces.lines.add(new Point[]{pisces.stars.get(8).position, pisces.stars.get(9).position});
        pisces.lines.add(new Point[]{pisces.stars.get(9).position, pisces.stars.get(10).position});
        pisces.lines.add(new Point[]{pisces.stars.get(10).position, pisces.stars.get(11).position});
        pisces.lines.add(new Point[]{pisces.stars.get(11).position, pisces.stars.get(0).position});
        constellations.put("pisces", pisces);
    }
    
    public void setConstellation(String constellation) {
        this.currentConstellation = constellation;
        this.selectedStar = null;
        
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();
            
            if (currentConstellation != null && constellations.containsKey(currentConstellation)) {
                Constellation constellation = constellations.get(currentConstellation);
                float scaleX = (float) getWidth() / 500f;
                float scaleY = (float) getHeight() / 500f;
                
                // 터치한 위치와 가장 가까운 별 찾기
                Star closestStar = null;
                float minDistance = Float.MAX_VALUE;
                
                for (Star star : constellation.stars) {
                    float starX = star.position.x * scaleX;
                    float starY = star.position.y * scaleY;
                    
                    float distance = (float) Math.sqrt(Math.pow(touchX - starX, 2) + Math.pow(touchY - starY, 2));
                    
                    // 터치 위치가 별 반경(15dp) 내에 있는지 확인
                    if (distance < 30 && distance < minDistance) {
                        minDistance = distance;
                        closestStar = star;
                    }
                }
                
                if (closestStar != null) {
                    selectedStar = closestStar;
                    invalidate();
                    
                    if (starClickListener != null) {
                        starClickListener.onStarClick(selectedStar);
                    }
                    
                    return true;
                } else {
                    // 별을 클릭하지 않은 경우 선택 해제
                    selectedStar = null;
                    invalidate();
                }
            }
        }
        
        return super.onTouchEvent(event);
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
                float radius = (5.5f - star.magnitude) + random.nextFloat() * 1.5f; // 별의 밝기에 따라 크기 조정
                
                // 선택된 별은 다른 색상으로 표시
                if (selectedStar != null && star.name.equals(selectedStar.name)) {
                    canvas.drawCircle(x, y, radius + 5, selectedStarPaint);
                } else {
                    canvas.drawCircle(x, y, radius, starPaint);
                }
            }
        }
    }
    
    // 별 데이터 클래스
    public static class Star {
        public String name;
        public String description;
        public Point position;
        public float magnitude; // 별의 밝기 (낮을수록 밝음)
        
        public Star(String name, String description, Point position, float magnitude) {
            this.name = name;
            this.description = description;
            this.position = position;
            this.magnitude = magnitude;
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