/*
 * [문제 14-1] 상속과 생성자의 호출
 *
 * 다음 클래스 각각에 적절한 생성자를 삽입해보자. 물론 상속 관계를 고려하여 각 클래스 별로 필
 * 요한 생성자를 삽입해야 한다.
 *
 *     class Car {          // 기본 연료 자동차
 *         int gasolineGauge;       // 가솔린 잔여량
 *     }
 *     class HybridCar extends Car {        // 하이브리드 자동차
 *         int electricGauge;       // 전기 배터리 잔여량
 *     }
 *     class HybridWaterCar extends HybridCar {     // 하이브리드 워터카
 *         int waterGauge;      // 에너지 전환용 물의 잔여량
 *         public void showCurrentGauge() {
 *             System.out.println("잔여 가솔린: " + gasolineGauge);
 *             System.out.println("잔여 전기량: " + electricGauge);
 *             System.out.println("잔여 워터량: " + waterGauge);
 *         }
 *     }
 *
 * Car는 가솔린으로 동작하는 자동차를 표현한 것이고, HybridCar는 가솔린과 전기로 동작하는
 * 자동차를 표현한 것이다. 그리고 HybridWaterCar는 가솔린과 전기뿐 아니라, 물도 동시에
 * 연료로 사용할 수 있는 꿈의 자동차를 표현한 것이다. 그러나 이 문제는 위의 클래스들이 의미하
 * 는 바를 몰라도 해결이 가능하다.
 */

class Car {          // 기본 연료 자동차
    int gasolineGauge;       // 가솔린 잔여량

    Car(int gasolineGauge) {
        this.gasolineGauge = gasolineGauge;
    }
}

class HybridCar extends Car {        // 하이브리드 자동차
    int electricGauge;       // 전기 배터리 잔여량

    HybridCar(int gG, int electricGauge) {
        super(gG);
        this.electricGauge = electricGauge;
    }
}

class HybridWaterCar extends HybridCar {     // 하이브리드 워터카
    int waterGauge;      // 에너지 전환용 물의 잔여량

    HybridWaterCar(int gG, int eG, int waterGauge) {
        super(gG, eG);
        this.waterGauge = waterGauge;
    }

    public void showCurrentGauge() {
        System.out.println("잔여 가솔린: " + gasolineGauge);
        System.out.println("잔여 전기량: " + electricGauge);
        System.out.println("잔여 워터량: " + waterGauge);
    }
}

public class Answer01 {
    public static void main(String[] args) {
        HybridWaterCar c1 = new HybridWaterCar(1,1,1);
        c1.showCurrentGauge();
    }
}
