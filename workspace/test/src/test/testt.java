package test;

import java.util.Scanner;

public class testt {
	
	public static void main(String[] args) {
		 int answer = 0;//입력 받을 숫자
	        int sumNum; //합한 값 저장
	        Scanner s = new Scanner(System.in);
	        
	        System.out.printf("숫자를 입력하시오:");
	        answer = s.nextInt();
	        
	        sumNum = digitNumber(answer);
	        
	        System.out.printf("각 자리수의 합은 %d 입니다.", sumNum);
	        s.close();
	}
	public static int digitNumber(int answer){
        int digitNum;
        int sum=0;
        while(answer>0){
            digitNum = answer % 10;//3//2
            System.out.println(answer + "% 10 = " + answer % 10);
            answer /= 10;//12//1
            sum += digitNum;//3+2
        }
        return sum;
    }

}

