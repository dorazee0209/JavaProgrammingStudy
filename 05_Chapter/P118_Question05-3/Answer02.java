/*
 * [문제 05-3] switch문의 활용 - 문제 2
 *
 * 아래의 코드를 if ~ else문이 아닌 switch문을 이용해는 형태로 변경해보자.
 *
 *     class NumberRange {
 *         public static void main(String[] args) {
 *             int n = 24;
 *             if(n >= 0 && n < 10)
 *                 System.out.println("0이상 10미만의 수");
 *             else if(n >= 10 && n < 20)
 *                 System.out.println("10이상 20미만의 수");
 *             else if(n >= 20 && n < 30)
 *                 System.out.println("20이상 30미만의 수");
 *             else
 *                 System.out.println("음수 혹은 30 이상의 수");
 *         }
 *     }
 */

public class Answer02{
    public static void main(String[] args){
        int n = 24;

        switch(n) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
            System.out.println("0이상 10미만의 수");
            break;

        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
            System.out.println("10이상 20미만의 수");
            break;

        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 26:
        case 27:
        case 28:
        case 29:
            System.out.println("20이상 30미만의 수");
            break;
        
        default:
            System.out.println("음수 혹은 30 이상의 수");
        }
    }
}

/*
 * [피드백] 동작은 ⭕️ 정답. fall-through(case 쌓기)도 정확히 활용함.
 *
 * 다만 0~29까지 case 30개를 일일이 나열했는데, 범위가 커지면 case가 폭발한다.
 * 답안 키는 정수 나눗셈으로 "범위"를 하나의 대표값으로 압축한다:
 *
 *     switch(n / 10) {     // 24/10 = 2  → case 2
 *     case 0:  ...0이상 10미만...  break;   // 0~9   → 몫 0
 *     case 1:  ...10이상 20미만... break;   // 10~19 → 몫 1
 *     case 2:  ...20이상 30미만... break;   // 20~29 → 몫 2
 *     default: ...음수 혹은 30 이상...      // 나머지
 *     }
 *
 * 핵심: switch는 ==(상수)만 비교 가능 → 범위를 / 로 값 하나로 줄여서 매칭.
 * (C도 fall-through·case 상수 제약 동일)
 */
