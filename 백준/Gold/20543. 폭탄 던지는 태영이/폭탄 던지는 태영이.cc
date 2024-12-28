
#include<iostream>
#include<vector>
using namespace std;

int n, m;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    cin >> n >> m;
    
    // 초기 board 생성 (위쪽은 0으로 채우고, 아래쪽만 입력 받음)
    vector<vector<long long>> map(n, vector<long long>(n, 0));
    
    // N-M+1개의 행만 입력 받음
    for(int i = 0; i < n-m+1; i++) {
        for(int j = 0; j < n; j++) {
            cin >> map[i+m-1][j];  // m-1 이후의 행에 입력
            map[i+m-1][j] *= -1;
        }
    }
    
    // 세로 방향 처리
    for(int j = 0; j < n; j++) {
        long long t = 0;
        for(int i = m-1; i < n; i++) {
            map[i][j] -= t;
            t += map[i][j] - map[i-m+1][j];
        }
    }
    
    // m-1 행만큼 위쪽 제거
    vector<vector<long long>> temp;
    for(int i = m-1; i < n; i++) {
        temp.push_back(map[i]);
    }
    map = temp;
    
    // 각 행의 왼쪽에 m-1개의 0 추가
    temp.clear();
    for(int i = 0; i < map.size(); i++) {
        vector<long long> newRow(m-1, 0);
        for(int j = 0; j < n-m+1; j++) {
            newRow.push_back(map[i][j]);
        }
        temp.push_back(newRow);
    }
    map = temp;
    
    // 가로 방향 처리
    for(int i = 0; i < map.size(); i++) {
        long long t = 0;
        for(int j = m-1; j < n; j++) {
            map[i][j] -= t;
            t += map[i][j] - map[i][j-m+1];
        }
    }
    
    // m-1 열만큼 왼쪽 제거
    for(int i = 0; i < map.size(); i++) {
        vector<long long> newRow(map[i].begin() + m-1, map[i].end());
        map[i] = newRow;
    }
    
    // 패딩 추가하여 최종 결과 생성
    vector<vector<long long>> board(n, vector<long long>(n, 0));
    int padding = m/2;
    for(int i = 0; i < n-m+1; i++) {
        for(int j = 0; j < n-m+1; j++) {
            board[i+padding][j+padding] = map[i][j];
        }
    }
    
    // 결과 출력
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cout << (board[i][j] > 0 ? board[i][j] : 0) << " ";
        }
        cout << "\n";
    }
    
    return 0;
}