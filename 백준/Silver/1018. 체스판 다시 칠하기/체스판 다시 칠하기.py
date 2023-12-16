N, M = map(int, input().split())
chess_stage = ""
for _ in range(N):
    chess_stage += input().split()[0]
# chess_stage = input().replace(" ","")
correct_stage_W = "WBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBW"
correct_stage_B = "BWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWBBWBWBWBWWBWBWBWB"
min = 2500
for x in range(N-7):
    for y in range(M-7):
        cnt_B = 0
        cnt_W = 0
        temp_stage=""
        for i in range(8):
                temp_stage+=chess_stage[x*M+y +M*i : x*M+y+8 + M*i]
        for i in range(64):
            if temp_stage[i] != correct_stage_B[i]:
                    cnt_B+=1
            if temp_stage[i] != correct_stage_W[i]:
                    cnt_W+=1
        if min > cnt_B:
                min = cnt_B
        if min > cnt_W:
                min = cnt_W
print(min)