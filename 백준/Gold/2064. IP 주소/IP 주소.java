import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 설계 시작: 오후 7시 51분
 * 설계 종료: 오후 8시 8분
 * 구현 종료: 오후 9시 23분
 * 
 * 문제 해석)
 * IP네트워크 : 네트워크주소, 네트워크 마스크라는 두개의 정보로 표현
 * 
 * ip 주소는 4개의 바이트로 구성, 각각 10진수로 나타냄(앞에 0을붙이지않은형태)
 * 사이에 점을 찍어 주소를 표현한다.
 * 바이트이기 때문에 0부터 255까지의 값을 갖게됨.
 * 네트워크 주소와 마스크 역시 같음
 * 
 * IP네트워크를 이해하려면 위와같은 주소를 2진수로 이해하면됨.
 * 각각의 바이트를 8자리로 나타내고
 * 이를 네 개 붙여놓은 32자리 이진수를 생각해보자.
 * 
 * IP네트워크는 기본적으로 2^m개의 컴퓨터(ip주소)가 할당될 수 있음
 * 이 경우의 네트워크 주소는 앞의 32 - m 자리가 임의의 수(0 또는 1)로 구성
 * 뒤의 m자리는 0으로 채워짐
 * 
 * 네트우크 마스크는 앞의 32-m 자리가 1로, 뒤의 m자리가 0으로 채워짐
 * 이와 같은 IP 네트워크는 앞의 32-m자리가 네트워크 주소와 일치하는 모든 Ip들이 포함
 * 
 * 예시)
 * 네트워크 주소 : 194.85.160.176
 * 			=> 이진법으로 나타내면?
 * 
 * 네트워크 마스크 : 11111111.11111111.11111111.11111000
 * 
 * 
 * 
 * => 주어진 네트워크 주소를 전부 나열한다.
 * => 1000개를 돌아가며 m을 갱신해가며 찾는다. => 1000 * 32
 * 서브넷마스크와 32-m에 전부 0을 채운 네트워크 주소를 출력한다.
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
//		System.out.println(Integer.toBinaryString(194));
//		System.out.println(Integer.toBinaryString(85));
//		System.out.println(Integer.toBinaryString(160));
//		System.out.println(Integer.toBinaryString(177));
		
//		System.out.println(Integer.toBinaryString(194));
//		System.out.println(Integer.toBinaryString(85));
//		System.out.println(Integer.toBinaryString(160));
//		System.out.println(Integer.toBinaryString(183));
		
//		System.out.println(Integer.toBinaryString(194));
//		System.out.println(Integer.toBinaryString(85));
//		System.out.println(Integer.toBinaryString(160));
//		System.out.println(Integer.toBinaryString(178));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int first = Integer.parseInt(st.nextToken("."));
		int second = Integer.parseInt(st.nextToken("."));
		int third = Integer.parseInt(st.nextToken("."));
		int fourth = Integer.parseInt(st.nextToken("."));
		
		List<int[]> ipList = new ArrayList<int[]>();
		ipList.add(new int[] {first, second, third, fourth});
		
		int[] networkIp = new int[4];
		int[] networkMask = new int[4];
		
		if(N==1) {
			// if( (fourth & 1 << 0) > 0) fourth -= 1 << 0;
			System.out.println(first + "." + second + "." + third + "." + fourth);
			System.out.println("255.255.255.255");
			return;
		}
		
		
		int min = 32;
		for(int k=1; k<N; k++) {
			
			st = new StringTokenizer(br.readLine());
			int[] newIp = new int[4];
			for(int i=0; i<4; i++) newIp[i] = Integer.parseInt(st.nextToken("."));
			
				// 새로운 IP 입력받을 때 마다 ipArr에 들어있는 모든 ip 확인 후 m 최솟값 갱신
			check:
			for(int j=0; j < ipList.size(); j++) {
				
				int m = 0;

				// 4칸 확인
				for(int i=0; i<4; i++) {
					
					int oldIp = ipList.get(j)[i];
					int tempIp = newIp[i];
					int len1 = Integer.toBinaryString(oldIp).length();
					int len2 = Integer.toBinaryString(tempIp).length();
					
					// 길이가 다르다 => 같지 않은 부분이 존재한다.
					// 			  => 갱신하고 끝냄
					if(len1 != len2) {
						m += 8 - Math.max(len1, len2);
						min = Math.min(min, m);
						continue check;
					}
					
					boolean flag = true;
					
					int cnt = 0;
					for(int a=7; a>=0; a--) {
						// 다른 부분이 생기면 갱신하고 끝냄
						if( (oldIp & 1 << a) !=  (tempIp & 1 << a)) {
							flag = false;
							min = Math.min(min, m+cnt);
							continue check;
						}
						cnt++;
					}
					
					if(flag) {
						m+= 8;
					}
					
				}
				
				min = Math.min(min, m);
			}
			ipList.add(newIp);
		}
			
		
		int idx = 0;
		int cnt = 0;
		
		while(cnt < min) {
			
			int subIp = 0;
			int	subMask = 0;
			 
			for(int i=7; i>=0 && cnt < min; i--, cnt++) {
				
				subMask |= 1 << i;
				subIp += (ipList.get(0)[idx] & 1 << i);
			}
			networkIp[idx] = subIp;
			networkMask[idx] = subMask;
			idx++;
		}
		
		
		System.out.printf("%d.%d.%d.%d%n", networkIp[0], networkIp[1], networkIp[2], networkIp[3]);
		System.out.printf("%d.%d.%d.%d", networkMask[0], networkMask[1], networkMask[2], networkMask[3]);
		
		
	}

}
