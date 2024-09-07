import java.util.*;

// 00:09 START!
// 그래프 탐색
class Solution {
    /**
     * 💡 모든 그래프의 특징을 정리해보자
     *  - 도넛 모양 그래프: 모든 정점이 in 1 / out 1 (돌다보면 자신으로 돌아옴)
     *  - 막대 모양 그래프: 정점이 in 1 / out 1 but 시작 정점은 out 1, 마지막 정점은 in 1 (돌다보면 out이 없는 정점이 있음)
     *  - 8자 모양 그래프: 모든 정점이 in 1 / out 1 but 중간에 in 2 / out 2 정점이 존재 (돌다보면 in 2 / out 2 정점이 있음)
     */
    /**
     * 💡 생성한 정점은 어떻게 구할까?
     *  - in이 없고 out만 2개 이상 (*도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 수의 합은 2이상)
     */
    static int N = 1_000_001;

    public int[] solution(int[][] edges) {
        int[] in = new int[N]; // in[i]: i 노드로 들어오는 노드 수
        int[] out = new int[N]; // out[i]: i노드에서 나가는 노드 수
        for(int[] edge : edges) {
            in[edge[1]]++;
            out[edge[0]]++;
        }
        
        int start = 0;
        int stick = 0;
        int eight = 0;
        for(int i = 1; i < N; i++) {
            if(in[i] == 0 && out[i] >= 2) {
                start = i;
                continue;
            }
            
            if(in[i] >= 1 && out[i] == 0) {
                stick++;
                continue;
            }
            
            if(in[i] >= 2 && out[i] == 2){
                eight++;
                continue;
            }
        }
        
        return new int[] {start, out[start] - eight - stick, stick, eight};
    }
}