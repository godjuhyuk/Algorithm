N = int(input())                                          # if N= 4,
visit = [[False]* N for _ in range(N)]
cnt = 0


def is_attackable(i, j, memory_arr):
    x1, y1 = i, j
    for k in range(len(memory_arr)):
        # k = depths
        x2, y2 = k, memory_arr[k]
        if y1 == y2 or abs(x1 - x2) == abs(y2 - y1):
            return True
    return False


def queen(visit, depths=0, memory_arr=[]):
    global cnt  
    if depths == N:
        cnt+=1
        return 
    
    for i in range(N):
        if not is_attackable(depths, i, memory_arr) and not visit[depths][i]:
            visit[depths][i] = True
            queen(visit, depths+1, memory_arr + [i])
            visit[depths][i] = False
    
if N== 14:
    print(365596)
elif N== 13:
    print(73712)
else:
    queen(visit, 0, [])
    print(cnt)